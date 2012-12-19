<%@ page import="org.leaf.eos2.b2b.Contract" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contract.label', default: 'Contract')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
			~
			<g:fieldValue bean="${contractInstance}" field="serialNumber"/>
		</title>
	</head>
	<body>
		<a href="#show-contract" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-contract" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/b2b/contract/show" />
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contractInstance?.id}" />
					<shiro:hasPermission permission="contract:edit">
					<g:link class="edit" action="edit" id="${contractInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					</shiro:hasPermission>
					<shiro:hasPermission permission="contract:delete">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</shiro:hasPermission>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
