<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 	<meta name="layout" content="main" />
 	<title>
		<g:message code="login.page.title"/>
		~
		<g:message code="global.app.name" />
	</title>
	<script type="text/javascript" src="${resource(dir:'js', file:'page_login.js')}" ></script>
</head>
<body>
	<g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
	<g:form action="signIn">
		<input type="hidden" name="targetUri" value="${targetUri}" />
		<table>
			<tbody>
				<tr>
					<td><g:message code="login.page.label.username" />:</td>
					<td><input type="text" name="username" value="${username}" /></td>
				</tr>
				<tr>
					<td><g:message code="login.page.label.password" />:</td>
					<td><input type="password" name="password" value="" /></td>
				</tr>
				<tr>
					<td><g:message code="login.page.label.rememberMe" />:</td>
					<td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="${message(code:'login.page.button.login')}" /></td>
				</tr>
			</tbody>
		</table>
	</g:form>
</body>
</html>
