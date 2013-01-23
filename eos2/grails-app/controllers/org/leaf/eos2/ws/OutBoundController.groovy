package org.leaf.eos2.ws

class OutBoundController {

	def outBoundService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/ws/outBound/list', model : [outBoundInstanceList: outBoundService.list(params), outBoundInstanceTotal: outBoundService.count(params)]
    }

    def create = {
        def outBoundInstance = new OutBound()
        outBoundInstance.properties = params
        render view : '/ws/outBound/create', model : [outBoundInstance: outBoundInstance]
    }

    def save = {
        def outBoundInstance = new OutBound(params)
        if (!outBoundInstance.hasErrors() && outBoundInstance.save()) {
            flash.message = "outBound.created"
            flash.args = [outBoundInstance.id]
            flash.defaultMessage = "OutBound ${outBoundInstance.id} created"
            redirect(action: "show", id: outBoundInstance.id)
        }
        else {
            render(view: "/ws/outBound/create", model: [outBoundInstance: outBoundInstance])
        }
    }

    def show = {
        def outBoundInstance = OutBound.get(params.id)
        if (!outBoundInstance) {
            flash.message = "outBound.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "OutBound not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/ws/outBound/show', model : [outBoundInstance: outBoundInstance]
        }
    }

    def edit = {
        def outBoundInstance = OutBound.get(params.id)
        if (!outBoundInstance) {
            flash.message = "outBound.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "OutBound not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/ws/outBound/edit', model : [outBoundInstance: outBoundInstance]
        }
    }

    def update = {
        def outBoundInstance = OutBound.get(params.id)
        if (outBoundInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (outBoundInstance.version > version) {
                    
                    outBoundInstance.errors.rejectValue("version", "outBound.optimistic.locking.failure", "Another user has updated this OutBound while you were editing")
                    render(view: "/ws/outBound/edit", model: [outBoundInstance: outBoundInstance])
                    return
                }
            }
            outBoundInstance.properties = params
            if (!outBoundInstance.hasErrors() && outBoundInstance.save()) {
                flash.message = "outBound.updated"
                flash.args = [params.id]
                flash.defaultMessage = "OutBound ${params.id} updated"
                redirect(action: "show", id: outBoundInstance.id)
            }
            else {
                render(view: "/ws/outBound/edit", model: [outBoundInstance: outBoundInstance])
            }
        }
        else {
            flash.message = "outBound.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "OutBound not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def outBoundInstance = OutBound.get(params.id)
        if (outBoundInstance) {
            try {
                outBoundInstance.delete()
                flash.message = "outBound.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "OutBound ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "outBound.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "OutBound ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "outBound.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "OutBound not found with id ${params.id}"
            redirect(action: "list")
        }
    }
    
    //激活已经死掉的出站消息
    def resurrect = {
        outBoundService.resurrect(params?.id?.toLong())
        redirect controller : 'outBound', action : 'show', id : params?.id
    }

    //初始化出站消息
    def reinit = {
        outBoundService.reinit(params?.id?.toLong())
        redirect controller : 'outBound', action : 'show', id : params?.id
    }

    def showXml = {
        //def outBoundMessage = OutBound.get()
        //def inputXml = generateRequestService."${outBoundMessage.method}"(outBoundMessage.objectId.toLong(), outBoundMessage.asynchronous)
 
        render "${outBoundService.showXml(params?.id?.toLong())}"
    }
}
