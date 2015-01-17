/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

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
public class BiggerHouseLevel extends Level {
    
    private final int LEVELNUM = 4;
    
    private ArrayList<Npc> biggerHouseLevelNpcs = new ArrayList<>();
    
    public BiggerHouseLevel(String path) {
        super(path);
        biggerHouseLevelNpcs.add(new MaleNpc(5 * 16, 14 * 16, "/dialogs/biggerHouseLevel/Ed Gone.txt"));
        biggerHouseLevelNpcs.add(new MaleNpc(13 * 16, 15 * 16, "/dialogs/biggerHouseLevel/Night Owl.txt"));
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
        for (int i = 0; i < biggerHouseLevelNpcs.size(); i++) {
            if (biggerHouseLevelNpcs.get(i).npcHere(x, y)) npcHere = true;
        }
        return npcHere;
    }
    
    public Npc getNpc(int x, int y) {
        for (int i = 0; i < biggerHouseLevelNpcs.size(); i++) {
            int xx = biggerHouseLevelNpcs.get(i).x;
            int yy = biggerHouseLevelNpcs.get(i).y;
            if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                return biggerHouseLevelNpcs.get(i);
            }
        }
        return null;
    }
    
    public void update() {
        for (int i = 0; i < biggerHouseLevelNpcs.size(); i++) {
            biggerHouseLevelNpcs.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < biggerHouseLevelNpcs.size(); i++) {
            biggerHouseLevelNpcs.get(i).render(screen);
        }
    }
    
}
