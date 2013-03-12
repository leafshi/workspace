<%@ page import="org.leaf.eos2.news.Entity" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="entity.list.label" default="Entity List" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="entity.list.label" default="Entity List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
            	<g:render template="/news/entity/list" />
            </div>
            <div class="paginateButtons">
                <g:paginate total="${entityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
