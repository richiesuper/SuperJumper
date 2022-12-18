package gamestates;

import java.awt.Graphics;

import entities.Player;

public abstract class GameState {

	protected GameStateManager gsm;

	public abstract void init();

	public abstract void update();

	public abstract void draw(Graphics g);

	public abstract void keyPressed(int k);

	public abstract void keyReleased(int k);

	public Player getPlayer() {
		return null;
	}
//	
//	public MainMenuState getMainMenu() {
//		return null;
//	}
//	
//	public Level1State getLevel1() {
//		return null;
//	}
}
