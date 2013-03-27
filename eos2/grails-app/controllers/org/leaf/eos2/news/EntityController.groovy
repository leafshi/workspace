package org.leaf.eos2.news

class EntityController {

	def entityService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view :'/news/entity/list', model : [entityInstanceList: entityService.list(params), entityInstanceTotal: entityService.count(params)]
    }

    def create = {
        def entityInstance = entityService.create();
        entityInstance.properties = params
        render view :'/news/entity/create', model : [entityInstance: entityInstance]
    }

    def save = {
        def entityInstance = new Entity(params)
        if (!entityInstance.hasErrors() && entityService.save(entityInstance)) {
            flash.message = "entity.created"
            flash.args = [entityInstance.id]
            flash.defaultMessage = "Entity ${entityInstance.id} created"
            redirect(action: "show", id: entityInstance.id)
        }
        else {
            render(view: "/news/entity/create", model: [entityInstance: entityInstance])
        }
    }

    def show = {
        def entityInstance = entityService.show(params.id)
        if (!entityInstance) {
            flash.message = "entity.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Entity not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/news/entity/show', model : [entityInstance: entityInstance]
        }
    }

    def edit = {
        def entityInstance = Entity.get(params.id)
        if (!entityInstance) {
            flash.message = "entity.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Entity not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/news/entity/edit', model : [entityInstance: entityService.edit(entityInstance)]
        }
    }

    def update = {
        def entityInstance = Entity.get(params.id)
        if (entityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (entityInstance.version > version) {
                    
                    entityInstance.errors.rejectValue("version", "entity.optimistic.locking.failure", "Another user has updated this Entity while you were editing")
                    render(view: "/news/entity/edit", model: [entityInstance: entityInstance])
                    return
                }
            }
            if (entityService.update(entityInstance, params)) {
                flash.message = "entity.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Entity ${params.id} updated"
                redirect(action: "show", id: entityInstance.id)
            }
            else {
                render(view: "/news/entity/edit", model: [entityInstance: entityInstance])
            }
        }
        else {
            flash.message = "entity.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Entity not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def entityInstance = Entity.get(params.id)
        if (entityInstance) {
            if(entityService.delete(entityInstance)){
                flash.message = "entity.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Entity ${params.id} deleted"
                redirect(action: "list")
            } else {
                flash.message = "entity.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Entity ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "entity.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Entity not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
