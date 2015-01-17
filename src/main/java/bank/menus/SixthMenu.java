/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.input.Mouse;
import bank.menus.panes.ItemPane;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class SixthMenu extends Menu {
    
    private boolean firstPage = true;
    private boolean showNextButton = false;
    private boolean showPreviousButton = false;
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    private ClickableButton nextButton;
    private ClickableButton previousButton;
    private ArrayList<ItemPane> itemPane = new ArrayList<>();
    
    public SixthMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // weapons button
        buttons.add(new ClickableButton(10, xloc, 25, yloc, "WEAPONS", 3));
        // add 500 dollars button
        buttons.add(new ClickableButton(61, xloc, 25, yloc, "ARMOR", 4));
        // adding button to show bobbie
        buttons.add(new ClickableButton(100, xloc, 25, yloc, "MAIN", 6));
        // adding next button
        nextButton = new ClickableButton(140, xloc, 195, yloc, "NEXT", 99);
        // adding previous button
        previousButton = new ClickableButton(135, xloc, 195, yloc, "PREVIOUS", 98);
    }
    
    public void setPanes() {
        itemPane.clear();
        byte count = 0;
        for (int i = 0; i < player.inventory.size(); i++) {
            if (player.inventory.get(i).getClass().getSimpleName().equals("UsableItem")) {
                itemPane.add(new ItemPane(count, player.inventory.get(i), "USE", 7));
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
                    if (itemPane.get(i).doAction) {
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
        font.renderSmallCharacters(27, 9, "ITEMS", screen);
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
        }
    }
}
