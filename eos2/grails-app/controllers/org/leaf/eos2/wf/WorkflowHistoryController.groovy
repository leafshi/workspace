package org.leaf.eos2.wf

class WorkflowHistoryController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/wf/workflowHistory/list', model : [workflowHistoryInstanceList: WorkflowHistory.list(params), workflowHistoryInstanceTotal: WorkflowHistory.count()]
    }

    def create = {
        def workflowHistoryInstance = new WorkflowHistory()
        workflowHistoryInstance.properties = params
        render view : '/wf/workflowHistory/create', model : [workflowHistoryInstance: workflowHistoryInstance]
    }

    def save = {
        def workflowHistoryInstance = new WorkflowHistory(params)
        if (!workflowHistoryInstance.hasErrors() && workflowHistoryInstance.save()) {
            flash.message = "workflowHistory.created"
            flash.args = [workflowHistoryInstance.id]
            flash.defaultMessage = "WorkflowHistory ${workflowHistoryInstance.id} created"
            redirect(action: "show", id: workflowHistoryInstance.id)
        }
        else {
            render(view: "/wf/workflowHistory/create", model: [workflowHistoryInstance: workflowHistoryInstance])
        }
    }

    def show = {
        def workflowHistoryInstance = WorkflowHistory.get(params.id)
        if (!workflowHistoryInstance) {
            flash.message = "workflowHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowHistory not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/wf/workflowHistory/show', model : [workflowHistoryInstance: workflowHistoryInstance]
        }
    }

    def edit = {
        def workflowHistoryInstance = WorkflowHistory.get(params.id)
        if (!workflowHistoryInstance) {
            flash.message = "workflowHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowHistory not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/wf/workflowHistory/edit', model : [workflowHistoryInstance: workflowHistoryInstance]
        }
    }

    def update = {
        def workflowHistoryInstance = WorkflowHistory.get(params.id)
        if (workflowHistoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (workflowHistoryInstance.version > version) {
                    
                    workflowHistoryInstance.errors.rejectValue("version", "workflowHistory.optimistic.locking.failure", "Another user has updated this WorkflowHistory while you were editing")
                    render(view: "/wf/workflowHistory/edit", model: [workflowHistoryInstance: workflowHistoryInstance])
                    return
                }
            }
            workflowHistoryInstance.properties = params
            if (!workflowHistoryInstance.hasErrors() && workflowHistoryInstance.save()) {
                flash.message = "workflowHistory.updated"
                flash.args = [params.id]
                flash.defaultMessage = "WorkflowHistory ${params.id} updated"
                redirect(action: "show", id: workflowHistoryInstance.id)
            }
            else {
                render(view: "/wf/workflowHistory/edit", model: [workflowHistoryInstance: workflowHistoryInstance])
            }
        }
        else {
            flash.message = "workflowHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowHistory not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def workflowHistoryInstance = WorkflowHistory.get(params.id)
        if (workflowHistoryInstance) {
            try {
                workflowHistoryInstance.delete()
                flash.message = "workflowHistory.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "WorkflowHistory ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "workflowHistory.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "WorkflowHistory ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "workflowHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowHistory not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
