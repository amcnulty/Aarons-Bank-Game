/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int width, height;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet2.png", 256);
	public static SpriteSheet toolBar = new SpriteSheet("/menus/toolBar.png", 130, 110);
	public static SpriteSheet invMenu1 = new SpriteSheet("/menus/inventory.png", 150, 190);
	public static SpriteSheet invMenu2 = new SpriteSheet("/menus/inventory2.png", 150, 190);
        public static SpriteSheet characters = new SpriteSheet("/textures/sheets/character_sheet.png", 288, 256);
        public static SpriteSheet moreCharacters = new SpriteSheet("/textures/sheets/character_sheet2.png", 384, 256);
        public static SpriteSheet items = new SpriteSheet("/textures/sheets/items_sheet.png", 160, 224);
        public static SpriteSheet furniture = new SpriteSheet("/textures/sheets/furniture_sheet.png", 96, 96);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = width * height;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		load();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
