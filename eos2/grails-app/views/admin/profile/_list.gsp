<table>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'profile.name.label', default: 'Name')}" />
			<g:sortableColumn property="dateCreated" title="${message(code: 'profile.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'profile.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${profileInstanceList}" status="i" var="profileInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td><g:link action="show" id="${profileInstance.id}">${fieldValue(bean: profileInstance, field: "name")}</g:link></td>
				<td><g:formatDate date="${profileInstance.dateCreated}" formatName="custom.datetime.format"/></td>
				<td><g:formatDate date="${profileInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
			</tr>
		</g:each>
	</tbody>
</table>
