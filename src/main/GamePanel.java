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

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private BufferedImage bufferedImage;
	private BufferedImage[][] arr;
	
	public GamePanel(int width, int height) {
		super();
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);
		this.arr = new BufferedImage[12][7];

		try {
			InputStream is = getClass().getResourceAsStream("/entities/player/player.png");
			this.bufferedImage = ImageIO.read(is);
			
			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 7; j++)
					this.arr[i][j] = this.bufferedImage.getSubimage(j*128, i*128, 128, 128);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 7; j++)
				g.drawImage(arr[i][j], j*128, i*128, 128, 128, null);
	}
}
