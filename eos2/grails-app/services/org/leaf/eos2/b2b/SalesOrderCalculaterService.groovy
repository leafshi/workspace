package org.leaf.eos2.b2b

class SalesOrderCalculaterService {


	/*
		思路：
			计算单身
				计算主附件定价
				计算非主附件定价
			计算合计
	*/
    def calculate(salesOrderInstance) {
    	log.info("开始计算")
    
    	//合计标准金额
    	def totalAmount = new BigDecimal(0);
    	//合计特价金额
    	def totalSpecialAmount = new BigDecimal(0);
    	//合计数量
    	def totalQuantity = new BigDecimal(0);
    	
    	salesOrderInstance.salesOrderDetails.each{ salesOrderDetailInstance ->
    		log.info("salesOrderDetailInstance=${salesOrderDetailInstance}")
    		//判断是否主附件定价
    		if(salesOrderDetailInstance.openDetail){
    			//salesOrderDetailInstance = 
    				calculateDetailWithDetail(salesOrderDetailInstance);
    		}else{
    			//salesOrderDetailInstance = 
    				calculateDetailWithNoDetail(salesOrderDetailInstance);
    		}
    		//累计标准金额
    		totalAmount += salesOrderDetailInstance.amount;
    		//累计特价金额
    		totalSpecialAmount += salesOrderDetailInstance.specialAmount;
    		//累计数量
    		totalQuantity +=  salesOrderDetailInstance.quantity;
    	}
    	//四舍五入，在累计的时候，不会四舍五入
    	totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    	totalSpecialAmount = totalSpecialAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    	totalQuantity = totalQuantity.setScale(0, BigDecimal.ROUND_HALF_UP);
    	
    	//检查标准金额
    	if(totalAmount != salesOrderInstance.amount){
    		log.error("合计标准金额不正确，客户端=${salesOrderInstance.amount}，服务器=${totalAmount}")
    		salesOrderInstance.amount = totalAmount;
    	}
    	//检查特价金额
    	if(totalSpecialAmount != salesOrderInstance.specialAmount){
    		log.error("合计特价金额不正确，客户端=${salesOrderInstance.specialAmount}，服务器=${totalSpecialAmount}")
    		salesOrderInstance.specialAmount = totalSpecialAmount;
    	}
    	//检查数量
    	if(totalQuantity != salesOrderInstance.quantity){
    		log.error("合计数量不正确，客户端=${salesOrderInstance.quantity}，服务器=${totalQuantity}")
    		salesOrderInstance.quantity = totalQuantity;
    	}
		//return salesOrderInstance;
    }
    
    //计算主附件定价单身
    def calculateDetailWithDetail(salesOrderDetailInstance){
    
    	//子单身合计面价金额
    	def totalFullAmount = new BigDecimal(0);
    	//子单身合计标准金额
    	def totalAmount = new BigDecimal(0);
    	//子单身合计特价金额
    	def totalSpecialAmount = new BigDecimal(0);
    	
    	salesOrderDetailInstance.salesOrderDetailDetails.each{ SalesOrderDetailDetailInstance ->
    		//数量 = 单身.数量 * 组成用量 / 工时底数
    		def quantity = new BigDecimal(0);
    			quantity = salesOrderDetailInstance.quantity * SalesOrderDetailDetailInstance.dosage / SalesOrderDetailDetailInstance.quota;
    			quantity = quantity.setScale(2, BigDecimal.ROUND_HALF_UP);
    			
    		if(quantity != SalesOrderDetailDetailInstance.quantity){
    			log.error("子单身数量不正确，客户端=${SalesOrderDetailDetailInstance.quantity}，服务器=${quantity}");
    			SalesOrderDetailDetailInstance.quantity = quantity;
    		}
    		
    		//面价金额 = 数量 * 单价
    		def fullAmount = new BigDecimal(0);
    			fullAmount = SalesOrderDetailDetailInstance.price * SalesOrderDetailDetailInstance.quantity;
    			fullAmount = fullAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    			
    		if(fullAmount != SalesOrderDetailDetailInstance.fullAmount){
    			log.error("子单身面价金额不正确，客户端=${SalesOrderDetailDetailInstance.fullAmount}，服务器=${fullAmount}");
    			SalesOrderDetailDetailInstance.fullAmount = fullAmount;
    		}
    		totalFullAmount += fullAmount;
    		
    		//标准金额 = 面价金额 * 标准折扣
    		def amount = new BigDecimal(0);
    			amount = SalesOrderDetailDetailInstance.fullAmount * SalesOrderDetailDetailInstance.discount;
    			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    			
    		if(amount != SalesOrderDetailDetailInstance.amount){
    			log.error("子单身标准金额不正确，客户端=${SalesOrderDetailDetailInstance.amount}，服务器=${amount}");
    			SalesOrderDetailDetailInstance.amount = amount;
    		}
    		totalAmount += amount;
    		
    		//特价金额 = 标准金额 * 特价折扣
    		def specialAmount = new BigDecimal(0);
    			specialAmount = SalesOrderDetailDetailInstance.amount * SalesOrderDetailDetailInstance.specialDiscount;
    			specialAmount = specialAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    			
    		if(specialAmount != SalesOrderDetailDetailInstance.specialAmount){
    			log.error("子单身特价金额不正确，客户端=${SalesOrderDetailDetailInstance.specialAmount}，服务器=${specialAmount}");
    			SalesOrderDetailDetailInstance.specialAmount = specialAmount;
    		}
    		totalSpecialAmount += specialAmount;
    		
    		//最终金额 = 标准金额 - 特价金额
    		def finalAmount = new BigDecimal(0);
    			finalAmount = SalesOrderDetailDetailInstance.amount - SalesOrderDetailDetailInstance.specialAmount;
    			finalAmount = finalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    			
    		if(finalAmount != SalesOrderDetailDetailInstance.finalAmount){
    			log.error("子单身特价金额不正确，客户端=${SalesOrderDetailDetailInstance.finalAmount}，服务器=${finalAmount}");
    			SalesOrderDetailDetailInstance.finalAmount = finalAmount;
    		}
    	}
    	
    	//面价金额 = 子单身合计面价金额
    	totalFullAmount = totalFullAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(totalFullAmount != salesOrderDetailInstance.fullAmount){
			log.error("单身面价金额不正确，客户端=${salesOrderDetailInstance.fullAmount}，服务器=${totalFullAmount}");
			salesOrderDetailInstance.fullAmount = totalFullAmount;
		}
		
		//标准金额 = 子单身合计标准金额
		totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(totalAmount != salesOrderDetailInstance.amount){
			log.error("单身标准金额不正确，客户端=${salesOrderDetailInstance.amount}，服务器=${totalAmount}");
			salesOrderDetailInstance.amount = totalAmount;
		}
    	
    	//特价金额 = 子单身合计特价金额
		totalSpecialAmount = totalSpecialAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(totalSpecialAmount != salesOrderDetailInstance.specialAmount){
			log.error("单身特价金额不正确，客户端=${salesOrderDetailInstance.specialAmount}，服务器=${totalSpecialAmount}");
			salesOrderDetailInstance.specialAmount = totalSpecialAmount;
		}
		
		//最终金额 = 标准金额 - 特价金额
		def finalAmount = new BigDecimal(0);
			finalAmount = salesOrderDetailInstance.amount - salesOrderDetailInstance.specialAmount;
			finalAmount = finalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(finalAmount != salesOrderDetailInstance.finalAmount){
			log.error("单身最终金额不正确，客户端=${salesOrderDetailInstance.finalAmount}，服务器=${finalAmount}");
			salesOrderDetailInstance.finalAmount = finalAmount;
		}
		
		
		//特价折扣 = 特价金额 / 标准金额
		def specialDiscount = new BigDecimal(0);
			specialDiscount = salesOrderDetailInstance.specialAmount / salesOrderDetailInstance.amount;
			specialDiscount = specialDiscount.setScale(4, BigDecimal.ROUND_HALF_UP);
		if(specialDiscount != salesOrderDetailInstance.specialDiscount){
			log.error("单身特价折扣不正确，客户端=${salesOrderDetailInstance.specialDiscount}，服务器=${specialDiscount}");
			salesOrderDetailInstance.specialDiscount = specialDiscount;
		}
		
		//最终折扣 = 最终金额 / 面价金额
		def finalDiscount = new BigDecimal(0);
			finalDiscount = salesOrderDetailInstance.finalAmount / salesOrderDetailInstance.fullAmount;
			finalDiscount = finalDiscount.setScale(4, BigDecimal.ROUND_HALF_UP);
			
		if(finalDiscount != salesOrderDetailInstance.finalDiscount){
			log.error("单身特价折扣不正确，客户端=${salesOrderDetailInstance.finalDiscount}，服务器=${finalDiscount}");
			salesOrderDetailInstance.finalDiscount = finalDiscount;
		}
    
		//最终售价=最终金额 / 数量
		def finalPrice = new BigDecimal(0);
			finalPrice = salesOrderDetailInstance.finalAmount / salesOrderDetailInstance.quantity;
			finalPrice = finalPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		if(finalPrice != salesOrderDetailInstance.finalPrice){
    		log.error("最终售价不正确，客户端=${salesOrderDetailInstance.finalAmount}，服务器=${finalAmount}")
			salesOrderDetailInstance.finalPrice = finalPrice;
		}		
    	
    }
    
    //计算非主附件定价单身及子单身
    def calculateDetailWithNoDetail(salesOrderDetailInstance){
		//单价		从产品信息取过来，不变
		//标准折扣	从产品属性分类取来，不变
		//特价折扣	从产品仓管分类取来，不变
		//最终折扣	添加时已经计算好了，不变

		//面价金额=单价*数量
		def fullAmount = new BigDecimal(0);
			fullAmount = salesOrderDetailInstance.price * salesOrderDetailInstance.quantity;
			fullAmount = fullAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			
		if(fullAmount != salesOrderDetailInstance.fullAmount){
    		log.error("面价金额不正确，客户端=${salesOrderDetailInstance.fullAmount}，服务器=${fullAmount}")
			salesOrderDetailInstance.fullAmount = fullAmount;
		}
		
		//标准金额=面价金额*标准折扣
		def amount = new BigDecimal(0);
			amount = salesOrderDetailInstance.fullAmount * salesOrderDetailInstance.discount;
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(amount != salesOrderDetailInstance.amount){
    		log.error("标准金额不正确，客户端=${salesOrderDetailInstance.amount}，服务器=${amount}")
			salesOrderDetailInstance.amount = amount;
		}
				
		//特价金额=标准金额*特价折扣
		def specialAmount = new BigDecimal(0);
			specialAmount = salesOrderDetailInstance.amount * salesOrderDetailInstance.specialDiscount;
			specialAmount = specialAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(specialAmount != salesOrderDetailInstance.specialAmount){
    		log.error("特价金额不正确，客户端=${salesOrderDetailInstance.specialAmount}，服务器=${specialAmount}")
			salesOrderDetailInstance.specialAmount = specialAmount;
		}
		
		//最终金额=标准金额-特价金额
		def finalAmount = new BigDecimal(0);
			finalAmount = salesOrderDetailInstance.amount - salesOrderDetailInstance.specialAmount;
			finalAmount = finalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		if(finalAmount != salesOrderDetailInstance.finalAmount){
    		log.error("最终金额不正确，客户端=${salesOrderDetailInstance.finalAmount}，服务器=${finalAmount}")					
			salesOrderDetailInstance.finalAmount = finalAmount;
		} 	

		//最终售价=最终金额 / 数量
		def finalPrice = new BigDecimal(0);
			finalPrice = salesOrderDetailInstance.finalAmount / salesOrderDetailInstance.quantity;
			finalPrice = finalPrice.setScale(6, BigDecimal.ROUND_HALF_UP);
		if(finalPrice != salesOrderDetailInstance.finalPrice){
    		log.error("最终售价不正确，客户端=${salesOrderDetailInstance.finalAmount}，服务器=${finalAmount}")
			salesOrderDetailInstance.finalPrice = finalPrice;
		}		
    	
    }
    
    //计算最小交货日期
    def firstDeliveryDate(deliveryCycle){
    	
    	def firstSaturday, firstWorkDay, today = new Date(), firstDeliveryDate;
    	def holidays = 0;
    
    	//计算第一天工作日和第一个星期六
    	switch(today.day){
    		case 0:
    			firstWorkDay = today.plus(1);
    			firstSaturday = today.plus(6);
    			break;
    		case 1:
    		case 2:
    		case 3:
    		case 4:
    		case 5:
    			firstWorkDay = today;
    			firstSaturday = today.plus(6 - today.day);
    			break;
    		case 6:
    			firstWorkDay = today.plus(2);
    			firstSaturday = today.plus(7);
    			break;
    	}
    	//最小交货日期=第一个工作日+交期
    	firstDeliveryDate = firstWorkDay.plus(deliveryCycle);
    	
    	//当最小交货日期 大于 第一个星期六，加两天
    	while(firstDeliveryDate > firstSaturday){
    		firstDeliveryDate = firstDeliveryDate.plus(2)
    		firstSaturday = firstSaturday.plus(7)
    	}
    	//遇到星期六，星期天，顺延到下个星期一
		if(firstDeliveryDate.day == 0){//如果是星期天 + 1天
			firstDeliveryDate = firstDeliveryDate.next()
		}else if(firstDeliveryDate.day == 6){//如果是星期六 + 2天
			firstDeliveryDate = firstDeliveryDate.plus(2)
		}
		
    	return firstDeliveryDate
    	
    }

}
