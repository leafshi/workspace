package org.leaf.eos2.news

import org.leaf.eos2.shiro.User
import org.leaf.eos2.shiro.Role
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod
import org.apache.shiro.SecurityUtils
import org.leaf.eos2.b2b.UtilityService

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM
import org.hibernate.criterion.CriteriaSpecification

class EntityService {

	static transactional = true
	
	def utilityService
	
    def create() {
        def entityInstance = new Entity()

        def currentUserId = utilityService.currentUserId()

        entityInstance.level = "普通"
	    //dynamic method
	    if(currentUserId){
	         BindDynamicMethod mybind = new BindDynamicMethod()
	         def mymap = ['createdBy.id' : currentUserId, 'lastModifiedBy.id' : currentUserId]
	         def myargs =  [entityInstance, mymap]
	         mybind.invoke(entityInstance, 'bind', (Object[]) myargs)
	    }
	    
	    User.listOrderByUsername().each{ userInstance ->
	    	entityInstance.addToReaders(new Reader(reader : userInstance, visible :false));
	    }
	    
	    return entityInstance
    }
    
    def edit(Entity entityInstance){
        def currentUserId = utilityService.currentUserId()

	    //dynamic method
	    if(currentUserId){
	         BindDynamicMethod mybind = new BindDynamicMethod()
	         def mymap = ['lastModifiedBy.id' : currentUserId]
	         def myargs =  [entityInstance, mymap]
	         mybind.invoke(entityInstance, 'bind', (Object[]) myargs)
	    }
	    return entityInstance
    }
    
    def update(Entity entityInstance, Object params){
        def success = false

		Entity.withTransaction{ status ->
			//delete details
			Reader.findAllByEntity(entityInstance).each { it.delete(flush:true)}
			entityInstance.save(flush:true)
			
            entityInstance.properties = params
            entityInstance.readers.each{reader ->
            	reader.visible = (reader.visible == true) ? true:false;
            }
            
			if(entityInstance.validate() && entityInstance.save(flush:true)){
				success = true
			}else{
				status.setRollbackOnly()
			}
		}
        return success
    }
    
    @Transactional(readOnly = true)
    def list(Object params) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        //get user role
        def currentUserRole = Role.get(currentUser.role.id)
        
        def entityIdList = Reader.withCriteria{
        
        	createAlias 'entity', 'entity', CriteriaSpecification.LEFT_JOIN
        	
        	projections{
				groupProperty("entity.id")
			}
			
        	fetchMode "entity", FM.EAGER
        	
        	if(currentUserRole.isAdmin == false) {
        		eq('reader.id', currentUser.id)
        		eq('visible', true)
        	}
        	
		}
		
		def entityInstanceList = Entity.withCriteria{
		
			if(params?.max) maxResults(params.int('max'))
			if(params?.offset) firstResult(params.int('offset'))
			if(params?.sort && params?.order) order(params?.sort, params?.order)
			inList("id", entityIdList)			
		}
		
		return entityInstanceList;
    }
    
    @Transactional(readOnly = true)
    def count(Object params) {
    
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        //get user role
        def currentUserRole = Role.get(currentUser.role.id)
        
        def entityIdList = Reader.withCriteria{
        
        	createAlias 'entity', 'entity', CriteriaSpecification.LEFT_JOIN
        	
        	projections{
				groupProperty("entity.id")
			}
			
        	fetchMode "entity", FM.EAGER
        	
        	if(currentUserRole.isAdmin == false) {
        		eq('reader.id', currentUser.id)
        		eq('visible', true)
        	}
        	
		}
		log.info("entityIdList=${entityIdList}");
		if (entityIdList == null)
			return 0
		else
        	return entityIdList.size();
    }
    
    @Transactional(readOnly = true)
    def show(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        //user role
        def currentUserRole = Role.get(currentUser.role.id)
        
        def entityIdList = Reader.withCriteria{
        
        	createAlias 'entity', 'entity', CriteriaSpecification.LEFT_JOIN
        	
        	projections{
				groupProperty("entity.id")
			}
			
        	fetchMode "entity", FM.EAGER
        	
        	if(currentUserRole.isAdmin == false) {
        		eq('reader.id', currentUser.id)
        		eq('visible', true)
        	}
        	eq("entity.id", id.toLong())
		}
		log.info("entityIdList=${entityIdList}")
		if (entityIdList == null || entityIdList == [])
			return null
		else
        	return Entity.get(id)
    }
}
