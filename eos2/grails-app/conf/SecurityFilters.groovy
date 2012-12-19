class SecurityFilters {
	def filters = {
		auth(controller: "*", action: "*"){
			before={
				accessControl{true}
			}
		}
		
		admin(controller: "*", action: "*"){
			before={
				accessControl{
					permission("${controllerName}:${actionName}:${params.id}")
				}
			}
		}
	}
}
