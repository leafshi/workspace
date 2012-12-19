
<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'workflowHistory.label', default: 'WorkflowHistory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-workflowHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-workflowHistory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/wf/workflowHistory/list" />
			<div class="pagination">
				<g:paginate total="${workflowHistoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
