package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.Constants;

public class Player extends Entity {

	private boolean goingLeft;
	private boolean goingRight;
	private float speedY;

	public Player(float x, float y) {
		super(x, y);
		init();
	}

	@Override
	public void init() {
		// default values
		state = Constants.Entities.Player.IDLE;
		speedX = Constants.Entities.Player.DEFAULT_WALK_SPEED;
		maxHealth = Constants.Entities.Player.DEFAULT_HEALTH;
		health = maxHealth;
		width = Constants.Entities.Player.SPRITE_WIDTH;
		height = Constants.Entities.Player.SPRITE_HEIGHT;
		ticker = (byte) 0;
		idx = (byte) 0;

		// sprite loading
		loadSprite();
		setupTileCount();
	}

	@Override
	public void setupTileCount() {
		tileColCount = new byte[Constants.Entities.Player.SPRITE_HEIGHT];
		tileColCount[0] = Constants.Entities.Player.IDLE_TILE_COUNT;
		tileColCount[1] = Constants.Entities.Player.WALK_TILE_COUNT;
		tileColCount[2] = Constants.Entities.Player.RUN_TILE_COUNT;
		tileColCount[3] = Constants.Entities.Player.JUMP_TILE_COUNT;
		tileColCount[4] = Constants.Entities.Player.ATK_1_TILE_COUNT;
		tileColCount[5] = Constants.Entities.Player.ATK_2_TILE_COUNT;
		tileColCount[6] = Constants.Entities.Player.ATK_3_TILE_COUNT;
		tileColCount[7] = Constants.Entities.Player.ATK_RUN_TILE_COUNT;
		tileColCount[8] = Constants.Entities.Player.DEFEND_TILE_COUNT;
		tileColCount[9] = Constants.Entities.Player.PROTECT_TILE_COUNT;
		tileColCount[10] = Constants.Entities.Player.HURT_TILE_COUNT;
		tileColCount[11] = Constants.Entities.Player.DEAD_TILE_COUNT;
	}

	@Override
	public void move() {
		if (moving) {
			switch (direction) {
			case Constants.Entities.Player.DIR_LEFT:
				x -= speedX;
				break;
			case Constants.Entities.Player.DIR_RIGHT:
				x += speedX;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void update() {
		switch (state) {
		case Constants.Entities.Player.WALK:
			speedX = Constants.Entities.Player.DEFAULT_WALK_SPEED;
			break;
		case Constants.Entities.Player.RUN:
			speedX = Constants.Entities.Player.DEFAULT_RUN_SPEED;
			break;
		case Constants.Entities.Player.JUMP:
			speedY = Constants.Entities.Player.DEFAULT_JUMP_HEIGHT;
			break;
		case Constants.Entities.Player.IDLE:
		case Constants.Entities.Player.ATK_1:
		case Constants.Entities.Player.ATK_2:
		case Constants.Entities.Player.ATK_3:
		case Constants.Entities.Player.ATK_RUN:
		case Constants.Entities.Player.DEFEND:
		case Constants.Entities.Player.PROTECT:
		case Constants.Entities.Player.HURT:
		case Constants.Entities.Player.DEAD:
			speedX = 0;
			break;
		default:
			break;
		}

		move();
	}

	@Override
	public void attack() {
	}

	@Override
	public void die() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(spriteTile[state][idx], (int) x, (int) y, Constants.Entities.Player.SPRITE_WIDTH,
				Constants.Entities.Player.SPRITE_HEIGHT, null);

		updateTicker();
	}

	@Override
	public void loadSprite() {
		// get the input stream
		InputStream is = getClass().getResourceAsStream(Constants.Entities.Player.SPRITE);

		// load image
		try {
			spriteSheet = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load individual tiles into array
		spriteTile = new BufferedImage[Constants.Entities.Player.SPRITE_ROWS][Constants.Entities.Player.SPRITE_COLS];

		for (int i = 0; i < Constants.Entities.Player.SPRITE_ROWS; i++)
			for (int j = 0; j < Constants.Entities.Player.SPRITE_COLS; j++)
				spriteTile[i][j] = spriteSheet.getSubimage(j * Constants.Entities.Player.SPRITE_WIDTH,
						i * Constants.Entities.Player.SPRITE_HEIGHT, Constants.Entities.Player.SPRITE_WIDTH,
						Constants.Entities.Player.SPRITE_HEIGHT);
	}

	public void halt() {
		moving = false;
		goingLeft = false;
		goingRight = false;
		state = Constants.Entities.Player.IDLE;
	}
}
