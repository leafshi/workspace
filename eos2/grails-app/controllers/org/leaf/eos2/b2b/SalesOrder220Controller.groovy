package org.leaf.eos2.b2b

class SalesOrder220Controller {

	def salesOrder220Service

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view:'/b2b/salesOrder/list', model:[salesOrderInstanceList: SalesOrder.list(params), salesOrderInstanceTotal: SalesOrder.count()]
    }

    def create = {
		def salesOrderInstance = salesOrder220Service.init(params)
        render view : '/b2b/salesOrder220/create', model : [salesOrderInstance: salesOrderInstance]
    }

    def save = {
        def salesOrderInstance = new SalesOrder(params)
        if (!salesOrderInstance.hasErrors() && salesOrderInstance.save()) {
            flash.message = "salesOrder.created"
            flash.args = [salesOrderInstance.id]
            flash.defaultMessage = "SalesOrder ${salesOrderInstance.id} created"
            redirect(action: "show", id: salesOrderInstance.id)
        }
        else {
            render(view: "/b2b/salesOrder220/create", model: [salesOrderInstance: salesOrderInstance])
        }
    }

    def show = {
        def salesOrderInstance = SalesOrder.get(params.id)
        if (!salesOrderInstance) {
            flash.message = "salesOrder.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SalesOrder not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/salesOrder220/show', model : [salesOrderInstance: salesOrderInstance]
        }
    }

    def edit = {
        def salesOrderInstance = SalesOrder.get(params.id)
        if (!salesOrderInstance) {
            flash.message = "salesOrder.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SalesOrder not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/salesOrder220/edit', model : [salesOrderInstance: salesOrderInstance]
        }
    }

    def update = {
        def salesOrderInstance = SalesOrder.get(params.id)
        if (salesOrderInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (salesOrderInstance.version > version) {
                    
                    salesOrderInstance.errors.rejectValue("version", "salesOrder.optimistic.locking.failure", "Another user has updated this SalesOrder while you were editing")
                    render(view: "/b2b/salesOrder220/edit", model: [salesOrderInstance: salesOrderInstance])
                    return
                }
            }
            //salesOrderInstance.properties = params
            if (salesOrder220Service.update(salesOrderInstance, params)) {
                flash.message = "salesOrder.updated"
                flash.args = [params.id]
                flash.defaultMessage = "SalesOrder ${params.id} updated"
                redirect(action: "show", id: salesOrderInstance.id)
            }
            else {
                render(view: "/b2b/salesOrder220/edit", model: [salesOrderInstance: salesOrderInstance])
            }
        }
        else {
            flash.message = "salesOrder.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SalesOrder not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def salesOrderInstance = SalesOrder.get(params.id)
        if (salesOrderInstance) {
            try {
                salesOrderInstance.delete()
                flash.message = "salesOrder.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SalesOrder ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "salesOrder.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SalesOrder ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "salesOrder.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SalesOrder not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
