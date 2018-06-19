class ItemController extends BaseController{
    static allowedMethods = [checkItem: "POST",save: "POST"]
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
        try{
        def itemList=methodsService.listOfItem()
        [itemList: itemList]}
        catch (Exception e){
render(view: "/home/error500")
        }
    }
    def save(){
        try{
        def item=methodsService.saveItem(params)
        if(item){
        if(params.identityItemName){
        flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
        redirect(action: "show",params:[identityItemName: item.identityItemName])
        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def show(){
        try{
        def item= methodsService.showItem(params.identityItemName)
        if(item){
        [item: item]}
        else{
            render(view: "/home/error404")
        }}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def edit(){
        try{
        def item=methodsService.showItem(params.identityItemName)
        if(item){
            [item: item]}
        else{
            render(view: "/home/error404")
        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def delete(){
        try{
        def item=methodsService.deleteItem(params.identityItemName)
        if(item){
        flash.message="successfully deleted"}
        else{
            flash.message="unable to delete the already deleted item"
        }

        redirect(action: "list")
    }
        catch (Exception e){
            render(view: "/home/error500")

        }
    }

}

