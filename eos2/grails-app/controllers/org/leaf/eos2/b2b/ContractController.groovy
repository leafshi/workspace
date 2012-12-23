package org.leaf.eos2.b2b

class ContractController {

	def contractService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/contract/list', model : [contractInstanceList: contractService.list(params), contractInstanceTotal: contractService.count()]
    }

    def create = {
        def contractInstance = new Contract()
        contractInstance.properties = params
        render view : '/b2b/contract/create', model : [contractInstance: contractInstance]
    }

    def save = {
        def contractInstance = new Contract(params)
        if (contractService.save(contractInstance)) {
            flash.message = "contract.created"
            flash.args = [contractInstance.id]
            flash.defaultMessage = "Contract ${contractInstance.id} created"
            redirect(action: "show", id: contractInstance.id)
        }
        else {
            render(view: "/b2b/contract/create", model: [contractInstance: contractInstance])
        }
    }

    def show = {
        def contractInstance = contractService.show(params.id)
        if (!contractInstance) {
            flash.message = "contract.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Contract not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/contract/show', model : [contractInstance: contractInstance]
        }
    }

    def edit = {
        def contractInstance = contractService.edit(params.id)
        if (!contractInstance) {
            flash.message = "contract.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Contract not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/contract/edit', model : [contractInstance: contractInstance]
        }
    }

    def update = {
        def contractInstance = contractService.edit(params.id)
        if (contractInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (contractInstance.version > version) {
                    
                    contractInstance.errors.rejectValue("version", "contract.optimistic.locking.failure", "Another user has updated this Contract while you were editing")
                    render(view: "/b2b/contract/edit", model: [contractInstance: contractInstance])
                    return
                }
            }
            contractInstance.properties = params
            if (contractService.update(contractInstance)) {
                flash.message = "contract.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Contract ${params.id} updated"
                redirect(action: "show", id: contractInstance.id)
            }
            else {
                render(view: "/b2b/contract/edit", model: [contractInstance: contractInstance])
            }
        }
        else {
            flash.message = "contract.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Contract not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def contractInstance = contractService.delete(params.id)
        if (contractInstance) {
            try {
                contractInstance.delete()
                flash.message = "contract.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Contract ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "contract.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Contract ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "contract.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Contract not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
