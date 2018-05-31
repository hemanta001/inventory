class ItemController {
    def methodsService
    def create(){

    }
    def list(){
        def itemList=methodsService.listOfItem()
        [itemList: itemList]
    }
    def save(){
        def itemName=methodsService.saveItem(params)
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
