<script type="text/javascript">var search_product_dialog_object = null;//去空格String.prototype.trim=function(){    return this.replace(/(^\s*)|(\s*$)/g, "");}String.prototype.endWith=function(str){    if(str==null||str==""||this.length==0||str.length>this.length)        return false;            if(this.substring(this.length-str.length)==str)        return true;    else        return false;    return true;}String.prototype.startWith=function(str){    if(str==null||str==""||this.length==0||str.length>this.length)        return false;    if(this.substr(0,str.length)==str)        return true;    else        return false;    return true;}//只能输入数字function myKeypress(event){	var keyCode = event.keyCode||event.charCode	if( keyCode == 13 ){		$(this).flur();		event.preventDefault(); 		return false;	}else{		return (/[\d]/.test(String.fromCharCode(event.keyCode||event.charCode)));	}}//不允许复制粘贴function myPaste(){	return false;}//当数值发生变化时function myChange(){//0	//取行数量_旧	var old_parent_row_quantity = $(this).data("before");	var v = parseInt($.formatNumber($(this).spinner( "value" ), "##0.00"), 10);		v = isNaN(v) ? 0 : v;	this.value = $.formatNumber(v, "###,##0.00");	//设置旧值	$(this).data("before", v);//1	//取行数量_新	var new_parent_row_quantity = parseInt($.formatNumber(this.value, "##0.00"), 10);	    //如果新数量为NaN，则取原值        new_parent_row_quantity = isNaN(new_parent_row_quantity) ? old_parent_row_quantity : new_parent_row_quantity;	//set value	this.value = $.formatNumber(new_parent_row_quantity, "###,##0.00");//2	//总数量_旧	var old_total_quantity = $("#quantity").val();		old_total_quantity = parseInt($.formatNumber(old_total_quantity, "##0.00"), 10);//3	//总数量_新	var new_total_quantity = old_total_quantity.sub(old_parent_row_quantity).add(new_parent_row_quantity) 		$("#quantity").val($.formatNumber(new_total_quantity, "###,##0.00"));	$("span[name='quantity_span']").html($.formatNumber(new_total_quantity, "###,##0.00"));//4	//获取行	var parent_tr = $(this).closest('tr');	//获取行主附件定价标识号	var childs_tr_parent = parent_tr.attr("childs");	//	hasBom = false;    var sum_amount = 0, sum_full_amount = 0, sum_special_amount = 0;		$("tr[parent="+childs_tr_parent+"]").each(function(){        hasBom = true;		var coordinate = $(this).attr("coordinate").split(",");					    //get child price		var child_price = $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].price']").val();			child_price = $.formatNumber(child_price, "#0.000000");            child_price = parseFloat(child_price, 10);				//get child discount		var child_discount = $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].discount']").val();            child_discount = parseFloat(child_discount, 10);				//get child special discount		var child_special_discount = $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].specialDiscount']").val();            child_special_discount = parseFloat(child_special_discount, 10);		//get dosage		var dosage = $("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].dosage_span']").html();			dosage = dosage.replace(/\n/g, "")            dosage = parseFloat(dosage, 10)		        //get quota		var quota = $("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].quota_span']").html();			quota = quota.replace(/\n/g, "");            quota = parseFloat(quota, 10);				    //calc quantity        var child_quantity = new_parent_row_quantity.mul(dosage.div(quota))            child_quantity = $.formatNumber(child_quantity, "#0.000")            child_quantity = parseFloat(child_quantity, 10)		//set quantity		$("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].quantity']").val($.formatNumber(child_quantity, "###,##0.000"))        $("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].quantity_span']").html($.formatNumber(child_quantity, "###,##0.000"));		        //set full amount = child.quantity * child.price 面价金额        var child_full_amount = child_quantity.mul(child_price)            child_full_amount = $.formatNumber(child_full_amount, "#0.00")            child_full_amount = parseFloat(child_full_amount, 10)        sum_full_amount = sum_full_amount.add(child_full_amount);		//set child full amount		$("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].fullAmount_span']").html($.formatNumber(child_full_amount, "###,##0.00"))        $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].fullAmount']").val($.formatNumber(child_full_amount, "###,##0.00"))		//set amount = child.quantity * child.price * child.discount 标准金额        var child_amount = child_full_amount.mul(child_discount)            child_amount = $.formatNumber(child_amount, "#0.00")            child_amount = parseFloat(child_amount, 10)        sum_amount = sum_amount.add(child_amount);		//set child amount		$("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].amount_span']").html($.formatNumber( child_amount, "###,##0.00"))        $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].amount']").val($.formatNumber( child_amount, "###,##0.00"))		//特价金额		var child_special_amount  = child_amount.mul(child_special_discount)            child_special_amount = $.formatNumber(child_special_amount, "#0.00")            child_special_amount = parseFloat(child_special_amount, 10)				sum_special_amount = sum_special_amount.add(child_special_amount);		//set child special amount		$("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].specialAmount_span']").html($.formatNumber( child_special_amount, "###,##0.00"))        $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].specialAmount']").val($.formatNumber( child_special_amount, "###,##0.00"))				//set child final amount 最终金额		var child_final_amount = child_amount.sub(child_special_amount)            child_final_amount = $.formatNumber(child_final_amount, "#0.00")            child_final_amount = parseFloat(child_final_amount, 10)				$("span[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].finalAmount_span']").html($.formatNumber( child_final_amount, "###,##0.00"))        $("input[name='salesOrderDetails["+coordinate[0]+"].salesOrderDetailDetails["+coordinate[1]+"].finalAmount']").val($.formatNumber( child_final_amount, "###,##0.00"))	});		var parent_coordinate = $(parent_tr).attr("coordinate").split(",");		//get parent price	var parent_price = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].price']").val()        parent_price = $.formatNumber(parent_price, "#0.000000")        parent_price = parseFloat(parent_price, 10)	//disocunt	var parent_discount = 0.0000;	var parent_special_discount = 0.00;	//new row amount	var new_row_amount = 0.00;    //new row full amount    var new_row_full_amount = 0.00;    //var new row special amount    var new_row_special_amount = 0.00;    if(hasBom==true){        //calc parent discount        /*        parent_discount = sum_amount.div(sum_full_amount)        parent_discount = isNaN(parent_discount)? 0 : parent_discount        parent_discount = $.formatNumber(parent_discount, "#0.0000")        parent_discount = parseFloat(parent_discount, 10)        */        parent_discount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].discount']").val()        parent_discount = $.formatNumber(parent_discount, "#0.0000")        parent_discount = parseFloat(parent_discount, 10)        //new amount        new_row_amount = sum_amount        new_row_amount = $.formatNumber(new_row_amount, "#0.00")        new_row_amount = parseFloat(new_row_amount, 10)        //new full amount        new_row_full_amount = sum_full_amount        new_row_full_amount = $.formatNumber(new_row_full_amount, "#0.00")        new_row_full_amount = parseFloat(new_row_full_amount, 10)                //new special amount        new_row_special_amount = sum_special_amount        new_row_special_amount = $.formatNumber(new_row_special_amount, "#0.00")        new_row_special_amount = parseFloat(new_row_special_amount, 10)   }else{        //calc parent discount        parent_discount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].discount']").val()        parent_discount = $.formatNumber(parent_discount, "#0.0000")        parent_discount = parseFloat(parent_discount, 10)                //get parent special discount        parent_special_discount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].specialDiscount']").val()        parent_special_discount = $.formatNumber(parent_special_discount, "#0.0000")        parent_special_discount = parseFloat(parent_special_discount, 10)        //new full amount,面价        new_row_full_amount = parent_price.mul(new_parent_row_quantity)        new_row_full_amount = $.formatNumber(new_row_full_amount, "#0.00")        new_row_full_amount = parseFloat(new_row_full_amount, 10)        //new amount        new_row_amount = new_row_full_amount.mul(parent_discount)        new_row_amount = $.formatNumber(new_row_amount, "#0.00")        new_row_amount = parseFloat(new_row_amount, 10)                //new special amount        new_row_special_amount = new_row_amount * parent_special_discount        new_row_special_amount = $.formatNumber(new_row_special_amount, "#0.00")        new_row_special_amount = parseFloat(new_row_special_amount, 10)    }	    //set row discount	//$("span[name='salesOrderDetails["+parent_coordinate[0]+"].discount_span']").html($.formatNumber(parent_discount.mul(100), "0.00") + '%')	//$("input[name='salesOrderDetails["+parent_coordinate[0]+"].discount']").val($.formatNumber(parent_discount, "0.0000"))	    //calc final price    var parent_final_price = new_row_amount.div(new_parent_row_quantity)        parent_final_price = isNaN(parent_final_price)? 0 : parent_final_price        parent_final_price = $.formatNumber(parent_final_price, "#0.0000")        parent_final_price = parseFloat(parent_final_price, 10)	//set final price	//$("span[name='salesOrderDetails["+parent_coordinate[0]+"].finalPrice_span']").html($.formatNumber(parent_final_price, "0.000000"))	//$("input[name='salesOrderDetails["+parent_coordinate[0]+"].finalPrice']").val($.formatNumber(parent_final_price, "0.000000")) 	//parent row full amount		$("span[name='salesOrderDetails["+parent_coordinate[0]+"].fullAmount_span']").html($.formatNumber(new_row_full_amount, "###,##0.00"))		$("input[name='salesOrderDetails["+parent_coordinate[0]+"].fullAmount']").val($.formatNumber(new_row_full_amount, "###,##0.00"))    //parent row amount		//get old row amount		var old_row_amount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].amount']").val()			old_row_amount = $.formatNumber(old_row_amount, "##0.00")			old_row_amount = parseFloat(old_row_amount, 10)		//set new row amount		$("span[name='salesOrderDetails["+parent_coordinate[0]+"].amount_span']").html($.formatNumber(new_row_amount, "###,##0.00"))		$("input[name='salesOrderDetails["+parent_coordinate[0]+"].amount']").val($.formatNumber(new_row_amount, "###,##0.00"))    //parent row special amount		//get old row special amount		var old_row_special_amount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].specialAmount']").val()			old_row_special_amount = $.formatNumber(old_row_special_amount, "##0.00")			old_row_special_amount = parseFloat(old_row_special_amount, 10)		//set new row special amount		$("span[name='salesOrderDetails["+parent_coordinate[0]+"].specialAmount_span']").html($.formatNumber(new_row_special_amount, "###,##0.00"))		$("input[name='salesOrderDetails["+parent_coordinate[0]+"].specialAmount']").val($.formatNumber(new_row_special_amount, "###,##0.00"))		    //parent row final amount		//get old row amount		var old_row_final_amount = $("input[name='salesOrderDetails["+parent_coordinate[0]+"].finalAmount']").val()			old_row_final_amount = $.formatNumber(old_row_final_amount, "##0.00")			old_row_final_amount = parseFloat(old_row_final_amount, 10)		//set new row amount		$("span[name='salesOrderDetails["+parent_coordinate[0]+"].finalAmount_span']").html($.formatNumber(new_row_amount.sub(new_row_special_amount), "###,##0.00"))		$("input[name='salesOrderDetails["+parent_coordinate[0]+"].finalAmount']").val($.formatNumber(new_row_amount.sub(new_row_special_amount), "###,##0.00"))	//total amount		//get total amount		var old_total_amount = $("input[name='amount']").val()			old_total_amount = $.formatNumber(old_total_amount, "##0.00")			old_total_amount = parseFloat(old_total_amount, 10)		//calculate new total amount		var new_total_amount = old_total_amount.sub(old_row_amount).add(new_row_amount)		//set total amount		$("span[name='amount_span']").html($.formatNumber(new_total_amount, "###,##0.00"))		$("input[name='amount']").val($.formatNumber(new_total_amount, "###,##0.00"))			//total special amount		//get total amount		var old_total_special_amount = $("input[name='specialAmount']").val()			old_total_special_amount = $.formatNumber(old_total_special_amount, "##0.00")			old_total_special_amount = parseFloat(old_total_special_amount, 10)		//calculate new total special amount		var new_total_special_amount = old_total_special_amount.sub(old_row_special_amount).add(new_row_special_amount)		//set total specail amount		$("span[name='specialAmount_span']").html($.formatNumber(new_total_special_amount, "###,##0.00"))		$("input[name='specialAmount']").val($.formatNumber(new_total_special_amount, "###,##0.00"))}function init_industry_ajax_list(){    //industry list auto complete	$("#industryInput").autocomplete({        minLength: 0,        source: function(request, response) {            $.ajax({                url: "${createLink(controller:'salesOrder221Ajax', action: 'industryList')}",                data: { term: request.term },                dataType: "json",                type: "POST",                success: function( data ) {                    response( $.map( data, function( item ) {                        return {                              id : item[0]                           	, value: item[2]+'-'+item[1]                            , label: item[2]+'-'+item[1]                        }                    }));                }            });        },        select: function( event, ui ) {	        $("#industry\\.id").val(ui.item.id);        },         change : function( event, ui ) {        	$("#project").val('');        }    }).click(function () {        if(!$(this).attr("readonly")){            $(this).autocomplete('search', '');        }    });}function init_project_ajax_list(){	//project list auto complete	$("#project").autocomplete({		minLength: 0,		source: function(request, response) {			$.ajax({			    url: "${createLink(controller:'salesOrder221Ajax', action: 'projectList')}",			    data: { dealer: $("#dealer\\.id").val(), industry: $("#industry\\.id").val(), term: request.term },			    dataType: "json",			    type: "POST",			    success: function( data ) {			        response( $.map( data, function( item ) {			            return {			                  project : item[0]			            	, value: item[0]			                , label: item[0] + '|' + item[1]			            }			        }));			    }			});		},		select: function( event, ui ) {			$("#project").val(ui.item.project);		}	}).click(function () {        if(!$(this).attr("readonly")){            $(this).autocomplete('search', '');        }    });}function init_search_product(){	//search product	$("#searchProductNumber").autocomplete({        minLength: 0,        source: function(request, response) {            $.ajax({                url: "${createLink(controller:'salesOrder221Ajax', action: 'searchProduct')}",                data: { term: request.term },                dataType: "json",                type: "POST",                success: function( data ) {                    response( $.map( data, function( item ) {return {                        id : item[0]                    	, value: $.trim(item[2])                		, label: $.trim(item[2])+' | '+$.trim(item[1]) + ' | ' + $.trim(item[3]) + ' | ' + item[4]            			, serialNumber : item[2]        				, name : $.trim(item[1])    					, standard : $.trim(item[3])						, price : item[4]					}}));                }            });        },        select: function( event, ui ) {			$("#searchProductId").val(ui.item.id);			$("#searchProductNumber").val(ui.item.serialNumber)			$("#searchProductName").html(ui.item.name);			$("#searchProductStandard").html(ui.item.standard)			$("#searchProductPrice").html(ui.item.price)        },		focus: function(event, ui) { 			$("#searchProductName").html(ui.item.name);			$("#searchProductStandard").html(ui.item.standard)			$("#searchProductPrice").html(ui.item.price)		}    });    $("#searchProductNumber").keypress(function(event){        //判断是否是回车        var code = event.keyCode||event.charCode        if(code == 13){            event.returnValue = false;            addDetail();            return false;        }else{            event.returnValue = true;            return true;        }    });}function addDetail(){	$("#search_product_dialog").dialog("close");		if($("#searchProductId").val() == ''){		return false;	}    	$.blockUI();	$.ajax({        url: "${createLink(controller:'salesOrder221Ajax', action: 'addDetail')}",        data: {         	rowId : $("#salesOrder_detail_list >tbody >tr[childs]").length        	, productId: $("#searchProductId").val()         	, dealerId : $("#dealer\\.id").val()        	, industryId : $("#industry\\.id").val()        	, project : $("#project").val()        },        dataType: "html",        type: "POST",        success: addDetailCallback    });}function addDetailCallback(data){	$("#salesOrder_detail_list >tbody").append(data);	window.location.href="#salesOrderPageFooter";	init_page_javascript();	$.unblockUI();	}function delete_detail_rows(rows, coordinate){    //total quantity        //get row quantity        var quantity = $("input[name='salesOrderDetails["+coordinate[0]+"].quantity']").val()            quantity = $.formatNumber(quantity, "#0.00")            quantity = parseFloat(quantity, 10)        var old_total_quantity = $("input[name='quantity']").val()            old_total_quantity = $.formatNumber(old_total_quantity, "##0.00")            old_total_quantity = parseFloat(old_total_quantity, 10)        //calculate new total quantity	    var new_total_quantity = old_total_quantity.sub(quantity)        //set total amount	    $("span[name='quantity_span']").html($.formatNumber(new_total_quantity, "###,##0.00"))        $("input[name='quantity']").val($.formatNumber(new_total_quantity, "###,##0.00"))    //total amount        //get row amount        var amount = $("input[name='salesOrderDetails["+coordinate[0]+"].amount']").val()            amount = $.formatNumber(amount, "#0.00")            amount = parseFloat(amount, 10)        //get total amount        var old_total_amount = $("input[name='amount']").val()            old_total_amount = $.formatNumber(old_total_amount, "##0.00")            old_total_amount = parseFloat(old_total_amount, 10)        //calculate new total amount        var new_total_amount = old_total_amount.sub(amount)        //set total amount        $("span[name='amount_span']").html($.formatNumber(new_total_amount, "###,##0.00"))        $("input[name='amount']").val($.formatNumber(new_total_amount, "###,##0.00"))    //total special amount = old_total_special_amount - special_amount        //get row amount        var specialAmount = $("input[name='salesOrderDetails["+coordinate[0]+"].specialAmount']").val()            specialAmount = $.formatNumber(specialAmount, "#0.00")            specialAmount = parseFloat(specialAmount, 10)        //get total amount        var old_total_special_amount = $("input[name='specialAmount']").val()            old_total_special_amount = $.formatNumber(old_total_special_amount, "##0.00")            old_total_special_amount = parseFloat(old_total_special_amount, 10)        //calculate new total amount        var new_total_special_amount = old_total_special_amount.sub(specialAmount)        //set total amount        $("span[name='specialAmount_span']").html($.formatNumber(new_total_special_amount, "###,##0.00"))        $("input[name='specialAmount']").val($.formatNumber(new_total_special_amount, "###,##0.00"))    	$("tr[childs='"+rows+"']").remove();	$("tr[parent='"+rows+"']").remove();		refresh_table_after_delete_rows();}function refresh_table_after_delete_rows(){	var new_coordinate = -1	//get table all parent row		$("tr[childs]").each(function(){		new_coordinate ++;				var old_serialNumber = $(this).attr("childs");		var old_coordinate = $(this).attr("coordinate").split(',');		var className = ( new_coordinate % 2 == 0 )?'even':'odd';				//serial number		var new_serialNumber = (new_coordinate + 1 < 10)? "0" + (new_coordinate + 1) : "" + (new_coordinate + 1);				//tr		$(this).attr('childs', new_serialNumber).attr('coordinate', new_coordinate + ',-1').attr('id', "salesOrderDetails["+new_coordinate+"]").attr('class', className)        $('input[name^="salesOrderDetails['+old_coordinate[0]+']."]').each(function(){            var old_name = $(this).attr("name");            var new_name = old_name.replace("salesOrderDetails["+old_coordinate[0]+"].", "salesOrderDetails["+new_coordinate+"].");                        if($(this).attr("name")){                $(this).attr("name", new_name);            }            if($(this).attr("id")){                $(this).attr("id", new_name);            }            if($(this).attr("target")){                $(this).attr("target", new_name);            }        });        $("input[name='_salesOrderDetails["+old_coordinate[0]+"].openDetail']").attr('name', "_salesOrderDetails["+new_coordinate+"].openDetail");        $('span[name^="salesOrderDetails['+old_coordinate[0]+']."]').each(function(){            var old_name = $(this).attr("name");            var new_name = old_name.replace("salesOrderDetails["+old_coordinate[0]+"].", "salesOrderDetails["+new_coordinate+"].");                        if($(this).attr("name")){                $(this).attr("name", new_name);            }            if($(this).attr("id")){                $(this).attr("id", new_name);            }        });            $('a[name^="salesOrderDetails['+old_coordinate[0]+']."]').each(function(){            var old_name = $(this).attr("name");            var new_name = old_name.replace("salesOrderDetails["+old_coordinate[0]+"].", "salesOrderDetails["+new_coordinate+"].");                        if($(this).attr("name")){                $(this).attr("name", new_name);            }            if($(this).attr("href")){                $(this).attr("href", "#salesOrderDetails["+new_coordinate+"]");            }        });		//serial number		$("span[name='salesOrderDetails["+new_coordinate+"].serialNumber_span']").html(			new_serialNumber		);		$("input[name='salesOrderDetails["+new_coordinate+"].serialNumber']").val(			new_serialNumber		);				//target		var deliveryCycle = $("span[name='salesOrderDetails["+new_coordinate+"].deliveryCycle']").html().replace(/\r\n/g, "").trim();		$("input[target='salesOrderDetails["+old_coordinate[0]+"].deliveryLimitation']").attr(			'target', "salesOrderDetails["+new_coordinate+"].deliveryLimitation"		).datepicker("destroy").datepicker({			dateFormat: 'yy-mm-dd'			, minDate: new Date(new Date().getTime() + deliveryCycle * 24 * 60 * 60 * 1000)			, onClose: function(dateText, inst) {				$("[name='salesOrderDetails["+new_coordinate+"].deliveryLimitation_year']").val(inst.selectedYear)				$("[name='salesOrderDetails["+new_coordinate+"].deliveryLimitation_month']").val(inst.selectedMonth + 1)				$("[name='salesOrderDetails["+new_coordinate+"].deliveryLimitation_day']").val(inst.selectedDay)			}		});		//childs		$("tr[parent="+old_serialNumber+"]").each(function(){			var child_old_coordinate = $(this).attr("coordinate").split(',');			//tr			$(this).attr(				"parent", new_serialNumber			).attr(				'coordinate', new_coordinate + ',' + child_old_coordinate[1]			).attr(				'class', className			);						//serial number			var child_old_serialNumber = $("span[name='salesOrderDetails["+new_coordinate+"].salesOrderDetailDetails["+child_old_coordinate[1]+"].serialNumber_span']").html().replace(/\r\n/g, "").trim().split('-');			$("span[name='salesOrderDetails["+new_coordinate+"].salesOrderDetailDetails["+child_old_coordinate[1]+"].serialNumber_span']").html(				new_serialNumber + "-" + child_old_serialNumber[1]			);		});	});    if($("#salesOrder_detail_list >tbody >tr[childs]").length > 0) {        $('[name="industryInput"]').attr("readonly", true)        $('[name="project"]').attr("readonly", true)    }else{        $('[name="industryInput"]').attr("readonly", false)        $('[name="project"]').attr("readonly", false)    }}function reset_search_product(){	$("#searchProductId").val('');	$("#searchProductNumber").val('');	$("#searchProductName").html('');	$("#searchProductStandard").html('');	$("#searchProductPrice").html('');}function init_page_javascript(){    if($("#salesOrder_detail_list >tbody >tr[childs]").length > 0) {        $('[name="industryInput"]').attr("readonly", true)        $('[name="project"]').attr("readonly", true)    }	//init spinner	$( "input[spinner=Y][name$=quantity]" ).each(function(){		if (!$(this).data( "ui-spinner" ) ) {			var it = this;			$(it).keypress(myKeypress).bind('paste', myPaste).focus(function(){$(this).select();}).spinner({mouseWheel: 'false',change: myChange, min : 0}).css("width", "50px");			//init old value			$(it).data("before", $(it).spinner( "value" ));		}	})	//datepicker  	$('[datepicker="datepicker"]').each(function(){		if(!$(this).attr('id')){			var deliveryCycle = $(this).parent().next().children(":last-child").html().replace(/\r\n/g, "").trim();			$(this).datepicker({				  dateFormat: 'yy-mm-dd'				, minDate: new Date(new Date().getTime() + deliveryCycle * 24 * 60 * 60 * 1000)				, beforeShowDay : function (date){					if(date.getDay() > 0 &&  date.getDay() < 6){						return [true, '']					}else{						return [false, '']					}				} 				, onClose: function(dateText, inst) {					$('[name="'+$(this).attr('target') + '_year"]').val(inst.selectedYear)					$('[name="'+$(this).attr('target') + '_month"]').val(inst.selectedMonth + 1)					$('[name="'+$(this).attr('target') + '_day"]').val(inst.selectedDay)				}			}).attr("readonly", true)		}    });		//open detail	$("input[name$='.openDetail']").unbind('click').click(function(){        $("tr[parent='"+$(this).closest('tr').attr('childs')+"']").toggleClass( 'hide_detail');        return false;	});	//delete	$("a[delete=delete]").each(function(){		$(this).click(function(){			var rows = $(this).closest('tr').attr("childs");			var coordinate = $(this).closest('tr').attr("coordinate").split(",");			$( "#dialog-confirm" ).dialog({				resizable: false,				height:200,				modal: true,				position : {my : "left+300 bottom+300", at : "center", of : $(this)},				buttons: {					"Delete selected items": function() {						delete_detail_rows(rows, coordinate);						$( this ).dialog( "close" );					},					Cancel: function() {						$( this ).dialog( "close" );					}				}			});		});	});	//all input element keypress	$(":input").keypress(function(event){        var keyCode = event.keyCode||event.charCode            if(keyCode == 27){//ESC            reset_search_product();            $("#search_product_dialog").dialog({modal: true,width:'90%'});            event.preventDefault();             return false;         }	});}function before_submit(){	$(":submit[name=_action_update], :submit[name=create]").click(function(){		var result = true		$('[datepicker="datepicker"]').each(function(){			if(!isDate($(this).val())){				$(this).parent().addClass("errors")				$(this).focus()				result = false			}		})				$('input[spinner=Y][name$=quantity]').each(function(){		    var value = $(this).val();                value = $.formatNumber(value, "##0.00")                value = parseFloat(value, 10)		    			if(value <= 0){				$(this).parent().addClass("errors")				$(this).focus()				result = false			}		})				        //get total amount        var total_amount = $("input[name='amount']").val()            total_amount = $.formatNumber(total_amount, "##0.00")            total_amount = parseFloat(total_amount, 10)                    if(result == true && total_amount < 3000){            if(confirm('amount is less than 3k!')){                result = true;            }else{                result = false;            }        }        if(result == true){            $.blockUI();        }		return result	})}$(document).ready(function() {	before_submit();	init_industry_ajax_list();	init_project_ajax_list();	init_search_product();	init_page_javascript();	$(document).bind('keydown', 'esc',function (evt){		reset_search_product();		$("#search_product_dialog").dialog({modal: true,width:'90%'});		return false; 	});});</script>