package org.leaf.eos2.shiro

class LoginHistory {

	String username
	Date loginTime
	String sourceIP
	String loginType
	Boolean isSucceed
	String browser
	String platform
	String operatingSystem
    Date dateCreated
    Date lastUpdated

    String toString() {
        return "$id"
    }

    static constraints = {
    	username(nullable:false, blank:false, maxSize : 100)
    	loginTime(nullable:false)
    	sourceIP(nullable:false, blank:false, maxSize : 40)
    	loginType(nullable:false, blank:false, maxSize : 50)
    	isSucceed(nullable:false)
    	browser(nullable:false, blank:false, maxSize : 100)
    	platform(nullable:false, blank:false, maxSize : 100)
    	operatingSystem(nullable:false, blank:false, maxSize : 100)
    }
    
    static mapping = {
        table 'SHIRO_LOGIN_HISTORY'
    }
}
