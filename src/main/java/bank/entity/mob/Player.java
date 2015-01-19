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
    private int playerSpeed = 1;
    private int lastX;
    private int lastY;
    
    // Player Stats
    public int cash = 500;
    public int playerLevel = 1;
    public int health = 50;
    public int attack = 20;
    public int defence = 15;
    public int speed = 10;
    public String name;
    public int totalReferrals;
    public int availableReferrals;
    public int usedReferrals;
    
    // Player Inventory
    public ArrayList<ArmorItem> armorInventory = new ArrayList<>();
    public ArrayList<WeaponItem> weaponInventory = new ArrayList<>();
    public ArrayList<UsableItem> usableItemInventory = new ArrayList<>();
    public ArrayList<Items> inventory = new ArrayList<>();
    
    // Player equiped items
    public Items equipedWeapon;
    public Items[] equipedArmor = new Items[3];
    
	
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
        //System.out.println("X:  " + x + "  Y:  " + y);
        int xa = 0, ya = 0;
        if (anim < 7500) anim++;
        else anim = 0;
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
            menu.update();
            if (menu.doAction) {
                int action = menu.currentAction;
                menu.doAction = false;
                doAction(action, menu.item);
            }
            if (input.space && !input.checked && dialog.isOpen) {
                dialog.advanceDialog();
            }
        }
        else {
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
                        if (level.getNpc(lastX, lastY).openShoppingMenu()) {
                            switch (level.getNpc(lastX, lastY).menuNum) {
                                case 1:
                                    menu = Menu.firstMenu;
                                    break;
                                case 2:
                                    menu = Menu.secondMenu;
                                    break;
                                default:
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
                    String[] array = {"You don't have enough money for that."};
                    dialog.setMessage(array);
                }
                break;
            case 9: // equiping armor
                byte count = 0;
                for (int i = 0; i < equipedArmor.length; i++) {
                    if (equipedArmor[i] == null) {
                        switch (i) {
                            case 0:
                                if (equipedArmor[1] != item && equipedArmor[2] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    String[] array = { "Already Equiped" };
                                    dialog.isOpen = true;
                                    dialog.setMessage(array);
                                }
                                break;
                            case 1:
                                if (equipedArmor[0] != item && equipedArmor[2] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    String[] array = { "Already Equiped" };
                                    dialog.isOpen = true;
                                    dialog.setMessage(array);
                                }
                                break;
                            case 2:
                                if (equipedArmor[0] != item && equipedArmor[1] != item) {
                                    equipedArmor[i] = item;
                                }
                                else {
                                    String[] array = { "Already Equiped" };
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
                    String[] array = { "You have the maximum amount of armor equiped already! You must unequip a piece of armor before you can equip this one." };
                    dialog.isOpen = true;
                    dialog.setMessage(array);
                }
                break;
            case 10: // equiping weapon
                if (equipedWeapon == item) {
                    String[] array = { "Already Equiped" };
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
            default:
                System.err.println("ERROR! YOU HAVE NOT MADE A CASE FOR THIS; From doAction in player class");
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
            else System.out.println("This chest has already been opened");
    }
        
    private void talk() {
        switch (dir) {
            case 0:
                for (int yp = y; yp >= y - 16; yp--) {
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
                for (int xp = x; xp <= x + 22; xp++) {
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
                for (int yp = y; yp <= y + 25; yp++) {
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
                for (int xp = x; xp >= x - 22; xp--) {
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
