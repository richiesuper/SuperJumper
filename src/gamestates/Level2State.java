package gamestates;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.HUD;
import entities.Player;
import tilemap.Background;
import tilemap.TileMap;
import utils.Constants;

public class Level2State extends GameState {
	// Attribute
	private TileMap tileMap;
	private Background bg;
	private Player player;
	private PauseMenuState pauseState;
	private HUD hud;

	// Pause
	private static boolean pause = false;

	public Level2State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		// Tilemap
		tileMap = new TileMap(Constants.Tile.WIDTH, Constants.Tile.HEIGHT);
		tileMap.loadTiles(Constants.TileSets.LVL_2);
		tileMap.loadMap(Constants.Maps.LVL_2);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		// Background
		bg = new Background(Constants.Backgrounds.LVL_2);

		// LevelManager and Player
		this.player = new Player(0, 0, Constants.Entities.Player.SPRITE_WIDTH,
				Constants.Entities.Player.SPRITE_HEIGHT, tileMap);
		
		// Pause
		pauseState = new PauseMenuState();
		
		// HUD
		hud = new HUD(player);
	}

	@Override
	public void update() {
		// Update Player
		player.update();

		// Set position tileMap
		tileMap.setPosition(Constants.Panel.WIDTH / 2 - player.getX(), Constants.Panel.HEIGHT / 2 - player.getY());

		// Set background position
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		if(player.getY() >= Constants.Tile.HEIGHT * (tileMap.getRowCount() - 2) - 20) {
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
		gsm.setState(Constants.GameStates.GAME_FINISH);
	}

	@Override
	public void draw(Graphics g) {
		// Draw Bg
		bg.draw(g);

		// Draw TileMap
		tileMap.draw(g);

		// Draw player
		player.draw(g);

		// Draw Pause
		if(pause) {
			pauseState.draw(g);
		}
		
		// Draw HUD
		hud.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) {
			if(!pause)
				pause = true;
			else
				pause = false;
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
