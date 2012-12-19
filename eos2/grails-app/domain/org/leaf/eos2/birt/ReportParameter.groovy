package org.leaf.eos2.birt

class ReportParameter {

	Report report
    String name
    String datatype
    String prompttext
    Boolean allowblank
    

    static belongsTo = [report: Report]

    static constraints = {
        report(nullable:false)
        name(nullable:false, blank:false, unique : 'report')
        datatype(nullable:false, maxSize:50)
        prompttext(nullable:true, maxSize: 200)
        allowblank(nullable:true)
    }
    
    static mapping = {
        table 'BIRT_REPORTPARAMETER'
    }
}
