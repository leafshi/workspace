package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.apache.shiro.SecurityUtils
import org.hibernate.FetchMode as FM

import org.springframework.transaction.annotation.*

class UtilityService {

	@Transactional(readOnly = true)
	def currentUserId() {
    	def userId = User.withCriteria(uniqueResult : true){
	    	projections{
	        	property('id')
	        }
	        eq("username", SecurityUtils.getSubject().getPrincipal())
	        maxResults(1)
		}
		return userId
    }
}
