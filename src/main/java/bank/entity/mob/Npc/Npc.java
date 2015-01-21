/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.mob.Npc;

import bank.entity.mob.Mob;
import bank.graphics.Screen;
import java.io.FileNotFoundException;

/**
 *
 * @author Aaron
 */
public class Npc extends Mob {

    public boolean talking;
    public int menuNum;

    protected String dialogPath;
    protected int nextMessage = 1;
    
    protected int anim;
    protected boolean timeForNextRanTurn = true;
    protected int timeTillTurn;
    protected int turnTimer;

    public Npc(int x, int y) {
        this.x = x;
        this.y = y;
        setSprite(0);
    }

    public Npc(int x, int y, String path) {
        this.x = x;
        this.y = y;
        setSprite(0);
        dialogPath = path;
    }
    
    public Npc(int x, int y, int menuNum) {
        this.x = x;
        this.y = y;
        this.menuNum = menuNum;
        setSprite(0);
    }
    
    public Npc(int x, int y, int menuNum, String path) {
        this.x = x;
        this.y = y;
        this.menuNum = menuNum;
        setSprite(0);
        dialogPath = path;
    }

    protected void setSprite(int dir) {
    }
    
    public boolean openShoppingMenu() {
        return false;
    }

    public boolean npcHere(int x, int y) {
        if (this.x + 3 <= x && x <= this.x + 28 && this.y + 2 <= y && y <= this.y + 32) return true;
        else return false;
    }

    public String[] getConversationDialog(int dir) throws FileNotFoundException {
        return null;
    }
    
    public void update() {
        if (anim < 7500) anim++;
        else anim = 0;
        
            if (turnTimer != timeTillTurn || talking) {
                if (turnTimer < 420) turnTimer++;
                else turnTimer = 0;
            }
            else {
                switch (dir) {
                    case 0:
                        switch (random.nextInt(3)) {
                            case 0:
                                dir = 1;
                                break;
                            case 1:
                                dir = 2;
                                break;
                            case 2:
                                dir = 3;
                                break;
                        }
                        break;
                    case 1:
                        switch (random.nextInt(3)) {
                            case 0:
                                dir = 2;
                                break;
                            case 1:
                                dir = 3;
                                break;
                            case 2:
                                dir = 0;
                                break;
                        }
                        break;
                    case 2:
                        switch (random.nextInt(3)) {
                            case 0:
                                dir = 3;
                                break;
                            case 1:
                                dir = 0;
                                break;
                            case 2:
                                dir = 1;
                                break;
                        }
                        break;
                    case 3:
                        switch (random.nextInt(3)) {
                            case 0:
                                dir = 0;
                                break;
                            case 1:
                                dir = 1;
                                break;
                            case 2:
                                dir = 2;
                                break;
                        }
                        break;
                }
                timeForNextRanTurn = true;
            }
            if (timeForNextRanTurn) {
                timeTillTurn = random.nextInt(180) + 180;
                turnTimer = 0;
                timeForNextRanTurn = false;
            }
            setSprite(dir);
    }

    public void render(Screen screen) {
        //System.out.println("RENDERING FROM NPC");
        screen.renderSprite(x, y, sprite, true);
    }

}
