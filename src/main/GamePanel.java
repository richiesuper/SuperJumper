package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import entities.Player;
import gamestates.LevelManager;
import inputs.KeyboardInput;
import inputs.MouseInput;
import tilemap.Background;
import tilemap.TileMap;
import utils.Constants;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private Background bg;
	private TileMap tm;

	private short halfWidth;
	private short halfHeight;

	private Player player;
	
	private LevelManager levelManager;

	public GamePanel(int width, int height) {
		super();

		this.halfWidth = Constants.Panel.WIDTH / 2;
		this.halfWidth = Constants.Panel.HEIGHT / 2;
		
		// LevelManager and Player
		this.player = new Player(100, 100, 
				(short) Constants.Entities.Player.SPRITE_WIDTH, (short) Constants.Entities.Player.SPRITE_HEIGHT);
		
		// Background
		bg = new Background(Constants.Backgrounds.LVL_1);

		// Tilemap
		tm = new TileMap(Constants.Tile.WIDTH, Constants.Tile.HEIGHT);
		tm.loadTiles(Constants.TileSets.LVL_1);
		tm.loadMap(Constants.Maps.LVL_1);
		tm.setPosition(0, 0);
		tm.setTween(1);
		
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);
	}

	public Player getPlayer() {
		return player;
	}

	public void update() {
		tm.setPosition(halfWidth - player.getX(), halfHeight - player.getY());
		bg.setPosition(tm.getX(), tm.getY());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		update();
		bg.draw(g);
		tm.draw(g);
		player.update();
		player.draw(g);
	}
}
