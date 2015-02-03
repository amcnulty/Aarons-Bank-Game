/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.chests.Chest;
import bank.entity.furniture.Furniture;
import bank.entity.mob.Npc.Npc;
import bank.entity.signs.Signs;
import bank.graphics.Screen;
import bank.inventory.ArmorItem;
import bank.inventory.UsableItem;
import bank.menus.Menu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
public class CrazyLevel extends Level {
    
    private final int LEVELNUM = 2;
    
    private ArrayList<Npc> npcs = new ArrayList<>();
    private ArrayList<Furniture> furniture = new ArrayList<>();
    private ArrayList<Chest> chests = new ArrayList<>();
    private ArrayList<Signs> signs = new ArrayList<>();

    public CrazyLevel(String path) {
        super(path);
        addNpcs();
        addFurniture();
        addChests();
        addSigns();
    }
    
    private void addSigns() {
        signs.add(new Signs(1053, 614, "Armor Store"));
        signs.add(new Signs(58 * 16 + 13, 49 * 16 + 6, "Weapon Store"));
        signs.add(new Signs(23 * 16 + 13, 46 * 16 + 6, "Potion Shop"));
        signs.add(new Signs(6 * 16 + 5, 22 * 16 + 2, "Bait Shop"));
        signs.add(new Signs(29 * 16 - 10, 27 * 16, "Training Inside!", "We specialise in stat building and level training. Come inside and we can bulk you up!"));
    }
    
    private void addChests() {
        chests.add(new Chest(40, 67, 2, UsableItem.COOKIE));
        chests.add(new Chest(1, 61, 2, UsableItem.BANANA));
        chests.add(new Chest(42, 20, 2, UsableItem.LEATHER_HELMET));
        chests.add(new Chest(45, 1, 2, UsableItem.CHEESE));
        chests.add(new Chest(1, 1, 2, ArmorItem.STEEL_TOE_BOOTS));
        chests.add(new Chest(37, 5, 2, UsableItem.SPEED_POTION));
        chests.add(new Chest(17, 1, 2, UsableItem.DRUMSTICK));
        chests.add(new Chest(4, 42, 2, UsableItem.DRINKING_WATER));
        chests.add(new Chest(67, 67, 2, UsableItem.FISH));
    }

    private void addNpcs() {
        npcs.add(new Npc(143, 58, 1, Menu.MAZEGUARDMENU, "Would you like to try the maze garden? Entrance is 100 dollars", "THIS SAYS NOTHING"));
        npcs.add(new Npc(168, 522, Npc.FEMALE2NPC, "/dialogs/crazyLevel/Bobbie.txt"));
        npcs.add(new Npc(540, 1088, Npc.MALE2NPC, "/dialogs/crazyLevel/towerPerson.txt"));
        npcs.add(new Npc(48, 774, Npc.MALE3NPC, "/dialogs/crazyLevel/parkMale.txt"));
        npcs.add(new Npc(76, 774, Npc.FEMALE3NPC, "/dialogs/crazyLevel/parkFemale.txt"));
        npcs.add(new Npc(622, 821, Npc.MALE4NPC, "/dialogs/crazyLevel/referral.txt"));
        npcs.add(new Npc(766, 510, Npc.FEMALE2NPC, "/dialogs/crazyLevel/girlOnCorner.txt"));
        npcs.add(new Npc(41, 384 + 40, Npc.MALENPC, "/dialogs/crazyLevel/fisherman.txt"));
        npcs.add(new Npc(917, 880, Npc.MALE3NPC, "/dialogs/crazyLevel/clue.txt"));
        npcs.add(new Npc(512, 22, Npc.MALE4NPC, Menu.MONEY_FOR_REFERRAL_MENU, "I'm looking for a referral. If you have one I'll give you 2000 dollars. Want to trade?", "NO SCRIPT"));
        
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).init(this);
        }
    }
    
    private void addFurniture() {
        furniture.add(new Furniture(1036, 307, Furniture.AIR_COMPRESSOR));
        furniture.add(new Furniture(801, 381, Furniture.BARREL));
        furniture.add(new Furniture(819, 381, Furniture.BARREL));
        furniture.add(new Furniture(1054, 381, Furniture.BARREL));
    }

    protected void loadLevel(String path) {
            try {
                    BufferedImage image = ImageIO.read(Level.class.getResource(path));
                    int w = width = image.getWidth();
                    int h = height = image.getHeight();
                    tiles = new int[w * h];
                    image.getRGB(0, 0, w, h, tiles, 0, w);
            }
            catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Exception! Could not load level file!");
            }
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tiles[x + y * width] == 0xffFAFF7F) {
                    playerSpawn = new TileCoordinate(x, y);
                }
            }
        }
        destinations = new int[width * height][3];
        //setDestinations(18, 69, Level.SPAWN_LEVEL, 11, true, 2, false);
        //setDestinations(19, 69, Level.SPAWN_LEVEL, 11, true, 2, false);
        //setDestinations(20, 69, Level.SPAWN_LEVEL, 11, true, 2, false);
        
        setDestinations(18, 69, Level.SWAMP_LEVEL, 30, true, 1, true);
        setDestinations(19, 69, Level.SWAMP_LEVEL, 30, true, 1, true);
        setDestinations(20, 69, Level.SWAMP_LEVEL, 30, true, 1, true);

        setDestinations(8, 0, Level.MAZE_LEVEL, 34, true, 68, false);
        setDestinations(9, 0, Level.MAZE_LEVEL, 34, true, 68, false);

        setDestinations(52, 38, Level.HOUSE2_SUBLEVEL, 7, false, 9, false);
        setDestinations(53, 38, Level.HOUSE2_SUBLEVEL, 7, false, 9, false);

        setDestinations(63, 38, Level.STOREONE_LEVEL, 7, false, 9, false);
        setDestinations(64, 38, Level.STOREONE_LEVEL, 7, false, 9, false);
        
        setDestinations(59, 25, Level.SCOTT_HOUSE_LEVEL, 10, false, 17, false);
        setDestinations(60, 25, Level.SCOTT_HOUSE_LEVEL, 10, false, 17, false);
        
        setDestinations(58, 63, Level.SIDEWAYS_HOUSE_LEVEL, 3, false, 5, true);
        setDestinations(58, 64, Level.SIDEWAYS_HOUSE_LEVEL, 3, false, 5, true);
        
        setDestinations(5, 22, 9, 4, true, 6, false);
        
        setDestinations(22, 46, Level.POTION_SHOP_LEVEL, 4, true, 6, true);
        
        setDestinations(31, 27, Level.DUPLEX_LEVEL, 9, false, 8, false);
        setDestinations(32, 27, Level.DUPLEX_LEVEL, 9, false, 8, false);
        setDestinations(36, 27, Level.DUPLEX_LEVEL, 14, false, 8, false);
        setDestinations(37, 27, Level.DUPLEX_LEVEL, 14, false, 8, false);
        
        setDestinations(69, 11, Level.RIGHT_ROUTE_LEVEL, 1, false, 57, false);
        setDestinations(69, 12, Level.RIGHT_ROUTE_LEVEL, 1, false, 57, false);
        
        setDestinations(0, 9, Level.LEFT_ROUTE_LEVEL, 49, false, 56, false);
        setDestinations(0, 10, Level.LEFT_ROUTE_LEVEL, 49, false, 56, false);


        //destinations[18 + 69 * width][0] = destinations[19 + 69 * width][0] = destinations[20 + 69 * width][0] = 1;
        //destinations[18 + 69 * width][1] = destinations[19 + 69 * width][1] = destinations[20 + 69 * width][1] = 11 * 16 + 8;
        //destinations[18 + 69 * width][2] = destinations[19 + 69 * width][2] = destinations[20 + 69 * width][2] = 2 * 16;

        destinations[56 + 49 * width][0] = destinations[57 + 49 * width][0] = 3;
        destinations[56 + 49 * width][1] = destinations[57 + 49 * width][1] = 7 * 16;
        destinations[56 + 49 * width][2] = destinations[57 + 49 * width][2] = 9 * 16;

        destinations[33 + 45 * width][0] = destinations[34 + 45 * width][0] = 4;
        destinations[33 + 45 * width][1] = destinations[34 + 45 * width][1] = 8 * 16;
        destinations[33 + 45 * width][2] = destinations[34 + 45 * width][2] = 17 * 16;

    }

    protected void setDestinations(int xTile, int yTile, int levelNum, int xDestTile, boolean xMid, int yDestTile, boolean yMid) {
        int i2 = 0;
        int addEightX = 0;
        int addEightY = 0;
        if (xMid) addEightX = 8;
        if (yMid) addEightY = 8;
        destinations[xTile + yTile * width][i2++] = levelNum;
        destinations[xTile + yTile * width][i2++] = (xDestTile << 4) + addEightX;
        destinations[xTile + yTile * width][i2] = (yDestTile << 4) + addEightY;
    }

    public boolean checkExit(int x, int y) {
        // replaced if ((y == 1118 || y == 1117) && x >= 288 && x <= 335) return true;
        if (bottomOf(y, 69) && inXRangeOf(x, 18, 20)) return true;
        // replaced else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
        else if (topOf(y, 49) && inXRangeOf(x, 56, 57)) return true;
        // replaced else if ((y == 722 || y == 723) && x >= 528 && x <= 559) return true;
        else if (topOf(y, 45) && inXRangeOf(x, 33, 34)) return true;
        else if (topOf(y, 0) && inXRangeOf(x, 8, 9)) return true;
        else if (topOf(y, 38) && inXRangeOf(x, 52, 53)) return true;
        else if (topOf(y, 38) && inXRangeOf(x, 63, 64)) return true;
        else if (topOf(y , 22) && inXRangeOf(x, 5, 5)) return true;
        else if (topOf(y, 25) && inXRangeOf(x, 59, 60)) return true;
        else if (topOf(y, 46) && inXRangeOf(x, 22, 22)) return true;
        else if (topOf(y, 27) && inXRangeOf(x, 31, 32)) return true;
        else if (topOf(y, 27) && inXRangeOf(x, 36, 37)) return true;
        else if (rightOf(x, 69) && inYRangeOf(y, 11, 12)) return true;
        else if (leftOf(x, 0) && inYRangeOf(y, 9, 10)) return true;
        if ((x == 937 || x == 936) && inYRangeOf(y, 63, 64)) return true;
        return false;
    }

    protected boolean topOf(int playerY, int tileY) {
        return playerY == ((tileY << 4) + 1) || playerY == ((tileY << 4) + 2);
    }

    protected boolean bottomOf(int playerY, int tileY) {
        return playerY == ((tileY + 1) << 4) - 2 || playerY == ((tileY + 1) << 4) - 3;
    }

    protected boolean rightOf(int playerX, int tileX) {
        return playerX == ((tileX + 1) << 4) - 1 || playerX == ((tileX + 1) << 4) - 2;
    }

    protected boolean leftOf(int playerX, int tileX) {
        return playerX == (tileX << 4) || playerX == (tileX << 4) + 1;
    }

    protected boolean inXRangeOf(int playerX, int begXTile, int endXTile) {
        return playerX >= (begXTile << 4) && playerX <= ((endXTile + 1) << 4) - 1;
    }

    protected boolean inYRangeOf(int playerY, int begYTile, int endYTile) {
        return playerY >= (begYTile << 4) && playerY <= ((endYTile + 1) << 4) - 1;
    }

    public String[] readMessage(int x, int y) throws FileNotFoundException {
            String s1 = new String();
            String s2 = new String();
            String s3 = new String();
            boolean iSaySo = true;
            File file = new File("C:/users/aaron/workspace/bankdialogs/sign_dialogs.txt");
            Scanner inputFile = new Scanner(file);

            do {
                    s1 = inputFile.nextLine();
                    s2 = inputFile.nextLine();
                    s3 = inputFile.nextLine();
                    if (s1.equals(Integer.toString(x)) && s3.equals(Integer.toString(y))) {
                            iSaySo = false;
                    }
                    else if (s2.equals(Integer.toString(x)) && s3.equals(Integer.toString(y))) {
                            iSaySo = false;
                    }
                    else {
                            s1 = inputFile.nextLine();
                            for (int i = 0; i < Integer.parseInt(s1) + 1; i++) {
                                    inputFile.nextLine();
                            }
                    }
            }
            while (iSaySo);		

            int lines = Integer.parseInt(inputFile.nextLine());
            String[] array = new String[lines];
            for (int i = 0; i < lines; i++) {
                    s1 = inputFile.nextLine();
                    array[i] = s1;
            }
            inputFile.close();
            return array;
    }

    public int getLevelNum() {
        return LEVELNUM;
    }

    public boolean npcHere(int x, int y) {
        boolean npcHere = false;
        for (int i = 0; i < npcs.size(); i++) {
            if (npcs.get(i).npcHere(x, y)) npcHere = true;
        }
        return npcHere;
    }
    
    public boolean signHere(int xp, int yp) {
        boolean signHere = false;
        for (int i = 0; i < signs.size(); i++) {
            if (signs.get(i).signHere(xp, yp)) signHere = true;
        }
        return signHere;
    }
    
    public boolean chestHere(int xp, int yp) {
        boolean chestHere = false;
        for (int i = 0; i < chests.size(); i++) {
            if (chests.get(i).chestHere(xp, yp)) chestHere = true;
        }
        return chestHere;
    }
    
    public boolean npcAhead(int x, int y) {
        boolean npcHere = false;
        int counter = 0;
        for (int i = 0; i < npcs.size(); i++) {
            if (npcs.get(i).npcAhead(x, y)) counter++;
        }
        if (counter > 1) npcHere = true;
        return npcHere;
    }
    
    public Chest getChest(int xp, int yp) {
        for (int i = 0; i < chests.size(); i++) {
            int xx = chests.get(i).x;
            int yy = chests.get(i).y;
            if (xx <= xp && xx + 32 >= xp && yy <= yp && yy + 32 >= yp) return chests.get(i);
        }
        return null;
    }
    
    public Signs getSign(int xp, int yp) {
        for (int i = 0; i < signs.size(); i++) {
            int xx = signs.get(i).x;
            int yy = signs.get(i).y;
            if (xx <= xp - 4 && xx + 28 >= xp && yy <= yp && yy + 28 >= yp) {
                return signs.get(i);
            }
        }
        return null;
    }
    
    public boolean[] getChestsOnLevel() {
        boolean[] chestStatus = new boolean[chests.size()];
        for (int i = 0; i < chests.size(); i++) {
            chestStatus[i] = (chests.get(i).isOpen());
        }
        return chestStatus;
    }
    
    public void setChests(boolean[] chestStatus) {
        for (int i = 0; i < chests.size(); i++) {
            if (chestStatus[i]) chests.get(i).opened = true;
            else chests.get(i).opened = false;
        }
    }
    
    public boolean furnitureHere(int xp, int yp) {
        boolean furnitureHere = false;
        for (int i = 0; i < furniture.size(); i++) {
            if (furniture.get(i).furnitureHere(xp, yp)) furnitureHere = true;
        }
        return furnitureHere;
    }

    public Npc getNpc(int x, int y) {
        for (int i = 0; i < npcs.size(); i++) {
            int xx = npcs.get(i).x;
            int yy = npcs.get(i).y;
            if (xx - 13 <= x && x <= xx + 13 && yy - 16 <= y && y <= yy + 15) {
                return npcs.get(i);
            }
        }
        return null;
    }

    public void update() {
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).update();
        }
        for (int i = 0; i < chests.size(); i++) {
            chests.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < furniture.size(); i++) {
            furniture.get(i).render(screen);
        }
        for (int i = 0; i < chests.size(); i++) {
            chests.get(i).render(screen);
        }
        for (int i = 0; i < signs.size(); i++) {
            signs.get(i).render(screen);
        }
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).render(screen);
        }
    }

}