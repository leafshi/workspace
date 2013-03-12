<table>
	<thead>
		<tr>
			<g:sortableColumn property="title" title="Title" titleKey="entity.title.label" />
			<th><g:message code="entity.createdBy.label" default="Created By" /></th>
			<th><g:message code="entity.lastModifiedBy.label" default="Last Modified By" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${entityInstanceList}" status="i" var="entityInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td>
				<g:link action="show" id="${entityInstance.id}">
					[${fieldValue(bean: entityInstance, field: "level")}]
					${fieldValue(bean: entityInstance, field: "title")}
				</g:link>
			</td>
			<td>
				<g:link action="show" id="${entityInstance.id}">
					${fieldValue(bean: entityInstance, field: "createdBy")}
					, 
					${formatDate(date: entityInstance?.dateCreated, formatName:'custom.datetime.format')}
				</g:link>
			</td>
			<td>
				<g:link action="show" id="${entityInstance.id}">
					${fieldValue(bean: entityInstance, field: "lastModifiedBy")}
					,
					${formatDate(date: entityInstance?.lastUpdated, formatName:'custom.datetime.format')}
				</g:link>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
