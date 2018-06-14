import liquibase.util.file.FilenameUtils
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserController {
    final static Pattern PATTERN = Pattern.compile("(.*?)(?:\\((\\d+)\\))?(\\.[^.]*)?");

    def methodsService

    def checkUserName(){
    try{
        def isAvailable=methodsService.checkUserName(params)
        render(contentType: 'text/json') {
            [
                    "valid": isAvailable,
            ]
        }
    }
    catch (Exception e){

    }
}
    def checkEmail(){
        try{
            def isAvailable=methodsService.checkEmail(params)
            render(contentType: 'text/json') {
                [
                        "valid": isAvailable,
                ]
            }
        }
        catch (Exception e){

        }
    }

    def list(){
        def userList=methodsService.listOfUser()
        [userList: userList]
    }
    def save(){
        def userInstance=methodsService.saveUser(params)
        if(params.userNameId){
            if(params.profileImageName) {
if(userInstance.profileImageName) {
    userInstance.profileImageName = editProfileImage(userInstance.profileImageName)
}
                else{
    userInstance.profileImageName=uploadProfileImage()
    
}
            }
            userInstance.save(flush: true)
            flash.message="successfully updated"
        }
        else{
            if(params.profileImageName){
            userInstance.profileImageName=uploadProfileImage()
            }
            userInstance.save(flush:true)
            flash.message="successfully added"

        }
        redirect(action: "show",params: [ userName: userInstance.userName])
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
        return fileName
    }
    def show(){
        def userInstance= methodsService.showUser(params)
        [userInstance: userInstance]
    }
    def edit(){
        def userInstance=methodsService.showUser(params)
        [userInstance: userInstance]
    }
    def delete(){
        methodsService.deleteUser(params)
        flash.message="successfully deleted"
        redirect(action: "list")
    }
    def create(){

    }
    def renderImage() {
        try {


            if(params.profileImageName!=null) {
                def homeDir = new File(System.getProperty("user.home"))
                String profileImagePath = "/" + homeDir + "/yarsaa/"
                String image = params.profileImageName // or whatever name you saved in your db
                String extension = FilenameUtils.getExtension(image)
                File imageFile = new File(profileImagePath + image);

                BufferedImage originalImage = ImageIO.read(imageFile);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if (extension.equalsIgnoreCase("jpg")) {
                    ImageIO.write(originalImage, "jpg", baos);
                } else if (extension.equalsIgnoreCase("jpeg")) {
                    ImageIO.write(originalImage, "jpeg", baos);

                } else if (extension.equalsIgnoreCase("bmp")) {
                    ImageIO.write(originalImage, "bmp", baos);

                } else if (extension.equalsIgnoreCase("png")) {
                    ImageIO.write(originalImage, "png", baos);

                } else if (extension.equalsIgnoreCase("gif")) {
                    ImageIO.write(originalImage, "gif", baos);

                } else {
                    ImageIO.write(originalImage, "jpg", baos);

                }

                byte[] imageInByte = baos.toByteArray();

                if (extension.equalsIgnoreCase("jpg")) {
                    response.contentType = 'image/jpg'
                } else if (extension.equalsIgnoreCase("jpeg")) {
                    response.contentType = 'image/jpeg'
                } else if (extension.equalsIgnoreCase("bmp")) {
                    response.contentType = 'image/bmp'
                } else if (extension.equalsIgnoreCase("png")) {
                    response.contentType = 'image/png'
                } else if (extension.equalsIgnoreCase("gif")) {
                    response.contentType = 'image/gif'
                } else {
                    ImageIO.write(originalImage, "jpg", baos);

                }

                response.outputStream << imageInByte
                response.outputStream.flush()
            }
        }
        catch (Exception e){

        }
    }

}
