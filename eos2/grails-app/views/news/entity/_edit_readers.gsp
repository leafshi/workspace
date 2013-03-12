<div>
	<g:radioGroup name="selectAllAndNone" values="['All', 'None']" value="None" labels="['-全部选中 -', '-全部取消-']">
		<g:message code="${it.label}" />: ${it.radio}
	</g:radioGroup>
</div>
<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
	<span class="entity_readers">
		<!--check box-->
		<g:if test="${readerInstance?.visible == true }">
			<g:checkBox name="readers[${i}].visible" value="${true}" />
		</g:if>
		<g:else>
			<g:checkBox name="readers[${i}].visible" value="${false}" />
		</g:else>
		<!--label-->
		<label for="readers[${i}].visible">
			${fieldValue(bean: readerInstance, field: "reader")}
		</label>

		<!--reader id-->
		<g:hiddenField name="readers[${i}].reader.id" value="${readerInstance?.reader?.id}"/>
	</span>
</g:each>
<script type="text/javascript">
$(document).ready(function() {
	$("input[name='selectAllAndNone']").click(function(){
		var selected_value = $(this).val();
		if(selected_value == 'All'){
			$(".entity_readers > input[type='checkbox']").attr('checked', true);
		}else{
			$(".entity_readers > input[type='checkbox']").attr('checked', false);
		}
	})
});
</script>