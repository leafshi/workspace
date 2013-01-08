package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.shiro.User

class Contract {

    String serialNumber//申请单编号
    String subject//主题
    RecordType recordType//申请单类型
    String applicant//申请人
    Date filingDate//申请日期
    Date effectiveDate//生效日期
    Dealer dealer//经销商
    String project//项目
    Industry industry//行业
    String description//描述
    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期
	User createdBy//创建人　
	User lastModifiedBy//上次修改人
	User owner//所有人
        
    static hasMany = [contractDetails:ContractDetail]

    static constraints = {
        serialNumber(maxSize:18, nullable:false, blank : false, unique:true)
        subject(maxSize:200, nullable:false, blank:false)
        recordType(nullable:false)
        applicant(nullable:false, blank:false, maxSize:20)
        filingDate(nullable:false, blank:false)
        effectiveDate(nullable:false,blank:false)
        dealer(nullable:false)
        project(nullable:false, blank : false, maxSize:200)
        industry(nullable:false)
        description(nullable:true, blank:true, maxSize:500)
    }
    
    String toString() {
        return "$serialNumber"
    }
    
    static mapping = {
        table 'B2B_CONTRACT'
        contractDetails sort:'serialNumber', order:'asc', lazy : true
    }

}
