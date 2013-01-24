<table>
	<tbody>
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.id.label" default="Id" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "id")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.username.label" default="Username" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "username")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.loginTime.label" default="Login Time" />:</td>
			<td valign="top" class="value"><g:formatDate date="${loginHistoryInstance?.loginTime}" formatName="custom.datetime.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.sourceIP.label" default="Source IP" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "sourceIP")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.loginType.label" default="Login Type" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "loginType")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.isSucceed.label" default="Is Succeed" />:</td>
			<td valign="top" class="value"><g:formatBoolean boolean="${loginHistoryInstance?.isSucceed}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.browser.label" default="Browser" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "browser")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.platform.label" default="Platform" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "platform")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.operatingSystem.label" default="Operating System" />:</td>
			<td valign="top" class="value">${fieldValue(bean: loginHistoryInstance, field: "operatingSystem")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.dateCreated.label" default="Date Created" />:</td>
			<td valign="top" class="value"><g:formatDate date="${loginHistoryInstance?.dateCreated}" formatName="custom.datetime.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="loginHistory.lastUpdated.label" default="Last Updated" />:</td>
			<td valign="top" class="value"><g:formatDate date="${loginHistoryInstance?.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
		
	</tbody>
</table>