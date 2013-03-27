<%@ page import="org.leaf.eos2.news.Entity" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="entity.show.label" default="Show Entity" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="entity.show.label" default="Show Entity" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${entityInstance?.id}" />
                <div class="dialog">
                    <g:render template="/news/entity/show" />
                </div>
                <div class="list">
                	<g:render template="/news/entity/show_readers" />
                </div>
                <div class="buttons">
                	<shiro:hasPermission permission="entity:edit">
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', 'default': 'Edit')}" /></span>
                    </shiro:hasPermission>
                    
                    <shiro:hasPermission permission="entity:delete">
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', 'default': 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 'default': 'Are you sure?')}');" /></span>
                	</shiro:hasPermission>
                </div>
            </g:form>
        </div>
    </body>
</html>
