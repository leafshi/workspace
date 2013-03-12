
<%@ page import="org.leaf.eos2.news.Attachment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="attachment.edit" default="Edit Attachment" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="attachment.list" default="Attachment List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="attachment.new" default="New Attachment" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="attachment.edit" default="Edit Attachment" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${attachmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${attachmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${attachmentInstance?.id}" />
                <g:hiddenField name="version" value="${attachmentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="entity"><g:message code="attachment.entity" default="Entity" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentInstance, field: 'entity', 'errors')}">
                                    <g:select name="entity.id" from="${org.leaf.eos2.news.Entity.list()}" optionKey="id" value="${attachmentInstance?.entity?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="serialNumber"><g:message code="attachment.serialNumber" default="Serial Number" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentInstance, field: 'serialNumber', 'errors')}">
                                    <g:textField name="serialNumber" maxlength="2" value="${fieldValue(bean: attachmentInstance, field: 'serialNumber')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="attachment.name" default="Name" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="100" value="${fieldValue(bean: attachmentInstance, field: 'name')}" />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'update', 'default': 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
