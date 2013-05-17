package org.leaf.eos2.shiro

import org.leaf.eos2.admin.Profile
import org.leaf.eos2.b2b.Department
import org.leaf.eos2.admin.Group
import org.apache.shiro.crypto.hash.Sha512Hash

class User {

    String username
    String passwordHash
	
    Profile profile
    Department department
    Group group
    Role role
    
    String phone
    String mail
    
    Boolean registerECS
    Boolean activateECS
    
    Boolean isActive
    
    Date dateCreated
    Date lastUpdated
    
    //static hasMany = [ permissions: String ]
	static belongsTo = [group : Group, role : Role]


    String toString() {
        return "$username"
    }

	def beforeValidate(){
        if(this.passwordHash == null){
            this.passwordHash = new Sha512Hash('gh123098& -.').toHex()
        }
    }

    def beforeInsert(){
		//用户名大写
		this.username = this.username?.toUpperCase();
        //如果部门不为空，则将用户加用到部门组中
        if(this.department != null){//办事处或者商务部用户，给用户分配组
            def departmentName = Department.get(this.department.id).name
            def group = Group.findByName(departmentName)
            this.group = group
        }
        this.registerECS = false
        this.activateECS = false
        this.isActive = true
    }
	
	def beforeUpdate(){
		//用户名大写
		this.username = this.username?.toUpperCase();
	}

    static constraints = {
		username(nullable: false, blank: false, unique: true, minSize:5, maxSize:25, matches: "[0-9a-zA-Z_]+")        
		passwordHash(nullable: false, blank: false, minSize:5)
        profile(nullable:true)
        department(nullable:true)
		group(nullable:true)
		role(nullable:false)
		
		phone(nullable:true, blank: false, maxSize:11)
		mail(nullable:true, blank: false, maxSize:50)
		
		registerECS(nullable:true)
		activateECS(nullable:true)
		
		isActive(nullable:true)
		
    }
	
    static mapping = {
        table 'SHIRO_USER'
    }
}