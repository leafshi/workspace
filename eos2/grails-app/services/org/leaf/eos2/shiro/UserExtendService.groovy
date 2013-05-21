package org.leaf.eos2.shiro

import com.digiwin.webservice.EOSWebService

import org.springframework.transaction.annotation.*


class UserExtendService {

	EOSWebService eOSWebServiceClient
	
	@Transactional(readOnly = true)	
	def getUserById(Long userId){
		def userInstance = User.get(userId)
		return userInstance
	}
	
    def registerECSUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = User.get(userId)
    		result = eOSWebServiceClient.registerUser(
    		  userInstance?.username?:""
    		, userInstance?.phone
    		, userInstance?.mail
    		);
    		if(result == "success"){
    			userInstance.registerECS = true
    			userInstance.activateECS = true
    			userInstance.validate()
    			userInstance.save(flush:true)
    			
    			result = true
    		}
    	}catch(e){
    		log.error("registerECSUser for ${userId} error : ${e}, result = ${result}")
    	}
		return result
    }

    def modifyECSUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = this.getUserById(userId)
    		result = eOSWebServiceClient.modifyUser(
    		  userInstance?.username
    		, userInstance?.phone
    		, userInstance?.mail
    		);
    		if(result == "success"){
    			
    			result = true
    		}
    		
    	}catch(e){
    		log.error("modifyECSUser for ${userId} error : ${e}, result = ${result}")
    	}
		return result
    }

    def activateECSUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = User.get(userId)
    		result = eOSWebServiceClient.activateUser(
    		  userInstance?.username
    		);
    		if(result == "success"){
    			userInstance.activateECS = true
    			userInstance.validate()
    			userInstance.save(flush:true)
    			result = true
    		}
    	}catch(e){
    		log.error("activateECSUser for ${userId} error : ${e}, result = ${result}")
    	}
		return result
    }
    
    def deactivateECSUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = User.get(userId)
    		result = eOSWebServiceClient.deactivateUser(
    		  userInstance?.username
    		);
    		if(result == "success"){
    			userInstance.activateECS = false
    			userInstance.validate()
    			userInstance.save(flush:true)
    			result = true
    		}
    	}catch(e){
    		log.error("deactivateECSUser for ${userId} error : ${e}")
    	}
		return result
    }
    
    def activeUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = User.get(userId)
    			userInstance.isActive = true
    			
    			userInstance.validate()
    			userInstance.save(flush:true)
    			
    			result = true
    	}catch(e){
    		log.error("activeUser for ${userId} error : ${e}")    	
    	}
		return result
    }
    
    def deactivateUser(Long userId) {
    	def result = false
    	try{
    		def userInstance = User.get(userId)
    			userInstance.isActive = false
    			
    			userInstance.validate()
    			userInstance.save(flush:true)
    			
    			result = true
    	}catch(e){
    		log.error("deactivateUser for ${userId} error : ${e}")    	
    	}
		return result
    }
    
}
