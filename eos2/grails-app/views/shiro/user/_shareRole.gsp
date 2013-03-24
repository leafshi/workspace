<%@ page import="org.leaf.eos2.admin.ShareRole" %>
<table>
	<caption><g:message code="shareRole.label" /></caption>
	<thead>
		<tr>
			<th><g:message code="shareRole.group.label" default="Group" /></th>
			<g:sortableColumn property="domain" title="${message(code: 'shareRole.domain.label', default: 'Domain')}" />
			<g:sortableColumn property="readable" title="${message(code: 'shareRole.readable.label', default: 'Readable')}" />
			<g:sortableColumn property="editable" title="${message(code: 'shareRole.editable.label', default: 'Editable')}" />
			<g:sortableColumn property="deletable" title="${message(code: 'shareRole.deletable.label', default: 'Deletable')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${ShareRole.findAllByUser(userInstance)}" status="i" var="shareRoleInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: shareRoleInstance, field: "group")}</td>
			<td>${fieldValue(bean: shareRoleInstance, field: "domain")}</td>
			<td><g:formatBoolean boolean="${shareRoleInstance.readable}" /></td>
			<td><g:formatBoolean boolean="${shareRoleInstance.editable}" /></td>
			<td><g:formatBoolean boolean="${shareRoleInstance.deletable}" /></td>
		</tr>
		</g:each>
	</tbody>
</table>
