package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInput implements KeyListener {
	private GamePanel gamePanel;
	public static final int LEFT = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;

	public KeyboardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was typed!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Key " + e.getKeyChar() + " was pressed!");
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				gamePanel.setDirection(UP);
				break;
			case KeyEvent.VK_A:
				gamePanel.setDirection(LEFT);
				break;
			case KeyEvent.VK_S:
				gamePanel.setDirection(DOWN);
				break;
			case KeyEvent.VK_D:
				gamePanel.setDirection(RIGHT);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was released!");
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
				gamePanel.setMoving(false);
				break;
		}
	}

}
