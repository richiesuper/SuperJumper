package gamestates;

import java.awt.Graphics2D;

import tilemap.Background;
import utils.Constants;

public class MainMenuState extends GameState {
	private Background bg;

	public MainMenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background(Constants.Backgrounds.MAIN_MENU);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		init();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
