package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.admin.Group
import org.leaf.eos2.admin.ShareRole

import org.leaf.eos2.wf.Workflow
import org.leaf.eos2.wf.WorkflowStep
import org.leaf.eos2.wf.WorkflowAction
import org.leaf.eos2.wf.WorkflowHistory


class DepartmentService {

    static transactional = false

	/*
		规则：name=recordtype.serialNumber_region.name_branch.name_审批流
		用途：所有经销商，及一次性定价客户
	*/
    def initWorkflow(Department branch) {
        //获取记录类型
        def recordTypes = RecordType.findAllByDomainAndIsActive('salesOrder', true)
        if(!recordTypes){
        	log.error("init workflow for department ${branch} error, no recordTypes")
        	return
        }
		//取得办事处用户组
        def branchGroup = Group.findByDepartment(branch)
        if(!branchGroup){
        	log.error("init workflow for department ${branch} error, no branchGroup")
        	return
        }
		//取得办事处所属大区
        def region = Department.get(branch.parentDept.id)
        if(!region){
        	log.error("init workflow for department ${branch} error, no region")
        	return
        }
        //取得大区用户组
        def regionGroup = Group.findByName(region.name)
        if(!regionGroup){
        	log.error("init workflow for department ${branch} error, no regionGroup")
        	return
        }
        //取得商务部
        def commercial = Department.get(region.parentDept.id) 
        if(!commercial){
        	log.error("init workflow for department ${branch} error, no commercial")
        	return
        }
        //取得商务部用户组
        def commercialGroup = Group.findByDepartment(commercial)
        if(!commercialGroup){
        	log.error("init workflow for department ${branch} error, no commercialGroup")
        	return
        }
		
		//为每个单别创建不同的工作流
		Workflow.withTransaction{ status ->
			try {
				for(recordType in recordTypes){
					//创建工作流
					def workflowInstance = new Workflow(
						name: "${recordType.serialNumber}_${region.name}_${branch.name}_审批流"
						, domain :'salesOrder'
						, recordType:recordType
						, department : branch
						, isActive:true
						, description :'该工作流适用于普通经销商和一定性定价经销商'
					).save()
					//创建工作流步骤
					def step_10 = new WorkflowStep(workflow:workflowInstance, serialNumber : '10', name : '草稿', lockRecord:false, isBegin:true, isEnd :false).save()
					def step_11 = new WorkflowStep(workflow:workflowInstance, serialNumber : '11', name : '未通过', lockRecord:false, isBegin:false, isEnd :false).save()
			
					def step_20 = new WorkflowStep(workflow:workflowInstance, serialNumber : '20', name : '待审批（办事处）', assignee : branchGroup, lockRecord:true, isBegin:false, isEnd :false).save()

					def step_30 = new WorkflowStep(workflow:workflowInstance, serialNumber : '30', name : '待审批（商务部）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save()
					def step_90 = new WorkflowStep(workflow:workflowInstance, serialNumber : '90', name : '终止', lockRecord:true, isBegin:false, isEnd :true).save()
					def step_91 = new WorkflowStep(workflow:workflowInstance, serialNumber : '91', name : '审批通过', lockRecord:true, isBegin:false, isEnd :true).save()
					def step_92 = new WorkflowStep(workflow:workflowInstance, serialNumber : '92', name : '审批未通过', lockRecord:true, isBegin:false, isEnd :true).save()
			
					//创建步骤动作
					def action_01_01 = new WorkflowAction(belognsToStep : step_10, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save()
					def action_01_02 = new WorkflowAction(belognsToStep : step_10, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save()

					def action_11_01 = new WorkflowAction(belognsToStep : step_11, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save()
					def action_11_02 = new WorkflowAction(belognsToStep : step_11, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save()
			
					def action_20_01 = new WorkflowAction(belognsToStep : step_20, serialNumber : '01', nextStep : step_30, name : '通过', needDescription:false).save()
					def action_20_02 = new WorkflowAction(belognsToStep : step_20, serialNumber : '02', nextStep : step_11, name : '拒绝', needDescription:true).save()
			
					def action_30_01 = new WorkflowAction(belognsToStep : step_30, serialNumber : '01', nextStep : step_91, name : '通过', needDescription:false).save()
					def action_30_02 = new WorkflowAction(belognsToStep : step_30, serialNumber : '02', nextStep : step_92, name : '拒绝', needDescription:false).save()
				}
			} catch(e) { 
				log.error("init workflow for department ${branch} error:${e}")
				status.setRollbackOnly() 
				
			}
        }
    }
	/*
		规则：name=recordtype.serialNumber_region.name_branch.name_审批流
		用途：OEM
		说明：这个工作流只有一个，单别为222
	*/
    def initWorkflow2(Department commercial) {
        //获取记录类型
        def recordTypes = RecordType.findAllByDomainAndSerialNumberAndIsActive('salesOrder', '222', true)
        if(!recordTypes){
        	log.error("init workflow 222 for department ${commercial} error, no recordTypes")
        	return
        }
        //取得商务部用户组
        def commercialGroup = Group.findByDepartment(commercial)
        if(!commercialGroup){
        	log.error("init workflow for department ${commercial} error, no commercialGroup")
        	return
        }

		//为每个单别创建不同的工作流
		Workflow.withTransaction{ status ->
			try {
				for(recordType in recordTypes){
					//创建工作流
					def workflow = new Workflow(
						name: "${recordType.serialNumber}_${commercial.name}_审批流"
						, domain :'salesOrder'
						, recordType:recordType
						, department : commercial
						, isActive:true
						, description :'该工作流只适用于OEM'
					).save()
					//创建工作流步骤
					def step_10 = new WorkflowStep(workflow:workflow, serialNumber : '10', name : '草稿', lockRecord:false, isBegin:true, isEnd :false).save()
					def step_11 = new WorkflowStep(workflow:workflow, serialNumber : '11', name : '未通过', lockRecord:false, isBegin:false, isEnd :false).save()
			
					def step_20 = new WorkflowStep(workflow:workflow, serialNumber : '20', name : '待审批（办事处）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save()

					def step_30 = new WorkflowStep(workflow:workflow, serialNumber : '30', name : '待审批（商务部）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save()
			
					def step_90 = new WorkflowStep(workflow:workflow, serialNumber : '90', name : '终止', lockRecord:true, isBegin:false, isEnd :true).save()
					def step_91 = new WorkflowStep(workflow:workflow, serialNumber : '91', name : '审批通过', lockRecord:true, isBegin:false, isEnd :true).save()
					def step_92 = new WorkflowStep(workflow:workflow, serialNumber : '92', name : '审批未通过', lockRecord:true, isBegin:false, isEnd :true).save()
			
					//创建步骤动作
					def action_01_01 = new WorkflowAction(belognsToStep : step_10, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save()
					def action_01_02 = new WorkflowAction(belognsToStep : step_10, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save()

					def action_11_01 = new WorkflowAction(belognsToStep : step_11, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save()
					def action_11_02 = new WorkflowAction(belognsToStep : step_11, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save()
			
					def action_20_01 = new WorkflowAction(belognsToStep : step_20, serialNumber : '01', nextStep : step_30, name : '通过', needDescription:false).save()
					def action_20_02 = new WorkflowAction(belognsToStep : step_20, serialNumber : '02', nextStep : step_11, name : '拒绝', needDescription:true).save()
			
					def action_30_01 = new WorkflowAction(belognsToStep : step_30, serialNumber : '01', nextStep : step_91, name : '通过', needDescription:false).save()
					def action_30_02 = new WorkflowAction(belognsToStep : step_30, serialNumber : '02', nextStep : step_92, name : '拒绝', needDescription:false).save()
				}
			}catch(e){
				log.error("init workflow 222 for department ${commercial} error, ${e}")
				status.setRollbackOnly()
			}
		}
    }
    
    def delete(departmentInstance){
    
    	def result = false
    	
    	//检查是否有经销商
    	def dealerInDepartment = Dealer.countByDepartment(departmentInstance)
    	if(dealerInDepartment > 0){
    		departmentInstance.errors.rejectValue('id', 'department.delete.hasDealer')
    		log.error("delete department ${departmentInstance} error : department.delete.hasDealer")
    	}
    	
    	if(!result){
    		return result
    	}
    
    	Workflow.withTransaction{ status ->
    		try{
    			//delete workflow
    			Workflow.findAllByDepartment(departmentInstance).each{ workflowInstance ->
    				//delete action and history
    				WorkflowStep.findAllByWorkflow(workflowInstance).each{stepInstance ->
    					//delete action
    					WorkflowAction.findAllByBelognsToStep(stepInstance).each{actionInstance ->
    						actionInstance.delete(flush:true)
    					}
    					//delete history
    					WorkflowHistory.findAllByStep(stepInstance).each{ historyInstance ->
    						historyInstance.delete(flush:true)
    					}
    				}
    				//delete step
    				WorkflowStep.findAllByWorkflow(workflowInstance).each{stepInstance ->
    					//delete step
    					stepInstance.delete(flush:true)
    				}
    				//delete workflow
    				workflowInstance.delete(flush:true)
    			}

    			//delete group
    			Group.findAllByDepartment(departmentInstance).each{ groupInstance ->
					//delete shareRole
					ShareRole.findAllByGroup(groupInstance).each{ shareRoleInstance ->
						shareRoleInstance.delete(flush:true)
					}
					//delete group
    				groupInstance.delete(flush:true)
    			}
    			    			
    			//delete department
    			departmentInstance.delete(flush:true)
    			result = true
    		}catch(e){
    			log.error("delete department ${departmentInstance} error : ${e}")
    			status.setRollbackOnly()
    		}
    	}
    	return result
    }

}
