<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="global.app.name"/>
		</title>
		<style type="text/css">
		h1 {
			color: #48802c;
			font-weight: normal;
			font-size: 20px;
			margin: .8em 0 .3em 0;
		}

		table {
			border: 1px solid #ccc;
			font-size: 14px;
		}

		tr {
			border: 0;
		}

		td, th {
			padding: 5px 6px;
			text-align: left;
			vertical-align: top;
		}

		</style>
	</head>
	<body>
		<div id="pageBody"> 
    		<g:render template="/sys/buildInfo/buildInfo" plugin="buildInfo"/>

    		<g:render template="/sys/buildInfo/runtimeStatus" plugin="buildInfo"/>

    		<g:render template="/sys/buildInfo/installedPlugins" plugin="buildInfo"/>
		</div>
	</body>
</html>