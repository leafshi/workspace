package org.leaf.eos2.b2b

import org.leaf.eos2.ws.OutBound
import org.leaf.eos2.ws.OutBoundService

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class SalesOrderTriggerService {

	def outBoundService
	def salesOrderCalculaterService
	
	static transactional = false
	
    def createOutBoundMessage(objectId, ownerId) {
		OutBound.withNewSession{
			def currentUserId = outBoundService.getCurrentUser()?.id
			def outBoundInstance = new OutBound(
				  objectName:'salesOrder'
				, objectId : objectId
				, method : 'transferSalesOrder'
				, asynchronous : false 
			)
			BindDynamicMethod mybind = new BindDynamicMethod()
			def mymap = ['createdBy.id' : currentUserId, 'lastModifiedBy.id' : currentUserId, 'owner.id' : ownerId]
			def myargs =  [outBoundInstance, mymap]
			mybind.invoke(outBoundInstance, 'bind', (Object[]) myargs)
			outBoundInstance.save(flush:true);
		}
    }
    
    def refreshDeliveryLimitation(salesOrderInstance){
    	SalesOrderDetail.withNewSession{
			SalesOrderDetail.findAllBySalesOrder(salesOrderInstance).each {detailInstance ->
				def min_date = salesOrderCalculaterService.firstDeliveryDate ( detailInstance.deliveryCycle )
				if(detailInstance.deliveryLimitation < min_date){
					log.info("update delivery limitation for salesOrderDetailInstance ${detailInstance}, source=${detailInstance.deliveryLimitation}, target=${min_date} ")
					detailInstance.deliveryLimitation = min_date
				}
				detailInstance.save(flush: true) 
			}
		}
    }
}
