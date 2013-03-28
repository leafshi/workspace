<%@ page import="org.leaf.eos2.wf.WorkflowStep" %>

<div class="fieldcontain ${hasErrors(bean: workflowStepInstance, field: 'assignee', 'error')} ">
	<label for="assignee">
		<g:message code="workflowStep.assignee.label" default="Assignee" />
		
	</label>
	<g:select name="assignee.id" from="${org.leaf.eos2.admin.Group.list()}" optionKey="id" value="${workflowStepInstance?.assignee?.id}" noSelection="['null': '']" />

</div>
