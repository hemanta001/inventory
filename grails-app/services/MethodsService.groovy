import grails.transaction.Transactional
import groovy.sql.Sql

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.regex.Matcher
import java.util.regex.Pattern


@Transactional
class MethodsService {
    private static Pattern dateFrmtPtrn = Pattern.compile("((?:19|20)\\d\\d)-(0?[1-9]|1[012])-([12][0-9]|3[01]|0?[1-9])")
    def dataSource
def renameTableName(String previousTableName,presentTableName){
    def sql = new Sql(dataSource)
    // the sql script that creates a table
    // execute the create table script
    def renameTableName="ALTER TABLE "+previousTableName+ " RENAME TO "+presentTableName
    sql.execute(renameTableName)
}
    def saveMaterial(Map params) {
        if (params.identityMaterialName) {
            def material = Material.findByDelFlagAndIdentityMaterialName(false, params.identityMaterialName)
            if(material){
            def previousTableName=material.identityMaterialName
            material.materialName = params.materialName
            material.identityMaterialName = convertToOriginalUrl(material.materialName)
            renameTableName(previousTableName,material.identityMaterialName)
            material.save(flush: true)}
            return material
        } else {
            def material = new Material()
            material.materialName = params.materialName
            material.identityMaterialName = convertToOriginalUrl(material.materialName)
            material.delFlag = false
            material.save(flush: true)
            createTable(material.identityMaterialName)
            return material
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
        if(material){
        material.delFlag = true
        material.save(flush: true)}
        return material
//        deleteTable(material.identityMaterialName)
    }

    def listOfMaterials() {
        def materialsList = Material.findAllByDelFlag(false)
        return materialsList
    }

    def saveItem(Map params) {
        if (params.identityItemName) {
            def item = Item.findByDelFlagAndIdentityItemName(false, params.identityItemName)
            if(item){
            item.itemName = params.itemName
            item.identityItemName = convertToOriginalUrl(item.itemName)
            item.save(flush: true)}
            return item
        } else {
            def item = new Item()
            item.itemName = params.itemName
            item.identityItemName = convertToOriginalUrl(params.itemName)
            item.delFlag = false
            item.save(flush: true)
            return item
        }
    }

    def showItem(String identityItemName) {
        def item = Item.findByDelFlagAndIdentityItemName(false, identityItemName)
        return item
    }

    def deleteItem(String identityItemName) {
        def item = Item.findByDelFlagAndIdentityItemName(false, identityItemName)
        if(item){
        item.delFlag = true
        item.save(flush: true)}
        return item
    }

    def listOfItem() {
        def itemList = Item.findAllByDelFlag(false)
        return itemList
    }

    def saveUnit(Map params) {
        if (params.identityUnitName) {
            def unit = Unit.findByDelFlagAndIdentityUnitName(false, params.identityUnitName)
            if(unit){
            unit.unitName = params.unitName
            unit.identityUnitName = convertToOriginalUrl(params.unitName)
            unit.save(flush: true)}
            return unit
        } else {
            def unit = new Unit()
            unit.unitName = params.unitName
            unit.identityUnitName = convertToOriginalUrl(params.unitName)
            unit.delFlag = false
            unit.save(flush: true)
            return unit
        }
    }

    def showUnit(String identityUnitName) {
        def unit = Unit.findByDelFlagAndIdentityUnitName(false, identityUnitName)
        return unit
    }

    def deleteUnit(String identityUnitName) {
        def unit = Unit.findByDelFlagAndIdentityUnitName(false, identityUnitName)
        if(unit){
        unit.delFlag = true
        unit.save(flush: true)
        }
        return unit
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
            id = params.id
            Stock stock = showStock(params.identityMaterialName, id)

            if(stock){
            createTableScript = "UPDATE " + params.identityMaterialName + " SET item_id = (SELECT id from item WHERE id=" + params.itemId + " ),unit_id = (SELECT id from unit WHERE id=" + params.unitId + " ), weight='"+ params.weight +"', quantity_number='" + params.quantityNumber + "', date='" + params.date + "'  WHERE id=" + id
            // execute the create table script
            sql.execute(createTableScript)
            // close connection
            sql.close()
            array = [stock, params.identityMaterialName]
        }
            return array

        }
    }

    def showStock(String tableName, Long id) {
        def sql = new Sql(dataSource)
        Stock stock = null
        sql.eachRow('SELECT * FROM ' + tableName + ' WHERE id=' + id) { row ->
            if(!row[2]) {
                stock = new Stock();
                stock.id = row[0]
                stock.quantityNumber = row[1]
                stock.delFlag = row[2]
                stock.date = row[3]
                stock.stockType = row[4]
                stock.weight = row[5]
                stock.item = Item.get(row[6])
                stock.unit = Unit.get(row[7])}
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
    def checkEmail(Map params){
        try {
            def isAvailable = false
            def userInstance = User.findByEmailAndDelFlag(params.email,false)
            if (!userInstance) {
                isAvailable = true

            }
            return isAvailable
        }
        catch (Exception e){

        }
    }

    def checkUserName(Map params){
        try {
            def isAvailable = false
            def userInstance = User.findByUserNameAndDelFlag(params.userName,false)
            if (!userInstance) {
                isAvailable = true

            }
            return isAvailable
        }
        catch (Exception e){

        }
    }
def checkInteger(Map params){
    def isInteger=false
    def quantityNumber=params.quantityNumber
    try{
    Integer.parseInt(quantityNumber)
        isInteger=true
    }
    catch (Exception e){

    }
    return isInteger
}
    def checkFloat(Map params){
        def isFloat=false
        def string=params.weight
        if (string.matches("[-+]?[0-9]*\\.?[0-9]+")) { // You can use the `\\d` instead of `0-9` too!
            isFloat=true
        }
        return isFloat
    }
    def saveUser(Map params){
        User userInstance=null
if(params.userNameId){
    userInstance=User.findByUserName(params.userNameId)
    if(userInstance){
    userInstance.firstName=params.firstName
    userInstance.lastName=params.lastName
    if(params.password){
    userInstance.password=encryptedPassword(params.password)
    }
    userInstance.contactNumber=params.contactNumber
    userInstance.role=params.role
}}
        else{
    userInstance=new User()
    userInstance.firstName=params.firstName
    userInstance.lastName=params.lastName
    userInstance.email=params.email
    userInstance.userName=params.userName
    userInstance.password=encryptedPassword(params.password)
    userInstance.contactNumber=params.contactNumber
    userInstance.role=params.role
    userInstance.delFlag=false
}
        return userInstance
    }
    def updateUser(User userInstance,Map params){
        userInstance.firstName=params.firstName
        userInstance.lastName=params.lastName
        if(params.password){
            userInstance.password=encryptedPassword(params.password)
        }
        userInstance.contactNumber=params.contactNumber
        return userInstance
    }
    def showUser(Map params){
        def userInstance=User.findByUserName(params.userName)
return userInstance
    }
    def listOfUser(){
def userList=User.findAllByDelFlag(false)
        return userList
    }
    def deleteUser(Map params){
        def userInstance=User.findByUserName(params.userName)
 userInstance.delete(flush: true)
    }
    def encryptedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String originalPassword = password;
        String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
        return generatedSecuredPasswordHash
    }

    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }


    }
    def decryptPassword(String inputPassword,String encryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String  originalPassword = inputPassword;
        boolean matched = validatePassword(originalPassword, encryptedPassword);
        return matched
    }
    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    def updatePassword(User userInstance,Map params){
        userInstance.password=encryptedPassword(params.newPassword)
        userInstance.save(flush: true)
    }
    def getNumberOfDays(){
        Map<Integer, int[]> nepaliMap = new HashMap<Integer, int[]>();
int[] array1=[0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 ]
        int[] array2
        int[] array3
        int[] array4
        int[] array5
        int[] array6
        int[] array7
        int[] array8
        int[] array9
        int[] array10
        int[] array11
        int[] array12
        int[] array13
        int[] array14
        int[] array15
        int[] array16
        int[] array17
        int[] array18
        int[] array19
        int[] array20
        int[] array21
        int[] array22
        int[] array23
        int[] array24
        int[] array25
        int[] array26
        int[] array27
        int[] array28
        int[] array29
        int[] array30
        int[] array31
        int[] array32
        int[] array33
        int[] array34
        int[] array35
        int[] array36
        int[] array37
        int[] array38
        int[] array39
        int[] array40
        int[] array41
        int[] array42
        int[] array43
        int[] array44
        int[] array45
        int[] array46
        int[] array47
        int[] array48
        int[] array49
        int[] array50
        int[] array51
        int[] array52
        int[] array53
        int[] array54
        int[] array55
        int[] array56
        int[] array57
        int[] array58
        int[] array59
        int[] array60
        int[] array61
        int[] array62
        int[] array63
        int[] array64
        int[] array65
        int[] array66
        int[] array67
        int[] array68
        int[] array69
        int[] array70
        int[] array71
        int[] array72
        int[] array73
        int[] array74
        int[] array75=[ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30]
        int[] array76=[ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30]
        int[] array77=[ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31]
        int[] array78
        int[] array79
        int[] array80
        int[] array81
        int[] array82
        int[] array83
        int[] array84
        int[] array85
        int[] array86
        int[] array87
        int[] array89
        int[] array90
        int[] array91

        nepaliMap.put(2000,array1);
//        nepaliMap.put(2001, [0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2002, [  0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2003, [  0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2004, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31] );
//        nepaliMap.put(2005, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2006, [ 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2007, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2008, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31] );
//        nepaliMap.put(2009, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2010, [ 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2011, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2012, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30 ]);
//        nepaliMap.put(2013, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2014, [ 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2015, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2016, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30] );
//        nepaliMap.put(2017, [0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2018, [ 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2019, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31] );
//        nepaliMap.put(2020, [ 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2021, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2022, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30] );
//        nepaliMap.put(2023, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 ]);
//        nepaliMap.put(2024, [ 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2025, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2026, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 ]);
//        nepaliMap.put(2027, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 ]);
//        nepaliMap.put(2028, [0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2029, [ 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2030, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2031, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31] );
//        nepaliMap.put(2032, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2033, [0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2034, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2035, [ 0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31] );
//        nepaliMap.put(2036, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2037, [ 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2038, [0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2039, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30] );
//        nepaliMap.put(2040, [0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2041, [ 0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2042,[ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 ]);
//        nepaliMap.put(2043, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30] );
//        nepaliMap.put(2044, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2045, [ 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2046, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2047,[ 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2048, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2049, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 ]);
//        nepaliMap.put(2050, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 ]);
//        nepaliMap.put(2051, [0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2052, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2053, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30 ]);
//        nepaliMap.put(2054, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31] );
//        nepaliMap.put(2055, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2056, [ 0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2057, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31 ]);
//        nepaliMap.put(2058, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31 ]);
//        nepaliMap.put(2059, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2060, [0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2061, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31]);
//        nepaliMap.put(2062, [ 0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31] );
//        nepaliMap.put(2063, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2064, [0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2065, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2066, [ 0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31] );
//        nepaliMap.put(2067, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2068, [0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2069, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2070, [0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30] );
//        nepaliMap.put(2071, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2072, [ 0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2073, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31] );
//        nepaliMap.put(2074, [ 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
        nepaliMap.put(2075,array75  );
        nepaliMap.put(2076, array76 );
        nepaliMap.put(2077, array77);
//        nepaliMap.put(2078, [ 0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30] );
//        nepaliMap.put(2079, [ 0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30 ]);
//        nepaliMap.put(2080, [ 0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30] );
//        nepaliMap.put(2081, [ 0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2082, [0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2083, [ 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2084, [ 0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2085, [ 0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2086, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2087, [ 0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2088, [ 0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30] );
//        nepaliMap.put(2089, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 ]);
//        nepaliMap.put(2090, [ 0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30 ]);
        int daysInIthMonth = nepaliMap.get(2075)[3]
        print "yessssssssssssssss "+daysInIthMonth
    }
    }

