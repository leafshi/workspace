package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role
import org.leaf.eos2.admin.ShareRoleService

import org.apache.shiro.SecurityUtils

import org.springframework.transaction.annotation.*

class ContractService {

    static transactional = false

	def shareRoleService

    @Transactional(readOnly = true)
    def list(Object params) {
	
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("contract", "list")
	
        def contractInstanceList = Contract.withCriteria{
			if(params?.max) maxResults(params.int('max'))
			if(params?.offset) firstResult(params.int('offset'))
			if(params?.sort && params?.order) order(params?.sort, params?.order)
            if(currentUserRole.isAdmin == false) {
    			or{
    				inList("owner.id", shareUsers)
    				eq("owner", currentUser)
    			}
            }
		}
        return contractInstanceList
    }

    @Transactional(readOnly = true)
    def count(Object params) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        def shareUsers = shareRoleService.getShareUserList("contract", "list")
        def count = Contract.withCriteria{
			projections{
				rowCount()
			}
            if(currentUserRole.isAdmin == false) {
    			or{
    				inList("owner.id", shareUsers)
    				eq("owner", currentUser)
    			}
            }
		}
        return (count ==null)? 100:  count[0]
    }

    @Transactional(readOnly = true)
    def show(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("contract", "show")
        def contractInstance = Contract.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return contractInstance
    }
    def edit(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("contract", "edit")
        def contractInstance = Contract.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return contractInstance
    }

    def save(contractInstance) {
		Contract.withTransaction{ status ->
			try {
				//get user
				def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
				//set instance params
				//validate
				contractInstance.validate()
				//save
				contractInstance.save(flush: true)
			}catch(e){
				log.error("create contract, ${e}")
				status.setRollbackOnly()
			}
		}
    }
    
    def update(contractInstance) {
		Contract.withTransaction{ status ->
			try {
				//get user
				def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
				//set instance params
				contractInstance.lastUpdated = new java.util.Date()
				//validate
				contractInstance.validate()
				//save
				contractInstance.save(flush: true)
			}catch(e){
				log.error("update contract for instance ${contractInstance} error, ${e}")
				status.setRollbackOnly()
			}
		}
    }
    
    def delete(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("contract", "delete")
        def contractInstance = Contract.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return contractInstance
    }

}
