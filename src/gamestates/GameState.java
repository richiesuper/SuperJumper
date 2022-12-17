package gamestates;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public abstract class GameState {
	
	protected GameStateManager gsm;

	public abstract void init();

	public abstract void update();

	public abstract void draw(Graphics2D g);
	
	public abstract void keyPressed(int k);
	
	public abstract void keyReleased(int k);
}
