package cxf.client.dcms

import org.yifeigateway.dcms.YiFeiGateWayServiceSoap

class YiFeiGatewayService {

    static transactional = true

    YiFeiGateWayServiceSoap yiFeiGatewayClient

    def yifeiGateway (inputXml) {
        def resp1
        try{
            resp1 = yiFeiGatewayClient.yiFeiGateway(inputXml)
        }catch(e){
        }
        return resp1
    }
}
