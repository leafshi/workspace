package org.leaf.eos2.b2b

class Staff {

    String serialNumber
    String name
    Department department
    Date dateCreated
    Date lastUpdated

    static constraints = {
        serialNumber(nullable:false, blank:false, maxSize:5)
        name(nullable:false, blank :false, maxSize:10)
        department(nullable:false)
    }
    
    String toString() {
        return "$serialNumber-$name"
    }
    
    static mapping = {
        table 'B2B_STAFF'
    }
}
