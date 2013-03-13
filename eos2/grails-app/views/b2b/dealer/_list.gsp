<%@ page import="org.leaf.eos2.b2b.Dealer" %>
<table>
	<thead>
		<tr>
			<th><g:message code="dealer.department.label" default="Department" /></th>
			<g:sortableColumn property="serialNumber" title="${message(code: 'dealer.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'dealer.name.label', default: 'Name')}" />
			<g:sortableColumn property="alias" title="${message(code: 'dealer.alias.label', default: 'Alias')}" />
			<g:sortableColumn property="shortcut" title="${message(code: 'dealer.shortcut.label', default: 'Shortcut')}" />
			<g:sortableColumn property="pricingStrategy" title="${message(code: 'dealer.pricingStrategy.label', default: 'Pricing Strategy')}" />
			<th><g:message code="dealer.salesMan.label" default="Sales Man" /></th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${dealerInstanceList}" status="i" var="dealerInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${dealerInstance.id}">${fieldValue(bean: dealerInstance, field: "department")}</g:link></td>
			<td>${fieldValue(bean: dealerInstance, field: "serialNumber")}</td>
			<td>${fieldValue(bean: dealerInstance, field: "name")}</td>
			<td>${fieldValue(bean: dealerInstance, field: "alias")}</td>
			<td>${fieldValue(bean: dealerInstance, field: "shortcut")}</td>
			<td>${fieldValue(bean: dealerInstance, field: "pricingStrategy")}</td>
			<td>${fieldValue(bean: dealerInstance, field: "salesMan")}</td>
		</tr>
		</g:each>
	</tbody>
</table>
