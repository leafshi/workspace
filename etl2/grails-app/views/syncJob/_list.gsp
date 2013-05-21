<table>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'syncJob.name.label', default: 'Name')}" />
			<g:sortableColumn property="source" title="${message(code: 'syncJob.source.label', default: 'Source')}" />
			<g:sortableColumn property="target" title="${message(code: 'syncJob.target.label', default: 'Target')}" />
			<g:sortableColumn property="priority" title="${message(code: 'syncJob.priority.label', default: 'Priority')}" />
			<g:sortableColumn property="scriptName" title="${message(code: 'syncJob.scriptName.label', default: 'Script Name')}" />
			<g:sortableColumn property="dateCreated" title="${message(code: 'syncJob.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'syncJob.lastUpdated.label', default: 'Last Updated')}" />
		
		</tr>
	</thead>
	<tbody>
	<g:each in="${syncJobInstanceList}" status="i" var="syncJobInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${syncJobInstance.id}">${fieldValue(bean: syncJobInstance, field: "name")}</g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}">${fieldValue(bean: syncJobInstance, field: "source")}</g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}">${fieldValue(bean: syncJobInstance, field: "target")}</g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}">${fieldValue(bean: syncJobInstance, field: "priority")}</g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}">${fieldValue(bean: syncJobInstance, field: "scriptName")}</g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}"><g:formatDate date="${syncJobInstance.dateCreated}" /></g:link></td>
			<td><g:link action="show" id="${syncJobInstance.id}"><g:formatDate date="${syncJobInstance.lastUpdated}" /></g:link></td>
		</tr>
	</g:each>
	</tbody>
</table>