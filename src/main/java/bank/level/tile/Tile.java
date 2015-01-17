/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level.tile;

import bank.graphics.Screen;
import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	// Void Tile
	public static Tile voidTile = new Tile(Sprite.voidSprite);
	
	
	// Button Tiles
	public static Tile red_button_tile = new DialogTile(Sprite.red_button);
	public static int col_red_button_tile = 0xff6C1818;
        
	
	// Sign Tiles
	public static Tile signNW_tile = new DialogTile(Sprite.signNW);
	public static int col_signNW_tile = 0xffEAAC00;
	
	public static Tile signSW_tile = new DialogTile(Sprite.signSW);
	public static int col_signSW_tile = 0xffEAAC01;
	
	public static Tile signNE_tile = new DialogTile(Sprite.signNE);
	public static int col_signNE_tile = 0xffEAAC02;
	
	public static Tile signSE_tile = new DialogTile(Sprite.signSE);
	public static int col_signSE_tile = 0xffEAAC03;
	
	public static Tile mudSignI_tile = new DialogTile(Sprite.mudSignI);
	public static int col_mudSignI_tile = 0xff532D29;
	
	public static Tile mudSignII_tile = new DialogTile(Sprite.mudSignII);
	public static int col_mudSignII_tile = 0xff532D28;
	
	public static Tile mudSignIII_tile = new DialogTile(Sprite.mudSignIII);
	public static int col_mudSignIII_tile = 0xff532D27;
	
	public static Tile mudSignIV_tile = new DialogTile(Sprite.mudSignIV);
	public static int col_mudSignIV_tile = 0xff532D26;
	
	// Grass Tiles
	public static Tile grass_tile1 = new Tile(Sprite.grass1);
	public static int col_grass_tile1 = 0xff00ff00;
	
	public static Tile grass_tile2 = new Tile(Sprite.grass2);
	public static int col_grass_tile2 = 0xff00aa00;
	
	public static Tile grass_tile3 = new Tile(Sprite.grass3);
	public static int col_grass_tile_3 = 0xff00dd00;
	
	
	// Dirt Tiles
	public static Tile dirt_tile1 = new Tile(Sprite.dirt1);
	public static int col_dirt_tile1 = 0xff603A34;
	
	public static Tile dirt_tile2 = new Tile(Sprite.dirt2);
	public static int col_dirt_tile2 = 0xff334F40;
			
	public static Tile dirt_tile3 = new Tile(Sprite.dirt3);
	public static int col_dirt_tile3 = 0xff554E32;
	
	
	// Flower Tiles
	public static Tile flowerTile1 = new Tile(Sprite.flower);
	public static int col_flower_tile2 = 0xffF1F905;
	
	
	// Rock/Brick/Stone Tiles
	public static Tile rock_on_grass_tile = new SolidTile(Sprite.rock_on_grass);
	public static int col_rock_on_grass_tile = 0xff807854;
	
	public static Tile large_brick1_tile = new SolidTile(Sprite.large_brick1);
	public static int col_large_brick1_tile = 0xff9D5F39;
	
	public static Tile large_brick2_tile = new SolidTile(Sprite.large_brick2);
	public static int col_large_brick2_tile = 0xffAAA99A;
	
	public static Tile large_brick3_tile = new SolidTile(Sprite.large_brick3);
	public static int col_large_brick3_tile = 0xffBF9042;
	
	public static Tile large_brick4_tile = new SolidTile(Sprite.large_brick4);
	public static int col_large_brick4_tile = 0xffB3B7B9;
	
	public static Tile large_brick5_tile = new SolidTile(Sprite.large_brick5);
	public static int col_large_brick5_tile = 0xff514838;
	
	public static Tile small_brick_tile = new SolidTile(Sprite.small_brick);
	public static int col_small_brick_tile = 0xff545E64;
	
	public static Tile cobbleStone1_tile = new Tile(Sprite.cobbleStone1);
	public static int col_cobbleStone1_tile = 0xffBAB587;
	public static Tile cobbleStone1_exitTile = new ExitTile(Sprite.cobbleStone1);
	public static int col_cobbleStone1_exitTile = 0xffBAB586;

	
	public static Tile cobbleStone2_tile = new Tile(Sprite.cobbleStone2);
	public static int col_cobbleStone2_tile = 0xff9B9B91;
	
	public static Tile cobbleStone3_tile = new Tile(Sprite.cobbleStone3);
	public static int col_cobbleStone3_tile = 0xff8F8E84;
	
	
	// Hedge Tiles
	
	public static Tile hedge1_tile = new SolidTile(Sprite.hedge1);
	public static int col_hedge1_tile = 0xff4E8011;
	
	public static Tile hedge2_tile = new SolidTile(Sprite.hedge2);
	public static int col_hedge2_tile = 0xff224817;
	
	public static Tile hedge3_tile = new SolidTile(Sprite.hedge3);
	public static int col_hedge3_tile = 0xff2B551A;
	
	
	// Metal Ground Tiles
	public static Tile metal_ground_tile1 = new Tile(Sprite.metal_ground1);
	public static int col_metal_groud_tile1 = 0xff919184;
	
	public static Tile metal_ground_tile2 = new Tile(Sprite.metal_ground2);
	public static int col_metal_groud_tile2 = 0xff8C8C80;
	
	public static Tile metal_ground_tile3 = new Tile(Sprite.metal_ground3);
	public static int col_metal_groud_tile3 = 0xff919FA0;
	
	public static Tile metal_ground_tile4 = new Tile(Sprite.metal_ground4);
	public static int col_metal_groud_tile4 = 0xff889898;
	
	// Ice Tiles
	public static Tile ice1_tile = new SolidTile(Sprite.ice1);
	public static int col_ice1_tile = 0xff68ACD0;
	
	public static Tile ice2_tile = new Tile(Sprite.ice2);
	public static int col_ice2_tile = 0xff9BDFFC;
	
	// Water Tiles
	public static Tile water1_tile = new Tile(Sprite.water1);
	public static int col_water1_tile = 0xff8CBBEC;
	
	public static Tile water2_tile = new Tile(Sprite.water2);
	public static int col_water2_tile = 0xff527CE2;
	
	public static Tile water3_tile = new Tile(Sprite.water3);
	public static int col_water3_tile = 0xff1C5D83;
	
	public static Tile water4_tile = new Tile(Sprite.water4);
	public static int col_water4_tile = 0xff427A9E;
	
	// Lava Tiles
	public static Tile lava1_tile = new Tile(Sprite.lava1);
	public static int col_lava1_tile = 0xffD25022;
	
	public static Tile lava2_tile = new Tile(Sprite.lava2);
	public static int col_lava2_tile = 0xffDD5A27;
	
	public static Tile lava3_tile = new Tile(Sprite.lava3);
	public static int col_lava3_tile = 0xffF44E00;
	
	public static Tile lava4_tile = new Tile(Sprite.lava4);
	public static int col_lava4_tile = 0xffF24D00;
	
	public static Tile lava5_tile = new Tile(Sprite.lava5);
	public static int col_lava5_tile = 0xffC53700;
	
	public static Tile lava6_tile = new Tile(Sprite.lava6);
	public static int col_lava6_tile = 0xffA92900;
	
	// Window Tiles
	public static Tile window_tile = new Tile(Sprite.window);
	public static int col_window_tile = 0xff1D4ED2;
	
	// Wood Tiles
	public static Tile woodFloor1_tile = new Tile(Sprite.woodFloor1);
	public static int col_woodFloor1_tile = 0xff6A4A13;
			
	public static Tile woodFloor2_tile = new Tile(Sprite.woodFloor2);
	public static int col_woodFloor2_tile = 0xffB1923F;
			
	public static Tile woodFloor3_tile = new Tile(Sprite.woodFloor3);
	public static int col_woodFloor3_tile = 0xffB29340;
	
	public static Tile woodWall_tile = new SolidTile(Sprite.woodWall);
	public static int col_woodWall_tile = 0xff4E330D;
			
	public static Tile woodWallLeft_tile = new SolidTile(Sprite.woodWallLeft);
	public static int col_woodWallLeft_tile = 0xff48300D;
			
	public static Tile woodWallRight_tile = new SolidTile(Sprite.woodWallRight);
	public static int col_woodWallRight_tile = 0xff4D330D;
	
	public static Tile woodLog1_tile = new SolidTile(Sprite.woodLog1);
	public static int col_woodLog1_tile = 0xffDCB447;
	
	public static Tile woodLog2_tile = new SolidTile(Sprite.woodLog2);
	public static int col_woodLog2_tile = 0xffDCB448;
	
	public static Tile woodDoor_tile = new ExitTile(Sprite.woodDoor);
	public static int col_woodDoor_tile = 0xff664319;
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean dialog() {
		return false;
	}
	
	public boolean exited() {
		return false;
	}

}
