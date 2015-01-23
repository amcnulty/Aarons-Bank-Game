/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

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
public class house1SubLevel extends Level {
    
    private final int LEVELNUM = 3;
    
    private ArrayList<Npc> houseSubLevelNpcs = new ArrayList<>();

	public house1SubLevel(String path) {
		super(path);
               // houseSubLevelNpcs.add(new FemaleNpcClerk(64, 48, 1, "/dialogs/houseSubLevel/clerk.txt"));
               // houseSubLevelNpcs.add(new MaleNpc(8 * 16, 6 * 16, "/dialogs/houseSubLevel/dude.txt"));
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
		destinations[6 + 10 * width][0] = destinations[7 + 10 * width][0] = 2;
		destinations[6 + 10 * width][1] = destinations[7 + 10 * width][1] = 57 * 16;
		destinations[6 + 10 * width][2] = destinations[7 + 10 * width][2] = 50 * 16;
	}
	
	public boolean checkExit(int x, int y) {
		if ((y == 174 || y == 173) && x >= 96 && x <= 127) return true;
		//else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
		//else if ((x == 382 || x == 383) && y >= 160 && y <= 207) return true;
		return false;
	}
        
        public int getLevelNum() {
            return LEVELNUM;
        }
        
        public boolean npcHere(int x, int y) {
            boolean npcHere = false;
            for (int i = 0; i < houseSubLevelNpcs.size(); i++) {
                if (houseSubLevelNpcs.get(i).npcHere(x, y)) npcHere = true;
            }
            return npcHere;
        }
        
        public Npc getNpc(int x, int y) {
            for (int i = 0; i < houseSubLevelNpcs.size(); i++) {
                int xx = houseSubLevelNpcs.get(i).x;
                int yy = houseSubLevelNpcs.get(i).y;
                if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                    return houseSubLevelNpcs.get(i);
                }
            }
            return null;
        }
        
    public void update() {
        for (int i = 0; i < houseSubLevelNpcs.size(); i++) {
            houseSubLevelNpcs.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < houseSubLevelNpcs.size(); i++) {
            houseSubLevelNpcs.get(i).render(screen);
        }
    }
}
