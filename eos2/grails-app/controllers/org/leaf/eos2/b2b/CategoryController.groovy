package org.leaf.eos2.b2b

class CategoryController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/category/list', model : [categoryInstanceList: Category.list(params), categoryInstanceTotal: Category.count()]
    }

    def create = {
        def categoryInstance = new Category()
        categoryInstance.properties = params
        render view : '/b2b/category/create', model : [categoryInstance: categoryInstance]
    }

    def save = {
        def categoryInstance = new Category(params)
        if (!categoryInstance.hasErrors() && categoryInstance.save()) {
            flash.message = "category.created"
            flash.args = [categoryInstance.id]
            flash.defaultMessage = "Category ${categoryInstance.id} created"
            redirect(action: "show", id: categoryInstance.id)
        }
        else {
            render(view: "/b2b/category/create", model: [categoryInstance: categoryInstance])
        }
    }

    def show = {
        def categoryInstance = Category.get(params.id)
        if (!categoryInstance) {
            flash.message = "category.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Category not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/category/show' , model : [categoryInstance: categoryInstance]
        }
    }

    def edit = {
        def categoryInstance = Category.get(params.id)
        if (!categoryInstance) {
            flash.message = "category.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Category not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/category/edit', model : [categoryInstance: categoryInstance]
        }
    }

    def update = {
        def categoryInstance = Category.get(params.id)
        if (categoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (categoryInstance.version > version) {
                    
                    categoryInstance.errors.rejectValue("version", "category.optimistic.locking.failure", "Another user has updated this Category while you were editing")
                    render(view: "/b2b/category/edit", model: [categoryInstance: categoryInstance])
                    return
                }
            }
            categoryInstance.properties = params
            if (!categoryInstance.hasErrors() && categoryInstance.save()) {
                flash.message = "category.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Category ${params.id} updated"
                redirect(action: "show", id: categoryInstance.id)
            }
            else {
                render(view: "/b2b/category/edit", model: [categoryInstance: categoryInstance])
            }
        }
        else {
            flash.message = "category.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Category not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def categoryInstance = Category.get(params.id)
        if (categoryInstance) {
            try {
                categoryInstance.delete()
                flash.message = "category.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Category ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "category.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Category ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "category.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Category not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
