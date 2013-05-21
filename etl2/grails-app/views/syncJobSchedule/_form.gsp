<%@ page import="org.leaf.etl.SyncJobSchedule" %>



<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'job', 'error')} required">
	<label for="job">
		<g:message code="syncJobSchedule.job.label" default="Job" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="job" name="job.id" from="${org.leaf.etl.SyncJob.list()}" optionKey="id" required="" value="${syncJobScheduleInstance?.job?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'beginTime', 'error')} required">
	<label for="beginTime">
		<g:message code="syncJobSchedule.beginTime.label" default="Begin Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="beginTime" precision="day"  value="${syncJobScheduleInstance?.beginTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'endTime', 'error')} required">
	<label for="endTime">
		<g:message code="syncJobSchedule.endTime.label" default="End Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endTime" precision="day"  value="${syncJobScheduleInstance?.endTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'days', 'error')} required">
	<label for="days">
		<g:message code="syncJobSchedule.days.label" default="Days" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="days" type="number" value="${syncJobScheduleInstance.days}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'finished', 'error')} ">
	<label for="finished">
		<g:message code="syncJobSchedule.finished.label" default="Finished" />
		
	</label>
	<g:checkBox name="finished" value="${syncJobScheduleInstance?.finished}" />
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'histories', 'error')} ">
	<label for="histories">
		<g:message code="syncJobSchedule.histories.label" default="Histories" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${syncJobScheduleInstance?.histories?}" var="h">
    <li><g:link controller="syncJobHistory" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="syncJobHistory" action="create" params="['syncJobSchedule.id': syncJobScheduleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'syncJobHistory.label', default: 'SyncJobHistory')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: syncJobScheduleInstance, field: 'working', 'error')} ">
	<label for="working">
		<g:message code="syncJobSchedule.working.label" default="Working" />
		
	</label>
	<g:checkBox name="working" value="${syncJobScheduleInstance?.working}" />
</div>

