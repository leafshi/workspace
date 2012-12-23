package org.leaf.eos2.b2b

class BomController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/bom/list', model : [bomInstanceList: Bom.list(params), bomInstanceTotal: Bom.count()]
    }

    def create = {
        def bomInstance = new Bom()
        bomInstance.properties = params
        render view : '/b2b/bom/create', model : [bomInstance: bomInstance]
    }

    def save = {
        def bomInstance = new Bom(params)
        if (!bomInstance.hasErrors() && bomInstance.save()) {
            flash.message = "bom.created"
            flash.args = [bomInstance.id]
            flash.defaultMessage = "Bom ${bomInstance.id} created"
            redirect(action: "show", id: bomInstance.id)
        }
        else {
            render(view: "/b2b/bom/create", model: [bomInstance: bomInstance])
        }
    }

    def show = {
        def bomInstance = Bom.get(params.id)
        if (!bomInstance) {
            flash.message = "bom.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Bom not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/bom/show', model : [bomInstance: bomInstance]
        }
    }

    def edit = {
        def bomInstance = Bom.get(params.id)
        if (!bomInstance) {
            flash.message = "bom.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Bom not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/bom/edit', model : [bomInstance: bomInstance]
        }
    }

    def update = {
        def bomInstance = Bom.get(params.id)
        if (bomInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (bomInstance.version > version) {
                    
                    bomInstance.errors.rejectValue("version", "bom.optimistic.locking.failure", "Another user has updated this Bom while you were editing")
                    render(view: "/b2b/bom/edit", model: [bomInstance: bomInstance])
                    return
                }
            }
            bomInstance.properties = params
            if (!bomInstance.hasErrors() && bomInstance.save()) {
                flash.message = "bom.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Bom ${params.id} updated"
                redirect(action: "show", id: bomInstance.id)
            }
            else {
                render(view: "/b2b/bom/edit", model: [bomInstance: bomInstance])
            }
        }
        else {
            flash.message = "bom.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Bom not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def bomInstance = Bom.get(params.id)
        if (bomInstance) {
            try {
                bomInstance.delete()
                flash.message = "bom.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Bom ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "bom.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Bom ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "bom.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Bom not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
