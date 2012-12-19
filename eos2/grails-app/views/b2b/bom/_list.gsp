<%@ page import="org.leaf.eos2.b2b.Bom" %>
<table>
	<thead>
		<tr>
			<th><g:message code="bom.product.label" default="Product" /></th>
			<g:sortableColumn property="dateCreated" title="${message(code: 'bom.dateCreated.label', default: 'Date Created')}" />
			<g:sortableColumn property="lastUpdated" title="${message(code: 'bom.lastUpdated.label', default: 'Last Updated')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${bomInstanceList}" status="i" var="bomInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${bomInstance.id}">${fieldValue(bean: bomInstance, field: "product")}</g:link></td>
			<td><g:formatDate date="${bomInstance.dateCreated}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${bomInstance.lastUpdated}" formatName="custom.datetime.format"/></td>
		</tr>
		</g:each>
	</tbody>
</table>
