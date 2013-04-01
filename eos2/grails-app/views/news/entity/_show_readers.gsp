<shiro:hasPermission permission="entity:readers">
<g:if test="${entityInstance?.readers?.size() > 0}">
<!--当可阅读人数量大于零时才显示列表-->
<table>
	<thead>
		<tr>
			<th><g:message code="reader.reader.label" default="Reader" /></th>
			<th><g:message code="reader.department.label" default="Department" /></th>
			<th><g:message code="reader.dealer.label" default="Dealer" /></th>
			<th><g:message code="reader.visible.label" default="Visible" /></th>
			<th><g:message code="button.default.delete.label" default="Delete" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td>
				${fieldValue(bean: readerInstance, field: "reader")}
			</td>
			<td>
				<g:include controller="entityExtend" action="userDepartmentName" id="${readerInstance?.reader?.id}" />
			</td>
			<td>
				<g:include controller="entityExtend" action="userDealerName" id="${readerInstance?.reader?.id}" />
			</td>
			<td>
				<g:formatBoolean boolean="${readerInstance?.visible}" />
			</td>
			<td>			
				<g:link controller="entityExtend" action="delete" id="${readerInstance?.id}">
					<g:message code="button.default.delete.label" default="Delete" />
				</g:link>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</g:if>
</shiro:hasPermission>