<%@ page import="org.leaf.eos2.birt.Report" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'report.name.label', default: 'Name')}" titleKey="report.name" />
			<g:sortableColumn property="title" title="${message(code: 'report.title.label', default: 'Title')}" titleKey="report.title" />
			<g:sortableColumn property="author" title="${message(code: 'report.author.label', default: 'Author')}" titleKey="report.author" />
			<g:sortableColumn property="description" title="${message(code: 'report.description.label', default: 'Description')}" titleKey="report.description" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${reportInstanceList}" status="i" var="reportInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td><g:link action="run" id="${reportInstance?.name}">${fieldValue(bean: reportInstance, field: "name")}</g:link></td>
			<td>${fieldValue(bean: reportInstance, field: "title")}</td>
			<td>${fieldValue(bean: reportInstance, field: "author")}</td>
			<td>${fieldValue(bean: reportInstance, field: "description")}</td>
		</tr>
	</g:each>
	</tbody>
</table>