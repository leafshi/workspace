<%@ page import="org.leaf.eos2.shiro.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" maxlength="25" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'profile', 'error')} ">
	<label for="profile">
		<g:message code="user.profile.label" default="Profile" />
	</label>
	<g:select id="profile" name="profile.id" from="${org.leaf.eos2.admin.Profile.list()}" optionKey="id" value="${userInstance?.profile?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'department', 'error')} ">
	<label for="department">
		<g:message code="user.department.label" default="Department" />
	</label>
	<g:select id="department" name="department.id" from="${org.leaf.eos2.b2b.Department.list()}" optionKey="id" value="${userInstance?.department?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'group', 'error')} ">
	<label for="group">
		<g:message code="user.group.label" default="Group" />
	</label>
	<g:select id="group" name="group.id" from="${org.leaf.eos2.admin.Group.list()}" optionKey="id" value="${userInstance?.group?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} ">
	<label for="group">
		<g:message code="user.role.label" default="Role" />
	</label>
	<g:select id="role" name="role.id" from="${org.leaf.eos2.shiro.Role.list()}" optionKey="id" value="${userInstance?.role?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>