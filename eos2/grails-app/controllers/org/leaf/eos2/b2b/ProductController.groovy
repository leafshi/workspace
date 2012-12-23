package org.leaf.eos2.b2b

class ProductController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 30,  100)
        render view : '/b2b/product/list', model : [productInstanceList: Product.list(params), productInstanceTotal: Product.count()]
    }

    def create = {
        def productInstance = new Product()
        productInstance.properties = params
        render view : '/b2b/product/create', model : [productInstance: productInstance]
    }

    def save = {
        def productInstance = new Product(params)
        if (!productInstance.hasErrors() && productInstance.save()) {
            flash.message = "product.created"
            flash.args = [productInstance.id]
            flash.defaultMessage = "Product ${productInstance.id} created"
            redirect(action: "show", id: productInstance.id)
        }
        else {
            render(view: "/b2b/product/create", model: [productInstance: productInstance])
        }
    }

    def show = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "product.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Product not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/product/show', model : [productInstance: productInstance]
        }
    }

    def edit = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "product.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Product not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            render view : '/b2b/product/edit', model : [productInstance: productInstance]
        }
    }

    def update = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productInstance.version > version) {
                    
                    productInstance.errors.rejectValue("version", "product.optimistic.locking.failure", "Another user has updated this Product while you were editing")
                    render(view: "/b2b/product/edit", model: [productInstance: productInstance])
                    return
                }
            }
            productInstance.properties = params
            if (!productInstance.hasErrors() && productInstance.save()) {
                flash.message = "product.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Product ${params.id} updated"
                redirect(action: "show", id: productInstance.id)
            }
            else {
                render(view: "/b2b/product/edit", model: [productInstance: productInstance])
            }
        }
        else {
            flash.message = "product.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Product not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            try {
                productInstance.delete()
                flash.message = "product.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Product ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "product.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Product ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "product.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Product not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
