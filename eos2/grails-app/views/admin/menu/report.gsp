<ul>
	<g:each in="${reportList}" status="i" var="report">
	<li>
		<a href="${createLink(controller: 'report', action : 'run', params:['id':report[0]])}">
			<span>${report[1]}</span>
		</a>
	</li>
	</g:each>
</ul>
