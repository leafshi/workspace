<%@ page import="org.leaf.eos2.wf.Workflow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'workflow.label', default: 'Workflow')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
			~
			<g:fieldValue bean="${workflowInstance}" field="name"/>
		</title>
	</head>
	<body>
		<a href="#show-workflow" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-workflow" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/wf/workflow/show" />
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${workflowInstance?.id}" />
					<g:link class="edit" action="edit" id="${workflowInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
