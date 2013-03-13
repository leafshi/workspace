<tr class="${(i % 2) == 0 ? 'odd' : 'even'}" childs="${salesOrderDetailInstance?.serialNumber}" coordinate="${i},-1" id="salesOrderDetails[${i}]">

	<!--serial number-->
	<td name="serialNumber">
		<span name="salesOrderDetails[${i}].serialNumber_span">${fieldValue(bean: salesOrderDetailInstance, field: "serialNumber")}</span>
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
    </td>

	<!--price-->
    <td class="number" name="price">
		<span name="salesOrderDetails[${i}].price_span">${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}</span>
    </td>
     
	<!--quantity-->
	<td class="number" name="quantity" >
		${formatNumber( number : salesOrderDetailInstance?.quantity?:0.00, format:'###,##0.00')}
	</td>
	
	<!--deliveryLimitation-->
	<td class="number" name="deliveryLimitation" >
		<ul>
			<li>
				<g:formatDate date="${salesOrderDetailInstance?.deliveryLimitation}" formatName="custom.date.format"/>
			</li>
			<li>
				<g:message code="category.deliveryCycle.label"/> :
       			<span name="salesOrderDetails[${i}].deliveryCycle">
       				${salesOrderDetailInstance?.deliveryCycle}
       			</span>
			</li>
		</ul>
	</td>
 
	<!--amount-->
	<td class="number" name="amount" >
		<g:formatNumber number="${salesOrderDetailInstance?.amount?:0}" format="###,##0.00" />
	</td>
</tr>