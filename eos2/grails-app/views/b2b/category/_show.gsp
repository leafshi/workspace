<%@ page import="org.leaf.eos2.b2b.Category" %>
			
<ol class="property-list category">

	<li class="fieldcontain">
		<span id="recordType-label" class="property-label"><g:message code="category.recordType.label" default="Record Type" /></span>
		<span class="property-value" aria-labelledby="recordType-label"><g:link controller="recordType" action="show" id="${categoryInstance?.recordType?.id}">${categoryInstance?.recordType?.encodeAsHTML()}</g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="category.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${categoryInstance}" field="serialNumber"/></span>
	</li>

	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="category.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${categoryInstance}" field="name"/></span>
	</li>

	<li class="fieldcontain">
		<span id="discount-label" class="property-label"><g:message code="category.discount.label" default="Discount" /></span>
		<span class="property-value" aria-labelledby="discount-label"><g:fieldValue bean="${categoryInstance}" field="discount"/></span>
	</li>

	<li class="fieldcontain">
		<span id="productionCycle-label" class="property-label"><g:message code="category.productionCycle.label" default="Production Cycle" /></span>
		<span class="property-value" aria-labelledby="productionCycle-label"><g:fieldValue bean="${categoryInstance}" field="productionCycle"/></span>
	</li>

	<li class="fieldcontain">
		<span id="transportCycle-label" class="property-label"><g:message code="category.transportCycle.label" default="Transport Cycle" /></span>
		<span class="property-value" aria-labelledby="transportCycle-label"><g:fieldValue bean="${categoryInstance}" field="transportCycle"/></span>
	</li>
	
	<li class="fieldcontain">
		<span id="deliveryCycle-label" class="property-label"><g:message code="category.deliveryCycle.label" default="Delivery Cycle" /></span>
		<span class="property-value" aria-labelledby="deliveryCycle-label"><g:fieldValue bean="${categoryInstance}" field="deliveryCycle"/></span>
	</li>

	<li class="fieldcontain">
		<span id="hasBom-label" class="property-label"><g:message code="category.hasBom.label" default="Has Bom" /></span>
		<span class="property-value" aria-labelledby="hasBom-label"><g:formatBoolean boolean="${categoryInstance?.hasBom}" /></span>
	</li>

	<li class="fieldcontain">
		<span id="isAllowSpecialDiscount-label" class="property-label"><g:message code="category.isAllowSpecialDiscount.label" default="Is Allow Special Discount" /></span>
		<span class="property-value" aria-labelledby="isAllowSpecialDiscount-label"><g:formatBoolean boolean="${categoryInstance?.isAllowSpecialDiscount}" /></span>
					
	</li>
	
	<li class="fieldcontain">
		<span id="isAllowZeroPrice-label" class="property-label"><g:message code="category.isAllowZeroPrice.label" default="Is Allow Zero Price" /></span>
		<span class="property-value" aria-labelledby="isAllowZeroPrice-label"><g:formatBoolean boolean="${categoryInstance?.isAllowZeroPrice}" /></span>
					
	</li>

	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="category.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${categoryInstance?.isActive}" /></span>
	</li>


	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="category.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${categoryInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>

	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="category.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${categoryInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>
			
</ol>
