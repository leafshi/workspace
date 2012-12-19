<%@ page import="org.leaf.eos2.b2b.Staff" %>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="staff.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="5" required="" value="${staffInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="staff.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="10" required="" value="${staffInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="staff.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="department" name="department.id" from="${org.leaf.eos2.b2b.Department.list()}" optionKey="id" required="" value="${staffInstance?.department?.id}" class="many-to-one"/>
</div>
