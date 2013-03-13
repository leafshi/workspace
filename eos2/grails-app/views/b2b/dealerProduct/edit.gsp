<%@ page import="org.leaf.eos2.b2b.DealerProduct" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="dealerProduct.edit.label" default="Edit DealerProduct" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="dealerProduct.edit.label" default="Edit DealerProduct" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${dealerProductInstance}">
            <div class="errors">
                <g:renderErrors bean="${dealerProductInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${dealerProductInstance?.id}" />
                <g:hiddenField name="version" value="${dealerProductInstance?.version}" />
                <div class="dialog">
                    <g:render template="/b2b/dealerProduct/edit" />
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
