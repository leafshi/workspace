<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'step', 'error')} required">
	<label for="step">
		<g:message code="workflowHistory.step.label" default="Step" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="step" name="step.id" from="${org.leaf.eos2.wf.WorkflowStep.list()}" optionKey="id" required="" value="${workflowHistoryInstance?.step?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'objectName', 'error')} required">
	<label for="objectName">
		<g:message code="workflowHistory.objectName.label" default="Object Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objectName" maxlength="30" required="" value="${workflowHistoryInstance?.objectName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'objectId', 'error')} required">
	<label for="objectId">
		<g:message code="workflowHistory.objectId.label" default="Object Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="objectId" type="number" value="${workflowHistoryInstance.objectId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="workflowHistory.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${workflowHistoryInstance.constraints.status.inList}" required="" value="${workflowHistoryInstance?.status}" valueMessagePrefix="workflowHistory.status"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="workflowHistory.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${workflowHistoryInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="workflowHistory.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${workflowHistoryInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowHistoryInstance, field: 'lastModifiedBy', 'error')} required">
	<label for="lastModifiedBy">
		<g:message code="workflowHistory.lastModifiedBy.label" default="Last Modified By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lastModifiedBy" name="lastModifiedBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${workflowHistoryInstance?.lastModifiedBy?.id}" class="many-to-one"/>
</div>

