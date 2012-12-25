<%@ page import="org.leaf.eos2.birt.Report" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'report.label', default: 'Report')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <g:render template="/birt/report/list" />
            </div>

            <div class="paginateButtons">
                <g:paginate total="${reportInstanceTotal}" />
            </div>
            
			<div class="buttons">
				<span class="button"><a href="${createLink(action: 'scan')}">${message(code:'report.scan.label', default : 'Scan')}</a></span>
			</div>
        </div>
    </body>
</html>
