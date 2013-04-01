<%@ page import="org.leaf.eos2.news.Reader" %>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'entity', 'error')} required">
	<g:hiddenField name="entity.id" value="${readerInstance?.entity?.id}" />
	<g:fieldValue bean="${readerInstance}" field="entity" />
</div>

<div class="fieldcontain ${hasErrors(bean: readerInstance, field: 'reader', 'error')} required">
	<div id="searchUser" contenteditable="true" class="searchDiv">${readerInstance?.reader}</div>
	<div id="searchUserDepartment"></div>
	<div id="searchUserDealer"></div>
	<g:hiddenField name="reader.id" value="${readerInstance?.reader?.id}"  />
	<g:hiddenField name="visible" value="${true}"  />
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
			$("#reader\\.id").val(ui.item.id);
			$("#searchUserDepartment").html(ui.item.department);
			$("#searchUserDealer").html(ui.item.dealer);
        }
    }).click(function () {
        $(this).autocomplete('search', $(this).html());
    });
}

$(document).ready(function(){
	initSearchUser();
	//定义错误处理
	$(document).ajaxError(function(event, request, settings) {
	    $.unblockUI();
        alert("未知错误，请联系管理员");
    });
})
</script>

