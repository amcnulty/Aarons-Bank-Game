/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.entity.mob;

import bank.entity.Entity;
import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	public int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya !=0) {
			move(xa, 0);
			move(0, ya);
			return;	
		}
		// This controls where the player faces while walking
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}		
	}
	
	
	public void update() {
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
                        int nx = (x + xa) + c % 2 * 14 - 8;
			int yt = ((y + ya) + c / 2 * 15 + 0) / 16;
                        int ny = (y + ya) + c / 2 * 15 + 0;
			if (level.getTile(xt, yt).solid()) solid = true;
                        else if (level.npcHere(nx, ny)) solid = true;
                        else if (level.chestHere(nx, ny)) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}
	

}
