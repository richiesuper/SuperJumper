package utils;

public class Constants {
	public static class Folders {
		// filenames
		public static final String BGS = "/bgimages/";
		public static final String PLAYER = "/entities/player/";
		public static final String ENEMIES = "/entities/enemies/";
		public static final String MAPS = "/maps/";
		public static final String TILESETS = "/tilesets/";
		public static final String UI = "/ui/";
	}

	public static class Game {
		// game title
		public static final String TITLE = "Super Jumpers";
		// please read this as "milliseconds in a second"
		public static final short MS_IN_S = 1000;
		// frame per second
		public static final byte FPS = 60;
		// period per frame in ms
		public static final float FRAME_PERIOD = (float) MS_IN_S / (float) FPS;
	}

	public static class GameStates {
		// Num GameStates
		public static final byte NUM_GAME_STATE = 8;

		// types of game states
		public static final byte MAIN_MENU = 0;
		public static final byte PAUSE_MENU = 1;
		public static final byte ABOUT_MENU = 2;
		public static final byte GAME_OVER = 3;
		public static final byte GAME_FINISH = 4;
		public static final byte LEVEL_SELECTION = 5;
		public static final byte LVL_1 = 6;
		public static final byte LVL_2 = 7;
	}

	// UI game
	public static class UI {
		// Button menu
		public static class MenuButton {
			public static final String MENU_BUTTON = Folders.UI + "menubutton.png";
		}
		
		// Level Selection Menu
		public static class LevelSelection{
			public static final String LEVEL_TITLE = Folders.UI + "leveltitle.png";
			public static final String LEVEL_UNSELECT = Folders.UI + "levelunselect.png";
			public static final String LEVEL_1_SELECT = Folders.UI + "level1select.png";
			public static final String LEVEL_2_SELECT = Folders.UI + "level2Select.png";
		}
		
		// Game Over Menu
		public static class GameOver{
			public static final String GAME_OVER = Folders.UI + "gameover.png";
		}
		
		public static class GameFinish{
			public static final String GAME_FINISH = Folders.UI + "finishscreen.png";
		}
		
		public static class PauseGame{
			public static final String PAUSE_SCREEN = Folders.UI + "pausescreen.png";
			public static final String PAUSE_THEME = Folders.UI + "pause.png";
		}
	}

	public static class Tile {
		// tile dimensions
		public static final byte WIDTH = 32;
		public static final byte HEIGHT = 32;

		// tile count
		public static final byte HORIZ_SUM = 32;
		public static final byte VERT_SUM = 18;

		// collision type
		public static final byte PASSTHROUGH = 0;
		public static final byte BLOCKED = 1;
	}

	public static class TileMap {
		public static final byte OFFSET = 16;
	}

	public static class Panel {
		// panel dimensions
		public static final short WIDTH = Tile.WIDTH * Tile.HORIZ_SUM; // 32 * 32 -> 1024
		public static final short HEIGHT = Tile.HEIGHT * Tile.VERT_SUM; // 32 * 18 -> 576
	}

	public static class Backgrounds {
		// filenames
		public static final String LVL_1 = Folders.BGS + "level-1.png";
		public static final String LVL_2 = Folders.BGS + "level-2.png";
		public static final String LVL_SELECTION_MENU = Folders.BGS + "level-selection-menu.png";
		public static final String MAIN_MENU = Folders.BGS + "main-menu.png";
		public static final String ABOUT_MENU = Folders.BGS + "about-menu.png";
	}

	public static class TileSets {
		// filenames
		public static final String LVL_1 = Folders.TILESETS + "level-1.png";
		public static final String LVL_2 = Folders.TILESETS + "level-2.png";
	}

	public static class Maps {
		// filenames
		public static final String LVL_1 = Folders.MAPS + "level-1.map";
		public static final String LVL_2 = Folders.MAPS + "level-2.map";
	}

	public static class Animations {
		// ticker divider
		public static final byte TICKER_CYCLE = 10;
	}

	public static class Entities {
		// entity types
		public static final byte PLAYER = 0;
		public static final byte ZOMBIE = 1;

		public static class Player {
			// sprite filename
			public static final String SPRITE = Folders.PLAYER + "player.png";

			// sprite tile dimensions
			public static final short SPRITE_WIDTH = 128;
			public static final short SPRITE_HEIGHT = 128;

			// sprite row and column count
			public static final byte SPRITE_ROWS = 12;
			public static final byte SPRITE_COLS = 7;

			// sprite tile matrix properties
			public static final byte IDLE_TILE_COUNT = 4;
			public static final byte WALK_TILE_COUNT = 7;
			public static final byte RUN_TILE_COUNT = 7;
			public static final byte JUMP_TILE_COUNT = 6;
			public static final byte ATK_1_TILE_COUNT = 5;
			public static final byte ATK_2_TILE_COUNT = 4;
			public static final byte ATK_3_TILE_COUNT = 4;
			public static final byte ATK_RUN_TILE_COUNT = 6;
			public static final byte DEFEND_TILE_COUNT = 5;
			public static final byte PROTECT_TILE_COUNT = 1;
			public static final byte HURT_TILE_COUNT = 2;
			public static final byte DEAD_TILE_COUNT = 6;

			// player direction
			public static final byte DIR_LEFT = 0;
			public static final byte DIR_RIGHT = 1;
			// Temo
			public static final byte DIR_UP = 2;
			public static final byte DIR_DOWN = 3;

			// player states
			public static final byte STATE_COUNT = 12;

			public static final byte IDLE = 0;
			public static final byte WALK = 1;
			public static final byte RUN = 2;
			public static final byte JUMP = 3;
			public static final byte ATK_1 = 4;
			public static final byte ATK_2 = 5;
			public static final byte ATK_3 = 6;
			public static final byte ATK_RUN = 7;
			public static final byte DEFEND = 8;
			public static final byte PROTECT = 9;
			public static final byte HURT = 10;
			public static final byte DEAD = 11;

			// player general stats
			public static final float DEFAULT_HEALTH = 1000f;
			public static final float DEFAULT_WALK_SPEED = 1.5f;
			public static final float DEFAULT_RUN_SPEED = DEFAULT_WALK_SPEED * 2.5f;
			public static final float DEFAULT_JUMP_HEIGHT = Tile.HEIGHT * 2.5f;

			public static final int HITBOX_INITIAL_WIDTH = 80;
			public static final int HITBOX_INITIAL_HEIGHT = 96;
			public static final int HITBOX_X_OFFSET = 48; // original = 48 (for full body hitbox)
			public static final int HITBOX_Y_OFFSET = 33; // original = 32 (for full body hitbox)
			// we made this so the player can zip through 2 tiles separated by a gap of
			// player sprite height

			public static final int HITBOX_WIDTH = HITBOX_INITIAL_WIDTH - HITBOX_X_OFFSET;
			public static final int HITBOX_HEIGHT = HITBOX_INITIAL_HEIGHT - HITBOX_Y_OFFSET;
		}

		public static class Enemies {

			public static class Zombie {
				// sprite filename
				public static final String SPRITE = Folders.ENEMIES + "zombie.png";

				// sprite tile dimensions
				public static final short SPRITE_WIDTH = 96;
				public static final short SPRITE_HEIGHT = 96;

				// sprite row and column count
				public static final byte SPRITE_ROWS = 10;
				public static final byte SPRITE_COLS = 11;

				// sprite tile matrix properties
				public static final byte IDLE_TILE_COUNT = 9;
				public static final byte WALK_TILE_COUNT = 10;
				public static final byte RUN_TILE_COUNT = 8;
				public static final byte JUMP_TILE_COUNT = 6;
				public static final byte ATK_1_TILE_COUNT = 4;
				public static final byte ATK_2_TILE_COUNT = 4;
				public static final byte ATK_3_TILE_COUNT = 4;
				public static final byte EATING_TILE_COUNT = 11;
				public static final byte HURT_TILE_COUNT = 5;
				public static final byte DEAD_TILE_COUNT = 5;

				// zombie states
				public static final byte STATE_COUNT = 10;

				public static final byte IDLE = 0;
				public static final byte WALK = 1;
				public static final byte RUN = 2;
				public static final byte JUMP = 3;
				public static final byte ATK_1 = 4;
				public static final byte ATK_2 = 5;
				public static final byte ATK_3 = 6;
				public static final byte EATING = 7;
				public static final byte HURT = 8;
				public static final byte DEAD = 9;

				// zombie general stats
				public static final float DEFAULT_HEALTH = 100f;
				public static final float DEFAULT_WALK_SPEED = 1.5f;
				public static final float DEFAULT_RUN_SPEED = DEFAULT_WALK_SPEED * 2.5f;
				public static final float DEFAULT_JUMP_HEIGHT = Tile.HEIGHT * 2.5f;

				public static final int HITBOX_INITIAL_WIDTH = 80;
				public static final int HITBOX_INITIAL_HEIGHT = 96;
				public static final int HITBOX_X_OFFSET = 48; // original = 48 (for full body hitbox)
				public static final int HITBOX_Y_OFFSET = 33; // original = 32 (for full body hitbox)

				public static final int HITBOX_WIDTH = HITBOX_INITIAL_WIDTH - HITBOX_X_OFFSET;
				public static final int HITBOX_HEIGHT = HITBOX_INITIAL_HEIGHT - HITBOX_Y_OFFSET;
			}
		}
	}
}
