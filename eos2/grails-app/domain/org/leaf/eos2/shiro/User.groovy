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
    }
	
    static mapping = {
        table 'SHIRO_USER'
    }
}
