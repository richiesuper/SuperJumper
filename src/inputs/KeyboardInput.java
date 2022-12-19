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
//		System.out.println("Key " + e.getKeyChar() + " was typed!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gamePanel.getGsm().getCurrState() > Constants.GameStates.HIGH_SCORE) {
			switch (e.getKeyChar()) {
			case 'a':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_LEFT);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.WALK);
				break;
			case 'A':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_LEFT);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.RUN);
				break;
			case 'd':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_RIGHT);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.WALK);
				break;
			case 'D':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_RIGHT);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.RUN);
				break;
			case ' ':
				gamePanel.getGsm().getGameState().getPlayer().setJumping(true);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setLastState(
						gamePanel.getGsm().getGameState().getPlayer().getState()
				);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.JUMP);
			default:
				break;
			}
			
			// Pause 
			gamePanel.getGsm().keyPressed(e.getKeyCode());
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ESCAPE) {
				System.out.println(gamePanel.getPause());
				if(!gamePanel.getPause()) 
					gamePanel.setPause(true);
				else
					gamePanel.setPause(false);
			}
			else if(keyCode == KeyEvent.VK_ENTER && gamePanel.getPause()) 
				gamePanel.setPause(false);
		}
		else {
			gamePanel.getGsm().keyPressed(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was released!");

		if (gamePanel.getGsm().getCurrState() > Constants.GameStates.HIGH_SCORE) {
			switch (e.getKeyChar()) {
			case 'w':
			case 'W':
			case 's':
			case 'S':
			case 'a':
			case 'A':
			case 'd':
			case 'D':
				gamePanel.getGsm().getGameState().getPlayer().setMoving(false);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.IDLE);
				break;
			case ' ':
				gamePanel.getGsm().getGameState().getPlayer().setJumping(false);
			default:
				break;
			}
		}
		else {
			gamePanel.getGsm().keyReleased(e.getKeyCode());
		}
	}
}
