/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.entity.mob.Player;
import bank.graphics.Fade;
import bank.graphics.Font;
import bank.graphics.Screen;
import bank.graphics.Sprite;
import bank.input.Keyboard;
import bank.input.Mouse;
import bank.inventory.Items;
import bank.inventory.UsableItem;
import bank.level.Level;
import bank.level.cutscenes.CutScenes;
import bank.level.dialog.Dialog;
import bank.menus.ToolBar;
import bank.saves.Save;
import bank.titleMenu.TitleMenu;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/**
 *
 * @author Aaron
 */
public class Game extends Canvas implements Runnable {
	
    private static int width = 420;
    private static int height = width * 9 / 16;
    private static int scale = 3;
    public static String title = "Game";

    private Thread thread; 
    private Keyboard key;
    private Mouse mouse;
    private Level level;
    private Player player;
    private Dialog dialog;
    private ToolBar toolBar;
    private Items items;
    private CutScenes cut;
    private TitleMenu titleMenu;
    private Save save;
    private Font font;
    private Fade fader;
    private JFrame frame;
    private boolean running = false;
    private boolean onStartup = true;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        fader = new Fade(width, height);
        frame = new JFrame();
        key = new Keyboard();
        mouse = new Mouse();
        cut = new CutScenes();
        save = new Save();
        items = UsableItem.drinkingWater;
        titleMenu = new TitleMenu(Sprite.titleScreen);
        //level = Level.spawnLevel;

        font = new Font();
        toolBar = new ToolBar(Sprite.toolBar1, key, screen.width, screen.height);
        dialog = new Dialog();

        //player = new Player(level.playerSpawn.x(), level.playerSpawn.y(), key, dialog, 2);		// then add level.playerSpawn() for x and y in the Player constructor
        //player.init(level);


        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
	
    public void startGame(int characterInfo) {
        level = Level.spawnLevel;
        player = new Player(level.playerSpawn.x(), level.playerSpawn.y(), key, dialog, characterInfo, cut);
        player.init(level);
        toolBar.initPlayer(player);
        player.onCutscene = true;
        cut.introScenePlaying = true;
    }
	
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
                thread.join();
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(title + "  |  " + updates + " ups " + frames + " fps ");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    private void loadGame(int characterInfo) {
        player = new Player(key, dialog, characterInfo, cut);
        save.decode(characterInfo);
        setLevel();
        setPlayer();
    }

    private void load() {
        save.decode(player.playerNum);
        setLevel();
        setPlayer();
        //player.changeLocation(save.playerX, save.playerY);
        //player.dir = save.playerDir;
    }
    
    private void setPlayer() {
        player.changeLocation(save.playerX, save.playerY);
        player.dir = save.playerDir;
        player.cash = save.cash;
        player.attack = save.attack;
        player.defence = save.defence;
        player.health = save.health;
        player.speed = save.speed;
        player.playerLevel = save.playerLevel;
        player.equipedWeapon = items.getItemFromID(save.equipedWeapon);
        player.inventory.clear();
        for (int i = 0; i < save.inventoryIDS.length; i++) {
            player.inventory.add(items.getItemFromID(save.inventoryIDS[i]));
            player.inventory.get(i).setAmount(save.inventoryAmounts[i]);
        }
        for (int i = 0; i < 3; i++) {
            player.equipedArmor[i] = items.getItemFromID(save.equipedArmor[i]);
        }
        player.setItemEffectToStats();
        player.init(level);
        toolBar.initPlayer(player);
    }
    
    private void setLevel() {
        switch (save.levelNum) {
            case 1:
                level = Level.spawnLevel;
                break;
            case 2:
                level = Level.crazyLevel;
                break;
            case 3:
                level = Level.house1SubLevel;
                break;
            case 4:
                level = Level.biggerHouseLevel;
                break;
            case 5:
                level = Level.biggerHouseUpstairsLevel;
                break;
            case 6:
                level = Level.mazeLevel;
                break;
            case 7:
                level = Level.house2SubLevel;
                break;
            case 8:
                level = Level.storeOneLevel;
                break;
            case 9:
                level = Level.baitShopLevel;
                break;
            case 10:
                level = Level.scottHouseLevel;
                break;
            case 11:
                level = Level.sidewaysHouseLevel;
                break;
            case 12:
                level = Level.underGroundCrazyLevel;
                break;
        }
        player.init(level);
        Level.spawnLevel.setChests(save.spawnLevelChests);
        Level.underGroundCrazyLevel.setChests(save.underGroundCrazyLevelChests);
    }

    public void update() {
        if (onStartup) {
            if (titleMenu.startGame) {
                startGame(titleMenu.characterInfo);
                onStartup = false;
                return;
            }
            else if(titleMenu.startGameFromLoad) {
                loadGame(titleMenu.characterInfo);
                onStartup = false;
                return;
            }
            titleMenu.update();
        }
        else if(player.onCutscene) {
            key.update();
            player.update();
            level.update();
            dialog.update();
        }
        else {
            key.update();
            if (!fader.fadeIn && !fader.fadeOut && !toolBar.showingInv && !toolBar.showingPauseMenu) player.update();
            if (player.exited()) {
                fader.fadeOut = true;
                fader.setPosition(player.x, player.y);
                player.resetExited();
                player.changingLevels = true;
            }
            if (fader.fadeIn) {
                if (player.changingLevels == true) {
                    player.changingLevels = false;
                    level = Level.getDestination(player.getLevelDestination());
                    player.changeLocation(player.getXDestination(), player.getYDestination());
                    player.init(level);
                    fader.setPosition(player.x, player.y);
                }
            }
            level.update();
            if (!dialog.isOpen) toolBar.update();
            if (toolBar.goToSave) save.save(player, level);
            if (toolBar.goToLoad) {
                load();
            }
            fader.update();
            dialog.update();
        }
    }

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		if (onStartup) {
			titleMenu.render(screen);
		}
		else if(player.onCutscene) {
			int xScroll = player.x - screen.width / 2;
			if (player.x <= screen.width / 2 && screen.width < level.getLevelPixelWidth()) xScroll = 0;
			else if (player.x >= level.getLevelPixelWidth() - (screen.width / 2) && screen.width < level.getLevelPixelWidth()) xScroll = level.getLevelPixelWidth() - screen.width;
			int yScroll = player.y - screen.height / 2;
			if (player.y <= screen.height / 2 && screen.height < level.getLevelPixelHeight()) yScroll = 0;
			else if (player.y >= level.getLevelPixelHeight() - (screen.height / 2) && screen.height < level.getLevelPixelHeight()) yScroll = level.getLevelPixelHeight() - screen.height;
			
			level.render(xScroll, yScroll, screen);
                        level.render(screen);
			player.render(screen);
			dialog.render(screen);
		}
		else {
			int xScroll = player.x - screen.width / 2;
			if (player.x <= screen.width / 2 && screen.width < level.getLevelPixelWidth()) xScroll = 0;
			else if (player.x >= level.getLevelPixelWidth() - (screen.width / 2) && screen.width < level.getLevelPixelWidth()) xScroll = level.getLevelPixelWidth() - screen.width;
			int yScroll = player.y - screen.height / 2;
			if (player.y <= screen.height / 2 && screen.height < level.getLevelPixelHeight()) yScroll = 0;
			else if (player.y >= level.getLevelPixelHeight() - (screen.height / 2) && screen.height < level.getLevelPixelHeight()) yScroll = level.getLevelPixelHeight() - screen.height;
			
			level.render(xScroll, yScroll, screen);
                        level.render(screen);
			player.render(screen);
			toolBar.render(screen);
			//screen.renderSprite(screen.width / 2 - Sprite.invMenu.getWidth() / 2, screen.height / 2 - Sprite.invMenu.getHeight() / 2, Sprite.invMenu, false);
			dialog.render(screen);
			fader.render(screen);
			//font.renderSmallCharacters("Aaron McNulty* Is the shit!", screen);
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}


	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.requestFocus();
		
		game.start();
	}

}
