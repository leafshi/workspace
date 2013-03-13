<tr class="${(i % 2) == 0 ? 'odd' : 'even'}" childs="${salesOrderDetailInstance?.serialNumber}" coordinate="${i},-1" id="salesOrderDetails[${i}]">

	<!--serial number-->
	<td name="serialNumber">
		<ul>
			<li>
				<span name="salesOrderDetails[${i}].serialNumber_span">${fieldValue(bean: salesOrderDetailInstance, field: "serialNumber")}</span>
				<g:hiddenField name="salesOrderDetails[${i}].serialNumber" value="${salesOrderDetailInstance?.serialNumber}"/>
			</li>
			<li>
				<a href="#salesOrderDetails[${i}]" delete="delete" name="salesOrderDetails[${i}].serialNumber_a">
					${message(code: 'default.button.delete.label', default: 'Delete')}
				</a>
			</li>
		</ul>
	</td>

	<!--product-->
    <td name="product">
    	<ul>
			<li><g:message code="product.serialNumber.label"/> : ${fieldValue(bean: salesOrderDetailInstance, field: "product")}</li>
			<li><g:message code="product.name.label"/> : ${fieldValue(bean: salesOrderDetailInstance, field: "product.name")}</li>
			<li><g:message code="product.standard.label"/> : ${fieldValue(bean: salesOrderDetailInstance, field: "product.standard")}</li>
			<li>
				<g:message code="category.warehouse.label"/> : 
				${include(controller : 'salesOrder220Ajax', action : 'productCategoryOfCG', params : [productId : salesOrderDetailInstance?.product?.id] )}
			</li>
		</ul>
        <g:hiddenField name="salesOrderDetails[${i}].product.id" value="${salesOrderDetailInstance?.product?.id}"/>
		<g:hiddenField name="salesOrderDetails[${i}].openDetail" value="${salesOrderDetailInstance?.openDetail}" />
    </td>

	<!--price-->
    <td class="number" name="price">
		<span name="salesOrderDetails[${i}].price_span">${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}</span>
		<g:hiddenField name="salesOrderDetails[${i}].price" value="${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}" />
		<g:hiddenField name="salesOrderDetails[${i}].isAllowZeroPrice" value="${salesOrderDetailInstance?.isAllowZeroPrice}" />
		<g:hiddenField name="salesOrderDetails[${i}].finalPrice" value="${formatNumber( number : salesOrderDetailInstance?.finalPrice, format:'###,##0.000000')}"/>
		<g:hiddenField name="salesOrderDetails[${i}].discount" value="${formatNumber( number : salesOrderDetailInstance?.discount, format:'###,##0.0000')}"/>
		<g:hiddenField name="salesOrderDetails[${i}].specialDiscount" value="${formatNumber( number : salesOrderDetailInstance?.specialDiscount, format:'###,##0.0000')}"/>
		<g:hiddenField name="salesOrderDetails[${i}].isAllowSpecialDiscount" value="${salesOrderDetailInstance?.isAllowSpecialDiscount}"/>
		<g:hiddenField name="salesOrderDetails[${i}].finalDiscount" value="${formatNumber( number : salesOrderDetailInstance?.finalDiscount, format:'###,##0.0000')}"/>
    </td>
     
	<!--quantity-->
	<td name="quantity" >
		<g:textField name="salesOrderDetails[${i}].quantity" value="${formatNumber( number : salesOrderDetailInstance?.quantity?:0.00, format:'###,##0.00')}" spinner="Y"/>
	</td>
	
	<td>
		<ul>
			<li>
				<g:message default="Delivery Limitation" code="salesOrderDetail.deliveryLimitation.label" /> :
       			<g:jqDatePicker name="salesOrderDetails[${i}].deliveryLimitation" value="${salesOrderDetailInstance?.deliveryLimitation}"/>
			</li>
			<li>
				<g:message code="category.deliveryCycle.label"/> :
				<g:hiddenField name="salesOrderDetails[${i}].deliveryCycle" value="${salesOrderDetailInstance?.deliveryCycle}" />
       			<span name="salesOrderDetails[${i}].deliveryCycle">
       				${salesOrderDetailInstance?.deliveryCycle}
       			</span>
			</li>
		</ul>
	</td>
 
	<!--amount-->
	<td class="number" name="amount" >
		<span name="salesOrderDetails[${i}].amount_span">
			<g:formatNumber number="${salesOrderDetailInstance?.amount?:0}" format="###,##0.00" />
		</span>
		<g:hiddenField name="salesOrderDetails[${i}].fullAmount" value="${salesOrderDetailInstance?.fullAmount?:0}"/>
		<g:hiddenField name="salesOrderDetails[${i}].amount" value="${salesOrderDetailInstance?.amount?:0}"/>
		<g:hiddenField name="salesOrderDetails[${i}].specialAmount" value="${salesOrderDetailInstance?.specialAmount?:0}"/>
		<g:hiddenField name="salesOrderDetails[${i}].finalAmount" value="${salesOrderDetailInstance?.finalAmount?:0}"/>
	</td>
</tr>