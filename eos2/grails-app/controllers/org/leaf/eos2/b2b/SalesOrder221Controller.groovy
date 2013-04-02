package org.leaf.eos2.b2b

class SalesOrder221Controller {

	def salesOrder221Service
	def salesOrderService
	def salesOrderExtendService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST", forceDelete :"POST", clearErpSerialNumber : "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view:'/b2b/salesOrder/list', model:[salesOrderInstanceList: salesOrderService.list(params), salesOrderInstanceTotal: salesOrderService.count(params)]
    }

    def create = {
        def salesOrderInstance = salesOrder221Service.init(params)
        render view : '/b2b/salesOrder221/create', model : [salesOrderInstance: salesOrderInstance]
    }

    def save = {
        def salesOrderInstance = new SalesOrder(params)
        if (salesOrder221Service.insert(salesOrderInstance)) {
            flash.message = "salesOrder.created"
            flash.args = [salesOrderInstance.id]
            flash.defaultMessage = "SalesOrder ${salesOrderInstance.id} created"
            redirect(action: "show", id: salesOrderInstance.id)
        }
        else {
            render(view: "/b2b/salesOrder221/create", model: [salesOrderInstance: salesOrderInstance])
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
            render view : '/b2b/salesOrder221/show', model : [salesOrderInstance: salesOrderInstance]
        }
    }

    def edit = {
		if(salesOrderService.isLocked(params.id) == true){
			flash.message = "${message(code: 'salesOrder.locked', args: [params.id])}"			
			redirect(action: "show", id: params.id)
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
            render view : '/b2b/salesOrder221/edit', model : [salesOrderInstance: salesOrderInstance]
        }
    }

    def update = {
		if(salesOrderService.isLocked(params.id) == true){
			flash.message = "${message(code: 'salesOrder.locked', args: [params.id])}"			
			redirect(action: "show", id: params.id)
            return
		}

        def salesOrderInstance = salesOrderService.edit(params.id)
        if (salesOrderInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (salesOrderInstance.version > version) {
                    
                    salesOrderInstance.errors.rejectValue("version", "salesOrder.optimistic.locking.failure", "Another user has updated this SalesOrder while you were editing")
                    render(view: "/b2b/salesOrder221/edit", model: [salesOrderInstance: salesOrderInstance])
                    return
                }
            }
            
            if (salesOrder221Service.update(salesOrderInstance, params)) {
                flash.message = "salesOrder.updated"
                flash.args = [params.id]
                flash.defaultMessage = "SalesOrder ${params.id} updated"
                redirect(action: "show", id: salesOrderInstance.id)
            }
            else {
                render(view: "/b2b/salesOrder221/edit", model: [salesOrderInstance: salesOrderInstance])
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
			flash.message = "${message(code: 'salesOrder.locked', args: [params.id])}"			
			redirect(action: "show", id: params.id)
            return
		}
        def salesOrderInstance = salesOrderService.delete(params.id)
        
        if (!salesOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
            redirect(controller : 'salesOrder', action: "list")
            return
        }
        
        if(salesOrderService.delete2(params.id) == true) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
            redirect(controller : 'salesOrder', action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
            redirect(controller : 'salesOrder', action: "show", id: params.id)
        }

    }
    
    def forceDelete = {
        if(salesOrderExtendService.forceDelete(params.id)) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
            redirect(controller : 'salesOrder', action: "list")
        }else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
            redirect(controller : 'salesOrder', action: "show", id: params.id)
        }
    	
    }
    def clearErpSerialNumber = {
        if(salesOrderExtendService.clearErpSerialNumber(params.id)) {
            flash.message = "clear erp serial number success"
            redirect(controller : 'salesOrder', action: "show", id: params.id)
        }else {
            flash.message = "clear erp serial number failed"
            redirect(controller : 'salesOrder', action: "show", id: params.id)
        }
    	
    }

}
