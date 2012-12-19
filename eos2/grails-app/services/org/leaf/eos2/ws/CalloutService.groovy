package org.leaf.eos2.ws

import cxf.client.dcms.YiFeiGatewayService

class CalloutService {

    static transactional = false 

    //定义Webservice
    def yiFeiGatewayService

    def generateRequestService
    def decipherResponseService

    //callout
    def fire (Long outBoundMessageId) {
        
            //获取出站消息
            def outBoundMessage = OutBound.get(outBoundMessageId)
            //出站消息ID不存在，退出
            if(!outBoundMessage){
                return;
            }
            try{
               //异步？
                def asynchronous = outBoundMessage.asynchronous
                //获取出站XML
                def inputXml = getInputXml(outBoundMessage.method, outBoundMessage.asynchronous, outBoundMessage.objectId)
                //log.info("inputXml=${inputXml}")
                //callout
                def outputXml = start(inputXml)
                //log.info "CalloutService.fire, ${outBoundMessageId}=${outputXml}"
                //end, update outbound message status
                if(asynchronous == true){//异步
                    def result = decipherAsynchronousOutputXml(outputXml)
                    outBoundMessage.error = result.get('error')
                    outBoundMessage.status = result.get('status')
                    outBoundMessage.jobId = result.get('jobId')
                }else{//同步
                    def result = decipherOutputXml(outBoundMessage.objectId, outBoundMessage.method, outputXml)
                    outBoundMessage.error = result.get('Error')
                    outBoundMessage.status = result.get('Status')
                }
            }catch(e){
                outBoundMessage.result = e.toString()
                outBoundMessage.retrySendError = (outBoundMessage.retrySendError?:0) + 1
            }
            outBoundMessage.save(flush:true)
        
    }
    //获取出站消息
    def getInputXml(method, asynchronous, objectId){
        def inputXml = generateRequestService."${method}"(objectId.toLong(), asynchronous)
        return inputXml
    }
    //出站
    def start(inputXml){
        def response = yiFeiGatewayService.yifeiGateway(inputXml)
        return response
    }
    //解析异步结果
    def decipherAsynchronousOutputXml(outputXml) {
        outputXml = outputXml.substring(outputXml.indexOf('<STD_OUT'), outputXml.size())
        //outputXml = yifeiEncodeService.decode(outputXml)
        def std_out = new XmlSlurper().parseText(outputXml)
        def result = [:]
        std_out.Service.each{
            result.put("error","${it.Error.text()}")
            result.put("status","${it.Status.text()}")
            result.put("jobId","${it.JobID.text()}")
        }
        return result
    }
    //解析同步结果
    def decipherOutputXml(objectId, method, outputXml) {
        def result = decipherResponseService."${method}"(objectId, outputXml, false)
        return result
    }
}
