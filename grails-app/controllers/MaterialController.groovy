
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
def table(){

}
    def list(){
        def materialsList=methodsService.listOfMaterials()
        [materialsList: materialsList]
    }
    def save(){
        def materialName=methodsService.saveMaterial(params)
        if(params.identityMaterialName){
            flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params: [ identityMaterialName: materialName])
    }
    def show(){
        def material= methodsService.showMaterial(params.identityMaterialName)
        [material: material]
    }
    def edit(){
        def material=methodsService.showMaterial(params.identityMaterialName)
        [material: material]
    }
    def delete(){
        methodsService.deleteMaterial(params.identityMaterialName)
        flash.message="successfully deleted"
        redirect(action: "list")
    }
    def create(){

    }

}
