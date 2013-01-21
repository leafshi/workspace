<%@ page import="org.leaf.eos2.ws.OutBound" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outBound.label', default: 'OutBound')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-outBound" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="show-outBound" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:render template="/ws/outBound/show" />
			
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${outBoundInstance?.id}" />
					
					<g:link class="edit" action="edit" id="${outBoundInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					
					<shiro:hasPermission permission="outBound:resurrect">
                    <span class="button"><g:actionSubmit class="edit" action="resurrect" value="${message(code: 'outBound.button.resurrect.label', 'default': 'Resurrect')}" /></span>
                	</shiro:hasPermission>
                	
                	<shiro:hasPermission permission="outBound:reinit">
                    <span class="button"><g:actionSubmit class="edit" action="reinit" value="${message(code: 'outBound.button.reinit.label', 'default': 'Reinit')}" /></span>
                	</shiro:hasPermission>                

				</fieldset>
			</g:form>
		</div>
	</body>
</html>
