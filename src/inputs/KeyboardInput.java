package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import utils.Constants;
import gamestates.*;

public class KeyboardInput implements KeyListener {
	private GamePanel gamePanel;
	protected Boolean pause = false;

	public KeyboardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("Key " + e.getKeyChar() + " was typed!");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gamePanel.getGsm().getCurrState() > Constants.GameStates.LEVEL_SELECTION) {
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
			case 'w':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_UP);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.WALK);
				break;
			case 'W':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_UP);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.RUN);
				break;
			case 's':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_DOWN);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.WALK);
				break;
			case 'S':
				gamePanel.getGsm().getGameState().getPlayer().setDirection(Constants.Entities.Player.DIR_DOWN);
				gamePanel.getGsm().getGameState().getPlayer().setMoving(true);
				gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.RUN);
				break;
			default:
				break;
			}
			
			gamePanel.getGsm().keyPressed(e.getKeyCode());
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ESCAPE) {
				System.out.println("p");
				if(!pause) 
					gamePanel.setPause(true);
				else
					gamePanel.setPause(false);
			}
			else if(keyCode == KeyEvent.VK_ENTER && pause) 
				gamePanel.setPause(false);
		}
		else {
			gamePanel.getGsm().keyPressed(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key " + e.getKeyChar() + " was released!");

		if (gamePanel.getGsm().getCurrState() > Constants.GameStates.LEVEL_SELECTION) {
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
			default:
				break;
			}
		}
		else {
			gamePanel.getGsm().keyReleased(e.getKeyCode());
		}
	}
}
