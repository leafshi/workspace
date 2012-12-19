package org.leaf.eos2.birt

class Report {

    String name
    String author
    String title
    String createdBy
    String description
    String comment
    String units
    String helpGuide
    String file
    String fullfile
    
    static hasMany = [ reportParameters : ReportParameter ]
    
    static constraints = {
		name(nullable:false, blank:false, unique : true, maxSize: 200)
		author(nullable:true, blank:true, maxSize : 200)
		title(nullable:true, blank:true, maxSize : 200)
		createdBy(nullable:true, blank:true, maxSize : 200)
		description(nullable:true, blank:true, maxSize : 200)
		comment(nullable:true, blank:true, maxSize : 200)
		units(nullable:true, blank:true, maxSize : 200)
		helpGuide(nullable:true, blank:true, maxSize : 200)
		file(nullable:true, blank:true, maxSize : 200)
		fullfile(nullable:true, blank:true, maxSize : 200)
    }
    
    static mapping = {
        table 'BIRT_REPORT'
        file column : '_file'
    }

}
