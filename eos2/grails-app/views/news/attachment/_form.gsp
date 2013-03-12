<%@ page import="org.leaf.eos2.news.Attachment" %>



<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'entity', 'error')} required">
	<label for="entity">
		<g:message code="attachment.entity.label" default="Entity" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="entity.id" from="${org.leaf.eos2.news.Entity.list()}" optionKey="id" value="${attachmentInstance?.entity?.id}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="attachment.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="2" value="${fieldValue(bean: attachmentInstance, field: 'serialNumber')}" />

</div>

<div class="fieldcontain ${hasErrors(bean: attachmentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="attachment.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" value="${fieldValue(bean: attachmentInstance, field: 'name')}" />

</div>

