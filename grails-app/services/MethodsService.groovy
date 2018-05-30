
import grails.transaction.Transactional

@Transactional
class MethodsService {
    def saveMaterial(Map params){
       if(params.identityMaterialName){
           def material=Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)
           material.materialName=params.materialName
           material.save(flush: true)
           return material.identityMaterialName
       }
        else{
           def material=new Material()
           material.materialName=params.materialName
           material.identityMaterialName=convertToOriginalUrl(params.materialName)
           material.delFlag=false
           material.save(flush: true)
           return material.identityMaterialName
       }
    }

    def showMaterial(String identityMaterialName){
        def material=Material.findByDelFlagAndIdentityMaterialName(false,identityMaterialName)
        return material
    }

    def deleteMaterial(String identityMaterialName){
        def material=Material.findByDelFlagAndIdentityMaterialName(false,identityMaterialName)
        material.delFlag=true
        material.save(flush: true)
    }

    def listOfMaterials(){
        def materialsList=Material.findAllByDelFlag(false)
            return materialsList
    }

    def saveItem(Map params){
        if(params.identityItemName){
            def item=Item.findByDelFlagAndIdentityItemName(false,params.identityItemName)
            item.itemName=params.itemName
            item.save(flush: true)
            return item.identityItemName
        }
        else{
            def item=new Item()
            item.itemName=params.itemName
            item.identityItemName=convertToOriginalUrl(params.itemName)
            item.delFlag=false
            item.save(flush: true)
            return item.identityItemName
        }
    }

    def showItem(String identityItemName){
        def item=Item.findByDelFlagAndIdentityItemName(false,identityItemName)
        return item
    }

    def deleteItem(String identityItemName){
        def item=Item.findByDelFlagAndIdentityItemName(false,identityItemName)
        item.delFlag=true
        item.save(flush: true)
    }

    def listOfItem(){
        def itemList=Item.findAllByDelFlag(false)
        return itemList
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
