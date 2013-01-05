<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="global.app.name"/>
			~
			build info
		</title>
	</head>
	<body>
		<div id="pageBody"> 
    		<g:render template="/sys/buildInfo/buildInfo" plugin="buildInfo"/>

    		<g:render template="/sys/buildInfo/runtimeStatus" plugin="buildInfo"/>

    		<g:render template="/sys/buildInfo/installedPlugins" plugin="buildInfo"/>
		</div>
	</body>
</html>