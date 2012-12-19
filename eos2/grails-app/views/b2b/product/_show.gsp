<%@ page import="org.leaf.eos2.b2b.Product" %>
<ol class="property-list product">
			
	<g:if test="${productInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="product.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${productInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="product.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${productInstance}" field="name"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.standard}">
	<li class="fieldcontain">
		<span id="standard-label" class="property-label"><g:message code="product.standard.label" default="Standard" /></span>
		<span class="property-value" aria-labelledby="standard-label"><g:fieldValue bean="${productInstance}" field="standard"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.unit}">
	<li class="fieldcontain">
		<span id="unit-label" class="property-label"><g:message code="product.unit.label" default="Unit" /></span>
		<span class="property-value" aria-labelledby="unit-label"><g:fieldValue bean="${productInstance}" field="unit"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.price}">
	<li class="fieldcontain">
		<span id="price-label" class="property-label"><g:message code="product.price.label" default="Price" /></span>
		<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${productInstance}" field="price"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.isIncludeTax}">
	<li class="fieldcontain">
		<span id="isIncludeTax-label" class="property-label"><g:message code="product.isIncludeTax.label" default="Is Include Tax" /></span>
		<span class="property-value" aria-labelledby="isIncludeTax-label"><g:formatBoolean boolean="${productInstance?.isIncludeTax}" /></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.isActive}">
	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="product.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${productInstance?.isActive}" /></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="product.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${productInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>
			
	<g:if test="${productInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="product.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${productInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>

</ol>

<g:if test="${productInstance?.categories}">
	<div class="list">
		<table>
			<thead>
				<tr>
					<th><g:message code="category.recordType.label" default="Record Type" /></th>
					<th><g:message code="category.serialNumber.label" default="Serial Number" /></th>
					<th><g:message code="category.name.label" default="Name" /></th>
					<th><g:message code="category.discount.label" default="Discount" /></th>
					<th><g:message code="category.productionCycle.label" default="Production Cycle" /></th>
					<th><g:message code="category.transportCycle.label" default="Transport Cycle" /></th>
					<th><g:message code="category.isAllowSpecialDiscount.label" default="Allow Special Discount?" /></th>
					<th><g:message code="category.isAllowZeroPrice.label" default="Allow Zero Price?" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${productInstance.categories.sort{return it.category.recordType.serialNumber}}" status="i" var="categoryInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>${fieldValue(bean: categoryInstance, field: "category.recordType")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.serialNumber")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.name")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.discount")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.productionCycle")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.transportCycle")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.isAllowSpecialDiscount")}</td>
					<td>${fieldValue(bean: categoryInstance, field: "category.isAllowZeroPrice")}</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</g:if>