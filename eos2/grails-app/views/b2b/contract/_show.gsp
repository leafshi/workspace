<%@ page import="org.leaf.eos2.b2b.Contract" %>
	
<table>
	<tr>
		<td>
			<span id="serialNumber-label" class="property-label"><g:message code="contract.serialNumber.label" default="Serial Number" /></span>
			<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${contractInstance}" field="serialNumber"/></span>
		</td>
		<td>
			<span id="subject-label" class="property-label"><g:message code="contract.subject.label" default="Subject" /></span>
			<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${contractInstance}" field="subject"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<span id="recordType-label" class="property-label"><g:message code="contract.recordType.label" default="Record Type" /></span>
			<span class="property-value" aria-labelledby="recordType-label"><g:link controller="recordType" action="show" id="${contractInstance?.recordType?.id}">${contractInstance?.recordType?.encodeAsHTML()}</g:link></span>
		</td>
		<td>
			<span id="applicant-label" class="property-label"><g:message code="contract.applicant.label" default="Applicant" /></span>
			<span class="property-value" aria-labelledby="applicant-label"><g:fieldValue bean="${contractInstance}" field="applicant"/></span>			
		</td>
	</tr>
	<tr>
		<td>
			<span id="filingDate-label" class="property-label"><g:message code="contract.filingDate.label" default="Filing Date" /></span>
			<span class="property-value" aria-labelledby="filingDate-label"><g:formatDate date="${contractInstance?.filingDate}" formatName="custom.date.format" /></span>
		</td>
		<td>
			<span id="effectiveDate-label" class="property-label"><g:message code="contract.effectiveDate.label" default="Effective Date" /></span>
			<span class="property-value" aria-labelledby="effectiveDate-label"><g:formatDate date="${contractInstance?.effectiveDate}" formatName="custom.date.format"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<span id="dealer-label" class="property-label"><g:message code="contract.dealer.label" default="Dealer" /></span>
			<span class="property-value" aria-labelledby="dealer-label"><g:link controller="dealer" action="show" id="${contractInstance?.dealer?.id}">${contractInstance?.dealer?.encodeAsHTML()}</g:link></span>
		</td>
		<td>
			<span id="project-label" class="property-label"><g:message code="contract.project.label" default="Project" /></span>
			<span class="property-value" aria-labelledby="project-label"><g:fieldValue bean="${contractInstance}" field="project"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<span id="industry-label" class="property-label"><g:message code="contract.industry.label" default="Industry" /></span>
			<span class="property-value" aria-labelledby="industry-label"><g:link controller="industry" action="show" id="${contractInstance?.industry?.id}">${contractInstance?.industry?.encodeAsHTML()}</g:link></span>
		</td>
		<td>
			<span id="owner-label" class="property-label"><g:message code="contract.owner.label" default="Owner" /></span>
			<span class="property-value" aria-labelledby="owner-label"><g:link controller="user" action="show" id="${contractInstance?.owner?.id}">${contractInstance?.owner?.encodeAsHTML()}</g:link></span>
		</td>

	</tr>
	<tr>
		<td colspan="2">
			<span id="description-label" class="property-label"><g:message code="contract.description.label" default="Description" /></span>
			<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${contractInstance}" field="description"/></span>
		</td>
	</tr>
	<tr>
		<td>
			<span id="createdBy-label" class="property-label"><g:message code="contract.createdBy.label" default="Created By" /></span>
			<span class="property-value" aria-labelledby="createdBy-label"><g:link controller="user" action="show" id="${contractInstance?.createdBy?.id}">${contractInstance?.createdBy?.encodeAsHTML()}, <g:formatDate date="${contractInstance?.dateCreated}" formatName="custom.datetime.format"/></g:link></span>
		</td>
		<td>
			<span id="lastModifiedBy-label" class="property-label"><g:message code="contract.lastModifiedBy.label" default="Last Modified By" /></span>
			<span class="property-value" aria-labelledby="lastModifiedBy-label"><g:link controller="user" action="show" id="${contractInstance?.lastModifiedBy?.id}">${contractInstance?.lastModifiedBy?.encodeAsHTML()}, <g:formatDate date="${contractInstance?.lastUpdated}" formatName="custom.datetime.format"/></g:link></span>
		</td>
	</tr>

</table>

<table>
	<thead>
		<tr>
			<th><g:message code="contractDetail.serialNumber.label" default="Serial Number" /></th>
			<th><g:message code="contractDetail.category.label" default="Category" /></th>
			<th><g:message code="contractDetail.discount.label" default="Discount" /></th>
			<th><g:message code="contractDetail.specialDiscount.label" default="Special Discount" /></th>
			<th><g:message code="contractDetail.finalDiscount.label" default="Final Discount" /></th>
			<th><g:message code="contractDetail.expiryDate.label" default="Expiry Date" /></th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${contractInstance.contractDetails}" status="i" var="contractDetailInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td>${fieldValue(bean: contractDetailInstance, field: "serialNumber")}</td>
			<td>${fieldValue(bean: contractDetailInstance, field: "category")}</td>
			<td>${formatNumber( number : contractDetailInstance?.discount?:0, format:'###,#00.00%')}</td>
			<td>${formatNumber( number : contractDetailInstance?.specialDiscount?:0, format:'###,#00.00%')}</td>
			<td>${formatNumber( number : contractDetailInstance?.finalDiscount?:0, format:'###,#00.00%')}</td>
			<td>${formatDate(date:contractDetailInstance?.expiryDate, formatName:'custom.date.format')}</td>
		</tr>
	</g:each>
	</tbody>
</table>