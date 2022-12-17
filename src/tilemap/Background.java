package tilemap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.Constants;

public class Background {
	private BufferedImage bg;
	private float x;
	private float y;
	private double moveScale;
	private short ticker;

	public Background(String filename) {
		// prepare for the worst
		this.bg = null;
		this.x = 0;
		this.y = 0;
		this.moveScale = 0.1;
		// for debugging
		this.ticker = 0;

		InputStream is;

		try {
			is = getClass().getResourceAsStream(filename);
			this.bg = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {
		this.x = (float) ((x * moveScale) % Constants.Panel.WIDTH);
		this.y = (float) ((y * moveScale) % Constants.Panel.HEIGHT);
	}

	public void draw(Graphics g) {
		// debugging
		ticker++;
		if (ticker >= 20) {
//			System.out.println("x: " + x + " y: " + y);
			ticker = 0;
		}

		// draw the first background "pane" until it finally disappears while going to
		// the left...
		g.drawImage(bg, (int) x, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);

		if (x < 0)
			// draw the second pane to the right, while also REVERSING THE BACKGROUND TO
			// MAKE IT PRETTY!
			// notice the multiplication by 2 and MINUS SIGN on the Constants.Panel.WIDTH!
			g.drawImage(bg, (int) x + 2 * Constants.Panel.WIDTH, (int) y, -Constants.Panel.WIDTH,
					Constants.Panel.HEIGHT, null);
		if (x > 0)
			// draw the pane to the left of original pane
			g.drawImage(bg, (int) x - Constants.Panel.WIDTH, (int) y, Constants.Panel.WIDTH, Constants.Panel.HEIGHT,
					null);
	}
}
