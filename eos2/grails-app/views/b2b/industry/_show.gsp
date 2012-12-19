<%@ page import="org.leaf.eos2.b2b.Industry" %>

<ol class="property-list industry">
			
	<g:if test="${industryInstance?.serialNumber}">
	<li class="fieldcontain">
		<span id="serialNumber-label" class="property-label"><g:message code="industry.serialNumber.label" default="Serial Number" /></span>
		<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${industryInstance}" field="serialNumber"/></span>
	</li>
	</g:if>
			
	<g:if test="${industryInstance?.shortName}">
	<li class="fieldcontain">
		<span id="shortName-label" class="property-label"><g:message code="industry.shortName.label" default="Short Name" /></span>
		<span class="property-value" aria-labelledby="shortName-label"><g:fieldValue bean="${industryInstance}" field="shortName"/></span>
	</li>
	</g:if>
			
	<g:if test="${industryInstance?.fullName}">
	<li class="fieldcontain">
		<span id="fullName-label" class="property-label"><g:message code="industry.fullName.label" default="Full Name" /></span>
		<span class="property-value" aria-labelledby="fullName-label"><g:fieldValue bean="${industryInstance}" field="fullName"/></span>
	</li>
	</g:if>
			
	<g:if test="${industryInstance?.isActive}">
	<li class="fieldcontain">
		<span id="isActive-label" class="property-label"><g:message code="industry.isActive.label" default="Is Active" /></span>
		<span class="property-value" aria-labelledby="isActive-label"><g:formatBoolean boolean="${industryInstance?.isActive}" /></span>
	</li>
	</g:if>
			
	<g:if test="${industryInstance?.description}">
	<li class="fieldcontain">
		<span id="description-label" class="property-label"><g:message code="industry.description.label" default="Description" /></span>
		<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${industryInstance}" field="description"/></span>
	</li>
	</g:if>
			
</ol>
