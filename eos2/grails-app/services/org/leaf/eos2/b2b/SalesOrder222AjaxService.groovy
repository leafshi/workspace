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
    
    	def dealerProductList = DealerProduct.withCriteria{
        
        	createAlias 'product', 'p'
        	
            projections {
                groupProperty('p.serialNumber')
            }
            
            join "product"
            
            or{
                ilike("p.serialNumber", term + "%")
                ilike("p.standard", term + "%")
            }
            and{
                not{like("p.serialNumber", '1%')}
                not{like("p.serialNumber", '2%')}
                not{like("p.serialNumber", '4%')}
            }
            eq("p.isActive", true)
            order("p.serialNumber", "asc")
            maxResults(10)
        }
        log.info("dealerProductList=${dealerProductList}")
        if(dealerProductList.size() > 0){
			def productList = Product.withCriteria{
		
				projections {
					property('id')
					property('name')
					property('serialNumber')
					property('standard')
					property('price')
				}
				inList("serialNumber", dealerProductList)
				order("serialNumber", "asc")
				maxResults(10)
			}
			log.info("productList=${productList}")
			productList.each{ productInstance -> 
				log.info("productInstance=${productInstance}")
				productInstance[4] = this.productPrice(productInstance[0])
				log.info("productInstance=${productInstance}")
			}
			log.info("productList=${productList}")
			return productList
        }else{
        	return [];
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
    
        def price = DealerProduct.withCriteria(uniqueResult:true){
        
        	createAlias 'product', 'p'
        	
            projections {
                property('price')
            }
            join "product"
			or{
				and{
					le("beginDate", new Date())
					ge("closeDate", new Date())
				}
				and{
					le("beginDate", new Date())
					isNull("closeDate")
				}
			}
            
            eq("p.id", productId)
            order("approvalDate", "desc")
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
