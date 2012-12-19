<%@ page import="org.leaf.eos2.shiro.Role" %>
<ol class="property-list role">
			
	<g:if test="${roleInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="role.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${roleInstance}" field="name"/></span>
	</li>
	</g:if>
	
	<g:if test="${roleInstance?.isAdmin}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="role.isAdmin.label" default="Admin?" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${roleInstance}" field="isAdmin"/></span>
	</li>
	</g:if>
			
	<g:if test="${roleInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="role.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${roleInstance?.dateCreated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>
			
	<g:if test="${roleInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="role.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${roleInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>

</ol>

<g:if test="${roleInstance?.users}">
<table>
	<thead>
		<tr>
			<th><g:message code="user.username.label" default="Username" /></th>
			<th><g:message code="user.profile.label" default="Profile" /></th>
			<th><g:message code="user.department.label" default="Department" /></th>
			<th><g:message code="user.group.label" default="Group" /></th>
			<th><g:message code="user.dateCreated.label" default="Date Created" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${roleInstance.users}" status="i" var="userInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
			<td>${fieldValue(bean: userInstance, field: "profile")}</td>
			<td>${fieldValue(bean: userInstance, field: "department")}</td>
			<td>${fieldValue(bean: userInstance, field: "group")}</td>
			<td><g:formatDate date="${userInstance.dateCreated}" formatName="custom.date.format"/></td>
		</tr>
		</g:each>
	</tbody>
</table>
</g:if>

<g:if test="${roleInstance?.permissions}">
<table>
	<thead>
		<tr>
			<th><g:message code="role.permissions.label" default="Permissions" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${roleInstance?.permissions?.sort{return it}}" status="i" var="permission">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${permission}</td>
		</tr>
		</g:each>
	</tbody>
</table>
</g:if>