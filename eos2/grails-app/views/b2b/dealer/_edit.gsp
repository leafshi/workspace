<%@ page import="org.leaf.eos2.b2b.Dealer" %>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="dealer.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="department" name="department.id" from="${org.leaf.eos2.b2b.Department.list()}" optionKey="id" required="" value="${dealerInstance?.department?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'serialNumber', 'error')} ">
	<label for="serialNumber">
		<g:message code="dealer.serialNumber.label" default="Serial Number" />
	</label>
	<g:textField name="serialNumber" maxlength="10" value="${dealerInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="dealer.name.label" default="Name" />
	</label>
	<g:textField name="name" maxlength="72" value="${dealerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'alias', 'error')} ">
	<label for="alias">
		<g:message code="dealer.alias.label" default="Alias" />
	</label>
	<g:textField name="alias" value="${dealerInstance?.alias}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'shortcut', 'error')} ">
	<label for="shortcut">
		<g:message code="dealer.shortcut.label" default="Shortcut" />
	</label>
	<g:textField name="shortcut" maxlength="20" value="${dealerInstance?.shortcut}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'salesMan', 'error')} required">
	<label for="salesMan">
		<g:message code="dealer.salesMan.label" default="Sales Man" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="salesMan" name="salesMan.id" from="${org.leaf.eos2.b2b.Staff.list()}" optionKey="id" required="" value="${dealerInstance?.salesMan?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'head', 'error')} ">
	<label for="head">
		<g:message code="dealer.head.label" default="Head" />
	</label>
	<g:textField name="head" maxlength="30" value="${dealerInstance?.head}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'contact', 'error')} ">
	<label for="contact">
		<g:message code="dealer.contact.label" default="Contact" />
	</label>
	<g:textField name="contact" maxlength="30" value="${dealerInstance?.contact}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'address1', 'error')} ">
	<label for="address1">
		<g:message code="dealer.address1.label" default="Address1" />
	</label>
	<g:textField name="address1" maxlength="72" value="${dealerInstance?.address1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'address2', 'error')} ">
	<label for="address2">
		<g:message code="dealer.address2.label" default="Address2" />
	</label>
	<g:textField name="address2" maxlength="72" value="${dealerInstance?.address2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'approvalStatus', 'error')} ">
	<label for="approvalStatus">
		<g:message code="dealer.approvalStatus.label" default="Approval Status" />
	</label>
	<g:textField name="approvalStatus" maxlength="8" value="${dealerInstance?.approvalStatus}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'pricingMode', 'error')} ">
	<label for="pricingMode">
		<g:message code="dealer.pricingMode.label" default="Pricing Mode" />
	</label>
	<g:textField name="pricingMode" maxlength="10" value="${dealerInstance?.pricingMode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dealerInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="dealer.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${dealerInstance?.owner?.id}" class="many-to-one"/>
</div>
