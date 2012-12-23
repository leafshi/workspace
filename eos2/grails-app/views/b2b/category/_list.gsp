<%@ page import="org.leaf.eos2.b2b.Category" %>

<table>
	<thead>
		<tr>
			<th><g:message code="category.recordType.label" default="Record Type" /></th>
			<g:sortableColumn property="serialNumber" title="${message(code: 'category.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'category.name.label', default: 'Name')}" />
			<g:sortableColumn property="discount" title="${message(code: 'category.discount.label', default: 'Discount')}" />
			<g:sortableColumn property="productionCycle" title="${message(code: 'category.productionCycle.label', default: 'Production Cycle')}" />
			<g:sortableColumn property="transportCycle" title="${message(code: 'category.transportCycle.label', default: 'Transport Cycle')}" />
			<g:sortableColumn property="isActive" title="${message(code: 'category.isActive.label', default: 'Active?')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${categoryInstanceList}" status="i" var="categoryInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
			<td><g:link action="show" id="${categoryInstance.id}">${fieldValue(bean: categoryInstance, field: "recordType")}</g:link></td>
			<td>${fieldValue(bean: categoryInstance, field: "serialNumber")}</td>
			<td>${fieldValue(bean: categoryInstance, field: "name")}</td>
			<td>
				${formatNumber( number : categoryInstance?.discount, format:'###,#00.00%')}	
			</td>
			<td>${fieldValue(bean: categoryInstance, field: "productionCycle")}</td>
			<td>${fieldValue(bean: categoryInstance, field: "transportCycle")}</td>
			<td>${fieldValue(bean: categoryInstance, field: "isActive")}</td>
		</tr>
		</g:each>
	</tbody>
</table>
