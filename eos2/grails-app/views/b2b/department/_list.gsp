<%@ page import="org.leaf.eos2.b2b.Department" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'department.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'department.name.label', default: 'Name')}" />
			<g:sortableColumn property="type" title="${message(code: 'department.type.label', default: 'Type')}" />
			<g:sortableColumn property="description" title="${message(code: 'department.description.label', default: 'Description')}" />
			<g:sortableColumn property="isActive" title="${message(code: 'department.isActive.label', default: 'Is Active')}" />
			<th><g:message code="department.parentDept.label" default="Parent Dept" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${departmentInstanceList}" status="i" var="departmentInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${departmentInstance.id}">${fieldValue(bean: departmentInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: departmentInstance, field: "name")}</td>
			<td>${fieldValue(bean: departmentInstance, field: "type")}</td>
			<td>${fieldValue(bean: departmentInstance, field: "description")}</td>
			<td><g:formatBoolean boolean="${departmentInstance.isActive}" /></td>
			<td>${fieldValue(bean: departmentInstance, field: "parentDept")}</td>
		</tr>
	</g:each>
	</tbody>
</table>
