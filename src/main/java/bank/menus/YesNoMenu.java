/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.input.Mouse;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class YesNoMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    
    public YesNoMenu(int yesButton, int noButton) {
        super(80, 45, 180, 80);
        addButtons(yesButton, noButton);
    }
    
    private void addButtons(int yesButton, int noButton) {
        buttons.add(new ClickableButton(15, xloc, 20, yloc, "YES", yesButton));
        buttons.add(new ClickableButton(50, xloc, 20, yloc, "NO", noButton));
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
