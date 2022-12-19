package entities;

import java.awt.Graphics;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import tilemap.TileMap;
import utils.Constants;
import static utils.Helpers.*;

public class Zombie extends Enemy {

	private float gravity;
	private float floatSpeed;
	private boolean floating;
	private int stateTicker;
	private int stateTickerCycle;

	public Zombie(float x, float y, short width, short height, TileMap tileMap, Player player) {
		super(x, y, width, height, tileMap);
		init();
		initHitbox(x, y, Constants.Entities.Enemies.Zombie.HITBOX_WIDTH,
				Constants.Entities.Enemies.Zombie.HITBOX_HEIGHT);
		
		facingLeft = true;
		facingRight = false;
		
		gravity = 0.01f;
		floatSpeed = 0f;
		floating = false;
		
		stateTicker = 0;
		stateTickerCycle = 200;
		
		this.player = player;
	}

	@Override
	public void init() {
		// default values
		state = Constants.Entities.Enemies.Zombie.IDLE;
		speedX = Constants.Entities.Enemies.Zombie.DEFAULT_WALK_SPEED;
		maxHealth = Constants.Entities.Enemies.Zombie.DEFAULT_HEALTH;
		health = maxHealth;
		width = Constants.Entities.Enemies.Zombie.SPRITE_WIDTH;
		height = Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT;
		ticker = (byte) 0;
		idx = (byte) 0;
		facingRight = true;
		facingLeft = false;

		// sprite loading
		loadSprite();
		setupTileCount();
	}

	@Override
	public void setupTileCount() {
		tileColCount = new byte[Constants.Entities.Enemies.Zombie.STATE_COUNT];
		tileColCount[0] = Constants.Entities.Enemies.Zombie.IDLE_TILE_COUNT;
		tileColCount[1] = Constants.Entities.Enemies.Zombie.WALK_TILE_COUNT;
		tileColCount[2] = Constants.Entities.Enemies.Zombie.RUN_TILE_COUNT;
		tileColCount[3] = Constants.Entities.Enemies.Zombie.JUMP_TILE_COUNT;
		tileColCount[4] = Constants.Entities.Enemies.Zombie.ATK_1_TILE_COUNT;
		tileColCount[5] = Constants.Entities.Enemies.Zombie.ATK_2_TILE_COUNT;
		tileColCount[6] = Constants.Entities.Enemies.Zombie.ATK_3_TILE_COUNT;
		tileColCount[7] = Constants.Entities.Enemies.Zombie.EATING_TILE_COUNT;
		tileColCount[8] = Constants.Entities.Enemies.Zombie.HURT_TILE_COUNT;
		tileColCount[9] = Constants.Entities.Enemies.Zombie.DEAD_TILE_COUNT;
	}

	@Override
	public void loadSprite() {
		// get the input stream
		InputStream is = getClass().getResourceAsStream(Constants.Entities.Enemies.Zombie.SPRITE);

		// load image
		try {
			spriteSheet = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load individual tiles into array
		spriteTile = new BufferedImage[Constants.Entities.Enemies.Zombie.SPRITE_ROWS][Constants.Entities.Enemies.Zombie.SPRITE_COLS];

		for (int i = 0; i < Constants.Entities.Enemies.Zombie.SPRITE_ROWS; i++)
			for (int j = 0; j < Constants.Entities.Enemies.Zombie.SPRITE_COLS; j++) {
				spriteTile[i][j] = spriteSheet.getSubimage(j * Constants.Entities.Enemies.Zombie.SPRITE_WIDTH,
						i * Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT,
						Constants.Entities.Enemies.Zombie.SPRITE_WIDTH,
						Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT);
			}
	}

	@Override
	public void move() {
		// Temp
		float tempSpeedX = 0;
		
		if (moving) {
			if (facingLeft && !facingRight) {
				// Temp
				tempSpeedX = -speedX;

				facingLeft = true;
				facingRight = false;
			} else if (facingRight && !facingLeft) {
				
				// Temp
				tempSpeedX = speedX;

				facingRight = true;
				facingLeft = false;
			}
		}

		if (!floating && !isEntityOnFloor(x, y, tileMap.getMap(), true, hitbox, tileMap)) {
			floating = true;
		}

		if (floating) {
			//System.out.println("floating");
			updateXandYPos(tempSpeedX);
		} else {
			//System.out.println("not floating");

			if (isEnemyOnFloor(x, y, tileMap.getMap(), true, hitbox, tileMap))
				updateXPos(tempSpeedX);
		}
	}

	private void resetFloating() {
		floating = false;
		floatSpeed = 0f;
		setState(Constants.Entities.Enemies.Zombie.IDLE);
	}

	private void updateXandYPos(float tempSpeedX) {
		if (canMoveHere(x, y + floatSpeed, hitbox.width, hitbox.height, tileMap.getMap(), true,
				hitbox, tileMap)) {
			hitbox.y += floatSpeed;
			y += floatSpeed;
		} else {
			// if we have landed...
			if (floatSpeed > 0) {
				resetFloating();
			}
		}
		
		floatSpeed += gravity;
		updateXPos(tempSpeedX);
	}

	private void updateXPos(float tempSpeedX) {
		if (canMoveHere(x + tempSpeedX, y, hitbox.width, hitbox.height, tileMap.getMap(),
				true, hitbox, tileMap)) {
			x += tempSpeedX;
			hitbox.x = (float) (x + tileMap.getX());
		}
	}

	@Override
	public void draw(Graphics g) {
		if (facingRight && !facingLeft) {
			g.drawImage(spriteTile[state][idx],
					(int) (x + tileMap.getX() - Constants.Entities.Enemies.Zombie.HITBOX_X_OFFSET),
					(int) (y + tileMap.getY() + Constants.Entities.Enemies.Zombie.HITBOX_Y_OFFSET),
					Constants.Entities.Enemies.Zombie.SPRITE_WIDTH, Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT, null);
		} else {
			g.drawImage(spriteTile[state][idx],
					(int) (x + tileMap.getX() - Constants.Entities.Enemies.Zombie.HITBOX_X_OFFSET + width + 32),
					(int) (y + tileMap.getY() + Constants.Entities.Enemies.Zombie.HITBOX_Y_OFFSET),
					-Constants.Entities.Enemies.Zombie.SPRITE_WIDTH, Constants.Entities.Enemies.Zombie.SPRITE_HEIGHT, null);
		}

		updateTicker();
		//drawHitbox(g);

		// debugging
		// System.out.println("x: " + x + " y: " + y);
		// System.out.println("hb-x: " + hitbox.x + " hb-y: " + hitbox.y);

	}

	@Override
	public void update() {
		stateTicker++;
		if (stateTicker >= stateTickerCycle) {
			stateTicker = 0;
			
			if (facingLeft && !facingRight) {
				System.out.println("left");
			} else if (facingRight && !facingLeft) {
				System.out.println("right");
			}
			if (state == Constants.Entities.Enemies.Zombie.IDLE) {
				if (facingLeft && !facingRight) {
					facingLeft = false;
					facingRight = true;
				} else if (facingRight && !facingLeft){
					facingRight = false;
					facingLeft = true;
				}
				moving = true;
				state = Constants.Entities.Enemies.Zombie.WALK;
			} else {
				moving = false;
				state = Constants.Entities.Enemies.Zombie.IDLE;
			}
		}

		switch (state) {
		case Constants.Entities.Enemies.Zombie.WALK:
			speedX = Constants.Entities.Enemies.Zombie.DEFAULT_WALK_SPEED;
			speedY = Constants.Entities.Enemies.Zombie.DEFAULT_WALK_SPEED;
			break;
		case Constants.Entities.Enemies.Zombie.RUN:
			speedX = Constants.Entities.Enemies.Zombie.DEFAULT_RUN_SPEED;
			speedY = Constants.Entities.Enemies.Zombie.DEFAULT_RUN_SPEED;
			break;
		case Constants.Entities.Enemies.Zombie.JUMP:
			speedY = Constants.Entities.Enemies.Zombie.DEFAULT_JUMP_HEIGHT;
			break;
		case Constants.Entities.Enemies.Zombie.IDLE:
		case Constants.Entities.Enemies.Zombie.ATK_1:
		case Constants.Entities.Enemies.Zombie.ATK_2:
		case Constants.Entities.Enemies.Zombie.ATK_3:
		case Constants.Entities.Enemies.Zombie.EATING:
		case Constants.Entities.Enemies.Zombie.HURT:
		case Constants.Entities.Enemies.Zombie.DEAD:
			speedX = 0;
			break;
		default:
			break;
		}

		move();
	}

	@Override
	public void attack() {
		if (facingRight) {
			if (player.x >= x && player.x <= x + 5) {
				player.setHealth(player.getHealth() - 0.001f);
			}
		}
				
		if (facingLeft) {
			if (player.x <= x && player.x >= x - 5) {
				player.setHealth(player.getHealth() - 0.001f);
			}
		}
	}

	@Override
	public void die() {
	}

}
