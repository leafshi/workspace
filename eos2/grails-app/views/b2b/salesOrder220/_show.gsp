<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>

<table>
	<caption><g:message code="salesOrder.page.master" default="Master" /></caption>
	<tbody>
		<!--first row-->
		<tr class="odd">
			<!--dealer-->
			<td>
				<span id="dealer-label" class="property-label"><g:message code="salesOrder.dealer.label" default="Dealer" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="dealer-label">
					<g:link controller="dealer" action="show" id="${salesOrderInstance?.dealer?.id}">${salesOrderInstance?.dealer?.encodeAsHTML()}</g:link>
				</span>
			</td>
			
			<!-- department -->
			<td>
				<label for="department">
					<g:message code="salesOrder.department.label" default="Department" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td>
				<span id="department">
					${include(controller : 'salesOrder220Ajax', action : 'getDealerDepartment', params : [dealerSerialNumber : salesOrderInstance?.dealer?.serialNumber] )}
				</span>
			</td>

			<!--order date-->
			<td>
				<label for="orderDate">
					<g:message code="salesOrder.orderDate.label" default="Order Date" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td>
				<span>
					<g:set var="orderDate" value="${salesOrderInstance?.orderDate }"></g:set>
					${formatDate(format:'yyyy-MM-dd', date : orderDate) }
					<g:hiddenField name="orderDate_year" value="${formatDate(format:'yyyy', date : orderDate) }" />
					<g:hiddenField name="orderDate_month" value="${formatDate(format:'MM', date : orderDate) }" />
					<g:hiddenField name="orderDate_day" value="${formatDate(format:'dd', date : orderDate) }" />
				</span>
			</td>
		</tr>
		
		
		<tr class="even">
			<td>

				<span id="industry-label" class="property-label"><g:message code="salesOrder.industry.label" default="Industry" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="industry-label">
					<g:link controller="industry" action="show" id="${salesOrderInstance?.industry?.id}">${salesOrderInstance?.industry?.encodeAsHTML()}</g:link>
				</span>
			</td>
			
			<td>
				<span id="project-label" class="property-label"><g:message code="salesOrder.project.label" default="Project" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="project-label"><g:fieldValue bean="${salesOrderInstance}" field="project"/></span>
			</td>
			
			<td>
				<span id="accountSerialNumber-label" class="property-label"><g:message code="salesOrder.accountSerialNumber.label" default="Account Serial Number" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="accountSerialNumber-label"><g:fieldValue bean="${salesOrderInstance}" field="accountSerialNumber"/></span>
			</td>

		</tr>
		
		<tr class="odd">
			<td>
				<span id="salesMan-label" class="property-label"><g:message code="salesOrder.salesMan.label" default="Sales Man" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="salesMan-label">
					<g:link controller="staff" action="show" id="${salesOrderInstance?.salesMan?.id}">${salesOrderInstance?.salesMan?.encodeAsHTML()}</g:link>
				</span>
			</td>
			
			<td>
				<span id="status-label" class="property-label"><g:message code="salesOrder.status.label" default="Status" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${salesOrderInstance}" field="status"/></span>
			</td>

			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>
		
		</tr>
		
		<tr class="even">
			<td>
				<span id="serialNumber-label" class="property-label"><g:message code="salesOrder.serialNumber.label" default="Serial Number" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="serialNumber-label"><g:fieldValue bean="${salesOrderInstance}" field="serialNumber"/></span>
			</td>
			
			<td>
				<span id="erpSerialNumber-label" class="property-label"><g:message code="salesOrder.erpSerialNumber.label" default="ERP Serial Number" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="erpSerialNumber-label"><g:fieldValue bean="${salesOrderInstance}" field="erpSerialNumber"/></span>
			</td>
			
			<td>
				<span id="effectiveDate-label" class="property-label"><g:message code="salesOrder.effectiveDate.label" default="Effective Date" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="effectiveDate-label"><g:formatDate date="${salesOrderInstance?.effectiveDate}" formatName="custom.date.format"/></span>
			</td>
		
		</tr>
		
		<tr class="odd">
			<td>
				<span id="address1-label" class="property-label"><g:message code="salesOrder.address1.label" default="Address1" /></span>
			</td>
			<td colspan="5">
				<span class="property-value" aria-labelledby="address1-label"><g:fieldValue bean="${salesOrderInstance}" field="address1"/></span>
			</td>
		</tr>
		
		<tr class="even">
			<td>
				<span id="address2-label" class="property-label"><g:message code="salesOrder.address2.label" default="Address2" /></span>
			</td>
			<td colspan="5">
				<span class="property-value" aria-labelledby="address2-label"><g:fieldValue bean="${salesOrderInstance}" field="address2"/></span>
			</td>
		</tr>
		
		<tr class="odd">
			<td>
				<span id="description-label" class="property-label"><g:message code="salesOrder.description.label" default="Description" /></span>
			</td>
			<td colspan="5">
				<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${salesOrderInstance}" field="description"/></span>
			</td>
		</tr>
		
		<!--seventh row-->
		<tr class="even">
			<!--owner-->
			<td>
				<span id="owner-label" class="property-label"><g:message code="salesOrder.owner.label" default="Owner" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="owner-label">
					<g:link controller="user" action="show" id="${salesOrderInstance?.owner?.id}">${salesOrderInstance?.owner?.encodeAsHTML()}</g:link>
				</span>
			</td>

			<!--created by-->
			<td>
				<span id="createdBy-label" class="property-label"><g:message code="salesOrder.createdBy.label" default="Created By" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="createdBy-label">
					<g:link controller="user" action="show" id="${salesOrderInstance?.createdBy?.id}">${salesOrderInstance?.createdBy?.encodeAsHTML()}</g:link>
				</span>
				,
				<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${salesOrderInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
			</td>

			<!--last modified by-->
			<td>
				<span id="lastModifiedBy-label" class="property-label"><g:message code="salesOrder.lastModifiedBy.label" default="Last Modified By" /></span>
			</td>
			<td>
				<span class="property-value" aria-labelledby="lastModifiedBy-label">
					<g:link controller="user" action="show" id="${salesOrderInstance?.lastModifiedBy?.id}">${salesOrderInstance?.lastModifiedBy?.encodeAsHTML()}</g:link>
				</span>
				,
				<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${salesOrderInstance?.lastUpdated}" formatName="custom.datetime.format"/></span>
			</td>
		</tr>
	</tbody>
</table>
<!--detail-->
<div class="list">
	<table id="salesOrder_detail_list">
		<caption><g:message code="salesOrder.page.detail" default="Detail" /></caption>
    	<thead>
        	<tr>
                <th><label><g:message code="salesOrderDetail.serialNumber.label" default="Serial Number"/></label></th>
                <th><label><g:message code="salesOrderDetail.product.label" default="Product" /></label></th>
				<th class="number"><label><g:message code="salesOrderDetail.priceTitle.label" default="Price" /></label></th>
				<th><label><g:message code="salesOrderDetail.discountTitle.label" default="Discount" /></label></th>
                <th>
				<label><g:message default="Quantity" code="salesOrderDetail.quantity.label" /></label>
				&
				<label><g:message default="Delivery Limitation" code="salesOrderDetail.deliveryLimitation.label" /></label>
				</th>
				<th class="number"><label><g:message code="salesOrderDetail.amountTitle.label" default="Amount" /></label></th>
            </tr>
        </thead>
        <tbody>
			<g:each in="${salesOrderInstance.salesOrderDetails}" status="i" var="salesOrderDetailInstance">
				<g:render template="/b2b/salesOrder220/showDetail" model="['salesOrderDetailInstance':salesOrderDetailInstance,'i':i]"/>
			</g:each>
		</tbody>
		<tfoot>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td class="required">
				<ul>
					<li>
						<span id="quantity-label" class="property-label"><g:message code="salesOrder.quantity.label" default="Quantity" /></span> :
						<span class="property-value" aria-labelledby="quantity-label">
							<g:formatNumber number="${salesOrderInstance?.quantity?:0}" format="###,##0.00" />
						</span>
					</li>
				</ul>
			</td>
			
			<td class="required">
				<ul>
					<li>
						<span id="amount-label" class="property-label"><g:message code="salesOrder.amount.label" default="Amount" /></span> :
						<span class="property-value" aria-labelledby="amount-label">
							<g:formatNumber number="${salesOrderInstance?.amount?:0}" format="###,##0.00" />
						</span>
					</li>
					<li>
						<span id="specialAmount-label" class="property-label"><g:message code="salesOrder.specialAmount.label" default="Special Amount" /> :</span>
						<span class="property-value" aria-labelledby="specialAmount-label">
							<g:formatNumber number="${salesOrderInstance?.specialAmount?:0}" format="###,##0.00" />
						</span>
					</li>
					<li>
						<span id="finalAmount-label" class="property-label"><g:message code="salesOrder.finalAmount.label" default="Final Amount" /> :</span>
						<span class="property-value" aria-labelledby="finalAmount-label">
							<g:formatNumber number="${(salesOrderInstance?.amount?:0) - (salesOrderInstance?.specialAmount?:0)}" format="###,##0.00" />
						</span>
					</li>
				</ul>
				<g:hiddenField name="specialAmount" value="${fieldValue(bean: salesOrderInstance, field: 'specialAmount')}" required=""/>
			</td>
		</tfoot>
	</table>
</div>
<!--workflow history-->
<div class="dialog">
	<g:include controller="salesOrder220Ajax" action="workflowHistory" params="[salesOrderId: salesOrderInstance?.id, ownerId : salesOrderInstance?.owner?.id]" />
</div>
<!--outbound history-->
<div class="dialog">
	<g:include controller="salesOrder220Ajax" action="outBoundMessage" params="[salesOrderId: salesOrderInstance?.id]" />
</div>
<script type="text/javascript">
$(document).ready(function() {

	$("tr[parent]").addClass('hide_detail');

	//open detail
	$("input[name$='.openDetail']").each(function(){
		$(this).click(function(){
			$("tr[parent='"+$(this).closest('tr').attr('childs')+"']").toggleClass( 'hide_detail');
			return false;
		});
	});
});
</script>
