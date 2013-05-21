<%@ page import="org.leaf.etl.SyncJobHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'syncJobHistory.label', default: 'SyncJobHistory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-syncJobHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-syncJobHistory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="syncJobHistory.jobSchedule.label" default="Job Schedule" /></th>
					
						<g:sortableColumn property="primaryKey" title="${message(code: 'syncJobHistory.primaryKey.label', default: 'Primary Key')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'syncJobHistory.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="endTime" title="${message(code: 'syncJobHistory.endTime.label', default: 'End Time')}" />
					
						<g:sortableColumn property="isOkay" title="${message(code: 'syncJobHistory.isOkay.label', default: 'Is Okay')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${syncJobHistoryInstanceList}" status="i" var="syncJobHistoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${syncJobHistoryInstance.id}">${fieldValue(bean: syncJobHistoryInstance, field: "jobSchedule")}</g:link></td>
					
						<td>${fieldValue(bean: syncJobHistoryInstance, field: "primaryKey")}</td>
					
						<td>${fieldValue(bean: syncJobHistoryInstance, field: "type")}</td>
					
						<td><g:formatDate date="${syncJobHistoryInstance.endTime}" /></td>
					
						<td><g:formatBoolean boolean="${syncJobHistoryInstance.isOkay}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${syncJobHistoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
