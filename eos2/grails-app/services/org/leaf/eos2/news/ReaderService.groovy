package org.leaf.eos2.news

class ReaderService {

	static transactional = false

    def delete(readerInstance) {
    	def result = false;
    	
    	
    	Reader.withTransaction{ status ->
    		try{
    			readerInstance.delete(flush:true);
			log.info("delete reader instance ${readerInstance} success")
    			result = true
    		}catch(e){
				log.error("delete reader instance ${readerInstance} error, ${e}")
    			status.setRollbackOnly()
    		}
    	}
		return result;
    }
}
