package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.ShareRole

class Dealer {

    def dealerService
    
    Department department
    String serialNumber		//经销商编号
    String name				//名称
    String alias			//简称
    String shortcut			//快捷码
    Staff salesMan			//业务员
    String head				//负责人
    String contact			//联系人
    String address1			//送货地址（一）
    String address2			//送货地址（二）
    String approvalStatus	//核准状况
    String pricingMode		//计价方式
    String pricingStrategy	//定价顺序
    User owner				//所有人
    Date dateCreated		//创建日期
    Date lastUpdated		//修改日期
    
    static belongsTo = [department:Department]
    
    static constraints = {
        department(nullable:false)
        serialNumber(maxSize:10, unique : true)
        name(maxSize:72)
        alias(maxSixe:20)
        shortcut(maxSize:20, nullable:true, blank:true)
        salesMan(nullable:false)
        head(maxSize:30, nullable:true, blank:true)
        contact(maxSize:30, nullable:true, blank:true)
        address1(maxSize:72, nullable:true, blank:true)
        address2(maxSize:72, nullable:true, blank:true)
        approvalStatus(maxSize:8)
        pricingMode(maxSize:10, nullable:true, blank:true)
        pricingStrategy(maxSize:20, nullable:false, blank:false)
        owner(nullable:false)
    }
    
    String toString() {
        return "$serialNumber-$alias"
    }

    def afterInsert () {
        ShareRole.withNewSession{
            dealerService.initShareRole(this)
        }
    }
    
    static mapping = {
        table 'B2B_DEALER'
    }

}
