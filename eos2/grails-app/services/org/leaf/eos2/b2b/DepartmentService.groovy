package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.admin.Group

import org.leaf.eos2.wf.Workflow
import org.leaf.eos2.wf.WorkflowStep
import org.leaf.eos2.wf.WorkflowAction

class DepartmentService {

    static transactional = true

	/*
		规则：name=recordtype.serialNumber_region.name_branch.name_审批流
		用途：所有经销商，及一次性定价客户
	*/
    def initWorkflow(Department branch) {
        //获取记录类型
        def recordTypes = RecordType.findAllByDomainAndIsActive('salesOrder', true)
		//取得办事处用户组
        def branchGroup = Group.findByDepartment(branch)
		//取得办事处所属大区
        def region = Department.get(branch.parentDept.id)
        //取得大区用户组
        def regionGroup = Group.findByName(region.name)
        //取得商务部
        def commercial = Department.get(region.parentDept.id) 
        //取得商务部用户组
        def commercialGroup = Group.findByDepartment(commercial)

		//为每个单别创建不同的工作流
        for(recordType in recordTypes){
        	//创建工作流
            def workflow = new Workflow(
                name: "${recordType.serialNumber}_${region.name}_${branch.name}_审批流"
                , domain :'salesOrder'
                , recordType:recordType
                , department : branch
                , isActive:true
                , description :'该工作流适用于普通经销商和一定性定价经销商'
            ).save(flush:true)
            //创建工作流步骤
            def step_10 = new WorkflowStep(workflow:workflow, serialNumber : '10', name : '草稿', lockRecord:false, isBegin:true, isEnd :false).save(flush:true)
            def step_11 = new WorkflowStep(workflow:workflow, serialNumber : '11', name : '未通过', lockRecord:false, isBegin:false, isEnd :false).save(flush:true)
            
            def step_20 = new WorkflowStep(workflow:workflow, serialNumber : '20', name : '待审批（办事处）', assignee : branchGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)

            def step_30 = new WorkflowStep(workflow:workflow, serialNumber : '30', name : '待审批（商务部）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)
            def step_90 = new WorkflowStep(workflow:workflow, serialNumber : '90', name : '终止', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_91 = new WorkflowStep(workflow:workflow, serialNumber : '91', name : '审批通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_92 = new WorkflowStep(workflow:workflow, serialNumber : '92', name : '审批未通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            
            //创建步骤动作
            def action_01_01 = new WorkflowAction(belognsToStep : step_10, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save(flush:true)
            def action_01_02 = new WorkflowAction(belognsToStep : step_10, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save(flush:true)

            def action_11_01 = new WorkflowAction(belognsToStep : step_11, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save(flush:true)
            def action_11_02 = new WorkflowAction(belognsToStep : step_11, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save(flush:true)
            
            def action_20_01 = new WorkflowAction(belognsToStep : step_20, serialNumber : '01', nextStep : step_30, name : '通过', needDescription:false).save(flush:true)
            def action_20_02 = new WorkflowAction(belognsToStep : step_20, serialNumber : '02', nextStep : step_11, name : '拒绝', needDescription:true).save(flush:true)
            
            def action_30_01 = new WorkflowAction(belognsToStep : step_30, serialNumber : '01', nextStep : step_91, name : '通过', needDescription:false).save(flush:true)
            def action_30_02 = new WorkflowAction(belognsToStep : step_30, serialNumber : '02', nextStep : step_92, name : '拒绝', needDescription:false).save(flush:true)
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
        //取得商务部用户组
        def commercialGroup = Group.findByDepartment(commercial)

		//为每个单别创建不同的工作流
        for(recordType in recordTypes){
        	//创建工作流
            def workflow = new Workflow(
                name: "${recordType.serialNumber}_${commercial.name}_审批流"
                , domain :'salesOrder'
                , recordType:recordType
                , department : commercial
                , isActive:true
                , description :'该工作流只适用于OEM'
            ).save(flush:true)
            //创建工作流步骤
            def step_10 = new WorkflowStep(workflow:workflow, serialNumber : '10', name : '草稿', lockRecord:false, isBegin:true, isEnd :false).save(flush:true)
            def step_11 = new WorkflowStep(workflow:workflow, serialNumber : '11', name : '未通过', lockRecord:false, isBegin:false, isEnd :false).save(flush:true)
            
            def step_20 = new WorkflowStep(workflow:workflow, serialNumber : '20', name : '待审批（办事处）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)

            def step_30 = new WorkflowStep(workflow:workflow, serialNumber : '30', name : '待审批（商务部）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)
            
            def step_90 = new WorkflowStep(workflow:workflow, serialNumber : '90', name : '终止', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_91 = new WorkflowStep(workflow:workflow, serialNumber : '91', name : '审批通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_92 = new WorkflowStep(workflow:workflow, serialNumber : '92', name : '审批未通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            
            //创建步骤动作
            def action_01_01 = new WorkflowAction(belognsToStep : step_10, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save(flush:true)
            def action_01_02 = new WorkflowAction(belognsToStep : step_10, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save(flush:true)

            def action_11_01 = new WorkflowAction(belognsToStep : step_11, serialNumber : '01', nextStep : step_20, name : '提交', needDescription:false).save(flush:true)
            def action_11_02 = new WorkflowAction(belognsToStep : step_11, serialNumber : '02', nextStep : step_90, name : '终止', needDescription:false).save(flush:true)
            
            def action_20_01 = new WorkflowAction(belognsToStep : step_20, serialNumber : '01', nextStep : step_30, name : '通过', needDescription:false).save(flush:true)
            def action_20_02 = new WorkflowAction(belognsToStep : step_20, serialNumber : '02', nextStep : step_11, name : '拒绝', needDescription:true).save(flush:true)
            
            def action_30_01 = new WorkflowAction(belognsToStep : step_30, serialNumber : '01', nextStep : step_91, name : '通过', needDescription:false).save(flush:true)
            def action_30_02 = new WorkflowAction(belognsToStep : step_30, serialNumber : '02', nextStep : step_92, name : '拒绝', needDescription:false).save(flush:true)
        }
    }

}
