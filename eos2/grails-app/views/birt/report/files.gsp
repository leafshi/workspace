<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="birt" />
    <title>Upload File</title>
</head>
<body>
<div class="body">
	<h1>Available Files</h1>
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
       <div class="list">
		<table>
 				<thead>
   				<tr>
     					<th>Filename</th>
  					</tr>
			</thead>
 				<tbody>
   				<g:each var="file" in="${fileList}">
					<tr>
						<td><a href="${resource(dir:'Reports', 'file':file)}">${file}</a></td>
					</tr>
				</g:each>
 				</tbody>
		</table>
	</div>
	<h1>Upload File</h1>
	<div class="dialog" id="form_pane">
 			<g:form action="upload" enctype="multipart/form-data">
			<input type="file" name="reportFile" />
			<br />
			<div class="buttons">
 					<span class="button">
   					<input class="files" type="submit" value="Upload" />
 					</span>
			</div>
		</g:form>
	</div>
</div>
</body>
</html>