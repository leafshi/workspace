package org.leaf.eos2.ws

class SendScanJobService {

    static transactional = false

    def calloutService

    def scan() {
        
        def outBoundInstance = OutBound.withCriteria(uniqueResult:true){
            projections{
                //取出站消息ID
                property("id")
            }
            eq('stage', '待发送')
            order("priority", "asc")
            order("retrySendError", "asc")
            order("id", "asc")
            maxResults(1)
        }
		if(outBoundInstance){
			calloutService.fire(outBoundInstance.toLong())
		}
    }
}
