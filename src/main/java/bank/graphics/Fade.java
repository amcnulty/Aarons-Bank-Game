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
public class Fade {
	
	public boolean fadeOut = false;
	public boolean fadeIn = false;
	public boolean finished = false;
	
	private int width, height;
	private boolean[] pixels;					// true if that pixel needs to be black
	private int lastX, lastY;
	private int nextX, nextY;
	private int xp, yp;
	private int radius = 480;					// radius of circle fade
	
	public Fade(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new boolean[width * height];
	}
	
	public void update() {
		if (fadeOut) {
			if (radius != 0 && radius > 0) {
				radius -= 16;
			}
			else {
				fadeIn = true;
				fadeOut = false;
			}
		}
		else if (fadeIn) {
			if (radius != 480 && radius < 480) {
				radius += 16;
			}
			else {
				fadeIn = false;
				fadeOut = false;
			}
		}
	}
	
	public void render(Screen screen) {
		if (fadeOut || fadeIn) {
			screen.renderFade(xp, yp, radius);
		}
	}
	
	public void setPosition(int xp, int yp) {
		this.xp = xp;
		this.yp = yp;
	}
	
	public void fadeOut() {
		if (radius != 0 && radius > 0) {
			for (int y = 0; y < height; y++) {
				int yy = y;
				double yDist = Math.abs(yy - lastY);
				for (int x = 0; x < width; x++) {
					int xx = x;
					double xDist = Math.abs(xx - lastX);
					if ((int)Math.sqrt(Math.pow(xDist, 2.0) + Math.pow(yDist, 2.0)) >= radius) {
						pixels[xx + yy * width] = true;
					}
					else {
						pixels[xx = yy * width] = false;
					}
				}
			}
		radius -= 16;
		}
		else if (radius == 0) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					pixels[x + y * width] = true;
				}
			}
			fadeOut = false;
			fadeIn = true;
		}
	}
	
	public void fadeIn() {
		if (radius != 480 && radius < 480) {
			for (int y = 0; y < height; y++) {
				double yDist = Math.abs(y - nextY);
				for (int x = 0; x < width; x++) {
					double xDist = Math.abs(x - nextX);
					if ((int)Math.sqrt(Math.pow(xDist, 2.0) + Math.pow(yDist, 2.0)) >= radius) {
						pixels[x + y * width] = true;
					}
					else pixels[x + y * width] = false;
				}
			}
			radius += 16;
		}
		else if (radius == 480) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					pixels[x + y * width] = false;
				}
			}
			fadeOut = true;
			fadeIn = false;
			finished = true;
		}
	}
	
	public void setLastPosition(int x, int y) {
		lastX = x;
		lastY = y;
	}
	
	public void setNextPosition(int x, int y) {
		nextX = x;
		nextY = y;
	}
	
	
}
