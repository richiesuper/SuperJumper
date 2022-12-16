package utils;

import java.awt.geom.Rectangle2D;
import main.GamePanel;
import tilemap.TileMap;

public class Helpers {

	public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData,
			boolean inMiddleArea, Rectangle2D.Float hitbox, TileMap tileMap) {
		if (!isSolid(x, y, lvlData, inMiddleArea, hitbox, tileMap)) // top left corner
			if (!isSolid(x + width, y + height, lvlData, inMiddleArea, hitbox, tileMap)) // bottom right corner
				if (!isSolid(x + width, y, lvlData, inMiddleArea, hitbox, tileMap)) // top right corner
					if (!isSolid(x, y + height, lvlData, inMiddleArea, hitbox, tileMap)) // bottom left corner
						if (!isSolid(x, y + height / 2, lvlData, inMiddleArea, hitbox, tileMap)) // middle left
							if (!isSolid(x + width, y + height / 2, lvlData, inMiddleArea, hitbox, tileMap)) // middle
																												// right
								return true;
		return false;
	}

	private static boolean isSolid(float x, float y, int[][] lvlData, boolean inMiddleArea, Rectangle2D.Float hitbox,
			TileMap tileMap) {
		if (x < 0 || x >= tileMap.getColCount() * Constants.Tile.WIDTH)
			return true;
		if (y < 0 || y >= tileMap.getColCount() * Constants.Tile.HEIGHT)
			return true;

		if (hitbox.x < 0 || hitbox.x >= Constants.Panel.WIDTH)
			return true;
		if (hitbox.y < 0 || hitbox.y >= Constants.Panel.HEIGHT)
			return true;

		float xIndex = x / Constants.Tile.WIDTH;
		float yIndex = y / Constants.Tile.HEIGHT;
		
		// measure against ArrayIndexOutOfBounds when player tries to move past panel borders
		if (xIndex < 0 || xIndex > tileMap.getColCount() || yIndex < 0 || yIndex > tileMap.getRowCount())
			return true;

		int tile = lvlData[(int) yIndex][(int) xIndex];

		System.out.println("xCorner: " + x + " yCorner: " + y + " Tile: " + tile);

		if (tile < 0 || tile > 19)
			return true;

		return false;
	}
}
