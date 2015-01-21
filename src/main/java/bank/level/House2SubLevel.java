/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.furniture.Furniture;
import bank.entity.mob.Npc.MaleNpc;
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
class House2SubLevel extends Level {

    private final int LEVELNUM = 7;
    
    private ArrayList<Furniture> furniture = new ArrayList<>();
    private ArrayList<Npc> npcs = new ArrayList<>();
    
    public House2SubLevel(String path) {
        super(path);
        addFurniture();
        addNPCs();
    }
    
    private void addNPCs() {
        npcs.add(new MaleNpc(136, 88, "/dialogs/crazyLevel/Anthony.txt"));
    }
    
    private void addFurniture() {
        // Left Room
        furniture.add(new Furniture(27, 32, Furniture.FRIDGE_ONE));
        furniture.add(new Furniture(67, 32, Furniture.OVEN));
        furniture.add(new Furniture(46, 31, Furniture.FRIDGE_TWO));
        furniture.add(new Furniture(25, 66, Furniture.BARREL));
        // Right Room
        furniture.add(new Furniture(115, 34, Furniture.BIG_COUCH));
        furniture.add(new Furniture(149, 33, Furniture.SMALL_COUCH));
        furniture.add(new Furniture(164, 80, Furniture.DRESSER));
        furniture.add(new Furniture(164, 99, Furniture.OFFICE_CHAIR));
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
        setDestinations(6, 10, 2, 53, false, 40, false);
        setDestinations(7, 10, 2, 53, false, 40, false);
    }
    
    public boolean checkExit(int x, int y) {
        return (bottomOf(y, 10) && inXRangeOf(x, 6, 7));
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
    
    public Npc getNpc(int x, int y) {
        for (int i = 0; i < npcs.size(); i++) {
            int xx = npcs.get(i).x;
            int yy = npcs.get(i).y;
            if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                return npcs.get(i);
            }
        }
        return null;
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
