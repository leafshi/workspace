
<%@ page import="org.leaf.eos2.news.Reader" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="reader.edit" default="Edit Reader" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="reader.list" default="Reader List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="reader.new" default="New Reader" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="reader.edit" default="Edit Reader" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${readerInstance}">
            <div class="errors">
                <g:renderErrors bean="${readerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${readerInstance?.id}" />
                <g:hiddenField name="version" value="${readerInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="entity"><g:message code="reader.entity" default="Entity" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: readerInstance, field: 'entity', 'errors')}">
                                    <g:select name="entity.id" from="${org.leaf.eos2.news.Entity.list()}" optionKey="id" value="${readerInstance?.entity?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="reader"><g:message code="reader.reader" default="Reader" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: readerInstance, field: 'reader', 'errors')}">
                                    <g:select name="reader.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" value="${readerInstance?.reader?.id}"  />

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
