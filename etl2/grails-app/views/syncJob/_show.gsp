<ol class="property-list syncJob">

<g:if test="${syncJobInstance?.name}">
<li class="fieldcontain">
	<span id="name-label" class="property-label"><g:message code="syncJob.name.label" default="Name" /></span>
	
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${syncJobInstance}" field="name"/></span>
	
</li>
</g:if>

<g:if test="${syncJobInstance?.source}">
<li class="fieldcontain">
	<span id="source-label" class="property-label"><g:message code="syncJob.source.label" default="Source" /></span>
	
		<span class="property-value" aria-labelledby="source-label"><g:fieldValue bean="${syncJobInstance}" field="source"/></span>
	
</li>
</g:if>

<g:if test="${syncJobInstance?.target}">
<li class="fieldcontain">
	<span id="target-label" class="property-label"><g:message code="syncJob.target.label" default="Target" /></span>
	
		<span class="property-value" aria-labelledby="target-label"><g:fieldValue bean="${syncJobInstance}" field="target"/></span>
	
</li>
</g:if>

<g:if test="${syncJobInstance?.priority}">
<li class="fieldcontain">
	<span id="priority-label" class="property-label"><g:message code="syncJob.priority.label" default="Priority" /></span>
	<span class="property-value" aria-labelledby="priority-label"><g:fieldValue bean="${syncJobInstance}" field="priority"/></span>
</li>
</g:if>

<g:if test="${syncJobInstance?.scriptName}">
<li class="fieldcontain">
	<span id="scriptName-label" class="property-label"><g:message code="syncJob.scriptName.label" default="Script Name" /></span>
	<span class="property-value" aria-labelledby="scriptName-label"><g:fieldValue bean="${syncJobInstance}" field="scriptName"/></span>
</li>
</g:if>

<g:if test="${syncJobInstance?.dateCreated}">
<li class="fieldcontain">
	<span id="dateCreated-label" class="property-label"><g:message code="syncJob.dateCreated.label" default="Date Created" /></span>
	<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${syncJobInstance?.dateCreated}" /></span>
</li>
</g:if>

<g:if test="${syncJobInstance?.lastUpdated}">
<li class="fieldcontain">
	<span id="lastUpdated-label" class="property-label"><g:message code="syncJob.lastUpdated.label" default="Last Updated" /></span>
	<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${syncJobInstance?.lastUpdated}" /></span>
</li>
</g:if>
</ol>


<g:if test="${syncJobInstance?.schedules}">
<table>
	<thead>
		<tr>
			<th><g:message code="syncJobSchedule.job.label" default="Job" /></th>
			<th><g:message code="syncJobSchedule.beginTime.label" default="Begin Time" /></th>
			<th><g:message code="syncJobSchedule.endTime.label" default="End Time" /></th>
			<th><g:message code="syncJobSchedule.days.label" default="Days" /></th>
			<th><g:message code="syncJobSchedule.finished.label" default="Finished" /></th>
			<th><g:message code="syncJobSchedule.working.label" default="Working" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${syncJobInstance.schedules.sort{return it.id}}" status="i" var="syncJobScheduleInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: syncJobScheduleInstance, field: "id")}</td>
			<td><g:formatDate date="${syncJobScheduleInstance.beginTime}" /></td>
			<td><g:formatDate date="${syncJobScheduleInstance.endTime}" /></td>
			<td>${fieldValue(bean: syncJobScheduleInstance, field: "days")}</td>
			<td><g:formatBoolean boolean="${syncJobScheduleInstance.finished}" /></td>
			<td><g:formatBoolean boolean="${syncJobScheduleInstance.working}" /></td>
		</tr>
	</g:each>
	</tbody>
</table>
</g:if>
