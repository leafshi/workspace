<g:each in="${entityInstance.readers.sort{ it?.reader?.username}}" status="i" var="readerInstance">
	<span class="entity_readers">
		<!--check box-->
		<g:if test="${readerInstance?.visible == true }">
			<span style="color:green;">${fieldValue(bean: readerInstance, field: "reader")}</span>
		</g:if>
		<g:else>
			<span style="color:red; text-decoration: line-through;">${fieldValue(bean: readerInstance, field: "reader")}</span>
		</g:else>
	</span>
</g:each>
