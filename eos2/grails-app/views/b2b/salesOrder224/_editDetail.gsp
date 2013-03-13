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
				${include(controller : 'salesOrder224Ajax', action : 'productCategoryOfCG', params : [productId : salesOrderDetailInstance?.product?.id] )}
			</li>
			<li>
				<g:message code="salesOrderDetail.openDetail.label" default="Open Detail" /> :
				<g:checkBox name="salesOrderDetails[${i}].openDetail" value="${salesOrderDetailInstance?.openDetail}" />
			</li>
		</ul>
        <g:hiddenField name="salesOrderDetails[${i}].product.id" value="${salesOrderDetailInstance?.product?.id}"/>
    </td>

	<!--price-->
    <td class="number" name="price">
		<ul>
        	<li>
				<g:message code="salesOrderDetail.price.label"/> : 
				<span name="salesOrderDetails[${i}].price_span">${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}</span>
				<g:hiddenField name="salesOrderDetails[${i}].price" value="${formatNumber( number : salesOrderDetailInstance?.price, format:'###,##0.000000')}" />
				<g:hiddenField name="salesOrderDetails[${i}].isAllowZeroPrice" value="${salesOrderDetailInstance?.isAllowZeroPrice}" />
			</li>
			<li>
				<g:message code="salesOrderDetail.finalPrice.label"/> : 
				<span name="salesOrderDetails[${i}].finalPrice_span">${formatNumber( number : salesOrderDetailInstance?.finalPrice, format:'###,##0.000000')}</span>
				<g:hiddenField name="salesOrderDetails[${i}].finalPrice" value="${formatNumber( number : salesOrderDetailInstance?.finalPrice, format:'###,##0.000000')}"/>
			</li>
		</ul>
    </td>
    
	<!--discount-->
    <td class="number" name="discount">
    	<g:set var="discountCss" value="${(salesOrderDetailInstance?.openDetail == true) ? 'color : red' : ''}" />
		<ul>
			<li>
				<g:message code="salesOrderDetail.discount.label"/> :
        		<span name="salesOrderDetails[${i}].discount_span" style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.discount, format:'###,##0.00%')}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].discount" value="${formatNumber( number : salesOrderDetailInstance?.discount, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialDiscount.label"/> :
        		<span name="salesOrderDetails[${i}].specialDiscount_span" style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.specialDiscount, format:'###,##0.00%')}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].specialDiscount" value="${formatNumber( number : salesOrderDetailInstance?.specialDiscount, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalDiscount.label"/> :
        		<span name="salesOrderDetails[${i}].finalDiscount_span" style="${discountCss}">
					${formatNumber( number : salesOrderDetailInstance?.finalDiscount, format:'###,##0.00%')}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].finalDiscount" value="${formatNumber( number : salesOrderDetailInstance?.finalDiscount, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.isAllowSpecialDiscount.label"/> :
				<span name="salesOrderDetails[${i}].isAllowSpecialDiscount_span">
					${salesOrderDetailInstance?.isAllowSpecialDiscount}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].isAllowSpecialDiscount" value="${salesOrderDetailInstance?.isAllowSpecialDiscount}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.contractDetail.label"/> :
				<span name="salesOrderDetails[${i}].contractDetail_span">
					${salesOrderDetailInstance?.contractDetail}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].contractDetail.id" value="${salesOrderDetailInstance?.contractDetail?.id}"/>
			</li>
		</ul>
    </td>
 
	<!--quantity-->
	<td name="quantity" >
		<ul>
			<li>
				<g:message default="Quantity" code="salesOrderDetail.quantity.label" /> :
				<g:textField name="salesOrderDetails[${i}].quantity" value="${formatNumber( number : salesOrderDetailInstance?.quantity?:0.00, format:'###,##0.00')}" spinner="Y"/>
			</li>
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
		<ul>
			<li>
				<g:message code="salesOrderDetail.fullAmount.label" default="Full Amount" /> :
				<span name="salesOrderDetails[${i}].fullAmount_span">
                    <g:formatNumber number="${salesOrderDetailInstance?.fullAmount?:0}" format="###,##0.00" />
                </span>
                <g:hiddenField name="salesOrderDetails[${i}].fullAmount" value="${salesOrderDetailInstance?.fullAmount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.amount.label" default="Amount" /> : 
				<span name="salesOrderDetails[${i}].amount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.amount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].amount" value="${salesOrderDetailInstance?.amount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialAmount.label" default="Special Amount" /> : 
				<span name="salesOrderDetails[${i}].specialAmount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.specialAmount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].specialAmount" value="${salesOrderDetailInstance?.specialAmount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalAmount.label" default="Final Amount" /> : 
				<span name="salesOrderDetails[${i}].finalAmount_span">
					<g:formatNumber number="${salesOrderDetailInstance?.finalAmount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].finalAmount" value="${salesOrderDetailInstance?.finalAmount?:0}"/>
			</li>
		</ul>
		
	</td>
</tr>

<g:each in="${salesOrderDetailInstance?.salesOrderDetailDetails?.sort{it?.serialNumber}}" status="j" var="salesOrderDetailDetailInstance">
		
<tr class="${(i % 2) == 0 ? 'odd' : 'even'} hide_detail" parent="${salesOrderDetailInstance?.serialNumber}" coordinate="${i},${j}">
	<!--serial number-->
	<td name="serialNumber">
		<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].serialNumber_span">${fieldValue(bean: salesOrderDetailInstance, field: "serialNumber")}-${fieldValue(bean: salesOrderDetailDetailInstance, field: "serialNumber")}</span>
		<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].serialNumber" value="${salesOrderDetailDetailInstance?.serialNumber}"/>
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
        <g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].product.id" value="${salesOrderDetailDetailInstance?.product?.id}"/>
    </td>


	<!--price-->
    <td class="number" name="price">
		<ul>
        	<li>
				<g:message code="salesOrderDetail.price.label"/> : 
				<span>${formatNumber( number : salesOrderDetailDetailInstance?.price, format:'###,##0.000000')}</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].price" value="${formatNumber( number : salesOrderDetailDetailInstance?.price, format:'###,##0.000000')}" />
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].isAllowZeroPrice" value="${salesOrderDetailDetailInstance?.isAllowZeroPrice}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalPrice.label"/> : 

				<span>${formatNumber( number : salesOrderDetailDetailInstance?.finalPrice, format:'###,##0.000000')}</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].finalPrice" value="${formatNumber( number : salesOrderDetailDetailInstance?.finalPrice, format:'###,##0.000000')}"/>

			</li>
		</ul>
    </td>
 
	<!--discount-->
    <td class="number" name="discount">
		<ul>
			<li>
				<g:message code="salesOrderDetail.discount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.discount?:0, format:'###,##0.00%')}</span>
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].discount"
					value="${formatNumber( number : salesOrderDetailDetailInstance?.discount?:0, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialDiscount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.specialDiscount?:0, format:'###,##0.00%')}</span>
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].specialDiscount"
					value="${formatNumber( number : salesOrderDetailDetailInstance?.specialDiscount?:0, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalDiscount.label"/> :
        		<span>${formatNumber( number : salesOrderDetailDetailInstance?.finalDiscount?:0, format:'###,##0.00%')}</span>
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].finalDiscount"
					value="${formatNumber( number : salesOrderDetailDetailInstance?.finalDiscount?:0, format:'###,##0.0000')}"/>
			</li>
			<li>
				<g:message code="salesOrderDetailDetail.isAllowSpecialDiscount.label"/> :
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].isAllowSpecialDiscount_span">
					${salesOrderDetailDetailInstance?.isAllowSpecialDiscount}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].isAllowSpecialDiscount" value="${salesOrderDetailDetailInstance?.isAllowSpecialDiscount}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.contractDetail.label"/> :
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].contractDetail_span">
					${salesOrderDetailDetailInstance?.contractDetail}
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].contractDetail.id" value="${salesOrderDetailDetailInstance?.contractDetail?.id}"/>
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
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].quantity" 
					value="${formatNumber( number : salesOrderDetailDetailInstance?.quantity?:0, format:'##0.000')}"/>
			</li>
			<li>
				<g:message code="bomDetail.dosage.label"/> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].dosage_span">
					${salesOrderDetailDetailInstance?.dosage?:0}
				</span>
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].dosage" 
					value="${salesOrderDetailDetailInstance?.dosage?:0}"/>
			</li>
			<li>
				<g:message code="bomDetail.quota.label"/> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].quota_span">
					${salesOrderDetailDetailInstance?.quota?:0}
				</span>
				<g:hiddenField 
					name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].quota" 
					value="${salesOrderDetailDetailInstance?.quota?:0}"/>
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
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].fullAmount" value="${salesOrderDetailDetailInstance?.fullAmount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.amount.label" default="Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].amount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.amount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].amount" value="${salesOrderDetailDetailInstance?.amount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.specialAmount.label" default="Special Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].specialAmount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.specialAmount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].specialAmount" value="${salesOrderDetailDetailInstance?.specialAmount?:0}"/>
			</li>
			<li>
				<g:message code="salesOrderDetail.finalAmount.label" default="Final Amount" /> : 
				<span name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].finalAmount_span">
					<g:formatNumber number="${salesOrderDetailDetailInstance?.finalAmount?:0}" format="###,##0.00" />
				</span>
				<g:hiddenField name="salesOrderDetails[${i}].salesOrderDetailDetails[${j}].finalAmount" value="${salesOrderDetailDetailInstance?.finalAmount?:0}"/>
			</li>
		</ul>
		
	</td>
</tr>
</g:each>
