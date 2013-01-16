package org.leaf.eos2.shiro

import org.apache.shiro.crypto.hash.Sha512Hash

class UserService {

    static transactional = true 
    
    def resetPassword(User userInstance, String password, String passwordConfirm){
        def result = false

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
