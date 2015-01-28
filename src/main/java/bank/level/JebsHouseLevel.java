/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.furniture.Furniture;
import bank.entity.mob.Npc.Npc;
import bank.graphics.Screen;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
class JebsHouseLevel extends Level {

    private final int LEVELNUM = 14;
    
    private ArrayList<Furniture> furniture = new ArrayList<>();
    private ArrayList<Npc> npcs = new ArrayList<>();
    
    public JebsHouseLevel(String path) {
        super(path);
        addNpcs();
        addFurniture();
    }

    private void addNpcs() {
        npcs.add(new Npc(52, 62, 1, "/dialogs/swampLevel/jeb.txt"));
        
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).init(this);
        }
    }
    
    private void addFurniture() {
        furniture.add(new Furniture(33, 33, Furniture.SMALL_COUCH));
        furniture.add(new Furniture(64, 36, Furniture.AIR_COMPRESSOR));
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
        // to swamp level
        setDestinations(4, 7, Level.SWAMP_LEVEL, 43, true, 48, true);
    }
    
    public boolean checkExit(int x, int y) {
        if (bottomOf(y, 7) && inXRangeOf(x, 4, 4)) return true;
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
        
    }
    
    public void render(Screen screen) {
        for (int i = 0; i < furniture.size(); i++) {
            furniture.get(i).render(screen);
        }
        for (int i = 0; i < npcs.size(); i++) {
            npcs.get(i).render(screen);
        }
    }
    
}
