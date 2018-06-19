

class StockController extends BaseController{
    static allowedMethods = [save: 'POST']
    def methodsService
    def remainingStockList(){
        def remainingStockList=methodsService.remainingStockList(params)
        [stockRemainingQuantity:remainingStockList,identityMaterialName: params.identityMaterialName,identityItemName:params.identityItemName]
    }
    def create(){
        def materialInstance=methodsService.showMaterialForStock(params)
[materialInstance:materialInstance,stockType:params.stockType]
    }
    def save(){
def totalArray=methodsService.saveStock(params)
        if(params.id){
            flash.message="successfully updated"
        }
        else{
            flash.message="successfully added"

        }
redirect(action: "show",params: [identityMaterialName: totalArray[1],stock: totalArray[0].id])
    }
    def show(){
        def materialInstance=Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)
        def stock=params.stock as Long
        def stockInstance=methodsService.showStock(materialInstance.identityMaterialName,stock)
        [stockInstance:stockInstance,materialInstance:materialInstance]
    }
    def edit(){
        def materialInstance=Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName)
        def stock=params.stock as Long
        def stockInstance=methodsService.showStock(materialInstance.identityMaterialName,stock)
        [stockInstance:stockInstance,materialInstance:materialInstance]
    }
    def list(){
        def stockList=methodsService.listOfStock(params)
        [stockList:stockList,itemInstance:Item.findByDelFlagAndIdentityItemName(false,params.identityItemName),materialInstance:Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName),stockType:params.stockType]
    }
    def delete(){
        methodsService.deleteStock(params)
        flash.message="successfully deleted"
        redirect(action: "list",params: [stockType:params.stockType,identityMaterialName:params.identityMaterialName,identityItemName:params.identityItemName])
    }
}
