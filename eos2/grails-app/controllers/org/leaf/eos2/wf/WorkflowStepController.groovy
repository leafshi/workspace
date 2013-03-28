package org.leaf.eos2.wf

class WorkflowStepController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def edit = {
        def workflowStepInstance = WorkflowStep.get(params.id)
        if (!workflowStepInstance) {
            flash.message = "workflowStep.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowStep not found with id ${params.id}"
            redirect(controller:"workflow", action: "list")
        }
        else {
            render view : '/wf/workflowStep/edit', model : [workflowStepInstance: workflowStepInstance]
        }
    }

    def update = {
        def workflowStepInstance = WorkflowStep.get(params.id)
        if (workflowStepInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (workflowStepInstance.version > version) {
                    
                    workflowStepInstance.errors.rejectValue("version", "workflowStep.optimistic.locking.failure", "Another user has updated this WorkflowStep while you were editing")
                    render(view: "/wf/workflowStep/edit", model: [workflowStepInstance: workflowStepInstance])
                    return
                }
            }
            workflowStepInstance.properties = params
            if (!workflowStepInstance.hasErrors() && workflowStepInstance.save()) {
                flash.message = "workflowStep.updated"
                flash.args = [params.id]
                flash.defaultMessage = "WorkflowStep ${params.id} updated"
                redirect(controller:"workflow", action: "show", id: workflowStepInstance.workflow.id)
            }
            else {
                render(view: "/wf/workflowStep/edit", model: [workflowStepInstance: workflowStepInstance])
            }
        }
        else {
            flash.message = "workflowStep.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "WorkflowStep not found with id ${params.id}"
            redirect(controller : "workflow", action: "list")
        }
    }

}
