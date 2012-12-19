package org.leaf.eos2.admin

import org.leaf.eos2.shiro.User
import org.apache.shiro.SecurityUtils
import org.hibernate.FetchMode as FM
import org.springframework.transaction.annotation.*

class ShareRoleService {

    static transactional = true

	@Transactional(readOnly = true)
    def getShareUserList(objectName, method) {
		//get current user
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
		//get user group
		def groups = Group.withCriteria{
            createAlias 'users', 'u'
			projections{
                groupProperty('id')
			}
			eq("isActive", true)
			eq("u.id", currentUser.id)
			fetchMode "users", FM.JOIN
		}
		//groups = groups ?: [-1L]  
        if(groups.size() == 0) {
            groups = [-1L]
        }
		//get share users
		def shareUsers = ShareRole.withCriteria{
			projections{
				groupProperty('user.id')
			}
			eq("domain", objectName)
			inList("group.id", groups)
			if(method == "list"){
				eq("readable", true)
			}
			if(method == "show"){
				eq("readable", true)
			}
			if(method == "edit"){
				eq("editable", true)
			}
			if(method == "delete"){
				eq("deletable", true)
			}
		}
        if(shareUsers.size() == 0) {
            shareUsers = [-1L]
        }

		return shareUsers
    }
}
