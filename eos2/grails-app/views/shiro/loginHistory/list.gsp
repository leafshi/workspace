<%@ page import="org.leaf.eos2.shiro.LoginHistory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="loginHistory.list.label" default="LoginHistory List" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="loginHistory.list.label" default="LoginHistory List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <g:render template="/shiro/loginHistory/list" />
            </div>
            <div class="paginateButtons">
                <g:paginate total="${loginHistoryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
