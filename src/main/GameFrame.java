package main;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import utils.Constants;

public class GameFrame extends JFrame {
	private GamePanel gamePanel;

	public GameFrame(String title, GamePanel gamePanel) throws HeadlessException {
		super(title);
		this.gamePanel = gamePanel;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(gamePanel);

		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				if (gamePanel.getGsm().getCurrState() >= Constants.GameStates.LEVEL_SELECTION) {
					gamePanel.getGsm().getGameState().getPlayer().halt();
				}
			}
		});
	}

}