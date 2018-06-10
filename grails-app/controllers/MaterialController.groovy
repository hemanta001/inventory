
class MaterialController {
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
        redirect(action: "list")
    }
    def create(){

    }

}
