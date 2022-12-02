package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
	// intrinsic traits
	private float x;
	private float y;
	private byte state;
	private byte direction;
	private float speed;
	private float health;
	private float maxHealth;
	private float attackDmg;
	private boolean moving;
	private boolean running;
	private boolean jumping;
	private byte ticker;
	private byte idx;
	private byte[] tileColCount;

	// dimensions
	private short width;
	private short height;

	// assets
	private BufferedImage spriteSheet;
	private BufferedImage spriteTile[][];

	// constructor
	public Entity(float x, float y) {
		setX(x);
		setY(y);
	}

	// abstract methods
	public abstract void init();

	public abstract void setupTileCount();

	public abstract void loadSprite();

	public abstract void draw(Graphics g);

	public abstract void move();

	public abstract void update();

	public abstract void computeHealth();

	public abstract void attack();

	public abstract void die();

	public abstract void jump();

	public abstract void run();

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

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
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

	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public BufferedImage[][] getSpriteTile() {
		return spriteTile;
	}

	public void setSpriteTile(BufferedImage[][] spriteTile) {
		this.spriteTile = spriteTile;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
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

	public byte[] getTileColCount() {
		return tileColCount;
	}

	public void setTileColCount(byte[] tileCount) {
		this.tileColCount = tileCount;
	}
}
