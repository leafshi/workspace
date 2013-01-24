<table>
	<thead>
		<tr>
			<g:sortableColumn property="id" title="Id" titleKey="loginHistory.id" />
			<g:sortableColumn property="username" title="Username" titleKey="loginHistory.username.label" />
			<g:sortableColumn property="loginTime" title="Login Time" titleKey="loginHistory.loginTime.label" />
			<g:sortableColumn property="sourceIP" title="Source IP" titleKey="loginHistory.sourceIP.label" />
			<g:sortableColumn property="loginType" title="Login Type" titleKey="loginHistory.loginType.label" />
			<g:sortableColumn property="isSucceed" title="Is Succeed" titleKey="loginHistory.isSucceed.label" />
			<g:sortableColumn property="dateCreated" title="Date Created" titleKey="loginHistory.dateCreated.label" />
			<g:sortableColumn property="lastUpdated" title="Last Updated" titleKey="loginHistory.lastUpdated.label" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${loginHistoryInstanceList}" status="i" var="loginHistoryInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td><g:link action="show" id="${loginHistoryInstance.id}">${fieldValue(bean: loginHistoryInstance, field: "id")}</g:link></td>
			<td>${fieldValue(bean: loginHistoryInstance, field: "username")}</td>
			<td><g:formatDate date="${loginHistoryInstance.loginTime}" formatName="custom.datetime.format"/></td>
			<td>${fieldValue(bean: loginHistoryInstance, field: "sourceIP")}</td>
			<td>${fieldValue(bean: loginHistoryInstance, field: "loginType")}</td>
			<td><g:formatBoolean boolean="${loginHistoryInstance.isSucceed}" /></td>
			<td><g:formatDate date="${loginHistoryInstance.dateCreated}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${loginHistoryInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
	</g:each>
	</tbody>
</table>