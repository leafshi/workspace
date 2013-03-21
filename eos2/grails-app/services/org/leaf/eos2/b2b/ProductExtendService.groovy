package org.leaf.eos2.b2b

import org.springframework.transaction.annotation.*

class ProductExtendService {

	//显示产品BOM
	@Transactional(readOnly = true)
    def showBOM(Long productId) { 
    	def bomId = Bom.withCriteria(uniqueResult:true){
    		projections{
    			property("id")
    		}
    		eq("product.id", productId)
    	};
    	
    	bomId = bomId?: -1L;
    	return bomId;
    }
    
}
