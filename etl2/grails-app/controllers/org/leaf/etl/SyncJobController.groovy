package org.leaf.etl

import org.springframework.dao.DataIntegrityViolationException

class SyncJobController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [syncJobInstanceList: SyncJob.list(params), syncJobInstanceTotal: SyncJob.count()]
    }

    def create() {
        [syncJobInstance: new SyncJob(params)]
    }

    def save() {
        def syncJobInstance = new SyncJob(params)
        if (!syncJobInstance.save(flush: true)) {
            render(view: "create", model: [syncJobInstance: syncJobInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), syncJobInstance.id])
        redirect(action: "show", id: syncJobInstance.id)
    }

    def show(Long id) {
        def syncJobInstance = SyncJob.get(id)
        if (!syncJobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "list")
            return
        }

        [syncJobInstance: syncJobInstance]
    }

    def edit(Long id) {
        def syncJobInstance = SyncJob.get(id)
        if (!syncJobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "list")
            return
        }

        [syncJobInstance: syncJobInstance]
    }

    def update(Long id, Long version) {
        def syncJobInstance = SyncJob.get(id)
        if (!syncJobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (syncJobInstance.version > version) {
                syncJobInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'syncJob.label', default: 'SyncJob')] as Object[],
                          "Another user has updated this SyncJob while you were editing")
                render(view: "edit", model: [syncJobInstance: syncJobInstance])
                return
            }
        }

        syncJobInstance.properties = params

        if (!syncJobInstance.save(flush: true)) {
            render(view: "edit", model: [syncJobInstance: syncJobInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), syncJobInstance.id])
        redirect(action: "show", id: syncJobInstance.id)
    }

    def delete(Long id) {
        def syncJobInstance = SyncJob.get(id)
        if (!syncJobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "list")
            return
        }

        try {
            syncJobInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'syncJob.label', default: 'SyncJob'), id])
            redirect(action: "show", id: id)
        }
    }
}
