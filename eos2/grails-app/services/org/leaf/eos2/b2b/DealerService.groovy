package org.leaf.eos2.b2b

import org.leaf.eos2.admin.ShareRole
import org.leaf.eos2.admin.Group
import org.leaf.eos2.shiro.User
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod


class DealerService {

    static transactional = true 

    def initShareRole(dealerInstance){
		log.info("init share role")
		//Dealer.withTransaction{ status ->
			def currentDeptInstance = Department.get((dealerInstance?.department?.id)?:-1L)
			def group_id = this.group_id((currentDeptInstance?.id)?:-1L)
			//共享给所有人所属部门的组
			if(group_id > 0){
				share(dealerInstance.owner.id, group_id, 'dealer')
				share(dealerInstance.owner.id, group_id, 'salesOrder')
				share(dealerInstance.owner.id, group_id, 'contract')
				share(dealerInstance.owner.id, group_id, 'outBound')
				share(dealerInstance.owner.id, group_id, 'creditControl')
			}
			//共享给上级部门
			def parentDept = Department.get((currentDeptInstance?.parentDept?.id)?:-1L)
			while(parentDept){
				def parent_group_id = this.group_id( parentDept.id)
				if(parent_group_id > 0){
					share(dealerInstance.owner.id, parent_group_id, 'dealer')
					share(dealerInstance.owner.id, parent_group_id, 'salesOrder')
					share(dealerInstance.owner.id, parent_group_id, 'contract')
					share(dealerInstance.owner.id, parent_group_id, 'outBound')
					share(dealerInstance.owner.id, parent_group_id, 'creditControl')
				}
				parentDept = Department.get((parentDept?.parentDept?.id)?:-1L)
			}
		//}
    }
    
    def group_id (department_id){
		def group_id = Group.withCriteria(uniqueResult:true){
            projections{
                property('id')
            }
            eq("department.id", department_id)
		}
		return group_id = group_id ?: -1L
    }
    
    
    def share(owner_id, group_id, object_name){
    	log.info("owner[${owner_id}] share object [${object_name}] to group [${group_id}]")
    	
		def shareRoleInstance = new ShareRole(domain : object_name, readable :true, editable:false, deletable:false)
		
		BindDynamicMethod mybind = new BindDynamicMethod()
		def mymap = ['user.id' : owner_id, 'group.id' : group_id]
		def myargs =  [shareRoleInstance, mymap]
		mybind.invoke(shareRoleInstance, 'bind', (Object[]) myargs)
			shareRoleInstance.save(flush:true)
    }
    
    
}