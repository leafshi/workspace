<%@ page import="org.leaf.eos2.admin.ShareRole" %>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="shareRole.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" required="" value="${shareRoleInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'group', 'error')} required">
	<label for="group">
		<g:message code="shareRole.group.label" default="Group" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="group" name="group.id" from="${org.leaf.eos2.admin.Group.list()}" optionKey="id" required="" value="${shareRoleInstance?.group?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'domain', 'error')} ">
	<label for="domain">
		<g:message code="shareRole.domain.label" default="Domain" />
	</label>
	<g:textField name="domain" value="${shareRoleInstance?.domain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'readable', 'error')} ">
	<label for="readable">
		<g:message code="shareRole.readable.label" default="Readable" />
	</label>
	<g:checkBox name="readable" value="${shareRoleInstance?.readable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'editable', 'error')} ">
	<label for="editable">
		<g:message code="shareRole.editable.label" default="Editable" />
	</label>
	<g:checkBox name="editable" value="${shareRoleInstance?.editable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: shareRoleInstance, field: 'deletable', 'error')} ">
	<label for="deletable">
		<g:message code="shareRole.deletable.label" default="Deletable" />
	</label>
	<g:checkBox name="deletable" value="${shareRoleInstance?.deletable}" />
</div>
