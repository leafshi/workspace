<%@ page import="org.leaf.eos2.admin.ShareRole" %>
<ol class="property-list shareRole">
			
	<g:if test="${shareRoleInstance?.user}">
	<li class="fieldcontain">
		<span id="user-label" class="property-label"><g:message code="shareRole.user.label" default="User" /></span>
		<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${shareRoleInstance?.user?.id}">${shareRoleInstance?.user?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${shareRoleInstance?.group}">
	<li class="fieldcontain">
		<span id="group-label" class="property-label"><g:message code="shareRole.group.label" default="Group" /></span>
		<span class="property-value" aria-labelledby="group-label"><g:link controller="group" action="show" id="${shareRoleInstance?.group?.id}">${shareRoleInstance?.group?.encodeAsHTML()}</g:link></span>
	</li>
	</g:if>
			
	<g:if test="${shareRoleInstance?.domain}">
	<li class="fieldcontain">
		<span id="domain-label" class="property-label"><g:message code="shareRole.domain.label" default="Domain" /></span>
		<span class="property-value" aria-labelledby="domain-label"><g:fieldValue bean="${shareRoleInstance}" field="domain"/></span>
	</li>
	</g:if>
			
	<g:if test="${shareRoleInstance?.readable}">
	<li class="fieldcontain">
		<span id="readable-label" class="property-label"><g:message code="shareRole.readable.label" default="Readable" /></span>
		<span class="property-value" aria-labelledby="readable-label"><g:formatBoolean boolean="${shareRoleInstance?.readable}" /></span>
	</li>
	</g:if>
			
	<g:if test="${shareRoleInstance?.editable}">
	<li class="fieldcontain">
		<span id="editable-label" class="property-label"><g:message code="shareRole.editable.label" default="Editable" /></span>
		<span class="property-value" aria-labelledby="editable-label"><g:formatBoolean boolean="${shareRoleInstance?.editable}" /></span>
	</li>
	</g:if>
			
	<g:if test="${shareRoleInstance?.deletable}">
	<li class="fieldcontain">
		<span id="deletable-label" class="property-label"><g:message code="shareRole.deletable.label" default="Deletable" /></span>
		<span class="property-value" aria-labelledby="deletable-label"><g:formatBoolean boolean="${shareRoleInstance?.deletable}" /></span>
	</li>
	</g:if>
			
</ol>
