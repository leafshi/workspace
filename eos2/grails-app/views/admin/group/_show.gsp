<%@ page import="org.leaf.eos2.admin.Group" %>
<ol class="property-list group">
	<g:if test="${groupInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="group.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${groupInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${groupInstance?.isActive}">
	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="group.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${groupInstance?.isActive}" /></span>
	</li>
	</g:if>
			
	<g:if test="${groupInstance?.description}">
	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="group.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${groupInstance}" field="description"/></span>
	</li>
	</g:if>
	
	<g:if test="${groupInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="group.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${groupInstance?.dateCreated}" formatName="custom.date.format"/></span>
	</li>
	</g:if>

	<g:if test="${groupInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="group.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${groupInstance?.lastUpdated}" formatName="custom.date.format" /></span>
	</li>
	</g:if>

			
	<g:if test="${groupInstance?.users}">
	<li class="fieldcontain">
		<span id="users-label" class="property-label"><g:message code="group.users.label" default="Users" /></span>
		<g:each in="${groupInstance.users}" var="u">
			<span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
		</g:each>
	</li>
	</g:if>
	
</ol>
