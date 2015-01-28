/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.chests;

import bank.entity.Entity;
import bank.graphics.Screen;
import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Chest extends Entity {
    
    private Sprite closed;
    private Sprite open;
    private Sprite chest;   // what sprite will actually be rendered. chest = closed or chest = open;
    private int item;       // every item in the game has a unique id example 1 = weak sword 2 = medium sword 3 = heavy sword
    public boolean opened;
    
    /**
     * This constructor creates a treasure chest at an x y location converted into tile precision
     * @param x The x tile coordinate that you want the top right of chest to be in
     * @param y The y tile coordinate that you want toe top right of chest to be in
     * @param chestNum The style of chest. 1 - 3
     */
    
    public Chest(int x, int y, int chestNum, int item) {
        this.x = x * 16;
        this.y = y * 16;
        this.item = item;
        setChestSprites(chestNum);
        chest = closed;
    }
    
    private void setChestSprites(int chestNum) {
        switch (chestNum) {
            case 1:
                closed = Sprite.chest1_closed;
                open = Sprite.chest1_open;
                break;
            case 2:
                closed = Sprite.chest2_closed;
                open = Sprite.chest2_open;
                break;
            case 3:
                closed = Sprite.chest3_closed;
                open = Sprite.chest3_open;
                break;
        }
    }
    // temporary way of checking chest interactions
    public void toggle() {
        chest = (chest.equals(open)) ? closed : open;
    }
    
    public int getItem() {
        int itemKey = (chest.equals(closed)) ? this.item : 0;
        chest = open;
        opened = true;
        return itemKey;
    }
    
    public boolean isOpen() {
        return opened;
    }
    
    public boolean chestHere(int xp, int yp) {
        if (x + 1 <= xp && x + 31 >= xp && y + 1 <= yp && y + 31 >= yp) return true;
        return false;
    }
    
    public void update() {
        if (opened) chest = open;
        else chest = closed;
    }
    
    public void render(Screen screen) {
        screen.renderSprite(x, y, chest, true);
    }
    
}
