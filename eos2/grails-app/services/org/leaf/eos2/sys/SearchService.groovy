package org.leaf.eos2.sys

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM

import org.leaf.eos2.admin.ShareRoleService
import org.apache.shiro.SecurityUtils

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role


import org.leaf.eos2.b2b.Product
import org.leaf.eos2.b2b.Contract
import org.leaf.eos2.b2b.SalesOrder

import org.leaf.eos2.b2b.Dealer

class SearchService {

	def shareRoleService

    @Transactional(readOnly = true)
    def searchProduct(term) {
        return Product.withCriteria{
            or{
                ilike("serialNumber", term + "%")
                ilike("standard", term + "%")
            }
            maxResults(30)
            order("serialNumber", "asc")
        }
    }
    
    @Transactional(readOnly = true)
    def searchContract(term) {
    
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)
        def shareUsers = shareRoleService.getShareUserList("contract", "list")

        return Contract.withCriteria{
			ilike("serialNumber", term + "%")
            if(currentUserRole.isAdmin == false) {
    			or{
					inList("owner.id", shareUsers)
    				eq("owner", currentUser)
    			}
            }
            maxResults(30)
            order("serialNumber", "asc")
        }
    } 
    
    @Transactional(readOnly = true)
    def searchSalesOrder(term) {
    
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        def currentUserRole = Role.get(currentUser.role.id)

        def shareUsers = shareRoleService.getShareUserList("salesOrder", "list")

        return SalesOrder.withCriteria{
            or{
                ilike("serialNumber", term + "%")
                ilike("erpSerialNumber", term + "%")
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

            maxResults(30)
            order("serialNumber", "asc")
        }
    } 
    
    //搜索用户
    @Transactional(readOnly = true)
    def searchUser(term) {
        return User.withCriteria{
			ilike("username", term + "%")
            maxResults(30)
            order("username", "asc")
        }
    } 
    
    //搜索用户
    @Transactional(readOnly = true)
    def searchDealer(term) {
        return Dealer.withCriteria{
			ilike("serialNumber", term + "%")
            maxResults(30)
            order("serialNumber", "asc")
        }
    } 
    
}