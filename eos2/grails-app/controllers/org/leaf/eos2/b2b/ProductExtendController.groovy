package org.leaf.eos2.b2b

class ProductExtendController {

	def productExtendService
	
	def showBom = {
		def productId = params.int("id").toLong();
		
		def bomId = productExtendService.showBOM(productId);
		
		render template : '/b2b/product/showBom', model : ['bomId':bomId]
	
	}

}
