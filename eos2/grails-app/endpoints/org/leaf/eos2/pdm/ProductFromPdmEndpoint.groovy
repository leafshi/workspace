package org.leaf.eos2.pdm

import org.grails.cxf.utils.EndpointType

import org.leaf.eos2.b2b.Product

import groovy.sql.Sql

class ProductFromPdmEndpoint {

    static expose = EndpointType.SIMPLE

    static excludes = ['transformFromERP']

    static transactional = false
    def messageSource
    
	//def dataSource_erp

    String receive(String productSerialNumber) {
    	log.info("receive form pdm, productSerialNumber=${productSerialNumber}")
    	def result = "0,成功"
    	def productFromPdmInstance
    	
    	
    	
        ProductFromPdm.withTransaction{ status ->
        	try{
        		def isOkay = transformFromERP(productSerialNumber)
        		productFromPdmInstance = new ProductFromPdm(
        		  productSerialNumber : productSerialNumber
        		, working : false
        		, finished : true
        		, isOkay : isOkay
        		, sentToPdm : true
        		)
        		productFromPdmInstance.validate()
        		productFromPdmInstance.save(flush:true)
        	}catch(e){
        		status.setRollbackOnly()
        		result ="-1"
        		productFromPdmInstance.errors.allErrors.each{ it ->
        			result = "${result},${messageSource.getMessage(it, null)}"
				}
				log.error("receive form pdm error, productSerialNumber=${productSerialNumber}, error=${result}")
        	}finally{
        		//sqlInstance.close()
        	}
        }
        return result;
    }
    
    Boolean transformFromERP(String productSerialNumber){
    	def result = false;
    	Product.withTransaction{ status ->
    		try{
        		def productInstance = Product.findBySerialNumber(productSerialNumber)
        		
        		if(!productInstance){
        			productInstance = new Product(serialNumber: productSerialNumber)
        		}
        	
        		def sqlInstance = Sql.newInstance(
        			"jdbc:sqlserver://192.168.1.22;databaseName=etl;"
        			, "sa"
        			, "sa"
        			, "com.microsoft.sqlserver.jdbc.SQLServerDriver") 
        		
        		sqlInstance.eachRow("""
					SELECT
					  RTRIM(MB.MB001) AS SerialNumber
					, RTRIM(MB.MB002) AS ProductName
					, RTRIM(MB.MB003) AS Standard
					, RTRIM(MB.MB004) AS Unit
					, MB.MB047 AS Price
					, MB.MB101 AS IsIncludeTax
					, MB.MB109 AS IsActive
					
					, RTRIM(MB.MB005) AS CATEGORY1
					, RTRIM(MB.MB006) AS CATEGORY2
					, RTRIM(MB.MB007) AS CATEGORY3
					, RTRIM(MB.MB008) AS CATEGORY4
					, RTRIM(MB.MB200) AS CATEGORY5
					
					FROM [Leader].[dbo].INVMB MB
					WITH(NOLOCK)
					WHERE RTRIM(MB.MB001) = ${productSerialNumber}
				"""
        		){ row ->
        			productInstance.name = row.ProductName
        			productInstance.standard = row.Standard
        			productInstance.unit = row.Unit
        			productInstance.price = row.Price
        			productInstance.isIncludeTax = (row.IsIncludeTax == 'Y')?true:false
        			productInstance.isActive = (row.IsActive == 'Y')?true:false
        			
        			if(row.CATEGORY1 == ''){
        				ProductCategory
        			}
        		}
        		
        		productInstance.validate()
        		productInstance.save(flush:true)
        		result = true
        	}catch(e){
        		status.setRollbackOnly()
        		log.error("get product from erp error:${e}")
        	}
        }
        return result
    
    }

}

