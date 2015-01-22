/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.chests.Chest;
import bank.entity.furniture.Furniture;
import bank.graphics.Screen;
import bank.inventory.Items;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
class UnderGroundCrazyLevel extends Level {

    private final int LEVELNUM = 12;
    
    private ArrayList<Furniture> furniture = new ArrayList<>();
    private ArrayList<Chest> chests = new ArrayList<>();
    
    public UnderGroundCrazyLevel(String path) {
        super(path);
        //addFurniture();
        addChests();
    }
    
    private void addChests() {
        chests.add(new Chest(2,19, 2, Items.BLACK_DAGGER));
        chests.add(new Chest(19, 27, 3, Items.LEATHER_TUNIC));
    }
    
    private void addFurniture() {
        furniture.add(new Furniture(83, 256, Furniture.OVEN));
        furniture.add(new Furniture(26, 208, Furniture.FRIDGE_ONE));
        furniture.add(new Furniture(44, 208, Furniture.FRIDGE_TWO));
        furniture.add(new Furniture(238, 178, Furniture.BIG_COUCH));
        furniture.add(new Furniture(208, 176, Furniture.SMALL_COUCH));
        furniture.add(new Furniture(180, 176, Furniture.DRESSER));
        furniture.add(new Furniture(159, 176, Furniture.DRESSER));
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
        setDestinations(25, 43, Level.SIDEWAYS_HOUSE_LEVEL, 8, true, 3, false);
        setDestinations(2, 4, Level.SCOTT_HOUSE_LEVEL, 2, true, 6, true);
    }
    
    public boolean checkExit(int x, int y) {
        if (leftOf(x, 1) && inYRangeOf(y, 5, 6)) return true;
        else if (bottomOf(y, 43) && inXRangeOf(x, 25, 25)) return true;
        else if (bottomOf(y, 4) && inXRangeOf(x, 2, 2)) return true;
        return false;
    }
    
    public int getLevelNum() {
        return LEVELNUM;
    }
    
    
    public boolean furnitureHere(int xp, int yp) {
        boolean furnitureHere = false;
        for (int i = 0; i < furniture.size(); i++) {
            if (furniture.get(i).furnitureHere(xp, yp)) furnitureHere = true;
        }
        return furnitureHere;
    }
    
    public boolean chestHere(int xp, int yp) {
        boolean chestHere = false;
        for (int i = 0; i < chests.size(); i++) {
            if (chests.get(i).chestHere(xp, yp)) chestHere = true;
        }
        return chestHere;
    }
    
    public Chest getChest(int xp, int yp) {
        for (int i = 0; i < chests.size(); i++) {
            int xx = chests.get(i).x;
            int yy = chests.get(i).y;
            if (xx <= xp && xx + 32 >= xp && yy <= yp && yy + 32 >= yp) return chests.get(i);
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
    
    public void update() {
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
    }
    
}
