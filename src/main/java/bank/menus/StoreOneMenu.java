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
class StoreOneMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();

    public StoreOneMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // add 500 dollars button
        buttons.add(new ClickableButton(50, xloc, 27, yloc, "BUY", 32, ArmorItem.leatherhelmet));
        // adding button to show bobbie
        buttons.add(new ClickableButton(50, xloc, 85, yloc, "BUY", 33, ArmorItem.hardLeatherHelmet));
        // adding banana button
        buttons.add(new ClickableButton(50, xloc, 143, yloc, "BUY", 34, ArmorItem.hardLeatherTunic));
        // adding speed potion button
        buttons.add(new ClickableButton(190, xloc, 27, yloc, "BUY", 35, ArmorItem.steelToeBoots));
        // buy button for leather tunic
        buttons.add(new ClickableButton(190, xloc, 85, yloc, "BUY", 36, ArmorItem.leatherTunic));
        // buy button for leather boots
        buttons.add(new ClickableButton(190, xloc, 143, yloc, "BUY", 37, ArmorItem.leatherBoots));
        
    }
    
    public void randomizeTotals() {
        item1rand = ArmorItem.leatherhelmet.standardPrice + random.nextInt(1200) - 600;
        item2rand = ArmorItem.hardLeatherHelmet.standardPrice + random.nextInt(2400) - 1200;
        item3rand = ArmorItem.hardLeatherTunic.standardPrice + random.nextInt(5000) - 2500;
        item4rand = ArmorItem.steelToeBoots.standardPrice + random.nextInt(1000) - 500;
        item5rand = ArmorItem.leatherTunic.standardPrice + random.nextInt(2500) - 1250;
        item6rand = ArmorItem.leatherBoots.standardPrice + random.nextInt(600) - 300;
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
        font.renderSuperSmallCharacters2(23, 39, ArmorItem.leatherhelmet.itemName, screen);
        screen.renderSprite(25 + 14, 45, ArmorItem.leatherhelmet.itemSprite, false);
        screen.renderSprite(32, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 58 + 5, Integer.toString(item1rand), screen);
        font.renderSuperSmallCharacters2(23, 66 + 5, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100, 66 + 5, Integer.toString(ArmorItem.leatherhelmet.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23, 74 + 5, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100, 74 + 5, Integer.toString(ArmorItem.leatherhelmet.getSpeedChange()), screen);
        
        
        font.renderSuperSmallCharacters2(23, 97, ArmorItem.hardLeatherHelmet.itemName, screen);
        screen.renderSprite(39, 103, ArmorItem.hardLeatherHelmet.itemSprite, false);
        screen.renderSprite(32, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 121, Integer.toString(item2rand), screen);
        font.renderSuperSmallCharacters2(23, 129, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100, 129, Integer.toString(ArmorItem.hardLeatherHelmet.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23, 137, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100, 137, Integer.toString(ArmorItem.hardLeatherHelmet.getSpeedChange()), screen);
        
        font.renderSuperSmallCharacters2(23, 155, ArmorItem.hardLeatherTunic.itemName, screen);
        screen.renderSprite(39, 161, ArmorItem.hardLeatherTunic.itemSprite, false);
        screen.renderSprite(32, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 179, Integer.toString(item3rand), screen);
        font.renderSuperSmallCharacters2(23, 187, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100, 187, Integer.toString(ArmorItem.hardLeatherTunic.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23, 195, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100, 195, Integer.toString(ArmorItem.hardLeatherTunic.getSpeedChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 39, ArmorItem.steelToeBoots.itemName, screen);
        screen.renderSprite(25 + 14 + 140, 45, ArmorItem.steelToeBoots.itemSprite, false);
        screen.renderSprite(32 + 140, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 58 + 5, Integer.toString(item4rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 66 + 5, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100 + 140, 66 + 5, Integer.toString(ArmorItem.steelToeBoots.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23 + 140, 74 + 5, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100 + 140, 74 + 5, Integer.toString(ArmorItem.steelToeBoots.getSpeedChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 97, ArmorItem.leatherTunic.itemName, screen);
        screen.renderSprite(39 + 140, 103, ArmorItem.leatherTunic.itemSprite, false);
        screen.renderSprite(32 + 140, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 121, Integer.toString(item5rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 129, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100 + 140, 129, Integer.toString(ArmorItem.leatherTunic.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23 + 140, 137, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100 + 140, 137, Integer.toString(ArmorItem.leatherTunic.getSpeedChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 155, ArmorItem.leatherBoots.itemName, screen);
        screen.renderSprite(39 + 140, 161, ArmorItem.leatherBoots.itemSprite, false);
        screen.renderSprite(32 + 140, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 179, Integer.toString(item6rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 187, "PROTECTION", screen);
        font.renderSuperSmallCharacters2(100 + 140, 187, Integer.toString(ArmorItem.leatherBoots.getDefenceChange()), screen);
        font.renderSuperSmallCharacters2(23 + 140, 195, "SPEED CHANGE", screen);
        font.renderSuperSmallCharacters2(100 + 140, 195, Integer.toString(ArmorItem.leatherBoots.getSpeedChange()), screen);
    }
    
}
