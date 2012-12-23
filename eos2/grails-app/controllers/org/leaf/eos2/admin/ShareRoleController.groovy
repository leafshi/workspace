package org.leaf.eos2.admin

class ShareRoleController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : "/admin/shareRole/list", model: [shareRoleInstanceList: ShareRole.list(params), shareRoleInstanceTotal: ShareRole.count()]
    }

    def create = {
        def shareRoleInstance = new ShareRole()
        shareRoleInstance.properties = params
        render view : '/admin/shareRole/create', model: [shareRoleInstance: shareRoleInstance]
    }

    def save = {
        def shareRoleInstance = new ShareRole(params)
        if (!shareRoleInstance.hasErrors() && shareRoleInstance.save()) {
            flash.message = "shareRole.created"
            flash.args = [shareRoleInstance.id]
            flash.defaultMessage = "ShareRole ${shareRoleInstance.id} created"
            redirect(action: "show", id: shareRoleInstance.id)
        }
        else {
            render(view: "/admin/shareRole/create", model: [shareRoleInstance: shareRoleInstance])
        }
    }

    def show = {
        def shareRoleInstance = ShareRole.get(params.id)
        if (!shareRoleInstance) {
            flash.message = "shareRole.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ShareRole not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/shareRole/show', model: [shareRoleInstance: shareRoleInstance]
        }
    }

    def edit = {
        def shareRoleInstance = ShareRole.get(params.id)
        if (!shareRoleInstance) {
            flash.message = "shareRole.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ShareRole not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/shareRole/edit', model: [shareRoleInstance: shareRoleInstance]
        }
    }

    def update = {
        def shareRoleInstance = ShareRole.get(params.id)
        if (shareRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (shareRoleInstance.version > version) {
                    
                    shareRoleInstance.errors.rejectValue("version", "shareRole.optimistic.locking.failure", "Another user has updated this ShareRole while you were editing")
                    render(view: "/admin/shareRole/edit", model: [shareRoleInstance: shareRoleInstance])
                    return
                }
            }
            shareRoleInstance.properties = params
            if (!shareRoleInstance.hasErrors() && shareRoleInstance.save()) {
                flash.message = "shareRole.updated"
                flash.args = [params.id]
                flash.defaultMessage = "ShareRole ${params.id} updated"
                redirect(action: "show", id: shareRoleInstance.id)
            }
            else {
                render(view: "/admin/shareRole/edit", model: [shareRoleInstance: shareRoleInstance])
            }
        }
        else {
            flash.message = "shareRole.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ShareRole not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def shareRoleInstance = ShareRole.get(params.id)
        if (shareRoleInstance) {
            try {
                shareRoleInstance.delete()
                flash.message = "shareRole.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "ShareRole ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "shareRole.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "ShareRole ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "shareRole.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "ShareRole not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
