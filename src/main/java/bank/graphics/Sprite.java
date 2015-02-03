/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Aaron
 */
public class Sprite {
	
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	// Player sprites
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	public static Sprite player_forward1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	
	public static Sprite player_back1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	public static Sprite player_left1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite player_left2 = new Sprite(32, 3, 7, SpriteSheet.tiles);
	
	public static Sprite player_right1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	
        
        // Female NPC Sprites
        
        public static Sprite femaleNPC_back = new Sprite(32, 1, 0, SpriteSheet.characters);
        public static Sprite femaleNPC_back1 = new Sprite(32, 0, 0, SpriteSheet.characters);
        public static Sprite femaleNPC_back2 = new Sprite(32, 2, 0, SpriteSheet.characters);
        
        public static Sprite femaleNPC_left = new Sprite(32, 1, 1, SpriteSheet.characters);
	public static Sprite femaleNPC_left1 = new Sprite(32, 0, 1, SpriteSheet.characters);
        public static Sprite femaleNPC_left2 = new Sprite(32, 2, 1, SpriteSheet.characters);
        
        public static Sprite femaleNPC_right = new Sprite(32, 1, 2, SpriteSheet.characters);
        public static Sprite femaleNPC_right1 = new Sprite(32, 0, 2, SpriteSheet.characters);
        public static Sprite femaleNPC_right2 = new Sprite(32, 2, 2, SpriteSheet.characters);
        
        public static Sprite femaleNPC_forward = new Sprite(32, 1, 3, SpriteSheet.characters);
        public static Sprite femaleNPC_forward1 = new Sprite(32, 0, 3, SpriteSheet.characters);
        public static Sprite femaleNPC_forward2 = new Sprite(32, 2, 3, SpriteSheet.characters);
        
        // Female2 NPC Sprites
        
        public static Sprite female2NPC_back = new Sprite(32, 4, 0, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_back1 = new Sprite(32, 3, 0, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_back2 = new Sprite(32, 5, 0, SpriteSheet.moreCharacters);
        
        public static Sprite female2NPC_left = new Sprite(32, 4, 1, SpriteSheet.moreCharacters);
	public static Sprite female2NPC_left1 = new Sprite(32, 3, 1, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_left2 = new Sprite(32, 5, 1, SpriteSheet.moreCharacters);
        
        public static Sprite female2NPC_right = new Sprite(32, 4, 2, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_right1 = new Sprite(32, 3, 2, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_right2 = new Sprite(32, 5, 2, SpriteSheet.moreCharacters);
        
        public static Sprite female2NPC_forward = new Sprite(32, 4, 3, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_forward1 = new Sprite(32, 3, 3, SpriteSheet.moreCharacters);
        public static Sprite female2NPC_forward2 = new Sprite(32, 5, 3, SpriteSheet.moreCharacters);
        
        
        // Female3 NPC Sprites
        
        public static Sprite female3NPC_back = new Sprite(32, 4, 4, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_back1 = new Sprite(32, 3, 4, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_back2 = new Sprite(32, 5, 4, SpriteSheet.moreCharacters);
        
        public static Sprite female3NPC_left = new Sprite(32, 4, 5, SpriteSheet.moreCharacters);
	public static Sprite female3NPC_left1 = new Sprite(32, 3, 5, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_left2 = new Sprite(32, 5, 5, SpriteSheet.moreCharacters);
        
        public static Sprite female3NPC_right = new Sprite(32, 4, 6, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_right1 = new Sprite(32, 3, 6, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_right2 = new Sprite(32, 5, 6, SpriteSheet.moreCharacters);
        
        public static Sprite female3NPC_forward = new Sprite(32, 4, 7, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_forward1 = new Sprite(32, 3, 7, SpriteSheet.moreCharacters);
        public static Sprite female3NPC_forward2 = new Sprite(32, 5, 7, SpriteSheet.moreCharacters);
        
        
        // Male2 NPC Sprites
        
        public static Sprite male2NPC_back = new Sprite(32, 1, 0, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_back1 = new Sprite(32, 0, 0, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_back2 = new Sprite(32, 2, 0, SpriteSheet.moreCharacters);
        
        public static Sprite male2NPC_left = new Sprite(32, 1, 1, SpriteSheet.moreCharacters);
	public static Sprite male2NPC_left1 = new Sprite(32, 0, 1, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_left2 = new Sprite(32, 2, 1, SpriteSheet.moreCharacters);
        
        public static Sprite male2NPC_right = new Sprite(32, 1, 2, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_right1 = new Sprite(32, 0, 2, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_right2 = new Sprite(32, 2, 2, SpriteSheet.moreCharacters);
        
        public static Sprite male2NPC_forward = new Sprite(32, 1, 3, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_forward1 = new Sprite(32, 0, 3, SpriteSheet.moreCharacters);
        public static Sprite male2NPC_forward2 = new Sprite(32, 2, 3, SpriteSheet.moreCharacters);
        
        
        // Male3 NPC Sprites
        
        public static Sprite male3NPC_back = new Sprite(32, 1, 4, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_back1 = new Sprite(32, 0, 4, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_back2 = new Sprite(32, 2, 4, SpriteSheet.moreCharacters);
        
        public static Sprite male3NPC_left = new Sprite(32, 1, 5, SpriteSheet.moreCharacters);
	public static Sprite male3NPC_left1 = new Sprite(32, 0, 5, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_left2 = new Sprite(32, 2, 5, SpriteSheet.moreCharacters);
        
        public static Sprite male3NPC_right = new Sprite(32, 1, 6, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_right1 = new Sprite(32, 0, 6, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_right2 = new Sprite(32, 2, 6, SpriteSheet.moreCharacters);
        
        public static Sprite male3NPC_forward = new Sprite(32, 1, 7, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_forward1 = new Sprite(32, 0, 7, SpriteSheet.moreCharacters);
        public static Sprite male3NPC_forward2 = new Sprite(32, 2, 7, SpriteSheet.moreCharacters);
        
        
        // ArmoredGuard NPC Sprites
        
        public static Sprite armoredGuardNPC_back = new Sprite(32, 7, 4, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_back1 = new Sprite(32, 6, 4, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_back2 = new Sprite(32, 8, 4, SpriteSheet.moreCharacters);
        
        public static Sprite armoredGuardNPC_left = new Sprite(32, 7, 5, SpriteSheet.moreCharacters);
	public static Sprite armoredGuardNPC_left1 = new Sprite(32, 6, 5, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_left2 = new Sprite(32, 8, 5, SpriteSheet.moreCharacters);
        
        public static Sprite armoredGuardNPC_right = new Sprite(32, 7, 6, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_right1 = new Sprite(32, 6, 6, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_right2 = new Sprite(32, 8, 6, SpriteSheet.moreCharacters);
        
        public static Sprite armoredGuardNPC_forward = new Sprite(32, 7, 7, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_forward1 = new Sprite(32, 6, 7, SpriteSheet.moreCharacters);
        public static Sprite armoredGuardNPC_forward2 = new Sprite(32, 8, 7, SpriteSheet.moreCharacters);
        
        
        // Male4 NPC Sprites
        
        public static Sprite male4NPC_back = new Sprite(32, 7, 0, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_back1 = new Sprite(32, 6, 0, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_back2 = new Sprite(32, 8, 0, SpriteSheet.moreCharacters);
        
        public static Sprite male4NPC_left = new Sprite(32, 7, 1, SpriteSheet.moreCharacters);
	public static Sprite male4NPC_left1 = new Sprite(32, 6, 1, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_left2 = new Sprite(32, 8, 1, SpriteSheet.moreCharacters);
        
        public static Sprite male4NPC_right = new Sprite(32, 7, 2, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_right1 = new Sprite(32, 6, 2, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_right2 = new Sprite(32, 8, 2, SpriteSheet.moreCharacters);
        
        public static Sprite male4NPC_forward = new Sprite(32, 7, 3, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_forward1 = new Sprite(32, 6, 3, SpriteSheet.moreCharacters);
        public static Sprite male4NPC_forward2 = new Sprite(32, 8, 3, SpriteSheet.moreCharacters);
        
        // Female4 NPC Sprites
        
        public static Sprite female4NPC_back = new Sprite(32, 10, 0, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_back1 = new Sprite(32, 9, 0, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_back2 = new Sprite(32, 11, 0, SpriteSheet.moreCharacters);
        
        public static Sprite female4NPC_left = new Sprite(32, 10, 1, SpriteSheet.moreCharacters);
	public static Sprite female4NPC_left1 = new Sprite(32, 9, 1, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_left2 = new Sprite(32, 11, 1, SpriteSheet.moreCharacters);
        
        public static Sprite female4NPC_right = new Sprite(32, 10, 2, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_right1 = new Sprite(32, 9, 2, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_right2 = new Sprite(32, 11, 2, SpriteSheet.moreCharacters);
        
        public static Sprite female4NPC_forward = new Sprite(32, 10, 3, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_forward1 = new Sprite(32, 9, 3, SpriteSheet.moreCharacters);
        public static Sprite female4NPC_forward2 = new Sprite(32, 11, 3, SpriteSheet.moreCharacters);
        
        // Male NPC Sprites
        
        public static Sprite maleNPC_back = new Sprite(32, 7, 0, SpriteSheet.characters);
        public static Sprite maleNPC_back1 = new Sprite(32, 6, 0, SpriteSheet.characters);
        public static Sprite maleNPC_back2 = new Sprite(32, 8, 0, SpriteSheet.characters);
        
        public static Sprite maleNPC_left = new Sprite(32, 7, 1, SpriteSheet.characters);
	public static Sprite maleNPC_left1 = new Sprite(32, 6, 1, SpriteSheet.characters);
        public static Sprite maleNPC_left2 = new Sprite(32, 8, 1, SpriteSheet.characters);
        
        public static Sprite maleNPC_right = new Sprite(32, 7, 2, SpriteSheet.characters);
        public static Sprite maleNPC_right1 = new Sprite(32, 6, 2, SpriteSheet.characters);
        public static Sprite maleNPC_right2 = new Sprite(32, 8, 2, SpriteSheet.characters);
        
        public static Sprite maleNPC_forward = new Sprite(32, 7, 3, SpriteSheet.characters);
        public static Sprite maleNPC_forward1 = new Sprite(32, 6, 3, SpriteSheet.characters);
        public static Sprite maleNPC_forward2 = new Sprite(32, 8, 3, SpriteSheet.characters);
        
        // Corbin's Sprites
        
        public static Sprite corbin_back = new Sprite(32, 7, 4, SpriteSheet.characters);
        public static Sprite corbin_back1 = new Sprite(32, 6, 4, SpriteSheet.characters);
        public static Sprite corbin_back2 = new Sprite(32, 8, 4, SpriteSheet.characters);
        
        public static Sprite corbin_left = new Sprite(32, 7, 5, SpriteSheet.characters);
        public static Sprite corbin_left1 = new Sprite(32, 6, 5, SpriteSheet.characters);
        public static Sprite corbin_left2 = new Sprite(32, 8, 5, SpriteSheet.characters);
        
        public static Sprite corbin_right = new Sprite(32, 7, 6, SpriteSheet.characters);
        public static Sprite corbin_right1 = new Sprite(32, 6, 6, SpriteSheet.characters);
        public static Sprite corbin_right2 = new Sprite(32, 8, 6, SpriteSheet.characters);
        
        public static Sprite corbin_forward = new Sprite(32, 7, 7, SpriteSheet.characters);
        public static Sprite corbin_forward1 = new Sprite(32, 6, 7, SpriteSheet.characters);
        public static Sprite corbin_forward2 = new Sprite(32, 8, 7, SpriteSheet.characters);
        
        // Player 2 Sprites
        
	public static Sprite player2_forward = new Sprite(32, 4, 2, SpriteSheet.tiles);
	public static Sprite player2_back = new Sprite(32, 7, 2, SpriteSheet.tiles);
	public static Sprite player2_left = new Sprite(32, 6, 2, SpriteSheet.tiles);
	public static Sprite player2_right = new Sprite(32, 5, 2, SpriteSheet.tiles);
	
	public static Sprite player2_forward1 = new Sprite(32, 4, 3, SpriteSheet.tiles);
	public static Sprite player2_forward2 = new Sprite(32, 4, 4, SpriteSheet.tiles);
	
	public static Sprite player2_back1 = new Sprite(32, 7, 3, SpriteSheet.tiles);
	public static Sprite player2_back2 = new Sprite(32, 7, 4, SpriteSheet.tiles);
	
	public static Sprite player2_left1 = new Sprite(32, 6, 3, SpriteSheet.tiles);
	public static Sprite player2_left2 = new Sprite(32, 6, 4, SpriteSheet.tiles);
	
	public static Sprite player2_right1 = new Sprite(32, 5, 3, SpriteSheet.tiles);
	public static Sprite player2_right2 = new Sprite(32, 5, 4, SpriteSheet.tiles);
        
        // Player 3 Sprites
        
        public static Sprite player3_back = new Sprite(32, 4, 4, SpriteSheet.characters);
        public static Sprite player3_back1 = new Sprite(32, 3, 4, SpriteSheet.characters);
        public static Sprite player3_back2 = new Sprite(32, 5, 4, SpriteSheet.characters);
        
        public static Sprite player3_left = new Sprite(32, 4, 5, SpriteSheet.characters);
        public static Sprite player3_left1 = new Sprite(32, 3, 5, SpriteSheet.characters);
        public static Sprite player3_left2 = new Sprite(32, 5, 5, SpriteSheet.characters);
        
        public static Sprite player3_right = new Sprite(32, 4, 6, SpriteSheet.characters);
        public static Sprite player3_right1 = new Sprite(32, 3, 6, SpriteSheet.characters);
        public static Sprite player3_right2 = new Sprite(32, 5, 6, SpriteSheet.characters);
        
        public static Sprite player3_forward = new Sprite(32, 4, 7, SpriteSheet.characters);
        public static Sprite player3_forward1 = new Sprite(32, 3, 7, SpriteSheet.characters);
        public static Sprite player3_forward2 = new Sprite(32, 5, 7, SpriteSheet.characters);
        
        // Player 4 Sprites
        
        public static Sprite player4_back = new Sprite(32, 4, 0, SpriteSheet.characters);
        public static Sprite player4_back1 = new Sprite(32, 3, 0, SpriteSheet.characters);
        public static Sprite player4_back2 = new Sprite(32, 5, 0, SpriteSheet.characters);
        
        public static Sprite player4_left = new Sprite(32, 4, 1, SpriteSheet.characters);
        public static Sprite player4_left1 = new Sprite(32, 3, 1, SpriteSheet.characters);
        public static Sprite player4_left2 = new Sprite(32, 5, 1, SpriteSheet.characters);
        
        public static Sprite player4_right = new Sprite(32, 4, 2, SpriteSheet.characters);
        public static Sprite player4_right1 = new Sprite(32, 3, 2, SpriteSheet.characters);
        public static Sprite player4_right2 = new Sprite(32, 5, 2, SpriteSheet.characters);
        
        public static Sprite player4_forward = new Sprite(32, 4, 3, SpriteSheet.characters);
        public static Sprite player4_forward1 = new Sprite(32, 3, 3, SpriteSheet.characters);
        public static Sprite player4_forward2 = new Sprite(32, 5, 3, SpriteSheet.characters);
        
        // Player 5 Sprites
        
        public static Sprite player5_back = new Sprite(32, 1, 4, SpriteSheet.characters);
        public static Sprite player5_back1 = new Sprite(32, 0, 4, SpriteSheet.characters);
        public static Sprite player5_back2 = new Sprite(32, 2, 4, SpriteSheet.characters);
        
        public static Sprite player5_left = new Sprite(32, 1, 5, SpriteSheet.characters);
        public static Sprite player5_left1 = new Sprite(32, 0, 5, SpriteSheet.characters);
        public static Sprite player5_left2 = new Sprite(32, 2, 5, SpriteSheet.characters);
        
        public static Sprite player5_right = new Sprite(32, 1, 6, SpriteSheet.characters);
        public static Sprite player5_right1 = new Sprite(32, 0, 6, SpriteSheet.characters);
        public static Sprite player5_right2 = new Sprite(32, 2, 6, SpriteSheet.characters);
        
        public static Sprite player5_forward = new Sprite(32, 1, 7, SpriteSheet.characters);
        public static Sprite player5_forward1 = new Sprite(32, 0, 7, SpriteSheet.characters);
        public static Sprite player5_forward2 = new Sprite(32, 2, 7, SpriteSheet.characters);
        
	
	// Void sprite
	public static Sprite voidSprite = new Sprite(16, 0xffa01f);
        
        // Armor name box sprite
        public static Sprite nameBoxSprite = new Sprite(100, 11, 0xffFF384B);
        
        // Furniture sprites
        public static Sprite big_couch = new Sprite(32, 0, 0, SpriteSheet.furniture);
        public static Sprite small_couch = new Sprite(32, 1, 0, SpriteSheet.furniture);
        public static Sprite oven = new Sprite(32, 2, 0, SpriteSheet.furniture);
        public static Sprite fridge_one = new Sprite(32, 0, 1, SpriteSheet.furniture);
        public static Sprite dresser = new Sprite(32, 1, 1, SpriteSheet.furniture);
        public static Sprite air_compressor = new Sprite(32, 2, 1, SpriteSheet.furniture);
        public static Sprite office_chair = new Sprite(32, 0, 2, SpriteSheet.furniture);
        public static Sprite barrel = new Sprite(32, 1, 2, SpriteSheet.furniture);
        public static Sprite fridge_two = new Sprite(32, 2, 2, SpriteSheet.furniture);
	
        // Treasure Chest Sprites
        public static Sprite chest1_open = new Sprite(32, 5, 5, SpriteSheet.tiles);
        public static Sprite chest1_closed = new Sprite(32, 6, 5, SpriteSheet.tiles);
        public static Sprite chest2_open = new Sprite(32, 5, 6, SpriteSheet.tiles);
        public static Sprite chest2_closed = new Sprite(32, 6, 6, SpriteSheet.tiles);
        public static Sprite chest3_open = new Sprite(32, 5, 7, SpriteSheet.tiles);
        public static Sprite chest3_closed = new Sprite(32, 6, 7, SpriteSheet.tiles);
        
        // Items sprites
        public static Sprite drinking_water = new Sprite(16, 8, 8, SpriteSheet.items);
        public static Sprite banana = new Sprite(16, 1, 8, SpriteSheet.items);
        public static Sprite speed_potion = new Sprite(16, 1, 9, SpriteSheet.items);
        public static Sprite defence_potion = new Sprite(16, 6, 8, SpriteSheet.items);
        public static Sprite attack_potion = new Sprite(16, 9, 8, SpriteSheet.items);
        public static Sprite special_candy = new Sprite(16, 4, 8, SpriteSheet.items);
        public static Sprite cheese = new Sprite(16, 2, 7, SpriteSheet.items);
        public static Sprite training_book = new Sprite(16, 3, 11, SpriteSheet.items);
        public static Sprite drumstick = new Sprite(16, 6, 7, SpriteSheet.items);
        public static Sprite cookie = new Sprite(16, 3, 8, SpriteSheet.items);
        public static Sprite fish = new Sprite(16, 5, 7, SpriteSheet.items);
        public static Sprite cherries = new Sprite(16, 2, 8, SpriteSheet.items);
        public static Sprite egg = new Sprite(16, 4, 7, SpriteSheet.items);
        public static Sprite lv2_training_book = new Sprite(16, 4, 11, SpriteSheet.items);
        
        // Weapon sprites
        public static Sprite deluxe_sword = new Sprite(16, 0, 3, SpriteSheet.items);
        public static Sprite heavy_sword = new Sprite(16, 2, 4, SpriteSheet.items);
        public static Sprite speed_sword = new Sprite(16, 1, 4, SpriteSheet.items);
        
        public static Sprite flame_sword = new Sprite(16, 1, 3, SpriteSheet.items);
        public static Sprite ice_sword = new Sprite(16, 2, 3, SpriteSheet.items);
        public static Sprite spark_sword = new Sprite(16, 5, 3, SpriteSheet.items);
        
        public static Sprite dragon_sword = new Sprite(16, 3, 4, SpriteSheet.items);
        public static Sprite crystal_sword = new Sprite(16, 4, 4, SpriteSheet.items);
        public static Sprite blood_sword = new Sprite(16, 9, 3, SpriteSheet.items);
        
        public static Sprite battle_axe = new Sprite(16, 5, 5, SpriteSheet.items);
        
        public static Sprite blood_dagger = new Sprite(16, 9, 4, SpriteSheet.items);
        public static Sprite black_dagger = new Sprite(16, 7, 4, SpriteSheet.items);
        public static Sprite deluxe_dagger = new Sprite(16, 6, 4, SpriteSheet.items);
        public static Sprite poison_dagger = new Sprite(16, 9, 12, SpriteSheet.items);
        
        // Armor sprites
        public static Sprite steel_toe_boots = new Sprite(16, 3 , 2, SpriteSheet.items);
        public static Sprite leather_boots = new Sprite(16, 1, 2, SpriteSheet.items);
        public static Sprite leather_helmet = new Sprite(16, 1, 1, SpriteSheet.items);
        public static Sprite leather_tunic = new Sprite(16, 1, 0, SpriteSheet.items);
        public static Sprite hard_leather_boots = new Sprite(16, 2, 2, SpriteSheet.items);
        public static Sprite hard_leather_helmet = new Sprite(16, 3, 1, SpriteSheet.items);
        public static Sprite hard_leather_tunic = new Sprite(16, 3, 0, SpriteSheet.items);
        public static Sprite iron_boots = new Sprite(16, 4, 2, SpriteSheet.items);
        public static Sprite iron_helmet = new Sprite(16, 4, 1, SpriteSheet.items);
        public static Sprite iron_chestplate = new Sprite(16, 4, 0, SpriteSheet.items);
        public static Sprite gold_boots = new Sprite(16, 6, 2, SpriteSheet.items);
        public static Sprite gold_helmet = new Sprite(16, 6, 1, SpriteSheet.items);
        public static Sprite gold_chestplate = new Sprite(16, 6, 0, SpriteSheet.items);
        public static Sprite corbins_sheild = new Sprite(16, 8, 1, SpriteSheet.items);
        
        
	// Button sprites
	public static Sprite red_button = new Sprite(16, 12, 3, SpriteSheet.tiles);
	
	// Dialog box sprites
	public static Sprite dialog_box = new Sprite(265, 75, 0x7F7FFF);
	public static Sprite dialog_box2 = new Sprite(261, 71, 0x8484FF);
	public static Sprite dialog_box3 = new Sprite(257, 67, 0x8989FF);
	public static Sprite dialog_box4 = new Sprite(253, 63, 0x8E8EFF);
	public static Sprite dialog_box5 = new Sprite(249, 59, 0x9393FF);
	
	// Grass sprites
	public static Sprite grass1 = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass2 = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite grass3 = new Sprite(16, 0, 2, SpriteSheet.tiles);
	
	// Dirt sprites
	public static Sprite dirt1 = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite dirt2 = new Sprite(16, 0, 4, SpriteSheet.tiles);
	public static Sprite dirt3 = new Sprite(16, 0, 5, SpriteSheet.tiles);
        public static Sprite dirt3_withRocks = new Sprite(16, 1, 5, SpriteSheet.tiles);
	
	// Flower sprites
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	
	// Rock/Brick/Stone sprites
	public static Sprite rock_on_grass = new Sprite(16, 2, 0, SpriteSheet.tiles);
        public static Sprite rock_on_dirt1 = new Sprite(16, 1, 3, SpriteSheet.tiles);
	public static Sprite large_brick1 = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite large_brick2 = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite large_brick3 = new Sprite(16, 2, 3, SpriteSheet.tiles);
	public static Sprite cobbleStone1 = new Sprite(16, 2, 4, SpriteSheet.tiles);
	public static Sprite large_brick4 = new Sprite(16, 2, 5, SpriteSheet.tiles);
	public static Sprite large_brick5 = new Sprite(16, 2, 6, SpriteSheet.tiles);
	public static Sprite small_brick = new Sprite(16, 2, 7, SpriteSheet.tiles);
	public static Sprite cobbleStone2 = new Sprite(16, 2, 8, SpriteSheet.tiles);
	public static Sprite cobbleStone3 = new Sprite(16, 2, 9, SpriteSheet.tiles);
        public static Sprite darkWall = new Sprite(16, 4, 5, SpriteSheet.tiles);
	
	// Hedge sprites
	public static Sprite hedge1 = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite hedge2 = new Sprite(16, 9, 14, SpriteSheet.tiles);
	public static Sprite hedge3 = new Sprite(16, 9, 15, SpriteSheet.tiles);
	
	// Metal ground sprites
	public static Sprite metal_ground1 = new Sprite(16, 5, 6, SpriteSheet.tiles);
	public static Sprite metal_ground2 = new Sprite(16, 5, 7, SpriteSheet.tiles);
	public static Sprite metal_ground3 = new Sprite(16, 5, 8, SpriteSheet.tiles);
	public static Sprite metal_ground4 = new Sprite(16, 5, 9, SpriteSheet.tiles);
	
	// Ice sprites
	public static Sprite ice1 = new Sprite(16, 7, 6, SpriteSheet.tiles);
	public static Sprite ice2 = new Sprite(16, 7, 7, SpriteSheet.tiles);
	
	// Water sprites
	public static Sprite water1 = new Sprite(16, 8, 0, SpriteSheet.tiles);
	public static Sprite water2 = new Sprite(16, 7, 3, SpriteSheet.tiles);
	public static Sprite water3 = new Sprite(16, 9, 12, SpriteSheet.tiles);
	public static Sprite water4 = new Sprite(16, 9, 13, SpriteSheet.tiles);
	
	// Lava sprites
	public static Sprite lava1 = new Sprite(16, 7, 1, SpriteSheet.tiles);
	public static Sprite lava2 = new Sprite(16, 7, 2, SpriteSheet.tiles);
	public static Sprite lava3 = new Sprite(16, 7, 8, SpriteSheet.tiles);
	public static Sprite lava4 = new Sprite(16, 7, 9, SpriteSheet.tiles);
	public static Sprite lava5 = new Sprite(16, 9, 10, SpriteSheet.tiles);
	public static Sprite lava6 = new Sprite(16, 9, 11, SpriteSheet.tiles);
	
	// window sprites
	public static Sprite window	 = new Sprite(16, 6, 4, SpriteSheet.tiles);
	
	// Wood sprites
	public static Sprite woodFloor1 = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite woodFloor2 = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite woodFloor3 = new Sprite(16, 4, 1, SpriteSheet.tiles);
	
	public static Sprite woodWall = new Sprite(16, 6, 3, SpriteSheet.tiles);
	public static Sprite woodWallLeft = new Sprite(16, 6, 1, SpriteSheet.tiles);
	public static Sprite woodWallRight = new Sprite(16, 6, 2, SpriteSheet.tiles);
	
	public static Sprite woodLog1 = new Sprite(16, 4 , 2, SpriteSheet.tiles);
	public static Sprite woodLog2 = new Sprite(16, 4 , 3, SpriteSheet.tiles);
	
	public static Sprite woodDoor = new Sprite(16, 13, 0, SpriteSheet.tiles);
	
	
	// Sign sprites
	public static Sprite signNW = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite signSW = new Sprite(16, 0, 7, SpriteSheet.tiles);
	public static Sprite signNE = new Sprite(16, 1, 6, SpriteSheet.tiles);
	public static Sprite signSE = new Sprite(16, 1, 7, SpriteSheet.tiles);
	
	public static Sprite mudSignI = new Sprite(16, 1, 8, SpriteSheet.tiles);
	public static Sprite mudSignII = new Sprite(16, 0, 8, SpriteSheet.tiles);
	public static Sprite mudSignIII = new Sprite(16, 0, 9, SpriteSheet.tiles);
	public static Sprite mudSignIV = new Sprite(16, 1, 9, SpriteSheet.tiles);
        
        public static Sprite signSprite = new Sprite(32, 7, 5, SpriteSheet.tiles);
	
	// ToolBar sprites
	public static Sprite toolBar1 = new Sprite(true, 130, 55, SpriteSheet.toolBar);
	public static Sprite toolBar2 = new Sprite(false, 130, 55, SpriteSheet.toolBar);
        
        public static Sprite dollarSign = new Sprite("/textures/sheets/dollarSign.png");
	
	// Menu sprites
	public static Sprite invMenu1 = new Sprite(150, 190, SpriteSheet.invMenu1);
	public static Sprite invMenu2 = new Sprite(150, 190, SpriteSheet.invMenu2);
	public static Sprite pauseMenu = new Sprite("/menus/pause_menu.png");
	
	// Pause menu pressed button sprites
	public static Sprite savePressedButton = new Sprite("/menus/buttons/save_button_pressed.png");
	public static Sprite loadPressedButton = new Sprite("/menus/buttons/load_button_pressed.png");
        public static Sprite smallExitPressedButton = new Sprite("/menus/buttons/smallexit_button_pressed.png");
	
	// Title screen sprites
	public static Sprite titleScreen = new Sprite("/menus/title_menu.png");
	public static Sprite newGameScreen = new Sprite("/menus/newgame_menu.png");
	public static Sprite loadGameScreen = new Sprite("/menus/loadgame_menu.png");
	public static Sprite settingsScreen = new Sprite("/menus/settings_menu.png");
	
	
	// Title screen pressed button sprites
	public static Sprite newGamePressedButton = new Sprite("/menus/buttons/newgame_button_pressed.png");
	public static Sprite loadGamePressedButton = new Sprite("/menus/buttons/loadgame_button_pressed.png");
	public static Sprite settingsPressedButton = new Sprite("/menus/buttons/settings_button_pressed.png");
	public static Sprite exitPressedButton = new Sprite("/menus/buttons/exit_button_pressed.png");
	public static Sprite backPressedButton = new Sprite("/menus/buttons/back_button_pressed.png");
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, SpriteSheet sheet) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.sheet = sheet;
		load3();
	}
	
	public Sprite(boolean top, int width, int height, SpriteSheet sheet) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.sheet = sheet;
		load2(top);
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}
	
	public Sprite(String path) {
		SIZE = -1;
		load4(path);
	}
	
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		
		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {
				
				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}
				
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_WIDTH);
			}
		}
		
		return sprites;
		
	}
	
	public void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
			}
		}
	}
	
	private void load2(boolean top) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (top) {
					pixels[x + y * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
				}
				else {
					pixels[x + y * width] = sheet.pixels[x + (y + 55) * sheet.SPRITE_WIDTH];
				}
			}
		}
	}
	
	private void load3() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
					pixels[x + y * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
	}
	
	private void load4(String path) {
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}


}
