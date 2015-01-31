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
public class BiggerHouseLevel extends Level {
    
    private final int LEVELNUM = 4;
    
    private ArrayList<Npc> npcs = new ArrayList<>();
    private ArrayList<Furniture> furniture = new ArrayList<>();
    
    public BiggerHouseLevel(String path) {
        super(path);
        addFurniture();
        addNpcs();
       // biggerHouseLevelNpcs.add(new MaleNpc(5 * 16, 14 * 16, "/dialogs/biggerHouseLevel/Ed Gone.txt"));
       // biggerHouseLevelNpcs.add(new MaleNpc(13 * 16, 15 * 16, "/dialogs/biggerHouseLevel/Night Owl.txt"));
    }
    
    private void addNpcs() {
        npcs.add(new Npc(82, 249, 1, "/dialogs/biggerHouseLevel/Ed Gone.txt"));
        npcs.add(new Npc(217, 258, 1, "/dialogs/biggerHouseLevel/Night Owl.txt"));
    }
    
    private void addFurniture() {
        furniture.add(new Furniture(28, 192, Furniture.FRIDGE_ONE));
        furniture.add(new Furniture(46, 192, Furniture.FRIDGE_TWO));
        furniture.add(new Furniture(66, 192, Furniture.OVEN));
        furniture.add(new Furniture(206, 144, Furniture.DRESSER));
        furniture.add(new Furniture(206, 178, Furniture.OFFICE_CHAIR));
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
        
        destinations[7 + 18 * width][0] = destinations[8 + 18 * width][0] = 2;
        destinations[7 + 18 * width][1] = destinations[8 + 18 * width][1] = 34 * 16;
        destinations[7 + 18 * width][2] = destinations[8 + 18 * width][2] = 46 * 16;
        
        destinations[14 + 6 * width][0] = 5;
        destinations[14 + 6 * width][1] = 7 * 16;
        destinations[14 + 6 * width][2] = 4 * 16;
    }
    
    public boolean checkExit(int x, int y) {
		if ((y == 98 || y == 99) && x >= 224 && x <= 239) return true;
                else if ((y == 302 || y == 301) && x >= 112 && x <= 143) return true;
		//else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
		//else if ((x == 382 || x == 383) && y >= 160 && y <= 207) return true;
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
