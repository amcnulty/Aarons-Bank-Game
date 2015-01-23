/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Keyboard;

/**
 *
 * @author Aaron
 */
public class SeventhMenu extends Menu {
    
    private Sprite itemSprite;
    private String itemName;
    
    public SeventhMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
    }
    
    public void setItemSpriteAndName(Sprite itemSprite, String itemName) {
        this.itemSprite = itemSprite;
        this.itemName = itemName;
    }
    
    public void update() {
        super.update();
        if (!key.checked && key.space) {
            isOpen = false;
            key.checked = true;
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        font.renderSuperSmallCharacters2(171, 180, "RECEIVED", screen);
        screen.renderSprite(130, 190, itemSprite, false);
        font.renderSuperSmallCharacters2(156, 195, itemName, screen);
    }
    
}
