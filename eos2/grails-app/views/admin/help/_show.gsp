<table>
	<tbody>
	
		<tr class="prop">
			<td valign="top" class="name"><g:message code="help.title" default="Title" />:</td>
			<td valign="top" class="value">${fieldValue(bean: helpInstance, field: "title")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="help.content" default="Content" />:</td>
			<td valign="top" class="value">${helpInstance?.content?.replaceAll("\n", "<br/>")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="help.dateCreated" default="Date Created" />:</td>
			<td valign="top" class="value"><g:formatDate date="${helpInstance.dateCreated}" formatName="custom.datetime.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="help.lastUpdated" default="Last Updated" />:</td>
			<td valign="top" class="value"><g:formatDate date="${helpInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
		
	</tbody>
</table>