<%@ page import="org.leaf.eos2.shiro.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/shiro/user/show" />
			<g:render template="/shiro/user/shareRole" />	
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:actionSubmit class="edit" action="resetPassword" value="${message(code: 'user.resetPassword', default: 'Reset Password')}" />

					<g:link class="edit" controller="userExtend" action="activeUser" id="${userInstance?.id}">Active</g:link>
					<g:link class="edit" controller="userExtend" action="deactivateUser" id="${userInstance?.id}">deactivateUser</g:link>

					<g:link class="edit" controller="userExtend" action="registerECSUser" id="${userInstance?.id}">registerECSUser</g:link>
					<g:link class="edit" controller="userExtend" action="modifyECSUser" id="${userInstance?.id}">modifyECSUser</g:link>

					<g:link class="edit" controller="userExtend" action="activateECSUser" id="${userInstance?.id}">activateECSUser</g:link>
					<g:link class="edit" controller="userExtend" action="deactivateECSUser" id="${userInstance?.id}">deactivateECSUser</g:link>

				</fieldset>
			</g:form>
		</div>
	</body>
</html>
