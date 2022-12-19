package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tilemap.Background;
import utils.Constants;

public class MainMenuState extends GameState{
	private Background bg;
	private Font font;
	private BufferedImage menuScreen;
	
	private int currentChoice;
	private String[] options = {
		"Play",
		"About",
		"Quit"
	};
	
	public MainMenuState(GameStateManager gsm) {
		this.gsm = gsm;
		currentChoice = 0;
		
		try {
			bg = new Background(Constants.Backgrounds.MAIN_MENU);
			font = new Font("Algerian", Font.PLAIN, 30);
			
			menuScreen = ImageIO.read(getClass().getResourceAsStream(Constants.UI.MenuButton.MENU_SCREEN));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
	
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		// draw background
		bg.draw(g);
		
		// draw image screen
		g.drawImage(menuScreen, 0, 0, menuScreen.getWidth(), menuScreen.getHeight(), null);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GRAY);
			
			if(i == 0) {
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 2 + 12, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 3) + i * 35);
			}
			else if(i == 1){
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 2, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 3) + i * 35);
			}
			else {
				g.drawString(options[i], Constants.Panel.WIDTH / 2 - font.getSize() * 2 + 20, Constants.Panel.HEIGHT - (Constants.Panel.HEIGHT / 3) + i * 35);
			}
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(Constants.GameStates.LEVEL_SELECTION);
		}
		if(currentChoice == 1) {
			gsm.setState(Constants.GameStates.ABOUT_MENU);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
}
