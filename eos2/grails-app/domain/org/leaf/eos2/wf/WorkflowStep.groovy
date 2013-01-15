package org.leaf.eos2.wf

import org.leaf.eos2.admin.Group

class WorkflowStep {

    Workflow workflow
    String serialNumber
    String name
	Group assignee
	Boolean lockRecord
	Boolean isBegin
	Boolean isEnd
    
    static belognsTo = [workflow: Workflow]
    static hasMany = [actions : WorkflowAction]
    static mappedBy = [actions:"belognsToStep"]
    

    static constraints = {
        workflow(nullable:false, blank:false)
        serialNumber(nullable:false, blank:false, maxSize:2, unique : 'workflow')
        name(nullable:false, blank:false, maxSize:20)
		assignee(nullable:true)
		lockRecord(nullable:false)
		isBegin(nullable:false)
		isEnd(nullable:false)
    }
        
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'WF_STEP'
        actions sort:'serialNumber', order:'asc'
    }

}
