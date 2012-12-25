package org.leaf.eos2.birt

class ReportController {

    def birtReportService
    
    def reportScanService

    def birtService

    def index = { redirect(action:"list") }
    def list = {
    	params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/birt/report/list', model : [reportInstanceList:Report.list(params), reportInstanceTotal: Report.count()]
    }

    def run = {
        if(params.id==null) {
            redirect(action:"list")
        } else {
            def rerun = params.remove('rerun')
            def reportName = params.remove('id')
            params.remove('controller')
            params.remove('action')
            params.remove('run')
            params.remove('submit')
            params['fintUserId'] = birtService.currentUser()
            params['fintMaxRows'] = 2000
            def reportParams = params
            def missing = []
            def pars = birtReportService.getReportParams(reportName)
            if(pars==null) {
                flash.error = "Could not find the Report ${reportName}!"
                redirect(action:"list")
            } else {
                pars.each { p ->
                    if( !(reportParams[p.name] || p.allowBlank) ) missing << p.name
                }
                if(missing.size == 0 && rerun==null) {
                    log.debug "No parameter missing, running report"
                    redirect(action:"view", id:reportName, params:reportParams)
                } else {
                    def props = birtReportService.getReportProperties(reportName)
                    if(rerun)
                        render view : '/birt/report/run', model :  [id:reportName, reportParams:reportParams, parameters:pars, title: props?.title, 'showAll':1]
                    else
                        render view : '/birt/report/run', model :  [id:reportName, reportParams:reportParams, parameters:pars, title: props?.title]
                }
            }
        }
    }
    
    def view = {
        if(params.list) {
            redirect(action:"list")
        } else {
            log.debug "Generating report with ${params}"
            def reportName=params.remove('id')
            params.remove('controller')
            params.remove('action')
            params.remove('view')
            params.remove('run')
            params['fintUserId'] = birtService.currentUser()
            params['fintMaxRows'] = 2000
            HashMap reportParams = []
            // Set the values of the parameter.
            def missing = []
            def pars = birtReportService.getReportParams(reportName)
            if(pars==null) {
                flash.error = "Could not find the Report ${reportName}!"
                redirect(action:"list")
            } else {
                pars.each { p ->
                    switch(p.type){
                    case 4: // DateTime
                    case 7: // Date
                        /*
                        if(params[p.name]=="struct")
                            reportParams[p.name] = params[p.name+"_month"] + "/" + params[p.name+"_day"] + "/" + params[p.name+"_year"]
                        else {
                            if(!params[p.name] && !p.allowBlank ) missing << p.name
                        }
                        */
                        if(!params[p.name]){
                            if(!p.allowBlank ){ 
                                missing << p.name
                            }
                        }else{
                            reportParams[p.name] = params[p.name]
                        }
                        break
                     case 5: // Boolean
                        if(params[p.name])
                            reportParams[p.name] = true
                        else
                            reportParams[p.name] = false
                        break
                     default:
                        reportParams[p.name] = params[p.name]
                        if( !(params[p.name] || p.allowBlank) ) missing << p.name
                    }
                }
                if(missing.size) {
                    flash.error ="Report ${params.reportName} is missing the required parameters: ${missing.join(',')}"
                    reportParams['rerun']=true
                    redirect(action:"run", id:reportName, params:reportParams)
                } else {
                    //get report name and launch the engine
                    def htmlOptions = birtReportService.getRenderOption(request, "html")
                        htmlOptions.setEmbeddable(true)
                    try {
                        def props = birtReportService.getReportProperties(reportName)
                        def reportContent = birtReportService.runAndRender(reportName, reportParams, htmlOptions).toString("UTF-8")
                        render view : '/birt/report/view',contentType:'text/html', model :  ['reportContent':reportContent, 'id':reportName, 'reportParams':reportParams, 'title' : props?.title]
                        return
                    } catch(Exception e){
                        flash.error = "Report ${reportName} encountered an error: ${e.message}"
                        reportParams['rerun']=true
                        redirect(action:"run", id:reportName, params:reportParams)
                    }
                }
            }
        }
    }

    def files = {
        def reportDir = new File(servletContext.getRealPath("/Reports"))
        def files = []
        reportDir.eachFile{ if(!it.directory) files<<it.name }
        render view : '/birt/report/files', model : ["fileList":files]
    }

    def upload = {
        def f = request.getFile('reportFile')
        if(!f?.empty) {
            f.transferTo( new File(servletContext.getRealPath("/Reports")+"/"+ f.getOriginalFilename().replaceAll(' ', '_')))
            flash.message = "File '${f.getOriginalFilename()}' successfully uploaded"
        } else {
            flash.message = "Uploaded file cannot be empty"
        }
        reportScanService.scan()
        redirect(action:"list")
    }

    def downloadAs = {
        // get report name and launch the engine
        log.info "params=${params}"
        String reportName = params.id
        String reportExt = params.format?:'xls'
        def format = grailsApplication.config.grails.mime.types[reportExt]?reportExt:'html'
        // Set the value of the parameter.
        HashMap reportParams = params
        reportParams.remove('action')
        reportParams.remove('controller')
        reportParams.remove('name')
        reportParams.remove('id')
        reportParams['fintUserId'] = birtService.currentUser()
        reportParams['fintMaxRows'] = 10000
        def options = birtReportService.getRenderOption(request, format)
        def result=birtReportService.runAndRender(reportName, reportParams, options)
        if(format=="html")
            render(view : 'birt/report/download', contentType:'text/html',encoding:"UTF-8", text:result.toString("UTF-8"))
        else {
            response.setHeader("Content-disposition", "attachment; filename=\"${reportName}.${reportExt}\"");
            response.contentType = grailsApplication.config.grails.mime.types[format.toLowerCase()]
            response.setCharacterEncoding "UTF-8"
            response.outputStream << result.toByteArray()
            return false
        }
    }
    
    def scan ={
    	reportScanService.scan()
    	redirect(action: "list")
    }

}
