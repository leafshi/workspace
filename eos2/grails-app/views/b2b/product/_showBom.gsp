<g:if test="${bomId > 0}">
	<g:link controller="bom" action="show" id="${bomId}">
		<g:message code="default.show.label" args="['Bom']" />
	</g:link>
</g:if>
