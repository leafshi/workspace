<%@ page import="org.leaf.eos2.admin.RecordType" %>

<div class="fieldcontain ${hasErrors(bean: recordTypeInstance, field: 'serialNumber', 'error')} ">
	<label for="serialNumber">
		<g:message code="recordType.serialNumber.label" default="Serial Number" />
	</label>
	<g:textField name="serialNumber" maxlength="3" value="${recordTypeInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="recordType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" required="" value="${recordTypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTypeInstance, field: 'domain', 'error')} required">
	<label for="domain">
		<g:message code="recordType.domain.label" default="Domain" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="domain" maxlength="20" required="" value="${recordTypeInstance?.domain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTypeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="recordType.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${recordTypeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordTypeInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="recordType.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${recordTypeInstance?.isActive}" />
</div>
