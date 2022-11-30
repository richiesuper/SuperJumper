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
import utils.Constants;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private Background bg;
	private BufferedImage spriteSheet;
	private BufferedImage[][] spriteTile;
	private int ticker, idx;
	
	public GamePanel(int width, int height) {
		super();
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);
		this.spriteTile = new BufferedImage[12][7];

		try {
			bg = new Background(Constants.Backgrounds.MAIN_MENU);
			InputStream is = getClass().getResourceAsStream("/entities/player/player.png");
			this.spriteSheet = ImageIO.read(is);
			
			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 7; j++)
					this.spriteTile[i][j] = this.spriteSheet.getSubimage(j*128, i*128, 128, 128);
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
		g.drawImage(spriteTile[1][idx], 0, 0, 128, 128, null);

		if (ticker % 10 == 0) {
			ticker = 0;
			
			if (idx >= 6) {
				idx = 0;
			}

			idx++;
		}
	}
}
