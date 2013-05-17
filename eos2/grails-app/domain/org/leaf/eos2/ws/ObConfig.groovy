package org.leaf.eos2.ws

class ObConfig {

    String objectName//对象名称
    String method//方法
    Boolean asynchronous//是否异步？
    Integer priority//优先级
    Integer defaultSendErrorLimit//发送次数
    Integer defaultGetErrorLimit//接收次数
    
    String assessor//ERP审核员
    Boolean autoApprove//自动审核
    
    Date dateCreated//创建日期
    Date lastUpdated//修改日期

    static constraints = {
        objectName(nullable:false, blank:false, maxSize : 60)
        method(nullable:false, blank:false, maxSize : 100, unique : ['objectName', 'asynchronous'])
        asynchronous(nullable:true)
        priority(nullable:false, min : 0, max : 20)
        defaultSendErrorLimit(nullable:true, scale:0, max : 255)
        defaultGetErrorLimit(nullable:true, scale:0, max : 255)
        
        assessor(nullable:false, blank:false, maxSize : 10)
        autoApprove(nullable:false)
    }

    String toString() {
        return "$id"
    }

    static mapping = {
        table 'WEBSERVICE_OUTBOUND_CONFIG'
    }    

}
