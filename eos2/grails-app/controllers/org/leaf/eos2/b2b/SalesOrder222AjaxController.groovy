package org.leaf.eos2.b2b

import grails.converters.JSON
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class SalesOrder222AjaxController {

	def salesOrder222AjaxService

	//添加单身
	def addDetail = {
		def productId = params.int('productId').toLong()

		def salesOrderDetailInstance = new SalesOrderDetail();

			salesOrderDetailInstance.serialNumber = (params.int('rowId') + 1).toString().padLeft(2, '0')
			salesOrderDetailInstance.openDetail = false
			
			salesOrderDetailInstance.discount = 1
			salesOrderDetailInstance.specialDiscount = 0
			salesOrderDetailInstance.isAllowSpecialDiscount = false
			salesOrderDetailInstance.finalDiscount = salesOrderDetailInstance.discount
			
			salesOrderDetailInstance.price = salesOrder222AjaxService.productPrice(productId)
			salesOrderDetailInstance.isAllowZeroPrice = false
			salesOrderDetailInstance.finalPrice = salesOrderDetailInstance.price
			
			salesOrderDetailInstance.deliveryCycle = salesOrder222AjaxService.productDeliveryCycle(productId)?:0
			salesOrderDetailInstance.deliveryLimitation = new Date().plus ( salesOrder222AjaxService.productDeliveryCycle(params.int('productId').toLong()) )
			//如果不是工作日，延时到下个周一
			if(salesOrderDetailInstance.deliveryLimitation.day == 0){
				salesOrderDetailInstance.deliveryLimitation = salesOrderDetailInstance.deliveryLimitation.next()
			}else if(salesOrderDetailInstance.deliveryLimitation.day == 6){
				salesOrderDetailInstance.deliveryLimitation = salesOrderDetailInstance.deliveryLimitation + 2
			}
			salesOrderDetailInstance.quantity = 0
			salesOrderDetailInstance.amount = 0


		BindDynamicMethod mybind = new BindDynamicMethod()
	    def mymap = ['product.id' : productId]
	    def myargs =  [salesOrderDetailInstance, mymap]
	    mybind.invoke(salesOrderDetailInstance, 'bind', (Object[]) myargs)

		render template : '/b2b/salesOrder222/editDetail', model : [salesOrderDetailInstance : salesOrderDetailInstance, i : params.int('rowId')]
	}

    //产品仓管分类
    def productCategoryOfCG = {
        def category = salesOrder222AjaxService.productCategory(params.int('productId').toLong())
        render "${category}"
    }

	//允许零价格
    def isAllowZeroPrice = {
        def isAllowZeroPrice = salesOrder222AjaxService.isAllowZeroPrice(params.int('productId').toLong())
        render "${isAllowZeroPrice}"  
    }

	//交货周期
    def deliveryCycle = {
        def deliveryCycle = salesOrder222AjaxService.productDeliveryCycle(params.int('productId').toLong())
        render "${deliveryCycle?:0}"
    }

	//组成用量
    def dosage = {
        def dosage = salesOrder222AjaxService.dosage(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${dosage}" 
    }

	//工时底数
    def quota = {
        def quota = salesOrder222AjaxService.quota(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${quota}" 
    }
    
    //获取经销商所属办事处
    def getDealerDepartment = {
        def department = salesOrder222AjaxService.dealerDeaprtment(params.dealerSerialNumber)
        render "${department}"
	}
	
	//订单行业列表
    def industryList = {
        render salesOrder222AjaxService.industryList(params.term) as JSON
    }

    def searchProduct = {
        render salesOrder222AjaxService.searchProduct(params.term) as JSON
    }
    
    def workflowHistory = {
		def historys = salesOrder222AjaxService.workflowHistory(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder220/workflowHistory', model : [workflowHistoryInstanceList : historys, objectId : params.int('salesOrderId'), ownerId : params.int('ownerId')]
    }
    
    def outBoundMessage = {
		def outBoundMessageInstanceList = salesOrder222AjaxService.outBoundMessage(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder220/outBoundMessage', model : [outBoundMessageInstanceList : outBoundMessageInstanceList]
    }
}
