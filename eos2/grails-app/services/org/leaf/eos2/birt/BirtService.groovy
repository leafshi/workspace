package org.leaf.eos2.birt


import org.springframework.transaction.annotation.*

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role
import org.leaf.eos2.b2b.Dealer

import org.apache.shiro.SecurityUtils

import org.leaf.eos2.admin.ShareRoleService
import org.hibernate.FetchMode as FM


class BirtService {

    static transactional = true
    
    def shareRoleService

    @Transactional(readOnly = true)
    def currentUser() {
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        return currentUser.id
    }
    
    @Transactional(readOnly = true)
    def dealerInstanceList() {
	
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        
        def shareUsers = shareRoleService.getShareUserList("dealer", "list")
	
        def dealerInstanceList = Dealer.withCriteria{
            projections{
                property('serialNumber')
                property('name')
            }
            if(currentUserRole.isAdmin == false) {
    			or{
					inList("owner.id", shareUsers)
    				eq("owner", currentUser)
    			}
            }
            order("serialNumber", "asc")
		}
        return dealerInstanceList
    }
}
