package main;

import gamestates.GameStateManager;
import utils.Constants;

public class Game implements Runnable {
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Thread gameThread;
	private GameStateManager gsm;

	public Game() {
		this.gamePanel = new GamePanel(Constants.Panel.WIDTH, Constants.Panel.HEIGHT);
		this.gameFrame = new GameFrame(Constants.Game.TITLE, this.gamePanel);
		this.gamePanel.requestFocus();
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		init();
		while (true) {
			gamePanel.repaint();

			try {
				Thread.sleep((long) Constants.Game.FRAME_PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void init() {
		gsm = new GameStateManager();
	}
}
