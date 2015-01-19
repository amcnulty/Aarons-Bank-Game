/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.signs;

import bank.entity.Entity;
import bank.graphics.Screen;
import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Signs extends Entity {
    
    private Sprite signSprite;
    private String[] signDialog;
    
    
    public Signs(int x, int y) {
        this.x = x;
        this.y = y;
        signSprite = Sprite.signSprite;
        setSignDialog();
    }
    
    private void setSignDialog() {
        signDialog = new String[2];
        signDialog[0] = "HI JERRY WHAT ARE YOU DOING?";
        signDialog[1] = "I am making signs that say things";
    }
    
    public String[] getSignDialog() {
        return signDialog;
    }
    
    public boolean signHere(int xp, int yp) {
        if (x <= xp - 4 && x + 28 >= xp && y <= yp && y + 28 >= yp) return true;
        return false;
    }
    
    public void render(Screen screen) {
        screen.renderSprite(x, y, signSprite, true);
    }
    
}
