package entities;

import tilemap.TileMap;
import utils.Constants;

public abstract class Enemy extends Entity {

	public Enemy(float x, float y, short width, short height, TileMap tileMap) {
		super(x, y, width, height, tileMap);
	}
}
