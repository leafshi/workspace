package org.leaf.eos2.b2b

class Industry {

    String serialNumber//编号
    String shortName//简称
    String fullName//全称
    Boolean isActive//是否启用
    String description//备注
    
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期

    static constraints = {
        serialNumber(nullable:false,blank:false, maxSize:6, unique:true)
        shortName(nullable:false, blank:false, maxSize:10)
        fullName(nullable:false, blank:false, maxSize:50)
        isActive(nullable:true)
        description(maxSize:255, nullable:true, blank:true)
    }
    
    String toString() {
        return "$serialNumber-$shortName"
    }
    
    static mapping = {
        table 'B2B_INDUSTRY'
    }

}
