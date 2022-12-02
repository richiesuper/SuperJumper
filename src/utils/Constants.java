package utils;

public class Constants {
	public static class Folders {
		// filenames
		public static final String BGS = "/bgimages/";
		public static final String PLAYER = "/entities/player/";
		public static final String ENEMIES = "/entities/enemies/";
		public static final String MAPS = "/maps/";
		public static final String TILESETS = "/tilesets/";
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
		// types of game states
		public static final byte MAIN_MENU = 0;
		public static final byte PAUSE_MENU = 1;
		public static final byte OPTIONS_MENU = 2;
		public static final byte GAME_OVER = 3;
		public static final byte GAME_FINISH = 4;
		public static final byte LEVEL_SELECTION = 5;
		public static final byte LVL_1 = 6;
		public static final byte LVL_2 = 7;
	}

	public static class Tile {
		// tile dimensions
		public static final byte WIDTH = 32;
		public static final byte HEIGHT = 32;
		// tile count
		public static final byte HORIZ_SUM = 32;
		public static final byte VERT_SUM = 18;
	}

	public static class Panel {
		// panel dimensions
		public static final short WIDTH = Tile.WIDTH * Tile.HORIZ_SUM;
		public static final short HEIGHT = Tile.HEIGHT * Tile.VERT_SUM;
	}

	public static class Backgrounds {
		// filenames
		public static final String LVL_1 = Folders.BGS + "level-1.png";
		public static final String LVL_2 = Folders.BGS + "level-2.png";
		public static final String LVL_SELECTION_MENU = Folders.BGS + "level-selection-menu.png";
		public static final String MAIN_MENU = Folders.BGS + "main-menu.png";
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
		public static final byte ZOMBIE = 0;

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

			// player states
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

			// player stats
			public static final float DEFAULT_HEALTH = 1000f;
			public static final float DEFAULT_WALK_SPEED = 0.5f;
			public static final float DEFAULT_RUN_SPEED = DEFAULT_WALK_SPEED * 4f;
			public static final float DEFAULT_JUMP_HEIGHT = Tile.HEIGHT * 2.5f;
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
			}
		}
	}
}
