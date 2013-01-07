package org.leaf.eos2.ws

import org.grails.cxf.utils.EndpointType

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


class InboundSalesOrderEndpoint {

    static expose = EndpointType.SIMPLE
    
    static excludes=[
        "checkWorkFlowStep"
        , "getSalesOrderId"
        , "getCurrentHistory"
        , "checkAssignee"
        , "checkHistory"
        , "getNextStep"
        , "createNextStep"
        , "finishCurrentStep"
        , "updateSalesOrderApprovalDate"
        , "checkIdAndSerailNumber"
    ]

    static fmapResult=[
          '0' : '审批成功'
        , '-1': '不是有效的订单号'
        , '-2': '不能认别的动作'
        , '-3': '未找到审批历史'
        , '-4': '您不是当前的任务分配人'
        , '-5': '另外一个用户更新了审批历史，请重试'
        , '-6': '结束当前审批历史失败'
        , '-7': '创建审批历史失败'
        , '-8': '更新审批日期失败'
        , '-9': '审批日期不能为NULL'
        , '-10': 'web单号与ERP单号不匹配'
    ]

    //商务部订单审批
    String approval(String id, String serialNumber, String action, String date){
    	log.info "id=${id}"
        log.info "serialNumber=${serialNumber}"
        log.info "action=${action}"
        log.info "date=${date}"
 
        def code = ''
        def error = ''
		
        try{
            WorkflowHistory.withTransaction{ status ->
            	//log.error("begin checkWorkFlowStep")
                code = checkWorkFlowStep(id, serialNumber, action, date)
                if(code != '0'){
                    status.setRollbackOnly()
                }
            }
            error = fmapResult.get(code)
        }catch(e){
            code = '-99'
            error = "${e}"
        }

        def outputXml = """
            <STD_OUT Origin="Leaf">
              <Service Name="SetData">
                <Status>${code}</Status>
                <Error>${error}</Error>
                <Data>
                  <DataSet Field="">
                    <Row Data="" />
                  </DataSet>
                </Data>
              </Service>
            </STD_OUT>
        """
        return outputXml
    }
	
    def checkWorkFlowStep(id, serialNumber, action, date) {
    	
        //check id
        def salesOrderId = getSalesOrderId(id)
        //log.error("salesOrderId=${salesOrderId}")
        if(salesOrderId == -1L){
            return '-1'
        }
        
        //check id and serialNumber
        if(checkIdAndSerailNumber(salesOrderId, id, serialNumber) != true){
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
        def description = 'approval form erp'


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
    	//log.error("getSalesOrderId, id=${id}")
    	def salesOrderId = SalesOrder.withCriteria(uniqueResult:true){
        	projections {
				property('id')
			}
			eq("serialNumber", id)
			maxResults(1)
        }
        //log.error("getSalesOrderId, salesOrderId=${salesOrderId ?: -1L}")
        return salesOrderId ?: -1L
    }


    //取待审批商务部的workflow history
    
    def getCurrentHistory(salesOrderId, action){
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
        def currentUser = User.findByUsername("3002_1")
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
	def checkIdAndSerailNumber(salesOrderId, id, serialNumber){
	
		def result = false
		
        def salesOrderInstance = SalesOrder.get(salesOrderId)
        
        result = (salesOrderInstance?.erpSerialNumber?:'' == serialNumber)?true:false
        //log.info("checkIdAndSerailNumber=${result}")
        
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
            eq("username", "3002_1")
        }
        log.info("user=${currentUserId}")
        def history = new WorkflowHistory(
			  step : nextStep
			, objectName : objectName
			, objectId : objectId
			, status : (nextStep.isEnd)?'已完成':'进行中'
			, description : ''
			//, createdBy : currentUserId
			//, lastModifiedBy : currentUserId
			, dateCreated : new Date()
			, lastUpdated : new Date()
		)

        BindDynamicMethod mybind = new BindDynamicMethod()
        def mymap = ['createdBy.id' : currentUserId, 'lastModifiedBy.id' : currentUserId]
        def myargs =  [history, mymap]
        mybind.invoke(history, 'bind', (Object[]) myargs)
        log.info("createNextStep.historyInstance=${history}")
        history.validate()
        history.save(flush:true)
        //log.info("createNextStep=${!history.hasErrors()}")
        /*
        if(history.hasErrors()) {
            history.errors.each {
                log.info("createNextStep.error=${it}")
            }
        }
        */
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
        log.info("finishCurrentStep=${!history.hasErrors()}")
        return !history.hasErrors()
	}

    //更新审批日期
    
    def updateSalesOrderApprovalDate(objectId, date){
        def salesOrder = SalesOrder.get(objectId)
            salesOrder.effectiveDate = Date.parse("yyyy-MM-dd", date)//.format('yyyy-MM-dd')

            salesOrder.save(flush:true)
        
        //log.info("updateSalesOrderApprovalDate=${!salesOrder.hasErrors()}")
        return !salesOrder.hasErrors()
    }

}

