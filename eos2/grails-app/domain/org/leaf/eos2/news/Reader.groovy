package org.leaf.eos2.news

import org.leaf.eos2.shiro.User

class Reader {

	Entity entity
	User reader
	Boolean visible
	
	static belongsTo = [entity : Entity]

    static constraints = {
    	entity nullable:false
    	reader nullable:false, unique : 'entity'
    	visible nullable: false
    }
    
    String toString(){
    	return "$reader.username"
    }
    
    static mapping = {
        table 'NEWS_READER'
    }
}
