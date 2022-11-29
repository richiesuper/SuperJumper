package main;

public class Game implements Runnable {
	private final String GAME_NAME = "Super Jumper";
	private final int GAME_PANEL_WIDTH = 800;
	private final int GAME_PANEL_HEIGHT = 600;
	private final int FPS = 60;
	private final long FRAME_PERIOD_IN_MS = 1000 / FPS;

	private GameFrame gameFrame;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	public Game() {
		this.gamePanel = new GamePanel(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
		this.gameFrame = new GameFrame(GAME_NAME, this.gamePanel);
		this.gamePanel.requestFocus();
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		while (true) {
			gamePanel.repaint();

			try {
				Thread.sleep(FRAME_PERIOD_IN_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
