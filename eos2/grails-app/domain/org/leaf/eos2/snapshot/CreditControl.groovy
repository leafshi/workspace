package org.leaf.eos2.snapshot

import org.leaf.eos2.b2b.Dealer
import org.leaf.eos2.shiro.User


class CreditControl {

    Dealer dealer//经销商
    BigDecimal orderAmount//订货金额
    BigDecimal openingSell//未结帐销货
    BigDecimal receivableAmout//应收帐款
    BigDecimal receivableNote//应收票据
    BigDecimal receivable//应收合计
    BigDecimal credit//信用额度
    BigDecimal creditExceed//信用可超出额度
    BigDecimal creditBalance//信用余额

    Date dateCreated//创建日期
    Date lastUpdated//上次修改日期

    User owner//所有人
    User createdBy//创建人
    User lastModifiedBy//上次修改人

    static constraints = {
        dealer(nullable:false)
        orderAmount(scale:2)
        openingSell(scale:2)
        receivableAmout(scale:2)
        receivableNote(scale:2)
        receivable(scale:2)
        credit(scale:2)
        creditExceed(scale:2)
        creditBalance(scale:2)
    }

    String toString() {
        return "$dealer.serialNumber"
    }
    
    static mapping = {
        table 'SNAPSHOT_CREDITCONTROL'
    }    
}
