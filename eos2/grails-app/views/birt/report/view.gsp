<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Report ${title}</title>
	<meta name="layout" content="birt" />
	<g:javascript src="jquery.fixedtableheader-1-0-2.min.js"></g:javascript>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#__bookmark_1").fixedtableheader(); 
		});
	</script>
</head>
<body>
<div class="body">
	<g:form action="run" name="paramForm">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="rerun" value="1">
		<g:each var="k" in="${reportParams.keySet() }">
			<input type="hidden" name="${k}" value="${reportParams[k]}">
		</g:each>
	</g:form>

	<% out << reportContent %>
</div>
</body>
</html>
