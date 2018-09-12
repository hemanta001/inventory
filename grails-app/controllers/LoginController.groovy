import grails.converters.JSON
import grails.converters.XML
import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.parser.JSONParser

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

//def check(){
//    String inliness=""
//    URL url = new URL("http://localhost:8081/rest/book/list?username=hemanta")
//    HttpURLConnection conn = (HttpURLConnection)url.openConnection()
//    conn.setRequestMethod("GET")
//    int responsecode = conn.getResponseCode()
//    if(responsecode != 200) {
//        render(view: "check")
//    }
//        else
//
//    {
//        Scanner sc = new Scanner(url.openStream());
//        while(sc.hasNext())
//        {
//            inliness+=sc.nextLine();
//        }
//        System.out.println("\nJSON data in string format")
//        System.out.println(inliness)
//        sc.close()
//        def jsonSlurper = new JsonSlurper()
//        def object = jsonSlurper.parseText(inliness)
//        [object:object]
//    }
//
//}
//    def list() {
//        if(params.username=="hemanta"){
//            def books = Book.list()
//
//            withFormat {
//
//                json { render books as JSON }
//                xml { render books as XML }
//            }
//
//        }
//
//    }
//
//    def show(Integer id) {
//        def book = Book.get(id)
//        if (book) {
//            withFormat {
//                xml {
//                    render book as XML
//                }
//                json {
//                    render book as JSON
//                }
//            }
//        } else {
//            render "book not found"
//        }
//    }

}
