package org.leaf.eos2.wf

import org.leaf.eos2.shiro.User

class WorkflowHistory {

	def workflowHistoryService
    
    WorkflowStep step
    String objectName
    Long objectId
    String status
    String description
	User createdBy
	User lastModifiedBy
	Date dateCreated
	Date lastUpdated

    static constraints = {
        step(nullable:false, blank:false)
        objectName(nullable:false, blank:false, maxSize:30)
        objectId(nullable:false, blank:false)
        status(nullable:false, blank:false, inList : ['进行中', '已完成'], maxSize:6)
        description(nullable:true, blank:true, maxSize:255)
		createdBy(nullable:false)
		lastModifiedBy(nullable:false)
    }
    
    String toString() {
        return "$objectId"
    }

	def afterInsert(){
		def step = WorkflowStep.get(this.step.id)
		workflowHistoryService.updateObjectStatus(this.objectName, this.objectId, step.name)
	}
    
    static mapping = {
        table 'WF_HISTORY'
    }

}
