/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.saves;

import bank.entity.mob.Player;
import bank.level.Level;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Aaron
 */
public class Save implements Serializable{
    private static final long serialVersionUID = -1088680233475794700L;

    public int playerX, playerY;
    public int playerDir;
    public int playerNum;
    public String playerName;
    public int cash;
    public int playerLevel;
    public int health;
    public int attack;
    public int defence;
    public int speed;
    public int totalRef;
    public int aRef;
    public int usedRef;
    public int levelNum;
    
    public boolean[] sidewaysLevelNpcs;
    public int[] sidewaysLevelNpcX;
    public int[] sidewaysLevelNpcY;
    
    public boolean[] swampLevelNpcs;    // new
    public int[] swampLevelNpcX;        // new
    public int[] swampLevelNpcY;        // new
    
    public boolean[] spawnLevelChests;
    public boolean[] underGroundCrazyLevelChests;
    public boolean[] mazeLevelChests;               // new
    public boolean[] swampLevelChests;              // new
    public boolean[] crazyLevelChests;              // new
    public boolean[] duplexLevelChests;              // new
    public boolean[] biggerHouseLevelChests;      // new
    
    
    public int equipedWeapon;
    public int[] equipedArmor;
    public int[] inventoryIDS;
    public int[] inventoryAmounts;
    
    // main json object that saves to the user.home\bank_save_games directory
    private JSONObject json = new JSONObject();
    // array for spawnlevel chests
    private JSONArray spawnLevelChestsJsonArray = new JSONArray();
    // array for underground crazy level chests
    private JSONArray underGroundCrazyLevelChestsJsonArray = new JSONArray();
    private JSONArray mazeLevelChestsJsonArray = new JSONArray();
    private JSONArray swampLevelChestsJsonArray = new JSONArray();
    private JSONArray crazyLevelChestsJsonArray = new JSONArray();
    private JSONArray dJsonArray = new JSONArray();
    private JSONArray bJsonArray = new JSONArray();
    // array for equiped armor
    private JSONArray equipedArmorJsonArray = new JSONArray();
    // array for inventory ids
    private JSONArray inventoryJsonArray = new JSONArray();
    // array for inventory amounts
    private JSONArray inventoryAmountJsonArray = new JSONArray();
    // array for npcs moved out of the way boolean
    private JSONArray sidewaysLevelNpcMovedJsonArray = new JSONArray();
    // array for npcs xloc
    private JSONArray sidewaysLevelNPCXJsonArray = new JSONArray();
    // array for npcs yloc
    private JSONArray sidewaysLevelNPCYJsonArray = new JSONArray();
    // array for npcs moved out of the way boolean in swamp level
    private JSONArray swampLevelNpcMovedJsonArray = new JSONArray();
    // array for npcs xlco on swamp level
    private JSONArray swampLevelNPCXJsonArray = new JSONArray();
    // array for npcs yloc on swamp level
    private JSONArray swampLevelNPCYJsonArray = new JSONArray();
    private String fileName;

    public void save(Player player, Level level) {
        playerX = player.x;
        playerY = player.y;
        playerDir = player.dir;
        playerNum = player.playerNum;
        playerName = player.name;
        cash = player.cash;
        playerLevel = player.playerLevel;
        health = player.health;
        attack = player.attack;
        defence = player.defence;
        speed = player.speed;
        totalRef = player.totalReferrals;
        aRef = player.availableReferrals;
        usedRef = player.usedReferrals;
        sidewaysLevelNpcs = Level.sidewaysHouseLevel.getNpcBoolean();
        sidewaysLevelNpcX = Level.sidewaysHouseLevel.getNpcX();
        sidewaysLevelNpcY = Level.sidewaysHouseLevel.getNpcY();
        swampLevelNpcs = Level.swampLevel.getNpcBoolean();
        swampLevelNpcX = Level.swampLevel.getNpcX();
        swampLevelNpcY = Level.swampLevel.getNpcY();
        levelNum = level.getLevelNum();
        if (player.equipedWeapon == null) {
            equipedWeapon = 0;
        }
        else equipedWeapon = player.equipedWeapon.itemID;
        equipedArmor = new int[3];
        for (int i = 0; i < 3; i++) {
            if (player.equipedArmor[i] == null) {
                equipedArmor[i] = 0;
            }
            else equipedArmor[i] = player.equipedArmor[i].itemID;
        }
        inventoryIDS = player.getInventoryIDS();
        inventoryAmounts = player.getInventoryAmount();
        spawnLevelChests = Level.spawnLevel.getChestsOnLevel();
        underGroundCrazyLevelChests = Level.underGroundCrazyLevel.getChestsOnLevel();
        mazeLevelChests = Level.mazeLevel.getChestsOnLevel();
        swampLevelChests = Level.swampLevel.getChestsOnLevel();
        crazyLevelChests = Level.crazyLevel.getChestsOnLevel();
        duplexLevelChests = Level.duplexLevel.getChestsOnLevel();
        biggerHouseLevelChests = Level.biggerHouseUpstairsLevel.getChestsOnLevel();
        encode();
        switch (player.playerNum) {
        case 1:
            fileName = "/playerOne.JSON";
            break;
        case 2:
            fileName = "/playerTwo.JSON";
            break;
        case 3:
            fileName = "/playerThree.JSON";
            break;
        case 4:
            fileName = "/playerFour.JSON";
            break;
        case 5:
            fileName = "/playerFive.JSON";
            break;
        }
        try {
            File folder = new File(System.getProperty("user.home"), "bank_game_saves");
            if (!folder.exists() && !folder.mkdirs()) {
                throw new RuntimeException("Failed to create save directory.");
            }
            File myFile = new File(folder, fileName);
            PrintWriter outputFile = new PrintWriter(myFile);
            outputFile.println(json);
            outputFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void encode() {
        json.put("playerX", playerX);
        json.put("playerY", playerY);
        json.put("playerDir", playerDir);
        json.put("playerNum", playerNum);
        json.put("playerName", playerName);
        json.put("cash", cash);
        json.put("playerLevel", playerLevel);
        json.put("health", health);
        json.put("attack", attack);
        json.put("defence", defence);
        json.put("speed", speed);
        json.put("totalReferrals", totalRef);
        json.put("availableReferrals", aRef);
        json.put("usedReferrals", usedRef);
        json.put("levelNum", levelNum);
        json.put("equipedWeapon", equipedWeapon);
        
        // everything down here is for arrays
        sidewaysLevelNpcMovedJsonArray.clear();
        sidewaysLevelNPCXJsonArray.clear();
        sidewaysLevelNPCYJsonArray.clear();
        swampLevelNpcMovedJsonArray.clear();
        swampLevelNPCXJsonArray.clear();
        swampLevelNPCYJsonArray.clear();
        spawnLevelChestsJsonArray.clear();
        underGroundCrazyLevelChestsJsonArray.clear();
        mazeLevelChestsJsonArray.clear();
        swampLevelChestsJsonArray.clear();
        crazyLevelChestsJsonArray.clear();
        dJsonArray.clear();
        bJsonArray.clear();
        equipedArmorJsonArray.clear();
        inventoryJsonArray.clear();
        inventoryAmountJsonArray.clear();
        for (int i = 0; i < equipedArmor.length; i++) {
            equipedArmorJsonArray.add(equipedArmor[i]);
        }
        for (int i = 0; i < inventoryIDS.length; i++) {
            inventoryJsonArray.add(inventoryIDS[i]);
        }
        for (int i = 0; i < inventoryAmounts.length; i++) {
            inventoryAmountJsonArray.add(inventoryAmounts[i]);
        }
        for (int i = 0; i < spawnLevelChests.length; i++) {
            spawnLevelChestsJsonArray.add(spawnLevelChests[i]);
        }
        for (int i = 0; i < underGroundCrazyLevelChests.length; i++) {
            underGroundCrazyLevelChestsJsonArray.add(underGroundCrazyLevelChests[i]);
        }
        for (int i = 0; i < mazeLevelChests.length; i++) {
            mazeLevelChestsJsonArray.add(mazeLevelChests[i]);
        }
        for (int i = 0; i < swampLevelChests.length; i++) {
            swampLevelChestsJsonArray.add(swampLevelChests[i]);
        }
        for (int i = 0; i < crazyLevelChests.length; i++) {
            crazyLevelChestsJsonArray.add(crazyLevelChests[i]);
        }
        for (int i = 0; i < duplexLevelChests.length; i++) {
            dJsonArray.add(duplexLevelChests[i]);
        }
        for (int i = 0; i < biggerHouseLevelChests.length; i++) {
            bJsonArray.add(biggerHouseLevelChests[i]);
        }
        for (int i = 0; i < sidewaysLevelNpcs.length; i++) {
            sidewaysLevelNpcMovedJsonArray.add(sidewaysLevelNpcs[i]);
        }
        for (int i = 0; i < sidewaysLevelNpcs.length; i++) {
            sidewaysLevelNPCXJsonArray.add(sidewaysLevelNpcX[i]);
        }
        for (int i = 0; i < sidewaysLevelNpcs.length; i++) {
            sidewaysLevelNPCYJsonArray.add(sidewaysLevelNpcY[i]);
        }
        for (int i = 0; i < swampLevelNpcs.length; i++) {
            swampLevelNpcMovedJsonArray.add(swampLevelNpcs[i]);
        }
        for (int i = 0; i < swampLevelNpcs.length; i++) {
            swampLevelNPCXJsonArray.add(swampLevelNpcX[i]);
        }
        for (int i = 0; i < swampLevelNpcs.length; i++) {
            swampLevelNPCYJsonArray.add(swampLevelNpcY[i]);
        }
        json.put("sidewaysLevelNpcs", sidewaysLevelNpcMovedJsonArray);
        json.put("sidewaysLevelNpcX", sidewaysLevelNPCXJsonArray);
        json.put("sidewaysLevelNpcY", sidewaysLevelNPCYJsonArray);
        json.put("swampLevelNpcs", swampLevelNpcMovedJsonArray);
        json.put("swampLevelNpcX", swampLevelNPCXJsonArray);
        json.put("swampLevelNpcY", swampLevelNPCYJsonArray);
        json.put("equipedArmor", equipedArmorJsonArray);
        json.put("inventoryIDS", inventoryJsonArray);
        json.put("inventoryAmounts", inventoryAmountJsonArray);
        json.put("spawnLevelChests", spawnLevelChestsJsonArray);
        json.put("underGroundCrazyLevelChests", underGroundCrazyLevelChestsJsonArray);
        json.put("mazeLevelChests", mazeLevelChestsJsonArray);
        json.put("swampLevelChests", swampLevelChestsJsonArray);
        json.put("crazyLevelChests", crazyLevelChestsJsonArray);
        json.put("duplexLevelChests", dJsonArray);
        json.put("biggerHouseLevelChests", bJsonArray);
        
    }
    
    public void decode(int playerNum) {
        switch (playerNum) {
        case 1:
            fileName = "/playerOne.JSON";
            break;
        case 2:
            fileName = "/playerTwo.JSON";
            break;
        case 3:
            fileName = "/playerThree.JSON";
            break;
        case 4:
            fileName = "/playerFour.JSON";
            break;
        case 5:
            fileName = "/playerFive.JSON";
            break;
        }
        try {
            File folder = new File(System.getProperty("user.home"), "bank_game_saves");
            File myFile = new File(folder, fileName);
            Scanner inputFile = new Scanner(myFile);
            String JSONText = inputFile.nextLine();
            json = (JSONObject) JSONValue.parse(JSONText);
            inputFile.close();
            resetFields();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void resetFields() {
        playerY = Integer.parseInt(json.get("playerY").toString());
        playerX = Integer.parseInt(json.get("playerX").toString());
        playerDir = Integer.parseInt(json.get("playerDir").toString());
        playerNum = Integer.parseInt(json.get("playerNum").toString());
        playerName = json.get("playerName").toString();
        cash = Integer.parseInt(json.get("cash").toString());
        playerLevel = Integer.parseInt(json.get("playerLevel").toString());
        health = Integer.parseInt(json.get("health").toString());
        attack = Integer.parseInt(json.get("attack").toString());
        defence = Integer.parseInt(json.get("defence").toString());
        speed = Integer.parseInt(json.get("speed").toString());
        totalRef = Integer.parseInt(json.get("totalReferrals").toString());
        aRef = Integer.parseInt(json.get("availableReferrals").toString());
        usedRef = Integer.parseInt(json.get("usedReferrals").toString());
        levelNum = Integer.parseInt(json.get("levelNum").toString());
        equipedWeapon = Integer.parseInt(json.get("equipedWeapon").toString());
        
        // everything down here is for arrays
        
        equipedArmorJsonArray = (JSONArray) json.get("equipedArmor");
        equipedArmor = new int[equipedArmorJsonArray.size()];
        for (int i = 0; i < equipedArmorJsonArray.size(); i++) {
            equipedArmor[i] = Integer.parseInt(equipedArmorJsonArray.get(i).toString());
        }
        
        inventoryJsonArray = (JSONArray) json.get("inventoryIDS");
        inventoryIDS = new int[inventoryJsonArray.size()];
        for (int i = 0; i < inventoryJsonArray.size(); i++) {
            inventoryIDS[i] = Integer.parseInt(inventoryJsonArray.get(i).toString());
        }
        
        inventoryAmountJsonArray = (JSONArray) json.get("inventoryAmounts");
        inventoryAmounts = new int[inventoryAmountJsonArray.size()];
        for (int i = 0; i < inventoryAmountJsonArray.size(); i++) {
            inventoryAmounts[i] = Integer.parseInt(inventoryAmountJsonArray.get(i).toString());
        }
        
        spawnLevelChestsJsonArray = (JSONArray) json.get("spawnLevelChests");
        spawnLevelChests = new boolean[spawnLevelChestsJsonArray.size()];
        for (int i = 0; i < spawnLevelChestsJsonArray.size(); i++) {
            if (spawnLevelChestsJsonArray.get(i).toString().equals("true")) {
                spawnLevelChests[i] = true;
            }
            else spawnLevelChests[i] = false;
        }
        
        underGroundCrazyLevelChestsJsonArray = (JSONArray) json.get("underGroundCrazyLevelChests");
        underGroundCrazyLevelChests = new boolean[underGroundCrazyLevelChestsJsonArray.size()];
        for (int i = 0; i < underGroundCrazyLevelChestsJsonArray.size(); i++) {
            if (underGroundCrazyLevelChestsJsonArray.get(i).toString().equals("true")) {
                underGroundCrazyLevelChests[i] = true;
            }
            else underGroundCrazyLevelChests[i] = false;
        }
        
        mazeLevelChestsJsonArray = (JSONArray) json.get("mazeLevelChests");
        mazeLevelChests = new boolean[mazeLevelChestsJsonArray.size()];
        for (int i = 0; i < mazeLevelChestsJsonArray.size(); i++) {
            if (mazeLevelChestsJsonArray.get(i).toString().equals("true")) {
                mazeLevelChests[i] = true;
            }
            else mazeLevelChests[i] = false;
        }
        
        swampLevelChestsJsonArray = (JSONArray) json.get("swampLevelChests");
        swampLevelChests = new boolean[swampLevelChestsJsonArray.size()];
        for (int i = 0; i < swampLevelChestsJsonArray.size(); i++) {
            if (swampLevelChestsJsonArray.get(i).toString().equals("true")) {
                swampLevelChests[i] = true;
            }
            else swampLevelChests[i] = false;
        }
        
        crazyLevelChestsJsonArray = (JSONArray) json.get("crazyLevelChests");
        crazyLevelChests = new boolean[crazyLevelChestsJsonArray.size()];
        for (int i = 0; i < crazyLevelChestsJsonArray.size(); i++) {
            if (crazyLevelChestsJsonArray.get(i).toString().equals("true")) {
                crazyLevelChests[i] = true;
            }
            else crazyLevelChests[i] = false;
        }
        
        dJsonArray = (JSONArray) json.get("duplexLevelChests");
        duplexLevelChests = new boolean[dJsonArray.size()];
        for (int i = 0; i < dJsonArray.size(); i++) {
            if (dJsonArray.get(i).toString().equals("true")) {
                duplexLevelChests[i] = true;
            }
            else duplexLevelChests[i] = false;
        }
        
        bJsonArray = (JSONArray) json.get("biggerHouseLevelChests");
        biggerHouseLevelChests = new boolean[bJsonArray.size()];
        for (int i = 0; i < bJsonArray.size(); i++) {
            if (bJsonArray.get(i).toString().equals("true")) {
                biggerHouseLevelChests[i] = true;
            }
            else biggerHouseLevelChests[i] = false;
        }
        
        sidewaysLevelNpcMovedJsonArray = (JSONArray) json.get("sidewaysLevelNpcs");
        sidewaysLevelNpcs = new boolean[sidewaysLevelNpcMovedJsonArray.size()];
        for (int i = 0; i < sidewaysLevelNpcMovedJsonArray.size(); i++) {
            sidewaysLevelNpcs[i] = Boolean.parseBoolean(sidewaysLevelNpcMovedJsonArray.get(i).toString());
        }
        sidewaysLevelNPCXJsonArray = (JSONArray) json.get("sidewaysLevelNpcX");
        sidewaysLevelNpcX = new int[sidewaysLevelNPCXJsonArray.size()];
        for (int i = 0; i < sidewaysLevelNPCXJsonArray.size(); i++) {
            sidewaysLevelNpcX[i] = Integer.parseInt(sidewaysLevelNPCXJsonArray.get(i).toString());
        }
        sidewaysLevelNPCYJsonArray = (JSONArray) json.get("sidewaysLevelNpcY");
        sidewaysLevelNpcY = new int[sidewaysLevelNPCYJsonArray.size()];
        for (int i = 0; i < sidewaysLevelNPCYJsonArray.size(); i++) {
            sidewaysLevelNpcY[i] = Integer.parseInt(sidewaysLevelNPCYJsonArray.get(i).toString());
        }
        
        swampLevelNpcMovedJsonArray = (JSONArray) json.get("swampLevelNpcs");
        swampLevelNpcs = new boolean[swampLevelNpcMovedJsonArray.size()];
        for (int i = 0; i < swampLevelNpcMovedJsonArray.size(); i++) {
            swampLevelNpcs[i] = Boolean.parseBoolean(swampLevelNpcMovedJsonArray.get(i).toString());
        }
        swampLevelNPCXJsonArray = (JSONArray) json.get("swampLevelNpcX");
        swampLevelNpcX = new int[swampLevelNPCXJsonArray.size()];
        for (int i = 0; i < swampLevelNPCXJsonArray.size(); i++) {
            swampLevelNpcX[i] = Integer.parseInt(swampLevelNPCXJsonArray.get(i).toString());
        }
        swampLevelNPCYJsonArray = (JSONArray) json.get("swampLevelNpcY");
        swampLevelNpcY = new int[swampLevelNPCYJsonArray.size()];
        for (int i = 0; i < swampLevelNPCYJsonArray.size(); i++) {
            swampLevelNpcY[i] = Integer.parseInt(swampLevelNPCYJsonArray.get(i).toString());
        }
    }
}
