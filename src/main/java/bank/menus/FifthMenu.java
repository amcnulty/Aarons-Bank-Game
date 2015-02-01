/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.menus.panes.ItemPane;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class FifthMenu extends Menu {
    
    private boolean firstPage = true;
    private boolean showNextButton = false;
    private boolean showPreviousButton = false;
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    private ClickableButton nextButton;
    private ClickableButton previousButton;
    private ArrayList<ItemPane> itemPane = new ArrayList<>();
    
    public FifthMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // weapons button
        buttons.add(new ClickableButton(10, xloc, 25, yloc, "WEAPONS", 3));
        // add 500 dollars button
        buttons.add(new ClickableButton(61, xloc, 25, yloc, "MAIN", 6));
        // adding button to show bobbie
        buttons.add(new ClickableButton(100, xloc, 25, yloc, "ITEMS", 5));
        // adding next button
        nextButton = new ClickableButton(140, xloc, 195, yloc, "NEXT", 99);
        // adding previous button
        previousButton = new ClickableButton(135, xloc, 195, yloc, "PREVIOUS", 98);
    }
    
    public void setPanes() {
        itemPane.clear();
        byte count = 0;
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getClass().getSimpleName().equals("ArmorItem")) {
                itemPane.add(new ItemPane(count, player.inventory.get(i), "EQUIP", 9));
                count++;
            }
        }
        if (count < 8) {
            firstPage = true;
            showNextButton = false;
        }
        else if (count > 7) {
            if (firstPage) {
                showNextButton = true;
            }
            else if (!firstPage) {
                showPreviousButton = true;
            }
        }
    }
    
    private Sprite buildNameBoxSprite(String itemName) {
        int width = ((itemName.length() * 6) + 6);
        int[] pixels = new int[width * 21];
        for (int y = 0; y < 21; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xffFF384B;
            }
        }
        for (int i = 0; i < width; i++) {
            pixels[i] = 0;
            pixels[i + 20 * width] = 0;
        }
        for (int i = 0; i < 21; i++) {
            pixels[0 + i * width] = 0;
            pixels[(width * (i + 1)) - 1] = 0;
        }
        Sprite newSprite = new Sprite(pixels, width, 21);
        return newSprite;
    }
    
    public void update() {
        super.update();
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
        if (firstPage) {
            for (int i = 0; i < 7; i++) {
                try {
                    itemPane.get(i).update();
                    if (itemPane.get(i).doAction()) {
                        currentAction = itemPane.get(i).action;
                        item = itemPane.get(i).item;
                        doAction = true;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            if (showNextButton) {
                nextButton.setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
                nextButton.update();
                if (nextButton.doAction()) {
                    showNextButton = false;
                    firstPage = false;
                }
            }
        }
        else if (!firstPage) {
            for (int i = 7; i < 14; i++) {
                try {
                    itemPane.get(i).update();
                    if (itemPane.get(i).doAction()) {
                        currentAction = itemPane.get(i).action;
                        item = itemPane.get(i).item;
                        doAction = true;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            previousButton.setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
            previousButton.update();
            if (previousButton.doAction()) {
                showPreviousButton = false;
                firstPage = true;
                showNextButton = true;
            }
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        font.renderSmallCharacters(27, 9, "Armor", screen);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
        if (firstPage) {
            for (int i = 0; i < 7; i++) {
                try {
                    itemPane.get(i).render(screen);
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            if (showNextButton) nextButton.render(screen);
            for (int i = 0; i < 7; i++) {
                try {
                    if (Mouse.getX() >= 74 && Mouse.getX() <= 122 && Mouse.getY() >= 178 + (60 * i) && Mouse.getY() <= 226 + (60 * i) && itemPane.get(i) != null) {
                        String lengthOfBox = "SPEED CHANGE - " + Integer.toString(itemPane.get(i).item.getSpeedChange());
                        Sprite newSprite = buildNameBoxSprite(lengthOfBox);
                        screen.renderSprite((Mouse.getX() / 3) + 10, (Mouse.getY() / 3), newSprite, false);
                        font.renderSuperSmallCharacters2((Mouse.getX() / 3) + 15, (Mouse.getY() / 3) + 4, "PROTECTION -  " + Integer.toString(itemPane.get(i).item.getDefenceChange()), screen);
                        font.renderSuperSmallCharacters2((Mouse.getX() / 3) + 15, (Mouse.getY() / 3) + 12, "SPEED CHANGE - " + Integer.toString(itemPane.get(i).item.getSpeedChange()), screen);
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        else if (!firstPage) {
            for (int i = 7; i < 14; i++) {
                try {
                    itemPane.get(i).render(screen);
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            previousButton.render(screen);
            for (int i = 0; i < 7; i++) {
                try {
                    if (Mouse.getX() >= 74 && Mouse.getX() <= 122 && Mouse.getY() >= 178 + (60 * i) && Mouse.getY() <= 226 + (60 * i) && itemPane.get(i + 7) != null) {
                        String lengthOfBox = "SPEED CHANGE - " + Integer.toString(itemPane.get(i + 7).item.getSpeedChange());
                        Sprite newSprite = buildNameBoxSprite(lengthOfBox);
                        screen.renderSprite((Mouse.getX() / 3) + 10, (Mouse.getY() / 3), newSprite, false);
                        font.renderSuperSmallCharacters2((Mouse.getX() / 3) + 15, (Mouse.getY() / 3) + 4, "PROTECTION -  " + Integer.toString(itemPane.get(i + 7).item.getDefenceChange()), screen);
                        font.renderSuperSmallCharacters2((Mouse.getX() / 3) + 15, (Mouse.getY() / 3) + 12, "SPEED CHANGE - " + Integer.toString(itemPane.get(i + 7).item.getSpeedChange()), screen);
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
    }    
    
}
