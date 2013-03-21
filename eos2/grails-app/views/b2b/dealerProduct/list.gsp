
<%@ page import="org.leaf.eos2.b2b.DealerProduct" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="dealerProduct.list.label" default="DealerProduct List" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="dealerProduct.list.label" default="DealerProduct List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <g:render template="/b2b/dealerProduct/list" />
            </div>
            <div class="pagination">
                <g:paginate total="${dealerProductInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
