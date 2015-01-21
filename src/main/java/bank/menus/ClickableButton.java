/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Font;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.Items;

/**
 *
 * @author Aaron
 */
public class ClickableButton {
    
    
    private Sprite notPressed;      //Image of button not being pressed
    private Sprite pressed;         //Image of button being pressed
    private Sprite button;          //What gets send to render will either be button = pressed; or button = notPressed;
    private int[] pixels;
    private Font font;
    
    public int action;
    public Items item;
    
    private final int SMALL_BUTTON_HEIGHT = 10; // height of a button with the 5x5 character sheet
    private int length;     // used to make the sprite
    private int leftX;      // have been multiplied by scale of three. used for mouse button checking
    private int rightX;     // used for mouse button clicking
    private int topY;       // used for mouse
    private int bottomY;    // used for mouse
    private int renderX;    // render x possition
    private int renderY;    // render y possition
    private String buttonText;  // the text of the button
    private boolean buttonWasPressed;
    private boolean startAction;
    private boolean doAction;
    private int timer;          // makes a delay so the button can be shown coming back up for a short period maybe a half second

    public ClickableButton(int xp, int xloc, int yp, int yloc, String text, int action) {
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
        this.action = action;
        setNotPressed();
        setPressed();
        button = notPressed;
    }
    
        public ClickableButton(int xp, int xloc, int yp, int yloc, String text, int action, Items item) {
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
        this.action = action;
        this.item = item;
        setNotPressed();
        setPressed();
        button = notPressed;
    }

    private void setNotPressed() {
        for (int x = 0; x < length; x++) {
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
        notPressed = new Sprite(pixels, length, SMALL_BUTTON_HEIGHT);
    }
    
    private void setPressed() {
        for (int x = 0; x < length; x++) {
            pixels[x] = 0;
            pixels[x + 9 * length] = (x == length - 1) ? 0 : 0xff7a7a7a;
            if (x == 0) {
                pixels[x + 1 * length] = 0xff7a7a7a;
                pixels[x + 2 * length] = 0xff7a7a7a;
                pixels[x + 3 * length] = 0xff7a7a7a;
                pixels[x + 4 * length] = 0xff7a7a7a;
                pixels[x + 5 * length] = 0xff7a7a7a;
                pixels[x + 6 * length] = 0xff7a7a7a;
                pixels[x + 7 * length] = 0xff7a7a7a;
                pixels[x + 8 * length] = 0xff7a7a7a;
            }
            else if (x == length - 3) {
                pixels[x + 1 * length] = 0xff6D6DDB;
                pixels[x + 2 * length] = 0xff7F7FFF;
                pixels[x + 3 * length] = 0xff7F7FFF;
                pixels[x + 4 * length] = 0xff7F7FFF;
                pixels[x + 5 * length] = 0xff7F7FFF;
                pixels[x + 6 * length] = 0xff7F7FFF;
                pixels[x + 7 * length] = 0xff7F7FFF;
                pixels[x + 8 * length] = 0xff7F7FFF;
            }
            else if (x > 0 && x < length - 3) {
                pixels[x + 1 * length] = 0xff6D6DDB;
                pixels[x + 2 * length] = 0xff7F7FFF;
                pixels[x + 3 * length] = 0xff7F7FFF;
                pixels[x + 4 * length] = 0xff7F7FFF;
                pixels[x + 5 * length] = 0xff7F7FFF;
                pixels[x + 6 * length] = 0xff7F7FFF;
                pixels[x + 7 * length] = 0xff7F7FFF;
                pixels[x + 8 * length] = 0xff7F7FFF;
            }
            else if (x == length - 2) {
                pixels[x + 1 * length] = 0xff6D6DDB;
                pixels[x + 2 * length] = 0xff6D6DDB;
                pixels[x + 3 * length] = 0xff6D6DDB;
                pixels[x + 4 * length] = 0xff6D6DDB;
                pixels[x + 5 * length] = 0xff6D6DDB;
                pixels[x + 6 * length] = 0xff6D6DDB;
                pixels[x + 7 * length] = 0xff6D6DDB;
                pixels[x + 8 * length] = 0xff6D6DDB;
            }
            else if (x == (length - 1)) {
                pixels[x + 1 * length] = 0;
                pixels[x + 2 * length] = 0;
                pixels[x + 3 * length] = 0;
                pixels[x + 4 * length] = 0;
                pixels[x + 5 * length] = 0;
                pixels[x + 6 * length] = 0;
                pixels[x + 7 * length] = 0;
                pixels[x + 8 * length] = 0;
            }
        }
        Sprite[] characters = font.returnCharacterSprites(buttonText);
        for (int i = 0; i < characters.length; i++) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (characters[i].pixels[x + y * 5] == 0xffDEDEDE) {
                        pixels[((6 * i) + x + 2) + (y + 3) * length] = 0xffDEDEDE;
                    }
                }
            }
        }
        pressed = new Sprite(pixels, length, SMALL_BUTTON_HEIGHT);
    }
    
    public void setButton(int x, int y, int b) {
        if (x >= leftX && x <= rightX && y >= topY && y <= bottomY && (b == 1 || b == 3)) {
            //System.out.println("CLICKING ON A BUTTON");
            button = pressed;
            buttonWasPressed = true;
        }
        else if (x < leftX || x > rightX || y < topY || y > bottomY) {
            //System.out.println("NOT OVER A BUTTON");
            button = notPressed;
            buttonWasPressed = false;
        }
        else if (x >= leftX && x <= rightX && y >= topY && y <= bottomY && b == -1 && buttonWasPressed) {
            button = notPressed;
            startAction = true;
            buttonWasPressed = false;
        }
    }
    
    public boolean doAction() {
        if (doAction) {
            doAction = false;
            return true;
        }
        else {
            return false;
        }
    }
    
    public void update() {
        //System.out.println("From the clickable button class X: " + Mouse.getX() + "  Y: " + Mouse.getY());
        if (startAction) {
            timer++;
            if (timer == 10) {
                timer = 0;
                startAction = false;
                doAction = true;
            }
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(renderX, renderY, button, false);
    }

}
