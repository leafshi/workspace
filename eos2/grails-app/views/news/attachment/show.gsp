
<%@ page import="org.leaf.eos2.news.Attachment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="attachment.show" default="Show Attachment" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="attachment.list" default="Attachment List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attachment.new" default="New Attachment" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attachment.show" default="Show Attachment" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${attachmentInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="attachment.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: attachmentInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="attachment.entity" default="Entity" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="entity" action="show" id="${attachmentInstance?.entity?.id}">${attachmentInstance?.entity?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="attachment.serialNumber" default="Serial Number" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: attachmentInstance, field: "serialNumber")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="attachment.name" default="Name" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: attachmentInstance, field: "name")}</td>
                                
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'edit', 'default': 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
