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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
class RightRouteLevel extends Level {
    
    private final int LEVELNUM = 17;
    
    private ArrayList<Furniture> furniture = new ArrayList<>();
    private ArrayList<Npc> npcs = new ArrayList<>();
    private ArrayList<Chest> chests = new ArrayList<>();
    private ArrayList<Signs> signs = new ArrayList<>();

    public RightRouteLevel(String path) {
        super(path);
        addNpcs();
    }
    
    private void addNpcs() {
        npcs.add(new Npc(73, 883, 3, "/dialogs/demo.txt"));
        
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).init(this);
        }
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
        // to crazy level
        setDestinations(0, 56, Level.CRAZY_LEVEL, 68, false, 12, false);
        setDestinations(0, 57, Level.CRAZY_LEVEL, 68, false, 12, false);
    }
    
    public boolean checkExit(int x, int y) {
        if (leftOf(x, 0) && inYRangeOf(y, 56, 57)) return true;
        return false;
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
    
    public boolean[] getNpcBoolean() {
        boolean[] array = new boolean[npcs.size()];
        for (int i = 0; i < npcs.size(); i++) {
            array[i] = npcs.get(i).movedOutOfWay;
        }
        return array;
    }
    
    public void setNpcBoolean(boolean[] movedOutOfWay) {
        for (int i = 0; i < movedOutOfWay.length; i++) {
            npcs.get(i).movedOutOfWay = movedOutOfWay[i];
        }
    }
    
    public int[] getNpcX() {
        int[] array = new int[npcs.size()];
        for (int i = 0; i < npcs.size(); i++) {
            array[i] = npcs.get(i).x;
        }
        return array;
    }
    
    public void setNpcX(int[] savedX) {
        for (int i = 0; i < savedX.length; i++) {
            npcs.get(i).x = savedX[i];
        }
    }
    
    public int[] getNpcY() {
        int[] array = new int[npcs.size()];
        for (int i = 0; i < npcs.size(); i++) {
            array[i] = npcs.get(i).y;
        }
        return array;
    }
    
    public void setNpcY(int[] savedY) {
        for (int i = 0; i < savedY.length; i++) {
            npcs.get(i).y = savedY[i];
        }
    }
    
    public boolean furnitureHere(int xp, int yp) {
        boolean furnitureHere = false;
        for (int i = 0; i < furniture.size(); i++) {
            if (furniture.get(i).furnitureHere(xp, yp)) furnitureHere = true;
        }
        return furnitureHere;
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
