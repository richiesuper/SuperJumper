package gamestates;

import java.awt.Graphics2D;

import utils.Constants;

public class GameStateManager {
	// GameState
	private GameState[] gameStates;
	private int currState;
	private int currLevel;
	
	public GameStateManager() {
		gameStates = new GameState[Constants.GameStates.NUM_GAME_STATE];
		this.currState = Constants.GameStates.MAIN_MENU;
		loadState(currState);
	}
	
	private void loadState(int state) {
		if(state == Constants.GameStates.MAIN_MENU) {
			gameStates[state] = new MenuState(this);
		}
		
		if(state == Constants.GameStates.LVL_1) {
			gameStates[state] = new Level1State(this);
		}
		
		if(state == Constants.GameStates.LVL_2) {
			gameStates[state] = new Level2State(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currState);
		this.currState = state;
		loadState(currState);
	}
	
	public void update() {
		gameStates[currState].update();
	}	
	
	public void draw(Graphics2D g) {
		gameStates[currState].draw(g);
	}
	
}
