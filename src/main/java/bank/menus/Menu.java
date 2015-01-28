/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.entity.mob.Player;
import bank.graphics.Font;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Keyboard;
import bank.input.Mouse;
import bank.inventory.ArmorItem;
import bank.inventory.Items;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Aaron
 */
public class Menu {
    
    protected Player player;
    protected Sprite menuBackground;
    protected Font font = new Font();
    private Mouse mouse;
    protected Keyboard key;
    protected Random random;
    
    private int width, height;
    protected int xloc, yloc;
    private int[] pixels;
    
    //temporary exit button bounds
    private int leftX;
    private int rightX;
    private int topY;
    private int bottomY;
    
    public Items item;
    public boolean doAction;
    public boolean isOpen;
    public int currentAction;
    
    public int item1rand;
    public int item2rand;
    public int item3rand;
    public int item4rand;
    public int item5rand;
    public int item6rand;
    
    public static int FAVORITEFOODMENU = 9;
    public static int SWORDYESNOMENU = 10;
    public static int THREEREFERRALYESNOTMENU = 11;
    public static int MAZEGUARDMENU = 12;
    public static int TRAINING_BOOK_FOR_REFERRAL_MENU = 13;
    public static int STORE_ONE_MENU = 14;
    
    public static Menu firstMenu = new FirstMenu(100, 150, 20, 20);
    public static Menu secondMenu = new YesNoMenu(18, 19);
    public static Menu inventoryMenu = new ThirdMenu(200, 215, 15, 15);
    public static Menu weaponsMenu = new FourthMenu(200, 215, 15, 15);
    public static Menu armorMenu = new FifthMenu(200, 215, 15, 15);
    public static Menu itemsMenu = new SixthMenu(200, 215, 15, 15);
    public static Menu chestMenu = new SeventhMenu(160, 50, 115, 170);
    public static Menu yesNoMenu = new YesNoReferralMenu(190, 110, 120, 70);
    public static Menu favoriteFoodMenu = new FavoriteFoodMenu(250, 110, 20, 20);
    public static Menu swordYesNoMenu = new YesNoMenu(24, 25);
    public static Menu threeReferralYesNoMenu = new YesNoMenu(26, 27);
    public static Menu mazeGuardMenu = new YesNoMenu(28, 29);
    public static Menu trainingBookReferralMenu = new YesNoMenu(30, 31);
    public static Menu storeOneMenu = new StoreOneMenu(270, 210, 10, 20);
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    
    public Menu(int width, int height, int xloc, int yloc) {
        this.width = width;
        this.height = height;
        this.xloc = xloc;
        this.yloc = yloc;
        mouse = new Mouse();
        random = new Random();
        pixels = new int[width * height];
        addMenuTheme();
        addButtons();
        //addNewClickableButton(2, 2, 15, 8);
        menuBackground = new Sprite(pixels, width, height);
    }
    
    private void addButtons() {
        // exit button
        buttons.add(new ClickableButton(2, xloc, 2, yloc, "EXIT", 0));
    }
    
    private void addMenuTheme() {
        // border fade
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xff7F7FFF;
            }
        }
        for (int y = 2; y < height - 2; y++) {
            for (int x = 2; x < width - 2; x++) {
                pixels[x + y * width] = 0xFF8484FF;
            }
        }
        for (int y = 4; y < height - 4; y++) {
            for (int x = 4; x < width - 4; x++) {
                pixels[x + y * width] = 0xFF8989FF;
            }
        }
        for (int y = 6; y < height - 6; y++) {
            for (int x = 6; x < width - 6; x++) {
                pixels[x + y * width] = 0xFF8E8EFF;
            }
        }
        for (int y = 8; y < height - 8; y++) {
            for (int x = 8; x < width - 8; x++) {
                pixels[x + y * width] = 0xFF9393FF;
            }
        }
    }
    
    private void addNewClickableButton(int xp, int yp, int length, int height) {
        // temporaty exit button
        leftX = (xp + xloc) * 3;
        rightX = leftX + (length * 3);
        topY = (yp + yloc) * 3;
        bottomY = topY + (height * 3);
        for (int x = xp; x < length + xp; x++) {
            pixels[x + yp * width] = 0;
        }
        for (int y = yp; y < height + yp; y++) {
            pixels[xp + y * width] = 0;
        }
        for (int y = yp; y < height + yp; y++) {
            pixels[(length + xp - 1) + y * width] = 0;
        }
        for (int x = xp; x < length + xp; x++) {
            pixels[x + (height + yp - 1) * width] = 0;
        }
        
    }
     public void initKeyboard(Keyboard key) {
         this.key = key;
     }
    
    public void setItemSpriteAndName(Sprite itemSprite, String itemName) {
    }
    
    public void randomizeTotals() {
    }
    
    public void setPanes() {
    }
    
    // temporary way of checking for buttons to exit menu
    private boolean checkButtons(int x, int y) {
        if (x >= leftX && x <= rightX && y >= topY && y <= bottomY) return true;
        return false;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void update() {
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
        screen.renderSprite(xloc, yloc, menuBackground, false);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
    }
    
    
    
   /** 
    private class ClickableButton {
        
        private Sprite notPressed;
        private Sprite pressed;
        private int[] pixels;
        private Font font;
        
        private final int SMALL_BUTTON_HEIGHT = 10; // height of a button with the 5x5 character sheet
        private int length;     // used to make the sprite
        private int leftX;      // have been multiplied by scale of three. used for mouse button checking
        private int rightX;     // used for mouse button clicking
        private int topY;       // used for mouse
        private int bottomY;    // used for mouse
        private int renderX;    // render x possition
        private int renderY;    // render y possition
        private String buttonText;  // the text of the button
        
        public ClickableButton(int xp, int yp, String text) {
            font = new Font();
            length = 4 + (6 * text.length());
            buttonText = text;
            renderX = xp + xloc;
            renderY = yp + yloc;
            leftX = (xp + xloc) * 3;
            rightX = leftX + (length * 3);
            topY = (yp + yloc) * 3;
            bottomY = topY + (SMALL_BUTTON_HEIGHT * 3);
            pixels = new int[length * SMALL_BUTTON_HEIGHT];
            setNotPressed();
            //setPressed();
        }
        
        private void setNotPressed() {
            for (int x = 0; x < length; x++) {
                System.out.println("X:  " + x);
                pixels[x] = (x == 0) ? 0 : 0xff7a7a7a;
                pixels[x + 9 * length] = 0;
                if (x == 0) {
                    pixels[x + 1 * length] = 0;
                    pixels[x + 2 * length] = 0;
                    pixels[x + 3 * length] = 0;
                    pixels[x + 4 * length] = 0;
                    pixels[x + 5 * length] = 0;
                    pixels[x + 6 * length] = 0;
                    pixels[x + 7 * length] = 0;
                    pixels[x + 8 * length] = 0;
                }
                else if (x == 1) {
                    pixels[x + 1 * length] = 0xff6D6DDB;
                    pixels[x + 2 * length] = 0xff6D6DDB;
                    pixels[x + 3 * length] = 0xff6D6DDB;
                    pixels[x + 4 * length] = 0xff6D6DDB;
                    pixels[x + 5 * length] = 0xff6D6DDB;
                    pixels[x + 6 * length] = 0xff6D6DDB;
                    pixels[x + 7 * length] = 0xff6D6DDB;
                    pixels[x + 8 * length] = 0xff6D6DDB;
                }
                else if (x > 1 && x < length - 2) {
                    System.out.println("HELLO FROM THE MIDDLE");
                    pixels[x + 1 * length] = 0xff7F7FFF;
                    pixels[x + 2 * length] = 0xff8484FF;
                    pixels[x + 3 * length] = 0xff8484FF;
                    pixels[x + 4 * length] = 0xff8484FF;
                    pixels[x + 5 * length] = 0xff8484FF;
                    pixels[x + 6 * length] = 0xff8484FF;
                    pixels[x + 7 * length] = 0xff8484FF;
                    pixels[x + 8 * length] = 0xff6D6DDB;
                }
                else if (x == length - 2) {
                    System.out.println("HELLO FROM LENGTH - 2");
                    pixels[x + 1 * length] = 0xff7F7FFF;
                    pixels[x + 2 * length] = 0xff7F7FFF;
                    pixels[x + 3 * length] = 0xff7F7FFF;
                    pixels[x + 4 * length] = 0xff7F7FFF;
                    pixels[x + 5 * length] = 0xff7F7FFF;
                    pixels[x + 6 * length] = 0xff7F7FFF;
                    pixels[x + 7 * length] = 0xff7F7FFF;
                    pixels[x + 8 * length] = 0xff6D6DDB;
                }
                else if (x == (length - 1)) {
                    System.out.println("HELLO FROM LENGTH - 1");
                    pixels[x + 1 * length] = 0xff7a7a7a;
                    pixels[x + 2 * length] = 0xff7a7a7a;
                    pixels[x + 3 * length] = 0xff7a7a7a;
                    pixels[x + 4 * length] = 0xff7a7a7a;
                    pixels[x + 5 * length] = 0xff7a7a7a;
                    pixels[x + 6 * length] = 0xff7a7a7a;
                    pixels[x + 7 * length] = 0xff7a7a7a;
                    pixels[x + 8 * length] = 0xff7a7a7a;
                }
            }
            Sprite[] characters = font.returnCharacterSprites(buttonText);
            for (int i = 0; i < characters.length; i++) {
                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (characters[i].pixels[x + y * 5] == 0xffDEDEDE) {
                            pixels[((6 * i) + x + 3) + (y + 2) * length] = 0xffDEDEDE;
                        }
                    }
                }
            }
            System.out.println(0xff7A7A7A);
            System.out.println(pixels[27]);
            pressed = new Sprite(pixels, length, SMALL_BUTTON_HEIGHT);
        }
        
        public void update() {
            
        }
        
        public void render(Screen screen) {
            screen.renderSprite(renderX, renderY, pressed, false);
        }
        
    }
    */
}
