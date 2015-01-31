/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.ArmorItem;
import bank.inventory.UsableItem;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
class BaitShopMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();

    public BaitShopMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        buttons.add(new ClickableButton(50, xloc, 27, yloc, "BUY", 47, UsableItem.drinkingWater));
        buttons.add(new ClickableButton(50, xloc, 85, yloc, "BUY", 48, UsableItem.banana));
        buttons.add(new ClickableButton(50, xloc, 143, yloc, "BUY", 49, UsableItem.cheese));
        buttons.add(new ClickableButton(190, xloc, 27, yloc, "BUY", 50, UsableItem.fish));
        buttons.add(new ClickableButton(190, xloc, 85, yloc, "BUY", 51, UsableItem.cherries));
        buttons.add(new ClickableButton(190, xloc, 143, yloc, "BUY", 52, UsableItem.egg));
        
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
        font.renderSuperSmallCharacters2(23, 39, UsableItem.drinkingWater.itemName, screen);
        screen.renderSprite(25 + 14, 45, UsableItem.drinkingWater.itemSprite, false);
        screen.renderSprite(32, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 58 + 5, Integer.toString(UsableItem.drinkingWater.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 66 + 5, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105, 66 + 5, Integer.toString(UsableItem.drinkingWater.getHealthPoints()), screen);
        
        
        font.renderSuperSmallCharacters2(23, 97, UsableItem.banana.itemName, screen);
        screen.renderSprite(39, 103, UsableItem.banana.itemSprite, false);
        screen.renderSprite(32, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 121, Integer.toString(UsableItem.banana.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 129, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105, 129, Integer.toString(UsableItem.banana.getHealthPoints()), screen);
        
        font.renderSuperSmallCharacters2(23, 155, UsableItem.cheese.itemName, screen);
        screen.renderSprite(39, 161, UsableItem.cheese.itemSprite, false);
        screen.renderSprite(32, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 179, Integer.toString(UsableItem.cheese.standardPrice), screen);
        font.renderSuperSmallCharacters2(23, 187, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105, 187, Integer.toString(UsableItem.cheese.getHealthPoints()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 39, UsableItem.fish.itemName, screen);
        screen.renderSprite(25 + 14 + 140, 45, UsableItem.fish.itemSprite, false);
        screen.renderSprite(32 + 140, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 58 + 5, Integer.toString(UsableItem.fish.standardPrice), screen);
        font.renderSuperSmallCharacters2(23 + 140, 66 + 5, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105 + 140, 66 + 5, Integer.toString(UsableItem.fish.getHealthPoints()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 97, UsableItem.cherries.itemName, screen);
        screen.renderSprite(39 + 140, 103, UsableItem.cherries.itemSprite, false);
        screen.renderSprite(32 + 140, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 121, Integer.toString(UsableItem.cherries.standardPrice), screen);
        font.renderSuperSmallCharacters2(23 + 140, 129, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105 + 140, 129, Integer.toString(UsableItem.cherries.getHealthPoints()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 155, UsableItem.egg.itemName, screen);
        screen.renderSprite(39 + 140, 161, UsableItem.egg.itemSprite, false);
        screen.renderSprite(32 + 140, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 179, Integer.toString(UsableItem.egg.standardPrice), screen);
        font.renderSuperSmallCharacters2(23 + 140, 187, "HEALTH CHANGE", screen);
        font.renderSuperSmallCharacters2(105 + 140, 187, Integer.toString(UsableItem.egg.getHealthPoints()), screen);
    }
}
