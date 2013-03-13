<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'recordType.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'recordType.name.label', default: 'Name')}" />
			<g:sortableColumn property="domain" title="${message(code: 'recordType.domain.label', default: 'Domain')}" />
			<g:sortableColumn property="description" title="${message(code: 'recordType.description.label', default: 'Description')}" />
			<g:sortableColumn property="isActive" title="${message(code: 'recordType.isActive.label', default: 'Is Active')}" />
			<g:sortableColumn property="dateCreated" title="${message(code: 'recordType.dateCreated.label', default: 'Date Created')}" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${recordTypeInstanceList}" status="i" var="recordTypeInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${recordTypeInstance.id}">${fieldValue(bean: recordTypeInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: recordTypeInstance, field: "name")}</td>
			<td>${fieldValue(bean: recordTypeInstance, field: "domain")}</td>
			<td>${fieldValue(bean: recordTypeInstance, field: "description")}</td>
			<td><g:formatBoolean boolean="${recordTypeInstance.isActive}" /></td>
			<td><g:formatDate date="${recordTypeInstance.dateCreated}" formatName="custom.datetime.format"/></td>
		</tr>
	</g:each>
	</tbody>
</table>
