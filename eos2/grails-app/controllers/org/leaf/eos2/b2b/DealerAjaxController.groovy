package org.leaf.eos2.b2b

class DealerAjaxController {

	def dealerService
	
    def initShareRole = { 
        def dealerInstance = Dealer.get(params.id)
        if (!dealerInstance) {
            flash.message = "dealer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Dealer not found with id ${params.id}"
            redirect(action: "list")
        }else {
            dealerService.initShareRole(dealerInstance)
            redirect(controller : 'dealer', action: "show", id: dealerInstance.id)
        }
    }
}
