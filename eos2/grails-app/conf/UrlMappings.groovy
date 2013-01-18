class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(controller:"index")
		"400" (view:"/error/400")//: Bad Request
		"401" (view:"/error/401")//: Unauthorized
		"402" (view:"/error/402")//: Payment Required
		"403" (view:"/error/403")//: Forbidden
		"404" (view:"/error/404")//: Not Found
		"405" (view:"/error/405")//: Method Not Allowed
		"406" (view:"/error/406")//: Not Acceptable
		"407" (view:"/error/407")//: Proxy Authentication Required
		"408" (view:"/error/408")//: Request Time-out
		"409" (view:"/error/409")//: Conflict
		"410" (view:"/error/410")//: Gone
		"411" (view:"/error/411")//: Length Required
		"412" (view:"/error/412")//: Precondition Failed
		"413" (view:"/error/413")//: Request Entity Too Large
		"414" (view:"/error/414")//: Request-URI Too Large
		"415" (view:"/error/415")//: Unsupported Media Type
		"416" (view:"/error/416")//: Requested range not satisfiable
		"417" (view:"/error/417")//: Expectation Failed
		"500" (view:"/error/500")//: Internal Server Error
		"501" (view:"/error/501")//: Not Implemented
		"502" (view:"/error/502")//: Bad Gateway
		"503" (view:"/error/503")//: Service Unavailable
		"504" (view:"/error/504")//: Gateway Time-out
		"505" (view:"/error/505")//: HTTP Version not supported 
	}
}
