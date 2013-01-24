package org.leaf.eos2.shiro

class LoginHistoryController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        render view : '/shiro/loginHistory/list', model : [loginHistoryInstanceList: LoginHistory.list(params), loginHistoryInstanceTotal: LoginHistory.count()]
    }

    def create = {
        def loginHistoryInstance = new LoginHistory()
        loginHistoryInstance.properties = params
        render view : '/shiro/loginHistory/create', model : [loginHistoryInstance: loginHistoryInstance]
    }

    def save = {
        def loginHistoryInstance = new LoginHistory(params)
        if (!loginHistoryInstance.hasErrors() && loginHistoryInstance.save()) {
            flash.message = "loginHistory.created"
            flash.args = [loginHistoryInstance.id]
            flash.defaultMessage = "LoginHistory ${loginHistoryInstance.id} created"
            redirect(action: "show", id: loginHistoryInstance.id)
        }
        else {
            render(view: "/shiro/loginHistory/create", model: [loginHistoryInstance: loginHistoryInstance])
        }
    }

    def show = {
        def loginHistoryInstance = LoginHistory.get(params.id)
        if (!loginHistoryInstance) {
            flash.message = "loginHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "LoginHistory not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/shiro/loginHistory/show', model : [loginHistoryInstance: loginHistoryInstance]
        }
    }

    def edit = {
        def loginHistoryInstance = LoginHistory.get(params.id)
        if (!loginHistoryInstance) {
            flash.message = "loginHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "LoginHistory not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/shiro/loginHistory/edit', model : [loginHistoryInstance: loginHistoryInstance]
        }
    }

    def update = {
        def loginHistoryInstance = LoginHistory.get(params.id)
        if (loginHistoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (loginHistoryInstance.version > version) {
                    
                    loginHistoryInstance.errors.rejectValue("version", "loginHistory.optimistic.locking.failure", "Another user has updated this LoginHistory while you were editing")
                    render(view: "/shiro/loginHistory/edit", model: [loginHistoryInstance: loginHistoryInstance])
                    return
                }
            }
            loginHistoryInstance.properties = params
            if (!loginHistoryInstance.hasErrors() && loginHistoryInstance.save()) {
                flash.message = "loginHistory.updated"
                flash.args = [params.id]
                flash.defaultMessage = "LoginHistory ${params.id} updated"
                redirect(action: "show", id: loginHistoryInstance.id)
            }
            else {
                render(view: "/shiro/loginHistory/edit", model: [loginHistoryInstance: loginHistoryInstance])
            }
        }
        else {
            flash.message = "loginHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "LoginHistory not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def loginHistoryInstance = LoginHistory.get(params.id)
        if (loginHistoryInstance) {
            try {
                loginHistoryInstance.delete()
                flash.message = "loginHistory.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "LoginHistory ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "loginHistory.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "LoginHistory ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "loginHistory.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "LoginHistory not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
