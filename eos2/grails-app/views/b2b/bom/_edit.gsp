<%@ page import="org.leaf.eos2.b2b.Bom" %>

<div class="fieldcontain ${hasErrors(bean: bomInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="bom.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${org.leaf.eos2.b2b.Product.list()}" optionKey="id" required="" value="${bomInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bomInstance, field: 'bomDetails', 'error')} ">
	<label for="bomDetails">
		<g:message code="bom.bomDetails.label" default="Bom Details" />
	</label>
	
	<ul class="one-to-many">
		<g:each in="${bomInstance?.bomDetails?}" var="b">
    	<li><g:link controller="bomDetail" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
	</g:each>
		
		<li class="add">
			<g:link controller="bomDetail" action="create" params="['bom.id': bomInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bomDetail.label', default: 'BomDetail')])}</g:link>
		</li>
	</ul>

</div>
