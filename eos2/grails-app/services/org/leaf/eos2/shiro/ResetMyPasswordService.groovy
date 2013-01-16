package org.leaf.eos2.shiro

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash

import org.springframework.transaction.annotation.*

class ResetMyPasswordService {

    static transactional = true 
    
	@Transactional(readOnly = true)
	def currentUser() {
    	def userInstance = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
		return userInstance
    }
    
    def resetPassword(String password, String passwordConfirm){
        def result = false
		//get current user
        def userInstance = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        User.withTransaction{ status ->
            if(password != null && !password.trim().equals('') && password.size() >= 8 && password.equals(passwordConfirm)){
                userInstance.passwordHash = new Sha512Hash(password).toHex()
                userInstance.validate()
                userInstance.save(flush: true)
                result = !userInstance.hasErrors()
                if(result != true){
                    status.setRollbackOnly()
                }
            }else{
                userInstance.errors.reject(
                    'user.passwordHash.doesnotmatch',                                    // Error code within the grails-app/i18n/message.properties
                    [] as Object[],                          // Groovy list cast to Object[]
                    '[password does not match confirmation]')   // Default mapping string

            }
        }
        return result
    }
}