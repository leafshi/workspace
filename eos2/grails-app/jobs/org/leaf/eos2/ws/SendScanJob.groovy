package org.leaf.eos2.ws

class SendScanJob {

    def concurrent = false

    def sendScanJobService
    
	static triggers = {
		cron name: 'SendScanJobTrigger', group: 'mySimpleTriggerGroup', cronExpression: "0,15,30,45 * * * * ?"
	}
	
	def execute() {
		// execute job
		sendScanJobService.scan()
	}
}
