<%@ page import="org.leaf.eos2.wf.WorkflowHistory" %>

<ol class="property-list workflowHistory">

	<li class="fieldcontain">
		<span id="step-label" class="property-label"><g:message code="workflowHistory.step.label" default="Step" /></span>
		<span class="property-value" aria-labelledby="step-label"><g:link controller="workflowStep" action="show" id="${workflowHistoryInstance?.step?.id}">${workflowHistoryInstance?.step?.encodeAsHTML()}</g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="objectName-label" class="property-label"><g:message code="workflowHistory.objectName.label" default="Object Name" /></span>
		<span class="property-value" aria-labelledby="objectName-label"><g:fieldValue bean="${workflowHistoryInstance}" field="objectName"/></span>
	</li>
	<li class="fieldcontain">
		<span id="objectId-label" class="property-label"><g:message code="workflowHistory.objectId.label" default="Object Id" /></span>
		<span class="property-value" aria-labelledby="objectId-label"><g:fieldValue bean="${workflowHistoryInstance}" field="objectId"/></span>
	</li>

	<li class="fieldcontain">
		<span id="status-label" class="property-label"><g:message code="workflowHistory.status.label" default="Status" /></span>
		<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${workflowHistoryInstance}" field="status"/></span>
	</li>

	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="workflowHistory.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${workflowHistoryInstance}" field="description"/></span>
	</li>

	<li class="fieldcontain">
		<span id="createdBy-label" class="property-label"><g:message code="workflowHistory.createdBy.label" default="Created By" /></span>
		<span class="property-value" aria-labelledby="createdBy-label"><g:link controller="user" action="show" id="${workflowHistoryInstance?.createdBy?.id}">${workflowHistoryInstance?.createdBy?.encodeAsHTML()}, <g:formatDate date="${workflowHistoryInstance?.dateCreated}" formatName="custom.datetime.format"/></g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="lastModifiedBy-label" class="property-label"><g:message code="workflowHistory.lastModifiedBy.label" default="Last Modified By" /></span>
		<span class="property-value" aria-labelledby="lastModifiedBy-label"><g:link controller="user" action="show" id="${workflowHistoryInstance?.lastModifiedBy?.id}">${workflowHistoryInstance?.lastModifiedBy?.encodeAsHTML()}, <g:formatDate date="${workflowHistoryInstance?.lastUpdated}" formatName="custom.datetime.format"/></g:link></span>
	</li>

</ol>
