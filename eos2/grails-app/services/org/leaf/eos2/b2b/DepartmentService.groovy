package org.leaf.eos2.b2b

import org.leaf.eos2.admin.RecordType
import org.leaf.eos2.admin.Group

import org.leaf.eos2.wf.Workflow
import org.leaf.eos2.wf.WorkflowStep
import org.leaf.eos2.wf.WorkflowAction

class DepartmentService {

    static transactional = true

    def initWorkflow(Department branch) {
        //name=recordtype.serialNumber_region.name_branch.name_审批流
        def recordTypes = RecordType.findAllByDomainAndIsActive('salesOrder', true)

        def branchGroup = Group.findByName(branch.name)

        def region = Department.get(branch.parentDept.id)
        def regionGroup = Group.findByName(region.name)
        
        def commercial = Department.get(region.parentDept.id) 
        def commercialGroup = Group.findByName(commercial.name)

        for(recordType in recordTypes){
            def workflow = new Workflow(
                name: "${recordType.serialNumber}_${region.name}_${branch.name}_审批流"
                , domain :'salesOrder'
                , recordType:recordType
                , department : branch
                , isActive:true
                , description :''
            ).save(flush:true)
            
            def step_10 = new WorkflowStep(workflow:workflow, serialNumber : '10', name : '草稿', lockRecord:false, isBegin:true, isEnd :false).save(flush:true)
            def step_11 = new WorkflowStep(workflow:workflow, serialNumber : '11', name : '未通过', lockRecord:false, isBegin:false, isEnd :false).save(flush:true)
            
            def step_20 = new WorkflowStep(workflow:workflow, serialNumber : '20', name : '待审批（办事处）', assignee : branchGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)

            def step_30 = new WorkflowStep(workflow:workflow, serialNumber : '30', name : '待审批（商务部）', assignee : commercialGroup, lockRecord:true, isBegin:false, isEnd :false).save(flush:true)
            def step_90 = new WorkflowStep(workflow:workflow, serialNumber : '90', name : '终止', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_91 = new WorkflowStep(workflow:workflow, serialNumber : '91', name : '审批通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            def step_92 = new WorkflowStep(workflow:workflow, serialNumber : '92', name : '审批未通过', lockRecord:true, isBegin:false, isEnd :true).save(flush:true)
            
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
