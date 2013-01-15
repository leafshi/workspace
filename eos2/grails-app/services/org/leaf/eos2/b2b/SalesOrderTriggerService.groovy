package org.leaf.eos2.b2b

import org.leaf.eos2.ws.OutBound
import org.leaf.eos2.ws.OutBoundService

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class SalesOrderTriggerService {

	def outBoundService

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
    	//log.info("refreshDeliveryLimitation")
    	SalesOrderDetail.withNewSession{
			SalesOrderDetail.findAllBySalesOrder(salesOrderInstance).each {detailInstance ->
				//log.info("detailInstance.deliveryCycle=${detailInstance.deliveryCycle}")
				def min_date = salesOrderInstance.orderDate.plus ( detailInstance.deliveryCycle )
				//log.info("min_date=${min_date}")
				//如果不是工作日，延时到下个周一
				if(min_date.day == 0){
					min_date = min_date.next()
				}else if(min_date.day == 6){
					min_date = min_date + 2
				}
				//log.info("detailInstance.deliveryLimitation=${detailInstance.deliveryLimitation}")
				//log.info("min_date=${min_date}")
				if(detailInstance.deliveryLimitation < min_date){
					//log.info("detailInstance.deliveryLimitation < min_date")
					detailInstance.deliveryLimitation = min_date
				}
				detailInstance.save(flush: true) 
			}
		}
    }
}
