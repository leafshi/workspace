package org.leaf.eos2.wf

import org.apache.shiro.SecurityUtils

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.Group

class WorkflowStepDispatcherService {

    static transactional = true

	/*
	*获取当前
	*objectName 	表名
	*objectId		ID
	*historyId		历史
	*actionId		动作
	*ownerId		所有人		检查共享规则
	*description	描述
	*version		版本号		防止记录在审批时被修改
	*/
    def checkWorkFlowStep(objectName, objectId, historyId, actionId, ownerId, description, version) {
		WorkflowHistory.withTransaction{ status ->
    		try{
				//检查分配人
				if(!checkAssignee(actionId, ownerId)){
					return
				}
				//检查历史
				if(!checkHistory(historyId, version)){
					return
				}
				//获取下一步
				def nextStep = getNextStep(actionId)
				if(nextStep != null){//如果下一步不为空，则创建下一步
					if(createNextStep(nextStep, objectName, objectId)){//创建下一步
						//结束当前历史
						finishCurrentStep(historyId, description)
					}
				}else{//如果下一步为空，则结束当前历史
					finishCurrentStep(historyId, description)
				}
			}catch(e){
				status.setRollbackOnly()
			}
		}
    }

	//动作	获取step分配人（用户组），判断当前用户是否在组中
	//所有人	当step分配人为空时，分配人为当前用户
	def checkAssignee(actionId, ownerId){
		def result = false
        //获取当前用户
        def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		//获取当前step
		def currentStepId = WorkflowAction.get(actionId).belognsToStep?.id
		//获取分配人（用户组）
		def assigneeId = WorkflowStep.get(currentStepId)?.assignee?.id
		
		if(assigneeId == null){//如果分配人（用户组）为空，则判断当前用户是否与所有人一致
			result = User.get(ownerId).id == currentUser.id
		}else{//如果分配人（用户组不为空）， 则判断当前用户是否在组中
			Group.get(assigneeId).users.each{
				if(it.id == currentUser.id) {
					result = true
				}
			}
		}
		return result
	}
	
	//根据历史ID判断判断记录版本｜状态不等于‘已完成’
	def checkHistory(historyId, version){
		def result = true
		def history = WorkflowHistory.get(historyId)
		
		if(history.version != version.toLong() || history.status == '已完成'){
			result = false
		}
		return result
	}

	//根据动作获取下一步
	def getNextStep(actionId){
		def nextStepId = WorkflowAction.get(actionId)?.nextStep?.id
		def nextStep = null
		if(nextStepId != null){
			nextStep = WorkflowStep.get(nextStepId)
		}
		return nextStep
	}
	
	//根据下一步，对象，记录ID创建下一步
	def createNextStep(nextStep, objectName, objectId){
        //get user
        def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
        def history = new WorkflowHistory(
			  step : nextStep
			, objectName : objectName
			, objectId : objectId
			, status : (nextStep.isEnd)?'已完成':'进行中'
			, description : ''
			, createdBy : currentUser
			, lastModifiedBy : currentUser
			, dateCreated : new Date()
			, lastUpdated : new Date()
		).save(flush:true)
		
		return (history.ident())?true:false
	}
	//结束当前step
	def finishCurrentStep(historyId, description){
		def history = WorkflowHistory.get(historyId)
        	history.status = '已完成'
        	history.description = description
			history.save(flush:true)
	}
}
