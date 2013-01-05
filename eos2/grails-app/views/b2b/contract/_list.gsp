<%@ page import="org.leaf.eos2.b2b.Contract" %>
		
<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'contract.serialNumber.label', default: 'Serial Number')}" />
			<th><g:message code="contract.recordType.label" default="Record Type" /></th>
			<g:sortableColumn property="subject" title="${message(code: 'contract.industry.label', default: 'Industry')}" />
			<g:sortableColumn property="subject" title="${message(code: 'contract.project.label', default: 'Project')}" />
			<g:sortableColumn property="applicant" title="${message(code: 'contract.applicant.label', default: 'Applicant')}" />
			<g:sortableColumn property="filingDate" title="${message(code: 'contract.filingDate.label', default: 'Filing Date')}" />
			<g:sortableColumn property="effectiveDate" title="${message(code: 'contract.effectiveDate.label', default: 'Effective Date')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${contractInstanceList}" status="i" var="contractInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link controller="contract" action="show" id="${contractInstance.id}">${fieldValue(bean: contractInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: contractInstance, field: "recordType")}</td>
			<td>${fieldValue(bean: contractInstance, field: "industry")}</td>
			<td>${fieldValue(bean: contractInstance, field: "project")}</td>
			<td>${fieldValue(bean: contractInstance, field: "applicant")}</td>
			<td><g:formatDate date="${contractInstance.filingDate}" formatName="custom.datetime.format"/></td>
			<td><g:formatDate date="${contractInstance.effectiveDate}" formatName="custom.datetime.format"/></td>
		</tr>
		</g:each>
	</tbody>
</table>
