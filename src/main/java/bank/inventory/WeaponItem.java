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
    
    public static Items heavySword = new WeaponItem(2, Sprite.heavy_sword, 6000, "HEAVY SWORD", 40);
    public static Items deluxeSword = new WeaponItem(17, Sprite.deluxe_sword, 6000, "DELUXE SWORD", 40);
    public static Items speedSword = new WeaponItem(18, Sprite.speed_sword, 6000, "SPEED SWORD", 40);
    public static Items flameSword = new WeaponItem(19, Sprite.flame_sword, 6000, "FLAME SWORD", 40);
    public static Items iceSword = new WeaponItem(20, Sprite.ice_sword, 6000, "ICE SWORD", 40);
    public static Items sparkSword = new WeaponItem(21, Sprite.spark_sword, 6000, "SPARK SWORD", 40);
    public static Items dragonSword = new WeaponItem(22, Sprite.dragon_sword, 6000, "DRAGON SWORD", 40);
    public static Items crystalSword = new WeaponItem(23, Sprite.crystal_sword, 6000, "CRYSTAL SWORD", 40);
    public static Items bloodSword = new WeaponItem(24, Sprite.blood_sword, 6000, "BLOOD SWORD", 40);
    public static Items battleAxe = new WeaponItem(25, Sprite.battle_axe, 6000, "BATTLE AXE", 40);
    public static Items bloodDagger = new WeaponItem(26, Sprite.blood_dagger, 6000, "BLOOD DAGGER", 40);
    public static Items blackDagger = new WeaponItem(27, Sprite.black_dagger, 6000, "BLACK DAGGER", 40);
    public static Items deluxeDagger = new WeaponItem(28, Sprite.deluxe_dagger, 6000, "DELUXE DAGGER", 40);
    public static Items poisonDagger = new WeaponItem(29, Sprite.poison_dagger, 6000, "POISON DAGGER", 40);
    
    public WeaponItem(int itemID, Sprite sprite, int standardPrice, String itemName, int attackChange) {
        super(itemID, sprite, standardPrice, itemName);
        this.attackChange = attackChange;
    }
    
    public int getAttackChange() {
        return attackChange;
    }
    
}
