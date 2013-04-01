package org.leaf.eos2.news

class ReaderController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST"]

    def create = {
        def readerInstance = new Reader()
        readerInstance.properties = params
        render view :'/news/reader/create', model : [readerInstance: readerInstance]
    }

    def save = {
        def readerInstance = new Reader(params)
        if (!readerInstance.hasErrors() && readerInstance.save()) {
            flash.message = "reader.created"
            flash.args = [readerInstance.id]
            flash.defaultMessage = "Reader ${readerInstance.id} created"
            redirect(controller : "entity", action: "show", id: readerInstance?.entity?.id)
        }
        else {
            render(view: "/news/reader/create", model: [readerInstance: readerInstance])
        }
    }

}
