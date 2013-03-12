<%@ page import="org.leaf.eos2.news.Entity" %>

<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="entity.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="level" value="${fieldValue(bean: entityInstance, field: 'level')}" from="${['紧急', '重要', '普通']}"/>
	<g:textField name="title" maxlength="100" value="${fieldValue(bean: entityInstance, field: 'title')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="entity.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="content" rows="5" cols="40" value="${fieldValue(bean: entityInstance, field: 'content')}" />
</div>

<!--created by-->
<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="entity.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<span>${entityInstance?.createdBy?.encodeAsHTML()}</span>
	<g:hiddenField name="createdBy.id" value="${entityInstance?.createdBy?.id}" />
</div>

<!--last modified by-->
<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'lastModifiedBy', 'error')} required">
	<label for="lastModifiedBy">
		<g:message code="entity.lastModifiedBy.label" default="Last Modified By" />
		<span class="required-indicator">*</span>
	</label>
	<span>${entityInstance?.lastModifiedBy?.encodeAsHTML()}</span>
	<g:hiddenField name="lastModifiedBy.id" value="${entityInstance?.lastModifiedBy?.id}" />
</div>