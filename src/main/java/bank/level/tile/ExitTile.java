/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.level.tile;

import bank.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class ExitTile extends Tile {

	public ExitTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean exited() {
		return true;
	}

}
