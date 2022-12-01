package tilemap;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.Constants;

import java.awt.Graphics;

public class Background {
	private BufferedImage bg;

	public Background(String filename) {
		this.bg = null;
		InputStream is;
		try {
			is = getClass().getResourceAsStream(filename);
			this.bg = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
	}
}
