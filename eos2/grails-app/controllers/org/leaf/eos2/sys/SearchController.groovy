package org.leaf.eos2.sys

class SearchController {

	def searchService

	//显示搜索页
    def index() { 
    	render view : '/sys/search/index'
    }
    
    def search ={
    	redirect(action: params.method, params: [term: params.term])
    }
        
    //搜索产品
    def searchProduct = {
    	render template : '/b2b/product/list', model : [productInstanceList : searchService.searchProduct(params.term)]
    }
    
    //搜索OA特价申请单
    def searchContract = {
    	render template : '/b2b/contract/list', model : [contractInstanceList : searchService.searchContract(params.term)]
    }

	//搜索客户订单
    def searchSalesOrder = {
    	render template : '/b2b/salesOrder/list', model : [salesOrderInstanceList : searchService.searchSalesOrder(params.term)]
    }
	//搜索用户
    def searchUser = {
    	render template : '/shiro/user/list', model : [userInstanceList : searchService.searchUser(params.term)]
    }
    
}