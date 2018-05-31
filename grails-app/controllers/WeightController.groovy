class WeightController {
    def methodsService
    def create(){

    }
    def list(){
        def weightList=methodsService.listOfWeight()
        [weightList: weightList]
    }
    def save(){
        def weightQuantityUnit=methodsService.saveWeight(params)
        redirect(action: "show",params: [ identityWeightQuantityUnit: weightQuantityUnit])
    }
    def show(){
        def weight= methodsService.showWeight(params.identityWeightQuantityUnit)
        [weight: weight]
    }
    def edit(){
        def weight=methodsService.showWeight(params.identityWeightQuantityUnit)
        [weight: weight]
    }
    def delete(){
        methodsService.deleteWeight(params.identityWeightQuantityUnit)
        redirect(action: "list")
    }

}
