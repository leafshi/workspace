<%@ page import="org.leaf.eos2.b2b.Product" %>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'serialNumber', 'error')} required">
	<label for="serialNumber">
		<g:message code="product.serialNumber.label" default="Serial Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="serialNumber" maxlength="20" required="" value="${productInstance?.serialNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="product.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="60" required="" value="${productInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'standard', 'error')} required">
	<label for="standard">
		<g:message code="product.standard.label" default="Standard" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="standard" maxlength="60" required="" value="${productInstance?.standard}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="product.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="unit" maxlength="4" required="" value="${productInstance?.unit}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="product.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: productInstance, field: 'price')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'isIncludeTax', 'error')} ">
	<label for="isIncludeTax">
		<g:message code="product.isIncludeTax.label" default="Is Include Tax" />
	</label>
	<g:checkBox name="isIncludeTax" value="${productInstance?.isIncludeTax}" />
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'isActive', 'error')} ">
	<label for="isActive">
		<g:message code="product.isActive.label" default="Is Active" />
	</label>
	<g:checkBox name="isActive" value="${productInstance?.isActive}" />
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'categories', 'error')} ">
	<label for="categories">
		<g:message code="product.categories.label" default="Categories" />
	</label>
	<ul class="one-to-many">
		<g:each in="${productInstance?.categories?}" var="c">
    		<li><g:link controller="productCategory" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
		</g:each>
		<li class="add">
			<g:link controller="productCategory" action="create" params="['product.id': productInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'productCategory.label', default: 'ProductCategory')])}</g:link>
		</li>
	</ul>
</div>
