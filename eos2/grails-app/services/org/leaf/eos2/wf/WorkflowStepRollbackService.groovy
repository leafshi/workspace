package org.leaf.eos2.wf

import org.springframework.transaction.annotation.*
import org.hibernate.FetchMode as FM
import org.hibernate.criterion.CriteriaSpecification
import org.leaf.eos2.b2b.UtilityService

import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod



class WorkflowStepRollbackService {

    static transactional = false
    
	def grailsApplication
	def utilityService
    
    private def object_name
    private def object_id
    private def history_id
    /*
 	def init1(java.lang.String objectName, java.lang.Long objectId, java.lang.Long historyId){
 		log.info("init workflow step rollback service!");
    	this.object_name = objectName
    	this.object_id = objectId
    	this.history_id = historyId
    }
	*/
    def rollback(objectName, objectId, historyId){
    //def rollback(){
    	this.object_name = objectName
    	this.object_id = objectId
    	this.history_id = historyId

    	WorkflowHistory.withTransaction{ status ->
    		try{
    			//如果当前步骤不是第一步
    			if(currentHistoryStepIsBegin()){
					log.error("rollback workflow history failed! current step is the first step, can not rollback.");
    				return;
    			}
    			
    			if(existLagerHistoryId()){
					log.error("rollback workflow history failed! exist newer histroy.");
    				return;
    			}
				//删除当前步骤
				this.deleteCurrentHistory();
				//激活上一步
				def lastHistoryId = this.activeLastHistory();
				//获取上一步名称
				def stepName = this.lastHistoryStepName(lastHistoryId)
				//更新状态
				updateObjectStatus(stepName)
				log.info("rollback workflow history success!");
    		}catch(e){
    			log.error("rollback workflow history failed! reason is : ${e}");
    			status.setRollbackOnly();
    		}
    		
    	}

    }
    //判断当前历史是否是第一步
    private def currentHistoryStepIsBegin(){
    	def current_history_isBegin = WorkflowHistory.withCriteria(uniqueResult:true){
    		createAlias 'step', 'step'
    		projections{
                property("step.isBegin")
            }
    		fetchMode "step", FM.JOIN

    		idEq(this.history_id)
    	}
    	
    	current_history_isBegin = current_history_isBegin ?: false
    	
    	return current_history_isBegin;
    }
    //判断当前历史是否是最后的一步审批
    private def existLagerHistoryId(){
    	def maxHistoryId = WorkflowHistory.withCriteria(uniqueResult:true){
    		projections{
                max("id")
            }
    		eq("objectName", this.object_name)
    		eq("objectId", this.object_id)
    		//gt("id", this.history_id)
    		//order("id", "desc")
    	}
    	//如果最大ID比当前的ID大，则不回滚
    	if(maxHistoryId > this.history_id){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    //删除当前步骤历史
    private def deleteCurrentHistory(){
		def workflowHistoryInstance = WorkflowHistory.get(this.history_id);
			workflowHistoryInstance.delete(flush:true);
    }
    //激活上一步骤历史
    private def activeLastHistory(){
    
    	def lastHistoryInstance = WorkflowHistory.withCriteria(uniqueResult:true){
    		eq("objectName", this.object_name)
    		eq("objectId", this.object_id)
    		lt("id", this.history_id)
    		order("id", "desc")
    		maxResults(1)
    	}
    	//修改状态
    	lastHistoryInstance.status = "进行中"
    	//最后修改人
    	
    	def currentUserId = utilityService.currentUserId()
		BindDynamicMethod mybind = new BindDynamicMethod()
		def mymap = ['lastModifiedBy.id' : currentUserId]
		def myargs =  [lastHistoryInstance, mymap]
		mybind.invoke(lastHistoryInstance, 'bind', (Object[]) myargs)
		//更新
		lastHistoryInstance.save(flush:true);
		
		return lastHistoryInstance.id
    }
    
    //获取上一步骤历史步骤名称
    private def lastHistoryStepName(last_workflow_history_id){
    	def last_history_step_name = WorkflowHistory.withCriteria(uniqueResult:true){
    		createAlias 'step', 'step'
    		projections{
                property("step.name")
            }
    		fetchMode "step", FM.JOIN

    		idEq(last_workflow_history_id)
    	}
    	
    	last_history_step_name = last_history_step_name ?: ""
    	
    	return last_history_step_name;
    }
    
    //更新对象状态
    private def updateObjectStatus(status) {
		Class clazz = grailsApplication.domainClasses.find{
			it.clazz.simpleName == this.object_name.capitalize()
		}.clazz
		
		def objectInstance = clazz.get(this.object_id)
			objectInstance.status = status
			objectInstance.save(flush:true);
    }
    

}
