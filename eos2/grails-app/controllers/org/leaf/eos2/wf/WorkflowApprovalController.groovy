package org.leaf.eos2.wf

class WorkflowApprovalController {

	def workflowStepDispatcherService
	
	def confirm = {
		def action = WorkflowAction.get(params.actionId) 
		render view : '/wf/approval/confirm', model : [
			objectName : params.objectName
			, objectId : params.objectId
			, historyId : params.historyId
			, actionId : params.actionId
			, ownerId : params.ownerId
			, action : action
			, version : params.version
		]	
	}

  	def approval = {
		workflowStepDispatcherService.checkWorkFlowStep(params.objectName, params.objectId, params.historyId, params.actionId, params.ownerId, params.description, params.version)
		redirect(controller : params.objectName, action: "show", id: params.objectId)
	}
}
