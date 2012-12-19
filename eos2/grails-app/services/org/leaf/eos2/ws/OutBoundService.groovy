package org.leaf.eos2.ws

import org.leaf.eos2.admin.ShareRoleService
import org.leaf.eos2.shiro.User

import org.springframework.transaction.annotation.*
import org.apache.shiro.SecurityUtils

class OutBoundService {

    static transactional = true

    def shareRoleService
    def grailsApplication

    def generateRequestService

    //获取出站消息列表
    
    @Transactional(readOnly = true)
    def list(Object params) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        
        def shareUsers = shareRoleService.getShareUserList("outBound", "list")

        def outBoundInstanceList = OutBound.withCriteria{
            //返回条数
            if(params?.max) maxResults(params.int('max'))
            //偏移量
			if(params?.offset) firstResult(params.int('offset'))
            //排序
			if(params?.sort && params?.order) order(params?.sort, params?.order)
            //stage
            if(params?.stage) eq("stage", params?.stage)
            if(currentUser.username != 'admin') {
    	        or{
                    inList("owner.id", shareUsers)
    			    eq("owner", currentUser)
    		    }
            }
        }
        return outBoundInstanceList
    }
    
    @Transactional(readOnly = true)
    def show(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        
        def shareUsers = shareRoleService.getShareUserList("outBound", "show")

        def outBoundInstance = OutBound.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUser.username != 'admin') {
    	        or{
                    inList("owner.id", shareUsers)
    			    eq("owner", currentUser)
    		    }
            }
        }
        return outBoundInstance
    }

    def edit(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        
        def shareUsers = shareRoleService.getShareUserList("outBound", "edit")

        def outBoundInstance = OutBound.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUser.username != 'admin') {
    	        or{
                    inList("owner.id", shareUsers)
    			    eq("owner", currentUser)
    		    }
            }
        }
        return outBoundInstance
    }

    def delete(id) {
        //get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        
        def shareUsers = shareRoleService.getShareUserList("outBound", "delete")

        def outBoundInstance = OutBound.withCriteria(uniqueResult:true){
            eq("id", id.toLong())
            if(currentUser.username != 'admin') {
    	        or{
                    inList("owner.id", shareUsers)
    			    eq("owner", currentUser)
    		    }
            }
        }
        return outBoundInstance
    }
    

    //获取出站消息配置
    @Transactional(readOnly = true)
    def getConfig(objectName, objectId, method) {
        def obConfig = ObConfig.withCriteria(uniqueResult:true){
            projections{
                property("defaultSendErrorLimit")
                property("defaultGetErrorLimit")
                property("priority")
                property("asynchronous")
            }
            eq("objectName", objectName)
            eq("method", method)
            //eq("asynchronous", asynchronous)
            maxResults(1)
        }

        if(obConfig == null){
            obConfig = []
            obConfig.add(3)
            obConfig.add(3)
            obConfig.add(5)
            obConfig.add(false)
        }
 
        def fmapResult = [:]
            fmapResult.put("defaultSendErrorLimit", obConfig[0])
            fmapResult.put("defaultGetErrorLimit", obConfig[1])
            fmapResult.put("priority", obConfig[2])
            fmapResult.put("asynchronous", obConfig[3])


        return fmapResult
    }
    //获取当前用户
    @Transactional(readOnly = true)
    def getCurrentUser (){
		def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        return currentUser
    }
    //复活单个出站消息, limit + 1
    def resurrect (Long outBoundId){
        def outBoundInstance = this.edit(outBoundId)

        if(outBoundInstance == null){
            return
        }

        def config = getConfig(outBoundInstance.objectName, outBoundInstance.objectId, outBoundInstance.method)

        switch(outBoundInstance.stage){
            case '待激活（发送）':
                outBoundInstance.retrySendErrorLimit += config?.get("defaultSendErrorLimit") ?: 1
                outBoundInstance.save(flush:true)
                break
            case '待激活（处理）':
                outBoundInstance.retryGetErrorLimit += config?.get("defaultGetErrorLimit") ?: 1
                outBoundInstance.save(flush:true)
                break
        }
        
    }
    //初始化单个出站消息
    def reinit (Long outBoundId){
        def outBoundInstance = this.edit(outBoundId)

        if(outBoundInstance == null){
            return
        }

        def config = getConfig(outBoundInstance.objectName, outBoundInstance.objectId, outBoundInstance.method)

        def possible = false

        if(outBoundInstance.stage == '已完成'  ){
            if(outBoundInstance.asynchronous == true){//异步
                if(outBoundInstance.JobError != null){
                    possible = true//异步并且队列状态不为0
                }
            }else{//同步
                if(outBoundInstance.status != '0'){
                    possible = true//同步并且状态不为0
                }
            }
        }
            

        if(possible==true){
            outBoundInstance.retrySendError = 0
            outBoundInstance.retryGet = 0
            outBoundInstance.retryGetError = 0
                
            outBoundInstance.retrySendErrorLimit = config?.get("defaultSendErrorLimit") ?: 1
            outBoundInstance.retryGetErrorLimit = config?.get("defaultGetErrorLimit") ?: 1

            outBoundInstance.priority= config?.get("priority") ?: 1
            
            outBoundInstance.status = null//状态
            outBoundInstance.result = null//结果
            outBoundInstance.error = null//错误
            outBoundInstance.jobId = null//队列ID
            outBoundInstance.jobStatus = null//队列状态
            outBoundInstance.jobResult = null//队列结果
            outBoundInstance.jobError = null//队列错误

            outBoundInstance.save(flush:true)
        }  
    }
    //显示出站消息
    @Transactional(readOnly = true)
    def showXml (Long outBoundMessageId){
        //获取出站消息
        def outBoundMessage = OutBound.get(outBoundMessageId)
        def inputXml = generateRequestService."${outBoundMessage.method}"(outBoundMessage.objectId.toLong(), outBoundMessage.asynchronous)
        return inputXml
    }

}
