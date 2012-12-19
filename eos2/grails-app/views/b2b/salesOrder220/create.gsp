<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesOrder.label', default: 'SalesOrder')}" />
		<title>
			<g:message code="default.create.label" args="[entityName]" />
			~
			${salesOrderInstance?.recordType?.encodeAsHTML()}
		</title>
		<g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
		<g:javascript src="jquery.hotkeys.js"/>
		<g:javascript src="JQueryBlockUI.js"/>
		<g:javascript src="my-numeric.js"/>
		<g:javascript src="jquery-ui-numeric-min.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.autocomplete.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
	</head>
	<body>
		<a href="#create-salesOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="create-salesOrder" class="content scaffold-create" role="main">
			<h1>
				<g:message code="default.create.label" args="[entityName]" />
				~
				${salesOrderInstance?.recordType?.encodeAsHTML()}
			</h1>
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
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="/b2b/salesOrder220/edit"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
