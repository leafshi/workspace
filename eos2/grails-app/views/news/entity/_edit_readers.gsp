<shiro:hasPermission permission="entity:readers">

<g:set var="visibleAll" value="${true}" />
<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
	<g:if test="${readerInstance.visible != true}">
		<g:set var="visibleAll" value="${false}" />
	</g:if>
</g:each>


<table>
	<thead>
		<tr>
			<th><g:message code="reader.reader.label" default="Reader" /></th>
			<th><g:message code="reader.department.label" default="Department" /></th>
			<th><g:message code="reader.dealer.label" default="Dealer" /></th>
			<th>
				<g:message code="reader.visible.label" default="Visible" />
				<g:checkBox name="visibleAll" value="${visibleAll}" />
			</th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
		<g:set var="style" value="${readerInstance?.visible ? 'color:green':'color:red'}" />
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td style="${style}">
				${fieldValue(bean: readerInstance, field: "reader")}
				<g:hiddenField name="readers[${i}].reader.id" value="${readerInstance?.reader?.id}" />
			</td>
			<td style="${style}">
				<g:include controller="entityExtend" action="userDepartmentName" id="${readerInstance?.reader?.id}" />
			</td>
			<td style="${style}">
				<g:include controller="entityExtend" action="userDealerName" id="${readerInstance?.reader?.id}" />
			</td>
			<td style="${style}">
				<g:checkBox name="readers[${i}].visible" value="${readerInstance?.visible}" />
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
<script type="text/javascript">
$(document).ready(function(){
	$("input[type='checkBox'][name^='readers']").click(function(){
	
		if($(this).attr('checked') ){
			$(this).parent('td').parent('tr').children('td').css('color', 'green')
		}else{
			$(this).parent('td').parent('tr').children('td').css('color','red')
		}
	})
	
	$("input[type='checkBox'][name='visibleAll']").click(function(){
		if($(this).attr('checked')){
			$("input[type='checkBox'][name^='readers']").each(function(){
				$(this).parent('td').parent('tr').children('td').css('color', 'green')
				$(this).attr('checked', true)
			})
			
		}else{
			$("input[type='checkBox'][name^='readers']").each(function(){
				$(this).parent('td').parent('tr').children('td').css('color', 'red')
				$(this).attr('checked', false)
			})
		}
	})
})
</script>
</shiro:hasPermission>