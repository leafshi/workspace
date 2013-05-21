package org.leaf.eos2.shiro

class UserExtendController {

	def userExtendService

	//启用
    def activeUser() { 
    	if(userExtendService.activeUser(params.int('id').toLong())){
			flash.message = "user.activeUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} active successed"
    	}else{
			flash.message = "user.activeUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} active failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
    
    //停用
    def deactivateUser(){
    	if(userExtendService.deactivateUser(params.int('id').toLong())){
			flash.message = "user.deactivateUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} deactivated successed"
    	}else{
			flash.message = "user.deactivateUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} deactivated failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
    
    //注册ECS
    def registerECSUser(){
    	if(userExtendService.registerECSUser(params.int('id').toLong())){
			flash.message = "user.registerECSUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} registerECSUser successed"
    	}else{
			flash.message = "user.registerECSUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} registerECSUser failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
    
    def modifyECSUser(){
    	if(userExtendService.modifyECSUser(params.int('id').toLong())){
			flash.message = "user.modifyECSUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} modifyECSUser successed"
    	}else{
			flash.message = "user.modifyECSUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} modifyECSUser failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
    
    //启用ECS
    def activateECSUser(){
    	if(userExtendService.activateECSUser(params.int('id').toLong())){
			flash.message = "user.activateECSUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} activateECSUser successed"
    	}else{
			flash.message = "user.activateECSUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} activateECSUser failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
    
    //停用ECS
    def deactivateECSUser(){
    	if(userExtendService.deactivateECSUser(params.int('id').toLong())){
			flash.message = "user.deactivateECSUser.successed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} deactivateECSUser successed"
    	}else{
			flash.message = "user.deactivateECSUser.failed"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} deactivateECSUser failed"
    	}
		redirect(controller : "user", action: "show", id: params.int('id').toLong())
    }
}
