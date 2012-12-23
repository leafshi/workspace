package org.leaf.eos2.b2b

class DealerController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : "/b2b/dealer/list", model : [dealerInstanceList: Dealer.list(params), dealerInstanceTotal: Dealer.count()]
    }

    def create = {
        def dealerInstance = new Dealer()
        dealerInstance.properties = params
        render view : "/b2b/dealer/create", model : [dealerInstance: dealerInstance]
    }

    def save = {
        def dealerInstance = new Dealer(params)
        if (!dealerInstance.hasErrors() && dealerInstance.save()) {
            flash.message = "dealer.created"
            flash.args = [dealerInstance.id]
            flash.defaultMessage = "Dealer ${dealerInstance.id} created"
            redirect(action: "show", id: dealerInstance.id)
        }
        else {
            render(view: "/b2b/dealer/create", model: [dealerInstance: dealerInstance])
        }
    }

    def show = {
        def dealerInstance = Dealer.get(params.id)
        if (!dealerInstance) {
            flash.message = "dealer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Dealer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/dealer/show', model : [dealerInstance: dealerInstance]
        }
    }

    def edit = {
        def dealerInstance = Dealer.get(params.id)
        if (!dealerInstance) {
            flash.message = "dealer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Dealer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/dealer/edit', model : [dealerInstance: dealerInstance]
        }
    }

    def update = {
        def dealerInstance = Dealer.get(params.id)
        if (dealerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dealerInstance.version > version) {
                    
                    dealerInstance.errors.rejectValue("version", "dealer.optimistic.locking.failure", "Another user has updated this Dealer while you were editing")
                    render(view: "/b2b/dealer/edit", model: [dealerInstance: dealerInstance])
                    return
                }
            }
            dealerInstance.properties = params
            if (!dealerInstance.hasErrors() && dealerInstance.save()) {
                flash.message = "dealer.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Dealer ${params.id} updated"
                redirect(action: "show", id: dealerInstance.id)
            }
            else {
                render(view: "/b2b/dealer/edit", model: [dealerInstance: dealerInstance])
            }
        }
        else {
            flash.message = "dealer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Dealer not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def dealerInstance = Dealer.get(params.id)
        if (dealerInstance) {
            try {
                dealerInstance.delete()
                flash.message = "dealer.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Dealer ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "dealer.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Dealer ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "dealer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Dealer not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
