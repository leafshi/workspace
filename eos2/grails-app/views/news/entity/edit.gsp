
<%@ page import="org.leaf.eos2.news.Entity" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="entity.edit.label" default="Edit Entity" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="entity.edit.label" default="Edit Entity" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${entityInstance}">
            <div class="errors">
                <g:renderErrors bean="${entityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${entityInstance?.id}" />
                <g:hiddenField name="version" value="${entityInstance?.version}" />
                <div class="dialog">
                    <g:render template="/news/entity/edit" />
                </div>
                <div class="list">
                	<g:render template="/news/entity/edit_readers" />
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', 'default': 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
