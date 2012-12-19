<%@ page import="org.leaf.eos2.ws.ObConfig" %>
<table>
	<thead>
		<tr>
			<g:sortableColumn property="objectName" title="${message(code: 'obConfig.objectName.label', default: 'Object Name')}" />
			<g:sortableColumn property="method" title="${message(code: 'obConfig.method.label', default: 'Method')}" />
			<g:sortableColumn property="asynchronous" title="${message(code: 'obConfig.asynchronous.label', default: 'Asynchronous')}" />
			<g:sortableColumn property="priority" title="${message(code: 'obConfig.priority.label', default: 'Priority')}" />
			<g:sortableColumn property="defaultSendErrorLimit" title="${message(code: 'obConfig.defaultSendErrorLimit.label', default: 'Default Send Error Limit')}" />
			<g:sortableColumn property="defaultGetErrorLimit" title="${message(code: 'obConfig.defaultGetErrorLimit.label', default: 'Default Get Error Limit')}" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${obConfigInstanceList}" status="i" var="obConfigInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${obConfigInstance.id}">${fieldValue(bean: obConfigInstance, field: "objectName")}</g:link></td>
			<td>${fieldValue(bean: obConfigInstance, field: "method")}</td>
			<td><g:formatBoolean boolean="${obConfigInstance.asynchronous}" /></td>
			<td>${fieldValue(bean: obConfigInstance, field: "priority")}</td>
			<td>${fieldValue(bean: obConfigInstance, field: "defaultSendErrorLimit")}</td>
			<td>${fieldValue(bean: obConfigInstance, field: "defaultGetErrorLimit")}</td>
		</tr>
	</g:each>
	</tbody>
</table>