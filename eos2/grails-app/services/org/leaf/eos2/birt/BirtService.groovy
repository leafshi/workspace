package org.leaf.eos2.birt


import org.springframework.transaction.annotation.*
import org.leaf.eos2.shiro.User
import org.apache.shiro.SecurityUtils


class BirtService {

    static transactional = true

    @Transactional(readOnly = true)
    def currentUser() {
        def currentUser = User.findByUsername( SecurityUtils.getSubject().getPrincipal() )
        return currentUser.id
    }
}
