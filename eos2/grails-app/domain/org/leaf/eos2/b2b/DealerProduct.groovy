package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User

class DealerProduct {

	Dealer dealer				//经销商			MB001
	Product product				//产品			MB002
	String unit1				//计价单位		MB003
	String unit2				//小单位			MB005
	String currency				//币种			MB004
	BigDecimal price			//单价			MB008
	Boolean componentPricing	//分量计价		MB007
	Date approvalDate			//核价日			MB009
	Date lastTransactionDate	//上次销货日		MB010
	Boolean	isIncludeTax		//含税			MB013
	Date firstTransactionDate	//初次交易日		MB014
	BigDecimal commissionOfUnit	//佣金单价		MB015
	BigDecimal commissionPercent//佣金百分比		MB016
	Date beginDate				//生效日期		MB017
	Date closeDate				//失效日期		MB018
	String description			//备注			MB012

    User owner					//所有人
	User createdBy				//创建人
	User lastModifiedBy			//上次修改人
	
    Date dateCreated			//创建日期
    Date lastUpdated			//修改日期

    static constraints = {
    	dealer(nullable:false)
    	product(nullable:false)
    	unit1(nullable:false, blank:true, maxSize:8)
    	unit2(nullable:false, blank:true, maxSize:8)
    	currency(nullable:false, blank:false, maxSize:8)
    	price(nullable:false, min : new BigDecimal(0), scale:6 )
    	componentPricing(nullable:false)
    	approvalDate(nullable:false)
    	lastTransactionDate(nullable:true)
    	isIncludeTax(nullable:false)
    	firstTransactionDate(nullable:true)
    	commissionOfUnit(nullable:false, min : new BigDecimal(0), scale:6)
    	commissionPercent(nullable:false, min : new BigDecimal(0), scale:4)
    	beginDate(nullable:false)
    	closeDate(nullable:true)
    	description(nullable:true, blank:true, maxSize : 500)
    	
    	owner(nullable:false)
    	createdBy(nullable:false)
    	lastModifiedBy(nullable:false)
    }
    
    String toString(){
    	return "${dealer.serialNumber}-${product.serialNumber}"
    }
    
    static mapping = {
        table 'B2B_DEALER_PRODUCT'
    }
    
}
