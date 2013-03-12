<%@ page import="org.leaf.eos2.news.Reader" %>



<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'entity', 'error')} required">
	<label for="entity">
		<g:message code="reader.entity.label" default="Entity" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="entity.id" from="${org.leaf.eos2.news.Entity.list()}" optionKey="id" value="${readerInstance?.entity?.id}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'reader', 'error')} required">
	<label for="reader">
		<g:message code="reader.reader.label" default="Reader" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="reader.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" value="${readerInstance?.reader?.id}"  />

</div>

