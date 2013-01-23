<%@ page import="org.leaf.eos2.ws.OutBound" %>
<ol class="property-list outBound">

	<li class="fieldcontain">
		<span id="objectName-label" class="property-label"><g:message code="outBound.objectName.label" default="Object Name" /></span>
		<span class="property-value" aria-labelledby="objectName-label">
			<g:link controller="salesOrder" action="show" id="${outBoundInstance?.objectId?:-1L}">
				<g:fieldValue bean="${outBoundInstance}" field="objectName"/>
				~
				<g:fieldValue bean="${outBoundInstance}" field="objectId"/>
			</g:link>
		</span>
	</li>

	<li class="fieldcontain">
		<span id="method-label" class="property-label"><g:message code="outBound.method.label" default="Method" /></span>
		<span class="property-value" aria-labelledby="method-label"><g:fieldValue bean="${outBoundInstance}" field="method"/></span>
	</li>

	<li class="fieldcontain">
		<span id="asynchronous-label" class="property-label"><g:message code="outBound.asynchronous.label" default="Asynchronous" /></span>
		<span class="property-value" aria-labelledby="asynchronous-label"><g:formatBoolean boolean="${outBoundInstance?.asynchronous}" /></span>
	</li>

	<li class="fieldcontain">
		<span id="priority-label" class="property-label"><g:message code="outBound.priority.label" default="Priority" /></span>
		<span class="property-value" aria-labelledby="priority-label"><g:fieldValue bean="${outBoundInstance}" field="priority"/></span>
	</li>

	<li class="fieldcontain">
		<span id="status-label" class="property-label"><g:message code="outBound.status.label" default="Status" /></span>
		<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${outBoundInstance}" field="status"/></span>
	</li>

	<li class="fieldcontain">
		<span id="result-label" class="property-label"><g:message code="outBound.result.label" default="Result" /></span>
		<span class="property-value" aria-labelledby="result-label"><g:fieldValue bean="${outBoundInstance}" field="result"/></span>
	</li>

	<li class="fieldcontain">
		<span id="error-label" class="property-label"><g:message code="outBound.error.label" default="Error" /></span>
		<span class="property-value" aria-labelledby="error-label"><g:fieldValue bean="${outBoundInstance}" field="error"/></span>
	</li>

	<li class="fieldcontain">
		<span id="jobId-label" class="property-label"><g:message code="outBound.jobId.label" default="Job Id" /></span>
		<span class="property-value" aria-labelledby="jobId-label"><g:fieldValue bean="${outBoundInstance}" field="jobId"/></span>
	</li>

	<li class="fieldcontain">
		<span id="jobStatus-label" class="property-label"><g:message code="outBound.jobStatus.label" default="Job Status" /></span>
		<span class="property-value" aria-labelledby="jobStatus-label"><g:fieldValue bean="${outBoundInstance}" field="jobStatus"/></span>
	</li>

	<li class="fieldcontain">
		<span id="jobResult-label" class="property-label"><g:message code="outBound.jobResult.label" default="Job Result" /></span>
		<span class="property-value" aria-labelledby="jobResult-label"><g:fieldValue bean="${outBoundInstance}" field="jobResult"/></span>
	</li>

	<li class="fieldcontain">
		<span id="jobError-label" class="property-label"><g:message code="outBound.jobError.label" default="Job Error" /></span>
		<span class="property-value" aria-labelledby="jobError-label"><g:fieldValue bean="${outBoundInstance}" field="jobError"/></span>
	</li>

	<li class="fieldcontain">
		<span id="retrySendError-label" class="property-label"><g:message code="outBound.retrySendError.label" default="Retry Send Error" /></span>
		<span class="property-value" aria-labelledby="retrySendError-label"><g:fieldValue bean="${outBoundInstance}" field="retrySendError"/></span>
	</li>

	<li class="fieldcontain">
		<span id="retrySendErrorLimit-label" class="property-label"><g:message code="outBound.retrySendErrorLimit.label" default="Retry Send Error Limit" /></span>
		<span class="property-value" aria-labelledby="retrySendErrorLimit-label"><g:fieldValue bean="${outBoundInstance}" field="retrySendErrorLimit"/></span>
	</li>

	<li class="fieldcontain">
		<span id="retryGet-label" class="property-label"><g:message code="outBound.retryGet.label" default="Retry Get" /></span>
		<span class="property-value" aria-labelledby="retryGet-label"><g:fieldValue bean="${outBoundInstance}" field="retryGet"/></span>
	</li>

	<li class="fieldcontain">
		<span id="retryGetError-label" class="property-label"><g:message code="outBound.retryGetError.label" default="Retry Get Error" /></span>
		<span class="property-value" aria-labelledby="retryGetError-label"><g:fieldValue bean="${outBoundInstance}" field="retryGetError"/></span>
	</li>

	<li class="fieldcontain">
		<span id="retryGetErrorLimit-label" class="property-label"><g:message code="outBound.retryGetErrorLimit.label" default="Retry Get Error Limit" /></span>
		<span class="property-value" aria-labelledby="retryGetErrorLimit-label"><g:fieldValue bean="${outBoundInstance}" field="retryGetErrorLimit"/></span>
	</li>

	<li class="fieldcontain">
		<span id="stage-label" class="property-label"><g:message code="outBound.stage.label" default="Stage" /></span>
		<span class="property-value" aria-labelledby="stage-label"><g:fieldValue bean="${outBoundInstance}" field="stage"/></span>
	</li>

	<li class="fieldcontain">
		<span id="createdBy-label" class="property-label"><g:message code="outBound.createdBy.label" default="Created By" /></span>
		<span class="property-value" aria-labelledby="createdBy-label"><g:link controller="user" action="show" id="${outBoundInstance?.createdBy?.id}">${outBoundInstance?.createdBy?.encodeAsHTML()}</g:link>, <g:formatDate date="${outBoundInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>

	<li class="fieldcontain">
		<span id="lastModifiedBy-label" class="property-label"><g:message code="outBound.lastModifiedBy.label" default="Last Modified By" /></span>
		<span class="property-value" aria-labelledby="lastModifiedBy-label"><g:link controller="user" action="show" id="${outBoundInstance?.lastModifiedBy?.id}">${outBoundInstance?.lastModifiedBy?.encodeAsHTML()}</g:link>, <g:formatDate date="${outBoundInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>

	<li class="fieldcontain">
		<span id="owner-label" class="property-label"><g:message code="outBound.owner.label" default="Owner" /></span>
		<span class="property-value" aria-labelledby="owner-label"><g:link controller="user" action="show" id="${outBoundInstance?.owner?.id}">${outBoundInstance?.owner?.encodeAsHTML()}</g:link></span>
	</li>

</ol>