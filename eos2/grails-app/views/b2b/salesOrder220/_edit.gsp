<%@ page import="org.leaf.eos2.b2b.SalesOrder" %>

<g:hiddenField name="recordType.id" value="${salesOrderInstance?.recordType?.id}" />
<!--master-->
<table>
	<caption><g:message code="salesOrder.page.master" default="Master" /></caption>
	<thead>
	</thead>
	<tbody>
		<!--first row-->
		<tr class="odd">
			<!--dealer-->
			<td>
				<label for="dealer">
					<g:message code="salesOrder.dealer.label" default="Dealer" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'dealer', 'error')} required">
				${salesOrderInstance?.dealer?.encodeAsHTML()}
				<g:hiddenField name="dealer.id" value="${salesOrderInstance?.dealer?.id}" />
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
			<td class="${hasErrors(bean: salesOrderInstance, field: 'orderDate', 'error')} required">
				<span>
					<g:set var="orderDate" value="${salesOrderInstance.orderDate?salesOrderInstance.orderDate:new Date() }"></g:set>
					${formatDate(format:'yyyy-MM-dd', date : orderDate) }
					<g:hiddenField name="orderDate_year" value="${formatDate(format:'yyyy', date : orderDate) }" />
					<g:hiddenField name="orderDate_month" value="${formatDate(format:'MM', date : orderDate) }" />
					<g:hiddenField name="orderDate_day" value="${formatDate(format:'dd', date : orderDate) }" />
				</span>
			</td>

		</tr>

		<!--second row -->
		<tr class="even">
			<!--industry-->
			<td>
				<label for="industryInput">
					<g:message code="salesOrder.industry.label" default="Industry" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'industry', 'error')} required">
				<g:field name="industryInput" value="${salesOrderInstance?.industry?.encodeAsHTML()}" required="" readonly="readonly"/>
				<g:hiddenField name="industry.id" value="${salesOrderInstance?.industry?.id}"  />
			</td>

			<!--project-->
			<td>
				<label for="project">
					<g:message code="salesOrder.project.label" default="Project" />
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'project', 'error')} ">
				<g:textField name="project" maxlength="10" value="${salesOrderInstance?.project}"/>
			</td>

			<!-- account serial number -->
			<td>
				<label for="accountSerialNumber">
					<g:message code="salesOrder.accountSerialNumber.label" default="Account Serial Number" />	
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'accountSerialNumber', 'error')} ">
				<g:textField name="accountSerialNumber" maxlength="10" value="${salesOrderInstance?.accountSerialNumber}"/>
			</td>
		</tr>
		

		<!--third row-->
		<tr class="odd">
			<!--sales man-->
			<td>
				<label for="salesMan">
					<g:message code="salesOrder.salesMan.label" default="Sales Man" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'salesMan', 'error')} required">
				<span id="salesMan">${salesOrderInstance?.salesMan?.encodeAsHTML()}</span>
				<g:hiddenField name="salesMan.id" value="${salesOrderInstance?.salesMan?.id}" />
			</td>

			<!--status-->
			<td>
				<label for="status">
					<g:message code="salesOrder.status.label" default="Status" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'status', 'error')} required">
				<span id="status">${salesOrderInstance?.status?.encodeAsHTML()}</span>
				<g:hiddenField name="status" value="${salesOrderInstance?.status}" />
			</td>
		
			<td>
				&nbsp;
			</td>
			<td>
				&nbsp;
			</td>

		</tr>

		<!--fourth row-->
		<tr class="even">
			<!--address no.1-->
			<td>
				<label for="address1">
					<g:message code="salesOrder.address1.label" default="Address1" />
				</label>
			</td>
			<td colspan="5" class="${hasErrors(bean: salesOrderInstance, field: 'address1', 'error')} ">
				<span>${salesOrderInstance?.address1}</span>
				<g:hiddenField name="address1" value="${salesOrderInstance?.address1}" />
			</td>
		</tr>

		<!--fifth row-->
		<tr class="odd">
			<!--address no.2-->
			<td>
				<label for="address2">
					<g:message code="salesOrder.address2.label" default="Address2" />
				</label>
			</td>
			<td colspan="5" class="${hasErrors(bean: salesOrderInstance, field: 'address2', 'error')} ">
				<span>${salesOrderInstance?.address2}</span>
				<g:hiddenField name="address2" value="${salesOrderInstance?.address2}" />
			</td>
		</tr>

		<!--sixth row-->
		<tr class="even">
			<!--description-->
			<td>
				<label for="description">
					<g:message code="salesOrder.description.label" default="Description" />
				</label>
			</td>
			<td colspan="5" class="${hasErrors(bean: salesOrderInstance, field: 'description', 'error')} ">
				<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${salesOrderInstance?.description}" rows="1"/>
			</td>
		</tr>

		<!--seventh row-->
		<tr class="odd">
			<!--owner-->
			<td>
				<label for="owner">
					<g:message code="salesOrder.owner.label" default="Owner" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'owner', 'error')} required">
				<span>${salesOrderInstance?.owner?.encodeAsHTML()}</span>
				<g:hiddenField name="owner.id" value="${salesOrderInstance?.owner?.id}" />
			</td>

			<!--created by-->
			<td>
				<label for="createdBy">
					<g:message code="salesOrder.createdBy.label" default="Created By" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'createdBy', 'error')} required">
				<span>${salesOrderInstance?.createdBy?.encodeAsHTML()}</span>
				<g:hiddenField name="createdBy.id" value="${salesOrderInstance?.createdBy?.id}" />
			</td>

			<!--last modified by-->
			<td>
				<label for="lastModifiedBy">
					<g:message code="salesOrder.lastModifiedBy.label" default="Last Modified By" />
					<span class="required-indicator">*</span>
				</label>
			</td>
			<td class="${hasErrors(bean: salesOrderInstance, field: 'lastModifiedBy', 'error')} required">
				<span>${salesOrderInstance?.lastModifiedBy?.encodeAsHTML()}</span>
				<g:hiddenField name="lastModifiedBy.id" value="${salesOrderInstance?.lastModifiedBy?.id}" />
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
				<g:render template="/b2b/salesOrder220/editDetail" model="['salesOrderDetailInstance':salesOrderDetailInstance,'i':i]"/>
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
						<g:message code="salesOrder.quantity.label" default="Quantity" /> :
						<span name="quantity_span">${fieldValue(bean: salesOrderInstance, field: 'quantity')}</span>
						<g:hiddenField name="quantity" value="${fieldValue(bean: salesOrderInstance, field: 'quantity')}" required=""/>
					</li>
				</ul>
			</td>
			
			<td class="required">
				<ul>
					<li>
						<g:message code="salesOrder.amount.label" default="Amount" /> :
						<span name="amount_span" >${fieldValue(bean: salesOrderInstance, field: 'amount')}</span>
						<g:hiddenField name="amount" value="${fieldValue(bean: salesOrderInstance, field: 'amount')}" required=""/>
					</li>
				</ul>
				<g:hiddenField name="specialAmount" value="${fieldValue(bean: salesOrderInstance, field: 'specialAmount')}" required=""/>
			</td>
		</tfoot>
	</table>
</div>
<div id="search_product_dialog" title="${message(code:'salesOrder.page.search.product', default:'Search Product')}" style="display:none;">
	<table>
		<thead>
			<tr>
				<th><g:message code="product.serialNumber.label" /></th>
				<th><g:message code="product.name.label" /></th>
				<th><g:message code="product.standard.label" /></th>
				<th><g:message code="product.price.label" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<g:textField name="searchProductNumber" style="width:100px;"/>
				</td>
				<td>
					<g:hiddenField name="searchProductId"/>	
					<span id="searchProductName"></span>
				</td>
				<td>
					<span id="searchProductStandard"></span>
				</td>
				<td>
					<span id="searchProductPrice"></span>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<div id="dialog-confirm" title="${message(code:'salesOrder.page.delete.dialog.title', default:'Delete Row!')}" style="display:none;">
    <p>
		<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
		${message(code:'salesOrder.page.delete.dialog.title', default:'Delete Row!')}
	</p>
</div>
<div id="salesOrderPageFooter"></div> 
<g:render template="/b2b/salesOrder220/javascript" />
