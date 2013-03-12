package org.leaf.eos2.news

import org.leaf.eos2.shiro.User

class Entity {

	String title
	String content
	String level
	
	User createdBy
	User lastModifiedBy
	
    Date dateCreated
    Date lastUpdated
    
    static hasMany = [attachments: Attachment, readers : Reader]

    static constraints = {
    	title nullable:false, blank:false, maxSize : 100
    	content nullable:false, blank:false, maxSize : 500
    	level nullable:false, blank:false, maxSize : 20
    	createdBy nullable:false
    	lastModifiedBy nullalbe:false
    }
    
    String toString() {
        return "$title"
    }
    
    static mapping = {
        table 'NEWS_ENTITY'
        attachments sort:'serialNumber', order:'asc'
    }
}
