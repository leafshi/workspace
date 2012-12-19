<%@ page import="org.leaf.eos2.admin.Group" %>

<div class="fieldcontain ${hasErrors(bean: groupInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="group.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" required="" value="${groupInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: groupInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="group.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${groupInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: groupInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="group.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${groupInstance?.description}"/>
</div>
