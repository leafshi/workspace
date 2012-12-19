package org.leaf.eos2.shiro

class RoleController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view :'/shiro/role/list', model : [roleInstanceList: Role.list(params), roleInstanceTotal: Role.count()]
    }

    def create = {
        def roleInstance = new Role()
        roleInstance.properties = params
        render view :'/shiro/role/create', model: [roleInstance: roleInstance]
    }

    def save = {
        def roleInstance = new Role(params)
        if (!roleInstance.hasErrors() && roleInstance.save()) {
            flash.message = "role.created"
            flash.args = [roleInstance.id]
            flash.defaultMessage = "Role ${roleInstance.id} created"
            redirect(action: "show", id: roleInstance.id)
        }
        else {
            render(view: "/shiro/role/create", model: [roleInstance: roleInstance])
        }
    }

    def show = {
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
            flash.message = "role.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Role not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/shiro/role/show', model : [roleInstance: roleInstance]
        }
    }

    def edit = {
        def roleInstance = Role.get(params.id)
        if (!roleInstance) {
            flash.message = "role.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Role not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/shiro/role/edit', model : [roleInstance: roleInstance]
        }
    }

    def update = {
        def roleInstance = Role.get(params.id)
        if (roleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (roleInstance.version > version) {
                    
                    roleInstance.errors.rejectValue("version", "role.optimistic.locking.failure", "Another user has updated this Role while you were editing")
                    render(view: "/shiro/role/edit", model: [roleInstance: roleInstance])
                    return
                }
            }
            roleInstance.properties = params
            if (!roleInstance.hasErrors() && roleInstance.save()) {
                flash.message = "role.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Role ${params.id} updated"
                redirect(action: "show", id: roleInstance.id)
            }
            else {
                render(view: "/shiro/role/edit", model: [roleInstance: roleInstance])
            }
        }
        else {
            flash.message = "role.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Role not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def roleInstance = Role.get(params.id)
        if (roleInstance) {
            try {
                roleInstance.delete()
                flash.message = "role.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Role ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "role.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Role ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "role.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Role not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
