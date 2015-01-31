/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.menus;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Mouse;
import bank.inventory.UsableItem;
import bank.inventory.WeaponItem;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class FirstMenu extends Menu {
    
    private ArrayList<ClickableButton> buttons = new ArrayList<>();
    
    public FirstMenu(int width, int height, int xloc, int yloc) {
        super(width, height, xloc, yloc);
        addButtons();
    }
    
    private void addButtons() {
        // buy deluxe sword button
        buttons.add(new ClickableButton(50, xloc, 27, yloc, "BUY", 38, WeaponItem.deluxeSword));
        // buy deluxe dagger button
        buttons.add(new ClickableButton(50, xloc, 85, yloc, "BUY", 39, WeaponItem.deluxeDagger));
        // buy heavy sword button
        buttons.add(new ClickableButton(50, xloc, 143, yloc, "BUY", 40, WeaponItem.heavySword));
        // buy black dagger button
        buttons.add(new ClickableButton(190, xloc, 27, yloc, "BUY", 41, WeaponItem.blackDagger));
        // buy ice sword button
        buttons.add(new ClickableButton(190, xloc, 85, yloc, "BUY", 42, WeaponItem.iceSword));
        // buy flame sword button
        buttons.add(new ClickableButton(190, xloc, 143, yloc, "BUY", 43, WeaponItem.flameSword));
        
    }
    
    public void randomizeTotals() {
        item1rand = WeaponItem.deluxeSword.standardPrice + random.nextInt(WeaponItem.deluxeSword.standardPrice) - (WeaponItem.deluxeSword.standardPrice / 3);
        item2rand = WeaponItem.deluxeDagger.standardPrice + random.nextInt(WeaponItem.deluxeDagger.standardPrice) - (WeaponItem.deluxeDagger.standardPrice / 3);
        item3rand = WeaponItem.heavySword.standardPrice + random.nextInt(WeaponItem.heavySword.standardPrice) - (WeaponItem.heavySword.standardPrice / 3);
        item4rand = WeaponItem.blackDagger.standardPrice + random.nextInt(WeaponItem.blackDagger.standardPrice) - (WeaponItem.blackDagger.standardPrice / 3);
        item5rand = WeaponItem.iceSword.standardPrice + random.nextInt(WeaponItem.iceSword.standardPrice) - (WeaponItem.iceSword.standardPrice / 3);
        item6rand = WeaponItem.flameSword.standardPrice + random.nextInt(WeaponItem.flameSword.standardPrice) - (WeaponItem.flameSword.standardPrice / 3);
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
                item = buttons.get(i).item;
                doAction = true;
            }
        }
    }
    
    public void render(Screen screen) {
        super.render(screen);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(screen);
        }
        font.renderSuperSmallCharacters2(23, 39, WeaponItem.deluxeSword.itemName, screen);
        screen.renderSprite(25 + 14, 45, WeaponItem.deluxeSword.itemSprite, false);
        screen.renderSprite(32, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 58 + 5, Integer.toString(item1rand), screen);
        font.renderSuperSmallCharacters2(23, 66 + 5, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70, 66 + 5, Integer.toString(WeaponItem.deluxeSword.getAttackChange()), screen);
        
        
        font.renderSuperSmallCharacters2(23, 97, WeaponItem.deluxeDagger.itemName, screen);
        screen.renderSprite(39, 103, WeaponItem.deluxeDagger.itemSprite, false);
        screen.renderSprite(32, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 121, Integer.toString(item2rand), screen);
        font.renderSuperSmallCharacters2(23, 129, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70, 129, Integer.toString(WeaponItem.deluxeDagger.getAttackChange()), screen);
        
        font.renderSuperSmallCharacters2(23, 155, WeaponItem.heavySword.itemName, screen);
        screen.renderSprite(39, 161, WeaponItem.heavySword.itemSprite, false);
        screen.renderSprite(32, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39, 179, Integer.toString(item3rand), screen);
        font.renderSuperSmallCharacters2(23, 187, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70, 187, Integer.toString(WeaponItem.heavySword.getAttackChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 39, WeaponItem.blackDagger.itemName, screen);
        screen.renderSprite(25 + 14 + 140, 45, WeaponItem.blackDagger.itemSprite, false);
        screen.renderSprite(32 + 140, 62, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 58 + 5, Integer.toString(item4rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 66 + 5, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70 + 140, 66 + 5, Integer.toString(WeaponItem.blackDagger.getAttackChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 97, WeaponItem.iceSword.itemName, screen);
        screen.renderSprite(39 + 140, 103, WeaponItem.iceSword.itemSprite, false);
        screen.renderSprite(32 + 140, 120, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 121, Integer.toString(item5rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 129, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70 + 140, 129, Integer.toString(WeaponItem.iceSword.getAttackChange()), screen);
        
        font.renderSuperSmallCharacters2(23 + 140, 155, WeaponItem.flameSword.itemName, screen);
        screen.renderSprite(39 + 140, 161, WeaponItem.flameSword.itemSprite, false);
        screen.renderSprite(32 + 140, 178, Sprite.dollarSign, false);
        font.renderSuperSmallCharacters2(39 + 140, 179, Integer.toString(item6rand), screen);
        font.renderSuperSmallCharacters2(23 + 140, 187, "ATTACK", screen);
        font.renderSuperSmallCharacters2(70 + 140, 187, Integer.toString(WeaponItem.flameSword.getAttackChange()), screen);
    }
}
