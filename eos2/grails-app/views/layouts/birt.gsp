<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="eos2"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'birt.css')}" type="text/css">
		<g:javascript src="jquery-1.8.2.min.js"/>
		
		<g:layoutHead/>
	</head>
	<body>
		<div class="main">
			<!-- header -->
			<div class="header">
				<div class="blank_line"></div>
				<div class="global_title"><g:message code="global.app.name"/></div>
			</div>
			<!-- end header -->
			<shiro:authenticated>
			<g:render template="/admin/menu/menu" />
			</shiro:authenticated>
			<!-- content -->
			<g:layoutBody/>
		</div>
		<!-- end content -->
		<!-- footer -->
		<div class="footer">
			<div class="blank_line"></div>
		</div>
		<!-- end footer -->
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading..."/></div>
	</body>
</html>
