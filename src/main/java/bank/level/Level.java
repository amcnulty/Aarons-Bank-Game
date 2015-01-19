/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level;

import bank.entity.chests.Chest;
import bank.entity.mob.Npc.Npc;
import bank.entity.signs.Signs;
import bank.graphics.Screen;
import bank.level.tile.Tile;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 *
 * @author Aaron
 */
public class Level implements Serializable {
	private static final long serialVersionUID = 8412071019019811363L;
        
        private int LEVELNUM;
        
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public TileCoordinate playerSpawn;
	
	public int[][] destinations;
	
	public static Level spawnLevel = new SpawnLevel("/levels/spawn_level.png");
	public static Level crazyLevel = new CrazyLevel("/levels/crazy_level.png");
	public static Level house1SubLevel = new house1SubLevel("/levels/house1_sublevel.png");
        public static Level biggerHouseLevel = new BiggerHouseLevel("/levels/bigger_house_level.png");
        public static Level biggerHouseUpstairsLevel = new BiggerHouseUpstairsLevel("/levels/bigger_house_upstairs_level.png");
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
        
	public static Level getDestination(int levelNum) {
            switch (levelNum) {
            case 1:
                return Level.spawnLevel;
            case 2:
                return Level.crazyLevel;
            case 3:
                return Level.house1SubLevel;
            case 4:
                return Level.biggerHouseLevel;
            case 5:
                return Level.biggerHouseUpstairsLevel;
            }
            return Level.spawnLevel;
	}
	
	public boolean checkExit(int x, int y) {
		return false;
	}
	
	public int getLevelTileWidth() {
		return width;
	}
	
	public int getLevelPixelWidth() {
		return width * 16;
	}
	
	public int getLevelTileHeight() {
		return height;
	}
	
	public int getLevelPixelHeight() {
		return height * 16;
	}
        
        public int getLevelNum() {
            return 0;
        }
        
        public boolean npcHere(int x, int y) {
            return false;
        }
        
        public boolean signHere(int x, int y) {
            return false;
        }
        
        public boolean chestHere(int xp, int yp) {
            return false;
        }
        
        public Npc getNpc(int x, int y) {
            return null;
        }
        
        public Chest getChest(int xp, int yp) {
            return null;
        }
        
        public Signs getSign(int xp, int yp) {
            return null;
        }
        
        public boolean[] getChestsOnLevel() {
            return null;
        }
        
        public void setChests(boolean[] chestStatus) {
        }
        
	public void update() {
	}
	
	public String[] readMessage(int x, int y) throws FileNotFoundException {
		return new String[0];
	}
        
        public void render(Screen screen) {
        }
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height +	16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);		
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		// Spawn Location Tiles
		if (tiles[x + y * width] == Tile.col_grass_tile1 || tiles[x + y * width] == 0xffFAFF7F) return Tile.grass_tile1;
		
		
		// Exit Location Tiles
		if (tiles[x + y * width] == Tile.col_cobbleStone1_exitTile) return Tile.cobbleStone1_exitTile;
		if (tiles[x + y * width] == Tile.col_woodDoor_tile) return Tile.woodDoor_tile;
		
		
		// Dialog Tiles
		if (tiles[x + y * width] == Tile.col_red_button_tile) return Tile.red_button_tile;
		if (tiles[x + y * width] == Tile.col_signNW_tile) return Tile.signNW_tile;
		if (tiles[x + y * width] == Tile.col_signSW_tile) return Tile.signSW_tile;
		if (tiles[x + y * width] == Tile.col_signNE_tile) return Tile.signNE_tile;
		if (tiles[x + y * width] == Tile.col_signSE_tile) return Tile.signSE_tile;
		if (tiles[x + y * width] == Tile.col_mudSignI_tile) return Tile.mudSignI_tile;
		if (tiles[x + y * width] == Tile.col_mudSignII_tile) return Tile.mudSignII_tile;
		if (tiles[x + y * width] == Tile.col_mudSignIII_tile) return Tile.mudSignIII_tile;
		if (tiles[x + y * width] == Tile.col_mudSignIV_tile) return Tile.mudSignIV_tile;
		
		
		// All Other Tiles
		if (tiles[x + y * width] == Tile.col_grass_tile2) return Tile.grass_tile2;
		if (tiles[x + y * width] == Tile.col_grass_tile_3) return Tile.grass_tile3;
		if (tiles[x + y * width] == Tile.col_dirt_tile1) return Tile.dirt_tile1;
		if (tiles[x + y * width] == Tile.col_dirt_tile2) return Tile.dirt_tile2;
		if (tiles[x + y * width] == Tile.col_dirt_tile3) return Tile.dirt_tile3;
		if (tiles[x + y * width] == Tile.col_flower_tile2) return Tile.flowerTile1;
		if (tiles[x + y * width] == Tile.col_metal_groud_tile1) return Tile.metal_ground_tile1;
		if (tiles[x + y * width] == Tile.col_metal_groud_tile2) return Tile.metal_ground_tile2;
		if (tiles[x + y * width] == Tile.col_metal_groud_tile3) return Tile.metal_ground_tile3;
		if (tiles[x + y * width] == Tile.col_metal_groud_tile4) return Tile.metal_ground_tile4;
		if (tiles[x + y * width] == Tile.col_rock_on_grass_tile) return Tile.rock_on_grass_tile;
		if (tiles[x + y * width] == Tile.col_large_brick1_tile) return Tile.large_brick1_tile;
		if (tiles[x + y * width] == Tile.col_large_brick2_tile) return Tile.large_brick2_tile;
		if (tiles[x + y * width] == Tile.col_large_brick3_tile) return Tile.large_brick3_tile;
		if (tiles[x + y * width] == Tile.col_large_brick4_tile) return Tile.large_brick4_tile;
		if (tiles[x + y * width] == Tile.col_large_brick5_tile) return Tile.large_brick5_tile;
		if (tiles[x + y * width] == Tile.col_small_brick_tile) return Tile.small_brick_tile;
		if (tiles[x + y * width] == Tile.col_cobbleStone1_tile) return Tile.cobbleStone1_tile;
		if (tiles[x + y * width] == Tile.col_cobbleStone2_tile) return Tile.cobbleStone2_tile;
		if (tiles[x + y * width] == Tile.col_cobbleStone3_tile) return Tile.cobbleStone3_tile;
		if (tiles[x + y * width] == Tile.col_hedge1_tile) return Tile.hedge1_tile;
		if (tiles[x + y * width] == Tile.col_hedge2_tile) return Tile.hedge2_tile;
		if (tiles[x + y * width] == Tile.col_hedge3_tile) return Tile.hedge3_tile;
		if (tiles[x + y * width] == Tile.col_ice1_tile) return Tile.ice1_tile;
		if (tiles[x + y * width] == Tile.col_ice2_tile) return Tile.ice2_tile;
		if (tiles[x + y * width] == Tile.col_water1_tile) return Tile.water1_tile;
		if (tiles[x + y * width] == Tile.col_water2_tile) return Tile.water2_tile;
		if (tiles[x + y * width] == Tile.col_water3_tile) return Tile.water3_tile;
		if (tiles[x + y * width] == Tile.col_water4_tile) return Tile.water4_tile;
		if (tiles[x + y * width] == Tile.col_lava1_tile) return Tile.lava1_tile;
		if (tiles[x + y * width] == Tile.col_lava2_tile) return Tile.lava2_tile;
		if (tiles[x + y * width] == Tile.col_lava3_tile) return Tile.lava3_tile;
		if (tiles[x + y * width] == Tile.col_lava4_tile) return Tile.lava4_tile;
		if (tiles[x + y * width] == Tile.col_lava5_tile) return Tile.lava5_tile;
		if (tiles[x + y * width] == Tile.col_lava6_tile) return Tile.lava6_tile;
		if (tiles[x + y * width] == Tile.col_woodFloor1_tile) return Tile.woodFloor1_tile;
		if (tiles[x + y * width] == Tile.col_woodFloor2_tile) return Tile.woodFloor2_tile;
		if (tiles[x + y * width] == Tile.col_woodFloor3_tile) return Tile.woodFloor3_tile;
		if (tiles[x + y * width] == Tile.col_woodWall_tile) return Tile.woodWall_tile;
		if (tiles[x + y * width] == Tile.col_woodWallLeft_tile) return Tile.woodWallLeft_tile;
		if (tiles[x + y * width] == Tile.col_woodWallRight_tile) return Tile.woodWallRight_tile;
		if (tiles[x + y * width] == Tile.col_window_tile) return Tile.window_tile;
		if (tiles[x + y * width] == Tile.col_woodLog1_tile) return Tile.woodLog1_tile;
		if (tiles[x + y * width] == Tile.col_woodLog2_tile) return Tile.woodLog2_tile;
		
		return Tile.voidTile;
	}

}