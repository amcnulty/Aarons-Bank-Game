/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;

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
    
    public void render(Screen screen) {
        super.render(screen);
        font.renderSuperSmallCharacters2(171, 25, "RECEIVED", screen);
        screen.renderSprite(130, 35, itemSprite, false);
        font.renderSuperSmallCharacters2(156, 40, itemName, screen);
    }
    
}
