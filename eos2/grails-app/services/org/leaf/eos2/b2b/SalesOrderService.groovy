package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role

import org.leaf.eos2.wf.WorkflowHistory
import org.leaf.eos2.wf.WorkflowStep

import org.leaf.eos2.ws.OutBound

import org.leaf.eos2.admin.ShareRoleService
import org.apache.shiro.SecurityUtils
import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM

import org.springframework.dao.DataIntegrityViolationException

class SalesOrderService {

    static transactional = true

	def shareRoleService

    @Transactional(readOnly = true)
    def list(Object params) {
	
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        //log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "list")
	
        def salesOrderInstanceList = SalesOrder.withCriteria{
			if(params?.max) maxResults(params.int('max'))
			if(params?.offset) firstResult(params.int('offset'))
			if(params?.sort && params?.order) order(params?.sort, params?.order)
			if(params?.status) eq("status", params?.status)
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
        return salesOrderInstanceList
    }

    @Transactional(readOnly = true)
    def count(Object params) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "list")
        def count = SalesOrder.withCriteria{
			projections{
				rowCount()
			}
			if(params?.status) eq("status", params?.status)
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
        return (count ==null)? 100:  count[0]
    }

    @Transactional(readOnly = true)
    def show(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        //log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "show")
        def salesOrderInstance = SalesOrder.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
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
        return salesOrderInstance
    }
    def edit(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        //log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "edit")
        def salesOrderInstance = SalesOrder.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
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
        return salesOrderInstance
    }
    
    def delete(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        //log.info("currentUserRole.isAdmin=${currentUserRole.isAdmin}")
        
        def shareUsers = shareRoleService.getShareUserList("salesOrder", "delete")
        def salesOrderInstance = SalesOrder.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
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
        return salesOrderInstance
    }
    
    def delete2(id) {
        def success = false

		SalesOrder.withTransaction{ status ->
			def salesOrderInstance = SalesOrder.get(id.toLong())
			try {
				//delete workflow history
				WorkflowHistory.findAllByObjectNameAndObjectId('salesOrder', id.toLong()).each{it.delete(flush:true)}
				//delete outbound message
				OutBound.findAllByObjectNameAndObjectId('salesOrder', id.toLong()).each{it.delete(flush:true)}
				//delete details
				SalesOrderDetail.findAllBySalesOrder(salesOrderInstance).each { it.delete(flush:true)}
				salesOrderInstance.save(flush: true) 
				salesOrderInstance.delete(flush: true)
				success = true
			}
			catch (DataIntegrityViolationException e) {
				status.setRollbackOnly()
			}        
		}
        return success
    }
    
	def isLocked(id){
    	def result = WorkflowHistory.withCriteria(uniqueResult:true){
            createAlias 'step', 's'
            projections{
                property('s.lockRecord')
            }
            fetchMode "step", FM.JOIN
            eq("objectId", id.toLong())
            eq("objectName", 'salesOrder')
            order("id", "desc")
            maxResults(1)
		}
		log.info("isLocked=${id}, ${result}")
        return result
	}
}
