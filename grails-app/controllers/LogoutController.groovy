class LogoutController {

    def logout(){
        try{
        if(session.adminUser){
        session.adminUser=null
        flash.messageSuccess="successfully logged out"
        redirect(action: "loginForm",controller: "login")
    }
            else{
            redirect(action: "loginForm",controller: "login")

        }
    }
        catch (Exception e){
            render(view: "/home/error500")

        }
    }


}
