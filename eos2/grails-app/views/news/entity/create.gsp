<%@ page import="org.leaf.eos2.news.Entity" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="entity.create.label" default="Create Entity" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="entity.create.label" default="Create Entity" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${entityInstance}">
            <div class="errors">
                <g:renderErrors bean="${entityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <g:render template="/news/entity/edit" />
                </div>
                <div class="buttons" style="position:relative;margin:10px 0 0 0;">
                	<shiro:hasPermission permission="entity:save">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', 'default': 'Create')}" /></span>
                	</shiro:hasPermission>
                </div>
            </g:form>
        </div>
    </body>
</html>
