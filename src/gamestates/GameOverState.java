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

public class GameOverState extends GameState {
	private Background bg;
	private BufferedImage gameOverTitle;
	private BufferedImage gameOverScreen;
	private Font font;
	
	private int currentChoice;
	private String[] options = {
			"RESTART", "QUIT"
	};

	public GameOverState(GameStateManager gsm) {
		this.gsm = gsm;
		currentChoice = 0;
		
		try {
			bg = new Background(Constants.Backgrounds.LVL_SELECTION_MENU);
			bg.setVector(-0.2, 0);
			
			gameOverTitle = ImageIO.read(getClass().getResourceAsStream(Constants.UI.GameOver.GAME_OVER));
			gameOverScreen = ImageIO.read(getClass().getResourceAsStream(Constants.UI.GameOver.GAME_OVER_SCREEN));
			
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
		bg.draw(g);
		
		g.drawImage(gameOverScreen, 0, 0, gameOverScreen.getWidth(), gameOverScreen.getHeight(), null);
		g.drawImage(gameOverTitle, (Constants.Panel.WIDTH - gameOverTitle.getWidth()) / 2, 40, gameOverTitle.getWidth(), gameOverTitle.getHeight(), null);
		
		// Draw Menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) g.setColor(Color.RED);
			else g.setColor(Color.GRAY);
			if(i == 1)
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 2 + 33, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 4) + i * 35);
			else
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 2, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 4) + i * 35);
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			if(gsm.getCurrLevel() == 1) {
				gsm.setState(Constants.GameStates.LVL_1);
			}
			else if(gsm.getCurrLevel() == 2){
				gsm.setState(Constants.GameStates.LVL_2);
			}
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
