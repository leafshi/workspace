package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.Group

class Department {

    def departmentService
    
    String serialNumber//部门编号
    String name//名称
    String type//类型：B办事处，R：大区，C：商务部
    String description//备注
    Boolean isActive//有效码
    Department parentDept//上级部门
    Date dateCreated
    Date lastUpdated
    
    static constraints = {
        serialNumber(maxSize:6, unique:true)
        name(maxSize:20)
        type(maxSize:2)
        description(maxSize:255, nullable:true, blank:true)
        isActive(nullable:true)
        parentDept(nullable:true)
    }
    
    String toString() {
        return "$serialNumber-$name"
    }

    def afterInsert (){
        Group.withNewSession{
            new Group(name:name, isActive:true).save(flush:true)
        }
        if(type == 'B'){
            Group.withNewSession{
                departmentService.initWorkflow(this)
            }
        }
    }
    
    static mapping = {
        table 'B2B_DEPARTMENT'
    }

}
