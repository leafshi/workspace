<%@ page import="org.leaf.eos2.ws.ObConfig" %>
<ol class="property-list obConfig">

	<li class="fieldcontain">
		<span id="objectName-label" class="property-label"><g:message code="obConfig.objectName.label" default="Object Name" /></span>
		<span class="property-value" aria-labelledby="objectName-label"><g:fieldValue bean="${obConfigInstance}" field="objectName"/></span>
	</li>

	<li class="fieldcontain">
		<span id="method-label" class="property-label"><g:message code="obConfig.method.label" default="Method" /></span>
		<span class="property-value" aria-labelledby="method-label"><g:fieldValue bean="${obConfigInstance}" field="method"/></span>
	</li>

	<li class="fieldcontain">
		<span id="asynchronous-label" class="property-label"><g:message code="obConfig.asynchronous.label" default="Asynchronous" /></span>
		<span class="property-value" aria-labelledby="asynchronous-label"><g:formatBoolean boolean="${obConfigInstance?.asynchronous}" /></span>
	</li>

	<li class="fieldcontain">
		<span id="priority-label" class="property-label"><g:message code="obConfig.priority.label" default="Priority" /></span>
		<span class="property-value" aria-labelledby="priority-label"><g:fieldValue bean="${obConfigInstance}" field="priority"/></span>
	</li>

	<li class="fieldcontain">
		<span id="defaultSendErrorLimit-label" class="property-label"><g:message code="obConfig.defaultSendErrorLimit.label" default="Default Send Error Limit" /></span>
		<span class="property-value" aria-labelledby="defaultSendErrorLimit-label"><g:fieldValue bean="${obConfigInstance}" field="defaultSendErrorLimit"/></span>
	</li>

	<li class="fieldcontain">
		<span id="defaultGetErrorLimit-label" class="property-label"><g:message code="obConfig.defaultGetErrorLimit.label" default="Default Get Error Limit" /></span>
		<span class="property-value" aria-labelledby="defaultGetErrorLimit-label"><g:fieldValue bean="${obConfigInstance}" field="defaultGetErrorLimit"/></span>
	</li>

	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="obConfig.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${obConfigInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>

	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="obConfig.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${obConfigInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>

</ol>