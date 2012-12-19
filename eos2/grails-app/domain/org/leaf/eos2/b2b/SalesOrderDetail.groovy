package org.leaf.eos2.b2b

class SalesOrderDetail {
    
    SalesOrder salesOrder           //订单
    String serialNumber             //序号              |TD003
    Product product                 //产品，品号        |TD004
    Integer deliveryCycle			//交货周期
    Date deliveryLimitation         //交货期限          |TD013
    Integer quantity                //订单数量          |TD008
    BigDecimal price                //标准单价          |TD011
    Boolean isAllowZeroPrice		//是否允许零价格
    BigDecimal discount             //标准折扣率        |TD026
    BigDecimal specialDiscount      //特价折扣          |TD202
    Boolean isAllowSpecialDiscount	//是否允许特价折扣
    BigDecimal finalDiscount        //最终折扣          |TD208
    BigDecimal finalPrice           //最终单价          |TD200
    BigDecimal fullAmount           //面价金额          |
    BigDecimal amount               //标准合计金额      |TD201
    BigDecimal specialAmount        //特价金额          |TD203
    BigDecimal finalAmount          //最终销售金额      |TD012
    Boolean openDetail              //主附件订价        |
    ContractDetail contractDetail   //OA特价申请单明细  |
        
    static belongsTo = [salesOrder:SalesOrder]
    static hasMany = [salesOrderDetailDetails:SalesOrderDetailDetail]

    static constraints = {
        salesOrder(nullable:false)
        serialNumber(nullable:true, blank:false,unique:'salesOrder', maxSize:2)
        product(nullable:false, blank:false)
        deliveryLimitation(nullable:false, blank:false)
        quantity(nullable:false, min : 1)
        price(nullable:false, scale:6, min : new BigDecimal(0), validator:{val, obj ->
            if(obj.price <= 0 && obj.isAllowZeroPrice != true){
                return false
            }
        })
        discount(nullable:false, scale:4)
        specialDiscount(nullable:false, scale:4, validator:{val, obj ->
        	//不开窗，不允许特价折扣，特价折扣>0
			if(obj.openDetail != true && obj.isAllowSpecialDiscount != true && obj.specialDiscount > 0){
				return false
			}
        })
        finalDiscount(nullable:false, scale:4)
        finalPrice(nullable:false, scale:6)
        amount(nullable:false, scale:2, min :new BigDecimal(0.01))
        specialAmount(nullable:false, scale:2, min : new BigDecimal(0))
        finalAmount(nullable:false, scale:2, min : new BigDecimal(0.01))
        openDetail(nullable:true, validator: { val, obj -> 
            //验证子单身行数是否大于零
            if(obj.openDetail == true){
                return (obj.salesOrderDetailDetails?.size() ?: 0 > 0) ? true:false
            }
        })
        contractDetail(nullable:true)
    }
    
    String toString() {
        return "$salesOrder.serialNumber-$serialNumber"
    }

    static mapping = {
        table 'B2B_SALESORDER_DETAIL'
        salesOrderDetailDetails cascade:"persist,merge,save-update,all-delete-orphan", sort:"serialNumber", batchSize: 100, lazy : true

		price sqlType : "numeric", precision : 18, scale : 6
		discount sqlType : "numeric", precision : 18, scale : 4
		specialDiscount sqlType : "numeric", precision : 18, scale : 4
		finalDiscount sqlType : "numeric", precision : 18, scale : 4
		finalPrice sqlType : "numeric", precision : 18, scale : 6
		fullAmount sqlType : "numeric", precision : 18, scale : 2
		amount sqlType : "numeric", precision : 18, scale : 2
		specialAmount sqlType : "numeric", precision : 18, scale : 2
		finalAmount sqlType : "numeric", precision : 18, scale : 3

    }
}
