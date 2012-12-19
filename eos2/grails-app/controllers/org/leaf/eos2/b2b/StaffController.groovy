package org.leaf.eos2.b2b

class StaffController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/b2b/staff/list', model:[staffInstanceList: Staff.list(params), staffInstanceTotal: Staff.count()]
    }

    def create = {
        def staffInstance = new Staff()
        staffInstance.properties = params
        render view : '/b2b/staff/create', model: [staffInstance: staffInstance]
    }

    def save = {
        def staffInstance = new Staff(params)
        if (!staffInstance.hasErrors() && staffInstance.save()) {
            flash.message = "staff.created"
            flash.args = [staffInstance.id]
            flash.defaultMessage = "Staff ${staffInstance.id} created"
            redirect(action: "show", id: staffInstance.id)
        }
        else {
            render(view: "/b2b/staff/create", model: [staffInstance: staffInstance])
        }
    }

    def show = {
        def staffInstance = Staff.get(params.id)
        if (!staffInstance) {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/staff/show', model : [staffInstance: staffInstance]
        }
    }

    def edit = {
        def staffInstance = Staff.get(params.id)
        if (!staffInstance) {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/staff/edit', model : [staffInstance: staffInstance]
        }
    }

    def update = {
        def staffInstance = Staff.get(params.id)
        if (staffInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (staffInstance.version > version) {
                    
                    staffInstance.errors.rejectValue("version", "staff.optimistic.locking.failure", "Another user has updated this Staff while you were editing")
                    render(view: "/b2b/staff/edit", model: [staffInstance: staffInstance])
                    return
                }
            }
            staffInstance.properties = params
            if (!staffInstance.hasErrors() && staffInstance.save()) {
                flash.message = "staff.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} updated"
                redirect(action: "show", id: staffInstance.id)
            }
            else {
                render(view: "/b2b/staff/edit", model: [staffInstance: staffInstance])
            }
        }
        else {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def staffInstance = Staff.get(params.id)
        if (staffInstance) {
            try {
                staffInstance.delete()
                flash.message = "staff.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "staff.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
