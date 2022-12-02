package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import entities.Player;
import inputs.KeyboardInput;
import inputs.MouseInput;
import tilemap.Background;
import utils.Constants;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private Background bg;

	private Player player;

	public GamePanel(int width, int height) {
		super();

		this.player = new Player(0, Constants.Panel.HEIGHT - Constants.Entities.Player.SPRITE_HEIGHT);

		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);

		bg = new Background(Constants.Backgrounds.LVL_1);

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		bg.draw(g);
		player.update();
		player.draw(g);
	}
}
