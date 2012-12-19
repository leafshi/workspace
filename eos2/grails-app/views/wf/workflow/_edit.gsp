<%@ page import="org.leaf.eos2.wf.Workflow" %>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="workflow.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${workflowInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'domain', 'error')} required">
	<label for="domain">
		<g:message code="workflow.domain.label" default="Domain" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="domain" maxlength="30" required="" value="${workflowInstance?.domain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'recordType', 'error')} ">
	<label for="recordType">
		<g:message code="workflow.recordType.label" default="Record Type" />
	</label>
	<g:select id="recordType" name="recordType.id" from="${org.leaf.eos2.admin.RecordType.list()}" optionKey="id" value="${workflowInstance?.recordType?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="workflow.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${workflowInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="workflow.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${workflowInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="workflow.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="department" name="department.id" from="${org.leaf.eos2.b2b.Department.list()}" optionKey="id" required="" value="${workflowInstance?.department?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: workflowInstance, field: 'steps', 'error')} ">
	<label for="steps">
		<g:message code="workflow.steps.label" default="Steps" />
	</label>
	
	<ul class="one-to-many">
		<g:each in="${workflowInstance?.steps?}" var="s">
    	<li><g:link controller="workflowStep" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
		</g:each>
		
		<li class="add">
			<g:link controller="workflowStep" action="create" params="['workflow.id': workflowInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'workflowStep.label', default: 'WorkflowStep')])}</g:link>
		</li>
	</ul>

</div>
