package org.leaf.eos2.admin

class RecordTypeController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/admin/recordType/list', model : [recordTypeInstanceList: RecordType.list(params), recordTypeInstanceTotal: RecordType.count()]
    }

    def create = {
        def recordTypeInstance = new RecordType()
        recordTypeInstance.properties = params
        render view : '/admin/recordType/create', model: [recordTypeInstance: recordTypeInstance]
    }

    def save = {
        def recordTypeInstance = new RecordType(params)
        if (!recordTypeInstance.hasErrors() && recordTypeInstance.save()) {
            flash.message = "recordType.created"
            flash.args = [recordTypeInstance.id]
            flash.defaultMessage = "RecordType ${recordTypeInstance.id} created"
            redirect(action: "show", id: recordTypeInstance.id)
        }
        else {
            render(view: "/admin/recordType/create", model: [recordTypeInstance: recordTypeInstance])
        }
    }

    def show = {
        def recordTypeInstance = RecordType.get(params.id)
        if (!recordTypeInstance) {
            flash.message = "recordType.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RecordType not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/recordType/show', model: [recordTypeInstance: recordTypeInstance]
        }
    }

    def edit = {
        def recordTypeInstance = RecordType.get(params.id)
        if (!recordTypeInstance) {
            flash.message = "recordType.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RecordType not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/admin/recordType/edit', model: [recordTypeInstance: recordTypeInstance]
        }
    }

    def update = {
        def recordTypeInstance = RecordType.get(params.id)
        if (recordTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (recordTypeInstance.version > version) {
                    
                    recordTypeInstance.errors.rejectValue("version", "recordType.optimistic.locking.failure", "Another user has updated this RecordType while you were editing")
                    render(view: "/admin/recordType/edit", model: [recordTypeInstance: recordTypeInstance])
                    return
                }
            }
            recordTypeInstance.properties = params
            if (!recordTypeInstance.hasErrors() && recordTypeInstance.save()) {
                flash.message = "recordType.updated"
                flash.args = [params.id]
                flash.defaultMessage = "RecordType ${params.id} updated"
                redirect(action: "show", id: recordTypeInstance.id)
            }
            else {
                render(view: "/admin/recordType/edit", model: [recordTypeInstance: recordTypeInstance])
            }
        }
        else {
            flash.message = "recordType.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RecordType not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def recordTypeInstance = RecordType.get(params.id)
        if (recordTypeInstance) {
            try {
                recordTypeInstance.delete()
                flash.message = "recordType.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RecordType ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "recordType.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RecordType ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "recordType.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RecordType not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
