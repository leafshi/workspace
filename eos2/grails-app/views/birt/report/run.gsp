<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'birt.reportParameters.label', default: 'Report Parameters')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'ui-lightness/jquery-ui-1.9.1.custom.css')}" type="text/css">
    <g:javascript src="jquery-ui-1.9.1.custom.min.js"/>
	<script type="text/javascript" language="javascript">
	// <![CDATA[
		function displayWait(){
			$('#form_pane').hide();
			$('#wait_indicator').show();
			return true;
		}
	// ]]>
	</script>
</head>
<body>
<div class="body">
	<div id="wait_indicator" style="display: none;text-align:center;padding-top:50px;">
		<b>${message( code:"birt.generating", default : "Generating Report, please wait...")}</b>
		<br/><br/>
		<img src="${resource(dir:'images',file:'spinner.gif')}" alt="Working..." />
	</div>
	<div id="form_pane">
		<h1><g:message code="default.edit.label" args="[entityName]" />: ${title}</h1>
		<g:if test="${message}">
			<div class="message">${message}</div>
		</g:if>
		<g:if test="${error }">
			<div class="errors">${error}</div>
		</g:if>
		<g:form action="view" method="post" id="${id}" onSubmit="displayWait()">
			<div class="dialog">
				<g:render template="/birt/report/run" />
			</div>
			<div class="buttons">
				<g:submitButton class="save" name="run" value="${message( code:"birt.view.run")}" />
			</div>
		</g:form>
	</div>
</body>
</html>
