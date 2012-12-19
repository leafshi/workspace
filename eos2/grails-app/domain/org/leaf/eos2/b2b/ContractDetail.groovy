package org.leaf.eos2.b2b

class ContractDetail {

    Contract contract//单头
    String serialNumber//序号
    Category category//产品系列
    BigDecimal discount//标准折扣
    BigDecimal specialDiscount//特价折扣
    BigDecimal finalDiscount//最终折扣
    Date expiryDate//截止日期
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期
    
    static belongsTo = [contract : Contract]
    
    static constraints = {
        contract(nullable:false)
        serialNumber(maxSize:2, nullable:false, blank:false)
        category(nullable:false, unique:['contract', 'serialNumber'])
        discount(nullable:false, scale:4)
        specialDiscount(nullable:false, scale:4)
        finalDiscount(nullable:false, scale:4)
        expiryDate(nullable:false)
    }
    
    String toString() {
        return "$contract.serialNumber-$serialNumber"
    }
    
    static mapping = {
        table 'B2B_CONTRACTDETAIL'
    }

}
