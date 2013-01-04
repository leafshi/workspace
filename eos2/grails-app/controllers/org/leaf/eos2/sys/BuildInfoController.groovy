package org.leaf.eos2.sys

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin

class BuildInfoController {

    static final List buildInfoProperties = [
    	'build.date'
    	, 'scm.version'
    	, 'environment.BUILD_NUMBER'
    	, 'environment.BUILD_ID'
    	, 'environment.BUILD_TAG'
    	, 'environment.GIT_BRANCH'
    	, 'environment.GIT_COMMIT'
    ] 

    def index = { 
        def buildInfoConfig = ConfigurationHolder.config?.buildInfo
        
        log.info("buildInfoConfig=${buildInfoConfig}")
        
        def customProperties = buildInfoProperties
        
        if (buildInfoConfig?.properties?.exclude){
            customProperties -= buildInfoConfig.properties.exclude
        }
        
        if (buildInfoConfig?.properties?.add){
            customProperties += buildInfoConfig.properties.add
        }
		
        Map model = [buildInfoProperties: customProperties]
        render(view:'/sys/buildInfo/index', model:model)
    }
}
