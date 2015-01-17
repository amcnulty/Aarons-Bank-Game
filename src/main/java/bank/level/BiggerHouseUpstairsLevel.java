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
public class BiggerHouseUpstairsLevel extends Level {
    
    private final int LEVELNUM = 5;
    
    private ArrayList<Npc> biggerHouseUpstairsLevelNpcs = new ArrayList<>();
    
    public BiggerHouseUpstairsLevel(String path) {
        super(path);
        biggerHouseUpstairsLevelNpcs.add(new MaleNpc(2 * 16, 8 * 16, "/dialogs/biggerHouseLevel/Luke.txt"));
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
        destinations[7 + 1 * width][0] = 4;
        destinations[7 + 1 * width][1] =  14 * 16;
        destinations[7 + 1 * width][2] = 5 * 16;
        
    }
    
    public boolean checkExit(int x, int y) {
		if ((y == 17 || y == 18) && x >= 112 && x <= 143) return true;
		//else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
		//else if ((x == 382 || x == 383) && y >= 160 && y <= 207) return true;
		return false;
	}
    
    public int getLevelNum() {
            return LEVELNUM;
        }
        
    public boolean npcHere(int x, int y) {
        boolean npcHere = false;
        for (int i = 0; i < biggerHouseUpstairsLevelNpcs.size(); i++) {
            if (biggerHouseUpstairsLevelNpcs.get(i).npcHere(x, y)) npcHere = true;
        }
        return npcHere;
    }
    
    public Npc getNpc(int x, int y) {
        for (int i = 0; i < biggerHouseUpstairsLevelNpcs.size(); i++) {
            int xx = biggerHouseUpstairsLevelNpcs.get(i).x;
            int yy = biggerHouseUpstairsLevelNpcs.get(i).y;
            if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                return biggerHouseUpstairsLevelNpcs.get(i);
            }
        }
        return null;
    }
    
    public void update() {
        for (int i = 0; i < biggerHouseUpstairsLevelNpcs.size(); i++) {
            biggerHouseUpstairsLevelNpcs.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < biggerHouseUpstairsLevelNpcs.size(); i++) {
            biggerHouseUpstairsLevelNpcs.get(i).render(screen);
        }
    }
    
}
