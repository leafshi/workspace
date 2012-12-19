<%@ page import="org.leaf.eos2.b2b.Department" %>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'serialNumber', 'error')} ">
	<label for="serialNumber">
		<g:message code="department.serialNumber.label" default="Serial Number" />
	</label>
	<g:textField name="serialNumber" maxlength="6" value="${departmentInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="department.name.label" default="Name" />
	</label>
	<g:textField name="name" maxlength="20" value="${departmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="department.type.label" default="Type" />
	</label>
	<g:textField name="type" maxlength="2" value="${departmentInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="department.description.label" default="Description" />
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${departmentInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="department.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${departmentInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'parentDept', 'error')} ">
	<label for="parentDept">
		<g:message code="department.parentDept.label" default="Parent Dept" />
	</label>
	<g:select id="parentDept" name="parentDept.id" from="${org.leaf.eos2.b2b.Department.list()}" optionKey="id" value="${departmentInstance?.parentDept?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>
