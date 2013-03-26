<%@ page import="org.leaf.eos2.b2b.DealerProduct" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="dealerProduct.create.label" default="Create DealerProduct" /></title>
		<g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
    </head>
    <body>
        <div class="body">
            <h1><g:message code="dealerProduct.create.label" default="Create DealerProduct" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${dealerProductInstance}">
            <div class="errors">
                <g:renderErrors bean="${dealerProductInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <g:render template="/b2b/dealerProduct/edit" />
                </div>
                <div class="buttons">
                	<shiro:hasPermission permission="dealerProduct:create">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'create', 'default': 'Create')}" /></span>
                	</shiro:hasPermission>
                </div>
            </g:form>
        </div>
    </body>
</html>
