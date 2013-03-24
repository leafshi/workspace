
<%@ page import="org.leaf.eos2.admin.Help" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="help.edit" default="Edit Help" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="help.edit" default="Edit Help" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${helpInstance}">
            <div class="errors">
                <g:renderErrors bean="${helpInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${helpInstance?.id}" />
                <g:hiddenField name="version" value="${helpInstance?.version}" />
                <div class="dialog">
                    <g:render template="/admin/help/edit" />
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
