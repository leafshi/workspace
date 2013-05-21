package org.leaf.etl

class SyncJobSchedule {

	SyncJob job

	Date beginTime
	Date endTime
	
	Integer days
	Boolean working
	Boolean finished
	
	static belognsTo = [job: SyncJob]
	static hasMany = [histories : SyncJobHistory]

    static constraints = {
    
		job(nullable:false)

		beginTime(nullable:false)
		endTime(nullable:false)
	
		days(nullable:false)
		
		finished(nullable:false)
		
    }
    
    String toString() {
        return "${job.name}-${id}"
    }
    
    static mapping = {
        table 'ETL_SYNC_JOB_SCHEDULE'
    }

}