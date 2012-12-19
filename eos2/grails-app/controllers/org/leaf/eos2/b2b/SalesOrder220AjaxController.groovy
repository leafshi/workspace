package org.leaf.eos2.b2b

import grails.converters.JSON
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class SalesOrder220AjaxController {

	def salesOrder220AjaxService

	//添加单身
	def addDetail = {
		def productId = params.int('productId').toLong()

		def salesOrderDetailInstance = new SalesOrderDetail();

			salesOrderDetailInstance.serialNumber = (params.int('rowId') + 1).toString().padLeft(2, '0')
			salesOrderDetailInstance.openDetail = salesOrder220AjaxService.productHasBom(productId)
			
			salesOrderDetailInstance.discount = salesOrder220AjaxService.productDiscount(productId)
			
			salesOrderDetailInstance.specialDiscount = 0
			salesOrderDetailInstance.isAllowSpecialDiscount = false
			
			salesOrderDetailInstance.finalDiscount = salesOrderDetailInstance.discount
			
			salesOrderDetailInstance.price = salesOrder220AjaxService.productPrice(productId)
			salesOrderDetailInstance.isAllowZeroPrice = salesOrder220AjaxService.isAllowZeroPrice(productId)
			
			salesOrderDetailInstance.finalPrice = salesOrderDetailInstance.price * salesOrderDetailInstance.discount
			
			salesOrderDetailInstance.deliveryCycle = salesOrder220AjaxService.productDeliveryCycle(productId)?:0
			salesOrderDetailInstance.deliveryLimitation = new Date().plus ( salesOrder220AjaxService.productDeliveryCycle(params.int('productId').toLong()) )
			
			salesOrderDetailInstance.quantity = 0
			salesOrderDetailInstance.amount = 0

		if(salesOrderDetailInstance.openDetail == true){//recalculate discount, specialDiscount, finalDiscount, price, finalPrice

			def j = 0
			//reset price, finalPrice
			salesOrderDetailInstance.price = 0
			salesOrderDetailInstance.finalPrice = 0

			for(bd in salesOrder220AjaxService.productBom(productId)) {
				j++
				def salesOrderDetailDetailInstance = new SalesOrderDetailDetail();
					salesOrderDetailDetailInstance.serialNumber = (j).toString().padLeft(2, '0')

					salesOrderDetailDetailInstance.price = bd[4]
					salesOrderDetailDetailInstance.isAllowZeroPrice = salesOrder220AjaxService.isAllowZeroPrice(bd[5])
					
					salesOrderDetailDetailInstance.discount = salesOrder220AjaxService.productDiscount (bd[5])
					
					salesOrderDetailDetailInstance.specialDiscount = 0
					salesOrderDetailDetailInstance.isAllowSpecialDiscount = false
					
					salesOrderDetailDetailInstance.finalDiscount = salesOrderDetailDetailInstance.discount * (1 - salesOrderDetailDetailInstance.specialDiscount)
					
					salesOrderDetailDetailInstance.finalPrice = salesOrderDetailDetailInstance.price * salesOrderDetailDetailInstance.discount  //实际售价

					salesOrderDetailDetailInstance.dosage = salesOrder220AjaxService.dosage(productId, bd[5])
					salesOrderDetailDetailInstance.quota = salesOrder220AjaxService.quota(productId, bd[5])
					
					//面价*组成用量/工时底数
					salesOrderDetailInstance.price += bd[4] * bd[3] / bd[6]
					salesOrderDetailInstance.finalPrice += bd[4] * bd[3] * salesOrderDetailDetailInstance.discount/ bd[6]

				BindDynamicMethod bdmDetail = new BindDynamicMethod()
	    		def mapDetail = ['product.id' : bd[5]]
	    		def argsDetail =  [salesOrderDetailDetailInstance, mapDetail]
	    		bdmDetail.invoke(salesOrderDetailDetailInstance, 'bind', (Object[]) argsDetail)

				salesOrderDetailInstance.addToSalesOrderDetailDetails(salesOrderDetailDetailInstance)

			}
			salesOrderDetailInstance.discount = salesOrderDetailInstance.finalPrice / salesOrderDetailInstance.price?:1
			salesOrderDetailInstance.specialDiscount = 0
			salesOrderDetailInstance.finalDiscount = salesOrderDetailInstance.discount * (1 - salesOrderDetailInstance.specialDiscount)
	    }

		BindDynamicMethod mybind = new BindDynamicMethod()
	    def mymap = ['product.id' : productId]
	    def myargs =  [salesOrderDetailInstance, mymap]
	    mybind.invoke(salesOrderDetailInstance, 'bind', (Object[]) myargs)

		render template : '/b2b/salesOrder220/editDetail', model : [salesOrderDetailInstance : salesOrderDetailInstance, i : params.int('rowId')]
	}

    //产品仓管分类
    def productCategoryOfCG = {
        def category = salesOrder220AjaxService.productCategory(params.int('productId').toLong())
        render "${category}"
    }

	//允许零价格
    def isAllowZeroPrice = {
        def isAllowZeroPrice = salesOrder220AjaxService.isAllowZeroPrice(params.int('productId').toLong())
        render "${isAllowZeroPrice}"  
    }

	//交货周期
    def deliveryCycle = {
        def deliveryCycle = salesOrder220AjaxService.productDeliveryCycle(params.int('productId').toLong())
        render "${deliveryCycle?:0}"
    }

	//组成用量
    def dosage = {
        def dosage = salesOrder220AjaxService.dosage(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${dosage}" 
    }

	//工时底数
    def quota = {
        def quota = salesOrder220AjaxService.quota(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${quota}" 
    }
    
    //获取经销商所属办事处
    def getDealerDepartment = {
        def department = salesOrder220AjaxService.dealerDeaprtment(params.dealerSerialNumber)
        render "${department}"
	}
	
	//订单行业列表
    def industryList = {
        render salesOrder220AjaxService.industryList(params.term) as JSON
    }

    def searchProduct = {
        render salesOrder220AjaxService.searchProduct(params.term) as JSON
    }
    
    def workflowHistory = {
		def historys = salesOrder220AjaxService.workflowHistory(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder220/workflowHistory', model : [workflowHistoryInstanceList : historys, objectId : params.int('salesOrderId'), ownerId : params.int('ownerId')]
    }
    
    def outBoundMessage = {
		def outBoundMessageInstanceList = salesOrder220AjaxService.outBoundMessage(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder220/outBoundMessage', model : [outBoundMessageInstanceList : outBoundMessageInstanceList]
    }

}
