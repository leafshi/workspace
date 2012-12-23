package org.leaf.eos2.b2b

class DepartmentController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/department/list', model : [departmentInstanceList: Department.list(params), departmentInstanceTotal: Department.count()]
    }

    def create = {
        def departmentInstance = new Department()
        departmentInstance.properties = params
        render view : '/b2b/department/create', model : [departmentInstance: departmentInstance]
    }

    def save = {
        def departmentInstance = new Department(params)
        if (!departmentInstance.hasErrors() && departmentInstance.save()) {
            flash.message = "department.created"
            flash.args = [departmentInstance.id]
            flash.defaultMessage = "Department ${departmentInstance.id} created"
            redirect(action: "show", id: departmentInstance.id)
        }
        else {
            render(view: "/b2b/department/create", model: [departmentInstance: departmentInstance])
        }
    }

    def show = {
        def departmentInstance = Department.get(params.id)
        if (!departmentInstance) {
            flash.message = "department.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Department not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/department/show', model : [departmentInstance: departmentInstance]
        }
    }

    def edit = {
        def departmentInstance = Department.get(params.id)
        if (!departmentInstance) {
            flash.message = "department.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Department not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/department/edit', model : [departmentInstance: departmentInstance]
        }
    }

    def update = {
        def departmentInstance = Department.get(params.id)
        if (departmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (departmentInstance.version > version) {
                    
                    departmentInstance.errors.rejectValue("version", "department.optimistic.locking.failure", "Another user has updated this Department while you were editing")
                    render(view: "/b2b/department/edit", model: [departmentInstance: departmentInstance])
                    return
                }
            }
            departmentInstance.properties = params
            if (!departmentInstance.hasErrors() && departmentInstance.save()) {
                flash.message = "department.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Department ${params.id} updated"
                redirect(action: "show", id: departmentInstance.id)
            }
            else {
                render(view: "/b2b/department/edit", model: [departmentInstance: departmentInstance])
            }
        }
        else {
            flash.message = "department.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Department not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def departmentInstance = Department.get(params.id)
        if (departmentInstance) {
            try {
                departmentInstance.delete()
                flash.message = "department.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Department ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "department.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Department ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "department.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Department not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
