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
	private BufferedImage[] arr;
	
	public GamePanel(int width, int height) {
		super();
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);
		this.arr = new BufferedImage[8];

		try {
			InputStream is = getClass().getResourceAsStream("/entities/player/Walk.png");
			this.bufferedImage = ImageIO.read(is);
			
			for (int i = 0; i < 8; i++)
				this.arr[i] = this.bufferedImage.getSubimage(i*72, 0, 72, 86);
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

		for (int i = 0; i < 8; i++)
			g.drawImage(arr[i], 0, i*86, 72, 86, null);
	}
}
