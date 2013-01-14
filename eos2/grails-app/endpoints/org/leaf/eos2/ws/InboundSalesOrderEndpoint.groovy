package org.leaf.eos2.ws

import org.grails.cxf.utils.EndpointType

class InboundSalesOrderEndpoint {

    static expose = EndpointType.SIMPLE
    
    static excludes=[]

    static fmapResult=[
          '0' : '审批成功'
        , '-1': '不是有效的订单号'
        , '-2': '不能认别的动作'
        , '-3': '未找到审批历史'
        , '-4': '您不是当前的任务分配人'
        , '-5': '另外一个用户更新了审批历史，请重试'
        , '-6': '结束当前审批历史失败'
        , '-7': '创建审批历史失败'
        , '-8': '更新审批日期失败'
        , '-9': '审批日期不能为NULL'
        , '-10': 'web单号与ERP单号不匹配'
    ]
    
    def approvalSalesOrderFromERPService

    //商务部订单审批
    String approval(String id, String serialNumber, String action, String date){
    	def code = approvalSalesOrderFromERPService.approval(id, serialNumber, action, date)
        def outputXml = """
            <STD_OUT Origin="Leaf">
              <Service Name="SetData">
                <Status>${code}</Status>
                <Error>${fmapResult.get(code)}</Error>
                <Data>
                  <DataSet Field="">
                    <Row Data="" />
                  </DataSet>
                </Data>
              </Service>
            </STD_OUT>
        """
        log.info("outputXml=${outputXml}")
        return outputXml
    }

}

