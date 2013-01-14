package org.leaf.eos2.birt

class ReportScanJob {

    static triggers = {
        cron name: 'reportScanJobTrigger', cronExpression: "0 5 * * * ?"
    }

    def group = "StartupGroup"

    def reportScanService
    
    def execute() { 
        log.warn "Running report scan"
        //reportScanService.scan()
    }
}