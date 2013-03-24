<%@ page import="org.leaf.eos2.admin.Help" %>



<div class="fieldcontain ${hasErrors(bean: helpInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="help.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="100" value="${fieldValue(bean: helpInstance, field: 'title')}" />

</div>

<div class="fieldcontain ${hasErrors(bean: helpInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="help.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="content" rows="5" cols="40" value="${fieldValue(bean: helpInstance, field: 'content')}" />

</div>

