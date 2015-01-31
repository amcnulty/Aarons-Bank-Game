/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.UsableItem;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
class PotionShopMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();

    public PotionShopMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // buy speed potion button
        buttons.add(new ClickableButton(50, xloc, 27, yloc, "BUY", 44, UsableItem.speed_potion));
        // buy defence potion button
        buttons.add(new ClickableButton(50, xloc, 85, yloc, "BUY", 45, UsableItem.defence_potion));
        // buy attack potion button
        buttons.add(new ClickableButton(50, xloc, 143, yloc, "BUY", 46, UsableItem.attack_potion));
        
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
                item = buttons.get(i).item;
                doAction = true;
            }
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
        font.renderSuperSmallCharacters2(23, 39, UsableItem.speed_potion.itemName, screen);
        screen.renderSprite(25 + 14, 45, UsableItem.speed_potion.itemSprite, false);
        screen.renderSprite(32, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 58 + 5, Integer.toString(UsableItem.speed_potion.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 66 + 5, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100, 66 + 5, Integer.toString(UsableItem.speed_potion.getSpeedChange()), screen);
        
        
        font.renderSuperSmallCharacters2(23, 97, UsableItem.defence_potion.itemName, screen);
        screen.renderSprite(39, 103, UsableItem.defence_potion.itemSprite, false);
        screen.renderSprite(32, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 121, Integer.toString(UsableItem.defence_potion.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 129, "DEFENCE CHANGE", screen);
        font.renderSuperSmallCharacters2(110, 129, Integer.toString(UsableItem.defence_potion.getDefenceChange()), screen);
        
        font.renderSuperSmallCharacters2(23, 155, UsableItem.attack_potion.itemName, screen);
        screen.renderSprite(39, 161, UsableItem.attack_potion.itemSprite, false);
        screen.renderSprite(32, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 179, Integer.toString(UsableItem.attack_potion.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 187, "ATTACK CHANGE", screen);
        font.renderSuperSmallCharacters2(104, 187, Integer.toString(UsableItem.attack_potion.getAttackChange()), screen);
        
    }
}
