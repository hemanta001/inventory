class MaterialController {
    def methodsService
    def list(){
        def materialsList=methodsService.listOfMaterials()
        [materialsList: materialsList]
    }
    def save(){
        def materialName=methodsService.saveMaterial(params)
        redirect(action: "edit",params: [ identityMaterialName: materialName])
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
