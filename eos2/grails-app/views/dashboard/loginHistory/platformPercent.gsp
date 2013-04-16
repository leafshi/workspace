<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="global.app.name"/>
			~
			Dashboard
			~
			Platform Percent
		</title>
		<gvisualization:apiImport/>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<div id="piechart"></div>

		<gvisualization:pieCoreChart 
			dynamicLoading="${true}" 
			elementId="piechart" 
			title="${title}" 
			width="${width}" 
			height="${height}" 
			columns="${columns}" 
			data="${data}"/>
	</body>
</html>