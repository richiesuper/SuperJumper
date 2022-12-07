package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import tilemap.TileMap;
import utils.Constants;
import utils.Constants.Tile;

public abstract class Entity {
	// intrinsic traits
	protected float x;
	protected float y;
	protected byte state;
	protected byte direction;
	protected float speedX;
	protected float speedY;
	protected float health;
	protected float maxHealth;
	protected float attackDmg;
	protected boolean moving;
	protected boolean running;
	protected boolean jumping;
	protected byte ticker;
	protected byte idx;
	protected byte[] tileColCount;
	
	// Animation
	protected boolean facingRight;
	protected boolean facingLeft;
	protected Player player;

	// dimensions
	protected short width;
	protected short height;

	// assets
	protected BufferedImage spriteSheet;
	protected BufferedImage spriteTile[][];
	protected int[][] lvlData;
	
	// Hitbox for testing collision
	protected Rectangle2D.Float hitbox;
	
	// Temp for hitbox
	protected float xOffset = 48;
	protected float yOffset = 32;
	
	/* Map Object */ 
	
	// TileStuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMap;
	protected double yMap;
	
	// Position and vector
	protected double xPos;
	protected double yPos;
	protected double dx;
	protected double dy;
	
	// Collision Box
	protected int cWidth = 20;
	protected int cHeight = 20;
	
	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	// constructor
	public Entity(float x, float y, short width, short height, TileMap tileMap) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		// Tile
		this.tileMap = tileMap;
		this.tileSize = tileMap.getTileWidth(); // Get the size of the tile
	}
	
	// Hitbox
	protected void initHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}
	
	protected Rectangle2D.Float getHitbox() {
		return hitbox;
	}
	
	protected void drawHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}
	
	public float getHitboxX() {
		return hitbox.x;
	}
	
	public float getHitboxY() {
		return hitbox.y;
	}
	
	// MapObject
	public void calculateCorners(double x, double y) {
		int leftTile = (int) (x - cWidth / 2) / tileSize;
		int rightTile = (int) (x + cWidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cHeight / 2) / tileSize;
		int bottomTile = (int) (y + cHeight / 2 - 1) / tileSize;
		
		// Check tiles normal or blocked
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
	}
	
	public void checkTileMapCollision() {
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;

		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cHeight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				ytemp = (currRow + 1) * tileSize - cHeight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cWidth / 2;
			}
			else {
				xtemp += dx;
			}	
		}
		
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cWidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setMapPosition() {
		xMap = tileMap.getX();
		yMap = tileMap.getY();
	}
	
	public void setPosition(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	// abstract methods
	public abstract void init();

	public abstract void setupTileCount();

	public abstract void loadSprite();

	public abstract void move();
	
	public abstract void draw(Graphics g);

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
	
	public boolean getFacingRight() {
		return facingRight;
	}
	
	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
	
	public boolean getFacingLeft() {
		return facingLeft;
	}
	
	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
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