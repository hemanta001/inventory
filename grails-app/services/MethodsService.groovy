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
        def createTableScript = "CREATE TABLE IF NOT EXISTS " + tableName + " (id BIGINT(20) NOT NULL  AUTO_INCREMENT,quantity_number BIGINT(20) NOT NULL,del_flag BIT(1) NOT NULL,date VARCHAR(255) NOT NULL,stock_type VARCHAR(50) NOT NULL,weight FLOAT(20) NOT NULL,item_id BIGINT(20) NOT NULL,unit_id BIGINT(20) NOT NULL,FOREIGN KEY (item_id) REFERENCES item(id),FOREIGN KEY(unit_id) REFERENCES unit(id),PRIMARY KEY (id))"
        // execute the create table script
        sql.execute(createTableScript);
        // query MySQL for the details of the created table
        sql.eachRow('DESCRIBE ' + tableName) { row ->
            println "Fielld = ${row[0]}, type = ${row[1]}"
        }
        // close connection
        sql.close()
    }

    def deleteTable(String tableName) {
        def sql = new Sql(dataSource)
        // the sql script that deletes a table
        def deleteTableScript =  "DROP TABLE "+tableName
        // execute the deletes table script
        sql.execute(deleteTableScript);
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
        deleteTable(material.identityMaterialName)
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
        if (!params.id) {

            // the sql script that creates a table
            createTableScript = "INSERT INTO " + params.identityMaterialName + " (item_id,unit_id,weight,quantity_number,date,stock_type,del_flag ) VALUES ((SELECT id from item WHERE id='" + params.itemId + "' ),(SELECT id from unit WHERE id='" + params.unitId + "' ),'" + params.weight + "','" + params.quantityNumber + "','" + params.date + "','" + params.stockType + "',0)"
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
            createTableScript = "UPDATE " + params.identityMaterialName + " SET item_id = (SELECT id from item WHERE id=" + params.itemId + " ),unit_id = (SELECT id from unit WHERE id=" + params.unitId + " ), weight='"+ params.weight +"', quantity_number='" + params.quantityNumber + "', date='" + params.date + "'  WHERE id=" + id
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
            stock.weight = row[5]
            stock.item = Item.get(row[6])
            stock.unit = Unit.get(row[7])

        }
        sql.close()
        return stock
    }

    def listOfStock(Map params) {
        def sql = new Sql(dataSource)
        def itemId=Item.findByDelFlagAndIdentityItemName(false,params.identityItemName).id
        List<Stock> stockList = new ArrayList<>()
        sql.eachRow('SELECT * FROM ' + params.identityMaterialName + " WHERE del_flag=0 and item_id="+itemId) { row ->
            Stock stock = new Stock()
            stock.id = row[0]
            stock.quantityNumber = row[1]
            stock.delFlag = row[2]
            stock.date = row[3]
            stock.stockType = row[4]
            stock.weight = row[5]
            stock.item = Item.get(row[6])
            stock.unit = Unit.get(row[7])
            if (stock.stockType.equalsIgnoreCase(params.stockType)) {
                stockList.add(stock)
            }
        }
        sql.close()
        return stockList
    }

    def deleteStock(Map params) {
        def sql = new Sql(dataSource)
        def id=params.stock as long
        print id
        String createScript = "UPDATE " + params.identityMaterialName + " SET del_flag=1  WHERE id=" + id
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
            if(stockList[i].stockType == "stock-in"){
                stockList[i].totalStockInNumber=stockList[i].quantityNumber
                stockList[i].totalStockOutNumber=0
            }
            else{
                stockList[i].totalStockOutNumber=stockList[i].quantityNumber
                stockList[i].totalStockInNumber=0
            }
            for (int j = i + 1; j < stockList.size(); j++) {
                if(stockList[j].stockType == "stock-in"){
                    stockList[j].totalStockInNumber=stockList[j].quantityNumber
                    stockList[j].totalStockOutNumber=0
                }
                else{
                    stockList[j].totalStockOutNumber=stockList[j].quantityNumber
                    stockList[j].totalStockInNumber=0
                }
                if (stockList[i].weight == stockList[j].weight && stockList[i].unit.unitName == stockList[j].unit.unitName) {
                    stockList[i].totalStockInNumber+=stockList[j].totalStockInNumber
                    stockList[i].totalStockOutNumber+=stockList[j].totalStockOutNumber
                    stockList.remove(stockList[j])
                    j--
                }
            }
        }
        return stockList

    }

    def totalArrayList(Map params) {
        def sql = new Sql(dataSource)
        def itemId=Item.findByDelFlagAndIdentityItemName(false,params.identityItemName).id
        List<Stock> stockList = new ArrayList<>()
        sql.eachRow('SELECT * FROM ' + params.identityMaterialName + " WHERE del_flag=0 and item_id="+itemId) { row ->
            Stock stock = new Stock()
            stock.id = row[0]
            stock.quantityNumber = row[1]
            stock.delFlag = row[2]
            stock.date = row[3]
            stock.stockType = row[4]
            stock.weight = row[5]
            stock.item = Item.get(row[6])
            stock.unit = Unit.get(row[7])
            stockList.add(stock)
        }
        sql.close()
    return stockList
    }
    def checkItem(Map params){
        try {
            def isAvailable = false
            def itemInstance
            if(!params.identityItemName) {
                itemInstance = Item.findByDelFlagAndItemName(false,params.itemName)
                if (!itemInstance) {
                    isAvailable = true

                }
            }
            else{
                def itemName=Item.findByDelFlagAndIdentityItemName(false,params.identityItemName).itemName
                print itemName
               if(itemName.equalsIgnoreCase(params.itemName)){
                       isAvailable = true

               }
                else{
                   itemInstance = Item.findByItemName(params.itemName)
                   if (!itemInstance) {
                       isAvailable = true

                   }
               }

            }

            return isAvailable
        }
        catch (Exception e){

        }
    }
    def checkUnit(Map params){
        try {
            def isAvailable = false
            def unitInstance = Unit.findByUnitNameAndDelFlag(params.unitName,false)
            if (!unitInstance) {
                isAvailable = true

            }
            return isAvailable
        }
        catch (Exception e){

        }
    }
    def checkMaterial(Map params){
        try {
            def isAvailable = false
            def materialInstance = Material.findByMaterialNameAndDelFlag(params.materialName,false)
            if (!materialInstance) {
                isAvailable = true

            }
            return isAvailable
        }
        catch (Exception e){

        }
    }


}