<%@ page import="org.leaf.eos2.admin.Profile" %>
<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="profile.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="40" required="" value="${profileInstance?.name}"/>
</div>

