<table>
	<thead>
		<tr>
			<g:sortableColumn property="id" title="Id" titleKey="loginHistory.id" />
			<g:sortableColumn property="username" title="Username" titleKey="loginHistory.username.label" />
			<g:sortableColumn property="loginTime" title="Login Time" titleKey="loginHistory.loginTime.label" />
			<g:sortableColumn property="sourceIP" title="Source IP" titleKey="loginHistory.sourceIP.label" />
			<g:sortableColumn property="loginType" title="Login Type" titleKey="loginHistory.loginType.label" />
			<g:sortableColumn property="isSucceed" title="Is Succeed" titleKey="loginHistory.isSucceed.label" />
			<g:sortableColumn property="browser" title="Browser" titleKey="loginHistory.browser.label" />
			<g:sortableColumn property="platform" title="Platform" titleKey="loginHistory.platform.label" />
			<g:sortableColumn property="operatingSystem" title="Operating System" titleKey="loginHistory.operatingSystem.label" />
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
			<td>${fieldValue(bean: loginHistoryInstance, field: "browser")}</td>
			<td>${fieldValue(bean: loginHistoryInstance, field: "platform")}</td>
			<td>${fieldValue(bean: loginHistoryInstance, field: "operatingSystem")}</td>
		</tr>
	</g:each>
	</tbody>
</table>