import groovy.sql.Sql

class MaterialController {
    def methodsService

def table(){

}
    def list(){
        def materialsList=methodsService.listOfMaterials()
        [materialsList: materialsList]
    }
    def save(){
        def materialName=methodsService.saveMaterial(params)
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
