package org.leaf.eos2.b2b

class DealerProductController {

	def dealerProductService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/b2b/dealerProduct/list', model : [dealerProductInstanceList: dealerProductService.list(params), dealerProductInstanceTotal: dealerProductService.count()]
    }

    def create = {
        def dealerProductInstance = dealerProductService.create()
        dealerProductInstance.properties = params
        render view : '/b2b/dealerProduct/create', model : [dealerProductInstance: dealerProductInstance]
    }

    def save = {
        def dealerProductInstance = new DealerProduct(params)
        if (!dealerProductInstance.hasErrors() && dealerProductService.save(dealerProductInstance)) {
            flash.message = "dealerProduct.created"
            flash.args = [dealerProductInstance.id]
            flash.defaultMessage = "DealerProduct ${dealerProductInstance.id} created"
            redirect(action: "show", id: dealerProductInstance.id)
        }
        else {
            render(view: "/b2b/dealerProduct/create", model: [dealerProductInstance: dealerProductInstance])
        }
    }

    def show = {
        def dealerProductInstance = dealerProductService.show(params.id)
        if (!dealerProductInstance) {
            flash.message = "dealerProduct.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "DealerProduct not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/dealerProduct/show', model : [dealerProductInstance: dealerProductInstance]
        }
    }

    def edit = {
        def dealerProductInstance = dealerProductService.edit(params.id)
        if (!dealerProductInstance) {
            flash.message = "dealerProduct.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "DealerProduct not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/dealerProduct/edit', model : [dealerProductInstance: dealerProductInstance]
        }
    }

    def update = {
        def dealerProductInstance = dealerProductService.get(params.id)
        if (dealerProductInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dealerProductInstance.version > version) {
                    
                    dealerProductInstance.errors.rejectValue("version", "dealerProduct.optimistic.locking.failure", "Another user has updated this DealerProduct while you were editing")
                    render(view: "/b2b/dealerProduct/edit", model: [dealerProductInstance: dealerProductInstance])
                    return
                }
            }
            dealerProductInstance.properties = params
            if (!dealerProductInstance.hasErrors() && dealerProductService.update(dealerProductInstance)) {
                flash.message = "dealerProduct.updated"
                flash.args = [params.id]
                flash.defaultMessage = "DealerProduct ${params.id} updated"
                redirect(action: "show", id: dealerProductInstance.id)
            }
            else {
                render(view: "/b2b/dealerProduct/edit", model: [dealerProductInstance: dealerProductInstance])
            }
        }
        else {
            flash.message = "dealerProduct.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "DealerProduct not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def dealerProductInstance = dealerProductService.delete(params.id)
        if (dealerProductInstance) {
            try {
                dealerProductService.delete2(params.id)
                flash.message = "dealerProduct.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "DealerProduct ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "dealerProduct.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "DealerProduct ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "dealerProduct.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "DealerProduct not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
