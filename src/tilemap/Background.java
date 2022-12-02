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
			int[] bgColor = this.bg.getRGB(0, 0, this.bg.getWidth(), this.bg.getHeight(), null, 0, this.bg.getWidth() * this.bg.getHeight() );
			//System.out.println(bgColor[1]);
			//System.out.println( this.bg.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y) {
		this.x = (float) ((x * moveScale) % Constants.Panel.WIDTH);
		this.y = (float) ((y * moveScale) % Constants.Panel.HEIGHT);
	}

	public void draw(Graphics g) {
		g.drawImage(bg, (int) x, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
		
		if(x < 0) g.drawImage(bg, (int) x + Constants.Panel.WIDTH, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
		if(x > 0) g.drawImage(bg, (int) x - Constants.Panel.WIDTH, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
	}
}
