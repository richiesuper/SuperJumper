package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tilemap.Background;
import utils.Constants;

public class GameFinishState extends GameState {
	private Background bg;
	private BufferedImage gameTitle;
	private Font font;
	
	private int currentChoice;
	private String[] options = {
			"Level Selection", 
			"Quit"
	};
	

	public GameFinishState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background(Constants.Backgrounds.LVL_SELECTION_MENU);
			bg.setVector(-0.2, 0);
			
			gameTitle = ImageIO.read(getClass().getResourceAsStream(Constants.UI.GameFinish.GAME_FINISH));
			
			font = new Font("Algerian", Font.PLAIN, 30);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics g) {
		// draw bg
		bg.draw(g);
		
		// draw title
		g.drawImage(gameTitle, 0, 0, gameTitle.getWidth(), gameTitle.getHeight(), null);
		
		// draw text
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) 
				g.setColor(Color.RED);
			else 
				g.setColor(Color.GRAY);
			
			if(i == 1)
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 4 + 75, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 4) + i * 35);
			else
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 4 - 13, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 4) + i * 35);
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(Constants.GameStates.LEVEL_SELECTION);
		}
		if(currentChoice == 1) {
			gsm.setState(Constants.GameStates.MAIN_MENU);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) 
				currentChoice = options.length - 1;
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
