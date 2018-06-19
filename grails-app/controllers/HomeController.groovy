import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import java.util.regex.Matcher
import java.util.regex.Pattern

class HomeController extends BaseController{
    static allowedMethods = [updatePassword: 'POST',checkOldPassword: 'POST',checkPassword: 'POST',update: 'POST',editProfileImage: 'POST',uploadProfileImage: 'POST']
    final static Pattern PATTERN = Pattern.compile("(.*?)(?:\\((\\d+)\\))?(\\.[^.]*)?");

    def methodsService
    def home() {

    }
    def updatePassword(){
        methodsService.updatePassword(session.adminUser,params)
        flash.message="successfully updated your password"
        redirect(action: "profile")
    }
    def checkOldPassword(){
        try{
            def isAvailable=methodsService.decryptPassword(params.newPassword, session.adminUser.password)
          if(isAvailable){
            render(contentType: 'text/json') {
                [
                        "valid": false,
                ]
            }}
            else{
              render(contentType: 'text/json') {
                  [
                          "valid": true,
                  ]
              }
          }
        }
        catch (Exception e){

        }
    }
    def changePassword(){
      render(view: "editPassword")
    }
    def checkPassword(){
        try{
            def isAvailable=methodsService.decryptPassword(params.password, session.adminUser.password)
            render(contentType: 'text/json') {
                [
                        "valid": isAvailable,
                ]
            }
        }
        catch (Exception e){

        }
    }
    def profile(){
        [userInstance:session.adminUser]
    }
    def update(){
       def userInstance=methodsService.updateUser(session.adminUser,params)
        if(userInstance.profileImageName) {
            userInstance.profileImageName = editProfileImage(userInstance.profileImageName)
        }
        else{
            userInstance.profileImageName=uploadProfileImage()

        }
        userInstance.save(flush: true)
        flash.message="successfully updated your profile"
        redirect(action: "profile")
    }
    def edit(){
        def userInstance=session.adminUser
[userInstance:userInstance]
    }
    def editProfileImage(String imageNameOld){
        def mp = (MultipartHttpServletRequest) request
        CommonsMultipartFile file = (CommonsMultipartFile) mp.getFile("profileImageName")
        def homeDir = new File(System.getProperty("user.home"))
        File theDir = new File(homeDir, "yarsaa");
        if (!theDir.exists()) {
            theDir.mkdir();
            print "yes"
        }
        if (file.size > 0) {
            File fileOld = new File(homeDir, "yarsaa/${imageNameOld}")
            fileOld.delete();
            String fileName = file.originalFilename
            abc:
            boolean check = new File(homeDir, "yarsaa/" + fileName).exists()
            if (check == true) {
                Matcher m = PATTERN.matcher(fileName);
                if (m.matches()) {
                    String prefix = m.group(1);
                    String last = m.group(2);
                    String suffix = m.group(3);
                    if (suffix == null) suffix = "";
                    int count = last != null ? Integer.parseInt(last) : 0;
                    count++;
                    fileName = prefix + "(" + count + ")" + suffix;
                    continue abc
                }
            }
            File fileDest = new File(homeDir, "yarsaa/${fileName}")
            file.transferTo(fileDest)
            return fileName

        } else {
            return imageNameOld
        }
    }
    def uploadProfileImage(){
        def mp = (MultipartHttpServletRequest) request
        CommonsMultipartFile file = (CommonsMultipartFile) mp.getFile("profileImageName")
        if(file.size>0){
            def fileName = file.originalFilename
            def homeDir = new File(System.getProperty("user.home"))
            File theDir = new File(homeDir, "yarsaa");
            if (!theDir.exists()) {
                theDir.mkdir();
            }

            abc:
            boolean check = new File(homeDir, "yarsaa/" + fileName).exists()
            if (check == true) {
                Matcher m = PATTERN.matcher(fileName);
                if (m.matches()) {
                    String prefix = m.group(1);
                    String last = m.group(2);
                    String suffix = m.group(3);
                    if (suffix == null) suffix = "";
                    int count = last != null ? Integer.parseInt(last) : 0;
                    count++;
                    fileName = prefix + "(" + count + ")" + suffix;
                    continue abc
                }
            }
            File fileDest = new File(homeDir, "yarsaa/${fileName}")
            file.transferTo(fileDest)
            return fileName}
    }
}
