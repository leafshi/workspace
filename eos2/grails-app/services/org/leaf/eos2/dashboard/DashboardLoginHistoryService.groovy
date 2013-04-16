package org.leaf.eos2.dashboard

import org.springframework.transaction.annotation.*

import org.leaf.eos2.shiro.LoginHistory

class DashboardLoginHistoryService {

	//浏览器使用百分比
	@Transactional(readOnly = true)
    def browserPercent () {
		def data = LoginHistory.withCriteria{
			projections{
				groupProperty("browser")
				count("id")
			}
		}
		return data;
    }

	//平台使用百分比
	@Transactional(readOnly = true)
    def platformPercent () {
		def data = LoginHistory.withCriteria{
			projections{
				groupProperty("platform")
				count("id")
			}
		}
		return data;
    }

}
