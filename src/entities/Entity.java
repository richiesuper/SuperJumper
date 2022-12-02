package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.Constants;

public abstract class Entity {
	// intrinsic traits
	protected float x;
	protected float y;
	protected byte state;
	protected byte direction;
	protected float speedX;
	protected float health;
	protected float maxHealth;
	protected float attackDmg;
	protected boolean moving;
	protected boolean running;
	protected boolean jumping;
	protected byte ticker;
	protected byte idx;
	protected byte[] tileColCount;

	// dimensions
	protected short width;
	protected short height;

	// assets
	protected BufferedImage spriteSheet;
	protected BufferedImage spriteTile[][];

	// constructor
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}

	// abstract methods
	public abstract void init();

	public abstract void setupTileCount();

	public abstract void loadSprite();

	public abstract void draw(Graphics g);

	public abstract void move();

	public abstract void update();

	public abstract void attack();

	public abstract void die();

	// concrete methods
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getAttackDmg() {
		return attackDmg;
	}

	public void setAttackDmg(float attackDmg) {
		this.attackDmg = attackDmg;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public byte getTicker() {
		return ticker;
	}

	public void setTicker(byte ticker) {
		this.ticker = ticker;
	}

	public byte getIdx() {
		return idx;
	}

	public void setIdx(byte idx) {
		this.idx = idx;
	}

	public byte[] getTileColCount() {
		return tileColCount;
	}

	public void setTileColCount(byte[] tileColCount) {
		this.tileColCount = tileColCount;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public BufferedImage[][] getSpriteTile() {
		return spriteTile;
	}

	public void setSpriteTile(BufferedImage[][] spriteTile) {
		this.spriteTile = spriteTile;
	}

	public void updateTicker() {
		if (ticker % Constants.Animations.TICKER_CYCLE == 0) {
			ticker = 0;

			if (idx >= tileColCount[state] - 1) {
				idx = 0;
			} else {
				idx++;
			}
		}

		ticker++;
	}
}