package gamestates;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.Clock;
import entities.Enemy;
import entities.HUD;
import entities.Player;
import entities.Zombie;
import tilemap.Background;
import tilemap.TileMap;
import utils.Constants;

public class Level1State extends GameState {
	// Attribute
	private TileMap tileMap;
	private Background bg;
	private Player player;
	private PauseMenuState pauseState;
	private HUD hud;
	private Clock clock;

	// Pause
	private static boolean pause = false;
	
	// enemies list
	private ArrayList<Enemy> enemies;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
		populateEnemies();

		this.player = new Player(100, 400, Constants.Entities.Player.SPRITE_WIDTH,
				Constants.Entities.Player.SPRITE_HEIGHT, tileMap, enemies);
		
		// HUD
		hud = new HUD(player);
	}

	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		enemies.add(new Zombie(300, 300, Constants.Entities.Enemies.Zombie.SPRITE_WIDTH, Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT, tileMap, player));
		enemies.add(new Zombie(600, 300, Constants.Entities.Enemies.Zombie.SPRITE_WIDTH, Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT, tileMap, player));
		enemies.add(new Zombie(2800, 300, Constants.Entities.Enemies.Zombie.SPRITE_WIDTH, Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT, tileMap, player));
	}

	@Override
	public void init() {
		// Tilemap
		tileMap = new TileMap(Constants.Tile.WIDTH, Constants.Tile.HEIGHT);
		tileMap.loadTiles(Constants.TileSets.LVL_1);
		tileMap.loadMap(Constants.Maps.LVL_1);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		// Background
		bg = new Background(Constants.Backgrounds.LVL_1);

		// pause
		pauseState = new PauseMenuState();
		
		// Clock
		clock = new Clock();
		clock.start();
	}

	@Override
	public void update() {
		// Update Player
		player.update();
		
		for (Enemy enemy : enemies) {
			enemy.update();
		}

		// Set position tileMap
		tileMap.setPosition(Constants.Panel.WIDTH / 2 - player.getX(), Constants.Panel.HEIGHT / 2 - player.getY());

		// Set background position
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		if(player.getY() >= Constants.Tile.HEIGHT * (tileMap.getRowCount() - 2)) {
			eventDead();
		}
		
		if(player.getX() >= Constants.Tile.WIDTH * (tileMap.getColCount() - 4)) {
			eventFinish();
		}
	}

	private void eventDead() {
		gsm.setState(Constants.GameStates.GAME_OVER);
	}

	private void eventFinish() {
		clock.stop();
		gsm.setState(Constants.GameStates.GAME_FINISH);
	}

	@Override
	public void draw(Graphics g) {
		// Draw Bg
		bg.draw(g);

		// Draw TileMap
		tileMap.draw(g);

		// Draw Player
		player.draw(g);
		
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
		
		// HUD
		hud.draw(g);
		
		// Clock
		clock.draw(g);
		
		// Pause
		if(pause) {
			pauseState.draw(g);
		}
				
	}

	@Override
	public void keyPressed(int k) {
		//System.out.println(pause);
		if(k == KeyEvent.VK_ESCAPE) {
			if(!pause) {
				pause = true;
				clock.stop();
			}
			else {
				pause = false;
				clock.start();
			}
		}
		else if(k == KeyEvent.VK_ENTER && pause) {
			pause = false;
			gsm.setState(Constants.GameStates.LEVEL_SELECTION);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

	@Override
	public Player getPlayer() {
		return player;
	}
}
