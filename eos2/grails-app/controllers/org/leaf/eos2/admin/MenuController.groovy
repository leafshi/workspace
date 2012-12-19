package org.leaf.eos2.admin

class MenuController {

	def menuService
	
    def mySalesOrderList() { 
    	render view : '/admin/menu/mySalesOrderList', model : [mySalesOrderList : menuService.myOrderList()]
    }
    
    def outBoundList(){
    	render view : '/admin/menu/outBound', model : [outBoundList : menuService.outBoundList()]
    }
    
    def reportList(){
    	render view : '/admin/menu/report', model : [reportList : menuService.reportList()]
    }
}
