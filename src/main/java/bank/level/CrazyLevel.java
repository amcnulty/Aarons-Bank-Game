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
    private ArrayList<Furniture> furniture = new ArrayList<>();

    public CrazyLevel(String path) {
        super(path);
        //addNpcs();
        addFurniture();
    }

    private void addNpcs() {
    }
    
    private void addFurniture() {
        furniture.add(new Furniture(1036, 307, Furniture.AIR_COMPRESSOR));
        furniture.add(new Furniture(801, 381, Furniture.BARREL));
        furniture.add(new Furniture(819, 381, Furniture.BARREL));
        furniture.add(new Furniture(1054, 381, Furniture.BARREL));
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
        setDestinations(18, 69, Level.SPAWN_LEVEL, 11, true, 2, false);
        setDestinations(19, 69, Level.SPAWN_LEVEL, 11, true, 2, false);
        setDestinations(20, 69, Level.SPAWN_LEVEL, 11, true, 2, false);

        setDestinations(8, 0, Level.MAZE_LEVEL, 34, true, 68, false);
        setDestinations(9, 0, Level.MAZE_LEVEL, 34, true, 68, false);

        setDestinations(52, 38, Level.HOUSE2_SUBLEVEL, 7, false, 9, false);
        setDestinations(53, 38, Level.HOUSE2_SUBLEVEL, 7, false, 9, false);

        setDestinations(63, 38, Level.STOREONE_LEVEL, 7, false, 9, false);
        setDestinations(64, 38, Level.STOREONE_LEVEL, 7, false, 9, false);
        
        setDestinations(59, 25, Level.SCOTT_HOUSE_LEVEL, 10, false, 17, false);
        setDestinations(60, 25, Level.SCOTT_HOUSE_LEVEL, 10, false, 17, false);
        
        setDestinations(58, 63, Level.SIDEWAYS_HOUSE_LEVEL, 3, false, 5, true);
        setDestinations(58, 64, Level.SIDEWAYS_HOUSE_LEVEL, 3, false, 5, true);
        
        setDestinations(5, 22, 9, 4, true, 6, false);


        //destinations[18 + 69 * width][0] = destinations[19 + 69 * width][0] = destinations[20 + 69 * width][0] = 1;
        //destinations[18 + 69 * width][1] = destinations[19 + 69 * width][1] = destinations[20 + 69 * width][1] = 11 * 16 + 8;
        //destinations[18 + 69 * width][2] = destinations[19 + 69 * width][2] = destinations[20 + 69 * width][2] = 2 * 16;

        destinations[56 + 49 * width][0] = destinations[57 + 49 * width][0] = 3;
        destinations[56 + 49 * width][1] = destinations[57 + 49 * width][1] = 7 * 16;
        destinations[56 + 49 * width][2] = destinations[57 + 49 * width][2] = 9 * 16;

        destinations[33 + 45 * width][0] = destinations[34 + 45 * width][0] = 4;
        destinations[33 + 45 * width][1] = destinations[34 + 45 * width][1] = 8 * 16;
        destinations[33 + 45 * width][2] = destinations[34 + 45 * width][2] = 17 * 16;

    }

    protected void setDestinations(int xTile, int yTile, int levelNum, int xDestTile, boolean xMid, int yDestTile, boolean yMid) {
        int i2 = 0;
        int addEightX = 0;
        int addEightY = 0;
        if (xMid) addEightX = 8;
        if (yMid) addEightY = 8;
        destinations[xTile + yTile * width][i2++] = levelNum;
        destinations[xTile + yTile * width][i2++] = (xDestTile << 4) + addEightX;
        destinations[xTile + yTile * width][i2] = (yDestTile << 4) + addEightY;
    }

    public boolean checkExit(int x, int y) {
        // replaced if ((y == 1118 || y == 1117) && x >= 288 && x <= 335) return true;
        if (bottomOf(y, 69) && inXRangeOf(x, 18, 20)) return true;
        // replaced else if ((y == 785 || y == 786) && x >= 896 && x <= 927) return true;
        else if (topOf(y, 49) && inXRangeOf(x, 56, 57)) return true;
        // replaced else if ((y == 722 || y == 723) && x >= 528 && x <= 559) return true;
        else if (topOf(y, 45) && inXRangeOf(x, 33, 34)) return true;
        else if (topOf(y, 0) && inXRangeOf(x, 8, 9)) return true;
        else if (topOf(y, 38) && inXRangeOf(x, 52, 53)) return true;
        else if (topOf(y, 38) && inXRangeOf(x, 63, 64)) return true;
        else if (topOf(y , 22) && inXRangeOf(x, 5, 5)) return true;
        else if (topOf(y, 25) && inXRangeOf(x, 59, 60)) return true;
        if ((x == 937 || x == 936) && inYRangeOf(y, 63, 64)) return true;
        return false;
    }

    protected boolean topOf(int playerY, int tileY) {
        return playerY == ((tileY << 4) + 1) || playerY == ((tileY << 4) + 2);
    }

    protected boolean bottomOf(int playerY, int tileY) {
        return playerY == ((tileY + 1) << 4) - 2 || playerY == ((tileY + 1) << 4) - 3;
    }

    protected boolean rightOf(int playerX, int tileX) {
        return playerX == ((tileX + 1) << 4) - 1 || playerX == ((tileX + 1) << 4) - 2;
    }

    protected boolean leftOf(int playerX, int tileX) {
        return playerX == (tileX << 4) || playerX == (tileX << 4) + 1;
    }

    protected boolean inXRangeOf(int playerX, int begXTile, int endXTile) {
        return playerX >= (begXTile << 4) && playerX <= ((endXTile + 1) << 4) - 1;
    }

    protected boolean inYRangeOf(int playerY, int begYTile, int endYTile) {
        return playerY >= (begYTile << 4) && playerY <= ((endYTile + 1) << 4) - 1;
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
    
    public boolean furnitureHere(int xp, int yp) {
        boolean furnitureHere = false;
        for (int i = 0; i < furniture.size(); i++) {
            if (furniture.get(i).furnitureHere(xp, yp)) furnitureHere = true;
        }
        return furnitureHere;
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
        //System.out.println("Player Y: 1119 should return false " + inYRangeOf(112, 4, 6));
        for (int i = 0; i < crazyLevelNpcs.size(); i++) {
            crazyLevelNpcs.get(i).update();
        }
    }

    public void render(Screen screen) {
        for (int i = 0; i < furniture.size(); i++) {
            furniture.get(i).render(screen);
        }
        for (int i = 0; i < crazyLevelNpcs.size(); i++) {
            crazyLevelNpcs.get(i).render(screen);
        }
    }

}