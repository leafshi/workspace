<table>
	<thead>
		<tr>
			<th><g:message code="dealerProduct.dealer.label" default="Dealer" /></th>
			<th><g:message code="dealerProduct.product.label" default="Product" /></th>
			<g:sortableColumn property="unit1" title="Unit1" titleKey="dealerProduct.unit1.label" />
			<g:sortableColumn property="unit2" title="Unit2" titleKey="dealerProduct.unit2.label" />
			<th><g:message code="dealerProduct.price.label" default="Price" /></th>
			<g:sortableColumn property="currency" title="Currency" titleKey="dealerProduct.currency.label" />
			<th><g:message code="dealerProduct.isIncludeTax.label" default="Include Tax?" /></th>
			<th><g:message code="dealerProduct.approvalDate.label" default="Approval Date" /></th>
			<th><g:message code="dealerProduct.beginDate.label" default="Begin Date" /></th>
			<th><g:message code="dealerProduct.closeDate.label" default="Close Date" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${dealerProductInstanceList}" status="i" var="dealerProductInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "dealer")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "product")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "unit1")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "unit2")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "price")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "currency")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}">${fieldValue(bean: dealerProductInstance, field: "isIncludeTax")}</g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}"><g:formatDate date="${dealerProductInstance?.approvalDate}" formatName="custom.date.format"/></g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}"><g:formatDate date="${dealerProductInstance?.beginDate}" formatName="custom.date.format"/></g:link></td>
			<td><g:link action="show" id="${dealerProductInstance.id}"><g:formatDate date="${dealerProductInstance?.closeDate}" formatName="custom.date.format"/></g:link></td>
		</tr>
	</g:each>
	</tbody>
</table>