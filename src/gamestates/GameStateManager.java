package gamestates;

import java.awt.Graphics;

import utils.Constants;

public class GameStateManager {
	// GameState
	private GameState[] gameStates;
	private int currState;

	public GameStateManager() {
		gameStates = new GameState[Constants.GameStates.NUM_GAME_STATE];
		this.currState = Constants.GameStates.MAIN_MENU;
		loadState();
		setState(currState);
	}

	private void loadState() {
		gameStates[Constants.GameStates.MAIN_MENU] = new MainMenuState(this);
		gameStates[Constants.GameStates.PAUSE_MENU] = new PauseMenuState(this);
		gameStates[Constants.GameStates.ABOUT_MENU] = new AboutState(this);
		gameStates[Constants.GameStates.GAME_OVER] = new GameOverState(this);
		gameStates[Constants.GameStates.GAME_FINISH] = new GameFinishState(this);
		gameStates[Constants.GameStates.LEVEL_SELECTION] = new LevelSelectionState(this);
		gameStates[Constants.GameStates.LVL_1] = new Level1State(this);
		gameStates[Constants.GameStates.LVL_2] = new Level2State(this);
	}

	public void setState(int state) {
		this.currState = state;
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
}
