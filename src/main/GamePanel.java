package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
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
	private TileMap tileMap;

	private short halfWidth;
	private short halfHeight;

	private Player player;

	public GamePanel(int width, int height) {
		super();

		this.halfWidth = Constants.Panel.WIDTH / 2;
		this.halfHeight = Constants.Panel.HEIGHT / 2;

		// Tilemap
		tileMap = new TileMap(Constants.Tile.WIDTH, Constants.Tile.HEIGHT);
		tileMap.loadTiles(Constants.TileSets.LVL_2);
		tileMap.loadMap(Constants.Maps.LVL_2);
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		// Background
		bg = new Background(Constants.Backgrounds.LVL_2);

		// LevelManager and Player
		this.player = new Player(0, 0, (short) Constants.Entities.Player.SPRITE_WIDTH,
				(short) Constants.Entities.Player.SPRITE_HEIGHT, tileMap);

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
		tileMap.setPosition(halfWidth - player.getX(), halfHeight - player.getY());
		bg.setPosition(tileMap.getX(), tileMap.getY());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		update();
		bg.draw(g);
		tileMap.draw(g);
		player.update();
		player.draw(g);
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}
}
