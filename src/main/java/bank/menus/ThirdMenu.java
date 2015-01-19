/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class ThirdMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    private ClickableButton[] unequipArmorButtons = new ClickableButton[3];
    private ClickableButton unequipWeaponButton;
    
    public ThirdMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // weapons button
        buttons.add(new ClickableButton(10, xloc, 25, yloc, "WEAPONS", 3));
        // add 500 dollars button
        buttons.add(new ClickableButton(61, xloc, 25, yloc, "ARMOR", 4));
        // adding button to show bobbie
        buttons.add(new ClickableButton(100, xloc, 25, yloc, "ITEMS", 5));
        // adding referals button
        buttons.add(new ClickableButton(18, xloc, 51, yloc, "ADD-REFERRAL", 15));
        // adding unequip weapon button
        unequipWeaponButton = new ClickableButton(28, xloc, 189, yloc, "UNEQUIP", 11);
        for (int i = 0; i < 3; i++) {
            unequipArmorButtons[i] = new ClickableButton(143, xloc, 150 + (i * 18), yloc, "UNEQUIP", 12 + i);
        }
    }
    
    private Sprite buildNameBoxSprite(String itemName) {
        int width = ((itemName.length() * 6) + 6);
        int[] pixels = new int[width * 13];
        for (int y = 0; y < 13; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xffFF384B;
            }
        }
        for (int i = 0; i < width; i++) {
            pixels[i] = 0;
            pixels[i + 12 * width] = 0;
        }
        for (int i = 0; i < 13; i++) {
            pixels[0 + i * width] = 0;
            pixels[(width * (i + 1)) - 1] = 0;
        }
        Sprite newSprite = new Sprite(pixels, width, 13);
        return newSprite;
    }
    
    public void update() {
        super.update();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).update();
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).doAction()) {
                currentAction = buttons.get(i).action;
                doAction = true;
            }
        }
        for (int i = 0; i < 3; i ++) {
            if (player.equipedArmor[i] != null) {
                unequipArmorButtons[i].setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
                unequipArmorButtons[i].update();
                if (unequipArmorButtons[i].doAction()) {
                    currentAction = unequipArmorButtons[i].action;
                    doAction = true;
                }
            }
        }
        if (player.equipedWeapon != null) {
            unequipWeaponButton.setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
            unequipWeaponButton.update();
        }
        if (unequipWeaponButton.doAction()) {
            currentAction = unequipWeaponButton.action;
            doAction = true;
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        font.renderSmallCharacters(27, 9, "Inventory", screen);
        font.renderSmallCharacters(20, 34, "Equiped Items", screen);
        font.renderSmallCharacters(15, 49, "Weapon", screen);
        font.renderSmallCharacters(45, 49, "Armor", screen);
        font.renderSmallCharacters(26, 18, "Referrals", screen);
        font.renderSuperSmallCharacters2(115, 68, "TOTAL-", screen);
        font.renderSuperSmallCharacters2(155, 68, Integer.toString(player.totalReferrals), screen);
        font.renderSuperSmallCharacters2(35, 85, "AVAILABLE-", screen);
        font.renderSuperSmallCharacters2(100, 85, Integer.toString(player.availableReferrals), screen);
        font.renderSuperSmallCharacters2(130, 85, "USED-", screen);
        font.renderSuperSmallCharacters2(165, 85, Integer.toString(player.usedReferrals), screen);
        for (int i = 0; i < 3; i ++) {
            if (player.equipedArmor[i] != null) {
                screen.renderSprite(133, 162 + (18 * i), player.equipedArmor[i].itemSprite, false);
                unequipArmorButtons[i].render(screen);
            }
            else {
                font.renderSuperSmallCharacters2(139, 166 + (18 * i), "EMPTY", screen);
            }
        }
        if (player.equipedWeapon == null) {
            font.renderSuperSmallCharacters2(45, 181, "NOTHING", screen);
            font.renderSuperSmallCharacters2(45, 190, "EQUIPED", screen);
        }
        else {
            screen.renderSprite(59, 164, player.equipedWeapon.itemSprite, false);
            font.renderSuperSmallCharacters2(36, 191, player.equipedWeapon.itemName, screen);
            unequipWeaponButton.render(screen);
        }
        screen.renderSprite(97, 111, player.getPlayerMenuSprite(player.playerNum), false);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
        for (int i = 0; i < 3; i++) {
            if (Mouse.getX() >= 399 && Mouse.getX() <= 447 && Mouse.getY() > 486 + ((18 * i)*3) && Mouse.getY() < 534 + ((18 * i)*3) && player.equipedArmor[i] != null) {
                Sprite newSprite = buildNameBoxSprite(player.equipedArmor[i].itemName);
                screen.renderSprite((Mouse.getX() / 3) + 10, (Mouse.getY() / 3), newSprite, false);
                font.renderSuperSmallCharacters2((Mouse.getX() / 3) + 15, (Mouse.getY() / 3) + 4, player.equipedArmor[i].itemName, screen);
            }
        }
    }
    
}
