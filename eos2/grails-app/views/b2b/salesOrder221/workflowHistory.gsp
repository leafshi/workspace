<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>
<table id="workflowHistoryInstanceList">
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
				<shiro:hasPermission permission="workflowApproval:check">
				<g:each in="${workflowHistoryInstance?.step?.actions}" status="j" var="workflowActionInstance">
				<g:set 
					var="check_approval_result" 
					value="${include(controller : 'workflowApproval', action : 'check', params : [historyId :workflowHistoryInstance?.id , actionId : workflowActionInstance?.id, ownerId : ownerId, version : workflowHistoryInstance?.version] )}" />
					<g:if test="${check_approval_result == 'Y'}">
						<shiro:hasPermission permission="workflowApproval:approval">
						<g:set var="need_approval" value="Y" />
							<a href="#" onclick="open_approval_dialog2(${workflowHistoryInstance?.id}, ${workflowActionInstance?.id}, ${workflowHistoryInstance?.version}, '${workflowHistoryInstance?.step}', '${workflowActionInstance?.name}');">[${fieldValue(bean: workflowActionInstance, field: "name")}]</a>
						</shiro:hasPermission>
					</g:if>
				</g:each>
				</shiro:hasPermission>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
<div id="approval_dialog2" style="display:none;">
	<g:textArea name="description" value="" rows="5" cols="40"/>
</div>
<script type="text/javascript">
function open_approval_dialog2(historyId, actionId, version, stepName, actionName){
	$("#approval_dialog2").dialog({
		modal: true, 
		width:'auto', 
		//position: {  of : this },
		title: stepName + "." +actionName,
		buttons: {
			"${message(code: 'default.button.approval', default: 'Approval')}": function() {
				if(confirm("${message(code: 'default.button.approval.confirm.message', default: 'Are you sure?')}" + "["+stepName+"."+actionName+"]")){
					$( this ).dialog( "close" );
					$.blockUI();
					$.ajax({
						url: "${createLink(controller:'workflowApproval', action: 'approval')}",
						data: { 
							objectName: 'salesOrder'
							, objectId : ${objectId}
							, historyId :historyId
							, actionId : actionId
							, ownerId : ${ownerId}
							, description : $("#approval_dialog2 :input[name='description']").val()
							, version : version
						},
						type: "POST",
						success: function(data){
							location.reload();
						},
						error : function(error) { 
							location.reload();
						}
					});
				}
			},
			"${message(code:'salesOrder.page.delete.dialog.cancel', default:'Cancel!')}": function() {
				$( this ).dialog( "close" );
			}
		}
	});
}
</script>
