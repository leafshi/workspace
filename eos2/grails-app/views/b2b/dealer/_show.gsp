<%@ page import="org.leaf.eos2.b2b.Dealer" %>
<ol class="property-list dealer">
			
	<g:if test="${dealerInstance?.department}">
	<li class="fieldcontain">
		<span id="department-label" class="property-label"><g:message code="dealer.department.label" default="Department" /></span>
		<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${dealerInstance?.department?.id}">${dealerInstance?.department?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="dealer.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${dealerInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="dealer.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${dealerInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.alias}">
	<li class="fieldcontain">
		<span id="alias-label" class="property-label"><g:message code="dealer.alias.label" default="Alias" /></span>
		<span class="property-value" aria-labelledby="alias-label"><g:fieldValue bean="${dealerInstance}" field="alias"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.shortcut}">
	<li class="fieldcontain">
		<span id="shortcut-label" class="property-label"><g:message code="dealer.shortcut.label" default="Shortcut" /></span>
		<span class="property-value" aria-labelledby="shortcut-label"><g:fieldValue bean="${dealerInstance}" field="shortcut"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.salesMan}">
	<li class="fieldcontain">
		<span id="salesMan-label" class="property-label"><g:message code="dealer.salesMan.label" default="Sales Man" /></span>
		<span class="property-value" aria-labelledby="salesMan-label"><g:link controller="staff" action="show" id="${dealerInstance?.salesMan?.id}">${dealerInstance?.salesMan?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
		
	<g:if test="${dealerInstance?.head}">
	<li class="fieldcontain">
		<span id="head-label" class="property-label"><g:message code="dealer.head.label" default="Head" /></span>
		<span class="property-value" aria-labelledby="head-label"><g:fieldValue bean="${dealerInstance}" field="head"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.contact}">
	<li class="fieldcontain">
		<span id="contact-label" class="property-label"><g:message code="dealer.contact.label" default="Contact" /></span>
		<span class="property-value" aria-labelledby="contact-label"><g:fieldValue bean="${dealerInstance}" field="contact"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.address1}">
	<li class="fieldcontain">
		<span id="address1-label" class="property-label"><g:message code="dealer.address1.label" default="Address1" /></span>
		<span class="property-value" aria-labelledby="address1-label"><g:fieldValue bean="${dealerInstance}" field="address1"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.address2}">
	<li class="fieldcontain">
		<span id="address2-label" class="property-label"><g:message code="dealer.address2.label" default="Address2" /></span>
		<span class="property-value" aria-labelledby="address2-label"><g:fieldValue bean="${dealerInstance}" field="address2"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.approvalStatus}">
	<li class="fieldcontain">
		<span id="approvalStatus-label" class="property-label"><g:message code="dealer.approvalStatus.label" default="Approval Status" /></span>
		<span class="property-value" aria-labelledby="approvalStatus-label"><g:fieldValue bean="${dealerInstance}" field="approvalStatus"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.pricingMode}">
	<li class="fieldcontain">
		<span id="pricingMode-label" class="property-label"><g:message code="dealer.pricingMode.label" default="Pricing Mode" /></span>
		<span class="property-value" aria-labelledby="pricingMode-label"><g:fieldValue bean="${dealerInstance}" field="pricingMode"/></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.owner}">
	<li class="fieldcontain">
		<span id="owner-label" class="property-label"><g:message code="dealer.owner.label" default="Owner" /></span>
		<span class="property-value" aria-labelledby="owner-label"><g:link controller="user" action="show" id="${dealerInstance?.owner?.id}">${dealerInstance?.owner?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="dealer.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${dealerInstance?.dateCreated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
	<g:if test="${dealerInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="dealer.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${dealerInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>
			
</ol>
