package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
	private BufferedImage spriteSheet;
	private BufferedImage[][] spriteTile;
	private int ticker, idx;
	private TileMap tm;

	public GamePanel(int width, int height) {
		super();
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);
		this.spriteTile = new BufferedImage[12][7];

		try {
			bg = new Background(Constants.Backgrounds.MAIN_MENU);
			InputStream is = getClass().getResourceAsStream(Constants.Entities.Player.SPRITE);
			this.spriteSheet = ImageIO.read(is);

			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 7; j++)
					this.spriteTile[i][j] = this.spriteSheet.getSubimage(j * 128, i * 128, 128, 128);

			tm = new TileMap(32);
			tm.loadTiles(Constants.Tilesets.LVL_2);
			tm.loadMap(Constants.Maps.LVL_2);
			tm.setPosition(0, 0);
			tm.setTween(1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);

		ticker = 0;
		idx = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ticker++;

		bg.draw(g);
		tm.draw(g);
		g.drawImage(spriteTile[1][idx], 0, 0, 128, 128, null);
//		g.drawImage(spriteTile[2][idx], 0, 128, 128, 128, null);
//		g.drawImage(spriteTile[3][idx], 0, 256, 128, 128, null);
//		g.drawImage(spriteTile[4][idx], 0, 384, 128, 128, null);
//		g.drawImage(spriteTile[5][idx], 128, 0, 128, 128, null);
//		g.drawImage(spriteTile[6][idx], 128, 128, 128, 128, null);
//		g.drawImage(spriteTile[7][idx], 128, 256, 128, 128, null);
//		g.drawImage(spriteTile[8][idx], 128, 384, 128, 128, null);
//		g.drawImage(spriteTile[9][idx], 256, 0, 128, 128, null);
//		g.drawImage(spriteTile[10][idx], 256, 128, 128, 128, null);
//		g.drawImage(spriteTile[11][idx], 256, 256, 128, 128, null);

		if (ticker % 10 == 0) {
			ticker = 0;

			if (idx >= 6) {
				idx = 0;
			}

			idx++;
		}
	}
}
