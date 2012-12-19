package org.leaf.eos2.b2b

class SalesOrderDetailDetail {

    def salesOrderValidateService
    
    SalesOrderDetail salesOrderDetail   //客户订单单身
    String serialNumber                 //序号              |XB003
    Product product                     //产品，品号        |XB004
    BigDecimal quantity                 //数量              |XB007
    Integer dosage						//组成用量
    Integer quota						//工时底数
    BigDecimal price                    //单价              |XB009
    Boolean isAllowZeroPrice			//是否允许零价格
    BigDecimal discount                 //标准折扣率        |XB011
    BigDecimal specialDiscount          //特价折扣          |XB013
    Boolean isAllowSpecialDiscount		//是否允许特价折扣
    BigDecimal finalDiscount            //最终折扣          |XB015
    BigDecimal finalPrice               //实际售价          |XB017
    BigDecimal fullAmount               //面价金额          |
    BigDecimal amount                   //标准金额          |XB012
    BigDecimal specialAmount            //特价金额          |XB014
    BigDecimal finalAmount              //最终销售金额      |XB016
    ContractDetail contractDetail       //OA特价申请单明细  |
    
    static belongsTo = [salesOrderDetail : SalesOrderDetail]
    
    static constraints = {
        salesOrderDetail(nullable:false)
        serialNumber(nullable:false, blank:false, unique : 'salesOrderDetail', maxSize : 2)
        product(nullable:false)
        quantity(nullable:false, scale : 3, min : new BigDecimal("0.001")) 
        price(nullable:false, min : new BigDecimal(0), scale:6, validator:{val, obj ->
        	if(obj.price <= 0 && obj.isAllowZeroPrice != true){
				return false
			}
		})
        discount(nullable:false, scale:4)
        specialDiscount(nullable:false, scale:4, validator:{val, obj ->
			if(obj.isAllowSpecialDiscount != true && obj.specialDiscount > 0){
				return false
			}
        })
        finalDiscount(nullable:false, scale:4)
        finalPrice(nullable:false, scale:6)
        amount(nullable:false, scale:2)
        specialAmount(nullable:false, scale:2)
        finalAmount(nullable:false, scale:2)
        contractDetail(nullable:true)
    }
    
    String toString() {
        return "$serialNumber"
    }
    
    static mapping = {
        table 'B2B_SALESORDER_DETAIL_DETAIL'
		quantity  sqlType : "numeric", precision : 18, scale : 3
		price  sqlType : "numeric", precision : 18, scale : 6
		discount  sqlType : "numeric", precision : 18, scale : 4
		specialDiscount  sqlType : "numeric", precision : 18, scale : 4
		finalDiscount  sqlType : "numeric", precision : 18, scale : 4
		finalPrice  sqlType : "numeric", precision : 18, scale : 6
		fullAmount  sqlType : "numeric", precision : 18, scale : 2
		amount  sqlType : "numeric", precision : 18, scale : 2
		specialAmount  sqlType : "numeric", precision : 18, scale : 2
		finalAmount  sqlType : "numeric", precision : 18, scale : 2

    }

}
