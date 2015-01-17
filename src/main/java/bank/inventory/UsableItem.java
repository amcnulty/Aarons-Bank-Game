/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.inventory;

import bank.graphics.Sprite;
import static bank.inventory.ArmorItem.steelToeBoots;

/**
 *
 * @author Aaron
 */
public class UsableItem extends Items {
    
    private int healthPoints;
    private int speedChange;
    private int attackChange;
    private int defenceChange;
    private int levelChange;
    
    // Food Items
    public static UsableItem drinkingWater = new UsableItem(3, Sprite.drinking_water, 100, "DRINKING WATER", 10, 0, 0, 0, 0);
    public static UsableItem banana = new UsableItem(4, Sprite.banana, 100, "BANANA", 10, 0, 0, 0, 0);
    public static UsableItem cheese = new UsableItem(9, Sprite.cheese, 100, "CHEESE", 10, 0, 0, 0, 0);
    public static UsableItem drumstick = new UsableItem(11, Sprite.drumstick, 100, "DRUMSTICK", 10, 0, 0, 0, 0);
    public static UsableItem cookie = new UsableItem(12, Sprite.cookie, 100, "COOKIE", 10, 0, 0, 0, 0);
    public static UsableItem fish = new UsableItem(13, Sprite.fish, 100, "FISH", 10, 0, 0, 0, 0);
    public static UsableItem cherries = new UsableItem(15, Sprite.cherries, 100, "CHERRIES", 10, 0, 0, 0, 0);
    public static UsableItem egg = new UsableItem(16, Sprite.egg, 100, "EGG", 10, 0, 0, 0, 0);
    
    
    // Potion Items
    public static UsableItem speed_potion = new UsableItem(5, Sprite.speed_potion, 500, "SPEED POTION", 0, 5, 0, 0, 0);
    public static UsableItem defence_potion = new UsableItem(6, Sprite.defence_potion, 500, "DEFENCE POTION", 0, 0, 0, 5, 0);
    public static UsableItem attack_potion = new UsableItem(7, Sprite.attack_potion, 500, "ATTACK POTION", 0, 0, 5, 0, 0);
    
    // Other Items
    public static UsableItem training_book = new UsableItem(10, Sprite.training_book, 10000, "TRAINING BOOK", 5, 5, 5, 5, 1);
    public static UsableItem lv2_training_book = new UsableItem(14, Sprite.lv2_training_book, 20000, "LV 2 TRAINING BOOK", 20, 20, 20, 20, 2);
    public static UsableItem special_candy = new UsableItem(8, Sprite.special_candy, 7000, "SPECIAL CANDY", 0, 0, 0, 0, 1);
    
    public UsableItem(int itemID, Sprite sprite, int standardPrice, String itemName, int healthPoints, int speedChange, int attackChange, int defenceChange, int levelChange) {
        super(itemID, sprite, standardPrice, itemName);
        this.healthPoints = healthPoints;
        this.speedChange = speedChange;
        this.attackChange = attackChange;
        this.defenceChange = defenceChange;
        this.levelChange = levelChange;
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }
    
    public int getSpeedChange() {
        return speedChange;
    }
    
    public int getAttackChange() {
        return attackChange;
    }
    
    public int getDefenceChange() {
        return defenceChange;
    }

}
