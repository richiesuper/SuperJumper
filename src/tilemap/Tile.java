package tilemap;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage image;

	public Tile(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

}
