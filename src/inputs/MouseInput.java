package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import utils.Constants;

public class MouseInput implements MouseListener {
	private GamePanel gamePanel;

	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			gamePanel.getPlayer().setState(Constants.Entities.Player.ATK_1);
			break;
		case MouseEvent.BUTTON2:
			System.out.println("Mouse button 2 was pressed!");
			break;
		case MouseEvent.BUTTON3:
			System.out.println("Mouse button 3 was pressed!");
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			gamePanel.getPlayer().setState(Constants.Entities.Player.IDLE);
			break;
		case MouseEvent.BUTTON2:
			System.out.println("Mouse button 2 was released!");
			break;
		case MouseEvent.BUTTON3:
			System.out.println("Mouse button 3 was released!");
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
