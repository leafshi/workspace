<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>
<table>
	<thead>
		<tr>
			<th><g:message code="workflowHistory.step.label" default="Step" /></th>
			<g:sortableColumn property="objectName" title="${message(code: 'workflowHistory.objectName.label', default: 'Object Name')}" />
			<g:sortableColumn property="objectId" title="${message(code: 'workflowHistory.objectId.label', default: 'Object Id')}" />
			<g:sortableColumn property="status" title="${message(code: 'workflowHistory.status.label', default: 'Status')}" />
			<g:sortableColumn property="description" title="${message(code: 'workflowHistory.description.label', default: 'Description')}" />
			<th><g:message code="workflowHistory.createdBy.label" default="Created By" /></th>
			<th><g:message code="workflowHistory.lastModifiedBy.label" default="Last Modified By" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${workflowHistoryInstanceList}" status="i" var="workflowHistoryInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${workflowHistoryInstance.id}">${fieldValue(bean: workflowHistoryInstance, field: "step")}</g:link></td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "objectName")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "objectId")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "status")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "description")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "createdBy")}, ${formatDate(date: workflowHistoryInstance?.dateCreated, formatName: "custom.datetime.format")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "lastModifiedBy")}, ${formatDate(date: workflowHistoryInstance?.lastUpdated, formatName: "custom.datetime.format")}</td>
		</tr>
	</g:each>
	</tbody>
</table>
