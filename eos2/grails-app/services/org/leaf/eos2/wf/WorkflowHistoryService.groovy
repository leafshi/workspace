package org.leaf.eos2.wf

class WorkflowHistoryService {

    static transactional = false 

	def grailsApplication

    def updateObjectStatus(objectName, objectId, stepName) {
		Class clazz = grailsApplication.domainClasses.find{
			it.clazz.simpleName == objectName.capitalize()
		}.clazz
		
		clazz.withNewSession{
			def objectInstance = clazz.get(objectId)
				objectInstance.status = stepName
				objectInstance.save(flush:true);
		}
    }
}
