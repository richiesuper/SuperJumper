package main;

import utils.Constants;

public class Game implements Runnable {
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Thread gameThread;

	public Game() {
		this.gamePanel = new GamePanel(Constants.Panel.WIDTH, Constants.Panel.HEIGHT);
		this.gameFrame = new GameFrame(Constants.Game.TITLE, this.gamePanel);
		this.gamePanel.requestFocus();
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		while (true) {
			gamePanel.repaint();

			try {
				Thread.sleep((long) Constants.Game.FRAME_PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
