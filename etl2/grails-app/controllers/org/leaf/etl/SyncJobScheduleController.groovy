package org.leaf.etl

import org.springframework.dao.DataIntegrityViolationException

class SyncJobScheduleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [syncJobScheduleInstanceList: SyncJobSchedule.list(params), syncJobScheduleInstanceTotal: SyncJobSchedule.count()]
    }

    def create() {
        [syncJobScheduleInstance: new SyncJobSchedule(params)]
    }

    def save() {
        def syncJobScheduleInstance = new SyncJobSchedule(params)
        if (!syncJobScheduleInstance.save(flush: true)) {
            render(view: "create", model: [syncJobScheduleInstance: syncJobScheduleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), syncJobScheduleInstance.id])
        redirect(action: "show", id: syncJobScheduleInstance.id)
    }

    def show(Long id) {
        def syncJobScheduleInstance = SyncJobSchedule.get(id)
        if (!syncJobScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "list")
            return
        }

        [syncJobScheduleInstance: syncJobScheduleInstance]
    }

    def edit(Long id) {
        def syncJobScheduleInstance = SyncJobSchedule.get(id)
        if (!syncJobScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "list")
            return
        }

        [syncJobScheduleInstance: syncJobScheduleInstance]
    }

    def update(Long id, Long version) {
        def syncJobScheduleInstance = SyncJobSchedule.get(id)
        if (!syncJobScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (syncJobScheduleInstance.version > version) {
                syncJobScheduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule')] as Object[],
                          "Another user has updated this SyncJobSchedule while you were editing")
                render(view: "edit", model: [syncJobScheduleInstance: syncJobScheduleInstance])
                return
            }
        }

        syncJobScheduleInstance.properties = params

        if (!syncJobScheduleInstance.save(flush: true)) {
            render(view: "edit", model: [syncJobScheduleInstance: syncJobScheduleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), syncJobScheduleInstance.id])
        redirect(action: "show", id: syncJobScheduleInstance.id)
    }

    def delete(Long id) {
        def syncJobScheduleInstance = SyncJobSchedule.get(id)
        if (!syncJobScheduleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "list")
            return
        }

        try {
            syncJobScheduleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule'), id])
            redirect(action: "show", id: id)
        }
    }
}
