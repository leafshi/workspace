
<%@ page import="org.leaf.eos2.news.Attachment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="attachment.list" default="Attachment List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attachment.new" default="New Attachment" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attachment.list" default="Attachment List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="attachment.id" />
                        
                   	    <th><g:message code="attachment.entity" default="Entity" /></th>
                   	    
                   	    <g:sortableColumn property="serialNumber" title="Serial Number" titleKey="attachment.serialNumber" />
                        
                   	    <g:sortableColumn property="name" title="Name" titleKey="attachment.name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attachmentInstanceList}" status="i" var="attachmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${attachmentInstance.id}">${fieldValue(bean: attachmentInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: attachmentInstance, field: "entity")}</td>
                        
                            <td>${fieldValue(bean: attachmentInstance, field: "serialNumber")}</td>
                        
                            <td>${fieldValue(bean: attachmentInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attachmentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
