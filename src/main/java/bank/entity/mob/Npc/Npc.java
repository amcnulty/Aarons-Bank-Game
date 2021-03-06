/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.mob.Npc;

import bank.entity.mob.Mob;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.level.cutscenes.CutScenes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
    
    private CutScenes cut;
    private byte[][] route;
    public boolean moveAlongRoute = false;
    private int index = 0;
    public boolean movedOutOfWay = false;
    
    private int characterDesign;
    private boolean openShop;
    
    private String initialConvo;
    public boolean initialConversation = true;
    private String finalConvo;
    
    public static int MALENPC = 1;
    public static int FEMALENPC = 2;
    public static int CORBIN = 3;
    public static int MALE2NPC = 4;
    public static int MALE3NPC = 5;
    public static int FEMALE2NPC = 6;
    public static int FEMALE3NPC = 7;
    public static int ARMOREDGUARD = 8;
    public static int FEMALE4NPC = 9;
    public static int MALE4NPC = 10;
    

    public Npc(int x, int y, int characterDesign) {
        this.x = x;
        this.y = y;
        this.characterDesign = characterDesign;
        setSprite(0);
    }

    public Npc(int x, int y, int characterDesign, String path) {
        this.x = x;
        this.y = y;
        this.characterDesign = characterDesign;
        setSprite(0);
        dialogPath = path;
    }
    
    public Npc(int x, int y, int characterDesign, int menuNum, String initialConvo, String finalConvo) {
        this.x = x;
        this.y = y;
        this.menuNum = menuNum;
        this.characterDesign = characterDesign;
        this.initialConvo = initialConvo;
        this.finalConvo = finalConvo;
        openShop = true;
        setSprite(0);
    }
    
    public Npc(int x, int y, int characterDesign, int menuNum, String path) {
        this.x = x;
        this.y = y ;
        this.menuNum = menuNum;
        setSprite(0);
        dialogPath = path;
    }

    protected void setSprite(int dir) {
        switch (characterDesign) {
            case 1:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.maleNPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.maleNPC_forward1;
                        }
                        else this.sprite = Sprite.maleNPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.maleNPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.maleNPC_right1;
                        }
                        else this.sprite = Sprite.maleNPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.maleNPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.maleNPC_back1;
                        }
                        else this.sprite = Sprite.maleNPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.maleNPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.maleNPC_left1;
                        }
                        else this.sprite = Sprite.maleNPC_left2;
                    }
                    break;
                }
                break;
            case 2:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.femaleNPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_forward1;
                        }
                        else this.sprite = Sprite.femaleNPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.femaleNPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_right1;
                        }
                        else this.sprite = Sprite.femaleNPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.femaleNPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_back1;
                        }
                        else this.sprite = Sprite.femaleNPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.femaleNPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.femaleNPC_left1;
                        }
                        else this.sprite = Sprite.femaleNPC_left2;
                    }
                    break;
                }
                break;
            case 3:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.corbin_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.corbin_forward1;
                        }
                        else this.sprite = Sprite.corbin_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.corbin_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.corbin_right1;
                        }
                        else this.sprite = Sprite.corbin_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.corbin_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.corbin_back1;
                        }
                        else this.sprite = Sprite.corbin_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.corbin_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.corbin_left1;
                        }
                        else this.sprite = Sprite.corbin_left2;
                    }
                    break;
                }
                break;
            case 4:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.male2NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male2NPC_forward1;
                        }
                        else this.sprite = Sprite.male2NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.male2NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male2NPC_right1;
                        }
                        else this.sprite = Sprite.male2NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.male2NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male2NPC_back1;
                        }
                        else this.sprite = Sprite.male2NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.male2NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male2NPC_left1;
                        }
                        else this.sprite = Sprite.male2NPC_left2;
                    }
                    break;
                }
                break;
            case 5:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.male3NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male3NPC_forward1;
                        }
                        else this.sprite = Sprite.male3NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.male3NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male3NPC_right1;
                        }
                        else this.sprite = Sprite.male3NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.male3NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male3NPC_back1;
                        }
                        else this.sprite = Sprite.male3NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.male3NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male3NPC_left1;
                        }
                        else this.sprite = Sprite.male3NPC_left2;
                    }
                    break;
                }
                break;
            case 6:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.female2NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female2NPC_forward1;
                        }
                        else this.sprite = Sprite.female2NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.female2NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female2NPC_right1;
                        }
                        else this.sprite = Sprite.female2NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.female2NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female2NPC_back1;
                        }
                        else this.sprite = Sprite.female2NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.female2NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female2NPC_left1;
                        }
                        else this.sprite = Sprite.female2NPC_left2;
                    }
                    break;
                }
                break;
            case 7:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.female3NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female3NPC_forward1;
                        }
                        else this.sprite = Sprite.female3NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.female3NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female3NPC_right1;
                        }
                        else this.sprite = Sprite.female3NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.female3NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female3NPC_back1;
                        }
                        else this.sprite = Sprite.female3NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.female3NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female3NPC_left1;
                        }
                        else this.sprite = Sprite.female3NPC_left2;
                    }
                    break;
                }
                break;
            case 8:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.armoredGuardNPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.armoredGuardNPC_forward1;
                        }
                        else this.sprite = Sprite.armoredGuardNPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.armoredGuardNPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.armoredGuardNPC_right1;
                        }
                        else this.sprite = Sprite.armoredGuardNPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.armoredGuardNPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.armoredGuardNPC_back1;
                        }
                        else this.sprite = Sprite.armoredGuardNPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.armoredGuardNPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.armoredGuardNPC_left1;
                        }
                        else this.sprite = Sprite.armoredGuardNPC_left2;
                    }
                    break;
                }
                break;
            case 9:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.female4NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female4NPC_forward1;
                        }
                        else this.sprite = Sprite.female4NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.female4NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female4NPC_right1;
                        }
                        else this.sprite = Sprite.female4NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.female4NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female4NPC_back1;
                        }
                        else this.sprite = Sprite.female4NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.female4NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.female4NPC_left1;
                        }
                        else this.sprite = Sprite.female4NPC_left2;
                    }
                    break;
                }
                break;
            case 10:
                switch (dir) {
                case 0:
                    this.sprite = Sprite.male4NPC_forward;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male4NPC_forward1;
                        }
                        else this.sprite = Sprite.male4NPC_forward2;
                    }
                    break;
                case 1:
                    this.sprite = Sprite.male4NPC_right;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male4NPC_right1;
                        }
                        else this.sprite = Sprite.male4NPC_right2;
                    }
                    break;
                case 2:
                    this.sprite = Sprite.male4NPC_back;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male4NPC_back1;
                        }
                        else this.sprite = Sprite.male4NPC_back2;
                    }
                    break;
                case 3:
                    this.sprite = Sprite.male4NPC_left;
                    if (walking) {
                        if (anim % 20 > 9) {
                            this.sprite = Sprite.male4NPC_left1;
                        }
                        else this.sprite = Sprite.male4NPC_left2;
                    }
                    break;
                }
                break;
        }
    }
    
    public void initCut(CutScenes cut) {
        this.cut = cut;
    }
    
    public void setRoute(int routeNum) {
        byte[][] copyArray = cut.getRoute(routeNum);
        route = new byte[copyArray.length][3];
        for (int i = 0; i < copyArray.length; i++) {
            for (int ii = 0; ii < 3; ii++) {
                route[i][ii] = copyArray[i][ii];
            }
        }
    }
    
    public boolean openShoppingMenu() {
        return openShop;
    }
    
    public void closeShop() {
        openShop = false;
    }

    public boolean npcHere(int x, int y) {
        if (this.x - 13 <= x && x <= this.x + 13 && this.y - 16 <= y && y <= this.y + 15) return true;
        else return false;
    }

    public boolean npcAhead(int x, int y) {
        if (this.x + 3 <= x && x <= this.x + 28 && this.y + 2 <= y && y <= this.y + 32) return true;
        else return false;
    }
    
    public String[] getConversationDialog(int dir) throws FileNotFoundException {
        this.dir = dir;
        talking = true;
        if (dialogPath == null) {
            if (initialConversation && finalConvo != null) {
                String[] array = new String[1];
                array[0] = initialConvo;
                return array;
            }
            else if (!initialConversation && finalConvo != null) {
                String[] array = new String[1];
                array[0] = finalConvo;
                return array;
            }
            else {
                String[] array = new String[1];
                array[0] = "I don't have any script";
                return array;
            }
        }
        else {
            String s1;
            String s2;
            String s3;
            boolean iSaySo = true;
            BufferedReader textReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dialogPath)));
            Scanner inputFile = new Scanner(textReader);
            s1 = inputFile.nextLine();
            if (nextMessage > Integer.parseInt(s1)) nextMessage = 1;
            do {
                s2 = inputFile.nextLine();
                if (Integer.parseInt(s2) == nextMessage) {
                    iSaySo = false;
                    nextMessage++;
                }
                else {
                    s3 = inputFile.nextLine();
                        for (int i = 0; i < Integer.parseInt(s3) + 1; i++) {
                            inputFile.nextLine();
				}
                }
            }
            while (iSaySo);
            int lines = Integer.parseInt(inputFile.nextLine());
            String[] convo = new String[lines];
            for (int i = 0; i < lines; i++) {
                    convo[i] = inputFile.nextLine();
            }
            inputFile.close();
            return convo;
        }
    }
    
    private void followRoute() {
        try {
            int xa = (int) route[index][0];
            int ya = (int) route[index++][1];
            moveNpc(xa, ya);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            index = 0;
            moveAlongRoute = false;
            walking = false;
            movedOutOfWay = true;
        }
    }
    
    public void update() {
        if (moveAlongRoute) {
            followRoute();
        }
        if (anim < 7500) anim++;
        else anim = 0;
        
        if (turnTimer != timeTillTurn || talking && !moveAlongRoute) {
            if (turnTimer < 420) turnTimer++;
            else turnTimer = 0;
        }
        else if (!moveAlongRoute) {
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
        screen.renderSprite(x - 16, y - 16, sprite, true);
    }

}
