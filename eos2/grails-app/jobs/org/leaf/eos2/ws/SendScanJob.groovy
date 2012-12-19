package org.leaf.eos2.ws

class SendScanJob {

    def concurrent = false

    def sendScanJobService
    
	static triggers = {
		cron name: 'SendScanJobTrigger', cronExpression: "15,45 * * * * ?"
	}
	
	def execute() {
		// execute job
		sendScanJobService.scan()
	}
}
