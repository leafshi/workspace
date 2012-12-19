<table>
	<thead>
		<tr>
			<g:sortableColumn property="serialNumber" title="${message(code: 'industry.serialNumber.label', default: 'Serial Number')}" />
			<g:sortableColumn property="shortName" title="${message(code: 'industry.shortName.label', default: 'Short Name')}" />
			<g:sortableColumn property="fullName" title="${message(code: 'industry.fullName.label', default: 'Full Name')}" />
			<g:sortableColumn property="isActive" title="${message(code: 'industry.isActive.label', default: 'Is Active')}" />
			<g:sortableColumn property="description" title="${message(code: 'industry.description.label', default: 'Description')}" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${industryInstanceList}" status="i" var="industryInstance">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:link action="show" id="${industryInstance.id}">${fieldValue(bean: industryInstance, field: "serialNumber")}</g:link></td>
			<td>${fieldValue(bean: industryInstance, field: "shortName")}</td>
			<td>${fieldValue(bean: industryInstance, field: "fullName")}</td>
			<td><g:formatBoolean boolean="${industryInstance.isActive}" /></td>
			<td>${fieldValue(bean: industryInstance, field: "description")}</td>
		</tr>
		</g:each>
	</tbody>
</table>
