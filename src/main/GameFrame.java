package main;

import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public GameFrame(String title, GamePanel gamePanel) throws HeadlessException {
		super(title);

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
				gamePanel.getPlayer().halt();
			}
		});
	}

}