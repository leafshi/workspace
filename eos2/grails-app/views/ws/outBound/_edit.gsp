<%@ page import="org.leaf.eos2.ws.OutBound" %>



<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'objectName', 'error')} required">
	<label for="objectName">
		<g:message code="outBound.objectName.label" default="Object Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objectName" maxlength="60" required="" value="${outBoundInstance?.objectName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'objectId', 'error')} required">
	<label for="objectId">
		<g:message code="outBound.objectId.label" default="Object Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="objectId" type="number" value="${outBoundInstance.objectId}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'method', 'error')} required">
	<label for="method">
		<g:message code="outBound.method.label" default="Method" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="method" maxlength="100" required="" value="${outBoundInstance?.method}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'asynchronous', 'error')} ">
	<label for="asynchronous">
		<g:message code="outBound.asynchronous.label" default="Asynchronous" />
		
	</label>
	<g:checkBox name="asynchronous" value="${outBoundInstance?.asynchronous}" />
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'priority', 'error')} ">
	<label for="priority">
		<g:message code="outBound.priority.label" default="Priority" />
		
	</label>
	<g:field name="priority" type="number" min="0" max="20" value="${outBoundInstance.priority}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="outBound.status.label" default="Status" />
		
	</label>
	<g:textField name="status" maxlength="20" value="${outBoundInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'result', 'error')} ">
	<label for="result">
		<g:message code="outBound.result.label" default="Result" />
		
	</label>
	<g:textArea name="result" cols="40" rows="5" maxlength="255" value="${outBoundInstance?.result}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'error', 'error')} ">
	<label for="error">
		<g:message code="outBound.error.label" default="Error" />
		
	</label>
	<g:textArea name="error" cols="40" rows="5" maxlength="255" value="${outBoundInstance?.error}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'jobId', 'error')} ">
	<label for="jobId">
		<g:message code="outBound.jobId.label" default="Job Id" />
		
	</label>
	<g:textField name="jobId" maxlength="64" value="${outBoundInstance?.jobId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'jobStatus', 'error')} ">
	<label for="jobStatus">
		<g:message code="outBound.jobStatus.label" default="Job Status" />
		
	</label>
	<g:textField name="jobStatus" maxlength="20" value="${outBoundInstance?.jobStatus}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'jobResult', 'error')} ">
	<label for="jobResult">
		<g:message code="outBound.jobResult.label" default="Job Result" />
		
	</label>
	<g:textArea name="jobResult" cols="40" rows="5" maxlength="255" value="${outBoundInstance?.jobResult}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'jobError', 'error')} ">
	<label for="jobError">
		<g:message code="outBound.jobError.label" default="Job Error" />
		
	</label>
	<g:textArea name="jobError" cols="40" rows="5" maxlength="255" value="${outBoundInstance?.jobError}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'retrySendError', 'error')} ">
	<label for="retrySendError">
		<g:message code="outBound.retrySendError.label" default="Retry Send Error" />
		
	</label>
	<g:field name="retrySendError" type="number" max="255" value="${outBoundInstance.retrySendError}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'retrySendErrorLimit', 'error')} ">
	<label for="retrySendErrorLimit">
		<g:message code="outBound.retrySendErrorLimit.label" default="Retry Send Error Limit" />
		
	</label>
	<g:field name="retrySendErrorLimit" type="number" max="255" value="${outBoundInstance.retrySendErrorLimit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'retryGet', 'error')} ">
	<label for="retryGet">
		<g:message code="outBound.retryGet.label" default="Retry Get" />
		
	</label>
	<g:field name="retryGet" type="number" max="255" value="${outBoundInstance.retryGet}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'retryGetError', 'error')} ">
	<label for="retryGetError">
		<g:message code="outBound.retryGetError.label" default="Retry Get Error" />
		
	</label>
	<g:field name="retryGetError" type="number" max="255" value="${outBoundInstance.retryGetError}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'retryGetErrorLimit', 'error')} ">
	<label for="retryGetErrorLimit">
		<g:message code="outBound.retryGetErrorLimit.label" default="Retry Get Error Limit" />
		
	</label>
	<g:field name="retryGetErrorLimit" type="number" max="255" value="${outBoundInstance.retryGetErrorLimit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'stage', 'error')} ">
	<label for="stage">
		<g:message code="outBound.stage.label" default="Stage" />
		
	</label>
	<g:textField name="stage" maxlength="20" value="${outBoundInstance?.stage}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="outBound.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${outBoundInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'lastModifiedBy', 'error')} required">
	<label for="lastModifiedBy">
		<g:message code="outBound.lastModifiedBy.label" default="Last Modified By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lastModifiedBy" name="lastModifiedBy.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${outBoundInstance?.lastModifiedBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outBoundInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="outBound.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${outBoundInstance?.owner?.id}" class="many-to-one"/>
</div>

