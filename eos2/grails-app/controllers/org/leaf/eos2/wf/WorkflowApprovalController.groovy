package org.leaf.eos2.wf

class WorkflowApprovalController {

	def workflowStepDispatcherService
	
	def workflowStepRollbackService
		
	//跳到审批页面，现已不使用
	def confirm = {
		def action = WorkflowAction.get(params.actionId) 
		render template : '/wf/approval/confirm', model : [
			objectName : params.objectName
			, objectId : params.objectId
			, historyId : params.historyId
			, actionId : params.actionId
			, ownerId : params.ownerId
			, action : action
			, version : params.version
		]	
	}
	//判断是否可以审批
		//actionId, ownerId		检查分配人
		//historyId, version	检查历史
	def check ={
		def result = true
		
		def historyId = params.int( 'historyId' )?: -1L
		//log.info("historyId=${historyId}")
		
		def version = params.version
		//log.info("version=${version}")
		
		def actionId = params.int( 'actionId' )?: -1L
		//log.info("actionId=${actionId}")
		
		def ownerId = params.int( 'ownerId' )?: -1L
		//log.info("ownerId=${ownerId}")
		
		def checkHistory = workflowStepDispatcherService.checkHistory(historyId, version)
		//log.info("checkHistory=${checkHistory}")
		if(checkHistory == true){
			def checkAssignee = workflowStepDispatcherService.checkAssignee(actionId, ownerId)
			if(checkAssignee != true){
				result = false
			}
		}else{
			result = false
		}
		//log.info("${(result== true)?"Y":"N"}")
		render "${(result== true)?"Y":"N"}"
	}
	
	//执行审批
  	def approval = {
		workflowStepDispatcherService.checkWorkFlowStep(params.objectName, params.objectId, params.historyId, params.actionId, params.ownerId, params.description, params.version)
		redirect(controller : params.objectName, action: "show", id: params.objectId)
	}
	
	def rollback = {
		//初始化
		//def workflowStepRollbackService = WorkflowStepRollbackService.init1(params.objectName, params.int('objectId').toLong(), params.int('historyId').toLong());
		//执行
		workflowStepRollbackService.rollback(params.objectName, params.int('objectId').toLong(), params.int('historyId').toLong())
		//返回
		redirect(controller : params.objectName, action: "show", id: params.objectId)
	}
}
