<ul>
	<shiro:hasPermission permission="salesOrder:list">
	<g:each in="${mySalesOrderList}" status="i" var="salesOrder">
	<li>
		<a href="${createLink(controller: 'salesOrder', action : 'list', params:['status':salesOrder[0]])}">
			<span>${salesOrder[0]}(${salesOrder[1]})</span>
		</a>
	</li>
	</g:each>
	</shiro:hasPermission>
</ul>