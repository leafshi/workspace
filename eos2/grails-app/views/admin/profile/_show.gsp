<ol class="property-list profile">

	<g:if test="${profileInstance?.name}">
	<li class="fieldcontain">
		<span id="name-label" class="property-label"><g:message code="profile.name.label" default="Name" /></span>
		<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${profileInstance}" field="name"/></span>
	</li>
	</g:if>

	<g:if test="${profileInstance?.dateCreated}">
	<li class="fieldcontain">
		<span id="dateCreated-label" class="property-label"><g:message code="profile.dateCreated.label" default="Date Created" /></span>
		<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${profileInstance?.dateCreated}" formatName="custom.datetime.format"/></span>
	</li>
	</g:if>

	<g:if test="${profileInstance?.lastUpdated}">
	<li class="fieldcontain">
		<span id="lastUpdated-label" class="property-label"><g:message code="profile.lastUpdated.label" default="Last Updated" /></span>
		<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${profileInstance?.lastUpdated}" formatName="custom.datetime.format" /></span>
	</li>
	</g:if>

</ol>
