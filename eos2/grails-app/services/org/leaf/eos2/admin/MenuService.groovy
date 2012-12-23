package org.leaf.eos2.admin


import org.apache.shiro.SecurityUtils

import org.springframework.transaction.annotation.*

import org.leaf.eos2.admin.ShareRoleService

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role
import org.leaf.eos2.b2b.SalesOrder
import org.leaf.eos2.birt.Report

import org.leaf.eos2.ws.OutBound

class MenuService {

	static transactional = true

	def shareRoleService

    @Transactional(readOnly = true)
	def myOrderList(){
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        //log.info("currentUser.username=${currentUser.username}")
        def currentUserRole = Role.get(currentUser.role.id)
        //log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")

        def shareUsers = shareRoleService.getShareUserList("salesOrder", "list")
		def result = SalesOrder.withCriteria{
			projections{
                groupProperty('status')
                count('status')
            }
            if(currentUserRole.isAdmin == false) {
    	        or{
    		        and{
                        inList("owner.id", shareUsers)
                        ne("status", "草稿")
                    }
    			    eq("owner", currentUser)
     	    	}
            }
		}
		return result
	}
	
    @Transactional(readOnly = true)
	def outBoundList(){
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def shareUsers = shareRoleService.getShareUserList("outBound", "list")
		def result = OutBound.withCriteria{
			projections{
                groupProperty('stage')
                count('stage')
            }
            if(currentUser.username != 'admin') {
    	        or{
                    inList("owner.id", shareUsers)
    			    eq("owner", currentUser)
     	    	}
            }
		}
		return result
	}
	
    @Transactional(readOnly = true)
	def reportList(){
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def shareUsers = shareRoleService.getShareUserList("report", "list")
		def result = Report.withCriteria{
			projections{
                property('name')
                property('title')
            }
		}
		return result
	}
}
