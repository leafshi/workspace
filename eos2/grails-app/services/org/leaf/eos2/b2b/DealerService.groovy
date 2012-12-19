package org.leaf.eos2.b2b

import org.leaf.eos2.admin.ShareRole
import org.leaf.eos2.admin.Group
import org.leaf.eos2.shiro.User

class DealerService {

    static transactional = true 

    def initShareRole(User owner, Long departmentId){
        //办事处
        def branch = Department.get(departmentId)
        def branchGroup = Group.findByName(branch.name)
        new ShareRole(user: owner, group : branchGroup, domain : 'salesOrder', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : branchGroup, domain : 'contract', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : branchGroup, domain : 'outBound', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : branchGroup, domain : 'creditControl', readable :true, editable:false, deletable:false).save()
        //大区
        def region = Department.get(branch.parentDept.id)
        def regionGroup = Group.findByName(region.name)
        new ShareRole(user: owner, group : regionGroup, domain : 'salesOrder', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : regionGroup, domain : 'contract', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : regionGroup, domain : 'outBound', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : regionGroup, domain : 'creditControl', readable :true, editable:false, deletable:false).save()
        //找到商务部
        def commercial = Department.get(region.parentDept.id)
        def commercialGroup = Group.findByName(commercial.name)
        new ShareRole(user: owner, group : commercialGroup, domain : 'salesOrder', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : commercialGroup, domain : 'contract', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : commercialGroup, domain : 'outBound', readable :true, editable:false, deletable:false).save()
        new ShareRole(user: owner, group : commercialGroup, domain : 'creditControl', readable :true, editable:false, deletable:false).save()
    }
}
