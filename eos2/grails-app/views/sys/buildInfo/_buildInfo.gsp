<h1><g:message code="plugin.title"/></h1>
<ol class="property-list buildInfo">
    <g:each in="${buildInfoProperties}" var="prop">
        <g:if test="${g.meta(name:prop)}">
    	<li class="fieldcontain">
			<span id="${prop}" class="property-label"><g:message code="${prop}"/></span>
			<span class="property-value" aria-labelledby="${prop}"><g:meta name="${prop}"/></span>
		</li>
        </g:if>
    </g:each>
</ol>