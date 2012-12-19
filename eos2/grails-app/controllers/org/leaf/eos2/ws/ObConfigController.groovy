package org.leaf.eos2.ws

class ObConfigController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/ws/obConfig/list', model : [obConfigInstanceList: ObConfig.list(params), obConfigInstanceTotal: ObConfig.count()]
    }

    def create = {
        def obConfigInstance = new ObConfig()
        obConfigInstance.properties = params
        render view : '/ws/obConfig/create', model : [obConfigInstance: obConfigInstance]
    }

    def save = {
        def obConfigInstance = new ObConfig(params)
        if (!obConfigInstance.hasErrors() && obConfigInstance.save()) {
            flash.message = "obConfig.created"
            flash.args = [obConfigInstance.id]
            flash.defaultMessage = "ObConfig ${obConfigInstance.id} created"
            redirect(action: "show", id: obConfigInstance.id)
        }
        else {
            render(view: "/ws/obConfig/create", model: [obConfigInstance: obConfigInstance])
        }
    }

    def show = {
        def obConfigInstance = ObConfig.get(params.id)
        if (!obConfigInstance) {
            flash.message = "obConfig.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ObConfig not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/ws/obConfig/show', model : [obConfigInstance: obConfigInstance]
        }
    }

    def edit = {
        def obConfigInstance = ObConfig.get(params.id)
        if (!obConfigInstance) {
            flash.message = "obConfig.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ObConfig not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/ws/obConfig/edit', model : [obConfigInstance: obConfigInstance]
        }
    }

    def update = {
        def obConfigInstance = ObConfig.get(params.id)
        if (obConfigInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (obConfigInstance.version > version) {
                    
                    obConfigInstance.errors.rejectValue("version", "obConfig.optimistic.locking.failure", "Another user has updated this ObConfig while you were editing")
                    render(view: "/ws/obConfig/edit", model: [obConfigInstance: obConfigInstance])
                    return
                }
            }
            obConfigInstance.properties = params
            if (!obConfigInstance.hasErrors() && obConfigInstance.save()) {
                flash.message = "obConfig.updated"
                flash.args = [params.id]
                flash.defaultMessage = "ObConfig ${params.id} updated"
                redirect(action: "show", id: obConfigInstance.id)
            }
            else {
                render(view: "/ws/obConfig/edit", model: [obConfigInstance: obConfigInstance])
            }
        }
        else {
            flash.message = "obConfig.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ObConfig not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def obConfigInstance = ObConfig.get(params.id)
        if (obConfigInstance) {
            try {
                obConfigInstance.delete()
                flash.message = "obConfig.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "ObConfig ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "obConfig.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "ObConfig ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "obConfig.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ObConfig not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
