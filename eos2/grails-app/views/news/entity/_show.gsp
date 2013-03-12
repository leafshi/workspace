<table>
	<tbody>		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="entity.title.label" default="Title" />:</td>
			<td valign="top" class="value">
				[${fieldValue(bean: entityInstance, field: "level")}]
				${fieldValue(bean: entityInstance, field: "title")}
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="entity.content.label" default="Content" />:</td>
			<td valign="top" class="value">${entityInstance?.content?.replaceAll("\n", "<br/>")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="entity.createdBy.label" default="Created By" />:</td>
			<td valign="top" class="value">
				<g:link controller="user" action="show" id="${entityInstance?.createdBy?.id}">${entityInstance?.createdBy?.encodeAsHTML()}</g:link>
				,
				<g:formatDate date="${entityInstance?.dateCreated}" formatName="custom.datetime.format"/>
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="entity.lastModifiedBy.label" default="Last Modified By" />:</td>
			<td valign="top" class="value">
				<g:link controller="user" action="show" id="${entityInstance?.lastModifiedBy?.id}">${entityInstance?.lastModifiedBy?.encodeAsHTML()}</g:link>
				,
				<g:formatDate date="${entityInstance?.lastUpdated}" formatName="custom.datetime.format"/>
			</td>
		</tr>
		
	</tbody>
</table>