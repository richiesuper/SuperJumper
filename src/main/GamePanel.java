package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import gamestates.GameStateManager;
import inputs.KeyboardInput;
import inputs.MouseInput;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private GameStateManager gsm;
	
	private boolean pause = false;
	public GamePanel(int width, int height, GameStateManager gsm) {
		super();

		this.gsm = gsm;

		this.keyboardInput = new KeyboardInput(this);
		this.mouseInput = new MouseInput(this);

		setPreferredSize(new Dimension(width, height));
		addKeyListener(this.keyboardInput);
		addMouseListener(this.mouseInput);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(!pause) {
			gsm.update();
		}
		
		gsm.draw(g);
	}

	public GameStateManager getGsm() {
		return gsm;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public boolean getPause() {
		return pause;
	}
}
