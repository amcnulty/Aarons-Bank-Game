/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.graphics;

/**
 *
 * @author Aaron
 */
public class Font {
	
    private static SpriteSheet bigFont = new SpriteSheet("/fonts/big_font_sheet.png", 16);
    private static SpriteSheet smallFont = new SpriteSheet("/fonts/8x8_font_sheet.png", 8);
    private static SpriteSheet superSmallFont = new SpriteSheet("/fonts/5x5_font_sheet.png", 5);
    private static Sprite[] superSmallCharacters = Sprite.split(superSmallFont);
    private static Sprite[]	bigCharacters = Sprite.split(bigFont);
    private static Sprite[] smallCharacters = Sprite.split(smallFont);

    public static String smallCharactersIndex = "!\"##%%%()**,-./0123456789:;;==??ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^__abcdefghijklmnopqrstuvwxyz{{}~";
    public static String superSmallCharactersIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789++-";

    public Font() {

    }

    public void renderSmallCharacters(int x, int y, String text, Screen screen) {
        int xx = x * 3;
        int yy = y * 3;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int index = smallCharactersIndex.indexOf(currentChar);
            screen.renderSprite(xx + i * 8, yy, smallCharacters[index + 33], false);
        }
    }
    
    public void renderSuperSmallCharacters(int x, int y, String text, Screen screen) {
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int index = superSmallCharactersIndex.indexOf(currentChar);
            screen.renderSprite(x + i * 5, y, superSmallCharacters[index], false);
        }
    }
    
    public void renderSuperSmallCharacters2(int x, int y, String text, Screen screen) {
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == ' ') {
                x -= 3;
                continue;
            }
            else {
                int index = superSmallCharactersIndex.indexOf(currentChar);
                screen.renderSprite(x + i * 6, y, superSmallCharacters[index], false);
            }
        }
    }
    
    public Sprite[] returnCharacterSprites(String text) {
        Sprite[] sprites = new Sprite[text.length()];
        for (int i = 0; i < text.length(); i ++) {
            char currentChar = text.charAt(i);
            int index = superSmallCharactersIndex.indexOf(currentChar);
            sprites[i] = superSmallCharacters[index];
        }
        return sprites;
    }
	
}
