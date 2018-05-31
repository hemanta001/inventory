

class StockController {
    def methodsService
    def create(){
        def materialInstance=methodsService.showMaterialForStock(params)
[materialInstance:materialInstance,stockType:params.stockType]
    }
    def save(){
def totalArray=methodsService.saveStock(params)
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
        [stockList:stockList,materialInstance:Material.findByDelFlagAndIdentityMaterialName(false,params.identityMaterialName),stockType:params.stockType]
    }
}
