package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role
import org.leaf.eos2.admin.ShareRoleService

import org.apache.shiro.SecurityUtils

import org.springframework.transaction.annotation.*
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod


class DealerProductService {
    static transactional = true

	def shareRoleService

    @Transactional(readOnly = true)
    def list(Object params) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        def shareUsers = shareRoleService.getShareUserList("dealer", "list")
        def dealerProductInstanceList = DealerProduct.withCriteria{
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
        return dealerProductInstanceList
    }

    @Transactional(readOnly = true)
    def count(Object params) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        def shareUsers = shareRoleService.getShareUserList("dealer", "list")
        def count = DealerProduct.withCriteria{
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
        return (count == null)? 0:  count[0]
    }
    
    def create(){
        //get user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
		//create instance
    	def dealerProductInstance = new DealerProduct();
    	//dynamic bind
		BindDynamicMethod mybind = new BindDynamicMethod();
		def mymap = ['createdBy.id' : currentUser.id, 'lastModifiedBy.id' : currentUser.id, 'owner.id' : currentUser.id];
		def myargs =  [dealerProductInstance, mymap];
		mybind.invoke(dealerProductInstance, 'bind', (Object[]) myargs);
    	return dealerProductInstance;
    }

    def save(dealerProductInstance) {
        //validate
        dealerProductInstance.validate()
        //save
        dealerProductInstance.save(flush: true)
    }

    @Transactional(readOnly = true)
    def show(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        def shareUsers = shareRoleService.getShareUserList("dealer", "show")
        def dealerProductInstance = DealerProduct.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return dealerProductInstance
    }
    def edit(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        def shareUsers = shareRoleService.getShareUserList("dealer", "edit")
        def dealerProductInstance = DealerProduct.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return dealerProductInstance
    }
    
    def update(dealerProductInstance) {
        //get user
        def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        //set instance params
		BindDynamicMethod mybind = new BindDynamicMethod();
		def mymap = ['lastModifiedBy.id' : currentUser.id];
		def myargs =  [dealerProductInstance, mymap];
		mybind.invoke(dealerProductInstance, 'bind', (Object[]) myargs);
        //validate
        dealerProductInstance.validate()
        //save
        dealerProductInstance.save(flush: true)
    }
    
    def delete(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("dealer", "delete")
        def dealerProductInstance = DealerProduct.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUserRole.isAdmin == false) {
                or{
                    inList("owner.id", shareUsers)
                    eq("owner", currentUser)
                }
            }
        }
        return dealerProductInstance
    }
}

