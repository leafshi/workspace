<%@ page import="grails.util.Environment" %>
<h1><g:message code="runtime.app.status"/></h1>

<ol class="property-list buildInfo">

    <li class="fieldcontain">
        <span class="property-label"><g:message code="grails.env"/></span>
        <span class="property-value">${Environment.current}</span>
    </li>
    <li class="fieldcontain">
        <span class="property-label"><g:message code="app.version"/></span>
        <span class="property-value"><g:meta name="app.version"/></span>
    </li>
    <li class="fieldcontain">
        <span class="property-label"><g:message code="app.grails.version"/></span>
        <span class="property-value"><g:meta name="app.grails.version"/></span>
    </li>
    <li class="fieldcontain">
        <span class="property-label"><g:message code="java.version"/></span>
        <span class="property-value">${System.getProperty('java.version')}</span>
    </li>
</ol>