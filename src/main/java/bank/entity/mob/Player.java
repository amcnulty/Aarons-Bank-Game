/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.mob;

import bank.graphics.Font;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Keyboard;
import bank.input.Mouse;
import bank.inventory.ArmorItem;
import bank.inventory.Items;
import bank.inventory.UsableItem;
import bank.inventory.WeaponItem;
import bank.level.Level;
import bank.level.cutscenes.CutScenes;
import bank.level.dialog.Dialog;
import bank.menus.ClickableButton;
import bank.menus.Menu;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Player extends Mob {
	
    public boolean onCutscene = false;
    public boolean changingLevels = false;
    public int playerNum;

    private Keyboard input;
    private Sprite sprite;
    private Dialog dialog;
    private CutScenes cut;
    private ClickableButton invButton;
    private Menu menu;
    private Font font = new Font();
    private Items item;
    private int anim = 0;
    private boolean walking = false;
    private boolean exited = false;
    private boolean goToSellMenu = false;
    private Items randomItem;
    private int playerSpeed = 1;
    private int lastX;
    private int lastY;
    private boolean movingForNpc;
    private int moveToX, moveToY;
    private int lastPlayerX, lastPlayerY;
    
    // Player Stats
    public int cash = 800;
    public int playerLevel = 1;
    public int health = 50;
    public int attack = 20;
    public int equipedAttack = attack;
    public int defence = 15;
    public int equipedDefence = defence;
    public int speed = 10;
    public int equipedSpeed = speed;
    public String name;
    public int totalReferrals;
    public int availableReferrals;
    public int usedReferrals;
    
    // Player equiped items
    public Items equipedWeapon;
    public Items[] equipedArmor = new Items[3];
    
    // Player Inventory
    public ArrayList<ArmorItem> armorInventory = new ArrayList<>();
    public ArrayList<WeaponItem> weaponInventory = new ArrayList<>();
    public ArrayList<UsableItem> usableItemInventory = new ArrayList<>();
    public ArrayList<Items> inventory = new ArrayList<>();
    
    
	
    public Player(int x, int y, Keyboard input, Dialog dialog, int playerNum, CutScenes cut) {
        this.input = input;
        this.x = x;
        this.y = y;
        this.playerNum = playerNum;
        this.dialog = dialog;
        menu = Menu.inventoryMenu;
        setPlayerToMenus();
        item = UsableItem.drinkingWater;
        invButton = new ClickableButton(300, 0, 28, 0, "INVENTORY", 1);
        this.cut = cut;
        switch (playerNum) {
            case 1:
                name = "BRAD";
                sprite = Sprite.player_forward;
                break;
            case 2:
                name = "AARON";
                sprite = Sprite.player2_forward;
                break;
            case 3:
                name = "KELLY";
                sprite = Sprite.player3_forward;
                break;
            case 4:
                name = "ANNETTE";
                sprite = Sprite.player4_forward;
                break;
            case 5:
                name = "BRIAN";
                sprite = Sprite.player5_forward;
                break;
        }
    }
    
    public Player(Keyboard input, Dialog dialog, int playerNum, CutScenes cut) {
        this.input = input;
        this.playerNum = playerNum;
        this.dialog = dialog;
        menu = Menu.inventoryMenu;
        setPlayerToMenus();
        item = UsableItem.drinkingWater;
        invButton = new ClickableButton(300, 0, 28, 0, "INVENTORY", 1);
        this.cut = cut;
        switch (playerNum) {
            case 1:
                name = "BRAD";
                sprite = Sprite.player_forward;
                break;
            case 2:
                name = "AARON";
                sprite = Sprite.player2_forward;
                break;
            case 3:
                name = "KELLY";
                sprite = Sprite.player3_forward;
                break;
            case 4:
                name = "ANNETTE";
                sprite = Sprite.player4_forward;
                break;
            case 5:
                name = "BRIAN";
                sprite = Sprite.player5_forward;
                break;
        }
    }
    
    private void setPlayerToMenus() {
        menu.setPlayer(this);
        menu = Menu.armorMenu;
        menu.setPlayer(this);
        menu = Menu.weaponsMenu;
        menu.setPlayer(this);
        menu = Menu.itemsMenu;
        menu.setPlayer(this);
        menu = Menu.chestMenu;
        menu.initKeyboard(input);
    }
	
    public void changeLocation(int x, int y) {
        this.x = x;
        this.y = y;
        walking = false;
    }
	
    public void update() {
        //System.out.println("X:  " + x + "  Y:  " + y + "  X Tile:  " + x / 16 + "  Y Tile:  " + y / 16);
        int xa = 0, ya = 0;
        if (anim < 7500) anim++;
        else anim = 0;
        if (movingForNpc) {
            moveForNpc(moveToX, moveToY);
        }
        if (onCutscene) {
            if (!dialog.isOpen && cut.checkForDialog() == 1) {
                dialog.openDialog();
                cut.index++;
                try {
                    dialog.setMessage(cut.getMessage());
                    walking = false;
                }
                catch (FileNotFoundException e) {
                }
            }
            if (!dialog.isOpen) {
                xa = (int) cut.getXa();
                ya = (int) cut.getYa();
                if (xa == 5) {
                    xa = 0;
                    ya = 0;
                    onCutscene = false;
                }
                if (xa != 0 || ya != 0) {
                    move(xa, ya);
                    walking = true;
                }
                else walking = false;
            }
            if (input.space && !input.checked && dialog.isOpen) {
                dialog.advanceDialog();
                input.checked = true;
            }
        }
        else if (menu.isOpen) {
            walking = false;
            setItemEffectToStats();
            menu.update();
            if (menu.doAction) {
                int action = menu.currentAction;
                menu.doAction = false;
                doAction(action, menu.item);
            }
            if (input.space && !input.checked && dialog.isOpen) {
                dialog.advanceDialog();
                input.checked = true;
            }
        }
        else if (!movingForNpc) {
            invButton.setButton(Mouse.getX(), Mouse.getY(), Mouse.getButton());
            invButton.update();
            if (invButton.doAction()) {
                doAction(6, null);
            }
            if (input.leftShift) playerSpeed = 2;
            else playerSpeed = 1;
            if (input.up) ya -= playerSpeed;
            if (input.down) ya += playerSpeed;
            if (input.left) xa -= playerSpeed;
            if (input.right) xa += playerSpeed;
            if (!dialog.isOpen) {
                if (xa != 0 || ya != 0) {
                    move(xa, ya);
                    walking = true;
                }
                else walking = false;
            }
            if (input.space && !input.checked && !dialog.isOpen) {
                readSign();
                talk();
                openChest();
                input.checked = true;
                walking = false;
            }
            if (input.space && !input.checked && dialog.isOpen) {
                dialog.advanceDialog();
                if (!dialog.isOpen) {
                    try {
                        level.getNpc(lastX, lastY).talking = false;
                        if (level.equals(Level.crazyLevel)) {
                            level.getNpc(143, 58).movedOutOfWay = false;
                        }
                        if (level.getNpc(lastX, lastY).openShoppingMenu() && !level.getNpc(lastX, lastY).movedOutOfWay) {
                            switch (level.getNpc(lastX, lastY).menuNum) {
                                case 1:
                                    menu = Menu.firstMenu;
                                    menu.randomizeTotals();
                                    break;
                                case 2:
                                    menu = Menu.secondMenu;
                                    break;
                                case 9:
                                    menu = Menu.favoriteFoodMenu;
                                    break;
                                case 10:
                                    menu = Menu.swordYesNoMenu;
                                    break;
                                case 11:
                                    menu = Menu.threeReferralYesNoMenu;
                                    break;
                                case 12:
                                    menu = Menu.mazeGuardMenu;
                                    break;
                                case 13:
                                    menu = Menu.trainingBookReferralMenu;
                                    break;
                                case 14:
                                    menu = Menu.storeOneMenu;
                                    menu.randomizeTotals();
                                    break;
                                case 15:
                                    menu = Menu.potionShopMenu;
                                    menu.randomizeTotals();
                                    break;
                                case 16:
                                    menu = Menu.baitShopMenu;
                                    break;
                                case 17:
                                    menu = Menu.attackTrainerMenu;
                                    break;
                                case 18:
                                    menu = Menu.defenceTrainerMenu;
                                    break;
                                case 19:
                                    menu = Menu.speedTrainerMenu;
                                    break;
                                case 20:
                                    menu = Menu.levelUpTrainerMenu;
                                    break;
                                case 21:
                                    if (goToSellMenu) menu = Menu.sellArmorMenu;
                                    else menu = Menu.sellArmorYesNoMenu;
                                    break;
                                case 23:
                                    menu = Menu.bribeMenu;
                                    break;
                                case 24:
                                    menu = Menu.goodBadMenu;
                                    break;
                                case 25:
                                    menu = Menu.buySpecialCandyMenu;
                                    break;
                                case 26:
                                    menu = Menu.moneyForReferralMenu;
                                    break;
                                default:
                                    System.err.println("YOU HAVE NOT MADE A CASE FOR MENU NUMBER " + level.getNpc(lastX, lastY).menuNum + " IN PLAYER.UPDATE()");
                                    break;
                            }
                            menu.isOpen = true;
                        }
                    }
                    catch (NullPointerException e) {
                    }
                }
                input.checked = true;
            }
            setExited(level.checkExit(x, y));
        }
    }
    
    private void doAction(int action, Items item) {
        String[] array;
        switch (action) {
            case 0:
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 1:
                cash += 500;
                break;
            case 2:
                int rand;
                do {
                    rand = random.nextInt(3);
                    System.out.println(rand);
                }
                while (rand == 0);
                if (random.nextBoolean()) speed += rand;
                else speed -= rand;
                break;
            case 3: // switching to weapons tab in inventory
                menu.isOpen = false;
                menu = Menu.weaponsMenu;
                menu.setPanes();
                menu.isOpen = true;
                break;
            case 4: // switching to armor tab in inventory
                menu.isOpen = false;
                menu = Menu.armorMenu;
                menu.setPanes();
                menu.isOpen = true;
                break;
            case 5: // switching to items tab in inventory
                menu.isOpen = false;
                menu = Menu.itemsMenu;
                menu.setPanes();
                menu.isOpen = true;
                break;
            case 6: // open the inventory menu
                menu.isOpen = false;
                menu = Menu.inventoryMenu;
                menu.isOpen = true;
                break;
            case 7: // using an item from inventory
                if (inventory.get(inventory.indexOf(item)).amount > 1) {
                    setStats(inventory.get(inventory.indexOf(item)).getHealthPoints(), inventory.get(inventory.indexOf(item)).getAttackChange(), inventory.get(inventory.indexOf(item)).getDefenceChange(), inventory.get(inventory.indexOf(item)).getSpeedChange());
                    if (inventory.get(inventory.indexOf(item)).getLevelChange() != 0) {
                        changeLevel(item.getLevelChange());
                    }
                    inventory.get(inventory.indexOf(item)).decrementAmount();
                    menu.setPanes();
                }
                else {
                    setStats(inventory.get(inventory.indexOf(item)).getHealthPoints(), inventory.get(inventory.indexOf(item)).getAttackChange(), inventory.get(inventory.indexOf(item)).getDefenceChange(), inventory.get(inventory.indexOf(item)).getSpeedChange());
                    if (inventory.get(inventory.indexOf(item)).getLevelChange() != 0) {
                        changeLevel(item.getLevelChange());
                    }
                    inventory.remove(inventory.indexOf(item));
                    menu.setPanes();
                }
                break;
            case 8: // buying an item from a clerk
                if (cash >= item.standardPrice) {
                    cash -= item.standardPrice;
                    boolean addedItem = false;
                    for (int i = 0; i < inventory.size(); i++) {
                        if (inventory.get(i).equals(item)) {
                            inventory.get(i).incrementAmount();
                            addedItem = true;
                        }
                    }
                    if (!addedItem) {
                        inventory.add(item);
                    }
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money for that.";
                    dialog.setMessage(array);
                }
                break;
            case 9: // equiping armor
                array = new String[1];
                byte count = 0;
                for (int i = 0; i < equipedArmor.length; i++) {
                    if (equipedArmor[i] == null) {
                        switch (i) {
                            case 0:
                                if (equipedArmor[1] != item && equipedArmor[2] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    array[0] = "Already Equiped";
                                    dialog.isOpen = true;
                                    dialog.setMessage(array);
                                }
                                break;
                            case 1:
                                if (equipedArmor[0] != item && equipedArmor[2] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    array[0] = "Already Equiped";
                                    dialog.isOpen = true;
                                    dialog.setMessage(array);
                                }
                                break;
                            case 2:
                                if (equipedArmor[0] != item && equipedArmor[1] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    array[0] = "Already Equiped";
                                    dialog.isOpen = true;
                                    dialog.setMessage(array);
                                }
                                break;
                        }
                            break;
                    }
                    else count++;
                }
                if (count == 3) {
                    array[0] = "You have the maximum amount of armor equiped already! You must unequip a piece of armor before you can equip this one.";
                    dialog.isOpen = true;
                    dialog.setMessage(array);
                }
                break;
            case 10: // equiping weapon
                array = new String[1];
                if (equipedWeapon == item) {
                    array[0] = "Already Equiped";
                    dialog.isOpen = true;
                    dialog.setMessage(array);
                }
                else equipedWeapon = item;
                break;
            case 11: // unequiping weapon
                equipedWeapon = null;
                break;
            case 12: // unequip armor slot 1
                equipedArmor[0] = null;
                break;
            case 13: // unequip armor slot 2
                equipedArmor[1] = null;
                break;
            case 14: // unequip armor slot 3
                equipedArmor[2] = null;
                break;
            case 15:
                menu = Menu.yesNoMenu;
                menu.isOpen = true;
                break;
            case 16:
                menu = Menu.inventoryMenu;
                totalReferrals++;
                availableReferrals++;
                break;
            case 17:
                menu = Menu.inventoryMenu;
                break;
            case 18: // yes on sideways house menu
                if (removeInventoryItem(UsableItem.special_candy)) {
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.LEFT40STEPS);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    moveToX = 70;
                    moveToY = 117;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Looks like you don't have any special candies. That't too bad.";
                    dialog.setMessage(array);
                }
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 19: // no on sideways house menu
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you change your mind come talk to me.";
                dialog.setMessage(array);
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 20:
                boolean gaveItem = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).equals(UsableItem.cookie)) {
                        if (inventory.get(i).amount > 1) {
                            inventory.get(i).decrementAmount();
                            gaveItem = true;
                            break;
                        }
                        else {
                            inventory.remove(inventory.get(i));
                            gaveItem = true;
                            break;
                        }
                    }
                }
                if (gaveItem) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "That is not my favorite food sorry.";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any cookies!";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                break;
            case 21:
                boolean gaveItem2 = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).equals(UsableItem.cherries)) {
                        if (inventory.get(i).amount > 1) {
                            inventory.get(i).decrementAmount();
                            gaveItem2 = true;
                            break;
                        }
                        else {
                            inventory.remove(inventory.get(i));
                            gaveItem2 = true;
                            break;
                        }
                    }
                }
                if (gaveItem2) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "That is not my favorite food sorry.";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any cherries!";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                break;
            case 22:
                boolean gaveItem3 = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).equals(UsableItem.cheese)) {
                        if (inventory.get(i).amount > 1) {
                            inventory.get(i).decrementAmount();
                            gaveItem3 = true;
                            break;
                        }
                        else {
                            inventory.remove(inventory.get(i));
                            gaveItem3 = true;
                            break;
                        }
                    }
                }
                if (gaveItem3) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "That is not my favorite food sorry.";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any cheese";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                break;
            case 23:
                boolean gaveItem4 = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).equals(UsableItem.egg)) {
                        if (inventory.get(i).amount > 1) {
                            inventory.get(i).decrementAmount();
                            gaveItem4 = true;
                            break;
                        }
                        else {
                            inventory.remove(inventory.get(i));
                            gaveItem4 = true;
                            break;
                        }
                    }
                }
                if (gaveItem4) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Yum that was tasty! Here take this sword.";
                    dialog.setMessage(array);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).movedOutOfWay = true;
                    inventory.add(WeaponItem.speedSword);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                    menu = Menu.chestMenu;
                    menu.isOpen = true;
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any eggs!";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                break;
            case 24: // yes to sword menu
                gaveItem = false;
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).equals(WeaponItem.speedSword)) {
                        if (inventory.get(i).amount > 1) {
                            inventory.get(i).decrementAmount();
                            gaveItem = true;
                            break;
                        }
                        else {
                            inventory.remove(inventory.get(i));
                            gaveItem = true;
                            break;
                        }
                    }
                }
                if (gaveItem) {
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.LEFTSWAMPGUARD);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    moveToX = 488;
                    moveToY = 120;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have anything that interests me.";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                }
                break;
            case 25: // no to sword menu
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "Well, if you do find something let me know!";
                dialog.setMessage(array);
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 26: // yes to three referrals menu
                if (availableReferrals >= 3) {
                    availableReferrals -= 3;
                    usedReferrals += 3;
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.DOWN25STEPS);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    moveToX = 488;
                    moveToY = 130;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough referrals to give me three.";
                    dialog.setMessage(array);
                }
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 27: // no to three referrals menu
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you change your mind come talk to me.";
                dialog.setMessage(array);
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 28: // yes in maze guard menu
                if (cash >= 100) {
                    cash -= 100;
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.MAZEGUARD);
                    moveToX = 144;
                    moveToY = 112;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough cash. You only have " + cash;
                    dialog.setMessage(array);
                }
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 29: // no in maze guard menu
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you change your mind come talk to me.";
                dialog.setMessage(array);
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 30: // yes to trade referral for book
                if (availableReferrals >= 1) {
                    availableReferrals--;
                    usedReferrals++;
                    addInventoryItem(UsableItem.training_book);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Thank you for the trade! Come back with more referrals and I'll give you more of these special books!";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                    menu = Menu.chestMenu;
                    menu.setItemSpriteAndName(UsableItem.training_book.itemSprite, UsableItem.training_book.itemName);
                    menu.isOpen = true;
                    
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have anything I need. Come back when you get some more referrals.";
                    dialog.setMessage(array);
                    menu.isOpen = false;
                }
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 31: // no to trade referral for book
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you change your mind come talk to me. Don't trade them with anyone else!";
                dialog.setMessage(array);
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 32: // buying leather helmet from store one in crazy level
                if (cash >= menu.item1rand) {
                    cash -= menu.item1rand;
                    addInventoryItem(ArmorItem.leatherhelmet);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Leather Helmet has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 33: // buying hard leather helmet from store one in crazy level
                if (cash >= menu.item2rand) {
                    cash -= menu.item2rand;
                    addInventoryItem(ArmorItem.hardLeatherHelmet);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Hard Leather Helmet has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 34: // buying hard leather tunic from store one in crazy level
                if (cash >= menu.item3rand) {
                    cash -= menu.item3rand;
                    addInventoryItem(ArmorItem.hardLeatherTunic);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Hard Leather Tunic has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 35: // buying steel toe boots from store one in crazy level
                if (cash >= menu.item4rand) {
                    cash -= menu.item4rand;
                    addInventoryItem(ArmorItem.steelToeBoots);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A pair of Steel Toe Boots have been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 36: // buying leather tunic from store one in crazy level
                if (cash >= menu.item5rand) {
                    cash -= menu.item5rand;
                    addInventoryItem(ArmorItem.leatherTunic);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Leather Tunic has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 37: // buying leather boots from store one in crazy level
                if (cash >= menu.item6rand) {
                    cash -= menu.item6rand;
                    addInventoryItem(ArmorItem.leatherBoots);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A pair of Leather Boots have been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 38: // buying deluxe sword from houseSubLevel
                if (cash >= menu.item1rand) {
                    cash -= menu.item1rand;
                    addInventoryItem(WeaponItem.deluxeSword);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Deluxe Sword has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 39: // buying deluxe dagger from houseSubLevel
                if (cash >= menu.item2rand) {
                    cash -= menu.item2rand;
                    addInventoryItem(WeaponItem.deluxeDagger);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Deluxe Dagger has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 40: // buying heavy sword from houseSubLevel
                if (cash >= menu.item3rand) {
                    cash -= menu.item3rand;
                    addInventoryItem(WeaponItem.heavySword);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Heavy Sword has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 41: // buying black dagger from houseSubLevel
                if (cash >= menu.item4rand) {
                    cash -= menu.item4rand;
                    addInventoryItem(WeaponItem.blackDagger);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Black Dagger has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 42: // buying ice sword from houseSubLevel
                if (cash >= menu.item5rand) {
                    cash -= menu.item5rand;
                    addInventoryItem(WeaponItem.iceSword);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "An Ice Sword has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 43: // buying flame sword from houseSubLevel
                if (cash >= menu.item6rand) {
                    cash -= menu.item6rand;
                    addInventoryItem(WeaponItem.flameSword);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Flame Sword has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 44: // buying speed potion from potion shop level
                if (cash >= UsableItem.speed_potion.standardPrice) {
                    cash -= UsableItem.speed_potion.standardPrice;
                    addInventoryItem(UsableItem.speed_potion);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Speed Potion has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 45: // buying defence potion from potion shop level
                if (cash >= UsableItem.defence_potion.standardPrice) {
                    cash -= UsableItem.defence_potion.standardPrice;
                    addInventoryItem(UsableItem.defence_potion);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A Defence Potion has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 46: // buying attack potion from potion shop level
                if (cash >= UsableItem.attack_potion.standardPrice) {
                    cash -= UsableItem.attack_potion.standardPrice;
                    addInventoryItem(UsableItem.attack_potion);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "An Attack Potion has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 47: // buying drinking water from bait shop
                if (cash >= UsableItem.drinkingWater.standardPrice) {
                    cash -= UsableItem.drinkingWater.standardPrice;
                    addInventoryItem(UsableItem.drinkingWater);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A bottle of Drinkning Water has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 48: // buying banana from bait shop
                if (cash >= UsableItem.banana.standardPrice) {
                    cash -= UsableItem.banana.standardPrice;
                    addInventoryItem(UsableItem.banana);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A banana has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 49: // buying cheese from bait shop
                if (cash >= UsableItem.cheese.standardPrice) {
                    cash -= UsableItem.cheese.standardPrice;
                    addInventoryItem(UsableItem.cheese);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A block of cheese has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 50: // buying fish from bait shop
                if (cash >= UsableItem.fish.standardPrice) {
                    cash -= UsableItem.fish.standardPrice;
                    addInventoryItem(UsableItem.fish);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "A fish has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 51: // buying cherries from bait shop
                if (cash >= UsableItem.cherries.standardPrice) {
                    cash -= UsableItem.cherries.standardPrice;
                    addInventoryItem(UsableItem.cherries);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Cherries have been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 52: // buying egg from bait shop
                if (cash >= UsableItem.egg.standardPrice) {
                    cash -= UsableItem.egg.standardPrice;
                    addInventoryItem(UsableItem.egg);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "An egg has been added to your inventory.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                break;
            case 53: // yes to attack trainer menu
                if (cash >= 5000) {
                    cash -= 5000;
                    attack += 40;
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Your attack power just went up by 40!";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money for my training!";
                    dialog.setMessage(array);
                }
                break;
            case 54: // no to trainer menus
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you change your mind come talk to me.";
                dialog.setMessage(array);
                break;
            case 55: // yes to defence trainer menu
                if (cash >= 5000) {
                    cash -= 5000;
                    defence += 40;
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Your defence stat just went up by 40!";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money for my training!";
                    dialog.setMessage(array);
                }
                break;
            case 56: // yes to speed trainer menu
                if (cash >= 5000) {
                    cash -= 5000;
                    speed += 40;
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Your speed stat just went up by 40!";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money for my training!";
                    dialog.setMessage(array);
                }
                break;
            case 57: // yes to level up trainer menu
                if (availableReferrals >= 1) {
                    availableReferrals--;
                    usedReferrals++;
                    changeLevel(1);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "LEVEL UP!!";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough referrals to level up. Come back when you get some referrals and then I can train you.";
                    dialog.setMessage(array);
                }
                break;
            case 58: // yes to sell armor menu
                switch (random.nextInt(6)) {
                    case 0:
                        randomItem = ArmorItem.leatherBoots;
                        break;
                    case 1:
                        randomItem = ArmorItem.steelToeBoots;
                        break;
                    case 2:
                        randomItem = ArmorItem.leatherTunic;
                        break;
                    case 3:
                        randomItem = ArmorItem.hardLeatherTunic;
                        break;
                    case 4:
                        randomItem = ArmorItem.leatherhelmet;
                        break;
                    case 5:
                        randomItem = ArmorItem.hardLeatherHelmet;
                        break;
                    default:
                        randomItem = ArmorItem.leatherBoots;
                        break;
                }
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "Alright, the piece of armor I'm looking for is a " + randomItem.itemName;
                dialog.setMessage(array);
                goToSellMenu = true;
                menu.isOpen = false;
                break;
            case 59: // trade button on sell armor menu
                if (removeInventoryItem(randomItem)) {
                    int randomPay = randomItem.standardPrice - (random.nextInt((int)((double)randomItem.standardPrice * .1)) + 100);
                    cash += randomPay;
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You just got " + randomPay + " dollars for selling your " + randomItem.itemName;
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any " + randomItem.itemName + " in your inventory";
                    dialog.setMessage(array);
                }
                goToSellMenu = false;
                break;
            case 60: // don't trade button on sell armor menu
                dialog.isOpen = true;
                array = new String[1];
                array[0] = "If you get some good stuff come see me and I'll buy it from you!";
                dialog.setMessage(array);
                goToSellMenu = false;
                break;
            case 61: // yes to bribe menu
                if (cash >= 1000) {
                    cash -= 1000;
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.RIGHTSWAMPGUARD);
                    moveToX = 488;
                    moveToY = 130;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money.";
                    dialog.setMessage(array);
                }
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 62: // yes to good bad menu
                if (random.nextInt(100) > 98) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Thats good to hear. You know what? I'm feeling generous today!";
                    dialog.setMessage(array);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.SCOTT);
                    moveToX = 205;
                    moveToY = 222;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Thats good to hear. I'm not feeling generous right now...";
                    dialog.setMessage(array);
                }
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 63: // no to good bad menu
                if (random.nextInt(100) > 98) {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Really? I'm not feeling very good either. You know what? I'm feeling generous today!";
                    dialog.setMessage(array);
                    level.getNpc(lastX, lastY).initialConversation = false;
                    level.getNpc(lastX, lastY).closeShop();
                    level.getNpc(lastX, lastY).initCut(cut);
                    level.getNpc(lastX, lastY).setRoute(CutScenes.SCOTT);
                    moveToX = 205;
                    moveToY = 222;
                    movingForNpc = true;
                    moveForNpc(moveToX, moveToY);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "That's too bad... I'm not feeling generous right now...";
                    dialog.setMessage(array);
                }
                menu.isOpen = false;
                if (level.getNpc(lastX, lastY) == null);
                else level.getNpc(lastX, lastY).talking = false;
                break;
            case 64: // yes to buy special candy menu
                if (cash >= 4000) {
                    cash -= 4000;
                    addInventoryItem(UsableItem.special_candy);
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Thank you for your business. Come back if you want more.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have enough money!";
                    dialog.setMessage(array);
                }
                    if (level.getNpc(lastX, lastY) == null);
                    else level.getNpc(lastX, lastY).talking = false;
                break;
            case 65: // yes to money for referral menu
                if (availableReferrals >= 1) {
                    availableReferrals--;
                    usedReferrals++;
                    cash += 2000;
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "Thank you for your business!! Come back if you want to trade again.";
                    dialog.setMessage(array);
                }
                else {
                    dialog.isOpen = true;
                    array = new String[1];
                    array[0] = "You don't have any referrals available to give me.";
                    dialog.setMessage(array);
                }
                break;
            default:
                System.err.println("ERROR! YOU HAVE NOT MADE A CASE FOR THIS; From doAction in player class");
        }
    }
    
    private boolean removeInventoryItem(Items item) {
        boolean tookItem = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                if (inventory.get(i).amount > 1) {
                    inventory.get(i).decrementAmount();
                    tookItem = true;
                    break;
                }
                else {
                    if (item.equals(equipedWeapon)) equipedWeapon = null;
                    for (int ii = 0; ii < 3; ii++) {
                        if (item.equals(equipedArmor[ii])) {
                            equipedArmor[ii] = null;
                            break;
                        }
                    }
                    inventory.remove(inventory.get(i));
                    tookItem = true;
                    break;
                }
            }
        }
        return tookItem;
    }
    
    private void addInventoryItem(Items item) {
        boolean gaveItem = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                inventory.get(i).incrementAmount();
                gaveItem = true;
            }
        }
        if (!gaveItem) {
            inventory.add(item);
        }
    }
    
    private void moveForNpc(int moveToX, int moveToY) {
        boolean atX = false;
        boolean atY = false;
        int xa = 0;
        int ya = 0;
        if (x < moveToX) xa = 1;
        else if (x > moveToX) xa = -1;
        else if (x == moveToX) atX = true;
        if (y < moveToY) ya = 1;
        else if (y > moveToY) ya = -1;
        else if (y == moveToY) atY = true;
        if ((atX && atY) || (lastPlayerX == x && lastPlayerY == y)) {
            movingForNpc = false;
            level.getNpc(lastX, lastY).moveAlongRoute = true;
            walking = false;
        }
        else {
            lastPlayerX = x;
            lastPlayerY = y;
            move(xa, ya);
            walking = true;
        }
    }
    
    private void changeLevel(int levelChange) {
        for (int i = 0; i < levelChange; i++) {
            playerLevel++;
            setStats(25, 5, 5, 2);
        }
    }
    
    private void setStats(int health, int attack, int defence, int speed) {
        this.health += health;
        this.attack += attack;
        this.defence += defence;
        this.speed += speed;
    }
    
    public void setItemEffectToStats() {
        if (equipedWeapon != null) equipedAttack = attack + equipedWeapon.getAttackChange();
        else equipedAttack = attack;
        int defenceChange = 0;
        int speedChange = 0;
        for (int i = 0; i < 3; i++) {
            if (equipedArmor[i] != null) {
                defenceChange += equipedArmor[i].getDefenceChange();
                speedChange += equipedArmor[i].getSpeedChange();
            }
        }
        equipedSpeed = speed + speedChange;
        equipedDefence = defence + defenceChange;
    }
	
    public int getLevelDestination() {
        return level.destinations[(x >> 4) + (y >> 4) * level.getLevelTileWidth()][0];
    }

    public int getXDestination() {
        return level.destinations[(x >> 4) + (y >> 4) * level.getLevelTileWidth()][1];
    }

    public int getYDestination() {
        return level.destinations[(x >> 4) + (y >> 4) * level.getLevelTileWidth()][2];
    }
    
    public int[] getInventoryIDS() {
        int[] itemIDs = new int[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) {
            itemIDs[i] = inventory.get(i).itemID;
        }
        return itemIDs;
    }
    
    public int[] getInventoryAmount() {
        int[] itemAmounts = new int[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) {
            itemAmounts[i] = inventory.get(i).amount;
        }
        return itemAmounts;
    }
    
    private void setExited(boolean exited) {
        this.exited = exited;
    }

    public boolean exited() {
        return exited;
    }

    public void resetExited() {
        exited = false;
    }
	
    public void readSign() {
       if (dir == 0) {
           for (int yp = y; yp >= y - 8; yp--) {
               if (level.signHere(x, yp)) {
                   lastX = x;
                   lastY = yp;
                   dialog.isOpen = true;
                   dialog.setMessage(level.getSign(x, yp).getSignDialog());
               }
           }
       }
        
        
        /**if (dir == 0) {
            for (int yp = y; yp >= y - 8; yp--) {
                if (level.getTile(x >> 4, yp >> 4).dialog() == true) {
                    // Open dialog box here
                    dialog.openDialog();
                    // send the strings to the dialog class
                    try {
                        dialog.setMessage((level.readMessage(x / 16, yp / 16)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }*/
    }
    
    private void openChest() {
        switch (dir) {
            case 0:
                for (int yp = y; yp >= y - 10; yp--) {
                    if (level.chestHere(x, yp)) {
                        checkChest(x, yp);
                        break;
                    }
                }
                break;
            case 1:
                for (int xp = x; xp <= x + 16; xp++) {
                    if (level.chestHere(xp, y)) {
                        checkChest(xp, y);
                        break;
                    }
                }
                break;
            case 2:
                for (int yp = y; yp <= y + 22; yp++) {
                    if (level.chestHere(x, yp)) {
                        checkChest(x, yp);
                        break;
                    }
                    
                }
                break;
            case 3:
                for (int xp = x; xp >= x - 16; xp--) {
                    if (level.chestHere(xp, y)) {
                        checkChest(xp, y);
                        break;
                    }
                }
                break;
                
        }
    }
    
    private void checkChest(int x, int y) {
        int itemNum = level.getChest(x, y).getItem();
            if (itemNum != 0) {
                //inventory.add(item.getItemFromID(itemNum));
                boolean addedItem = false;
                Items newItem = item.getItemFromID(itemNum);
                for (int i = 0; i < inventory.size(); i++) {
                    if (inventory.get(i).itemName.equals(newItem.itemName)) {
                        inventory.get(i).incrementAmount();
                        addedItem = true;
                    }
                }
                if (!addedItem) inventory.add(newItem);
                menu = Menu.chestMenu;
                menu.isOpen = true;
                menu.setItemSpriteAndName(item.getItemFromID(itemNum).itemSprite, item.getItemFromID(itemNum).itemName);
            }
    }
        
    private void talk() {
        switch (dir) {
            case 0:
                for (int yp = y; yp >= y - 19; yp--) {
                    if (level.npcHere(x, yp)) {
                        dialog.openDialog();
                        lastX = x;
                        lastY = yp;
                        try {
                            dialog.setMessage(level.getNpc(x, yp).getConversationDialog(2));
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            case 1:
                for (int xp = x; xp <= x + 27; xp++) {
                    if (level.npcHere(xp, y)) {
                        dialog.openDialog();
                        lastX = xp;
                        lastY = y;
                        try {
                            dialog.setMessage(level.getNpc(xp, y).getConversationDialog(3));
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            case 2:
                for (int yp = y; yp <= y + 30; yp++) {
                    if (level.npcHere(x, yp)) {
                        dialog.openDialog();
                        lastX = x;
                        lastY = yp;
                        try {
                            dialog.setMessage(level.getNpc(x, yp).getConversationDialog(0));
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            case 3:
                for (int xp = x; xp >= x - 27; xp--) {
                    if (level.npcHere(xp, y)) {
                        dialog.openDialog();
                        lastX = xp;
                        lastY = y;
                        try {
                            dialog.setMessage(level.getNpc(xp, y).getConversationDialog(1));
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
        }
    }
    
    public Sprite getPlayerMenuSprite(int playerNum) {
        switch (playerNum) {
            case 1:
                return Sprite.player_back;
            case 2:
                return Sprite.player2_back;
            case 3:
                return Sprite.player3_back;
            case 4:
                return Sprite.player4_back;
            case 5:
                return Sprite.player5_back;
        }
        return null;
    }
        
    private void setSprite(int playerNum, int dir) {
        switch (playerNum) {
            case 1:
                switch (dir) {
                    case 0:
                        sprite = Sprite.player_forward;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player_forward1;
                            }
                            else {
                                sprite = Sprite.player_forward2;
                            }
                        }
                        break;
                    case 1:
                        sprite = Sprite.player_right;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player_right1;
                            }
                            else {
                                sprite = Sprite.player_right2;
                            }
                        }
                        break;
                    case 2:
                        sprite = Sprite.player_back;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player_back1;
                            }
                            else {
                                sprite = Sprite.player_back2;
                            }
                        }
                        break;
                    case 3:
                        sprite = Sprite.player_left;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player_left1;
                            }
                            else {
                                sprite = Sprite.player_left2;
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (dir) {
                    case 0:
                        sprite = Sprite.player2_forward;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player2_forward1;
                            }
                            else {
                                sprite = Sprite.player2_forward2;
                            }
                        }
                        break;
                    case 1:
                        sprite = Sprite.player2_right;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player2_right1;
                            }
                            else {
                                sprite = Sprite.player2_right2;
                            }
                        }
                        break;
                    case 2:
                        sprite = Sprite.player2_back;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player2_back1;
                            }
                            else {
                                sprite = Sprite.player2_back2;
                            }
                        }
                        break;
                    case 3:
                        sprite = Sprite.player2_left;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player2_left1;
                            }
                            else {
                                sprite = Sprite.player2_left2;
                            }
                        }
                        break;
                }
                break;
            case 3:
                switch (dir) {
                    case 0:
                        sprite = Sprite.player3_forward;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player3_forward1;
                            }
                            else {
                                sprite = Sprite.player3_forward2;
                            }
                        }
                        break;
                    case 1:
                        sprite = Sprite.player3_right;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player3_right1;
                            }
                            else {
                                sprite = Sprite.player3_right2;
                            }
                        }
                        break;
                    case 2:
                        sprite = Sprite.player3_back;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player3_back1;
                            }
                            else {
                                sprite = Sprite.player3_back2;
                            }
                        }
                        break;
                    case 3:
                        sprite = Sprite.player3_left;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player3_left1;
                            }
                            else {
                                sprite = Sprite.player3_left2;
                            }
                        }
                        break;
                }
                break;
            case 4:
                switch (dir) {
                    case 0:
                        sprite = Sprite.player4_forward;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player4_forward1;
                            }
                            else {
                                sprite = Sprite.player4_forward2;
                            }
                        }
                        break;
                    case 1:
                        sprite = Sprite.player4_right;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player4_right1;
                            }
                            else {
                                sprite = Sprite.player4_right2;
                            }
                        }
                        break;
                    case 2:
                        sprite = Sprite.player4_back;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player4_back1;
                            }
                            else {
                                sprite = Sprite.player4_back2;
                            }
                        }
                        break;
                    case 3:
                        sprite = Sprite.player4_left;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player4_left1;
                            }
                            else {
                                sprite = Sprite.player4_left2;
                            }
                        }
                        break;
                }
                break;
            case 5:
                switch (dir) {
                    case 0:
                        sprite = Sprite.player5_forward;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player5_forward1;
                            }
                            else {
                                sprite = Sprite.player5_forward2;
                            }
                        }
                        break;
                    case 1:
                        sprite = Sprite.player5_right;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player5_right1;
                            }
                            else {
                                sprite = Sprite.player5_right2;
                            }
                        }
                        break;
                    case 2:
                        sprite = Sprite.player5_back;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player5_back1;
                            }
                            else {
                                sprite = Sprite.player5_back2;
                            }
                        }
                        break;
                    case 3:
                        sprite = Sprite.player5_left;
                        if (walking) {
                            if (anim % 20 > 9) {
                                sprite = Sprite.player5_left1;
                            }
                            else {
                                sprite = Sprite.player5_left2;
                            }
                        }
                        break;
                }
                break;
        }
    }
	
    public void render(Screen screen) {
        setSprite(playerNum, dir);
        screen.renderPlayer(x -16, y - 16, sprite);
        if (!onCutscene) invButton.render(screen);
        if (menu.isOpen) {
            menu.render(screen);
        }
    }
}
