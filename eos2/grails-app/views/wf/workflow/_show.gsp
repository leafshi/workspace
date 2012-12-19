<%@ page import="org.leaf.eos2.wf.Workflow" %>

<ol class="property-list workflow">
			
	<li class="fieldcontain">
		<span id="department-label" class="property-label"><g:message code="workflow.department.label" default="Department" /></span>
		<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${workflowInstance?.department?.id}">${workflowInstance?.department?.encodeAsHTML()}</g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="workflow.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${workflowInstance}" field="name"/></span>
	</li>

	<li class="fieldcontain">
		<span id="domain-label" class="property-label"><g:message code="workflow.domain.label" default="Domain" /></span>
		<span class="property-value" aria-labelledby="domain-label"><g:fieldValue bean="${workflowInstance}" field="domain"/></span>
	</li>

	<li class="fieldcontain">
		<span id="recordType-label" class="property-label"><g:message code="workflow.recordType.label" default="Record Type" /></span>
		<span class="property-value" aria-labelledby="recordType-label"><g:link controller="recordType" action="show" id="${workflowInstance?.recordType?.id}">${workflowInstance?.recordType?.encodeAsHTML()}</g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="workflow.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${workflowInstance?.isActive}" /></span>
	</li>

	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="workflow.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${workflowInstance}" field="description"/></span>
	</li>
			
</ol>

<table>
	<thead>
		<tr>		
			<th><g:message code="workflowStep.serialNumber.label" default="Serial Number" /></th>
			<th><g:message code="workflowStep.name.label" default="Name" /></th>
			<th><g:message code="workflowStep.assignee.label" default="Assignee" /></th>
			<th><g:message code="workflowStep.lockRecord.label" default="Lock Record" /></th>
			<th><g:message code="workflowStep.isBegin.label" default="Is Begin" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${workflowInstance.steps.sort{return it?.serialNumber}}" status="i" var="workflowStepInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: workflowStepInstance, field: "serialNumber")}</td>
			<td>${fieldValue(bean: workflowStepInstance, field: "name")}</td>
			<td>${fieldValue(bean: workflowStepInstance, field: "assignee")}</td>
			<td><g:formatBoolean boolean="${workflowStepInstance.lockRecord}" /></td>
			<td><g:formatBoolean boolean="${workflowStepInstance.isBegin}" /></td>
		</tr>

		<g:each in="${workflowStepInstance.actions.sort{return it?.serialNumber}}" status="j" var="workflowActionInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td>(+)${fieldValue(bean: workflowActionInstance, field: "serialNumber")}</td>
				<td>${fieldValue(bean: workflowActionInstance, field: "name")}</td>
				<td><g:message code="workflowAction.nextStep.label" default="Next Step" />:${fieldValue(bean: workflowActionInstance, field: "nextStep")}</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</g:each>
	</g:each>
	</tbody>
</table>