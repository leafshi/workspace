<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesOrder.label', default: 'SalesOrder')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
			~
			${salesOrderInstance?.recordType?.encodeAsHTML()}			
			~
			${salesOrderInstance?.id}
		</title>
	</head>
	<body>
		<a href="#show-salesOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-salesOrder" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
				~
				${salesOrderInstance?.recordType?.encodeAsHTML()}
				~
				${salesOrderInstance?.id}
			</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/b2b/salesOrder224/show" />
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${salesOrderInstance?.id}" />
					<g:link class="edit" action="edit" id="${salesOrderInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
