

class MaterialsController {
def methodsService
    def list(){
        def materialsList=methodsService.listOfMaterials()
        [materialsList:materialsList]
    }
    def save(){
        def materialId=methodsService.saveMaterial(params)
        redirect(action: "show",id: materialId)
    }
    def show(Long id){

    }

}
