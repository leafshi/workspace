package org.leaf.eos2.b2b

class BomDetail {

    Bom bom//主件品号
    String serialNumber//序号
    Product product//元件品号
    Integer dosage//组成用量
    Integer quota//工时底数
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期
    
    static belongsTo = [bom : Bom]
        
    static constraints = {
        bom(blank:false, nullable: false)
        serialNumber(maxSize:4, nullable:false, blank:false, unique:['bom'])
        product(blank:false, nullable: false)
        dosage(nullable:false, min:1)
        quota(nullable:false, min:1)
    }
    
    String toString() {
        return "$product.serialNumber"
    }
    
    static mapping = {
        table 'B2B_BOMDETAIL'
    }
	
}
