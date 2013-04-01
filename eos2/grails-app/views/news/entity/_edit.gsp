<%@ page import="org.leaf.eos2.news.Entity" %>
<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'level', 'error')} required">
	<g:select name="level" value="${fieldValue(bean: entityInstance, field: 'level')}" from="${['紧急', '重要', '普通']}" style="width:513px;margin-left:200px;"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'title', 'error')} required">
	<g:textField name="title" maxlength="100" style="width:500px;margin-left:200px;" value="${fieldValue(bean: entityInstance, field: 'title')}" placeholder="${message(code:'entity.title.label', default:'Title')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'content', 'error')} required">
	<div name="contentDiv" contenteditable="true" class="textArea" >${entityInstance?.content}</div>
	<g:hiddenField name="content" value="${fieldValue(bean: entityInstance, field: 'content')}" />
</div>

<!--created by-->
<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'createdBy', 'error')} required">
	<g:hiddenField name="createdBy.id" value="${entityInstance?.createdBy?.id}" />
</div>

<!--last modified by-->
<div class="fieldcontain ${hasErrors(bean: entityInstance, field: 'lastModifiedBy', 'error')} required">
	<g:hiddenField name="lastModifiedBy.id" value="${entityInstance?.lastModifiedBy?.id}" />
</div>

<script>
//用DIV代替输入框
$(document).ready(function(){
	$("div[name='contentDiv']").height('500px');
});
$("div[name='contentDiv']").keyup(function(){
    $("input[name='content']").val( $(this).html().replace( /<br>/g, '\n' ) );
});
//提交时，填定内容
$("form").submit(function() {
	$("input[name='content']").val($("div[name='contentDiv']").html());
	return true;
});
</script>