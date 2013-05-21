<%@ page import="org.leaf.etl.SyncJob" %>



<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="syncJob.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${syncJobInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'source', 'error')} required">
	<label for="source">
		<g:message code="syncJob.source.label" default="Source" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="source" maxlength="50" required="" value="${syncJobInstance?.source}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'target', 'error')} required">
	<label for="target">
		<g:message code="syncJob.target.label" default="Target" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="target" maxlength="50" required="" value="${syncJobInstance?.target}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'priority', 'error')} required">
	<label for="priority">
		<g:message code="syncJob.priority.label" default="Priority" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="priority" type="number" value="${syncJobInstance.priority}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'scriptName', 'error')} required">
	<label for="scriptName">
		<g:message code="syncJob.scriptName.label" default="Script Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="scriptName" cols="40" rows="5" maxlength="300" required="" value="${syncJobInstance?.scriptName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: syncJobInstance, field: 'schedules', 'error')} ">
	<label for="schedules">
		<g:message code="syncJob.schedules.label" default="Schedules" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${syncJobInstance?.schedules?}" var="s">
    <li><g:link controller="syncJobSchedule" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="syncJobSchedule" action="create" params="['syncJob.id': syncJobInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'syncJobSchedule.label', default: 'SyncJobSchedule')])}</g:link>
</li>
</ul>

</div>

