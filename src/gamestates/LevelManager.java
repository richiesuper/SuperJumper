package gamestates;

import main.GamePanel;
import tilemap.TileMap;

public class LevelManager {
	private GamePanel gamePanel;
	private Level levelOne;
	
	public LevelManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		//levelOne = new Level(TileMap.GetLevelData());
	}
	
	public Level getCurrentLevel() {
		return levelOne;
	}
}
