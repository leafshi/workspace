package org.leaf.eos2.b2b

class SalesOrder220Controller {

	def salesOrder220Service
	def salesOrderService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view:'/b2b/salesOrder/list', model:[salesOrderInstanceList: salesOrderService.list(params), salesOrderInstanceTotal: salesOrderService.count(params)]
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
        def salesOrderInstance = salesOrderService.show(params.id)
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
		if(salesOrderService.isLocked(params.id) == true){
			render(view : "/unauthorized") 
            return
		}

        def salesOrderInstance = salesOrderService.edit(params.id)
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
		if(salesOrderService.isLocked(params.id) == true){
			render(view : "/unauthorized") 
            return
		}

        def salesOrderInstance = salesOrderService.edit(params.id)
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
		if(salesOrderService.isLocked(params.id) == true){
			render(view : "/unauthorized") 
            return
		}

        def salesOrderInstance = salesOrderService.delete(params.id)
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
