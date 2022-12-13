package utils;

import java.awt.geom.Rectangle2D;

public class Helpers {

	public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData, Rectangle2D.Float hitbox) {
		if (!isSolid(x, y, lvlData, hitbox)) // top left corner
			if (!isSolid(x + width, y + height, lvlData, hitbox)) // bottom right corner
				if (!isSolid(x + width, y, lvlData, hitbox)) // top right corner
					if (!isSolid(x, y + height, lvlData, hitbox)) // bottom left corner
						return true;
		return false;
	}

	private static boolean isSolid(float x, float y, int[][] lvlData, Rectangle2D.Float hitbox) {
		if (x < 0 || x >= Constants.Panel.WIDTH)
			return true;
		if (y < 0 || y >= Constants.Panel.HEIGHT)
			return true;
		
		float xIndex = x / Constants.Tile.WIDTH;
		float yIndex = y / Constants.Tile.HEIGHT;
		int tile = lvlData[(int) yIndex][(int) xIndex];

		//System.out.println("Tile: " + tile);

		if (tile < 0 || tile > 16)
			return true;

		return false;
	}
}
