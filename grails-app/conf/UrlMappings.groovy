class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/edit-profile"(action: "edit",controller: "home")
        "/edit-password"(action: "changePassword",controller: "home")

        "/profile"(action: "profile",controller: "home")
        "/"(action: "loginForm",controller: "login")
        "500"(view: "/home/error500")
        "404"(view:"/home/error404")
        "405"(view:"/home/error403")
//        "500"(view:'/error')
	}
}
