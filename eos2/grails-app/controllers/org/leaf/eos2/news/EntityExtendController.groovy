package org.leaf.eos2.news

class EntityExtendController {

	def entityExtendService
	
    def userDepartmentName = { 
    	log.info("${params.int('id')}")
    	def departmentName = entityExtendService.userDepartmentName(params.int('id'))
    	render "${departmentName}"
    }
    
    def userDealerName = { 
    	def dealerName = entityExtendService.userDealerName(params.int('id'))
    	render "${dealerName}"
    }
}
