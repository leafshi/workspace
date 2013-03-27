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
}
