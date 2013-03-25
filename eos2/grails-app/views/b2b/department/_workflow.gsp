<%@ page import="org.leaf.eos2.wf.Workflow" %>
<g:set var="workflowInstanceList" value="${Workflow.findAllByDepartment(departmentInstance)}" />
<g:if test="${workflowInstanceList.size() > 0}">
<table>
	<caption>${message(code: 'workflow.label', default: 'Workflow')}</caption>
	<thead>
		<tr>
			<g:sortableColumn property="name" title="${message(code: 'workflow.name.label', default: 'Name')}" />
			<g:sortableColumn property="domain" title="${message(code: 'workflow.domain.label', default: 'Domain')}" />
			<th><g:message code="workflow.recordType.label" default="Record Type" /></th>
			<g:sortableColumn property="isActive" title="${message(code: 'workflow.isActive.label', default: 'Is Active')}" />
			<g:sortableColumn property="description" title="${message(code: 'workflow.description.label', default: 'Description')}" />
			<th><g:message code="workflow.department.label" default="Department" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${workflowInstanceList}" status="i" var="workflowInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link controller="workflow" action="show" id="${workflowInstance.id}">${fieldValue(bean: workflowInstance, field: "name")}</g:link></td>
			<td>${fieldValue(bean: workflowInstance, field: "domain")}</td>
			<td>${fieldValue(bean: workflowInstance, field: "recordType")}</td>
			<td><g:formatBoolean boolean="${workflowInstance.isActive}" /></td>
			<td>${fieldValue(bean: workflowInstance, field: "description")}</td>
			<td>${fieldValue(bean: workflowInstance, field: "department")}</td>
		</tr>
		</g:each>
	</tbody>
</table>
</g:if>
