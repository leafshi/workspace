<%@ page import="org.leaf.eos2.news.Reader" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="reader.edit" default="Edit Reader" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="reader.edit" default="Edit Reader" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${readerInstance}">
            <div class="errors">
                <g:renderErrors bean="${readerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${readerInstance?.id}" />
                <g:hiddenField name="version" value="${readerInstance?.version}" />
                <div class="dialog">
                    <g:render template="/news/reader/edit" />
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
