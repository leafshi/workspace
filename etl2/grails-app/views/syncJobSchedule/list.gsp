
<%@ page import="org.leaf.etl.SyncJobSchedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-syncJobSchedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-syncJobSchedule" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="syncJobSchedule.job.label" default="Job" /></th>
					
						<g:sortableColumn property="beginTime" title="${message(code: 'syncJobSchedule.beginTime.label', default: 'Begin Time')}" />
					
						<g:sortableColumn property="endTime" title="${message(code: 'syncJobSchedule.endTime.label', default: 'End Time')}" />
					
						<g:sortableColumn property="days" title="${message(code: 'syncJobSchedule.days.label', default: 'Days')}" />
					
						<g:sortableColumn property="finished" title="${message(code: 'syncJobSchedule.finished.label', default: 'Finished')}" />
					
						<g:sortableColumn property="working" title="${message(code: 'syncJobSchedule.working.label', default: 'Working')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${syncJobScheduleInstanceList}" status="i" var="syncJobScheduleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${syncJobScheduleInstance.id}">${fieldValue(bean: syncJobScheduleInstance, field: "job")}</g:link></td>
					
						<td><g:formatDate date="${syncJobScheduleInstance.beginTime}" /></td>
					
						<td><g:formatDate date="${syncJobScheduleInstance.endTime}" /></td>
					
						<td>${fieldValue(bean: syncJobScheduleInstance, field: "days")}</td>
					
						<td><g:formatBoolean boolean="${syncJobScheduleInstance.finished}" /></td>
					
						<td><g:formatBoolean boolean="${syncJobScheduleInstance.working}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${syncJobScheduleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
