package org.leaf.eos2.b2b

import org.springframework.transaction.annotation.*
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod
import org.apache.shiro.SecurityUtils
import org.hibernate.FetchMode as FM

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.RecordType

class SalesOrder221Service {

	static transactional = true
   
   	def utilityService

    //record type
    @Transactional(readOnly = true)
    def recordType() {
        def recordType221 = RecordType.withCriteria(uniqueResult:true){
            projections{
                property('id')
            }
            eq("domain", "salesOrder")
            eq("serialNumber", "221")
        }
        return recordType221
    }    
																													    
    def init(Object params) {
        def salesOrderInstance = new SalesOrder()

        def currentUserId = utilityService.currentUserId()
    	//get dealer, department, salesman
	    def dealer = Dealer.withCriteria(uniqueResult:true){
	    	createAlias 'department', 'dept'
		    createAlias 'salesMan', 'staff'
		    createAlias 'owner', 'owner'
		    projections {
		    	property('id')
			    property('dept.id')
			    property('staff.id')
			    property('address1')
			    property('address2')
			}
			join "department"
			join "salesMan"
			join "owner"
			eq('owner.id', currentUserId)
			maxResults(1)
			order("serialNumber", "asc")
		}

        salesOrderInstance.status = "草稿"
		salesOrderInstance.quantity = 0.00
		salesOrderInstance.amount = 0.00
		salesOrderInstance.specialAmount = 0.00
        
		if(dealer) {
             salesOrderInstance.dealer = Dealer.get(dealer[0])
             salesOrderInstance.salesMan = Staff.get(dealer[2])
	         salesOrderInstance.address1 = dealer[3]
	         salesOrderInstance.address2 = dealer[4]
	    }
	    //dynamic method
	    if(currentUserId){
	         BindDynamicMethod mybind = new BindDynamicMethod()
	         def mymap = ['createdBy.id' : currentUserId, 'lastModifiedBy.id' : currentUserId, 'owner.id' : currentUserId, 'recordType.id' : this.recordType()]
	         def myargs =  [salesOrderInstance, mymap]
	         mybind.invoke(salesOrderInstance, 'bind', (Object[]) myargs)
	    }
	    return salesOrderInstance
	}
	
	def insert(SalesOrder salesOrderInstance){
		def success = false
		def error_times = 0
		def error_times_limit = 3
		while (error_times < error_times_limit){
			salesOrderInstance.serialNumber = "EB221-" + new Date().format('yyyyMMdd') + '-' + this.lastSerialNumber()
			if(salesOrderInstance.validate() && salesOrderInstance.save(flush:true)){
				success = true
				break;
			}else{
				def breakable = false
				salesOrderInstance.errors.allErrors.each{ it ->
					if(it.field == 'serialNumber'){
						breakable = true
					}
				}
				if(breakable == true){
					break
				}else{
					error_times += 1
				}
			}
		}
		return success;
	}
	
    //record type
    @Transactional(readOnly = true)
    def lastSerialNumber() {
    	def result = ""
    	SalesOrder.withNewSession { session ->
    	
			def lastSerialNumber = SalesOrder.withCriteria(uniqueResult:true){
				createAlias 'recordType', 'rt'
				projections {
					property('serialNumber')
				}
				join "recordType"
				eq("rt.domain", "salesOrder")
				eq("rt.serialNumber", "221")
				ge("dateCreated", new Date().clearTime())
				order("id", "desc")
				maxResults(1)
			}
			
			if(lastSerialNumber == null){
				result = "001"
			}else{
				def pos = lastSerialNumber.lastIndexOf('-') + 1
				def temp = lastSerialNumber[pos..-1]
				//log.info("temp=${temp}")
				result = (temp.toInteger() + 1).toString().padLeft(3, '0')
			}
		}
        return result
    }

    def update(SalesOrder salesOrderInstance, Object params) {
        def success = false

		SalesOrder.withTransaction{ status ->
			//delete details
			SalesOrderDetail.findAllBySalesOrder(salesOrderInstance).each { it.delete(flush:true)}
			salesOrderInstance.save(flush: true)  

			salesOrderInstance.properties = params
	
			//get user
			def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
			//set instance params
			salesOrderInstance.lastModifiedBy = currentUser
			salesOrderInstance.lastUpdated = new java.util.Date()
			//save
			if(salesOrderInstance.validate() && salesOrderInstance.save(flush:true)){
				success = true
			}else{
				status.setRollbackOnly()
			}
		}
        return success
    }
}
