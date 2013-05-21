dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {

    development {
        dataSource {
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
			driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
            url = "jdbc:sqlserver://192.168.1.31;databaseName=etl;"
            username = "etl"
            password = "P@55word"
            logSql = false
            pooled = true
            properties {
               maxActive = 2
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
    
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    
    production {
    
        dataSource {
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
			driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
            url = "jdbc:sqlserver://192.168.1.31;databaseName=etl;"
            username = "etl"
            password = "P@55word"
            logSql = false
            pooled = true
            properties {
               maxActive = 2
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
