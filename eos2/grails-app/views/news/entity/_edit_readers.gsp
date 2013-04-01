<table>
	<thead>
		<tr>
			<th><g:message code="reader.reader.label" default="Reader" /></th>
			<th><g:message code="reader.department.label" default="Department" /></th>
			<th><g:message code="reader.dealer.label" default="Dealer" /></th>
			<th>
				<g:message code="reader.visible.label" default="Visible" />
			</th>
		</tr>
	</thead>
	<tbody>
	<g:each in="${entityInstance?.readers?.sort{ it?.reader?.username}}" status="i" var="readerInstance">
		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			<td>
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
<div id="search_user_dialog" title="Search User" style="display:none;">
	<table>
		<thead>
			<tr>
				<th><g:message code="reader.reader.label" /></th>
				<th><g:message code="reader.department.label" /></th>
				<th><g:message code="reader.dealer.label" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div id="searchUser" contenteditable="true" class="searchDiv" ></div>
					<div id="searchUserId" style="display:none;">
				</td>
				<td>
					<span id="searchUserDepartment"></span>
				</td>
				<td>
					<span id="searchUserDealer"></span>
				</td>
			</tr>
		</tbody>
	</table>
</div>


<script type="text/javascript">

function initSearchUser(){
	$("#searchUser").autocomplete({
        minLength: 0,
        source: function(request, response) {
            $.ajax({
                url: "${createLink(controller:'entityExtend', action: 'searchUser')}",
                data: { term: request.term },
                dataType: "json",
                type: "POST",
                success: function( data ) {
                    response( $.map( data, function( item ) {
                        return {
                          id : item[0]
                    	, value: $.trim(item[1])
                		, label: $.trim(item[1])+'|'+$.trim(item[2]) + '|' + $.trim(item[3])
                		, department : $.trim(item[2])
                		, dealer :  $.trim(item[3])
					}}));
                }
            });
        },
        select: function( event, ui ) {
			$("#searchUserId").html(ui.item.id);
			$("#searchUserDepartment").html(ui.item.department);
			$("#searchUserDealer").html(ui.item.dealer);
        }
    }).click(function () {
        $(this).autocomplete('search', $(this).html());
    });;

}
function initSearchDialog(){
	$("#search_user_dialog").dialog({modal: true, width:'90%', buttons: {
		"${message(code:'salesOrder.page.addDetail.dialog.confirm', default:'Confirm!')}": function() {
			if($("#searchProductId").val() == ''){
				alert("请选择一个用户");
				$("#searchUser").autocomplete('search', $("#searchUser").html()).focus();
			}else{
				//addDetail();
				$( this ).dialog( "close" );
			}
		},
		"${message(code:'salesOrder.page.delete.dialog.cancel', default:'Cancel!')}": function() {
			$( this ).dialog( "close" );
		}
	}});
	event.returnValue = null;
}
$(document).ready(function(){

	//绑定ESC按键
	initSearchUser();
	//初始化搜索窗口
	$(document).bind('keydown', 'esc',function (event){
		initSearchDialog();
	});
	
	//定义错误处理
	$(document).ajaxError(function(event, request, settings) {
	    $.unblockUI();
        alert("未知错误，请联系管理员");
    });
})
</script>