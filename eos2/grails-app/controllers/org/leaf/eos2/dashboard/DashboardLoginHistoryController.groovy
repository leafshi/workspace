package org.leaf.eos2.dashboard

class DashboardLoginHistoryController {

	def dashboardLoginHistoryService
		
    def browserPercent = {
    
		def columns = [['string', 'Browser Name'], ['number', 'Quantity']];
        def data = dashboardLoginHistoryService.browserPercent();
        def title = "Browser Percent"
        def width = "100%"
        def height = "600"
        
		render view : '/dashboard/loginHistory/browserPercent', model: [
        	  "title" : title
        	, "columns": columns
        	, "data": data
        	, "width" : width
        	, "height" : height
        	]
    }
    
    def platformPercent = {
    
		def columns = [['string', 'Platform Percent'], ['number', 'Quantity']];
        def data = dashboardLoginHistoryService.platformPercent();
        def title = "Platform Percent"
        def width = "100%"
        def height = "600"
		render view : '/dashboard/loginHistory/platformPercent', model: [
        	  "title" : title
        	, "columns": columns
        	, "data": data
        	, "width" : width
        	, "height" : height
        	]

    
    }
}
