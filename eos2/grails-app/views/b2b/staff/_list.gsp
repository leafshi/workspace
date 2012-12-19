<%@ page import="org.leaf.eos2.b2b.Staff" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'staff.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'staff.name.label', default: 'Name')}" />
			<th><g:message code="staff.department.label" default="Department" /></th>
			<g:sortableColumn property="dateCreated" title="${message(code: 'staff.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'staff.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${staffInstanceList}" status="i" var="staffInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${staffInstance.id}">${fieldValue(bean: staffInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: staffInstance, field: "name")}</td>
			<td>${fieldValue(bean: staffInstance, field: "department")}</td>
			<td><g:formatDate date="${staffInstance.dateCreated}" formatName="custom.date.format" /></td>
			<td><g:formatDate date="${staffInstance.lastUpdated}" formatName="custom.date.format" /></td>
		</tr>
		</g:each>
	</tbody>
</table>
