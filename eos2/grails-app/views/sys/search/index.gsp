<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="global.app.name"/>
			~
			Search
		</title>
		<g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
		<g:javascript src="JQueryBlockUI.js"/>

		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
		<script type="text/javascript">
		$(document).ready(function(){
			//隐藏结果
			$("#list-search").hide()
			//初始化搜索选项
			$( "#radio" ).buttonset();
			//初始化搜索按钮
			$("#searchButton").click(function(){
				$("#list-search").hide()
				$.blockUI();
				$.ajax({
					url: "${createLink(controller:'search', action: 'search')}",
					data: { method : $("#radio :radio:checked").attr('id'), term: $("#search").val() },
					dataType: "html",
					type: "POST",
					success: function(data){
						$.unblockUI();
						$("#list-search").html(data).show();
						//如果返回值不为空，则清空搜索条件
						if($("#list-search > table > tbody > tr").size() > 0){
							$("#search").val('')
						}
					},
					error : function(error) { 
						$.unblockUI();
					}
				});
			});
		});

		</script>
	</head>
	<body>
		<!--begin search-->
		<div>
			<br/>
			<div id="radio">
				<input type="radio" id="searchProduct" name="radio" checked="checked"/><label for="searchProduct"><g:message code="product.label" /></label>
				<input type="radio" id="searchContract" name="radio"  /><label for="searchContract"><g:message code="contract.label" /></label>
				<input type="radio" id="searchSalesOrder" name="radio" /><label for="searchSalesOrder"><g:message code="salesOrder.label" /></label>
			</div>
			<br/>
			<div>
				<g:textField name="search" value="" class="search"/>
				<a href="#" id="searchButton"><g:message code="default.button.search" /></a>
			</div>
		</div>
		<!--end search-->
		
		<!--begin result-->
		<div id="list-search" class="content scaffold-list" role="main"></div>
		<!--end result-->
    	<br/>
	</body>
</html>