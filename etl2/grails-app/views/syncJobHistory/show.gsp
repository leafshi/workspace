
<%@ page import="org.leaf.etl.SyncJobHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'syncJobHistory.label', default: 'SyncJobHistory')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-syncJobHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-syncJobHistory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list syncJobHistory">
			
				<g:if test="${syncJobHistoryInstance?.jobSchedule}">
				<li class="fieldcontain">
					<span id="jobSchedule-label" class="property-label"><g:message code="syncJobHistory.jobSchedule.label" default="Job Schedule" /></span>
					
						<span class="property-value" aria-labelledby="jobSchedule-label"><g:link controller="syncJobSchedule" action="show" id="${syncJobHistoryInstance?.jobSchedule?.id}">${syncJobHistoryInstance?.jobSchedule?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${syncJobHistoryInstance?.primaryKey}">
				<li class="fieldcontain">
					<span id="primaryKey-label" class="property-label"><g:message code="syncJobHistory.primaryKey.label" default="Primary Key" /></span>
					
						<span class="property-value" aria-labelledby="primaryKey-label"><g:fieldValue bean="${syncJobHistoryInstance}" field="primaryKey"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${syncJobHistoryInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="syncJobHistory.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${syncJobHistoryInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${syncJobHistoryInstance?.endTime}">
				<li class="fieldcontain">
					<span id="endTime-label" class="property-label"><g:message code="syncJobHistory.endTime.label" default="End Time" /></span>
					
						<span class="property-value" aria-labelledby="endTime-label"><g:formatDate date="${syncJobHistoryInstance?.endTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${syncJobHistoryInstance?.isOkay}">
				<li class="fieldcontain">
					<span id="isOkay-label" class="property-label"><g:message code="syncJobHistory.isOkay.label" default="Is Okay" /></span>
					
						<span class="property-value" aria-labelledby="isOkay-label"><g:formatBoolean boolean="${syncJobHistoryInstance?.isOkay}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${syncJobHistoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${syncJobHistoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
