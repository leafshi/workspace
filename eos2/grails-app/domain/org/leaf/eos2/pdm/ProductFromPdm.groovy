package org.leaf.eos2.pdm

class ProductFromPdm {

	String productSerialNumber
	
	Boolean working
	Boolean finished
	Boolean isOkay
	Boolean sentToPdm

	Date dateCreated
	Date lastUpdated

    static constraints = {
    	productSerialNumber(nullable:false, blank:false, unique : false, maxSize : 20)
    	working(nullable:false)
    	finished(nullable:false)
    	isOkay(nullalbe:false)
    	sentToPdm(nullable:false)
    }
    
    String toString() {
        return "$productSerialNumber"
    }
    
    static mapping = {
        table 'PRODUCT_FROM_PDM'
    }

}
