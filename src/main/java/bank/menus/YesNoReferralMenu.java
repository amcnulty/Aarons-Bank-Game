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
class YesNoReferralMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();

    public YesNoReferralMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        buttons.add(new ClickableButton(70, xloc, 80, yloc, "YES", 16));
        buttons.add(new ClickableButton(110, xloc, 80, yloc, "NO", 17));
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
    }
    
    public void render(Screen screen) {
        super.render(screen);
        font.renderSuperSmallCharacters2(130, 90, "YOU ARE ABOUT TO ADD A REFERRAL", screen);
        font.renderSuperSmallCharacters2(130, 109, "IF YOU ARE LYING WE WILL KNOW", screen);
        font.renderSuperSmallCharacters2(130, 130, "ARE YOU SURE THIS IS WHAT YOU", screen);
        font.renderSuperSmallCharacters2(130, 137, "WANT TO DO", screen);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
    }
    
}
