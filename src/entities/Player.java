package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.Constants;

public class Player extends Entity {

	public Player(float x, float y) {
		super(x, y);

		init();
	}

	@Override
	public void init() {
		// default values
		setState(Constants.Entities.Player.IDLE);
		setSpeed(Constants.Entities.Player.DEFAULT_WALK_SPEED);
		setMaxHealth(Constants.Entities.Player.DEFAULT_HEALTH);
		setHealth(getMaxHealth());
		setWidth(Constants.Entities.Player.SPRITE_WIDTH);
		setHeight(Constants.Entities.Player.SPRITE_HEIGHT);
		setTicker((byte) 0);
		setIdx((byte) 0);

		loadSprite();
		setupTileCount();
	}

	@Override
	public void setupTileCount() {
		setTileColCount(new byte[Constants.Entities.Player.SPRITE_HEIGHT]);
		getTileColCount()[0] = Constants.Entities.Player.IDLE_TILE_COUNT;
		getTileColCount()[1] = Constants.Entities.Player.WALK_TILE_COUNT;
		getTileColCount()[2] = Constants.Entities.Player.RUN_TILE_COUNT;
		getTileColCount()[3] = Constants.Entities.Player.JUMP_TILE_COUNT;
		getTileColCount()[4] = Constants.Entities.Player.ATK_1_TILE_COUNT;
		getTileColCount()[5] = Constants.Entities.Player.ATK_2_TILE_COUNT;
		getTileColCount()[6] = Constants.Entities.Player.ATK_3_TILE_COUNT;
		getTileColCount()[7] = Constants.Entities.Player.ATK_RUN_TILE_COUNT;
		getTileColCount()[8] = Constants.Entities.Player.DEFEND_TILE_COUNT;
		getTileColCount()[9] = Constants.Entities.Player.PROTECT_TILE_COUNT;
		getTileColCount()[10] = Constants.Entities.Player.HURT_TILE_COUNT;
		getTileColCount()[11] = Constants.Entities.Player.DEAD_TILE_COUNT;
	}

	@Override
	public void move() {
		if (isMoving()) {
			switch (getDirection()) {
			case Constants.Entities.Player.DIR_LEFT:
				setX(getX() - getSpeed());
				break;
			case Constants.Entities.Player.DIR_RIGHT:
				setX(getX() + getSpeed());
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void update() {
		switch (getState()) {
		case Constants.Entities.Player.IDLE:
			setSpeed(0);
			break;
		case Constants.Entities.Player.WALK:
			setSpeed(Constants.Entities.Player.DEFAULT_WALK_SPEED);
			break;
		case Constants.Entities.Player.RUN:
			setSpeed(Constants.Entities.Player.DEFAULT_RUN_SPEED);
			break;
		case Constants.Entities.Player.JUMP:
			setSpeed(0);
			break;
		case Constants.Entities.Player.ATK_1:
			setSpeed(0);
			break;
		case Constants.Entities.Player.ATK_2:
			setSpeed(0);
			break;
		case Constants.Entities.Player.ATK_3:
			setSpeed(0);
			break;
		case Constants.Entities.Player.ATK_RUN:
			setSpeed(0);
			break;
		case Constants.Entities.Player.DEFEND:
			setSpeed(0);
			break;
		case Constants.Entities.Player.PROTECT:
			setSpeed(0);
			break;
		case Constants.Entities.Player.HURT:
			setSpeed(0);
			break;
		case Constants.Entities.Player.DEAD:
			setSpeed(0);
			break;
		}

		move();
	}

	@Override
	public void computeHealth() {
	}

	@Override
	public void attack() {
	}

	@Override
	public void die() {
	}

	@Override
	public void jump() {
	}

	@Override
	public void draw(Graphics g) {
		setTicker((byte) (getTicker() + 1));

		g.drawImage(getSpriteTile()[getState()][getIdx()], (int) getX(), (int) getY(),
				Constants.Entities.Player.SPRITE_WIDTH, Constants.Entities.Player.SPRITE_HEIGHT, null);

		if (getTicker() % Constants.Animations.TICKER_CYCLE == 0) {
			setTicker((byte) 0);

			if (getIdx() >= getTileColCount()[getState()] - 1) {
				setIdx((byte) 0);
			} else {
				setIdx((byte) (getIdx() + 1));
			}
		}
	}

	@Override
	public void loadSprite() {
		// get the input stream
		InputStream is = getClass().getResourceAsStream(Constants.Entities.Player.SPRITE);

		try {
			setSpriteSheet(ImageIO.read(is));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setSpriteTile(new BufferedImage[Constants.Entities.Player.SPRITE_ROWS][Constants.Entities.Player.SPRITE_COLS]);

		for (int i = 0; i < Constants.Entities.Player.SPRITE_ROWS; i++)
			for (int j = 0; j < Constants.Entities.Player.SPRITE_COLS; j++)
				getSpriteTile()[i][j] = getSpriteSheet().getSubimage(j * Constants.Entities.Player.SPRITE_WIDTH,
						i * Constants.Entities.Player.SPRITE_HEIGHT, Constants.Entities.Player.SPRITE_WIDTH,
						Constants.Entities.Player.SPRITE_HEIGHT);
	}

	@Override
	public void run() {
	}
}
