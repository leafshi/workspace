<%@ page import="org.leaf.eos2.b2b.Product" %>
	
<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'product.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="name" title="${message(code: 'product.name.label', default: 'Name')}" />
			<g:sortableColumn property="standard" title="${message(code: 'product.standard.label', default: 'Standard')}" />
			<g:sortableColumn property="unit" title="${message(code: 'product.unit.label', default: 'Unit')}" />
			<g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
			<g:sortableColumn property="isIncludeTax" title="${message(code: 'product.isIncludeTax.label', default: 'Is Include Tax')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${productInstanceList}" status="i" var="productInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link controller="product" action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: productInstance, field: "name")}</td>
			<td>${fieldValue(bean: productInstance, field: "standard")}</td>
			<td>${fieldValue(bean: productInstance, field: "unit")}</td>
			<td>${fieldValue(bean: productInstance, field: "price")}</td>
			<td><g:formatBoolean boolean="${productInstance.isIncludeTax}" /></td>
		</tr>
		</g:each>
	</tbody>
</table>
