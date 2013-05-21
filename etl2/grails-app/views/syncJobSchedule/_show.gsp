<ol class="property-list syncJobSchedule">

	<g:if test="${syncJobScheduleInstance?.job}">
	<li class="fieldcontain">
		<span id="job-label" class="property-label"><g:message code="syncJobSchedule.job.label" default="Job" /></span>
		<span class="property-value" aria-labelledby="job-label"><g:link controller="syncJob" action="show" id="${syncJobScheduleInstance?.job?.id}">${syncJobScheduleInstance?.job?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>

	<g:if test="${syncJobScheduleInstance?.beginTime}">
	<li class="fieldcontain">
		<span id="beginTime-label" class="property-label"><g:message code="syncJobSchedule.beginTime.label" default="Begin Time" /></span>
		<span class="property-value" aria-labelledby="beginTime-label"><g:formatDate date="${syncJobScheduleInstance?.beginTime}" /></span>
	</li>
	</g:if>

	<g:if test="${syncJobScheduleInstance?.endTime}">
	<li class="fieldcontain">
		<span id="endTime-label" class="property-label"><g:message code="syncJobSchedule.endTime.label" default="End Time" /></span>
		<span class="property-value" aria-labelledby="endTime-label"><g:formatDate date="${syncJobScheduleInstance?.endTime}" /></span>
	</li>
	</g:if>

	<g:if test="${syncJobScheduleInstance?.days}">
	<li class="fieldcontain">
		<span id="days-label" class="property-label"><g:message code="syncJobSchedule.days.label" default="Days" /></span>
		<span class="property-value" aria-labelledby="days-label"><g:fieldValue bean="${syncJobScheduleInstance}" field="days"/></span>
	</li>
	</g:if>

	<g:if test="${syncJobScheduleInstance?.finished}">
	<li class="fieldcontain">
		<span id="finished-label" class="property-label"><g:message code="syncJobSchedule.finished.label" default="Finished" /></span>
		<span class="property-value" aria-labelledby="finished-label"><g:formatBoolean boolean="${syncJobScheduleInstance?.finished}" /></span>
	</li>
	</g:if>

	<g:if test="${syncJobScheduleInstance?.working}">
	<li class="fieldcontain">
		<span id="working-label" class="property-label"><g:message code="syncJobSchedule.working.label" default="Working" /></span>
		<span class="property-value" aria-labelledby="working-label"><g:formatBoolean boolean="${syncJobScheduleInstance?.working}" /></span>
	</li>
	</g:if>

</ol>


<g:if test="${syncJobScheduleInstance?.histories}">
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
		<g:each in="${syncJobScheduleInstance.histories.sort{return it.id}}" status="i" var="syncJobHistoryInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td>${fieldValue(bean: syncJobHistoryInstance, field: "jobSchedule")}</td>
				<td>${fieldValue(bean: syncJobHistoryInstance, field: "primaryKey")}</td>
				<td>${fieldValue(bean: syncJobHistoryInstance, field: "type")}</td>
				<td><g:formatDate date="${syncJobHistoryInstance.endTime}" /></td>
				<td><g:formatBoolean boolean="${syncJobHistoryInstance.isOkay}" /></td>
			</tr>
		</g:each>
		</tbody>
	</table>

</g:if>
