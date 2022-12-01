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
		public static final int MS_IN_S = 1000;
		// frame per second
		public static final int FPS = 60;
		// period per frame in ms
		public static final int FRAME_PERIOD = MS_IN_S / FPS;
	}
	
	public static class GameStates {
		// types of game states
		public static final int MAIN_MENU = 0;
		public static final int PAUSE_MENU = 1;
		public static final int OPTIONS_MENU = 2;
		public static final int GAME_OVER = 3;
		public static final int GAME_FINISH = 4;
		public static final int LEVEL_SELECTION = 5;
		public static final int LVL_1 = 6;
		public static final int LVL_2 = 7;
	}

	public static class Tile {
		// tile dimensions
		public static final int WIDTH = 32;
		public static final int HEIGHT = 32;
		// tile count
		public static final int HORIZ_SUM = 32;
		public static final int VERT_SUM = 18;
	}

	public static class Panel {
		// panel dimensions
		public static final int WIDTH = Tile.WIDTH * Tile.HORIZ_SUM;
		public static final int HEIGHT = Tile.HEIGHT * Tile.VERT_SUM;
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

	public static class Entities {
		public static class Player {
			// sprite filename
			public static final String SPRITE = Folders.PLAYER + "player.png";
			
			// sprite tile dimensions
			public static final int SPRITE_WIDTH = 128;
			public static final int SPRITE_HEIGHT = 128;
			
			// sprite row and column count
			public static final int SPRITE_ROWS = 12;
			public static final int SPRITE_COLS = 7;
			
			// sprite tile matrix properties
			public static final int IDLE_TILE_COUNT = 4;
			public static final int WALK_TILE_COUNT = 7;
			public static final int RUN_TILE_COUNT = 7;
			public static final int JUMP_TILE_COUNT = 6;
			public static final int ATK_1_TILE_COUNT = 5;
			public static final int ATK_2_TILE_COUNT = 4;
			public static final int ATK_3_TILE_COUNT = 4;
			public static final int ATK_RUN_TILE_COUNT = 6;
			public static final int DEFEND_TILE_COUNT = 5;
			public static final int PROTECT_TILE_COUNT = 1;
			public static final int HURT_TILE_COUNT = 2;
			public static final int DEAD_TILE_COUNT = 6;

			// player states
			public static final int IDLE = 0;
			public static final int WALK = 1;
			public static final int RUN = 2;
			public static final int JUMP = 3;
			public static final int ATK_1 = 4;
			public static final int ATK_2 = 5;
			public static final int ATK_3 = 6;
			public static final int ATK_RUN = 7;
			public static final int DEFEND = 8;
			public static final int PROTECT = 9;
			public static final int HURT = 10;
			public static final int DEAD = 11;
		}

		public static class Enemies {
			public static class Zombie {
				// sprite filename
				public static final String SPRITE = Folders.ENEMIES + "zombie.png";

				// sprite tile dimensions
				public static final int SPRITE_WIDTH = 96;
				public static final int SPRITE_HEIGHT = 96;

				// sprite row and column count
				public static final int SPRITE_ROWS = 10;
				public static final int SPRITE_COLS = 11;

				// sprite tile matrix properties
				public static final int IDLE_TILE_COUNT = 9;
				public static final int WALK_TILE_COUNT = 10;
				public static final int RUN_TILE_COUNT = 8;
				public static final int JUMP_TILE_COUNT = 6;
				public static final int ATK_1_TILE_COUNT = 4;
				public static final int ATK_2_TILE_COUNT = 4;
				public static final int ATK_3_TILE_COUNT = 4;
				public static final int EATING_TILE_COUNT = 11;
				public static final int HURT_TILE_COUNT = 5;
				public static final int DEAD_TILE_COUNT = 5;

				// zombie states
				public static final int IDLE = 0;
				public static final int WALK = 1;
				public static final int RUN = 2;
				public static final int JUMP = 3;
				public static final int ATK_1 = 4;
				public static final int ATK_2 = 5;
				public static final int ATK_3 = 6;
				public static final int EATING = 7;
				public static final int HURT = 8;
				public static final int DEAD = 9;
			}
		}
	}
}
