class UnitController {
    def methodsService
    def list(){
        def unitList=methodsService.listOfUnit()
        [unitList: unitList]
    }
    def save(){
        def unitName=methodsService.saveUnit(params)
        redirect(action: "show",identityUnitName: unitName)
    }
    def show(){
        def unit= methodsService.showUnit(params.identityUnitName)
        [unit: unit]
    }
    def edit(){
        def unit=methodsService.showUnit(params.identityUnitName)
        [unit: unit]
    }
    def delete(){
        methodsService.deleteUnit(params.identityUnitName)
        redirect(action: "list")
    }

}
