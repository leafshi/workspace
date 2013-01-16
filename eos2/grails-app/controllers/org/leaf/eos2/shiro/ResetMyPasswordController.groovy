package org.leaf.eos2.shiro

class ResetMyPasswordController {

	def resetMyPasswordService

    def resetPassword = {
        render view : '/shiro/user/resetPassword', model : [userInstance: resetMyPasswordService.currentUser()]
    }
    
    def reset = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    userInstance.errors.rejectValue("version", "user.optimistic.locking.failure", "Another user has updated this User while you were editing")
                    render(view: "/shiro/user/resetPassword", model: [userInstance: userInstance])
                    return
                }
            }
            if (resetMyPasswordService.resetPassword(params.password, params.passwordConfirm)) {
                flash.message = "user.password.reset"
                flash.args = [params.id]
                flash.defaultMessage = "User ${params.id} password reseted"
				redirect(controller:'index', action: "index")
            }
            else {
                render(view: "/shiro/user/resetPassword", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "user.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "User not found with id ${params.id}"
            redirect(controller:'index', action: "index")
        }
    }

}
