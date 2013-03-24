package org.leaf.eos2.admin

class HelpController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/admin/help/list', model : [helpInstanceList: Help.list(params), helpInstanceTotal: Help.count()]
    }

    def create = {
        def helpInstance = new Help()
        helpInstance.properties = params
        render view : '/admin/help/create', model : [helpInstance: helpInstance]
    }

    def save = {
        def helpInstance = new Help(params)
        if (!helpInstance.hasErrors() && helpInstance.save()) {
            flash.message = "help.created"
            flash.args = [helpInstance.id]
            flash.defaultMessage = "Help ${helpInstance.id} created"
            redirect(action: "show", id: helpInstance.id)
        }
        else {
            render(view: "/admin/help/create", model: [helpInstance: helpInstance])
        }
    }

    def show = {
        def helpInstance = Help.get(params.id)
        if (!helpInstance) {
            flash.message = "help.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Help not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/help/show', model : [helpInstance: helpInstance]
        }
    }

    def edit = {
        def helpInstance = Help.get(params.id)
        if (!helpInstance) {
            flash.message = "help.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Help not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/help/edit', model : [helpInstance: helpInstance]
        }
    }

    def update = {
        def helpInstance = Help.get(params.id)
        if (helpInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (helpInstance.version > version) {
                    
                    helpInstance.errors.rejectValue("version", "help.optimistic.locking.failure", "Another user has updated this Help while you were editing")
                    render(view: "/admin/help/edit", model: [helpInstance: helpInstance])
                    return
                }
            }
            helpInstance.properties = params
            if (!helpInstance.hasErrors() && helpInstance.save()) {
                flash.message = "help.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Help ${params.id} updated"
                redirect(action: "show", id: helpInstance.id)
            }
            else {
                render(view: "/admin/help/edit", model: [helpInstance: helpInstance])
            }
        }
        else {
            flash.message = "help.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Help not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def helpInstance = Help.get(params.id)
        if (helpInstance) {
            try {
                helpInstance.delete()
                flash.message = "help.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Help ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "help.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Help ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "help.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Help not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
