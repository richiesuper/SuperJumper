package main;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	public GameFrame(String title, GamePanel gamePanel) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(gamePanel);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		setVisible(true);
	}

}
