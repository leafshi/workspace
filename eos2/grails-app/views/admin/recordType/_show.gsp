<%@ page import="org.leaf.eos2.admin.RecordType" %>
<ol class="property-list recordType">

	<g:if test="${recordTypeInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="recordType.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${recordTypeInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="recordType.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${recordTypeInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.domain}">
	<li class="fieldcontain">
		<span id="domain-label" class="property-label"><g:message code="recordType.domain.label" default="Domain" /></span>
		<span class="property-value" aria-labelledby="domain-label"><g:fieldValue bean="${recordTypeInstance}" field="domain"/></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.description}">
	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="recordType.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recordTypeInstance}" field="description"/></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.isActive}">
	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="recordType.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${recordTypeInstance?.isActive}" /></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="recordType.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${recordTypeInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>
			
	<g:if test="${recordTypeInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="recordType.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${recordTypeInstance?.lastUpdated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
	
</ol>
