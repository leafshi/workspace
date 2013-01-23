<table>
    <caption><g:message code="outBound.label" default="Oubound Message"/></caption>
    <thead>
        <tr>
			<th><g:message code="outBound.result.label" default="result" /></th>
        </tr>
    </thead>
    <tbody>
    <g:each in="${outBoundMessageInstanceList}" status="i" var="outBoundInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
        		<li>
        			<g:link controller="outBound" action="show" id="${outBoundInstance.id}">
        				<g:message code="outBound.id.label" default="Id"/>:${fieldValue(bean: outBoundInstance, field: "id")}
        			</g:link>
        		</li>
				<li><g:message code="outBound.status.label" default="Status" />:${fieldValue(bean: outBoundInstance, field: "status")}</li>
				<li><g:message code="outBound.result.label" default="result" />:${fieldValue(bean: outBoundInstance, field: "result")}</li>
				<li><g:message code="outBound.error.label" default="error" />:${fieldValue(bean: outBoundInstance, field: "error")}</li>
        	</td>
        </tr>
    </g:each>
    </tbody>
</table>
