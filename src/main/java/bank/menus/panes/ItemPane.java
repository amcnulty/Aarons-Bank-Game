/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus.panes;

import bank.graphics.Font;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.Items;
import bank.menus.ClickableButton;

/**
 *
 * @author Aaron
 */
public class ItemPane {
    
    private Sprite itemSprite;
    private String amount;
    private String buttonName;
    private int index;
    private String itemName;
    
    private ClickableButton useButton;
    private Font font = new Font();
    
    public Items item;
    public int action;
    public boolean doAction;
    
    public ItemPane(int index, Items item, String buttonName, int buttonAction) {
        this.item = item;
        if (index < 7) this.index = index;
        else if (index > 6) this.index = (index - 7);
        itemSprite = item.itemSprite;
        itemName = item.itemName;
        amount = Integer.toString(item.amount);
        this.buttonName = buttonName;
        action = buttonAction;
        addButton();
    }
    
    private void addButton() {
        if (buttonName == "USE") {
            useButton = new ClickableButton(180, 0, 62 + (index * 20), 0, buttonName, action);
        }
        else if (buttonName == "EQUIP") {
            useButton = new ClickableButton(172, 0, 62 + (index * 20), 0, buttonName, action);
        }
    }
    
    public boolean doAction() {
        if (doAction) {
            doAction = false;
            return true;
        }
        else return false;
    }
    
    public void update() {
        //this.amount = Integer.toString(item.amount);
        useButton.setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
        useButton.update();
        if (useButton.doAction()) {
            doAction = true;
        }
    }
    
    public void render(Screen screen) {
        useButton.render(screen);
        screen.renderSprite(25, 60 + (index * 20), itemSprite, false);
        font.renderSuperSmallCharacters2(48, 65 + (index * 20), itemName, screen);
        font.renderSuperSmallCharacters2(162, 65 + (index * 20), amount, screen);
    }
    
}
