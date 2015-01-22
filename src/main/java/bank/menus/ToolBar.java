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

/**
 *
 * @author Aaron
 */
public class ToolBar {
	
    private Sprite toolBarSprite;
    private Sprite inventoryMenuSprite = Sprite.invMenu1;
    private Sprite pressedButton;
    private int xp, yp;
    public boolean showingInv = false;
    public boolean showingPauseMenu = false;
    public boolean goToSave = false;
    public boolean goToLoad = false;
    private boolean pressedDown = false;
    private boolean savePressedDown = false;
    private boolean loadPressedDown = false;
    private boolean exitPressedDown = false;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private boolean[] inventoryButton;
    private boolean[] inventoryExitButton;
    private boolean[] menuSaveButton;
    private boolean[] menuLoadButton;
    private boolean[] menuExitButton;
    private Keyboard key;
    private Font font = new Font();
    private Player player;

    public ToolBar(Sprite sprite, Keyboard key, int screenWidth, int screenHeight) {
        SCREEN_WIDTH = screenWidth * 3;
        SCREEN_HEIGHT = screenHeight * 3;
        this.toolBarSprite = sprite;
        this.key = key;
        inventoryButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        inventoryExitButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        menuSaveButton = newSetButtons(187, 92, Sprite.savePressedButton);
        menuLoadButton = newSetButtons(187, 106, Sprite.loadPressedButton);
        menuExitButton = newSetButtons(187, 120, Sprite.smallExitPressedButton);
        setButtons();
    }
    
    public void initPlayer(Player player) {
        this.player = player;
    }

    private boolean[] newSetButtons(int xLoc, int yLoc, Sprite Pressed) {
        boolean[] buttonLocation = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        for (int y = yLoc * 3; y <= (yLoc * 3) + (Pressed.getHeight() * 3); y++) {
            for (int x = xLoc * 3; x <= (xLoc * 3) + (Pressed.getWidth() * 3); x++) {
                buttonLocation[x + y * SCREEN_WIDTH] = true;
            }
        }
        return buttonLocation;
    }

    private void setButtons() {
        for (int y = 28 * 3; y <= 37 * 3; y++) {
            for (int x = SCREEN_WIDTH - 120 * 3; x <= SCREEN_WIDTH - 96 * 3; x++) {
                inventoryButton[x + y * SCREEN_WIDTH] = true; 
            }
        }
        for (int y = 582; y <= 609; y++) {
            for (int x = 744; x <= 825; x++) {
                inventoryExitButton[x + y * SCREEN_WIDTH] = true;
            }
        }
    }

    public void update() {
        goToSave = false;
        goToLoad = false;
        if (key.escape && showingPauseMenu == false && !key.checked) {
            showingPauseMenu = true;
            key.checked = true;
        }
        else if (key.escape && showingPauseMenu == true && !key.checked) {
            showingPauseMenu = false;
            key.checked = true;
        }
        this.toolBarSprite = Sprite.toolBar1;
        inventoryMenuSprite = Sprite.invMenu1;
        try {
            if (!showingInv && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && inventoryButton[(Mouse.getX() + Mouse.getY() * SCREEN_WIDTH)] && !showingPauseMenu) {
                this.toolBarSprite = Sprite.toolBar1;
                pressedDown = true;
            }
            else if(!showingInv && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && !inventoryButton[(Mouse.getX() + Mouse.getY() * SCREEN_WIDTH)] && !showingPauseMenu) {
                pressedDown = false;
            }
            if (!showingInv && Mouse.getButton() == -1 && pressedDown == true && !showingPauseMenu) {
                pressedDown = false;
                showingInv = false;
            }
            if (showingInv && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && inventoryExitButton[(Mouse.getX() + Mouse.getY() * SCREEN_WIDTH)] && !showingPauseMenu) {
                inventoryMenuSprite = Sprite.invMenu2;
                pressedDown = true;
            }
            else if (showingInv && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && !inventoryExitButton[(Mouse.getX() + Mouse.getY() * SCREEN_WIDTH)] && !showingPauseMenu) {
                pressedDown = false;
            }
            if (showingInv && Mouse.getButton() == -1 && pressedDown == true && !showingPauseMenu) {
                pressedDown = false;
                showingInv = false;
            }
            //savePressedDown = false;
            if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && menuSaveButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                pressedButton = Sprite.savePressedButton;
                savePressedDown = true;
                xp = 561 / 3;
                yp = 276 / 3;
            }
            else if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && !(menuSaveButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH])) {
                savePressedDown = false;
            }
            if (showingPauseMenu && Mouse.getButton() == -1 && savePressedDown == true) {
                goToSave = true;
                savePressedDown = false;
            }
            if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && menuLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                pressedButton = Sprite.loadPressedButton;
                loadPressedDown = true;
                xp = 561 / 3;
                yp = 318 / 3;
            }
            else if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && !menuLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                loadPressedDown = false;
            }
            if (showingPauseMenu && Mouse.getButton() == -1 && loadPressedDown == true) {
                goToLoad = true;
                loadPressedDown = false;
            }
            if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && menuExitButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                pressedButton = Sprite.smallExitPressedButton;
                exitPressedDown = true;
                xp = 187;
                yp = 120;
            }
            else if (showingPauseMenu && (Mouse.getButton() == 1 ||Mouse.getButton() == 3) && !(menuExitButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH])) {
                exitPressedDown = false;
            }
            if (showingPauseMenu && Mouse.getButton() == -1 && exitPressedDown == true) {
                System.exit(0);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(screen.width - 130, 0, toolBarSprite, false);
        font.renderSuperSmallCharacters2(299, 11, player.name, screen);
        font.renderSuperSmallCharacters(334, 40, Integer.toString(player.cash), screen);
        font.renderSuperSmallCharacters(320, 20, Integer.toString(player.playerLevel), screen);
        font.renderSuperSmallCharacters(392, 11, Integer.toString(player.equipedHealth), screen);
        font.renderSuperSmallCharacters(398, 21, Integer.toString(player.equipedAttack), screen);
        font.renderSuperSmallCharacters(398, 31, Integer.toString(player.equipedDefence), screen);
        font.renderSuperSmallCharacters(398, 41, Integer.toString(player.equipedSpeed), screen);
        if (showingInv) {
            screen.renderSprite(screen.width / 2 - Sprite.invMenu1.getWidth() / 2, screen.height / 2 - Sprite.invMenu1.getHeight() / 2, inventoryMenuSprite, false);
        }
        if (showingPauseMenu) {
            screen.renderSprite(screen.width / 2 - Sprite.pauseMenu.getWidth() / 2, screen.height / 2 - Sprite.pauseMenu.getHeight() / 2, Sprite.pauseMenu, false);
            if (savePressedDown || loadPressedDown || exitPressedDown) {
                screen.renderSprite(xp, yp, pressedButton, false);
            }
        }
    }
}
