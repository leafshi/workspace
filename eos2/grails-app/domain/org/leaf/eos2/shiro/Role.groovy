package org.leaf.eos2.shiro

class Role {

    String name
    Boolean isAdmin
    Date dateCreated
    Date lastUpdated

    static hasMany = [ users: User, permissions: String ]
    //static belongsTo = User

    static constraints = {
        name(nullable: false, blank: false, unique: true, maxSize:50)
        isAdmin(nullable:false)
    }
    
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'SHIRO_ROLE'
    }
}
