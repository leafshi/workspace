package org.leaf.eos2.admin


import org.apache.shiro.SecurityUtils

import org.springframework.transaction.annotation.*

import org.leaf.eos2.admin.ShareRoleService

import org.leaf.eos2.shiro.User
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
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "list")
		def result = SalesOrder.withCriteria{
			projections{
                groupProperty('status')
                count('status')
            }
            if(currentUser.username != 'admin') {
    	        or{
    		        and{
                        inList("owner.id", shareUsers)
                        inList("status", ["待审批（办事处）", "待审批（商务部）", "审批通过", "审批未通过"])
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
