<%@ page import="org.leaf.eos2.shiro.Role" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'role.name.label', default: 'Name')}" />
			<g:sortableColumn property="isAdmin" title="${message(code: 'role.isAdmin.label', default: 'Admin?')}" />
			<g:sortableColumn property="dateCreated" title="${message(code: 'role.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'role.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${roleInstanceList}" status="i" var="roleInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${roleInstance.id}">${fieldValue(bean: roleInstance, field: "name")}</g:link></td>
			<td>${roleInstance.isAdmin}</td>
			<td><g:formatDate date="${roleInstance.dateCreated}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${roleInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
		</g:each>
	</tbody>
</table>
