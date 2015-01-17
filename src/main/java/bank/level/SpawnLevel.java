/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.chests.Chest;
import bank.entity.mob.Npc.MaleNpc;
import bank.entity.mob.Npc.FemaleNpc;
import bank.entity.mob.Npc.Npc;
import bank.graphics.Screen;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
public class SpawnLevel extends Level {
	
    private final int LEVELNUM = 1;
    
    private static String dialogFilePath = "/dialogs/sign_dialogs.txt";
    
    private ArrayList<Chest> spawnLevelChests = new ArrayList<>();
    private ArrayList<Npc> spawnLevelNpcs = new ArrayList<>();

    public SpawnLevel(String path) {
        super(path);
        addNpcs();
        addChests();
    }
    
    private void addChests() {
        spawnLevelChests.add(new Chest(10, 11, 1, 30));
        spawnLevelChests.add(new Chest(3, 15, 2, 23));
        spawnLevelChests.add(new Chest(20, 18, 2, 34));
        spawnLevelChests.add(new Chest(3, 25, 3, 25));
        spawnLevelChests.add(new Chest(20, 21, 1, 40));
        spawnLevelChests.add(new Chest(20, 24, 2, 41));
        spawnLevelChests.add(new Chest(20, 27, 3, 42));
        spawnLevelChests.add(new Chest(20, 30, 1, 39));
        spawnLevelChests.add(new Chest(20, 33, 2, 38));
        spawnLevelChests.add(new Chest(20, 36, 2, 4));
        spawnLevelChests.add(new Chest(20, 39, 1, 8));
        spawnLevelChests.add(new Chest(20, 42, 3, 11));
        spawnLevelChests.add(new Chest(3, 30, 1, 5));
        spawnLevelChests.add(new Chest(3, 33, 2, 6));
        spawnLevelChests.add(new Chest(3, 36, 3, 7));
        spawnLevelChests.add(new Chest(3, 39, 1, 3));
        spawnLevelChests.add(new Chest(3, 42, 1, 9));
        spawnLevelChests.add(new Chest(7, 36, 2, 10));
        spawnLevelChests.add(new Chest(7, 39, 3, 12));
        //spawnLevelChests.add(new Chest(3, 30, 1, 5));
        //spawnLevelChests.add(new Chest(3, 30, 1, 5));
        //spawnLevelChests.add(new Chest(3, 30, 1, 5));
        //spawnLevelChests.add(new Chest(3, 30, 1, 5));
        //spawnLevelChests.add(new Chest(3, 30, 1, 5));
    }
    
    private void addNpcs() {
        spawnLevelNpcs.add(new FemaleNpc(250, 100, "/dialogs/spawnLevel/gatekeeper.txt"));
        spawnLevelNpcs.add(new MaleNpc(100, 110, "/dialogs/spawnLevel/jeb.txt"));
        spawnLevelNpcs.add(new FemaleNpc(160, 270));
        spawnLevelNpcs.add(new FemaleNpc(125, 330));
        spawnLevelNpcs.add(new MaleNpc(90, 330));
        spawnLevelNpcs.add(new FemaleNpc(152, 450));
        spawnLevelNpcs.add(new FemaleNpc(250, 400));
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
        destinations = new int[width * height][3];									// Create 2D array rows: # of tiles; columns: level number, x coordinate pixel, y coordinate pixel
        destinations[10][0] = destinations[11][0] = destinations[12][0] = 2;		// set up level number
        destinations[10][1] = destinations[11][1] = destinations[12][1] = 19 * 16 + 8;	// set up x coordinate pixel
        destinations[10][2] = destinations[11][2] = destinations[12][2] = 67 * 16;	// set up y coordiante pixel

        destinations[4 * width][0] = destinations[5 * width][0] = destinations[6 * width][0] = 1;
        destinations[4 * width][1] = destinations[5 * width][1] = destinations[6 * width][1] = 21 * 16 + 8;
        destinations[4 * width][2] = destinations[5 * width][2] = destinations[6 * width][2] = 11 * 16;

        destinations[23 + 10 * width][0] = destinations[23 + 11 * width][0] = destinations[23 + 12 * width][0] = 1;
        destinations[23 + 10 * width][1] = destinations[23 + 11 * width][1] = destinations[23 + 12 * width][1] = 2 * 16 + 8;
        destinations[23 + 10 * width][2] = destinations[23 + 11 * width][2] = destinations[23 + 12 * width][2] = 5 * 16;
    }

    public String[] readMessage(int x, int y) throws FileNotFoundException {
        String s1 = new String();
        String s2 = new String();
        String s3 = new String();
        boolean iSaySo = true;
        BufferedReader textReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dialogFilePath)));
        Scanner inputFile = new Scanner(textReader);

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
        if ((x == 1 || x == 2) && y >= 64 && y <= 111) return true;
        else if ((y == 1 || y == 2) && x >= 160 && x <= 207) return true;
        else if ((x == 382 || x == 383) && y >= 160 && y <= 207) return true;
        return false;
    }

    public int getLevelNum() {
        return LEVELNUM;
    }

    public boolean npcHere(int x, int y) {
        boolean npcHere = false;
        for (int i = 0; i < spawnLevelNpcs.size(); i++) {
            if (spawnLevelNpcs.get(i).npcHere(x, y)) npcHere = true;
        }
        return npcHere;
    }
    
    public boolean chestHere(int xp, int yp) {
        boolean chestHere = false;
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            if (spawnLevelChests.get(i).chestHere(xp, yp)) chestHere = true;
        }
        return chestHere;
    }

    public Npc getNpc(int x, int y) {
        for (int i = 0; i < spawnLevelNpcs.size(); i++) {
            int xx = spawnLevelNpcs.get(i).x;
            int yy = spawnLevelNpcs.get(i).y;
            if (xx + 3 <= x && x <= xx + 28 && yy + 2 <= y && y <= yy + 32) {
                return spawnLevelNpcs.get(i);
            }
        }
        return null;
    }
    
    public Chest getChest(int xp, int yp) {
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            int xx = spawnLevelChests.get(i).x;
            int yy = spawnLevelChests.get(i).y;
            if (xx <= xp && xx + 32 >= xp && yy <= yp && yy + 32 >= yp) return spawnLevelChests.get(i);
        }
        return null;
    }
    
    /**
     * Gets the open/closed status of all the chests on the level
     * @return an array of booleans True = chest is open False = chest has not been opened
     */
    
    public boolean[] getChestsOnLevel() {
        boolean[] chestStatus = new boolean[spawnLevelChests.size()];
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            chestStatus[i] = (spawnLevelChests.get(i).isOpen());
        }
        return chestStatus;
    }
    
    public void setChests(boolean[] chestStatus) {
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            if (chestStatus[i]) spawnLevelChests.get(i).opened = true;
            else spawnLevelChests.get(i).opened = false;
        }
    }
    
    public void update() {
        for (int i = 0; i < spawnLevelNpcs.size(); i++) {
            spawnLevelNpcs.get(i).update();
        }
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            spawnLevelChests.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < spawnLevelNpcs.size(); i++) {
            spawnLevelNpcs.get(i).render(screen);
        }
        for (int i = 0; i < spawnLevelChests.size(); i++) {
            spawnLevelChests.get(i).render(screen);
        }
    }

}
