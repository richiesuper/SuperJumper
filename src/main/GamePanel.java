package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInput;
import inputs.MouseInput;
import tilemap.Background;
import utils.Constants;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private Background bg;
	private BufferedImage spriteSheet;
	private BufferedImage[][] spriteTile;
	private int ticker, idx;
	
	// Tambah
	private int playerDir;
	private int playerAction;
	private float xDelta, yDelta;
	private static final int IDLE = 0;
	private static final int RUNNING = 1;
	private boolean moving;
	
	// Temp
	public static final int LEFT = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;

	public GamePanel(int width, int height) {
		super();
		
		// Add
		this.playerDir = -1;
		this.moving = false;
		this.playerAction = IDLE;
		this.xDelta = 100;
		this.yDelta = 100;
		
		
		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);
		this.spriteTile = new BufferedImage[12][7];

		try {
			bg = new Background(Constants.Backgrounds.LVL_1);
			InputStream is = getClass().getResourceAsStream(Constants.Entities.Player.SPRITE);
			this.spriteSheet = ImageIO.read(is);

			for (int i = 0; i < Constants.Entities.Player.SPRITE_ROWS; i++)
				for (int j = 0; j < Constants.Entities.Player.SPRITE_COLS; j++)
					this.spriteTile[i][j] = this.spriteSheet.getSubimage(j * Constants.Entities.Player.SPRITE_WIDTH,
							i * Constants.Entities.Player.SPRITE_HEIGHT,
							Constants.Entities.Player.SPRITE_WIDTH,
							Constants.Entities.Player.SPRITE_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);

		ticker = 0;
		idx = 0;
	}
	
	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void setAnimation() {
		if(moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

	}
	
	private void updatePos() {
		if(moving) {
			switch(playerDir) {
				case LEFT:
					xDelta -= 5;
					break;
				case UP:
					yDelta -= 5;
					break;
				case RIGHT:
					xDelta += 5;
					break;
				case DOWN:
					yDelta += 5;
					break;
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ticker++;
		
		bg.draw(g);
		setAnimation();
		updatePos();
		if(playerAction == IDLE) {
			g.drawImage(spriteTile[playerAction][idx], (int) xDelta, (int) yDelta, Constants.Entities.Player.SPRITE_WIDTH, Constants.Entities.Player.SPRITE_HEIGHT, null);

			if (ticker % 10 == 0) {
				ticker = 0;

				if (idx >= 3) {
					idx = 0;
				}

				idx++;
			}	
		}
		else {
			g.drawImage(spriteTile[playerAction][idx], (int) xDelta, (int) yDelta, Constants.Entities.Player.SPRITE_WIDTH, Constants.Entities.Player.SPRITE_HEIGHT, null);
			
			if (ticker % 10 == 0) {
				ticker = 0;
				
				if (idx >= 6) {
					idx = 0;
				}
				
				idx++;
			}
		}
	}
}
