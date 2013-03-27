<shiro:hasPermission permission="entity:readers">
<table>
	<thead>
		<tr>
			<th><g:message code="reader.reader.label" default="Reader" /></th>
			<th><g:message code="reader.department.label" default="Department" /></th>
			<th><g:message code="reader.dealer.label" default="Dealer" /></th>
			<th><g:message code="reader.visible.label" default="Visible" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
		<g:set var="style" value="${readerInstance?.visible ? 'color:green':'color:red'}" />
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td style="${style}">
				${fieldValue(bean: readerInstance, field: "reader")}
			</td>
			<td style="${style}">
				<g:include controller="entityExtend" action="userDepartmentName" id="${readerInstance?.reader?.id}" />
			</td>
			<td style="${style}">
				<g:include controller="entityExtend" action="userDealerName" id="${readerInstance?.reader?.id}" />
			</td>
			<td style="${style}">
				<g:formatBoolean boolean="${readerInstance?.visible}" />
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</shiro:hasPermission>