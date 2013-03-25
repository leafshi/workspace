package org.leaf.eos2.b2b

class DepartmentAjaxController {

	def departmentService

    def initWorkflow = {
    	
    	def departmentInstance = Department.get(params.id)
    	if(departmentInstance.type=='B'){
    		departmentService.initWorkflow(departmentInstance)
    	}
    	if(departmentInstance.type=='C'){
    		departmentService.initWorkflow2(departmentInstance)
    	}
    	redirect(controller : 'department', action: "show", id: departmentInstance.id)
    }
}
