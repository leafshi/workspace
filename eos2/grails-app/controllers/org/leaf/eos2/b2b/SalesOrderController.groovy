package org.leaf.eos2.b2b

class SalesOrderController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 100,  100)
        render view : '/b2b/salesOrder/list', model :[salesOrderInstanceList: SalesOrder.list(params), salesOrderInstanceTotal: SalesOrder.count()]
    }
    
    def show = {
        def salesOrderInstance = SalesOrder.read(params.id)//salesOrderService.show(params.id)
        if (!salesOrderInstance) {
            flash.message = "${message(code: 'b2b.salesOrder.not.found', args: [params.id])}"
            redirect(action: "list")
        }else {
            render view : "/b2b/salesOrder${salesOrderInstance?.recordType?.serialNumber}/show", model : [salesOrderInstance: salesOrderInstance]
        }
    }

}
