package org.leaf.eos2.b2b

import grails.converters.JSON
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class SalesOrder221AjaxController {

	def salesOrder221AjaxService

	//添加单身
	def addDetail = {
		def productId = params.int('productId')?.toLong()
			productId = productId ?: -1L
		
		def dealerId = params.int('dealerId')?.toLong()
			dealerId = dealerId?:-1L
		
		def industryId = params.int('industryId')?.toLong()
			industryId = industryId ?: -1L
		
		def project = params.project
			project = project ?: ""

		BindDynamicMethod mybind = new BindDynamicMethod()
	    def mymap = [:]
	    	mymap['product.id'] = productId


		def salesOrderDetailInstance = new SalesOrderDetail();

			salesOrderDetailInstance.serialNumber = (params.int('rowId') + 1).toString().padLeft(2, '0')
			salesOrderDetailInstance.openDetail = salesOrder221AjaxService.productHasBom(productId)
						
			salesOrderDetailInstance.deliveryCycle = salesOrder221AjaxService.productDeliveryCycle(productId)?:0
			salesOrderDetailInstance.deliveryLimitation = new Date().plus ( salesOrder221AjaxService.productDeliveryCycle(params.int('productId').toLong()) )
			
			salesOrderDetailInstance.quantity = 0
			salesOrderDetailInstance.amount = 0

		if(salesOrderDetailInstance.openDetail == true){//recalculate discount, specialDiscount, finalDiscount, price, finalPrice

			def j = 0
			//reset price, finalPrice
			salesOrderDetailInstance.price = 0
			salesOrderDetailInstance.finalPrice = 0

			for(bd in salesOrder221AjaxService.productBom(productId)) {
				j++
				BindDynamicMethod bdmDetail = new BindDynamicMethod()
				def mapDetail = [:]
					mapDetail['product.id'] = bd[5]
				def salesOrderDetailDetailInstance = new SalesOrderDetailDetail();
				
					salesOrderDetailDetailInstance.serialNumber = (j).toString().padLeft(2, '0')

					salesOrderDetailDetailInstance.price = bd[4]
					salesOrderDetailDetailInstance.isAllowZeroPrice = salesOrder221AjaxService.isAllowZeroPrice(bd[5])
					
					salesOrderDetailDetailInstance.discount = salesOrder221AjaxService.productDiscount (bd[5])?:1
					
					
					salesOrderDetailDetailInstance.isAllowSpecialDiscount = salesOrder221AjaxService.isAllowSpecialDiscount(bd[5])
					if(salesOrderDetailDetailInstance.isAllowSpecialDiscount == true){
						def child_product_special_discount = salesOrder221AjaxService.productSpecialDiscount(dealerId, industryId, project, bd[5])
						if(child_product_special_discount != null){
							mapDetail['contractDetail.id'] = child_product_special_discount[0]
							salesOrderDetailDetailInstance.specialDiscount = child_product_special_discount[1]?:0
						}else{
							salesOrderDetailDetailInstance.specialDiscount = 0
						}					
					}else{
						salesOrderDetailDetailInstance.specialDiscount = 0
					}
					
					salesOrderDetailDetailInstance.finalDiscount = salesOrderDetailDetailInstance.discount * (1 - salesOrderDetailDetailInstance.specialDiscount)
					
					//面价*最终折扣
					salesOrderDetailDetailInstance.finalPrice = salesOrderDetailDetailInstance.price * salesOrderDetailDetailInstance.finalDiscount  //实际售价

					salesOrderDetailDetailInstance.dosage = salesOrder221AjaxService.dosage(productId, bd[5])
					salesOrderDetailDetailInstance.quota = salesOrder221AjaxService.quota(productId, bd[5])

					//面价*组成用量/工时底数
					salesOrderDetailInstance.price += bd[4] * bd[3] / bd[6]
					salesOrderDetailInstance.finalPrice += bd[4] * bd[3] * salesOrderDetailDetailInstance.finalDiscount/ bd[6]

	    		
	    		def argsDetail =  [salesOrderDetailDetailInstance, mapDetail]
	    		bdmDetail.invoke(salesOrderDetailDetailInstance, 'bind', (Object[]) argsDetail)

				salesOrderDetailInstance.addToSalesOrderDetailDetails(salesOrderDetailDetailInstance)

			}
			
			salesOrderDetailInstance.isAllowZeroPrice = salesOrder221AjaxService.isAllowZeroPrice(productId)
			salesOrderDetailInstance.isAllowSpecialDiscount = salesOrder221AjaxService.isAllowSpecialDiscount(productId)

			salesOrderDetailInstance.discount = salesOrder221AjaxService.productDiscount(productId)?:1 
			salesOrderDetailInstance.finalDiscount = salesOrderDetailInstance.finalPrice / (salesOrderDetailInstance.price?:1)
			
			if(salesOrderDetailInstance.finalDiscount > salesOrderDetailInstance.discount){
				salesOrderDetailInstance.discount = salesOrderDetailInstance.finalDiscount
			}			
			
			salesOrderDetailInstance.specialDiscount = 1 - salesOrderDetailInstance.finalPrice/((salesOrderDetailInstance.price?:1)*(salesOrderDetailInstance.discount?:1))
			
			
	    }else{
			salesOrderDetailInstance.discount = salesOrder221AjaxService.productDiscount(productId)?:1
			
			salesOrderDetailInstance.isAllowSpecialDiscount = salesOrder221AjaxService.isAllowSpecialDiscount(productId)
			
			if(salesOrderDetailInstance.isAllowSpecialDiscount == true){
				def parent_product_special_discount =salesOrder221AjaxService.productSpecialDiscount(dealerId, industryId, project, productId)
				if(parent_product_special_discount != null){
					mymap['contractDetail.id'] = parent_product_special_discount[0]
					salesOrderDetailInstance.specialDiscount = parent_product_special_discount[1]?:0
				}else{
					salesOrderDetailInstance.specialDiscount = 0
				}
			}else{
				salesOrderDetailInstance.specialDiscount = 0
			}
			salesOrderDetailInstance.finalDiscount = salesOrderDetailInstance.discount * (1 - salesOrderDetailInstance.specialDiscount)
			
			salesOrderDetailInstance.price = salesOrder221AjaxService.productPrice(productId)
			salesOrderDetailInstance.isAllowZeroPrice = salesOrder221AjaxService.isAllowZeroPrice(productId)
			
			salesOrderDetailInstance.finalPrice = salesOrderDetailInstance.price * salesOrderDetailInstance.finalDiscount
	    }

	    def myargs =  [salesOrderDetailInstance, mymap]
	    mybind.invoke(salesOrderDetailInstance, 'bind', (Object[]) myargs)

		render template : '/b2b/salesOrder221/editDetail', model : [salesOrderDetailInstance : salesOrderDetailInstance, i : params.int('rowId')]
	}

    //产品仓管分类
    def productCategoryOfCG = {
        def category = salesOrder221AjaxService.productCategory2(params.int('productId').toLong())
        render "${category}"
    }

	//允许零价格
    def isAllowZeroPrice = {
        def isAllowZeroPrice = salesOrder221AjaxService.isAllowZeroPrice(params.int('productId').toLong())
        render "${isAllowZeroPrice}"  
    }

	//交货周期
    def deliveryCycle = {
        def deliveryCycle = salesOrder221AjaxService.productDeliveryCycle(params.int('productId').toLong())
        render "${deliveryCycle?:0}"
    }

	//组成用量
    def dosage = {
        def dosage = salesOrder221AjaxService.dosage(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${dosage}" 
    }

	//工时底数
    def quota = {
        def quota = salesOrder221AjaxService.quota(params.int('masterProductId').toLong(), params.int('detailProductId').toLong())
        render "${quota}" 
    }
    
    //获取经销商所属办事处
    def getDealerDepartment = {
        def department = salesOrder221AjaxService.dealerDeaprtment(params.dealerSerialNumber)
        render "${department}"
	}
	
	//订单行业列表
    def industryList = {
        render salesOrder221AjaxService.industryList(params.term) as JSON
    }
    
    def projectList = {
        render salesOrder221AjaxService.projectList(params.dealer, params.industry, params.term) as JSON
    }

    def searchProduct = {
        render salesOrder221AjaxService.searchProduct(params.term) as JSON
    }
    
    def workflowHistory = {
		def historys = salesOrder221AjaxService.workflowHistory(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder221/workflowHistory', model : [workflowHistoryInstanceList : historys, objectId : params.int('salesOrderId'), ownerId : params.int('ownerId')]
    }
    
    def outBoundMessage = {
		def outBoundMessageInstanceList = salesOrder221AjaxService.outBoundMessage(params.int('salesOrderId').toLong())
        render view : '/b2b/salesOrder221/outBoundMessage', model : [outBoundMessageInstanceList : outBoundMessageInstanceList]
    }

}
