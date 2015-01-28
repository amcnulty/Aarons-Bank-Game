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
public class ArmorItem extends Items {
    
    private int protection;
    private int speedChange;
    
    public static ArmorItem steelToeBoots = new ArmorItem(1, Sprite.steel_toe_boots, 1000, "STEEL TOE BOOTS", 8, -4);
    public static ArmorItem leatherBoots = new ArmorItem(30, Sprite.leather_boots, 600, "LEATHER BOOTS", 5, -2);
    public static ArmorItem leatherhelmet = new ArmorItem(31, Sprite.leather_helmet, 1200, "LEATHER HELMET", 15, 0);
    public static ArmorItem leatherTunic = new ArmorItem(32, Sprite.leather_tunic, 2500, "LEATHER TUNIC", 20, -8);
    public static ArmorItem hardLeatherBoots = new ArmorItem(33, Sprite.hard_leather_boots, 1200, "HARD LEATHER BOOTS", 10, -4);
    public static ArmorItem hardLeatherHelmet = new ArmorItem(34, Sprite.hard_leather_helmet, 2400, "HARD LEATHER HELMET", 20, 0);
    public static ArmorItem hardLeatherTunic = new ArmorItem(35, Sprite.hard_leather_tunic, 5000, "HARD LEATHER TUNIC", 35, -8);
    public static ArmorItem ironBoots = new ArmorItem(36, Sprite.iron_boots, 2000, "IRON BOOTS", 5, 2);
    public static ArmorItem ironHelmet = new ArmorItem(37, Sprite.iron_helmet, 2000, "IRON HELMET", 5, 2);
    public static ArmorItem ironChestplate = new ArmorItem(38, Sprite.iron_chestplate, 2000, "IRON CHESTPLATE", 5, 2);
    public static ArmorItem goldBoots = new ArmorItem(39, Sprite.gold_boots, 2000, "GOLD BOOTS", 5, 2);
    public static ArmorItem goldHelmet = new ArmorItem(40, Sprite.gold_helmet, 2000, "GOLD HELMET", 5, 2);
    public static ArmorItem goldChestplate = new ArmorItem(41, Sprite.gold_chestplate, 2000, "GOLD CHESTPLATE", 5, 2);
    public static ArmorItem corbinsSheild = new ArmorItem(42, Sprite.corbins_sheild, 2000, "CORBINS SHEILD", 5, 2);
    
    
    public ArmorItem(int itemID, Sprite sprite, int standardPrice, String itemName, int protection, int speedChange) {
        super(itemID, sprite, standardPrice, itemName);
        this.protection = protection;
        this.speedChange = speedChange;
    }
    
    public int getDefenceChange() {
        return protection;
    }
    
    public int getSpeedChange() {
        return speedChange;
    }
    
}
