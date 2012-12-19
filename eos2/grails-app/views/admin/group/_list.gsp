<%@ page import="org.leaf.eos2.admin.Group" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'group.name.label', default: 'Name')}" />
			<g:sortableColumn property="isActive" title="${message(code: 'group.isActive.label', default: 'Is Active')}" />
			<g:sortableColumn property="description" title="${message(code: 'group.description.label', default: 'Description')}" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${groupInstanceList}" status="i" var="groupInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${groupInstance.id}">${fieldValue(bean: groupInstance, field: "name")}</g:link></td>
			<td><g:formatBoolean boolean="${groupInstance.isActive}" /></td>
			<td>${fieldValue(bean: groupInstance, field: "description")}</td>
		</tr>
	</g:each>
	</tbody>
</table>
