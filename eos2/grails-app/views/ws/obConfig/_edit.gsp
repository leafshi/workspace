<%@ page import="org.leaf.eos2.ws.ObConfig" %>



<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'objectName', 'error')} required">
	<label for="objectName">
		<g:message code="obConfig.objectName.label" default="Object Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objectName" maxlength="60" required="" value="${obConfigInstance?.objectName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'method', 'error')} required">
	<label for="method">
		<g:message code="obConfig.method.label" default="Method" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="method" maxlength="100" required="" value="${obConfigInstance?.method}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'asynchronous', 'error')} ">
	<label for="asynchronous">
		<g:message code="obConfig.asynchronous.label" default="Asynchronous" />
		
	</label>
	<g:checkBox name="asynchronous" value="${obConfigInstance?.asynchronous}" />
</div>

<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'priority', 'error')} required">
	<label for="priority">
		<g:message code="obConfig.priority.label" default="Priority" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="priority" type="number" min="0" max="20" value="${obConfigInstance.priority}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'defaultSendErrorLimit', 'error')} ">
	<label for="defaultSendErrorLimit">
		<g:message code="obConfig.defaultSendErrorLimit.label" default="Default Send Error Limit" />
		
	</label>
	<g:field name="defaultSendErrorLimit" type="number" max="255" value="${obConfigInstance.defaultSendErrorLimit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: obConfigInstance, field: 'defaultGetErrorLimit', 'error')} ">
	<label for="defaultGetErrorLimit">
		<g:message code="obConfig.defaultGetErrorLimit.label" default="Default Get Error Limit" />
		
	</label>
	<g:field name="defaultGetErrorLimit" type="number" max="255" value="${obConfigInstance.defaultGetErrorLimit}"/>
</div>

