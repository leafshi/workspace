<%@ page import="org.leaf.eos2.b2b.DealerProduct" %>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'dealer', 'error')} required">
	<label for="dealer">
		<g:message code="dealerProduct.dealer.label" default="Dealer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="dealer.id" from="${org.leaf.eos2.b2b.Dealer.list()}" optionKey="id" value="${dealerProductInstance?.dealer?.id}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="dealerProduct.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="product.id" from="${org.leaf.eos2.b2b.Product.list()}" optionKey="id" value="${dealerProductInstance?.product?.id}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'unit1', 'error')} ">
	<label for="unit1">
		<g:message code="dealerProduct.unit1.label" default="Unit1" />
	</label>
	<g:textField name="unit1" maxlength="8" value="${fieldValue(bean: dealerProductInstance, field: 'unit1')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'unit2', 'error')} ">
	<label for="unit2">
		<g:message code="dealerProduct.unit2.label" default="Unit2" />
	</label>
	<g:textField name="unit2" maxlength="8" value="${fieldValue(bean: dealerProductInstance, field: 'unit2')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'currency', 'error')} required">
	<label for="currency">
		<g:message code="dealerProduct.currency.label" default="Currency" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="currency" maxlength="8" value="${fieldValue(bean: dealerProductInstance, field: 'currency')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="dealerProduct.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="price" value="${fieldValue(bean: dealerProductInstance, field: 'price')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'componentPricing', 'error')} ">
	<label for="componentPricing">
		<g:message code="dealerProduct.componentPricing.label" default="Component Pricing" />
	</label>
	<g:checkBox name="componentPricing" value="${dealerProductInstance?.componentPricing}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'approvalDate', 'error')} required">
	<label for="approvalDate">
		<g:message code="dealerProduct.approvalDate.label" default="Approval Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:jqDatePicker name="approvalDate" value="${dealerProductInstance?.approvalDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'lastTransactionDate', 'error')} ">
	<label for="lastTransactionDate">
		<g:message code="dealerProduct.lastTransactionDate.label" default="Last Transaction Date" />
	</label>
	<g:jqDatePicker name="lastTransactionDate" value="${dealerProductInstance?.lastTransactionDate}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'isIncludeTax', 'error')} ">
	<label for="isIncludeTax">
		<g:message code="dealerProduct.isIncludeTax.label" default="Is Include Tax" />
	</label>
	<g:checkBox name="isIncludeTax" value="${dealerProductInstance?.isIncludeTax}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'firstTransactionDate', 'error')} ">
	<label for="firstTransactionDate">
		<g:message code="dealerProduct.firstTransactionDate.label" default="First Transaction Date" />
	</label>
	<g:jqDatePicker name="firstTransactionDate" value="${dealerProductInstance?.firstTransactionDate}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'commissionOfUnit', 'error')} required">
	<label for="commissionOfUnit">
		<g:message code="dealerProduct.commissionOfUnit.label" default="Commission Of Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="commissionOfUnit" value="${fieldValue(bean: dealerProductInstance, field: 'commissionOfUnit')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'commissionPercent', 'error')} required">
	<label for="commissionPercent">
		<g:message code="dealerProduct.commissionPercent.label" default="Commission Percent" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="commissionPercent" value="${fieldValue(bean: dealerProductInstance, field: 'commissionPercent')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'beginDate', 'error')} required">
	<label for="beginDate">
		<g:message code="dealerProduct.beginDate.label" default="Begin Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:jqDatePicker name="beginDate" value="${dealerProductInstance?.beginDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'closeDate', 'error')} ">
	<label for="closeDate">
		<g:message code="dealerProduct.closeDate.label" default="Close Date" />
	</label>
	<g:jqDatePicker name="closeDate" value="${dealerProductInstance?.closeDate}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="dealerProduct.description.label" default="Description" />
	</label>
	<g:textArea name="description" rows="5" cols="40" value="${fieldValue(bean: dealerProductInstance, field: 'description')}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dealerProductInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="dealerProduct.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="owner.id" from="${org.leaf.eos2.shiro.User.list()}" optionKey="id" value="${dealerProductInstance?.owner?.id}"  />
</div>

<script type="text/javascript">
$(document).ready(function() {
	//datepicker  
	$('[datepicker="datepicker"]').each(function(){
		if(!$(this).attr('id')){
			$(this).datepicker({
				  dateFormat: 'yy-mm-dd'
				, onClose: function(dateText, inst) {
					$('[name="'+$(this).attr('target') + '_year"]').val(inst.selectedYear)
					$('[name="'+$(this).attr('target') + '_month"]').val(inst.selectedMonth + 1)
					$('[name="'+$(this).attr('target') + '_day"]').val(inst.selectedDay)
				}
			}).attr("readonly", true)
			//
		}
    });
});
</script>

