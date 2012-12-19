package org.leaf.eos2.admin

import java.util.Date

class Profile {

    String name
    Date dateCreated
    Date lastUpdated

    static constraints = {
		name(maxSize:40, nullable:false, blank:false, unique : true)
    }
	
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'ADMIN_PROFILE'
    }
}
