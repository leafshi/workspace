package org.leaf.eos2.b2b

class SalesOrderController {

	def salesOrderService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/salesOrder/list', model :[salesOrderInstanceList: salesOrderService.list(params), salesOrderInstanceTotal: salesOrderService.count(params)]
    }
    
    def show = {
        def salesOrderInstance = salesOrderService.show(params.id)//salesOrderService.show(params.id)
        if (!salesOrderInstance) {
            flash.message = "${message(code: 'b2b.salesOrder.not.found', args: [params.id])}"
            redirect(action: "list")
        }else {
        	redirect(controller : "salesOrder${salesOrderInstance?.recordType?.serialNumber}", action: "show", id : params.id)
        }
    }

    def edit = {
        def salesOrderInstance = salesOrderService.edit(params.id)//salesOrderService.show(params.id)
        if (!salesOrderInstance) {
            flash.message = "${message(code: 'b2b.salesOrder.not.found', args: [params.id])}"
            redirect(action: "list")
        }else {
        	redirect(controller : "salesOrder${salesOrderInstance?.recordType?.serialNumber}", action: "edit", id : params.id)
        }
    }
    
    def delete = {
        def salesOrderInstance = salesOrderService.delete(params.id)
        if (!salesOrderInstance) {
            flash.message = "${message(code: 'b2b.salesOrder.not.found', args: [params.id])}"
            redirect(action: "list")
        }else {
        	redirect(controller : "salesOrder${salesOrderInstance?.recordType?.serialNumber}", action: "delete", id : params.id)
        }
    }
}
