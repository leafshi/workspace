<%@ page import="org.leaf.eos2.shiro.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <title><g:message code="user.resetPassword" default="Reset Password" /></title>
</head>
<body>
    <div class="body">
        <h1><g:message code="user.resetPassword" default="Reset Password" /></h1>
        <g:if test="${flash.message}">
        <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
        </g:if>
        <g:hasErrors bean="${userInstance}">
        <div class="errors">
            <g:renderErrors bean="${userInstance}" as="list" />
        </div>
        </g:hasErrors>
        <g:form method="post" >
            <g:hiddenField name="id" value="${userInstance?.id}" />
            <g:hiddenField name="version" value="${userInstance?.version}" />
            <div class="dialog">
            	<g:render template="/shiro/user/resetPassword"/>
            </div>
            <div class="buttons">
                <span class="button">
                    <g:actionSubmit class="save" action="reset" value="${message(code: 'user.resetPassword', default: 'Reset Password')}" />
                </span>
            </div>
        </g:form>
    </div>
</body>
</html>
