<!DOCTYPE html>
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
		<div class="nav" role="navigation">
			<ul>
				<li>
					<a class="home" href="${createLink(uri: '/')}">
						<g:message code="default.home.label"/>
					</a>
				</li>
				<li>
					<a class="list" href="${createLink(controller:'syncJob')}">
						<g:message code="default.list.label" args="['SyncJob']" />
					</a>
				</li>
				<li>
					<a class="list" href="${createLink(controller:'syncJobSchedule')}">
						<g:message code="default.list.label" args="['SyncJobSchedule']" />
					</a>
				</li>
				<li>
					<a class="list" href="${createLink(controller:'syncJobHistory')}">
						<g:message code="default.list.label" args="['SyncJobHistory']" />
					</a>
				</li>
			</ul>
		</div>

	</body>
</html>
