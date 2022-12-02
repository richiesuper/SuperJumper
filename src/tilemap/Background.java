package tilemap;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.Constants;

import java.awt.Graphics;

public class Background {
	private BufferedImage bg;
	private float x;
	private float y;
	private double moveScale;

	public Background(String filename) {
		this.bg = null;
		this.x = 0;
		this.y = 0;
		this.moveScale = 0.1;
		
		InputStream is;
		try {
			is = getClass().getResourceAsStream(filename);
			this.bg = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double d, double e) {
		this.x = (float) ((d * moveScale) % Constants.Panel.WIDTH);
		this.y = (float) ((e * moveScale) % Constants.Panel.HEIGHT);
	}

	public void draw(Graphics g) {
		g.drawImage(bg, (int) x, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
	}
}
