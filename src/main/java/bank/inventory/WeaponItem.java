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
public class WeaponItem extends Items {
    
    private int attackChange;
    
    public static Items heavySword = new WeaponItem(2, Sprite.heavy_sword, 4200, "HEAVY SWORD", 70);
    public static Items deluxeSword = new WeaponItem(17, Sprite.deluxe_sword, 1900, "DELUXE SWORD", 38);
    public static Items speedSword = new WeaponItem(18, Sprite.speed_sword, 2100, "SPEED SWORD", 42);
    public static Items flameSword = new WeaponItem(19, Sprite.flame_sword, 4800, "FLAME SWORD", 80);
    public static Items iceSword = new WeaponItem(20, Sprite.ice_sword, 4800, "ICE SWORD", 80);
    public static Items sparkSword = new WeaponItem(21, Sprite.spark_sword, 5400, "SPARK SWORD", 90);
    public static Items dragonSword = new WeaponItem(22, Sprite.dragon_sword, 8400, "DRAGON SWORD", 105);
    public static Items crystalSword = new WeaponItem(23, Sprite.crystal_sword, 14875, "CRYSTAL SWORD", 175);
    public static Items bloodSword = new WeaponItem(24, Sprite.blood_sword, 9600, "BLOOD SWORD", 120);
    public static Items battleAxe = new WeaponItem(25, Sprite.battle_axe, 10800, "BATTLE AXE", 135);
    public static Items bloodDagger = new WeaponItem(26, Sprite.blood_dagger, 2000, "BLOOD DAGGER", 50);
    public static Items blackDagger = new WeaponItem(27, Sprite.black_dagger, 1200, "BLACK DAGGER", 30);
    public static Items deluxeDagger = new WeaponItem(28, Sprite.deluxe_dagger, 600, "DELUXE DAGGER", 15);
    public static Items poisonDagger = new WeaponItem(29, Sprite.poison_dagger, 132050, "POISON DAGGER", 350);
    
    public WeaponItem(int itemID, Sprite sprite, int standardPrice, String itemName, int attackChange) {
        super(itemID, sprite, standardPrice, itemName);
        this.attackChange = attackChange;
    }
    
    public int getAttackChange() {
        return attackChange;
    }
    
}
