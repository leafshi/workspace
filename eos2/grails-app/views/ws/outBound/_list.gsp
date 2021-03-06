<%@ page import="org.leaf.eos2.ws.OutBound" %>

<table>
	<thead>
		<tr>
			<g:sortableColumn property="objectName" title="${message(code: 'outBound.objectName.label', default: 'Object Name')}" />
			<g:sortableColumn property="method" title="${message(code: 'outBound.method.label', default: 'Method')}" />
			<g:sortableColumn property="asynchronous" title="${message(code: 'outBound.asynchronous.label', default: 'Asynchronous')}" />
			<g:sortableColumn property="priority" title="${message(code: 'outBound.priority.label', default: 'Priority')}" />
			<g:sortableColumn property="status" title="${message(code: 'outBound.status.label', default: 'Status')}" />
			<g:sortableColumn property="status" title="${message(code: 'outBound.stage.label', default: 'Stage')}" />
		</tr>
	</thead>
	<tbody>
	<g:each in="${outBoundInstanceList}" status="i" var="outBoundInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>
				<g:link action="show" id="${outBoundInstance.id}">
					${fieldValue(bean: outBoundInstance, field: "objectName")}
					~
					${fieldValue(bean: outBoundInstance, field: "objectId")}
				</g:link>
			</td>
			<td>${fieldValue(bean: outBoundInstance, field: "method")}</td>
			<td><g:formatBoolean boolean="${outBoundInstance.asynchronous}" /></td>
			<td>${fieldValue(bean: outBoundInstance, field: "priority")}</td>
			<td>${fieldValue(bean: outBoundInstance, field: "status")}</td>
			<td>${fieldValue(bean: outBoundInstance, field: "stage")}</td>
		</tr>
	</g:each>
	</tbody>
</table>
