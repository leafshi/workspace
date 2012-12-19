package org.leaf.eos2.b2b

class Product {

    String serialNumber//品号
    String name//品名
    String standard//规格
    String unit//单位
    BigDecimal price//标准单价
    Boolean isIncludeTax//含税?
    Boolean isActive//有效?
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期
    
    static hasMany = [categories : ProductCategory]
    
    static constraints = {
        serialNumber(maxSize:20, blank:false, nullable: false, unique:true)
        name(maxSize:60, blank:false, nullable:false)
        standard(maxSize:60, blank:false, nullable:false)
        unit(maxSize:4, nullable:false, blank: false)
        price(scale:6)
        isIncludeTax(nullable:true)
        isActive(nullable:true)
    }
    
    String toString() {
        return "$serialNumber"
    }
    
    static mapping = {
        table 'B2B_PRODUCT'
    }
}
