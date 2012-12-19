
<%@ page import="org.leaf.eos2.snapshot.CreditControl" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'creditControl.label', default: 'CreditControl')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-creditControl" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-creditControl" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list creditControl">
			
				<g:if test="${creditControlInstance?.dealer}">
				<li class="fieldcontain">
					<span id="dealer-label" class="property-label"><g:message code="creditControl.dealer.label" default="Dealer" /></span>
					
						<span class="property-value" aria-labelledby="dealer-label"><g:link controller="dealer" action="show" id="${creditControlInstance?.dealer?.id}">${creditControlInstance?.dealer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.orderAmount}">
				<li class="fieldcontain">
					<span id="orderAmount-label" class="property-label"><g:message code="creditControl.orderAmount.label" default="Order Amount" /></span>
					
						<span class="property-value" aria-labelledby="orderAmount-label"><g:fieldValue bean="${creditControlInstance}" field="orderAmount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.openingSell}">
				<li class="fieldcontain">
					<span id="openingSell-label" class="property-label"><g:message code="creditControl.openingSell.label" default="Opening Sell" /></span>
					
						<span class="property-value" aria-labelledby="openingSell-label"><g:fieldValue bean="${creditControlInstance}" field="openingSell"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.receivableAmout}">
				<li class="fieldcontain">
					<span id="receivableAmout-label" class="property-label"><g:message code="creditControl.receivableAmout.label" default="Receivable Amout" /></span>
					
						<span class="property-value" aria-labelledby="receivableAmout-label"><g:fieldValue bean="${creditControlInstance}" field="receivableAmout"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.receivableNote}">
				<li class="fieldcontain">
					<span id="receivableNote-label" class="property-label"><g:message code="creditControl.receivableNote.label" default="Receivable Note" /></span>
					
						<span class="property-value" aria-labelledby="receivableNote-label"><g:fieldValue bean="${creditControlInstance}" field="receivableNote"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.receivable}">
				<li class="fieldcontain">
					<span id="receivable-label" class="property-label"><g:message code="creditControl.receivable.label" default="Receivable" /></span>
					
						<span class="property-value" aria-labelledby="receivable-label"><g:fieldValue bean="${creditControlInstance}" field="receivable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.credit}">
				<li class="fieldcontain">
					<span id="credit-label" class="property-label"><g:message code="creditControl.credit.label" default="Credit" /></span>
					
						<span class="property-value" aria-labelledby="credit-label"><g:fieldValue bean="${creditControlInstance}" field="credit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.creditExceed}">
				<li class="fieldcontain">
					<span id="creditExceed-label" class="property-label"><g:message code="creditControl.creditExceed.label" default="Credit Exceed" /></span>
					
						<span class="property-value" aria-labelledby="creditExceed-label"><g:fieldValue bean="${creditControlInstance}" field="creditExceed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.creditBalance}">
				<li class="fieldcontain">
					<span id="creditBalance-label" class="property-label"><g:message code="creditControl.creditBalance.label" default="Credit Balance" /></span>
					
						<span class="property-value" aria-labelledby="creditBalance-label"><g:fieldValue bean="${creditControlInstance}" field="creditBalance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.createdBy}">
				<li class="fieldcontain">
					<span id="createdBy-label" class="property-label"><g:message code="creditControl.createdBy.label" default="Created By" /></span>
					
						<span class="property-value" aria-labelledby="createdBy-label"><g:link controller="user" action="show" id="${creditControlInstance?.createdBy?.id}">${creditControlInstance?.createdBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="creditControl.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${creditControlInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.lastModifiedBy}">
				<li class="fieldcontain">
					<span id="lastModifiedBy-label" class="property-label"><g:message code="creditControl.lastModifiedBy.label" default="Last Modified By" /></span>
					
						<span class="property-value" aria-labelledby="lastModifiedBy-label"><g:link controller="user" action="show" id="${creditControlInstance?.lastModifiedBy?.id}">${creditControlInstance?.lastModifiedBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="creditControl.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${creditControlInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${creditControlInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="creditControl.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="user" action="show" id="${creditControlInstance?.owner?.id}">${creditControlInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${creditControlInstance?.id}" />
					<g:link class="edit" action="edit" id="${creditControlInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
