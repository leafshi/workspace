<%@ page import="org.leaf.eos2.b2b.Category" %>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'recordType', 'error')} required">
	<label for="recordType">
		<g:message code="category.recordType.label" default="Record Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recordType" name="recordType.id" from="${org.leaf.eos2.admin.RecordType.list()}" optionKey="id" required="" value="${categoryInstance?.recordType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="category.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="6" required="" value="${categoryInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" required="" value="${categoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'discount', 'error')} required">
	<label for="discount">
		<g:message code="category.discount.label" default="Discount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="discount" value="${fieldValue(bean: categoryInstance, field: 'discount')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'productionCycle', 'error')} required">
	<label for="productionCycle">
		<g:message code="category.productionCycle.label" default="Production Cycle" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="productionCycle" type="number" min="0" value="${categoryInstance.productionCycle}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'transportCycle', 'error')} required">
	<label for="transportCycle">
		<g:message code="category.transportCycle.label" default="Transport Cycle" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="transportCycle" type="number" min="0" value="${categoryInstance.transportCycle}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'hasBom', 'error')} ">
	<label for="hasBom">
		<g:message code="category.hasBom.label" default="Has Bom" />
	</label>
	<g:checkBox name="hasBom" value="${categoryInstance?.hasBom}" />
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'isAllowSpecialDiscount', 'error')} ">
	<label for="isAllowSpecialDiscount">
		<g:message code="category.isAllowSpecialDiscount.label" default="Is Allow Special Discount" />
	</label>
	<g:checkBox name="isAllowSpecialDiscount" value="${categoryInstance?.isAllowSpecialDiscount}" />
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'isAllowZeroPrice', 'error')} ">
	<label for="isAllowZeroPrice">
		<g:message code="category.isAllowZeroPrice.label" default="Is Allow Zero Price" />
	</label>
	<g:checkBox name="isAllowZeroPrice" value="${categoryInstance?.isAllowZeroPrice}" />
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="category.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${categoryInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'deliveryCycle', 'error')} required">
	<label for="deliveryCycle">
		<g:message code="category.deliveryCycle.label" default="Delivery Cycle" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="deliveryCycle" type="number" value="${categoryInstance.deliveryCycle}" required=""/>
</div>
