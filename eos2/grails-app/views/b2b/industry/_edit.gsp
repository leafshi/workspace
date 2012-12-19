<%@ page import="org.leaf.eos2.b2b.Industry" %>

<div class="fieldcontain ${hasErrors(bean: industryInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="industry.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="6" required="" value="${industryInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: industryInstance, field: 'shortName', 'error')} required">
	<label for="shortName">
		<g:message code="industry.shortName.label" default="Short Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="shortName" maxlength="10" required="" value="${industryInstance?.shortName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: industryInstance, field: 'fullName', 'error')} required">
	<label for="fullName">
		<g:message code="industry.fullName.label" default="Full Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fullName" maxlength="50" required="" value="${industryInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: industryInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="industry.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${industryInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: industryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="industry.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${industryInstance?.description}"/>
</div>
