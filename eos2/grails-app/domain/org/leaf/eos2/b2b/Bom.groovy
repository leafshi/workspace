package org.leaf.eos2.b2b

class Bom {

    Product product//主件品号
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期
    
    static hasMany = [bomDetails : BomDetail]
    
    static constraints = {
        product(blank:false, nullable: false, unique:true)
    }
    
    String toString() {
        return "$product.serialNumber"
    }
    
    static mapping = {
        table 'B2B_BOM'
        bomDetails sort:'serialNumber', order:'asc'
    }

}
