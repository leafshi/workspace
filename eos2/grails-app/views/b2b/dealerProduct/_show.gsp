<table>
	<tbody>
	
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.dealer.label" default="Dealer" />:</td>
			<td valign="top" class="value"><g:link controller="dealer" action="show" id="${dealerProductInstance?.dealer?.id}">${dealerProductInstance?.dealer?.encodeAsHTML()}</g:link></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.product.label" default="Product" />:</td>
			<td valign="top" class="value"><g:link controller="product" action="show" id="${dealerProductInstance?.product?.id}">${dealerProductInstance?.product?.encodeAsHTML()}</g:link></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.unit1.label" default="Unit1" />:</td>
			<td valign="top" class="value">${fieldValue(bean: dealerProductInstance, field: "unit1")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.unit2.label" default="Unit2" />:</td>
			<td valign="top" class="value">${fieldValue(bean: dealerProductInstance, field: "unit2")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.currency.label" default="Currency" />:</td>
			<td valign="top" class="value">${fieldValue(bean: dealerProductInstance, field: "currency")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.price.label" default="Price" />:</td>
			<td valign="top" class="value"><g:formatNumber number="${dealerProductInstance?.price}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.componentPricing.label" default="Component Pricing" />:</td>
			<td valign="top" class="value"><g:formatBoolean boolean="${dealerProductInstance?.componentPricing}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.approvalDate.label" default="Approval Date" />:</td>
			<td valign="top" class="value"><g:formatDate date="${dealerProductInstance?.approvalDate}" formatName="custom.date.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.lastTransactionDate.label" default="Last Transaction Date" />:</td>
			<td valign="top" class="value"><g:formatDate date="${dealerProductInstance?.lastTransactionDate}" formatName="custom.date.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.isIncludeTax.label" default="Is Include Tax" />:</td>
			<td valign="top" class="value"><g:formatBoolean boolean="${dealerProductInstance?.isIncludeTax}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.firstTransactionDate.label" default="First Transaction Date" />:</td>
			<td valign="top" class="value"><g:formatDate date="${dealerProductInstance?.firstTransactionDate}" formatName="custom.date.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.commissionOfUnit.label" default="Commission Of Unit" />:</td>
			<td valign="top" class="value"><g:formatNumber number="${dealerProductInstance?.commissionOfUnit}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.commissionPercent.label" default="Commission Percent" />:</td>
			<td valign="top" class="value"><g:formatNumber number="${dealerProductInstance?.commissionPercent}" /></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.beginDate.label" default="Begin Date" />:</td>
			<td valign="top" class="value"><g:formatDate date="${dealerProductInstance?.beginDate}" formatName="custom.date.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.closeDate.label" default="Close Date" />:</td>
			<td valign="top" class="value"><g:formatDate date="${dealerProductInstance?.closeDate}" formatName="custom.date.format"/></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.description.label" default="Description" />:</td>
			<td valign="top" class="value">${fieldValue(bean: dealerProductInstance, field: "description")}</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.owner.label" default="Owner" />:</td>
			<td valign="top" class="value"><g:link controller="user" action="show" id="${dealerProductInstance?.owner?.id}">${dealerProductInstance?.owner?.encodeAsHTML()}</g:link></td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.createdBy.label" default="Created By" />:</td>
			<td valign="top" class="value">
				<g:link controller="user" action="show" id="${dealerProductInstance?.createdBy?.id}">
					${dealerProductInstance?.createdBy?.encodeAsHTML()}
					,
					<g:formatDate date="${dealerProductInstance?.dateCreated}" formatName="custom.datetime.format"/>
				</g:link>
			</td>
		</tr>
		
		<tr class="prop">
			<td valign="top" class="name"><g:message code="dealerProduct.lastModifiedBy.label" default="Last Modified By" />:</td>
			<td valign="top" class="value">
				<g:link controller="user" action="show" id="${dealerProductInstance?.lastModifiedBy?.id}">
					${dealerProductInstance?.lastModifiedBy?.encodeAsHTML()}
					,
					<g:formatDate date="${dealerProductInstance?.lastUpdated}" formatName="custom.datetime.format"/>
				</g:link>
			</td>
		</tr>		
	</tbody>
</table>