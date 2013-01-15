<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesOrder.label', default: 'SalesOrder')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
		<g:javascript src="jquery.hotkeys.js"/>
		<g:javascript src="JQueryBlockUI.js"/>
		<g:javascript src="my-numeric.js"/>
		<g:javascript src="jquery-ui-numeric-min.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.autocomplete.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
	</head>
	<body>
		<a href="#edit-salesOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="edit-salesOrder" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${salesOrderInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${salesOrderInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${salesOrderInstance?.id}" />
				<g:hiddenField name="version" value="${salesOrderInstance?.version}" />
				<fieldset class="form">
					<g:render template="/b2b/salesOrder221/edit" />
				</fieldset>
				<fieldset class="buttons">
					<shiro:hasPermission permission="${controllerName}:update">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					</shiro:hasPermission>
					
					<shiro:hasPermission permission="${controllerName}:delete">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</shiro:hasPermission>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
