package org.leaf.eos2.admin

import org.leaf.eos2.shiro.User

class Group {

	String name
	Boolean isActive
	String description
	
    Date dateCreated
    Date lastUpdated

	
	static hasMany = [ users : User ]

    static constraints = {
        name(nullable:false, blank:false, unique:true, maxSize:20)
		isActive(nullable:true)
		description(nullable:true, blank:true, maxSize:255)
    }

    String toString() {
		return "$name"
	}
					    
	static mapping = {
		table 'ADMIN_GROUP'
	}
}
