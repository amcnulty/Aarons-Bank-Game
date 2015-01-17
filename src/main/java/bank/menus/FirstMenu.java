/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.input.Mouse;
import bank.inventory.UsableItem;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class FirstMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    
    public FirstMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // exit button
        buttons.add(new ClickableButton(15, xloc, 25, yloc, "EXIT", 0));
        // add 500 dollars button
        buttons.add(new ClickableButton(15, xloc, 40, yloc, "ADD+CASH", 1));
        // adding button to show bobbie
        buttons.add(new ClickableButton(15, xloc, 55, yloc, "SPEED-UP", 2));
        // adding banana button
        buttons.add(new ClickableButton(15, xloc, 70, yloc, "BANANA-PHONE", 8, UsableItem.banana));
        // adding speed potion button
        buttons.add(new ClickableButton(15, xloc, 85, yloc, "SPEED-POTION", 8, UsableItem.speed_potion));
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
    }
}
