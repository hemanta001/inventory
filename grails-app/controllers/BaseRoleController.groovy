
class BaseRoleController {

    def beforeInterceptor=[action: this.&auth]
//    def beforeInterceptor=[action: this.&auth,except:"adminLoginForm"]
    def auth(){
        if(session.adminUser){
            if(!session.adminUser.role.equalsIgnoreCase("admin")){
render(view: "/home/error403")
            }
        }
        else{
            redirect(action: "loginForm",controller: "login")
        }
    }

}
