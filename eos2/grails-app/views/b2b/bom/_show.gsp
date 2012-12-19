<%@ page import="org.leaf.eos2.b2b.Bom" %>
<ol class="property-list bom">

	<li class="fieldcontain">
		<span id="product-label" class="property-label"><g:message code="bom.product.label" default="Product" /></span>
		<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${bomInstance?.product?.id}">${bomInstance?.product?.encodeAsHTML()}</g:link></span>
	</li>

	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="bom.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${bomInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>

	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="bom.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${bomInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>
			
</ol>

<table>
	<thead>
		<tr>
			<th><g:message code="bomDetail.serialNumber.label" default="Serial Number" /></th>
			<th><g:message code="bomDetail.product.label" default="Product" /></th>
			<th><g:message code="bomDetail.dosage.label" default="Dosage" /></th>
			<th><g:message code="bomDetail.quota.label" default="Quota" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${bomInstance.bomDetails}" status="i" var="bomDetailInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: bomDetailInstance, field: "serialNumber")}</td>
			<td>${fieldValue(bean: bomDetailInstance, field: "product")}</td>		
			<td>${fieldValue(bean: bomDetailInstance, field: "dosage")}</td>
			<td>${fieldValue(bean: bomDetailInstance, field: "quota")}</td>
		</tr>
	</g:each>
	</tbody>
</table>