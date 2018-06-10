class UnitController {
    def methodsService
    def checkUnit(){
        try{
            def isAvailable=methodsService.checkUnit(params)
            render(contentType: 'text/json') {
                [
                        "valid": isAvailable,
                ]
            }
        }
        catch (Exception e){

        }
    }
    def create(){

    }
    def list(){
        def unitList=methodsService.listOfUnit()
        [unitList: unitList]
    }
    def save(){
        def unitName=methodsService.saveUnit(params)
        if(params.identityUnitName){
            flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params:[identityUnitName: unitName])
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
