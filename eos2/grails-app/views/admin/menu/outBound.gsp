<a class="parent" onclick="javascript:none()" href="#"><span><g:message code="outBound.label" default="OutBound Message"/></span></a>
<ul>
	<g:each in="${outBoundList}" status="i" var="outBound">
	<li>
		<a href="${createLink(controller: 'outBound', action : 'list', params:['stage':outBound[0]])}">
			<span>${outBound[0]}(${outBound[1]})</span>
		</a>
	</li>
	</g:each>
</ul>
