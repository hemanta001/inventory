import grails.transaction.Transactional
import groovy.sql.Sql


@Transactional
class MethodsService {
    def dataSource

    def saveMaterial(Map params) {
        if (params.identityMaterialName) {
            def material = Material.findByDelFlagAndIdentityMaterialName(false, params.identityMaterialName)
            material.materialName = params.materialName
            material.identityMaterialName = convertToOriginalUrl(material.materialName)
            material.save(flush: true)
            return material.identityMaterialName
        } else {
            def material = new Material()
            material.materialName = params.materialName
            material.identityMaterialName = convertToOriginalUrl(material.materialName)
            material.delFlag = false
            material.save(flush: true)
            createTable(material.identityMaterialName)
            return material.identityMaterialName
        }
    }

    def createTable(String tableName) {
        def sql = new Sql(dataSource)
        // the sql script that creates a table
        def createTableScript = "CREATE TABLE IF NOT EXISTS " + tableName + " (id BIGINT(20) NOT NULL  AUTO_INCREMENT,quantity_number BIGINT(20) NOT NULL,del_flag BIT(1) NOT NULL,date VARCHAR(255) NOT NULL,stock_type VARCHAR(50) NOT NULL,item_with_weight_and_unit VARCHAR(255) NOT NULL,item_id BIGINT(20) NOT NULL,weight_id BIGINT(20) NOT NULL,FOREIGN KEY (item_id) REFERENCES item(id),FOREIGN KEY (weight_id) REFERENCES weight(id),PRIMARY KEY (id))"
        // execute the create table script
        sql.execute(createTableScript);
        // query MySQL for the details of the created table
        sql.eachRow('DESCRIBE ' + tableName) { row ->
            println "Fielld = ${row[0]}, type = ${row[1]}"
        }
        // close connection
        sql.close()
    }

    def showMaterial(String identityMaterialName) {
        def material = Material.findByDelFlagAndIdentityMaterialName(false, identityMaterialName)
        return material
    }

    def deleteMaterial(String identityMaterialName) {
        def material = Material.findByDelFlagAndIdentityMaterialName(false, identityMaterialName)
        material.delFlag = true
        material.save(flush: true)
    }

    def listOfMaterials() {
        def materialsList = Material.findAllByDelFlag(false)
        return materialsList
    }

    def saveItem(Map params) {
        if (params.identityItemName) {
            def item = Item.findByDelFlagAndIdentityItemName(false, params.identityItemName)
            item.itemName = params.itemName
            item.identityItemName = convertToOriginalUrl(item.itemName)

            item.save(flush: true)
            return item.identityItemName
        } else {
            def item = new Item()
            item.itemName = params.itemName
            item.identityItemName = convertToOriginalUrl(params.itemName)
            item.delFlag = false
            item.save(flush: true)
            return item.identityItemName
        }
    }

    def showItem(String identityItemName) {
        def item = Item.findByDelFlagAndIdentityItemName(false, identityItemName)
        return item
    }

    def deleteItem(String identityItemName) {
        def item = Item.findByDelFlagAndIdentityItemName(false, identityItemName)
        item.delFlag = true
        item.save(flush: true)
    }

    def listOfItem() {
        def itemList = Item.findAllByDelFlag(false)
        return itemList
    }

    def saveWeight(Map params) {
        if (params.identityWeightQuantityUnit) {
            def weight = Weight.findByDelFlagAndIdentityWeightQuantityUnit(false, params.identityWeightQuantityUnit)
            weight.weightQuantity = params.weightQuantity as float
            weight.unit = Unit.findByDelFlagAndId(false, params.unitId)
            weight.weightQuantityUnit = weight.weightQuantity + " " + weight.unit.unitName
            weight.identityWeightQuantityUnit = convertToOriginalUrl(weight.weightQuantityUnit)
            weight.save(flush: true)
            return weight.identityWeightQuantityUnit
        } else {
            def weight = new Weight()
            weight.weightQuantity = params.weightQuantity as float
            weight.unit = Unit.findByDelFlagAndId(false, params.unitId)
            weight.weightQuantityUnit = weight.weightQuantity + " " + weight.unit.unitName
            weight.identityWeightQuantityUnit = convertToOriginalUrl(weight.weightQuantityUnit)
            weight.delFlag = false
            weight.save(flush: true)
            return weight.identityWeightQuantityUnit
        }
    }

    def showWeight(String identityWeightQuantityUnit) {
        def weight = Weight.findByDelFlagAndIdentityWeightQuantityUnit(false, identityWeightQuantityUnit)
        return weight
    }

    def deleteWeight(String identityWeightQuantityUnit) {
        def weight = Weight.findByDelFlagAndIdentityWeightQuantityUnit(false, identityWeightQuantityUnit)
        weight.delFlag = true
        weight.save(flush: true)
    }

    def listOfWeight() {
        def weightList = Weight.findAllByDelFlag(false)
        return weightList
    }

    def saveUnit(Map params) {
        if (params.identityUnitName) {
            def unit = Unit.findByDelFlagAndIdentityUnitName(false, params.identityUnitName)
            unit.unitName = params.unitName
            unit.identityUnitName = convertToOriginalUrl(params.unitName)
            unit.save(flush: true)
            return unit.identityUnitName
        } else {
            def unit = new Unit()
            unit.unitName = params.unitName
            unit.identityUnitName = convertToOriginalUrl(params.unitName)
            unit.delFlag = false
            unit.save(flush: true)
            return unit.identityUnitName
        }
    }

    def showUnit(String identityUnitName) {
        def unit = Unit.findByDelFlagAndIdentityUnitName(false, identityUnitName)
        return unit
    }

    def deleteUnit(String identityUnitName) {
        def unit = Unit.findByDelFlagAndIdentityUnitName(false, identityUnitName)
        unit.delFlag = true
        unit.save(flush: true)
    }

    def listOfUnit() {
        def unitList = Unit.findAllByDelFlag(false)
        return unitList
    }

    def convertToOriginalUrl(String urlName) {
        def urlOriginal = ""
        urlName = urlName.replace("&", "");
        urlName = urlName.replace("/", "");
        urlName = urlName.replace(" ", "-");
        for (int i = 0; i < urlName.size(); i++) {
            if (urlOriginal.contains('-') && urlName[i] == '-' && urlName[i] == urlName[i - 1]) {
                urlOriginal = urlOriginal + ""
            } else {
                urlOriginal = urlOriginal + urlName[i]

            }
        }
        urlOriginal = urlOriginal.toLowerCase()
        return urlOriginal

    }

    def showMaterialForStock(Map params) {
        def materialInstance = Material.findByDelFlagAndIdentityMaterialName(false, params.identityMaterialName)
        return materialInstance
    }

    def saveStock(Map params) {
        def sql = new Sql(dataSource)
        def id
        def createTableScript
        def array = []
        def itemName = Item.findByIdAndDelFlag(params.itemId, false).itemName
        def weightQuantityUnit = Weight.findByIdAndDelFlag(params.weightId, false).weightQuantityUnit
        def itemWithWeightAndUnit = weightQuantityUnit + " " + itemName
        if (!params.id) {

            // the sql script that creates a table
            createTableScript = "INSERT INTO " + params.identityMaterialName + " (item_id,weight_id,quantity_number,date,stock_type,item_with_weight_and_unit,del_flag ) VALUES ((SELECT id from item WHERE id='" + params.itemId + "' ),(SELECT id from weight WHERE id='" + params.weightId + "' ),'" + params.quantityNumber + "','" + params.date + "','" + params.stockType + "','" + itemWithWeightAndUnit + "',0)"
            // execute the create table script
            def keys = sql.executeInsert(createTableScript);
            id = keys[0][0]
            Stock stock = showStock(params.identityMaterialName, id)
            // close connection
            sql.close()
            array = [stock, params.identityMaterialName]
            return array
        } else {
            id = params.id as Long
            createTableScript = "UPDATE " + params.identityMaterialName + " SET item_id = (SELECT id from item WHERE id=" + params.itemId + " ), weight_id= (SELECT id from weight WHERE id=" + params.weightId + "), quantity_number='" + params.quantityNumber + "', item_with_weight_and_unit='" + itemWithWeightAndUnit + "',date='" + params.date + "'  WHERE id=" + id
            // execute the create table script
            sql.execute(createTableScript)
            Stock stock = showStock(params.identityMaterialName, id)
            // close connection
            sql.close()
            array = [stock, params.identityMaterialName]
            return array
        }
    }

    def showStock(String tableName, Long id) {
        def sql = new Sql(dataSource)
        Stock stock = new Stock()
        sql.eachRow('SELECT * FROM ' + tableName + ' WHERE id=' + id) { row ->
            stock.id = row[0]
            stock.quantityNumber = row[1]
            stock.delFlag = row[2]
            stock.date = row[3]
            stock.stockType = row[4]
            stock.itemWithWeightAndUnit = row[5]
            stock.item = Item.get(row[6])
            stock.weight = Weight.get(row[7])
        }
        sql.close()
        return stock
    }

    def listOfStock(Map params) {
        def sql = new Sql(dataSource)
        List<Stock> stockList = new ArrayList<>()
        sql.eachRow('SELECT * FROM ' + params.identityMaterialName + " WHERE del_flag=0") { row ->
            Stock stock = new Stock()
            stock.id = row[0]
            stock.quantityNumber = row[1]
            stock.delFlag = row[2]
            stock.date = row[3]
            stock.stockType = row[4]
            stock.itemWithWeightAndUnit = row[5]
            stock.item = Item.get(row[6])
            stock.weight = Weight.get(row[7])
            if (stock.stockType.equalsIgnoreCase(params.stockType)) {
                stockList.add(stock)
            }
        }
        sql.close()
        return stockList
    }

    def deleteStock(Map params) {
        def sql = new Sql(dataSource)
        String createScript = "UPDATE " + params.identityMaterialName + " SET del_flag=1  WHERE id=" + params.stock
        sql.execute(createScript)
        sql.close()
    }

    def remainingStockList(Map params) {
        def totalStockList = totalArrayList(params)
        def remainingStockList = totalToRemainingStockList(totalStockList)
        return remainingStockList
    }

    def totalToRemainingStockList(List<Stock> stockList) {
        for (int i = 0; i < stockList.size() - 1; i++) {
            for (int j = i + 1; j < stockList.size(); j++) {
                if (stockList[i].itemWithWeightAndUnit == stockList[j].itemWithWeightAndUnit) {
                    if (stockList[i].stockType == stockList[j].stockType) {
                        stockList[i].quantityNumber = stockList[i].quantityNumber + stockList[j].quantityNumber
                        stockList.remove(stockList[j])
                        j--
                    } else {
                        stockList[i].quantityNumber = stockList[i].quantityNumber - stockList[j].quantityNumber
                        stockList.remove(stockList[j])
                        j--
                    }
                }
            }
        }
        return stockList

    }

    def totalArrayList(Map params) {
        def sql = new Sql(dataSource)
        List<Stock> stockList = new ArrayList<>()
        sql.eachRow('SELECT * FROM ' + params.identityMaterialName + " WHERE del_flag=0") { row ->
            Stock stock = new Stock()
            stock.id = row[0]
            stock.quantityNumber = row[1]
            stock.delFlag = row[2]
            stock.date = row[3]
            stock.stockType = row[4]
            stock.itemWithWeightAndUnit = row[5]
            stock.item = Item.get(row[6])
            stock.weight = Weight.get(row[7])
            stockList.add(stock)
        }
        sql.close()
    return stockList
    }

}

