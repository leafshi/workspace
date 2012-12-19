package org.leaf.eos2.admin

class RecordType {

    String serialNumber//编号
    String name//名称
    String domain//对象
    String description//描述
    Boolean isActive//是否启用
    Date dateCreated
    Date lastUpdated

    static constraints = {
        serialNumber(nullable:false, unique:'domain', maxSize:3)
        name(nullable:false, blank:false, maxSize:20)
        domain(nullable:false, blank:false, maxSize:20)
        description(nullable:true, blank:true, maxSize:255)
        isActive(nullable:true)
    }
	
    String toString() {
        return "$serialNumber-$name"
    }
    
    static mapping = {
        table 'ADMIN_RECORDTYPE'
    }
	
}
