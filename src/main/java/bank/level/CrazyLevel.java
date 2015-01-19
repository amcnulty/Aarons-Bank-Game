/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.mob.Npc.FemaleNpc;
import bank.entity.mob.Npc.FemaleNpcClerk;
import bank.entity.mob.Npc.MaleNpc;
import bank.entity.mob.Npc.Npc;
import bank.graphics.Screen;
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
    
    private ArrayList<Npc> crazyLevelNpcs = new ArrayList<>();

	public CrazyLevel(String path) {
		super(path);
                crazyLevelNpcs.add(new FemaleNpcClerk(56 * 16, 55 * 16, 2));
                crazyLevelNpcs.add(new MaleNpc(34 * 16, 52 * 16, "/dialogs/crazyLevel/Cody.txt"));
                crazyLevelNpcs.add(new FemaleNpc(37 * 16, 52 * 16, "/dialogs/crazyLevel/Kali.txt"));
                crazyLevelNpcs.add(new FemaleNpc(10 * 16, 31 * 16, "/dialogs/crazyLevel/Bobbie.txt"));
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
		destinations[18 + 69 * width][0] = destinations[19 + 69 * width][0] = destinations[20 + 69 * width][0] = 1;
		destinations[18 + 69 * width][1] = destinations[19 + 69 * width][1] = destinations[20 + 69 * width][1] = 11 * 16 + 8;
		destinations[18 + 69 * width][2] = destinations[19 + 69 * width][2] = destinations[20 + 69 * width][2] = 2 * 16;
		
		destinations[56 + 49 * width][0] = destinations[57 + 49 * width][0] = 3;
		destinations[56 + 49 * width][1] = destinations[57 + 49 * width][1] = 7 * 16;
		destinations[56 + 49 * width][2] = destinations[57 + 49 * width][2] = 9 * 16;
                
                destinations[33 + 45 * width][0] = destinations[34 + 45 * width][0] = 4;
		destinations[33 + 45 * width][1] = destinations[34 + 45 * width][1] = 8 * 16;
		destinations[33 + 45 * width][2] = destinations[34 + 45 * width][2] = 17 * 16;
		
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
	
	public boolean checkExit(int x, int y) {
		if ((y == 1118 || y == 1117) && x >= 288 && x <= 335) return true;
		else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
                else if ((y == 722 || y == 723) && x >= 528 && x <= 559) return true;
		//else if ((x == 382 || x == 383) && y >= 160 && y <= 207) return true;
		return false;
	}
        
        public int getLevelNum() {
            return LEVELNUM;
        }
        
        public boolean npcHere(int x, int y) {
            boolean npcHere = false;
            for (int i = 0; i < crazyLevelNpcs.size(); i++) {
                if (crazyLevelNpcs.get(i).npcHere(x, y)) npcHere = true;
            }
            return npcHere;
        }
        
        public Npc getNpc(int x, int y) {
            for (int i = 0; i < crazyLevelNpcs.size(); i++) {
                int xx = crazyLevelNpcs.get(i).x;
                int yy = crazyLevelNpcs.get(i).y;
                if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                    return crazyLevelNpcs.get(i);
                }
            }
            return null;
        }
        
        public void update() {
            for (int i = 0; i < crazyLevelNpcs.size(); i++) {
                crazyLevelNpcs.get(i).update();
            }
        }
        
        public void render(Screen screen) {
            for (int i = 0; i < crazyLevelNpcs.size(); i++) {
                crazyLevelNpcs.get(i).render(screen);
            }
        }

}