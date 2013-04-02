package org.leaf.eos2.b2b

import org.leaf.eos2.wf.WorkflowHistory
import org.leaf.eos2.wf.WorkflowStep

import org.leaf.eos2.ws.OutBound


class SalesOrderExtendService {

	static transactional = true

	//暴力删除订单，包括审批历史，出站消息，订单明细，子单身
    def forceDelete(id) {
        def success = false

		SalesOrder.withTransaction{ status ->
			def salesOrderInstance = SalesOrder.get(id.toLong())
			try {
				//delete workflow history
				WorkflowHistory.findAllByObjectNameAndObjectId('salesOrder', id.toLong()).each{it.delete(flush:true)}
				//delete outbound message
				OutBound.findAllByObjectNameAndObjectId('salesOrder', id.toLong()).each{it.delete(flush:true)}
				//delete details
				SalesOrderDetail.findAllBySalesOrder(salesOrderInstance).each { 
					it.delete(flush:true);
				}
				salesOrderInstance.save(flush: true) 
				salesOrderInstance.delete(flush: true)
				success = true
				log.info("force delete salesOrder success, id is ${id}");
			}catch (e) {
				log.error("force delete salesOrder failed, id is ${id}, error is ${e}");
				status.setRollbackOnly()
			}        
		}
        return success
    }
    //清除ERP单号
    def clearErpSerialNumber(id){
        def success = false

		SalesOrder.withTransaction{ status ->
			def salesOrderInstance = SalesOrder.get(id.toLong())
			try {
				salesOrderInstance.erpSerialNumber = null;
				salesOrderInstance.save(flush: true) 
				success = true
				log.info("clear erp serialNumber for  salesOrder ${id} success");
			}catch (e) {
				log.error("clear erp serialNumber for salesOrder ${id} failed, error is ${e}");
				status.setRollbackOnly()
			}        
		}
        return success
    }
}
