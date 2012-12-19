package org.leaf.eos2.wf

import org.apache.shiro.SecurityUtils

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.Group

class WorkflowStepDispatcherService {

    static transactional = true

    def checkWorkFlowStep(objectName, objectId, historyId, actionId, ownerId, description, version) {
		if(!checkAssignee(actionId, ownerId)){
			return
		}
		if(!checkHistory(historyId, version)){
			return
		}
		def nextStep = getNextStep(actionId)
		if(nextStep != null){
			if(createNextStep(nextStep, objectName, objectId)){
				finishCurrentStep(historyId, description)
			}
		}else{
			finishCurrentStep(historyId, description)
		}
    }

	def checkAssignee(actionId, ownerId){
		
		def result = false
        //get user
        def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		
		def currentStepId = WorkflowAction.get(actionId).belognsToStep?.id
		def assigneeId = WorkflowStep.get(currentStepId)?.assignee?.id
		if(assigneeId == null){
			result = User.get(ownerId).id == currentUser.id
		}else{
			Group.get(assigneeId).users.each{
				if(it.id == currentUser.id) {
					result = true
				}
			}
		}
		return result
	}
	
	def checkHistory(historyId, version){
		def result = true
		def history = WorkflowHistory.get(historyId)
		
		if(history.version != version.toLong() || history.status == '已完成'){
			result = false
		}
		return result
	}

	def getNextStep(actionId){
		def nextStepId = WorkflowAction.get(actionId)?.nextStep?.id
		def nextStep = null
		if(nextStepId != null){
			nextStep = WorkflowStep.get(nextStepId)
		}
		return nextStep
	}
	
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
	
	def finishCurrentStep(historyId, description){
		def history = WorkflowHistory.get(historyId)
        history.status = '已完成'
        history.description = description
			  history.save(flush:true)
	}
}
