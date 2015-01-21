/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
public class MazeLevel extends Level {
    
    private final int LEVELNUM = 6;
    
    public MazeLevel(String path) {
        super(path);
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
        setDestinations(33, 69, 2, 9, false, 1, false);
        setDestinations(34, 69, 2, 9, false, 1, false);
        setDestinations(35, 69, 2, 9, false, 1, false);
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
        if (bottomOf(y, 69) && inXRangeOf(x, 33, 35)) return true;
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
    
}
