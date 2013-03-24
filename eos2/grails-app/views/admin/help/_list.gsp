<%@ page import="org.leaf.eos2.admin.Help" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="title" title="Title" titleKey="help.title" />
			<g:sortableColumn property="dateCreated" title="${message(code: 'profile.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'profile.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${helpInstanceList}" status="i" var="helpInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td><g:link action="show" id="${helpInstance.id}">${fieldValue(bean: helpInstance, field: "title")}</g:link></td>
			<td><g:formatDate date="${helpInstance.dateCreated}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${helpInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
	</g:each>
	</tbody>
</table>