
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

    def saveWeight(Map params){
        if(params.identityWeightQuantityUnit){
            def weight=Weight.findByDelFlagAndIdentityWeightQuantityUnit(false,params.identityWeightQuantityUnit)
            weight.weightQuantityUnit=params.weightQuantityUnit
            weight.save(flush: true)
            return weight.identityWeightQuantityUnit
        }
        else{
            def weight=new Weight()
            weight.weightQuantityUnit=params.weightQuantityUnit
            weight.identityWeightQuantityUnit=convertToOriginalUrl(params.weightQuantityUnit)
            weight.delFlag=false
            weight.save(flush: true)
            return weight.identityWeightQuantityUnit
        }
    }

    def showWeight(String identityWeightQuantityUnit){
        def weight=Weight.findByDelFlagAndIdentityWeightQuantityUnit(false,identityWeightQuantityUnit)
        return weight
    }

    def deleteWeight(String identityWeightQuantityUnit){
        def weight=Weight.findByDelFlagAndIdentityWeightQuantityUnit(false,identityWeightQuantityUnit)
        weight.delFlag=true
        weight.save(flush: true)
    }

    def listOfWeight(){
        def weightList=Weight.findAllByDelFlag(false)
        return weightList
    }

    def saveUnit(Map params){
        if(params.identityUnitName){
            def unit=Unit.findByDelFlagAndIdentityUnitName(false,params.identityUnitName)
            unit.unitName=params.unitName
            unit.save(flush: true)
            return unit.identityUnitName
        }
        else{
            def unit=new Unit()
            unit.unitName=params.unitName
            unit.identityUnitName=convertToOriginalUrl(params.unitName)
            unit.delFlag=false
            unit.save(flush: true)
            return unit.identityUnitName
        }
    }

    def showUnit(String identityUnitName){
        def unit=Unit.findByDelFlagAndIdentityUnitName(false,identityUnitName)
        return unit
    }

    def deleteUnit(String identityUnitName){
        def unit=Unit.findByDelFlagAndIdentityUnitName(false,identityUnitName)
        unit.delFlag=true
        unit.save(flush: true)
    }

    def listOfUnit(){
        def unitList=Unit.findAllByDelFlag(false)
        return unitList
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
