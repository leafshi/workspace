<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="global.app.name"/>
		</title>
	</head>
	<body>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<div class="version">
			<ul>
				<h1>版本说明：</h1>
				<li>
					<h2>0.1.4 [2013-03-25]</h2>
					<ul>
						<li>1 新增222单别</li>
						<li>2 交付周期跳过周末
							<ul>2.1 如果下单日是周末，则从下周一开始计算</ul>
							<ul>2.2 从开始到结束，中间包括几个周六，周日，则延续几天</ul>
							<ul>2.3 如果结束日期为周末，则交期为下周一</ul>
						</li>
					</ul>
				</li>
				<li>
					<h2>0.1.3 [2013-01-26] </h2>
					<ul>
						<li>经常漫长的开发和测试，新版终于上线了：）</li>
						<li>1 全新的系统架构，运行更快</li>
						<li>2 全新的权限控制方式及安全模块</li>
						<li>3 对浏览器的支持，IE, FF, Chrome, Safari...</li>
						<li>4 记录登录历史，包括操作系统，浏览器，版本，IP</li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>