/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.furniture;

import bank.entity.Entity;
import bank.graphics.Screen;
import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Furniture extends Entity {
    
    private Sprite furnitureSprite;
    private int rightBound, leftBound, topBound, bottomBound;
    
    public static int BIG_COUCH = 1;
    public static int SMALL_COUCH = 2;
    public static int OVEN = 3;
    public static int FRIDGE_ONE = 4;
    public static int DRESSER = 5;
    public static int AIR_COMPRESSOR = 6;
    public static int OFFICE_CHAIR = 7;
    public static int BARREL = 8;
    public static int FRIDGE_TWO = 9;
    
    public Furniture(int x, int y, int furnitureType) {
        this.x = x;
        this.y = y;
        setBoundsAndSprite(furnitureType);
    }
    
    private void setBoundsAndSprite(int furnitureType) {
        switch (furnitureType) {
            case 1:
                topBound = y + 1;
                leftBound = x;
                rightBound = x + 32;
                bottomBound = y + 23;
                furnitureSprite = Sprite.big_couch;
                break;
            case 2:
                topBound = y;
                leftBound = x + 1;
                rightBound = x + 27;
                bottomBound = y + 22;
                furnitureSprite = Sprite.small_couch;
                break;
            case 3:
                topBound = y;
                leftBound = x + 3;
                rightBound = x + 28;
                bottomBound = y + 32;
                furnitureSprite = Sprite.oven;
                break;
            case 4:
                topBound = y;
                leftBound = x + 5;
                rightBound = x + 26;
                bottomBound = y + 32;
                furnitureSprite = Sprite.fridge_one;
                break;
            case 5:
                topBound = y + 1;
                leftBound = x + 4;
                rightBound = x + 27;
                bottomBound = y + 31;
                furnitureSprite = Sprite.dresser;
                break;
            case 6:
                topBound = y + 6;
                leftBound = x;
                rightBound = x + 31;
                bottomBound = y + 31;
                furnitureSprite = Sprite.air_compressor;
                break;
            case 7:
                topBound = y + 1;
                leftBound = x + 6;
                rightBound = x + 24;
                bottomBound = y + 32;
                furnitureSprite = Sprite.office_chair;
                break;
            case 8:
                topBound = y + 1;
                leftBound = x + 6;
                rightBound = x + 25;
                bottomBound = y + 32;
                furnitureSprite = Sprite.barrel;
                break;
            case 9:
                topBound = y + 1;
                leftBound = x + 8;
                rightBound = x + 23;
                bottomBound = y + 32;
                furnitureSprite = Sprite.fridge_two;
                break;
        }
    }
    
    public boolean furnitureHere(int xp, int yp) {
        return xp >= leftBound && xp <= rightBound && yp >= topBound && yp <= bottomBound;
    }
    
    public void render(Screen screen) {
        screen.renderSprite(x, y, furnitureSprite, true);
    }
    
}
