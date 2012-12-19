package org.leaf.eos2.wf

import org.apache.shiro.SecurityUtils
import org.leaf.eos2.shiro.User

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.b2b.Department

class WorkflowDispatcherService {

    static transactional = true

    def checkWorkFlow(objectName, objectId, recordTypeId, departmentId) {
		
        def firstStep = getFirstStep(objectName, recordTypeId, departmentId)
        if(firstStep != null) {
	        //get user
	        def currentUser = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
            def history = new WorkflowHistory(
				  step : firstStep
				, objectName : objectName
				, objectId : objectId
				, status : '进行中'
				, description : ''
				, createdBy : currentUser
				, lastModifiedBy : currentUser
				, dateCreated : new Date()
				, lastUpdated : new Date()
			).save(flush:true)
        }
    }

	def getFirstStep(objectName, recordTypeId, departmentId){
		def recordType = RecordType.get(recordTypeId)
		def department = Department.get(departmentId)
		def workflow = Workflow.findWhere(domain : objectName, recordType : recordType, department : department, isActive : true)
		def firstStep = null
		if(workflow != null){
			firstStep = WorkflowStep.findWhere(workflow : workflow, isBegin : true)
		}
		return firstStep
	}
}
