<tr class="${(i % 2) == 0 ? 'odd' : 'even'}" childs="${salesOrderDetailInstance?.serialNumber}" coordinate="${i},-1" id="salesOrderDetails[${i}]">

	<!--serial number-->
	<td name="serialNumber">
		<ul>
			<li>
				<span name="salesOrderDetails[${i}].serialNumber_span">${fieldValue(bean: salesOrderDetailInstance, field: "serialNumber")}</span>
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
				${include(controller : 'salesOrder224Ajax', action : 'productCategoryOfCG', params : [productId : salesOrderDetailInstance?.product?.id] )}
			</li>
			<li>
				<g:message code="salesOrderDetail.openDetail.label" default="Open Detail" /> :
				<g:checkBox name="salesOrderDetails[${i}].openDetail" value="${salesOrderDetailInstance?.openDetail}" onclick="javascript: return false;" />
			</li>
		</ul>
    </td>

	<!--price-->
    <td class="number" name="price">
		<ul>
        	<li>
				<g:message code="salesOrderDetail.price.label"/> : 
				<span name="salesOrderDetails[${i}].price_span">${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalPrice.label"/> : 
				<span name="salesOrderDetails[${i}].finalPrice_span">${formatNumber( number : salesOrderDetailInstance?.finalPrice, format:'###,##0.000000')}</span>
			</li>
			<li>
				<g:message code="category.isAllowZeroPrice.label"/> :
        		<span style="${salesOrderDetailInstance?.isAllowZeroPrice == 'Y'? 'color : blue':'color : red'}">
        			${salesOrderDetailInstance?.isAllowZeroPrice}
        		</span>
			</li>
    </td>
    
	<!--discount-->
    <td class="number" name="discount">
    	<g:set var="discountCss" value="${(salesOrderDetailInstance?.openDetail == true) ? 'color : red' : ''}" />
		<ul>
			<li>
				<g:message code="salesOrderDetail.discount.label"/> :
        		<span style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.discount, format:'###,##0.00%')}
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialDiscount.label"/> :
        		<span style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.specialDiscount, format:'###,##0.00%')}
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalDiscount.label"/> :
        		<span style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.finalDiscount, format:'###,##0.00%')}
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.isAllowSpecialDiscount.label"/> :
				<span>${salesOrderDetailInstance?.isAllowSpecialDiscount}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.contractDetail.label"/> :
				<span>${salesOrderDetailInstance?.contractDetail}</span>
			</li>

		</ul>
       	
    </td>
 
	<!--quantity-->
	<td class="number" name="quantity" >
		<ul>
			<li>
				<g:message default="Quantity" code="salesOrderDetail.quantity.label" /> :
				${formatNumber( number : salesOrderDetailInstance?.quantity?:0.00, format:'###,##0.00')}
			</li>
			<li>
				<g:message default="Delivery Limitation" code="salesOrderDetail.deliveryLimitation.label" /> :
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
		<ul>
			<li>
				<g:message code="salesOrderDetail.fullAmount.label" default="Full Amount" /> :
				<span name="salesOrderDetails[${i}].fullAmount_span">
                    <g:formatNumber number="${salesOrderDetailInstance?.fullAmount?:0}" format="###,##0.00" />
                </span>
			</li>
			<li>
				<g:message code="salesOrderDetail.amount.label" default="Amount" /> : 
				<span name="salesOrderDetails[${i}].amount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.amount?:0}" format="###,##0.00" />
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialAmount.label" default="Special Amount" /> : 
				<span name="salesOrderDetails[${i}].specialAmount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.specialAmount?:0}" format="###,##0.00" />
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalAmount.label" default="Final Amount" /> : 
				<span name="salesOrderDetails[${i}].finalAmount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.finalAmount?:0}" format="###,##0.00" />
				</span>
			</li>
		</ul>
		
	</td>
</tr>


<g:each in="${salesOrderDetailInstance?.salesOrderDetailDetails?.sort{it?.serialNumber}}" status="j" var="salesOrderDetailDetailInstance">
		
<tr class="${(i % 2) == 0 ? 'odd' : 'even'}" parent="${salesOrderDetailInstance?.serialNumber}" coordinate="${i},${j}">
	<!--serial number-->
	<td name="serialNumber">
		<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].serialNumber_span">
			${fieldValue(bean: salesOrderDetailInstance, field: "serialNumber")}-${fieldValue(bean: salesOrderDetailDetailInstance, field: "serialNumber")}
		</span>
	</td>
	<!--product-->
    <td name="product">
    	<ul>
			<li><g:message code="product.serialNumber.label"/> : ${fieldValue(bean: salesOrderDetailDetailInstance, field: "product")}</li>
			<li><g:message code="product.name.label"/> : ${fieldValue(bean: salesOrderDetailDetailInstance, field: "product.name")}</li>
			<li><g:message code="product.standard.label"/> : ${fieldValue(bean: salesOrderDetailDetailInstance, field: "product.standard")}</li>
			<li>
				<g:message code="category.warehouse.label"/> : 
				${include(controller : 'salesOrder224Ajax', action : 'productCategoryOfCG', params : [productId : salesOrderDetailDetailInstance?.product?.id] )}
			</li>
		</ul>
    </td>

	<!--price-->
    <td class="number" name="price">
		<ul>
        	<li>
				<g:message code="salesOrderDetail.price.label"/> : 
				<span>${formatNumber( number : salesOrderDetailDetailInstance?.price, format:'###,##0.000000')}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalPrice.label"/> : 
				<span>${formatNumber( number : salesOrderDetailDetailInstance?.finalPrice, format:'###,##0.000000')}</span>
			</li>
			<li>
				<g:message code="category.isAllowZeroPrice.label"/> :
        		<span style="${salesOrderDetailDetailInstance?.isAllowZeroPrice == 'Y'? 'color : blue':'color : red'}">
        			${salesOrderDetailDetailInstance?.isAllowZeroPrice}
        		</span>
			</li>
		</ul>
    </td>
 
	<!--discount-->
    <td class="number" name="discount">
		<ul>
			<li>
				<g:message code="salesOrderDetail.discount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.discount?:0, format:'###,##0.00%')}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialDiscount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.specialDiscount?:0, format:'###,##0.00%')}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalDiscount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.finalDiscount?:0, format:'###,##0.00%')}</span>
			</li>
			<li>
				<g:message code="salesOrderDetailDetail.isAllowSpecialDiscount.label"/> :
				<span>${salesOrderDetailDetailInstance?.isAllowSpecialDiscount}</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.contractDetail.label"/> :
				<span>${salesOrderDetailDetailInstance?.contractDetail}</span>
			</li>

		</ul>
    </td>

	<!--quantity-->
	<td class="number" name="quantity">

		<ul>
			<li>
				<g:message code="salesOrderDetail.quantity.label" default="Quantity" /> : 
				<span 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].quantity_span">
					${formatNumber( number : salesOrderDetailDetailInstance?.quantity?:0.00, format:'##0.000')}
				</span>
			</li>
			<li>
				<g:message code="bomDetail.dosage.label"/> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].dosage_span">
					${salesOrderDetailDetailInstance?.dosage}
				</span>
			</li>
			<li>
				<g:message code="bomDetail.quota.label"/> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].quota_span">
					${salesOrderDetailDetailInstance?.quota}
				</span>
			</li>
		</ul>
	</td>
 
	<!--amount-->
	<td class="number" name="amount">
		<ul>
			<li>
				<g:message code="salesOrderDetail.fullAmount.label" default="Full Amount" /> :
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].fullAmount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.fullAmount?:0}" format="###,##0.00" />
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.amount.label" default="Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].amount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.amount?:0}" format="###,##0.00" />
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialAmount.label" default="Special Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].specialAmount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.specialAmount?:0}" format="###,##0.00" />
				</span>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalAmount.label" default="Final Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].finalAmount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.finalAmount?:0}" format="###,##0.00" />
				</span>
			</li>
		</ul>
		
	</td>
	</tr>
</g:each>
