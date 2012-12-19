<%@ page import="org.leaf.eos2.b2b.Staff" %>
<ol class="property-list staff">
			
	<g:if test="${staffInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="staff.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${staffInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
			
	<g:if test="${staffInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="staff.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${staffInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${staffInstance?.department}">
	<li class="fieldcontain">
		<span id="department-label" class="property-label"><g:message code="staff.department.label" default="Department" /></span>
		<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${staffInstance?.department?.id}">${staffInstance?.department?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${staffInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="staff.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${staffInstance?.dateCreated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
	<g:if test="${staffInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="staff.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${staffInstance?.lastUpdated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
	
</ol>
