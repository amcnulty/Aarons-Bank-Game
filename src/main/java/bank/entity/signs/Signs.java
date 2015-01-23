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
    private String index1;
    private String index2;
    private String index3;
    private String[] signDialog;
    
    public Signs(int x, int y, String index1) {
        this.x = x;
        this.y = y;
        this.index1 = index1;
        signSprite = Sprite.signSprite;
        setSignDialog();
    }
    
    public Signs(int x, int y, String index1, String index2) {
        this.x = x;
        this.y = y;
        this.index1 = index1;
        this.index2 = index2;
        signSprite = Sprite.signSprite;
        setSignDialog();
    }
    
    public Signs(int x, int y, String index1, String index2, String index3) {
        this.x = x;
        this.y = y;
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
        signSprite = Sprite.signSprite;
        setSignDialog();
    }
    
    private void setSignDialog() {
        if (index2 == null && index3 == null)  {
            signDialog = new String[1];
            signDialog[0] = index1;
        }
        else if (index2 != null && index3 == null) {
            signDialog = new String[2];
            signDialog[0] = index1;
            signDialog[1] = index2;
        }
        else if (index1 != null && index2 != null && index3 != null) {
            signDialog = new String[3];
            signDialog[0] = index1;
            signDialog[1] = index2;
            signDialog[2] = index3;
        }
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
