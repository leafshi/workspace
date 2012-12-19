<%@ page import="org.leaf.eos2.snapshot.CreditControl" %>



<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'dealer', 'error')} required">
	<label for="dealer">
		<g:message code="creditControl.dealer.label" default="Dealer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dealer" name="dealer.id" from="${org.leaf.eos2.b2b.Dealer.list()}" optionKey="id" required="" value="${creditControlInstance?.dealer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'orderAmount', 'error')} required">
	<label for="orderAmount">
		<g:message code="creditControl.orderAmount.label" default="Order Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="orderAmount" value="${fieldValue(bean: creditControlInstance, field: 'orderAmount')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'openingSell', 'error')} required">
	<label for="openingSell">
		<g:message code="creditControl.openingSell.label" default="Opening Sell" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="openingSell" value="${fieldValue(bean: creditControlInstance, field: 'openingSell')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'receivableAmout', 'error')} required">
	<label for="receivableAmout">
		<g:message code="creditControl.receivableAmout.label" default="Receivable Amout" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="receivableAmout" value="${fieldValue(bean: creditControlInstance, field: 'receivableAmout')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'receivableNote', 'error')} required">
	<label for="receivableNote">
		<g:message code="creditControl.receivableNote.label" default="Receivable Note" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="receivableNote" value="${fieldValue(bean: creditControlInstance, field: 'receivableNote')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'receivable', 'error')} required">
	<label for="receivable">
		<g:message code="creditControl.receivable.label" default="Receivable" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="receivable" value="${fieldValue(bean: creditControlInstance, field: 'receivable')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'credit', 'error')} required">
	<label for="credit">
		<g:message code="creditControl.credit.label" default="Credit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="credit" value="${fieldValue(bean: creditControlInstance, field: 'credit')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'creditExceed', 'error')} required">
	<label for="creditExceed">
		<g:message code="creditControl.creditExceed.label" default="Credit Exceed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="creditExceed" value="${fieldValue(bean: creditControlInstance, field: 'creditExceed')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'creditBalance', 'error')} required">
	<label for="creditBalance">
		<g:message code="creditControl.creditBalance.label" default="Credit Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="creditBalance" value="${fieldValue(bean: creditControlInstance, field: 'creditBalance')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="creditControl.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${creditControlInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'lastModifiedBy', 'error')} required">
	<label for="lastModifiedBy">
		<g:message code="creditControl.lastModifiedBy.label" default="Last Modified By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lastModifiedBy" name="lastModifiedBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${creditControlInstance?.lastModifiedBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: creditControlInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="creditControl.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${creditControlInstance?.owner?.id}" class="many-to-one"/>
</div>

