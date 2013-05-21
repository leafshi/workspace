package org.leaf.eos2.admin

import org.leaf.eos2.shiro.User
import org.leaf.eos2.b2b.Department

class Group {

	String name
	Boolean isActive
	Department department
	String description
	
    Date dateCreated
    Date lastUpdated

	
	static hasMany = [ users : User ]

    static constraints = {
        name(nullable:false, blank:false, maxSize:20)
        department(nullable:false, unique : true)
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
