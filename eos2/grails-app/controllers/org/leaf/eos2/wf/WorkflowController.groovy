package org.leaf.eos2.wf

class WorkflowController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/wf/workflow/list', model : [workflowInstanceList: Workflow.list(params), workflowInstanceTotal: Workflow.count()]
    }

    def create = {
        def workflowInstance = new Workflow()
        workflowInstance.properties = params
        render view : '/wf/workflow/create', model : [workflowInstance: workflowInstance]
    }

    def save = {
        def workflowInstance = new Workflow(params)
        if (!workflowInstance.hasErrors() && workflowInstance.save()) {
            flash.message = "workflow.created"
            flash.args = [workflowInstance.id]
            flash.defaultMessage = "Workflow ${workflowInstance.id} created"
            redirect(action: "show", id: workflowInstance.id)
        }
        else {
            render(view: "/wf/workflow/create", model: [workflowInstance: workflowInstance])
        }
    }

    def show = {
        def workflowInstance = Workflow.get(params.id)
        if (!workflowInstance) {
            flash.message = "workflow.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Workflow not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/wf/workflow/show', model : [workflowInstance: workflowInstance]
        }
    }

    def edit = {
        def workflowInstance = Workflow.get(params.id)
        if (!workflowInstance) {
            flash.message = "workflow.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Workflow not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/wf/workflow/edit', model : [workflowInstance: workflowInstance]
        }
    }

    def update = {
        def workflowInstance = Workflow.get(params.id)
        if (workflowInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (workflowInstance.version > version) {
                    
                    workflowInstance.errors.rejectValue("version", "workflow.optimistic.locking.failure", "Another user has updated this Workflow while you were editing")
                    render(view: "/wf/workflow/edit", model: [workflowInstance: workflowInstance])
                    return
                }
            }
            workflowInstance.properties = params
            if (!workflowInstance.hasErrors() && workflowInstance.save()) {
                flash.message = "workflow.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Workflow ${params.id} updated"
                redirect(action: "show", id: workflowInstance.id)
            }
            else {
                render(view: "/wf/workflow/edit", model: [workflowInstance: workflowInstance])
            }
        }
        else {
            flash.message = "workflow.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Workflow not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def workflowInstance = Workflow.get(params.id)
        if (workflowInstance) {
            try {
                workflowInstance.delete()
                flash.message = "workflow.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Workflow ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "workflow.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Workflow ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "workflow.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Workflow not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
