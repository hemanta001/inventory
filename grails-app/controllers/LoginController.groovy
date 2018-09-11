

class LoginController {

    static allowedMethods = [login: 'POST']
    def methodsService

    def loginForm() {
if(session.adminUser){
    redirect(action: "home",controller: "home")
}
        else{
    render(view:"loginForm")
}
    }
    def login(){
        try{
            def userInstance= User.findByEmailOrUserName(params.emailOrUserName,params.emailOrUserName)
            def status=false
            if (userInstance) {
                status = methodsService.decryptPassword(params.password, userInstance.password)
            }

            if(status==true){
                session.adminUser = userInstance
                redirect(action: "home",controller: "home")
            }
            else{
                flash.messageError="username or email or password does not exist"
                redirect(action: "loginForm")
            }

        }
        catch (Exception e){
            render(view: "/home/error500")
        }
    }

}
