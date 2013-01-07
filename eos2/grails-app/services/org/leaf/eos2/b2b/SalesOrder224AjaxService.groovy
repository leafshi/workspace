package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.wf.WorkflowHistory
import org.leaf.eos2.ws.OutBound

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM

class SalesOrder224AjaxService {

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
    
    @Transactional(readOnly = true)
    def projectList(dealer, industry, term) {
        term = term.trim()
        dealer = (dealer != "")?dealer.toLong():-1L
        industry = (industry != "")?industry.toLong():-1L

        return ContractDetail.withCriteria{

            createAlias 'contract', 'contract'
            createAlias 'contract.dealer', 'dealer'
            createAlias 'contract.industry', 'industry'
            createAlias 'contract.recordType', 'recordType'

            projections{
                groupProperty('contract.project')
                count('contract.project')
            }

            fetchMode "contract", FM.JOIN
            fetchMode "contract.recordType", FM.JOIN
            fetchMode 'contract.dealer', FM.JOIN
            fetchMode 'contract.industry', FM.JOIN
 
            eq("dealer.id", dealer)
			eq("industry.id", industry)
            eq("recordType.serialNumber", "224")
            le("contract.effectiveDate", new Date())
            ge("expiryDate", new Date())
            or{
                ilike("contract.project", "%"+term + "%")
                ilike("contract.project", "%"+term + "%")
            }

            order("contract.project", "asc")

            maxResults(10)
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

    //get product if has bom, decide salesOrderDetail open detail, recordtype serial number is 5
    @Transactional(readOnly = true)
    def productHasBom(productId) {
        def hasBom = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.hasBom')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "5")
            eq("p.isActive", true)
        }

        return hasBom ?: false
    }

    //get product master-detail price
    @Transactional(readOnly = true)
    def productBom(productId) {
        return BomDetail.withCriteria{
            createAlias 'bom', 'b'
            createAlias 'bom.product', 'p1'
            createAlias 'product', 'p2'
            projections {
                property('p2.serialNumber')
                property('p2.name')
                property('p2.standard')
                property('dosage')
                property('p2.price')
                property('p2.id')
                property('quota')
            }
            join "bom"
            join "bom.product"
            join "product"
            eq("p1.id", productId)
            order("serialNumber", "asc")
        }
    }

    //get product category, recordtype serial number is 2
    @Transactional(readOnly = true)
    def productCategory2(productId) {
        def category = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.name')
                //property('c.id')
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
    
    @Transactional(readOnly = true)
    def isAllowSpecialDiscount(productId) {
        def result = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.isAllowSpecialDiscount')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "5")
            eq("p.isActive", true)
        }
        return (result == true)?true:false
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

    //get bom dosage
    @Transactional(readOnly = true)
    def dosage (masterProductId, detailProductId) {
        return BomDetail.withCriteria(uniqueResult:true){
            createAlias 'bom', 'b'
            createAlias 'bom.product', 'p1'
            createAlias 'product', 'p2'
            projections {
                property('dosage')
            }
            join "bom"
            join "bom.product"
            join "product"
            eq("p1.id", masterProductId)
            eq("p2.id", detailProductId)
        }
    }

    @Transactional(readOnly = true)
    def quota (masterProductId, detailProductId) {
        return BomDetail.withCriteria(uniqueResult:true){
            createAlias 'bom', 'b'
            createAlias 'bom.product', 'p1'
            createAlias 'product', 'p2'
            projections {
                property('quota')
            }
            join "bom"
            join "bom.product"
            join "product"
            eq("p1.id", masterProductId)
            eq("p2.id", detailProductId)
        }
    }
    
    //get product discount, recordtype serial number is 5
    @Transactional(readOnly = true)
    def productDiscount(productId) {
        def discount = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                property('c.discount')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "5")
            eq("p.isActive", true)
        }
        return discount?:1
    }
    
    //get product special discount-sales order record type = 224
    @Transactional(readOnly = true)
    def productSpecialDiscount(dealerId, industryId, project, productId){
    
        dealerId = dealerId ?: ""
        dealerId = (dealerId != "") ? dealerId.toLong():-1L
        
        industryId = industryId ?: ""
        industryId = (industryId != "") ? industryId.toLong():-1L

        project = project ?:""
        
        productId = productId ?: ""
        productId = (productId != "") ? productId.toLong():-1L

        def categoryId = ProductCategory.withCriteria(uniqueResult:true){
            createAlias 'product', 'p'
            createAlias 'category', 'c'
            createAlias 'category.recordType', 'rt'
            projections {
                //property('c.name')
                property('c.id')
            }
            join "product"
            join "category"
            join "category.recordType"
            eq("p.id", productId)
            eq("rt.serialNumber", "2")
            eq("p.isActive", true)
        }
        categoryId = categoryId ?: -1L
		//log.info("categoryId=${categoryId}")
        def fobjContractDetail = ContractDetail.withCriteria(uniqueResult:true){
            createAlias 'contract', 'm'
            createAlias 'contract.recordType', 'rt'
            projections{
                property('id')
                property('specialDiscount')
                //property('m.serialNumber')
                //property('serialNumber')
            }
            fetchMode "contract", FM.JOIN
            fetchMode "contract.recordType", FM.JOIN
            eq("rt.serialNumber", "224")
            eq("m.dealer.id", dealerId)
            eq("m.industry.id", industryId)
            eq("category.id", categoryId)
            le("m.effectiveDate", new Date())
            ge("expiryDate", new Date())
            eq("m.project", project)
            order("specialDiscount", "desc")
            maxResults(1)
        }
		//log.info("fobjContractDetail=${fobjContractDetail}")
        return fobjContractDetail;
		/*
        def fmapContractDetail = [:]

        if(fobjContractDetail!=null){
            fmapContractDetail.put('id', fobjContractDetail[0])
            fmapContractDetail.put('discount', fobjContractDetail[1])
            fmapContractDetail.put('specialDiscount', fobjContractDetail[2])
            fmapContractDetail.put('finalDiscount', fobjContractDetail[3])
            fmapContractDetail.put('serialNumber', fobjContractDetail[4] + '-' + fobjContractDetail[5])
        }else{
            fmapContractDetail = null
        }
        return fmapContractDetail
        */
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
