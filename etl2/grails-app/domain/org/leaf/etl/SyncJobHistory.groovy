package org.leaf.etl

class SyncJobHistory {

	SyncJobSchedule jobSchedule
	
	String primaryKey
	String type
	
	Date endTime
	
	Boolean isOkay
	
	static belognsTo = [jobSchedule: SyncJobSchedule]
	
    static constraints = {
    	jobSchedule(nullable:false)
    	
		primaryKey(nullable:false,blank:false, maxSize:100)
		type(nullable:false, blank:false, maxSize : 10)
	
		endTime(nullable:true)
	
		isOkay(nullable:false)
    }
    
    String toString() {
        return "${jobSchedule.job.name}-${id}"
    }
    
    static mapping = {
        table 'ETL_SYNC_JOB_HISTORY'
    }
    
}