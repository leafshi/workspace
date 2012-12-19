package org.leaf.eos2.ws

import org.leaf.eos2.snapshot.CreditControl
import org.leaf.eos2.b2b.Dealer
import org.leaf.eos2.b2b.SalesOrder

class DecipherResponseService {

    static transactional = false

    def yifeiEncodeService

    def transferSalesOrder(Long objectId, String outputXml, boolean asynchronous) {

        def method = "${(asynchronous == true)?'decipherAsynchronousResponseXml':'decipherResponseXml'}"

        def result = this."${method}"(outputXml)

        def hasSerialNumber = false

        if(asynchronous == true){
            if(result.get("JobError") == ""){
                hasSerialNumber = true
            }
        }else{
            if(result.get("Status") == "0"){
                hasSerialNumber = true
            }
        }

        if(hasSerialNumber == true){
            def fmapData = result.remove("Data")
                
            def serialNumber = fmapData.get('单号')
                
            def salesOrder = SalesOrder.get(objectId)

                salesOrder.serialNumber = serialNumber

                salesOrder.save(flush:true)
        }

        return result
    }

    def creditControl(Long objectId, String outputXml, boolean asynchronous){
        def method = "${(asynchronous == true)?'decipherAsynchronousResponseXml':'decipherResponseXml'}"

        def result = this."${method}"(outputXml)

        if(result.get("Status") == "0"){//返回了结果
            def fmapData = result.remove("Data")
            def dealer = Dealer.get(objectId)
            new CreditControl(
                dealer : dealer
                , orderAmount : fmapData.get("COP_TD_SUM")      //订货金额
                , openingSell : fmapData.get("COP_TG_SUM")      //未结帐销货
                , receivableAmout : fmapData.get("ACR_TA_SUM")  //应收帐款
                , receivableNote : fmapData.get("NOT_TC_SUM")   //应收票据
                , receivable : fmapData.get("Sum")              //应收合计
                , credit : fmapData.get("COP_MA033")            //信用额度
                , creditExceed : fmapData.get("COP_MA034")      //信用可超出额度
                , creditBalance : fmapData.get("Last")          //信用余额
                , owner : dealer.owner                          //所有人
                , createdBy : dealer.owner                      //创建人
                , lastModifiedBy : dealer.owner                 //上次修改人
            ).save(flush:true)
        }
        return result
    }

    def decipherResponseXml(outputXml){
        outputXml = outputXml.substring(outputXml.indexOf('<STD_OUT'), outputXml.size())
        def std_out = new XmlSlurper().parseText(outputXml)
        def result = [:]
        

        std_out.Service.each{
            //status
            result.put("Status", "${it?.Status.text()}")
            //error
            it?.Error?.each{
                it?.Row?.each{
                    result.put("Error", "[Table:${it.@Table}, Field:${it.@Field}, Data:${it.@Data}, Code:${it.@Code}, Desc:${it.@Desc}]")
                }
                if(!result.get("Error")){
                    result.put("Error", "${it.text()}")
                }
            }
            //data
            it?.Data?.each{ data ->
                data?.DataSet?.each{ dataset ->
                    dataset?.Row?.each{ row ->
                        def fmapData = [:]
                        def flstLabel = "${dataset.@Field}".tokenize('|')
                        def flstValue = "${row.@Data}".tokenize('|')
                        for(i in 0..<flstLabel.size() ){
                            fmapData.put(yifeiEncodeService.decode(flstLabel[i]), yifeiEncodeService.decode(flstValue[i]))
                        }
                        result.put("Data", fmapData)
                    }
                }
            }
        } 
        return result
    }

    def decipherAsynchronousResponseXml(outputXml){
        outputXml = outputXml.substring(outputXml.indexOf('<STD_OUT'), outputXml.size())
        def std_out = new XmlSlurper().parseText(outputXml)
        def result = [:]
        std_out.Service.each{ 
            result.put("JobStatus", "${it.JobStatus.text()}")
            def jobResultXml = it.JobResult.text()
            if(jobResultXml.length() > 0){
                jobResultXml = yifeiEncodeService.decode(jobResultXml)
                jobResultXml = jobResultXml.substring(jobResultXml.indexOf('<STD_OUT'), jobResultXml.size())
                def job_std_out = new XmlSlurper().parseText(jobResultXml)
                job_std_out.Service.each{
                    it?.Error?.each{
                        def fmapError = [:]
                        it?.Row?.each{
                            fmapError.put("Table", "${it.@Table}")
                            fmapError.put("Field", "${it.@Field}")
                            fmapError.put("Code", "${it.@Code}")
                            fmapError.put("Desc", "${it.@Desc}")
                        }
                        if(!fmapError.get("Desc")){
                            fmapError.put("Desc", "${it.text()}")
                        }
                        result.put("JobError", fmapError)
                    }
                    it?.Data?.each{ data ->
                        data?.DataSet?.each{ dataset ->
                            dataset?.Row?.each{ row ->
                                def fmapData = [:]
                                    fmapData.put("Field", "${dataset.@Field}")
                                    fmapData.put("Data", "${dataset.@Data}")
                                result.put("JobResult", fmapData)
                            }
                        }
                    }
                }
            }else{
                result.put("JobError", null)
                result.put("JobResult", null)
            }
        }
        return result
    }
}
