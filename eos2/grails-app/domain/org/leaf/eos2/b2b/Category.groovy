package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType

class Category {

    RecordType recordType
    String serialNumber
    String name
    BigDecimal discount
    Integer productionCycle
    Integer transportCycle
    Integer deliveryCycle
    Boolean hasBom//是否有下阶主附件
    Boolean isAllowSpecialDiscount//是否允许特价折扣
    Boolean isAllowZeroPrice//是否允许零价格
    Boolean isActive
    Date dateCreated
    Date lastUpdated

    static constraints = {
        recordType(nullable:false)
        serialNumber(nullable:false, blank:false, unique:'recordType', maxSize:6)
        name(nullable:false, blank:false, maxSize:20)
        discount(nullable:false, min: new BigDecimal(0), scale : 4)
        productionCycle(nullable:false, min : 0I)
        transportCycle(nullable:false, min : 0I)
        hasBom(nullable:true)
        isAllowSpecialDiscount(nullable:true)
        isAllowZeroPrice(nullable:true)
        isActive(nullable:true)
    }
    
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'B2B_CATEGORY'
        deliveryCycle formula: 'production_cycle + transport_cycle'
    }

}
