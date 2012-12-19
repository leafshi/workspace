<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>
<table>
	<caption><g:message code="workflowHistory.label" default="Workflow History" /></caption>
	<thead>
		<tr>
			<th><g:message code="workflowHistory.id.label" default="Id" /></th>
			<th><g:message code="workflowHistory.step.label" default="Step" /></th>
			<th><g:message code="workflowHistory.status.label" default="Status" /></th>
			<th><g:message code="workflowHistory.description.label" default="Description" /></th>
			<th><g:message code="workflowHistory.createdBy.label" default="Created By" /></th>
			<th><g:message code="workflowHistory.lastModifiedBy.label" default="Last Modified By" /></th>
			<th><g:message code="workflowHistory.actions" default="Actions" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${workflowHistoryInstanceList}" status="i" var="workflowHistoryInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: workflowHistoryInstance, field: "id")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "step")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "status")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "description")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "createdBy")}, ${formatDate(date: workflowHistoryInstance?.dateCreated, formatName: "custom.datetime.format")}</td>
			<td>${fieldValue(bean: workflowHistoryInstance, field: "lastModifiedBy")}, ${formatDate(date: workflowHistoryInstance?.lastUpdated, formatName: "custom.datetime.format")}</td>
			<td>
				<g:each in="${workflowHistoryInstance?.step?.actions}" status="j" var="workflowActionInstance">
					<g:if test="${workflowHistoryInstance?.status == '进行中'}">
						<g:link controller="workflowApproval" action="confirm" 
							params="[objectName: 'salesOrder', objectId : objectId, historyId :workflowHistoryInstance?.id , actionId : workflowActionInstance?.id, ownerId : ownerId, version : workflowHistoryInstance?.version]"
							>
							[${fieldValue(bean: workflowActionInstance, field: "name")}]
						</g:link>
					</g:if>
				</g:each>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
