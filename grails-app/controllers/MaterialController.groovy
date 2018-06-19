
class MaterialController extends BaseController{
    static allowedMethods = [checkMaterial: 'POST',checkInteger: 'POST',checkFloat: 'POST',save: 'POST']
    def methodsService
    def checkMaterial(){
        try{
            def isAvailable=methodsService.checkMaterial(params)
            render(contentType: 'text/json') {
                [
                        "valid": isAvailable,
                ]
            }
        }
        catch (Exception e){

        }
    }

    def checkInteger(){
        try{
            def isAvailable=methodsService.checkInteger(params)
            render(contentType: 'text/json') {
                [
                        "valid": isAvailable,
                ]
            }
        }
        catch (Exception e){

        }
    }
    def checkFloat(){
        try{
            def isAvailable=methodsService.checkFloat(params)
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
        try{
        def materialsList=methodsService.listOfMaterials()
        [materialsList: materialsList]
    }
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def save(){
        try{
        def material=methodsService.saveMaterial(params)
        if(material){
        if(params.identityMaterialName){
            flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params: [ identityMaterialName: material.identityMaterialName])
    }}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def show(){
        try{
        def material= methodsService.showMaterial(params.identityMaterialName)
        if(material) {
            [material: material]
        }
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def edit(){
        try{
        def material=methodsService.showMaterial(params.identityMaterialName)
        if(material){
        [material: material]}
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def delete(){
        try{
        def material=methodsService.deleteMaterial(params.identityMaterialName)
        if(material){
        flash.message="successfully deleted"}
        else{
         flash.message="unable to delete already deleted material"
        }
        redirect(action: "list")}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def create(){

    }

}
