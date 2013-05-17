<%@ page import="org.leaf.eos2.shiro.User" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
			<th><g:message code="user.profile.label" default="Profile" /></th>
			<th><g:message code="user.department.label" default="Department" /></th>
			<th><g:message code="user.group.label" default="Group" /></th>
			<th><g:message code="user.phone.label" default="Phone" /></th>
			<th><g:message code="user.mail.label" default="Mail" /></th>
			<th><g:message code="user.registerECS.label" default="Register ECS?" /></th>
			<th><g:message code="user.activateECS.label" default="Activate ECS?" /></th>
			<th><g:message code="user.isActive.label" default="Active?" /></th>
			<g:sortableColumn property="dateCreated" title="${message(code: 'user.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'user.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${userInstanceList}" status="i" var="userInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link controller="user" action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
			<td>${fieldValue(bean: userInstance, field: "profile")}</td>
			<td>${fieldValue(bean: userInstance, field: "department")}</td>
			<td>${fieldValue(bean: userInstance, field: "group")}</td>
			<td>${fieldValue(bean: userInstance, field: "phone")}</td>
			<td>${fieldValue(bean: userInstance, field: "mail")}</td>
			<td>${fieldValue(bean: userInstance, field: "registerECS")}</td>
			<td>${fieldValue(bean: userInstance, field: "activateECS")}</td>
			<td>${fieldValue(bean: userInstance, field: "isActive")}</td>
			<td><g:formatDate date="${userInstance.dateCreated}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${userInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
		</g:each>
	</tbody>
</table>
