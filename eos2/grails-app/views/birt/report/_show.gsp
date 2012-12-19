<%@ page import="org.leaf.eos2.birt.Report" %>
<table>
	<tbody>
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.id" default="Id" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "id")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.author" default="Author" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "author")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.comment" default="Comment" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "comment")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.createdBy" default="Created By" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "createdBy")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.description" default="Description" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "description")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.file" default="File" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "file")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.fullfile" default="Fullfile" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "fullfile")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.helpGuide" default="Help Guide" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "helpGuide")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.name" default="Name" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "name")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.reportParameters" default="Report Parameters" />:</td>
			<td  valign="top" style="text-align: left;" class="value">
				<ul>
				<g:each in="${reportInstance?.reportParameters}" var="reportParameterInstance">
					<li><g:link controller="reportParameter" action="show" id="${reportParameterInstance.id}">${reportParameterInstance.encodeAsHTML()}</g:link></li>
				</g:each>
				</ul>
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.title" default="Title" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "title")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="report.units" default="Units" />:</td>
			<td valign="top" class="value">${fieldValue(bean: reportInstance, field: "units")}</td>
		</tr>
		
	</tbody>
</table>