/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.inventory;

import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Items {
    
    public int amount = 1;
    public int itemID;
    public Sprite itemSprite;
    public int standardPrice;
    public String itemName;
    
    public static int STEEL_TOE_BOOTS = 1;
    public static int HEAVY_SWORD = 2;
    public static int DRINKING_WATER = 3;
    public static int BANANA = 4;
    public static int SPEED_POTION = 5;
    public static int DEFENCE_POTION = 6;
    public static int ATTACK_POTION = 7;
    public static int SPECIAL_CANDY = 8;
    public static int CHEESE = 9;
    public static int TRAINING_BOOK = 10;
    public static int DRUMSTICK = 11;
    public static int COOKIE = 12;
    public static int FISH = 13;
    public static int LV2_TRAINING_BOOK = 14;
    public static int CHERRIES = 15;
    public static int EGG = 16;
    public static int DELUXE_SWORD = 17;
    public static int SPEED_SWORD = 18;
    public static int FLAME_SWORD = 19;
    public static int ICE_SWORD = 20;
    public static int SPARK_SWORD = 21;
    public static int DRAGON_SWORD = 22;
    public static int CRYSTAL_SWORD = 23;
    public static int BLOOD_SWORD = 24;
    public static int BATTLE_AXE = 25;
    public static int BLOOD_DAGGER = 26;
    public static int BLACK_DAGGER = 27;
    public static int DELUXE_DAGGER = 28;
    public static int POISON_DAGGER = 29;
    public static int LEATHER_BOOTS = 30;
    public static int LEATHER_HELMET = 31;
    public static int LEATHER_TUNIC = 32;
    public static int HARD_LEATHER_BOOTS = 33;
    public static int HARD_LEATHER_HELMET = 34;
    public static int HARD_LEATHER_TUNIC = 35;
    public static int IRON_BOOTS = 36;
    public static int IRON_HELMET = 37;
    public static int IRON_CHESTPLATE = 38;
    public static int GOLD_BOOTS = 39;
    public static int GOLD_HELMET = 40;
    public static int GOLD_CHESTPLATE = 41;
    public static int CORBINS_SHEILD = 42;

    // constructor makes static items in the game
    //public static Items steelToeBoots = new ArmorItem(1, Sprite.steel_toe_boots, 2000, "STEEL TOE BOOTS");
    //public static Items heavySword = new WeaponItem(2, Sprite.heavy_sword, 6000, "HEAVY SWORD");
    //public static Items drinkingWater = new UsableItem(3, Sprite.drinking_water, 20, "DRINKING WATER");
    // parameters = itemID, Sprite, standardPrice
    public Items(int itemID, Sprite sprite, int standardPrice, String itemName) {
        this.itemID = itemID;
        this.itemSprite = sprite;
        this.standardPrice = standardPrice;
        this.itemName = itemName;
    }
    
    public Items getItemFromID(int itemID) {
        switch (itemID) {
            case 1:
                return ArmorItem.steelToeBoots;
            case 2:
                return WeaponItem.heavySword;
            case 3:
                return UsableItem.drinkingWater;
            case 4:
                return UsableItem.banana;
            case 5:
                return UsableItem.speed_potion;
            case 6:
                return UsableItem.defence_potion;
            case 7:
                return UsableItem.attack_potion;
            case 8:
                return UsableItem.special_candy;
            case 9:
                return UsableItem.cheese;
            case 10:
                return UsableItem.training_book;
            case 11:
                return UsableItem.drumstick;
            case 12:
                return UsableItem.cookie;
            case 13:
                return UsableItem.fish;
            case 14:
                return UsableItem.lv2_training_book;
            case 15:
                return UsableItem.cherries;
            case 16:
                return UsableItem.egg;
            case 17:
                return WeaponItem.deluxeSword;
            case 18:
                return WeaponItem.speedSword;
            case 19:
                return WeaponItem.flameSword;
            case 20:
                return WeaponItem.iceSword;
            case 21:
                return WeaponItem.sparkSword;
            case 22:
                return WeaponItem.dragonSword;
            case 23:
                return WeaponItem.crystalSword;
            case 24:
                return WeaponItem.bloodSword;
            case 25:
                return WeaponItem.battleAxe;
            case 26:
                return WeaponItem.bloodDagger;
            case 27:
                return WeaponItem.blackDagger;
            case 28:
                return WeaponItem.deluxeDagger;
            case 29:
                return WeaponItem.poisonDagger;
            case 30:
                return ArmorItem.leatherBoots;
            case 31:
                return ArmorItem.leatherhelmet;
            case 32:
                return ArmorItem.leatherTunic;
            case 33:
                return ArmorItem.hardLeatherBoots;
            case 34:
                return ArmorItem.hardLeatherHelmet;
            case 35:
                return ArmorItem.hardLeatherTunic;
            case 36:
                return ArmorItem.ironBoots;
            case 37:
                return ArmorItem.ironHelmet;
            case 38:
                return ArmorItem.ironChestplate;
            case 39:
                return ArmorItem.goldBoots;
            case 40:
                return ArmorItem.goldHelmet;
            case 41:
                return ArmorItem.goldChestplate;
            case 42:
                return ArmorItem.corbinsSheild;
            default:
                System.err.println("ERROR! YOU HAVE NOT MADE A CASE FOR ITEM ID NUMBER " + itemID + ". From getItemFromID in Items class");
        }
            
        return null;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public int getHealthPoints() {
        return 0;
    }
    
    public int getSpeedChange() {
        return 0;
    }
    
    public int getAttackChange() {
        return 0;
    }
    
    public int getDefenceChange() {
        return 0;
    }
    
    public int getLevelChange() {
        return 0;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void incrementAmount() {
        this.amount++;
    }
    
    public void decrementAmount() {
        this.amount--;
    }
}
