package org.leaf.eos2.admin

import org.leaf.eos2.shiro.User

class ShareRole {

	User user
	Group group
	String domain
	Boolean readable
	Boolean editable
	Boolean deletable
	
    Date dateCreated
    Date lastUpdated

    static constraints = {
		user (nullable:false)
		group(nullable:false)
		domain(nullable:false, unique : ['group', 'domain'])
		readable(nullable:true)
		editable(nullable:true)
		deletable(nullable:true)
    }
	
    String toString() {
        return "$user.username - $group.name - $domain"
    }

	static mapping = {
		table 'ADMIN_SHAREROLE'
	}
	
}
