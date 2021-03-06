package org.leaf.eos2.ws

//leaf
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

import org.hibernate.FetchMode as FM
import grails.converters.JSON
import org.hibernate.criterion.CriteriaSpecification

import org.leaf.eos2.wf.WorkflowHistory
import org.leaf.eos2.wf.WorkflowAction
import org.leaf.eos2.wf.WorkflowStep

import org.leaf.eos2.b2b.SalesOrder
import org.leaf.eos2.shiro.User
import org.leaf.eos2.admin.Group

import org.springframework.transaction.annotation.*

class ApprovalSalesOrderFromERPService {

    //商务部订单审批
    String approval(String id, String serialNumber, String action, String date){
        def code = '0'
		
        try{
            WorkflowHistory.withTransaction{ status ->
                code = checkWorkFlowStep(id, serialNumber, action, date)
                if(code != '0'){
                    status.setRollbackOnly()
                }
            }
        }catch(e){
            code = '-99'
            log.error "approval.error=${e}"
        }

        return code
    }
	
    def checkWorkFlowStep(id, serialNumber, action, date) {
    	
        //check id
        def salesOrderId = getSalesOrderId(id)
        if(salesOrderId == -1L){
            return '-1'
        }
        
        //check id and serialNumber
        if(checkIdAndSerailNumber(salesOrderId, serialNumber) != true){
        	return '-10'
        }
        //check action
        def fmapAccessAction = ['Y':'Y', 'N':'N']
        if(fmapAccessAction.containsKey(action) != true){
            return '-2'
        }
        //check approval date
        if(date == null){
            return '-9'
        }
        //init params

        def workflowHistory = getCurrentHistory(salesOrderId, action)

        if(workflowHistory == null){
            return '-3'
        }

        def historyId = workflowHistory[0]//workflow history's id
        def actionId = workflowHistory[1]//workflow belongsTo step's action's id
        def version = workflowHistory[2]//workflow history's version
        def objectName = 'salesOrder'
        def objectId = salesOrderId
        def description = 'approval from erp'


		if(checkAssignee(actionId) != true){
			return '-4'
		}
		if(checkHistory(historyId, version) != true){
			return '-5'
		}
        if(updateSalesOrderApprovalDate(objectId, date) != true){
            return '-8'
        }
		def nextStep = getNextStep(actionId)
		if(nextStep != null){
			if(createNextStep(nextStep, objectName, objectId) == true){
				if( finishCurrentStep(historyId, description) == true){
                    return '0'
                }else{
                    return '-6'
                }
			}else{
                return '-7'
            }
		}else{
			if( finishCurrentStep(historyId, description) == true){
                return '0'
            }else{
                return '-6'
            }
		}
        
    }
    
    //取订单id
    @Transactional(readOnly = true)
    def getSalesOrderId(id){
    	def salesOrderId = SalesOrder.withCriteria(uniqueResult:true){
        	projections {
				property('id')
			}
			eq("serialNumber", id)
			maxResults(1)
        }
        return salesOrderId ? salesOrderId.toLong() : -1L
    }


    //取待审批商务部的workflow history
    def getCurrentHistory(salesOrderId, action){
    	log.info("getCurrentHistory")
		return WorkflowHistory.withCriteria(uniqueResult:true){

            createAlias 'step', 'step', CriteriaSpecification.LEFT_JOIN
            createAlias 'step.actions', 'action', CriteriaSpecification.LEFT_JOIN
            projections{
                property("id", "historyId")
                property("action.id", "actionId")
                property("version", "version")
            }
            
            fetchMode "step", FM.JOIN
            fetchMode "step.actions", FM.JOIN

			eq("objectName", 'salesOrder')
			eq("objectId", salesOrderId)
            eq("status", '进行中')
            eq("step.name", '待审批（商务部）')
            eq("action.name", (action == 'Y')?'通过':'拒绝')
		}
	}
    
    //检查分配人,如果分配人为空，则判断是否是当前用户，否则判断当前用户是否在分配的组中
    
	def checkAssignee(actionId){
		def result = false
        //get user, default commercial
        def currentUser = User.findByUsername("C3002")
		//get action belongsto step's id
		def currentStepId = WorkflowAction.get(actionId).belognsToStep?.id
        //get step assignee's id
		def assigneeId = WorkflowStep.get(currentStepId)?.assignee?.id
        //判断用户是否在分配的组中
		if(assigneeId != null){
			Group.get(assigneeId).users.each{
				if(it.id == currentUser.id) {
					result = true
				}
			}
		}
		return result
	}

	//检查历史是否已完成或者版本不一致
	
	def checkHistory(historyId, version){
		def result = true
		def history = WorkflowHistory.get(historyId)
		
		if(history.version != version.toLong() || history.status == '已完成'){
			result = false
		}
		return result
	}
	
	//检查id与serailNumber是否匹配
	def checkIdAndSerailNumber(salesOrderId, serialNumber){
		def result = false
		
        def salesOrderInstance = SalesOrder.get(salesOrderId.toLong())
        
        result = (salesOrderInstance?.erpSerialNumber?:'' == serialNumber)?true:false
        
        return result
		
	}

    //获取下一步
    
	def getNextStep(actionId){
		def nextStepId = WorkflowAction.get(actionId)?.nextStep?.id
		def nextStep = null
		if(nextStepId != null){
			nextStep = WorkflowStep.get(nextStepId)
		}
		return nextStep
	}
	//创建下一步
	
	def createNextStep(nextStep, objectName, objectId){
        //get user
        def currentUserId = User.withCriteria(uniqueResult:true){
            projections{
                property("id")
            }
            eq("username", "C3002")
        }
        def history = new WorkflowHistory(
			  step : nextStep
			, objectName : objectName
			, objectId : objectId
			, status : (nextStep.isEnd)?'已完成':'进行中'
			, description : ''
			, dateCreated : new Date()
			, lastUpdated : new Date()
		)

        BindDynamicMethod mybind = new BindDynamicMethod()
        def mymap = ['createdBy.id' : currentUserId, 'lastModifiedBy.id' : currentUserId]
        def myargs =  [history, mymap]
        mybind.invoke(history, 'bind', (Object[]) myargs)
        history.validate()
        history.save(flush:true)
		return !history.hasErrors()
	}
	//标记当前history为已完成
	
	def finishCurrentStep(historyId, description){
        def result = false

		def history = WorkflowHistory.get(historyId)
            history.status = '已完成'
            history.description = description    
        history.validate()
        history.save(flush:true)
        return !history.hasErrors()
	}

    //更新审批日期
    
    def updateSalesOrderApprovalDate(objectId, date){
        def salesOrder = SalesOrder.get(objectId)
            salesOrder.effectiveDate = Date.parse("yyyy-MM-dd", date)//.format('yyyy-MM-dd')
            salesOrder.save(flush:true)
        return !salesOrder.hasErrors()
    }
}