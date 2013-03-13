package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.wf.WorkflowHistory
import org.leaf.eos2.ws.OutBound

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM

class SalesOrder222AjaxService {

	static transactional = true

    //get dealer department
    @Transactional(readOnly = true)
    def dealerDeaprtment(dealerSerialNumber){
        def department = Dealer.withCriteria(uniqueResult:true){
            createAlias 'department', 'dept'
            projections{
                property("dept.serialNumber")
                property("dept.name")
            }
            fetchMode "department", FM.JOIN
            eq("serialNumber", dealerSerialNumber)
        }
        if(department == null){
	        department = []
	        department.push('')
	        department.push('')
	    }
	    return "${department[0]?:''}-${department[1]?:''}"
    }
	
    //get industry list
    @Transactional(readOnly = true)
    def industryList(term) {
        term = term.trim()
        return Industry.withCriteria{
            projections{
                property('id')
                property('shortName')
                property('serialNumber')
            }
            or{
                ilike("serialNumber", term + "%")
                ilike("shortName", "%"+term + "%")
                ilike("fullName", "%"+term + "%")
            }
            eq("isActive", true)
            maxResults(10)
            order("serialNumber", "asc")
        }
    }
    //get product list
    @Transactional(readOnly = true)
    def searchProduct(term) {
        return Product.withCriteria{
            projections {
                property('id')
                property('name')
                property('serialNumber')
                property('standard')
                property('price')
            }
            or{
                ilike("serialNumber", term + "%")
                ilike("standard", term + "%")
            }
            and{
                not{like("serialNumber", '1%')}
                not{like("serialNumber", '2%')}
                not{like("serialNumber", '4%')}
            }
            eq("isActive", true)
            maxResults(10)
            order("serialNumber", "asc")
        }

    }
    
    //get product category, recordtype serial number is 2
    @Transactional(readOnly = true)
    def productCategory(productId) {
        def category = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.name')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "2")
            eq("p.isActive", true)
        }
		return "${category?:''}"
    }

    //get product price
    @Transactional(readOnly = true)
    def productPrice (productId){
        def price = Product.withCriteria(uniqueResult:true){
            projections{
                property("price")
            }
            eq("id", productId)
            maxResults(1)
        }
        return price ?: 0
    }

    @Transactional(readOnly = true)
    def isAllowZeroPrice(productId) {
        def result = 'N'
        def isAllowZeroPrice = ProductCategory.withCriteria(uniqueResult : true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.isAllowZeroPrice')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "5")
            eq("p.isActive", true)
        }
        //如果没有属性，默认允许零价格
        if(isAllowZeroPrice == null){
        	isAllowZeroPrice = true
        }
        return (isAllowZeroPrice == true)?true:false
    }

    //get product delivery cycle, recordtype serial number is 2
    @Transactional(readOnly = true)
    def productDeliveryCycle(productId) {
        return ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.deliveryCycle')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "2")
            eq("p.isActive", true)
        }
    }
    
    @Transactional(readOnly = true)
	def workflowHistory(salesOrderId){
		salesOrderId = (salesOrderId != "") ? salesOrderId:-1L
		return WorkflowHistory.withCriteria(){
			eq("objectName", 'salesOrder')
			eq("objectId", salesOrderId)
			order("id", "desc")
		}
	}
	
    @Transactional(readOnly = true)
    def outBoundMessage(salesOrderId){
		salesOrderId = (salesOrderId != "") ? salesOrderId:-1L
		return OutBound.withCriteria(){
			eq("objectName", 'salesOrder')
			eq("objectId", salesOrderId)
			order("id", "desc")
		}
	}
}
