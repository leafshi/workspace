package org.leaf.eos2.b2b

import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.RecordType

import org.leaf.eos2.wf.WorkflowDispatcherService
import org.leaf.eos2.wf.WorkflowHistory

class SalesOrder {

	def workflowDispatcherService
    
    def salesOrderTriggerService
    
    //字段列表
    RecordType recordType           //单别          |TC001
    String serialNumber             //单号          |TC002
    String erpSerialNumber			//ERP单号
    String accountSerialNumber      //客户单号      |TC004
    Date orderDate                  //单据日期      |TC003, TC039
    Dealer dealer                   //客户编号      |TC004
    String project                  //项目          |TC012
    Staff salesMan                  //业务人员      |TC006
    Date effectiveDate              //订单日期,生效日期
    String status                   //状态
    Industry industry               //订单行业      |TC205
    String address1                 //送货地址（一）|TC010
    String address2                 //送货地址（二）|TC011
    String description              //备注          |TC015
    BigDecimal quantity             //总数量        |TC031
    BigDecimal amount               //标准金额      |TC201
    BigDecimal specialAmount        //特价金额      |TC202
    User owner                      //所有人
    Date dateCreated                //创建日期
    Date lastUpdated                //上次修改日期
    User createdBy                  //创建人
    User lastModifiedBy             //上次修改人
        
    static hasMany = [salesOrderDetails : SalesOrderDetail]

    //约束条件
    static constraints = {
        recordType(nullable:false, blank:false)
        serialNumber(nullable:false, blank:false, maxSize:20, unique : true)
        erpSerialNumber(nullable:true, blank:false, maxSize:16)
        accountSerialNumber(nullable:true, blank:false, maxSize:40)
        orderDate(nullable:false)
        dealer(nullable:false)
        project(nullable:true, blank:true, maxSize:200)
        salesMan(nullable:false)
        effectiveDate(nullable:true)
        status(nullable:false, blank:false, maxSize:20)
        industry(nullable:false)
        address1(maxSize:144, nullable:true, blank:true)
        address2(maxSize:144, nullable:true, blank:true)
        description(maxSize:500, nullable:true, blank:true)
        quantity(nullable:false, scale : 3, min : new BigDecimal(1.000), , max : new BigDecimal(999999999.999))
        amount(nullable:false, scale : 2, min : new BigDecimal(0.01), max : new BigDecimal(999999999.99))
        specialAmount(nullable:false, scale : 2, min : new BigDecimal(0), max : new BigDecimal(999999999.99))
    }
    
    String toString() {
        return "$serialNumber"
    }
    
    //拉起工作流
	def afterInsert(){
        WorkflowHistory.withNewSession{
            def departmentId = Dealer.get(this.dealer.id).department.id
            workflowDispatcherService.checkWorkFlow('salesOrder', this.id, this.recordType.id, departmentId)
		}
	}

    //拉起出站消息
    def beforeUpdate(){
    	def current_status = this.status
    	def before_status = this.getPersistentValue('status')
    	if(current_status == '待审批（办事处）' && (before_status == '草稿' || before_status == '未通过') ){
    		salesOrderTriggerService.refreshDeliveryLimitation(this);
    	}
        else if(current_status == '待审批（商务部）' && before_status == '待审批（办事处）' ){
        	salesOrderTriggerService.createOutBoundMessage(this.id, this.owner.id);
        }
    }
    //ORM映射
    static mapping = {
        table 'B2B_SALESORDER'
        salesOrderDetails cascade:"persist,merge,save-update,all-delete-orphan", sort:"serialNumber", batchSize: 100, lazy : true
        quantity sqlType : "numeric", precision : 18, scale : 3
        amount sqlType : "numeric", precision : 18, scale : 2
        specialAmount sqlType : "numeric", precision : 18, scale : 2

    }
}
