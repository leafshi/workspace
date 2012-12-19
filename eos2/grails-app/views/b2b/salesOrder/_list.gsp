<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>
		
<table>
	<thead>
		<tr>
			<th><g:message code="salesOrder.id.label" default="Id" /></th>
			<th><g:message code="salesOrder.recordType.label" default="Record Type" /></th>
			<g:sortableColumn property="serialNumber" title="${message(code: 'salesOrder.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="accountSerialNumber" title="${message(code: 'salesOrder.accountSerialNumber.label', default: 'Account Serial Number')}" />
			<g:sortableColumn property="orderDate" title="${message(code: 'salesOrder.orderDate.label', default: 'Order Date')}" />
			<th><g:message code="salesOrder.dealer.label" default="Dealer" /></th>
			<g:sortableColumn property="project" title="${message(code: 'salesOrder.project.label', default: 'Project')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${salesOrderInstanceList}" status="i" var="salesOrderInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "id")}</g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "recordType")}</g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "serialNumber")}</g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "accountSerialNumber")}</g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}"><g:formatDate date="${salesOrderInstance.orderDate}" formatName="custom.date.format"/></g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "dealer")}</g:link></td>
			<td><g:link action="show" controller="salesOrder${fieldValue(bean: salesOrderInstance, field: "recordType.serialNumber")}" id="${salesOrderInstance.id}">${fieldValue(bean: salesOrderInstance, field: "project")}</g:link></td>
		</tr>
		</g:each>
	</tbody>
</table>
