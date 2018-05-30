
import grails.transaction.Transactional

@Transactional
class MethodsService {
    def saveMaterial(Map params){
       if(params.identityName){
           def material=Materials.findByDelFlagAndIdentityName(false,params.identityName)
           material.name=params.name
           material.identityName=convertToOriginalUrl(params.name)
           material.save(flush: true)
           return material.id
       }
        else{
           def material=new Materials()
           material.name=params.name
           material.identityName=convertToOriginalUrl(params.name)
           material.delFlag=false
           material.save(flush: true)
           return material.id
       }
    }
def listOfMaterials(){
  def materialsList=Materials.findAllByDelFlag(false)
    return materialsList
}
    def convertToOriginalUrl(String urlName){
        def urlOriginal=""
        urlName = urlName.replace("&", "");
        urlName = urlName.replace("/", "");
        urlName = urlName.replace(" ", "-");
        for(int i=0;i<urlName.size();i++){
            if(urlOriginal.contains('-') && urlName[i]=='-' && urlName[i]==urlName[i-1]) {
                urlOriginal=urlOriginal+""
            }
            else{
                urlOriginal=urlOriginal+urlName[i]

            }
        }
        urlOriginal=urlOriginal.toLowerCase()
        return  urlOriginal

    }

}
