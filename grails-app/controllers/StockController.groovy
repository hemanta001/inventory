

class StockController extends BaseController{
    static allowedMethods = [save: 'POST']
    def methodsService
    def remainingStockList(){
        try{
       if(Item.findByDelFlagAndIdentityItemName(false,params.identityItemName) && Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)){
        def remainingStockList=methodsService.remainingStockList(params)
        [stockRemainingQuantity:remainingStockList,identityMaterialName: params.identityMaterialName,identityItemName:params.identityItemName]
    }
        else{
           render(view: "/home/error404")
       }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def create(){
        try{
        if((Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName) && params.stockType=="stock-in") ||(Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName) && params.stockType=="stock-out")){
        def materialInstance=methodsService.showMaterialForStock(params)
[materialInstance:materialInstance,stockType:params.stockType]}
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def save(){
        try{
        if(Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)){
            def totalArray=methodsService.saveStock(params)
            if(totalArray) {
                if (params.id) {
                    flash.message = "successfully updated"
                } else {
                    flash.message = "successfully added"

                }
                redirect(action: "show", params: [identityMaterialName: totalArray[1], stock: totalArray[0].id])
            }
        }}
        catch (Exception e){
            render(view: "/home/error500")
        }
    }
    def show(){
        try{
        def materialInstance=Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)
        if(materialInstance){
        def stock=params.stock as Long
        def stockInstance=methodsService.showStock(materialInstance.identityMaterialName,stock)
            if(stockInstance){
        [stockInstance:stockInstance,materialInstance:materialInstance]}
        else{
                render(view: "/home/error404")
            }
        }
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def edit(){
        try{
        def materialInstance=Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)
        if(materialInstance){
        def stock=params.stock as Long
        def stockInstance=methodsService.showStock(materialInstance.identityMaterialName,stock)
        if(stockInstance){
            [stockInstance:stockInstance,materialInstance:materialInstance]}
        else{
            render(view: "/home/error404")

        }
    }
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def list(){
        try{
        if((Item.findByDelFlagAndIdentityItemName(false, params.identityItemName) && Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName) && params.stockType=="stock-in") ||(Item.findByDelFlagAndIdentityItemName(false, params.identityItemName) && Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName) && params.stockType=="stock-out")) {

            def stockList = methodsService.listOfStock(params)
            [stockList: stockList, itemInstance: Item.findByDelFlagAndIdentityItemName(false, params.identityItemName), materialInstance: Material.findByDelFlagAndIdentityMaterialName(false, params.identityMaterialName), stockType: params.stockType]
        }
        else{
            render(view: "/home/error404")

        }}
        catch (Exception e){
            render(view: "/home/error500")

        }
    }
    def delete(){
     try{
        if(Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)){
            def stockInstance=methodsService.showStock(params.identityMaterialName,params.stock as long)
   if(stockInstance){
            methodsService.deleteStock(params)
        flash.message="successfully deleted"
    }
            else{
       flash.message="unable to delete already deleted stock"

   }
            redirect(action: "list",params: [stockType:params.stockType,identityMaterialName:params.identityMaterialName,identityItemName:params.identityItemName])

        }}
     catch (Exception e){
         render(view: "/home/error500")
     }
    }
}
