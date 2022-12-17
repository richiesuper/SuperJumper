package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamestates.GameStateManager;
import gamestates.MainMenuState;
import utils.Constants;

public class Game implements Runnable {
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Thread gameThread;
	private GameStateManager gsm;
	private MainMenuState menu;
	private boolean running;
	
	// image
	private Graphics2D g;
	private BufferedImage img;

	public Game() {
		this.gamePanel = new GamePanel(Constants.Panel.WIDTH, Constants.Panel.HEIGHT);
		this.gameFrame = new GameFrame(Constants.Game.TITLE, this.gamePanel);
		this.gamePanel.requestFocus();
		
		startGameLoop();
	}

	private void startGameLoop() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}
	
	public void init() {
		img = new BufferedImage(Constants.Panel.WIDTH, Constants.Panel.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) img.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}

	@Override
	public void run() {
		init();
		while (running) {
			gamePanel.repaint();
			
			update();
			draw();
			drawToScreen();
			
			try {
				Thread.sleep((long) Constants.Game.FRAME_PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void drawToScreen() {
		Graphics g2 = img.getGraphics();
		g2.drawImage(img, 0, 0, Constants.Panel.WIDTH, Constants.Panel.HEIGHT, null);
		g2.dispose();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void update() {
		gsm.update();
	}
}
