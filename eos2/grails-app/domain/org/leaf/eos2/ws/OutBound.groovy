package org.leaf.eos2.ws

import org.leaf.eos2.shiro.User


class OutBound {

    def outBoundService

    String objectName//对象
    Long objectId//对象ID
    String method//方法
    boolean asynchronous//是否采取异步的方式
    Integer priority//优先级    
    String status//状态
    String result//结果
    String error//错误
    String jobId//队列ID
    String jobStatus//队列状态
    String jobResult//队列结果
    String jobError//队列错误
    Integer retrySendError//重试发送次数
    Integer retrySendErrorLimit//重试发送次数限制
    Integer retryGet//重试取结果次数
    Integer retryGetError//重试取结果失败次数
    Integer retryGetErrorLimit//重试取结果失败次数限制
    String stage//最终状态
    Date dateCreated//创建日期
    Date lastUpdated//修改日期
    User owner//所有人
    User createdBy//创建人
    User lastModifiedBy//上次修改人

    static constraints = {
        objectName(nullable:false, blank:false, maxSize : 60)
        objectId(nullable:false, unique : 'objectName')
        method(nullable:false, blank:false, maxSize : 100)
        asynchronous(nullable:true)
        priority(nullable:true, min : 0, max : 20)
        status(nullable:true, blank:true, maxSize:20)
        result(nullable:true, blank:true,maxSize:255)
        error(nullable:true, blank:true, maxSize:255)
        jobId(nullable:true, blank:true, maxSize:64)
        jobStatus(nullable:true, blank:true, maxSize:20)
        jobResult(nullable:true, blank:true,maxSize:255)
        jobError(nullable:true, blank:true, maxSize:255)
        retrySendError(nullable:true, scale:0, max : 255)
        retrySendErrorLimit(nullable:true, scale:0, max : 255)
        retryGet(nullable:true, scale:0, max : 255)
        retryGetError(nullable:true, scale:0, max : 255)
        retryGetErrorLimit(nullable:true, scale:0, max : 255)
        stage(nullable:true, blank:true, maxSize : 20)
    }

    def beforeInsert() {
        def config = outBoundService.getConfig(objectName, objectId, method)
        
        retrySendError = 0
        retryGet = 0
        retryGetError = 0

        retrySendErrorLimit = config.get("defaultSendErrorLimit")
        retryGetErrorLimit = config.get("defaultGetErrorLimit")
        
        priority = config.get("priority")
        asynchronous = config.get("asynchronous")
        
        stage = '待发送'
   }

    def beforeUpdate(){
    /*
待发送-未达到限制-未取到JOBID，且未达到限制
待处理-未达到限掉-已取到JOBID，状态不等于2:已完成
待激活（发送）-已经达到限制，未取到JOBID，已达到限制
待激活（处理）-已经达到限制，已取到JOBID，状态不等于2:已完成

已完成

发送完成把ERP单号传回来
    */
        if(asynchronous == true){//异步方式
            if(jobId == null){//jobid == null
                if(retrySendError < retrySendErrorLimit){
                    stage = '待发送'
                }else{
                    stage = '待激活（发送）'
                }
            }else{//jobid != null
                if(jobStatus =='2:处理完'){
                    stage = '已完成'
                }else{
                    if(retryGetError < retryGetErrorLimit){
                        stage = '待处理'
                    }else{
                        stage = '待激活（处理）'
                    }
                }
            }
        }else{//同步方式
            if(status != null){
                stage = '已完成'
            }else{
                if(retrySendError < retrySendErrorLimit){
                    stage = '待发送'
                }else{
                    stage = '待激活（发送）'
                }
            }
        }
         
    }

    String toString() {
        return "$id"
    }

    static mapping = {
        table 'WEBSERVICE_OUTBOUND'
    }
}
