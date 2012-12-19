package org.leaf.eos2.wf

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.b2b.Department

class Workflow {

    String name//名称
    String domain//对象名
    RecordType recordType//记录类型
	Department department//部门
    Boolean isActive//是否启用
    String description//描述
    
    Date dateCreated                //创建日期
    Date lastUpdated                //上次修改日期

    
    static hasMany = [steps : WorkflowStep]

    static constraints = {
        name(nullable:false, blank:false, maxSize:100, unique:['domain', 'recordType'])
        domain(nullable:false, blank:false, maxSize:30)
        recordType(nullable:true)
        isActive(nullable:true)
        description(nullable:true, blank:true, maxSize:255)
    }
    
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'WF_WORKFLOW'
        steps sort:'serialNumber', order:'asc'
    }
}
