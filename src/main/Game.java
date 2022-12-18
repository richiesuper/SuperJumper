package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamestates.GameStateManager;
import gamestates.MainMenuState;
import utils.Constants;
import inputs.KeyboardInput.*;

public class Game implements Runnable {
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Thread gameThread;
	private GameStateManager gsm;
	private MainMenuState menu;

	// image
	private Graphics2D g;
	private BufferedImage img;

	public Game() {
		this.gsm = new GameStateManager();
		this.gamePanel = new GamePanel(Constants.Panel.WIDTH, Constants.Panel.HEIGHT, gsm);
		this.gameFrame = new GameFrame(Constants.Game.TITLE, this.gamePanel);
		this.gamePanel.requestFocus();

		startGameLoop();
	}

	private void startGameLoop() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		while (true) {
			//System.out.println(gamePanel.getPause());
			if(gamePanel.getPause()) {
				try {
					gamePanel.repaint();
					Thread.sleep(1000);
					continue;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			gamePanel.repaint();
			
			try {
				Thread.sleep((long) Constants.Game.FRAME_PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
