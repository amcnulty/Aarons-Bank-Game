/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level.dialog;

import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.graphics.SpriteSheet;

/**
 *
 * @author Aaron
 */
public class Dialog {
	
	private byte messageSpeed = 2;
	
	private Sprite sprite1;
	private Sprite sprite2;
	private Sprite sprite3;
	private Sprite sprite4;
	private Sprite sprite5;
	public boolean isOpen = false;
	private boolean hasNext = false;
	private String[] message;
	private String segment1 = "";	// line one of dialog box
	private String segment2 = "";	// line two of dialog box
	private String segment3 = "";	// line three of dialog box
	private String segment4 = "";	// line four of dialog box
	private String segment5 = "";	// line five of dialog box
	
	
	private static SpriteSheet smallFont = new SpriteSheet("/fonts/8x8_font_sheet.png", 8);
	private static Sprite[] smallCharacters = Sprite.split(smallFont);
	
	public static String charactersIndex = "!\"#$%%'()**,-./0123456789:;;==??ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^__abcdefghijklmnopqrstuvwxyz{{}~";
	
	private int time1 = 0;				// keeps track of iterations in update method to control messageSpeed
	private int time2 = 0;				// keeps track of time for blinking down arrow in corner of dialog box
	private int index = 0;				// index of message array. Current message being displayed in dialog box
	private int currentMessageLength;	// length of message[index]
	private int p = 0;					// position of charAt() looking backwards through string at that index in render method
	private int n = 0;					// the first argument in substring in update method
	private int i = 1;					// the second argument in substring in update method
	private int s = 0;					// switch statement control variable in update method
	private int toggle = 75;
	
	public Dialog() {
		sprite1 = Sprite.dialog_box;
		sprite2 = Sprite.dialog_box2;
		sprite3 = Sprite.dialog_box3;
		sprite4 = Sprite.dialog_box4;
		sprite5 = Sprite.dialog_box5;
	}
	
	public void update() {
		if (isOpen) {
			if (index < message.length - 1) hasNext = true;
			else hasNext = false;
			time1++;
			time2++;
			switch(s) {
			case 0:
				currentMessageLength = message[index].length();
				s = 1;
				if (currentMessageLength > 31) p = 31;
				break;
			case 1:
				if (time1 % messageSpeed == 0 && i <= message[index].length() && p == 0) {
					segment1 = message[index].substring(n, i);
					i++;
					time1 = 0;
				}
				else if (i < p + 1 && time1 % messageSpeed == 0) {
					segment1 = message[index].substring(n, i);
					i++;
					time1 = 0;
					
				}
				else if (i == p + 1 && p != 0) {			// if the end of the substring has reached the last space
					currentMessageLength -= segment1.length(); 			// find out how much is left in the message
					if (currentMessageLength == 0) {		// if current message is empty
						s = 6;								// always break from this switch statement
						p = 0;
					}
					if (currentMessageLength <= 31) {		// if there is less than or equal to 31 characters left in the message
						n  = i;								// more needs to go here
						p = 0;								// don't check for any more spaces
						i++;
						s = 2;
					}
					else if (currentMessageLength > 31) {	// else if there is more than 31 characters left in the message
						n = i;
						p = n + 31;							// check for spaces in the next segment
						i++;								// get ready to scan through the next 31 characters
						s = 2;								// start filling up segment2
					}
				}
				break;
			case 2:
				if (time1 % messageSpeed == 0 && i <= message[index].length() && p == 0) {
					segment2 = message[index].substring(n, i);
					i++;
					time1 = 0;
				}
				else if (i < p + 1 && time1 % messageSpeed == 0) {
					segment2 = message[index].substring(n, i);
					i++;
					time1 = 0;
					
				}
				else if (i == p + 1 && p != 0) { 						
					currentMessageLength -= segment2.length(); 			
					if (currentMessageLength == 0) {		
						s = 6;								
						p = 0;
					}
					if (currentMessageLength <= 31) {		
						n  = i;								// more needs to go here
						p = 0;								// don't check for any more spaces
						i++;
						s = 3;
					}
					else if (currentMessageLength > 31) {	
						n = i;
						p = n + 31;							// check for spaces in the next segment
						i++;								
						s = 3;								
					}
				}
				break;
			case 3:
				if (time1 % messageSpeed == 0 && i <= message[index].length() && p == 0) {
					segment3 = message[index].substring(n, i);
					i++;
					time1 = 0;
				}
				else if (i < p + 1 && time1 % messageSpeed == 0) {
					segment3 = message[index].substring(n, i);
					i++;
					time1 = 0;
					
				}
				else if (i == p + 1 && p != 0) { 						
					currentMessageLength -= segment3.length(); 			
					if (currentMessageLength == 0) {		
						s = 6;								
						p = 0;
					}
					if (currentMessageLength <= 31) {		
						n  = i;								// more needs to go here
						p = 0;								// don't check for any more spaces
						i++;
						s = 4;
					}
					else if (currentMessageLength > 31) {	
						n = i;
						p = n + 31;							// check for spaces in the next segment
						i++;								
						s = 4;								
					}
				}
				break;
			case 4:
				if (time1 % messageSpeed == 0 && i <= message[index].length() && p == 0) {
					segment4 = message[index].substring(n, i);
					i++;
					time1 = 0;
				}
				else if (i < p + 1 && time1 % messageSpeed == 0) {
					segment4 = message[index].substring(n, i);
					i++;
					time1 = 0;
					
				}
				else if (i == p + 1 && p != 0) { 						
					currentMessageLength -= segment4.length(); 			
					if (currentMessageLength == 0) {		
						s = 6;								
						p = 0;
					}
					if (currentMessageLength <= 31) {		
						n  = i;								// more needs to go here
						p = 0;								// don't check for any more spaces
						i++;
						s = 5;
					}
					else if (currentMessageLength > 31) {	
						n = i;
						p = n + 31;							// check for spaces in the next segment
						i++;									
						s = 5;								
					}
				}
				break;
			case 5:
				if (time1 % messageSpeed == 0 && i <= message[index].length() && p == 0) {
					segment5 = message[index].substring(n, i);
					i++;
					time1 = 0;
				}
				else if (i < p + 1 && time1 % messageSpeed == 0) {
					segment5 = message[index].substring(n, i);
					i++;
					time1 = 0;
					
				}
			/**	else if (i == p + 1) { 						
					currentMessageLength -= 31; 			
					if (currentMessageLength == 0) {		
						s = 6;								
						p = 0;
					}
					if (currentMessageLength <= 31) {		
						n  = i;								// more needs to go here
						p = 0;								// don't check for any more spaces
						i++;
						s = 3;
					}
					else if (currentMessageLength > 31) {	
						i++;								
						n += p + 1;							
						p = 31;								
						s = 3;								
					}
				}*/
				break;
			default:
					break;
				
			}
			
			if (time2 % 30 == 0 && toggle == 75) toggle = 79;
			else if (time2 % 30 == 0 && toggle == 79) toggle = 75;
			if (time1 > 6000) {
				time1 = 30;
			}
			if (time2 > 6000) {
				time2 = 0;
			}
		}
	}
	
	public void render(Screen screen) {
		if (isOpen) {
			// open up dialog box
			screen.renderSprite(16, 16, sprite1, false);
			screen.renderSprite(18, 18, sprite2, false);
			screen.renderSprite(20, 20, sprite3, false);
			screen.renderSprite(22, 22, sprite4, false);
			screen.renderSprite(24, 24, sprite5, false);
			// render blinking arrow if there is more to the message
			if (hasNext) screen.renderSprite(265, toggle, smallCharacters[36], false);
			else screen.renderSprite(265, 75, smallCharacters[88], false);
			
			// scan through message[index].charAt(p) where p is the relative 31 position
                        try {
                            if (p != 0) {
                                    if (message[index].charAt(p) == 32) {
                                    }
                                    else p--;
                            }
                        }
                        catch (StringIndexOutOfBoundsException e) {
                            p = 0;
                        }
			// render all of the segments on the dialog box
			for (int i = 0; i < segment1.length(); i++) {
				char currentChar = segment1.charAt(i);
				int index = charactersIndex.indexOf(currentChar);
				screen.renderSprite(24 + i * 8, 24, smallCharacters[index + 33], false);
			}
			for (int i = 0; i < segment2.length(); i++) {
				char currentChar = segment2.charAt(i);
				int index = charactersIndex.indexOf(currentChar);
				screen.renderSprite(24 + i * 8, 24 + 1 * 10, smallCharacters[index + 33], false);
			}
			for (int i = 0; i < segment3.length(); i++) {
				char currentChar = segment3.charAt(i);
				int index = charactersIndex.indexOf(currentChar);
				screen.renderSprite(24 + i * 8, 24 + 2 * 10, smallCharacters[index + 33], false);
			}
			for (int i = 0; i < segment4.length(); i++) {
				char currentChar = segment4.charAt(i);
				int index = charactersIndex.indexOf(currentChar);
				screen.renderSprite(24 + i * 8, 24 + 3 * 10, smallCharacters[index + 33], false);
			}
			for (int i = 0; i < segment5.length(); i++) {
				char currentChar = segment5.charAt(i);
				int index = charactersIndex.indexOf(currentChar);
				screen.renderSprite(24 + i * 8, 24 + 4 * 10, smallCharacters[index + 33], false);
			}
		}
	}
	
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	public void openDialog() {
		isOpen = true;
	}
	
	public void closeDialog() {
		isOpen = false;
		i = 1;
		n = 0;
		p = 0;
		s = 0;
		segment1 = "";
		segment2 = "";
		segment3 = "";
		segment4 = "";
		segment5 = "";
	}
	
	public void advanceDialog() {
		if (index < message.length - 1) {		// change this to just if (hasNext)
			i = 1;
			n = 0;
			p = 0;
			s = 0;
			
			segment1 = "";
			segment2 = "";
			segment3 = "";
			segment4 = "";
			segment5 = "";
			
			index++;
		}
		else {
			i = 1;
			index = 0;
			time2 = 0;
			toggle = 75;
			closeDialog();
		}
	}
	
	public void setMessageSpeed(byte b) {
		messageSpeed = b;
	}
	

}
