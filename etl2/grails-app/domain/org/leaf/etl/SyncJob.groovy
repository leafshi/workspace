package org.leaf.etl

class SyncJob {

	String name						//名称
	String source					//源
	String target					//目标
	Integer priority				//优先级
	String scriptName				//脚本名称
	
    Date dateCreated                //创建日期
    Date lastUpdated                //上次修改日期
	
	static hasMany = [schedules : SyncJobSchedule]

    static constraints = {
		name(nullable:false, blank:false, maxSize:50, unique : true)
		source(nullable:false, blank:false, maxSize:50)
		target(nullable:false, blank:false, maxSize:50)
		priority(nullable:false)
		scriptName(nullable:false, blank:false, maxSize:300)
    }
    
    String toString() {
        return "$name"
    }
    
    static mapping = {
        table 'ETL_SYNC_JOB'
    }
}