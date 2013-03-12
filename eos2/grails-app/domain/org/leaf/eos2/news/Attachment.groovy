package org.leaf.eos2.news

class Attachment {

	Entity entity
    String serialNumber
    String name

	static belongsTo = [entity: Entity]

    static constraints = {
    	entity nullable:false
    	serialNumber nullable:false, blank:false, maxSize : 2, unique : 'entity'
    	name nullable:false, blank:false, maxSize : 100
    }
    
    String toString() {
        return "$serialNumber"
    }
    
    static mapping = {
        table 'NEWS_ATTACHMENT'
    }
}
