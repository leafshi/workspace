package org.leaf.eos2.shiro

import org.geeks.browserdetection.UserAgentIdentService

class LogLoginHistoryService {

	def userAgentIdentService

    def log(sourceIP, username, isSucceed) {
		try{
			def loginHistory = new LoginHistory(
				username : username
				, loginTime : new Date()
				, sourceIP : sourceIP
				, loginType : 'Application'
				, isSucceed : isSucceed
				, browser : "${userAgentIdentService.getBrowser()}-${userAgentIdentService.getBrowserVersion()}"
				, platform : "${userAgentIdentService.getPlatform()}"
				, operatingSystem : "${userAgentIdentService.getOperatingSystem()}"
			).save(flush:true);
		}catch(e){
			log.error("log login history error, error="+ e)
		}
    }
}
