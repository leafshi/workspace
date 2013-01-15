package org.leaf.eos2.wf

class WorkflowAction {

    WorkflowStep belognsToStep
    String serialNumber
	WorkflowStep nextStep
    String name
    Boolean needDescription
    
    static belognsTo = [belognsToStep: WorkflowStep]

    static constraints = {
        serialNumber(nullable:false, blank:false, maxSize:2)
        name(nullable:false, blank:false, maxSize:20)
        belognsToStep(nullable:false)
		nextStep(nullable:true)
		needDescription(nullable:false)
    }
    
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'WF_ACTION'
    }
}
