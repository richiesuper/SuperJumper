package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.GamePanel;
import utils.Constants;

public class MouseInput implements MouseListener {
	private GamePanel gamePanel;

	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 * switch (e.getButton()) { case MouseEvent.BUTTON1:
		 * System.out.println("Mouse button 1 was clicked!"); break; case
		 * MouseEvent.BUTTON2: System.out.println("Mouse button 2 was clicked!"); break;
		 * case MouseEvent.BUTTON3: System.out.println("Mouse button 3 was clicked!");
		 * break; default: break; }
		 */
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gamePanel.getGsm().getGameState().getPlayer().setLastState(
				gamePanel.getGsm().getGameState().getPlayer().getState()
		);
		gamePanel.getGsm().getGameState().getPlayer().setAttacking(true);
		gamePanel.getGsm().getGameState().getPlayer().setState(Constants.Entities.Player.ATK_1);
		gamePanel.getGsm().getGameState().getPlayer().attack();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gamePanel.getGsm().getGameState().getPlayer().setAttacking(false);
		gamePanel.getGsm().getGameState().getPlayer().setState(
				gamePanel.getGsm().getGameState().getPlayer().getLastState());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("Mouse cursor entered the panel!");
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("Mouse cursor left the panel!");
	}

}
