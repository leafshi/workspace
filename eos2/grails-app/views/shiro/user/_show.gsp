<%@ page import="org.leaf.eos2.shiro.User" %>
<ol class="property-list user">
			
	<g:if test="${userInstance?.username}">
	<li class="fieldcontain">
		<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
		<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
	</li>
	</g:if>
			
	<g:if test="${userInstance?.profile}">
	<li class="fieldcontain">
		<span id="profile-label" class="property-label"><g:message code="user.profile.label" default="Profile" /></span>
		<span class="property-value" aria-labelledby="profile-label"><g:link controller="profile" action="show" id="${userInstance?.profile?.id}">${userInstance?.profile?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${userInstance?.department}">
	<li class="fieldcontain">
		<span id="department-label" class="property-label"><g:message code="user.department.label" default="Department" /></span>
		<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${userInstance?.department?.id}">${userInstance?.department?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${userInstance?.group}">
	<li class="fieldcontain">
		<span id="group-label" class="property-label"><g:message code="user.group.label" default="Group" /></span>
		<span class="property-value" aria-labelledby="group-label"><g:link controller="group" action="show" id="${userInstance?.group?.id}">${userInstance?.group?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
	
	<g:if test="${userInstance?.role}">
	<li class="fieldcontain">
		<span id="roles-label" class="property-label"><g:message code="user.roles.label" default="Roles" /></span>
		<span class="property-value" aria-labelledby="role-label">${userInstance.role}</span>
	</li>
	</g:if>
			
	<g:if test="${userInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="user.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
	<g:if test="${userInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="user.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${userInstance?.lastUpdated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
</ol>
