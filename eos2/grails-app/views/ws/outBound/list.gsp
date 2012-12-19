
<%@ page import="org.leaf.eos2.ws.OutBound" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outBound.label', default: 'OutBound')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-outBound" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-outBound" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:render template="/ws/outBound/list" />
			
			<div class="pagination">
				<g:paginate total="${outBoundInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
