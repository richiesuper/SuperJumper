package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.GamePanel;

public class MouseInput implements MouseListener {
	private GamePanel gamePanel;

	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			System.out.println("Mouse button 1 was clicked!");
			break;
		case MouseEvent.BUTTON2:
			System.out.println("Mouse button 2 was clicked!");
			break;
		case MouseEvent.BUTTON3:
			System.out.println("Mouse button 3 was clicked!");
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			System.out.println("Mouse button 1 was pressed!");
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
			System.out.println("Mouse button 1 was released!");
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
		System.out.println("Mouse cursor entered the panel!");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse cursor left the panel!");
	}

}
