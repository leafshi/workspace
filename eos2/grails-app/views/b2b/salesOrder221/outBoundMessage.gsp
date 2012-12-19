<table>
    <caption><g:message code="outBound.label" default="Oubound Message"/></caption>
    <thead>
        <tr>
        	<th><g:message code="outBound.id.label" default="Id"/></th>
			<th><g:message code="outBound.result.label" default="result" /></th>
        </tr>
    </thead>
    <tbody>
    <g:each in="${outBoundMessageInstanceList}" status="i" var="outBoundInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        	<td>${fieldValue(bean: outBoundInstance, field: "id")}</td>
            <td>
            	<li><g:message code="outBound.method.label" default="Method" />:${fieldValue(bean: outBoundInstance, field: "method")}</li>
            	<li><g:message code="outBound.asynchronous.label" default="Asynchronous?" />:<g:formatBoolean boolean="${outBoundInstance.asynchronous}" /></li>
				<li><g:message code="outBound.status.label" default="Status" />:${fieldValue(bean: outBoundInstance, field: "status")}</li>
				<li><g:message code="outBound.result.label" default="result" />:${fieldValue(bean: outBoundInstance, field: "result")}</li>
				<li><g:message code="outBound.error.label" default="error" />:${fieldValue(bean: outBoundInstance, field: "error")}</li>
        	</td>
        </tr>
    </g:each>
    </tbody>
</table>
