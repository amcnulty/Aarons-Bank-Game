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
                System.err.println("ERROR! YOU HAVE NOT MADE A CASE FOR THIS ITEM. From getItemFromID in Items class");
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
