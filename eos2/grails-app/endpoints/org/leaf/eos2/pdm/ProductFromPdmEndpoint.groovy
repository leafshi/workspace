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
    	def sqlInstance
		try{

			def dbProperty = [
			  url : "jdbc:sqlserver://192.168.1.31;databaseName=etl;"
			, user : "sa"
			, password : "P@55word"
			, driver : "com.microsoft.sqlserver.jdbc.SQLServerDriver"
			]			
			
		
			sqlInstance = Sql.newInstance(
			  dbProperty.url
			, dbProperty.user
			, dbProperty.password
			, dbProperty. driver) 
			
			sqlInstance.execute("""
				EXECUTE SYNC_JOB_PRODUCT_FROM_ERP '${productSerialNumber}'
			"""
			)
			result = true
		}catch(e){
			log.error("get product from erp error:${e}")
		}finally{
			sqlInstance.close()
		}
        return result
    
    }

}

