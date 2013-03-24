package org.leaf.eos2.admin

class Help {

	//标题
	String title
	//内容
	String content
	//创建日期
    Date dateCreated
    //修改日期
    Date lastUpdated

    static constraints = {
    	title(nullable:false, blank:false, maxSize : 100)
    	content(nullable:false, blank:false, maxSize : 1000)
    }
    
    String toString(){
    	render "${title}"
    }
    
    static mapping = {
    	table 'ADMIN_HELP'
    }
}
