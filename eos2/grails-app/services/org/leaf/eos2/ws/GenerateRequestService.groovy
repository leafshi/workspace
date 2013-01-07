package org.leaf.eos2.ws

import org.hibernate.FetchMode as FM
import grails.converters.JSON
import org.hibernate.criterion.CriteriaSpecification

import org.leaf.eos2.b2b.SalesOrder
import org.leaf.eos2.b2b.SalesOrderDetail
import org.leaf.eos2.b2b.SalesOrderDetailDetail
import org.leaf.eos2.b2b.Dealer

class GenerateRequestService {

    static transactional = false 

    def yifeiEncodeService

    def transferSalesOrder (Long salesOrderId, boolean asynchronous) {

        //get object instance
        def salesOrderInstance = SalesOrder.withCriteria{

            createAlias 'recordType', 'recordType'
            createAlias 'dealer', 'dealer'
            createAlias 'salesMan', 'salesMan'
            createAlias 'industry', 'industry'
            createAlias 'salesOrderDetails', 'salesOrderDetails', CriteriaSpecification.LEFT_JOIN
            createAlias 'salesOrderDetails.product', 'detailProduct', CriteriaSpecification.LEFT_JOIN
            createAlias 'salesOrderDetails.salesOrderDetailDetails', 'salesOrderDetailDetails', CriteriaSpecification.LEFT_JOIN
            createAlias 'salesOrderDetails.salesOrderDetailDetails.product', 'detailDetailProduct', CriteriaSpecification.LEFT_JOIN

            projections{
                //head
                property('recordType.serialNumber', 'TC001')        //00-TC001, 单别
                property('erpSerialNumber', 'TC002')                   //01-TC002, 单号
                property('orderDate' , 'TC003')                     //02-TC003, 订单日期
                property('dealer.serialNumber', 'TC004')            //03-TC004, 客户编号
                property('salesMan.serialNumber', 'TC006')          //04-TC006, 业务人员
                property('address1', 'TC010')                       //05-TC010, 送货地址（一）
                property('address2', 'TC011')                       //06-TC011, 送货地址（二）
                property('project', 'TC012')                        //07-TC012, 项目
                property('description', 'TC015')                    //08-TC015, 备注
                property('accountSerialNumber', 'TC213')            //09-TC213, 客户单号
                property('industry.serialNumber', 'TC205')          //10-TC205, 订单行业
                property('serialNumber', 'TC212')                   //11-TC212, WEB单号
                //body
                property('salesOrderDetails.serialNumber', 'TD003') //12-TD003, 序号
                property('detailProduct.serialNumber', 'TD004')     //13-TD004, 品号
                property('salesOrderDetails.quantity', 'TD008')     //14-TD008, 订单数量
                property('salesOrderDetails.price', 'TD011')        //15-TD011, 单价
                property('salesOrderDetails.amount', 'TD012')       //16-TD012, 金额
                property('salesOrderDetails.deliveryLimitation', 'TD013')   //17-预交货日
                property('salesOrderDetails.discount', 'TD026')     //18-TD026, 标准折扣
                property('salesOrderDetails.finalPrice', 'TD200')   //19-TD200, 实际售价
                property('salesOrderDetails.specialDiscount', 'TD202')      //20-TD202, 特价折扣
                property('salesOrderDetails.specialAmount', 'TD203')        //21-TD203, 特价金额
                property('salesOrderDetails.finalDiscount', 'TD208')//22-TD208, 最终折扣率
                property('salesOrderDetails.finalAmount', 'TD012')  //23-TD012, 最终金额
                property('salesOrderDetails.openDetail', 'TD210')   //24-TD210, 主附件定价
                property('salesOrderDetails.contractDetail', 'TD211')       //25-TD211, OA单号
                //detail
                property('salesOrderDetailDetails.serialNumber', 'XB003')   //26-XB003, 序号
                property('detailDetailProduct.serialNumber', 'XB004')       //27-XB004, 品号
                property('salesOrderDetailDetails.quantity', 'XB007')       //28-XB007, 数量
                property('salesOrderDetailDetails.price', 'XB009')          //29-XB009, 单价
                property('salesOrderDetailDetails.discount', 'XB011')       //30-XB011, 标准折扣率
                property('salesOrderDetailDetails.amount', 'XB012')         //31-XB012, 标准金额
                property('salesOrderDetailDetails.specialDiscount', 'XB013')//32-XB013, 特价折扣率
                property('salesOrderDetailDetails.specialAmount', 'XB014')  //33-XB014, 特价金额
                property('salesOrderDetailDetails.finalDiscount', 'XB015')  //34-XB015, 最终折扣率
                property('salesOrderDetailDetails.finalAmount', 'XB016')    //35-XB016, 最终金额
                property('salesOrderDetailDetails.finalPrice', 'XB017')     //36-XB017, 实际售价
                property('salesOrderDetailDetails.contractDetail', 'XB019') //37-XB019, OA单号
                //head plus
                property('quantity', 'TC031')                               //38-TC031, 总数量
                property('amount', 'TC201')                                 //39-TC201, 标准金额
                property('specialAmount', 'TC202')                          //40-TC202, 特价金额
            }

            fetchMode "recordType", FM.JOIN
            fetchMode "dealer", FM.JOIN
            fetchMode "salesMan", FM.JOIN
            fetchMode "industry", FM.JOIN
            fetchMode "salesOrderDetails", FM.EAGER
            fetchMode "salesOrderDetails.product", FM.JOIN
            fetchMode "salesOrderDetails.salesOrderDetailDetails", FM.JOIN
            fetchMode "salesOrderDetails.salesOrderDetailDetails.product", FM.JOIN

            eq("id", salesOrderId.toLong())
        }
        //log.info "${salesOrderInstance as JSON}"

        //put data into map
        def fchrFieldName = ''
        def fchrRowData = ''
        salesOrderInstance?.each{
            def fmapData = [:]
            def flstFieldName = [] 
            def flstFieldData = []
            //head
            fmapData.put('TC001', it[0]?: '')   //TC001, 单别
            def erpSerialNumber = (it[1]?: '').tokenize('-')
            fmapData.put('TC002', (erpSerialNumber.size() > 1)?erpSerialNumber[1]:'')   //TC002, 单号
            fmapData.put('TC003', it[2]?.format('yyyy-MM-dd'))  //TC003, 订单日期
            fmapData.put('TC039', it[2]?.format('yyyy-MM-dd'))  //TC039, 单据日期
            fmapData.put('TC004', it[3]?: '')   //TC004, 客户编号
            //fmapData.put('TC006', '')   //TC006, 业务人员
            fmapData.put('TC010', it[5]?: '')   //TC010, 送货地址（一）
            fmapData.put('TC011', it[6]?: '')   //TC011, 送货地址（二）
            fmapData.put('TC012', it[7]?: '')   //TC012, 项目
            fmapData.put('TC015', it[8]?: '')  //TC015, 备注
            fmapData.put('TC027', 'N')          //TC027，审核码
            fmapData.put('TC031', String.format('%.2f', it[38]))   //TC031, 总数量
            fmapData.put('TC201', String.format('%.2f', it[39]) )   //TC201, 标准金额
            fmapData.put('TC202', String.format('%.2f', it[40]) )   //TC202, 特价金额
            fmapData.put('TC205', it[10]?: '')   //TC205, 订单行业
            fmapData.put('TC208', 'N')          //TC208,纳入季返
            fmapData.put('TC209', 'N')          //TC209,纳入年返
            fmapData.put('TC212', it[11]?: '')   //TC212, WEB单号
            fmapData.put('TC213', it[9]?: '')  //TC213, 客户单号 
            //body
            fmapData.put('TD003', it[12]?: '')   //TD003, 序号
            fmapData.put('TD004', it[13]?: '')   //TD004, 品号
            fmapData.put('TD008', it[14]?: '')   //TD008, 订单数量
            fmapData.put('TD009', 0)            //TD009, 已交数量
            fmapData.put('TD011', String.format('%.6f', it[15]) )  //TD011, 单价, price
            fmapData.put('TD012', String.format('%.2f', it[23]) )  //TD012, 金额, final amount
            fmapData.put('TD013', it[17]?.format('yyyy-MM-dd'))  //预交货日
            fmapData.put('TD016', 'N')          //TD016, 结束
            fmapData.put('TD021', 'N')          //TD021, 审核码
            fmapData.put('TD026', String.format('%.4f', it[18]) )  //TD026, 标准折扣率
            fmapData.put('TD200', String.format('%.6f', it[19]) )  //TD200, 实际售价, final price
            fmapData.put('TD201', String.format('%.2f', it[16]) )  //TD201, 标准金额, amount
            fmapData.put('TD202', String.format('%.4f', it[20]) )  //TD202, 特价折扣, special discount
            fmapData.put('TD203', String.format('%.2f', it[21]) )  //TD203, 特价金额, special amount
            fmapData.put('TD208', String.format('%.4f', it[22]) )  //TD208, 最终折扣率, final discount
            fmapData.put('TD012', String.format('%.2f', it[23]) )  //TD012, 最终金额, final amount
            fmapData.put('TD210', (it[24] == true)? 'Y' : 'N')  //TD210, 主附件定价
            fmapData.put('TD211', it[25]?: '')  //TD211, OA单号

            //detail
            fmapData.put('XB003', it[26]?: '')  //XB003, 序号
            fmapData.put('XB004', it[27]?: '')  //XB004, 品号
            fmapData.put('XB007', it[28]?: '')  //XB007, 数量
            fmapData.put('XB009', it[29]?: '')  //XB009, 单价
            fmapData.put('XB011', String.format('%.4f', it[30]?:0.0000)   )  //XB011, 标准折扣率
            fmapData.put('XB012', String.format('%.2f', it[31]?:0.00)   )  //XB012, 标准金额
            fmapData.put('XB013', String.format('%.4f', it[32]?:0.0000)   )  //XB013, 特价折扣率
            fmapData.put('XB014', String.format('%.2f', it[33]?:0.00)   )  //XB014, 特价金额
            fmapData.put('XB015', String.format('%.4f', it[34]?:0.0000)   )  //XB015, 最终折扣率
            fmapData.put('XB016', String.format('%.2f', it[35]?:0.00)   )  //XB016, 最终金额
            fmapData.put('XB017', String.format('%.6f', it[36]?:0.000000)   )  //XB017, 实际售价
            fmapData.put('XB019', it[37]?: '')  //XB019, OA单号
            //向LIST中加入字段和值
            for(key in fmapData.keySet()){
                flstFieldName.add(yifeiEncodeService.encode(key))
                flstFieldData.add(yifeiEncodeService.encode(fmapData.get(key)))
            }
            //用|连接list
            if(fchrFieldName == ''){
                fchrFieldName = flstFieldName.join('|')
            }
            def fchrFieldData = flstFieldData.join('|')
            fchrRowData = """
                ${fchrRowData}
                <Row Data='${fchrFieldData}'/>
            """
        }

        //准备出站消息
        def inputData = """
            <Data>
                <DataSet Field='${fchrFieldName}'>
                    ${fchrRowData}         
                </DataSet>
            </Data>
        """
        
        /*Operate
        
        Insert              新增，如果主键已经存在，则报错
        Delete              删除，如果主键不存在，则报错
        Update              更新，如果主键不存在，则不执行操作，更新时不会删除多余明细资料
        Sync                更新，将原对象删除并新增为新对象
        Adjust              更新，如果原对象不存在则新增，否则为更新（更新时，不会删除多余的明细资料）
        DelRedundantAdjust  更新，如果原对象不存在则新增，否则为更新（更新时，删除多余明细资料）
        
        */
        //
    
        def waitingForResult = "<WaitingForResult>${asynchronous?'N':'Y'}</WaitingForResult>"

        def inputXml = """
            <STD_IN Origin="Leaf">
                <Factory>Leader</Factory>
                ${waitingForResult} 
                <ObjectID>INCO1</ObjectID>
                <Service Name="SetData">
                    <Operate>Insert</Operate>
                    ${inputData}
                </Service>
            </STD_IN>
        """
        
        return inputXml
    }

    def creditControl(Long dealerId, boolean asynchronous){
        def dealer = Dealer.withCriteria(uniqueResult:true){
            projections{
                property("serialNumber")
            }
            eq("id", dealerId)
            maxResults(1)
        }
        def waitingForResult = "<WaitingForResult>${asynchronous?'N':'Y'}</WaitingForResult>"
        def inputXml = """
            <STD_IN Origin="Leaf">
                <Service Name="GetData">
                    <Factory>Leader</Factory>
                    ${waitingForResult}
                    <ObjectID>CreditCtrl</ObjectID>
                    <Condition>
                        <Group Type="AND">
                            <Property Name="TC004" Type="=" Value="${dealer}"></Property>
                        </Group>
                    </Condition>
                </Service>
            </STD_IN>
        """
        return inputXml
    }
}
