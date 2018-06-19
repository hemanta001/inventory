class UnitController extends BaseController{
    static allowedMethods = [checkUnit: 'POST',save: 'POST']
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
        try {
            def unitList = methodsService.listOfUnit()
            [unitList: unitList]
        }
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def save(){
     try{
        def unit=methodsService.saveUnit(params)
        if(unit){
        if(params.identityUnitName){
            flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params:[identityUnitName: unit.identityUnitName])
    }}
     catch (Exception e){
         render(view: "/home/error500")

     }
    }
    def show(){
        try{
        def unit= methodsService.showUnit(params.identityUnitName)
        if(unit){
        [unit: unit]}
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def edit(){
        try{
        def unit=methodsService.showUnit(params.identityUnitName)
        if(unit){
        [unit: unit]}
        else {
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def delete(){
        try{
        def unit=methodsService.deleteUnit(params.identityUnitName)
                if(unit){
        flash.message="successfully deleted"}
        else{
                    flash.message="unable to delete already deleted unit"
                }
        redirect(action: "list")
    }
        catch (Exception e){
            render(view: "/home/error500")
        }
    }


}
