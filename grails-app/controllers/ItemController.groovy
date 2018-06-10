class ItemController {
    def methodsService
    def checkItem(){
        try{
            def isAvailable=methodsService.checkItem(params)
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
        def itemList=methodsService.listOfItem()
        [itemList: itemList]
    }
    def save(){
        def itemName=methodsService.saveItem(params)
        if(params.identityItemName){
        flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params:[identityItemName: itemName])
    }
    def show(){
        def item= methodsService.showItem(params.identityItemName)
        [item: item]
    }
    def edit(){
        def item=methodsService.showItem(params.identityItemName)
        [item: item]
    }
    def delete(){
        methodsService.deleteItem(params.identityItemName)
        redirect(action: "list")
    }

}
