
<%@ page import="org.leaf.eos2.snapshot.CreditControl" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'creditControl.label', default: 'CreditControl')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-creditControl" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-creditControl" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="creditControl.dealer.label" default="Dealer" /></th>
					
						<g:sortableColumn property="orderAmount" title="${message(code: 'creditControl.orderAmount.label', default: 'Order Amount')}" />
					
						<g:sortableColumn property="openingSell" title="${message(code: 'creditControl.openingSell.label', default: 'Opening Sell')}" />
					
						<g:sortableColumn property="receivableAmout" title="${message(code: 'creditControl.receivableAmout.label', default: 'Receivable Amout')}" />
					
						<g:sortableColumn property="receivableNote" title="${message(code: 'creditControl.receivableNote.label', default: 'Receivable Note')}" />
					
						<g:sortableColumn property="receivable" title="${message(code: 'creditControl.receivable.label', default: 'Receivable')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${creditControlInstanceList}" status="i" var="creditControlInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${creditControlInstance.id}">${fieldValue(bean: creditControlInstance, field: "dealer")}</g:link></td>
					
						<td>${fieldValue(bean: creditControlInstance, field: "orderAmount")}</td>
					
						<td>${fieldValue(bean: creditControlInstance, field: "openingSell")}</td>
					
						<td>${fieldValue(bean: creditControlInstance, field: "receivableAmout")}</td>
					
						<td>${fieldValue(bean: creditControlInstance, field: "receivableNote")}</td>
					
						<td>${fieldValue(bean: creditControlInstance, field: "receivable")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${creditControlInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
