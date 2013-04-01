package org.leaf.eos2.news

import grails.converters.JSON

class EntityExtendController {

	def entityExtendService
	def readerService
	
    def searchUser = {
        render entityExtendService.searchUser(params.term) as JSON
    }
	
    def userDepartmentName = { 
    	log.info("${params.int('id')}")
    	def departmentName = entityExtendService.userDepartmentName(params.int('id'))
    	render "${departmentName}"
    }
    
    def userDealerName = { 
    	def dealerName = entityExtendService.userDealerName(params.int('id'))
    	render "${dealerName}"
    }
    
    def delete = {
    	log.info("delete reader, id is : ${params.id}")
        def readerInstance = Reader.get(params.id)
        if (readerInstance) {
			def entityId = readerInstance.entity.id
			if(readerService.delete(readerInstance)){
                flash.message = "reader.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Reader ${params.id} deleted"
                redirect(controller:"entity", action: "show", id: entityId)
            }else{
                flash.message = "reader.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Reader ${params.id} could not be deleted"
                redirect(controller:"entity", action: "show", id: entityId)
            }
        }
        else {
            flash.message = "reader.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Reader not found with id ${params.id}"
            redirect(controller:"entity", action: "list")
        }
    }

    
}
