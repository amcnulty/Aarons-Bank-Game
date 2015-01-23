/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.Items;
import bank.inventory.UsableItem;
import bank.inventory.WeaponItem;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
class FavoriteFoodMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();

    public FavoriteFoodMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        buttons.add(new ClickableButton(25, xloc, 65, yloc, "GIVE", 20));
        buttons.add(new ClickableButton(75, xloc, 65, yloc, "GIVE", 21));
        buttons.add(new ClickableButton(125, xloc, 65, yloc, "GIVE", 22));
        buttons.add(new ClickableButton(175, xloc, 65, yloc, "GIVE", 23));
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
                if (currentAction == 23) {
                    Menu.chestMenu.setItemSpriteAndName(WeaponItem.speedSword.itemSprite, WeaponItem.speedSword.itemName);
                }
                doAction = true;
            }
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        screen.renderSprite(50, 46, UsableItem.cookie.itemSprite, false);
        font.renderSuperSmallCharacters2(40, 70, "COOKIE", screen);
        screen.renderSprite(100, 46, UsableItem.cherries.itemSprite, false);
        font.renderSuperSmallCharacters2(85, 70, "CHERRIES", screen);
        screen.renderSprite(150, 46, UsableItem.cheese.itemSprite, false);
        font.renderSuperSmallCharacters2(140, 70, "CHEESE", screen);
        screen.renderSprite(200, 46, UsableItem.egg.itemSprite, false);
        font.renderSuperSmallCharacters2(200, 70, "EGG", screen);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
    }
    
}
