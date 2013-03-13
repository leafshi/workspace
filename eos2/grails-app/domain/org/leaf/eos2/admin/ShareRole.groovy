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
		domain(nullable:false, blank:false, unique : ['group', 'user'])
		readable(nullable:false)
		editable(nullable:false)
		deletable(nullable:false)
    }
	
    String toString() {
        return "$user.username - $group.name - $domain"
    }

	static mapping = {
		table 'ADMIN_SHAREROLE'
	}
	
}
