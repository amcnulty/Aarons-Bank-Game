/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.titleMenu;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;

/**
 *
 * @author Aaron
 */
public class TitleMenu {

    private Sprite background;
    private Sprite pressedButton;
    private int xp, yp;
    private int characterNum;
    private boolean pressedDown = false;
    private boolean buttonWasPressed = false;
    private boolean characterWasSelected = false;

    public boolean startGame = false;
    public boolean startGameFromLoad = false;
    public int characterInfo;

    private boolean[] backButton;

    // Title screen buttons
    private boolean[] newGameButton;
    private boolean[] loadGameButton;
    private boolean[] settingsButton;
    private boolean[] exitButton;

    // New game screen buttons
    private boolean[] bradButton;
    private boolean[] aaronButton;
    private boolean[] kellyButton;
    private boolean[] annetteButton;
    private boolean[] brianButton;

    // Load game screen buttons
    private boolean[] bradLoadButton;
    private boolean[] aaronLoadButton;
    private boolean[] kellyLoadButton;
    private boolean[] annetteLoadButton;
    private boolean[] brianLoadButton;

    // Settings buttons


    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;

    public TitleMenu(Sprite background) {
        this.background = background;
        SCREEN_WIDTH = background.getWidth() * 3;
        SCREEN_HEIGHT = background.getHeight() * 3;
        newGameButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        loadGameButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        settingsButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        exitButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        backButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        bradButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        aaronButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        kellyButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        annetteButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        brianButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        bradLoadButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        aaronLoadButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        kellyLoadButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        annetteLoadButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        brianLoadButton = new boolean[SCREEN_WIDTH * SCREEN_HEIGHT];
        setButtons();
    }

    private void setButtons() {
        for (int y = 88 * 3; y <= 105 * 3; y++) {
            for (int x = 27 * 3; x <= 121 * 3; x++) {
                newGameButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 114 * 3; y <= 131 * 3; y++) {
            for (int x = 27 * 3; x <= 132 * 3; x++) {
                loadGameButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 140 * 3; y <= 157 * 3; y++) {
            for (int x = 27 * 3; x <= 124 * 3; x++) {
                settingsButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 166 * 3; y <= 183 * 3; y++) {
            for (int x = 27 * 3; x <= 75 * 3; x++) {
                exitButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 207 * 3; y <= 224 * 3; y++) {
            for (int x = 358 * 3; x <= 409 * 3; x++) {
                backButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 95 * 3; y <= 128 * 3; y++) {
            for (int x = 43 * 3; x <= 73 * 3; x++) {
                bradButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 95 * 3; y <= 128 * 3; y++) {
            for (int x = 109 * 3; x <= 139 * 3; x++) {
                aaronButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 191 * 3; x <= 221 * 3; x++) {
                kellyButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 261 * 3; x <= 292 * 3; x++) {
                annetteButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 355 * 3; x <= 384 * 3; x++) {
                brianButton[x + y * SCREEN_WIDTH] = true;
            }
        }for (int y = 95 * 3; y <= 128 * 3; y++) {
            for (int x = 43 * 3; x <= 73 * 3; x++) {
                bradLoadButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 95 * 3; y <= 128 * 3; y++) {
            for (int x = 109 * 3; x <= 139 * 3; x++) {
                aaronLoadButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 191 * 3; x <= 221 * 3; x++) {
                kellyLoadButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 261 * 3; x <= 292 * 3; x++) {
                annetteLoadButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        for (int y = 91 * 3; y <= 126 * 3; y++) {
            for (int x = 355 * 3; x <= 384 * 3; x++) {
                brianLoadButton[x + y * SCREEN_WIDTH] = true;
            }
        }
        
    }

    public void setCharacter(int characterInfo) {
        this.characterInfo = characterInfo;
        startGame = true;
    }
    
    public void loadCharacter(int characterInfo) {
        this.characterInfo = characterInfo;
        startGameFromLoad = true;
    }

    public void update() {
        pressedDown = false;
        try {
            if (background.equals(Sprite.titleScreen)) {
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && (!exitButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !settingsButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !loadGameButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !newGameButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH])) {
                    buttonWasPressed = false;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && newGameButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 27;
                    yp = 88;
                    pressedButton = Sprite.newGamePressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && loadGameButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 27;
                    yp = 114;
                    pressedButton = Sprite.loadGamePressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && settingsButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 27;
                    yp = 140;
                    pressedButton = Sprite.settingsPressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && exitButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 27;
                    yp = 166;
                    pressedButton = Sprite.exitPressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if (Mouse.getButton() == -1 && buttonWasPressed == true) {
                    if (yp == 88) background = Sprite.newGameScreen;
                    else if (yp == 114) background = Sprite.loadGameScreen;
                    else if (yp == 140) background = Sprite.settingsScreen;
                    else if (yp == 166) System.exit(0);
                    buttonWasPressed = false;
                }
            }
            else if (background.equals(Sprite.newGameScreen)) {
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && (!bradButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !aaronButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !kellyButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !annetteButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !brianButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH])) {
                    buttonWasPressed = false;
                    characterWasSelected = false;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && bradButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 1;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && aaronButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 2;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && kellyButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 3;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && annetteButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 4;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && brianButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 5;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 358;
                    yp = 207;
                    pressedButton = Sprite.backPressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if (Mouse.getButton() == -1 && characterWasSelected == true) {
                    setCharacter(characterNum);
                }
                if (Mouse.getButton() == -1 && buttonWasPressed == true) {
                    if (yp == 207) background = Sprite.titleScreen;
                }
            }
            else if (background.equals(Sprite.loadGameScreen)) {
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && (!backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || !bradLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || aaronLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || kellyLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || annetteLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH] || brianLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH])) {
                    buttonWasPressed = false;
                    characterWasSelected = false;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && bradLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 1;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && aaronLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 2;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && kellyLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 3;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && annetteLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 4;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && brianLoadButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    characterWasSelected = true;
                    characterNum = 5;
                }
                if (Mouse.getButton() == -1 && characterWasSelected == true) {
                    loadCharacter(characterNum);
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 358;
                    yp = 207;
                    pressedButton = Sprite.backPressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if (Mouse.getButton() == -1 && buttonWasPressed == true) {
                    if (yp == 207) background = Sprite.titleScreen;
                }
            }
            else if (background.equals(Sprite.settingsScreen)) {
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && !backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    buttonWasPressed = false;
                }
                if ((Mouse.getButton() == 1 || Mouse.getButton() == 3) && backButton[Mouse.getX() + Mouse.getY() * SCREEN_WIDTH]) {
                    xp = 358;
                    yp = 207;
                    pressedButton = Sprite.backPressedButton;
                    pressedDown = true;
                    buttonWasPressed = true;
                }
                if (Mouse.getButton() == -1 && buttonWasPressed == true) {
                    if (yp == 207) background = Sprite.titleScreen;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(0, 0, background, false);
        if (pressedDown) {
            screen.renderSprite(xp, yp, pressedButton, false);
        }
    }

}
