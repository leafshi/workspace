<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title>${action.name}</title>
</head>
<body>
<div class="body">
  <g:form method="post" >
	  <g:hiddenField name="objectName" value="${objectName}" />
	  <g:hiddenField name="objectId" value="${objectId}" />
	  <g:hiddenField name="historyId" value="${historyId}" />
	  <g:hiddenField name="actionId" value="${actionId}" />
	  <g:hiddenField name="ownerId" value="${ownerId}" />
    <g:hiddenField name="version" value="${version}" />
      ${action.name}
    <div class="dialog">
      <g:textArea name="description" value="" rows="5" cols="40"/>
    </div>
    <div class="buttons">
      <shiro:hasPermission permission="workflowApproval:approval">
        <span class="button">
          <g:actionSubmit class="save" controller="workflowApproval" action="approval" value="${message(code: 'default.button.approval', default: 'Approval')}" /></span>
      </shiro:hasPermission>
    </div>
  </g:form>
</div>
</body>
</html>
