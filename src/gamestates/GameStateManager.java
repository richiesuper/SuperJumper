package gamestates;

import java.awt.Graphics;

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
		if (state == Constants.GameStates.MAIN_MENU) {
			gameStates[state] = new MainMenuState(this);
		}
		if (state == Constants.GameStates.ABOUT_MENU) {
			gameStates[state] = new AboutState(this);
		} 
		if (state == Constants.GameStates.LVL_1) {
			currLevel = 1;
			gameStates[state] = new Level1State(this);
		}
		if (state == Constants.GameStates.LVL_2) {
			currLevel = 2;
			gameStates[state] = new Level2State(this);
		}
		if (state == Constants.GameStates.LEVEL_SELECTION) {
			gameStates[state] = new LevelSelectionState(this);
		}
		if (state == Constants.GameStates.GAME_OVER) {
			gameStates[state] = new GameOverState(this);
		}
		if (state == Constants.GameStates.GAME_FINISH) {
			gameStates[state] = new GameFinishState(this);
		}
	}

	public void setState(int state) {
		unloadState(currState);
		this.currState = state;
		loadState(currState);
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void update() {
		gameStates[currState].update();
	}

	public void draw(Graphics g) {
		gameStates[currState].draw(g);
	}

	public void keyPressed(int k) {
		gameStates[currState].keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates[currState].keyReleased(k);
	}

	public GameState getGameState() {
		return gameStates[currState];
	}

	public int getCurrState() {
		return currState;
	}

	public int getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(int currLevel) {
		this.currLevel = currLevel;
	}
}
