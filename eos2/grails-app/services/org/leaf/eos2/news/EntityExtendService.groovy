package org.leaf.eos2.news

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM
import org.hibernate.criterion.CriteriaSpecification

import org.leaf.eos2.shiro.User
import org.leaf.eos2.b2b.Department
import org.leaf.eos2.b2b.Dealer

class EntityExtendService {

    @Transactional(readOnly = true)
    def userDepartmentName(userId) {
        log.info("get department name for user ${userId}")
        def departmentName = User.withCriteria(uniqueResult:true){
        
        	createAlias 'department', 'department', CriteriaSpecification.LEFT_JOIN
        	
        	projections{
				groupProperty("department.name")
			}
			
        	fetchMode "department", FM.EAGER
        	
        	eq("id", userId.toLong())
		}
		
		departmentName = departmentName?:""
		
		return departmentName;
    }
    
    @Transactional(readOnly = true)
    def userDealerName(userId) {
        log.info("get dealer name for user ${userId}")
        
        def dealerName = Dealer.withCriteria(uniqueResult:true){
        
        	createAlias 'owner', 'owner', CriteriaSpecification.LEFT_JOIN
        	
        	projections{
				groupProperty("name")
			}
			
        	fetchMode "owner", FM.EAGER
        	
        	eq("owner.id", userId.toLong())
		}
		
		dealerName = dealerName?:""
		
		return dealerName;
    }
    
    @Transactional(readOnly = true)
    def searchUser(term) {
        
        def userList = User.withCriteria{
        	
        	projections{
        		property("id")
				property("username")
				property("username")
				property("username")
			}
			ilike("username", term + "%")
            maxResults(10)
            order("username", "asc")
		}
		
		userList.each{ user ->
			log.info("user is ${user}")
			user.putAt(2, "[经销商-${userDealerName(user[0])}]")
			user.putAt(3, "[部门-${userDepartmentName(user[0])}]")
		}
		
				
		return userList;
    }
    
}
