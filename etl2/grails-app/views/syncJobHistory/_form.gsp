<%@ page import="org.leaf.etl.SyncJobHistory" %>



<div class="fieldcontain ${hasErrors(bean: syncJobHistoryInstance, field: 'jobSchedule', 'error')} required">
	<label for="jobSchedule">
		<g:message code="syncJobHistory.jobSchedule.label" default="Job Schedule" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="jobSchedule" name="jobSchedule.id" from="${org.leaf.etl.SyncJobSchedule.list()}" optionKey="id" required="" value="${syncJobHistoryInstance?.jobSchedule?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobHistoryInstance, field: 'primaryKey', 'error')} required">
	<label for="primaryKey">
		<g:message code="syncJobHistory.primaryKey.label" default="Primary Key" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="primaryKey" maxlength="100" required="" value="${syncJobHistoryInstance?.primaryKey}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobHistoryInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="syncJobHistory.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" maxlength="10" required="" value="${syncJobHistoryInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobHistoryInstance, field: 'endTime', 'error')} ">
	<label for="endTime">
		<g:message code="syncJobHistory.endTime.label" default="End Time" />
		
	</label>
	<g:datePicker name="endTime" precision="day"  value="${syncJobHistoryInstance?.endTime}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobHistoryInstance, field: 'isOkay', 'error')} ">
	<label for="isOkay">
		<g:message code="syncJobHistory.isOkay.label" default="Is Okay" />
		
	</label>
	<g:checkBox name="isOkay" value="${syncJobHistoryInstance?.isOkay}" />
</div>

