<%@ page import="org.leaf.eos2.b2b.Department" %>
<ol class="property-list department">
	<g:if test="${departmentInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="department.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${departmentInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
	
	<g:if test="${departmentInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="department.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${departmentInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.type}">
	<li class="fieldcontain">
		<span id="type-label" class="property-label"><g:message code="department.type.label" default="Type" /></span>
		<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${departmentInstance}" field="type"/></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.description}">
	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="department.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${departmentInstance}" field="description"/></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.isActive}">
	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="department.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${departmentInstance?.isActive}" /></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.parentDept}">
	<li class="fieldcontain">
		<span id="parentDept-label" class="property-label"><g:message code="department.parentDept.label" default="Parent Dept" /></span>
		<span class="property-value" aria-labelledby="parentDept-label"><g:link controller="department" action="show" id="${departmentInstance?.parentDept?.id}">${departmentInstance?.parentDept?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="department.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${departmentInstance?.dateCreated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
	<g:if test="${departmentInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="department.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${departmentInstance?.lastUpdated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
</ol>
