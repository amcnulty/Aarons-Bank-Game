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
    public int levelNum;
    public boolean[] spawnLevelChests;
    public int[] inventoryIDS;
    public int[] inventoryAmounts;

    private JSONObject json = new JSONObject();
    // array for chests
    private JSONArray jsonArray = new JSONArray();
    // array for inventory ids
    private JSONArray inventoryJsonArray = new JSONArray();
    // array for inventory amounts
    private JSONArray inventoryAmountJsonArray = new JSONArray();
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
        levelNum = level.getLevelNum();
        inventoryIDS = player.getInventoryIDS();
        inventoryAmounts = player.getInventoryAmount();
        spawnLevelChests = Level.spawnLevel.getChestsOnLevel();
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
        json.put("levelNum", levelNum);
        jsonArray.clear();
        inventoryJsonArray.clear();
        inventoryAmountJsonArray.clear();
        for (int i = 0; i < inventoryIDS.length; i++) {
            inventoryJsonArray.add(inventoryIDS[i]);
        }
        for (int i = 0; i < inventoryAmounts.length; i++) {
            inventoryAmountJsonArray.add(inventoryAmounts[i]);
        }
        for (int i = 0; i < spawnLevelChests.length; i++) {
            jsonArray.add(spawnLevelChests[i]);
        }
        json.put("inventoryIDS", inventoryJsonArray);
        json.put("inventoryAmounts", inventoryAmountJsonArray);
        json.put("chests", jsonArray);
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
        levelNum = Integer.parseInt(json.get("levelNum").toString());
        
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
        
        jsonArray = (JSONArray) json.get("chests");
        spawnLevelChests = new boolean[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i).toString().equals("true")) {
                spawnLevelChests[i] = true;
            }
            else spawnLevelChests[i] = false;
        }
    }
}