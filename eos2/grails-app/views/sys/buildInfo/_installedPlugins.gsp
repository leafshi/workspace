<h1>Installed Plugins</h1>

<ol class="property-list buildInfo">
    <g:set var="pluginManager"
            value="${applicationContext.getBean('pluginManager')}"></g:set>

    <g:each status="i" var="plugin" in="${pluginManager.allPlugins.sort({it.name.toUpperCase()})}">
    <li class="fieldcontain">
		<span class="property-label"><g:message code="${plugin.name}"/></span>
		<span class="property-value">${plugin.version}</span>
	</li>
    </g:each>
</ol>