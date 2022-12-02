package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import utils.Constants;

public class KeyboardInput implements KeyListener {
	private GamePanel gamePanel;

	public KeyboardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was typed!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			gamePanel.getPlayer().setDirection(Constants.Entities.Player.DIR_LEFT);
			gamePanel.getPlayer().setMoving(true);
			gamePanel.getPlayer().setState(Constants.Entities.Player.WALK);
			break;
		case 'A':
			gamePanel.getPlayer().setDirection(Constants.Entities.Player.DIR_LEFT);
			gamePanel.getPlayer().setMoving(true);
			gamePanel.getPlayer().setState(Constants.Entities.Player.RUN);
			break;
		case 'd':
			gamePanel.getPlayer().setDirection(Constants.Entities.Player.DIR_RIGHT);
			gamePanel.getPlayer().setMoving(true);
			gamePanel.getPlayer().setState(Constants.Entities.Player.WALK);
			break;
		case 'D':
			gamePanel.getPlayer().setDirection(Constants.Entities.Player.DIR_RIGHT);
			gamePanel.getPlayer().setMoving(true);
			gamePanel.getPlayer().setState(Constants.Entities.Player.RUN);
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was released!");
		switch (e.getKeyChar()) {
		case 'a':
		case 'A':
		case 'd':
		case 'D':
			gamePanel.getPlayer().setMoving(false);
			gamePanel.getPlayer().setState(Constants.Entities.Player.IDLE);
			break;
		default:
			break;
		}
	}

}
