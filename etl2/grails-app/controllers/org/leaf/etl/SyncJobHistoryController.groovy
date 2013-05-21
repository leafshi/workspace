package org.leaf.etl

import org.springframework.dao.DataIntegrityViolationException

class SyncJobHistoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [syncJobHistoryInstanceList: SyncJobHistory.list(params), syncJobHistoryInstanceTotal: SyncJobHistory.count()]
    }

    def create() {
        [syncJobHistoryInstance: new SyncJobHistory(params)]
    }

    def save() {
        def syncJobHistoryInstance = new SyncJobHistory(params)
        if (!syncJobHistoryInstance.save(flush: true)) {
            render(view: "create", model: [syncJobHistoryInstance: syncJobHistoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), syncJobHistoryInstance.id])
        redirect(action: "show", id: syncJobHistoryInstance.id)
    }

    def show(Long id) {
        def syncJobHistoryInstance = SyncJobHistory.get(id)
        if (!syncJobHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "list")
            return
        }

        [syncJobHistoryInstance: syncJobHistoryInstance]
    }

    def edit(Long id) {
        def syncJobHistoryInstance = SyncJobHistory.get(id)
        if (!syncJobHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "list")
            return
        }

        [syncJobHistoryInstance: syncJobHistoryInstance]
    }

    def update(Long id, Long version) {
        def syncJobHistoryInstance = SyncJobHistory.get(id)
        if (!syncJobHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (syncJobHistoryInstance.version > version) {
                syncJobHistoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'syncJobHistory.label', default: 'SyncJobHistory')] as Object[],
                          "Another user has updated this SyncJobHistory while you were editing")
                render(view: "edit", model: [syncJobHistoryInstance: syncJobHistoryInstance])
                return
            }
        }

        syncJobHistoryInstance.properties = params

        if (!syncJobHistoryInstance.save(flush: true)) {
            render(view: "edit", model: [syncJobHistoryInstance: syncJobHistoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), syncJobHistoryInstance.id])
        redirect(action: "show", id: syncJobHistoryInstance.id)
    }

    def delete(Long id) {
        def syncJobHistoryInstance = SyncJobHistory.get(id)
        if (!syncJobHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "list")
            return
        }

        try {
            syncJobHistoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory'), id])
            redirect(action: "show", id: id)
        }
    }
}
