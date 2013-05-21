import org.leaf.etl.SyncJob

class BootStrap {

    def init = { servletContext ->
    /*
    	new SyncJob(name:'Industry', source:'ERP', target:'EOS', priority:1, scriptName: 'SYNC_JOB_INDUSTRY').save(flush:true)
    	new SyncJob(name:'Department', source:'ERP', target:'EOS', priority:2, scriptName: 'SYNC_JOB_DEPARTMENT').save(flush:true)
    	new SyncJob(name:'Staff', source:'ERP', target:'EOS', priority:3, scriptName: 'SYNC_JOB_STAFF').save(flush:true)
    	new SyncJob(name:'Category', source:'ERP', target:'EOS', priority:4, scriptName: 'SYNC_JOB_CATEGORY').save(flush:true)
    	new SyncJob(name:'Product', source:'ERP', target:'EOS', priority:5, scriptName: 'SYNC_JOB_PRODUCT').save(flush:true)
    	new SyncJob(name:'Bom', source:'ERP', target:'EOS', priority:6, scriptName: 'SYNC_JOB_BOM').save(flush:true)
    */
    }
    def destroy = {
    }
}