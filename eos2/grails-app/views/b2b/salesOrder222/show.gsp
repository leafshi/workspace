<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesOrder.label', default: 'SalesOrder')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
			~
			${salesOrderInstance?.recordType?.encodeAsHTML()}			
			~
			${salesOrderInstance?.serialNumber}
		</title>
		<g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
		<g:javascript src="JQueryBlockUI.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
	</head>
	<body>
		<a href="#show-salesOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-salesOrder" class="content scaffold-show" role="main">
			<h1>
				<g:message code="default.show.label" args="[entityName]" />
				~
				${salesOrderInstance?.recordType?.encodeAsHTML()}
				~
				${salesOrderInstance?.serialNumber}
			</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="/b2b/salesOrder222/show" />
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${salesOrderInstance?.id}" />

					<shiro:hasPermission permission="${controllerName}:edit">
					<g:link class="edit" action="edit" id="${salesOrderInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					</shiro:hasPermission>
					
					<shiro:hasPermission permission="${controllerName}:delete">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</shiro:hasPermission>
					
					<shiro:hasPermission permission="${controllerName}:forceDelete">
					<g:actionSubmit class="delete" action="forceDelete" value="${message(code: 'default.button.forceDelete.label', default: 'Force Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</shiro:hasPermission>
					
					<shiro:hasPermission permission="${controllerName}:clearErpSerialNumber">
					<g:actionSubmit class="edit" action="clearErpSerialNumber" value="ClEAR ERP SERIAL NUMBER" onclick="return confirm('ClEAR ERP SERIAL NUMBER?');" />
					</shiro:hasPermission>
					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
