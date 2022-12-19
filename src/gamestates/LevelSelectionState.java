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

public class LevelSelectionState extends GameState {
	private Font font;
	private BufferedImage levelTitle;
	private BufferedImage levelUnselect;
	private BufferedImage[] level;
	private Background bg;
	private int currentChoice;
	
	private String[] options = {
		"1",
		"2",
		"HIGH SCORE",
		"BACK"
	};

	public LevelSelectionState(GameStateManager gsm) {
		this.gsm = gsm;
		level = new BufferedImage[2];
		currentChoice = 0;
		
		try {
			bg = new Background(Constants.Backgrounds.LVL_SELECTION_MENU);
			bg.setVector(0.2, 0);
			
			font = new Font("Algerian", Font.BOLD, 30);
			
			levelTitle = ImageIO.read(getClass().getResourceAsStream(Constants.UI.LevelSelection.LEVEL_TITLE));
			levelUnselect = ImageIO.read(getClass().getResourceAsStream(Constants.UI.LevelSelection.LEVEL_UNSELECT));
			level[0] = ImageIO.read(getClass().getResourceAsStream(Constants.UI.LevelSelection.LEVEL_1_SELECT));
			level[1] = ImageIO.read(getClass().getResourceAsStream(Constants.UI.LevelSelection.LEVEL_2_SELECT));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		init();
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
		g.drawImage(levelTitle, 120, 40, levelTitle.getWidth(), levelTitle.getHeight(), null);
		//System.out.println(currentChoice);
		if(currentChoice < 2)
			g.drawImage(level[currentChoice], 0, 0, level[currentChoice].getWidth(), level[currentChoice].getHeight(), null);
		else
			g.drawImage(levelUnselect, 0, 0, levelUnselect.getWidth(), levelUnselect.getHeight(), null);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GRAY);
			
			if(i == options.length - 1)
				g.drawString(options[i], 880, 520);
			else if(i == options.length - 2)
				g.drawString(options[i], 50, 520);
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(Constants.GameStates.LVL_1);
		}
		else if(currentChoice == 1) {
			gsm.setState(Constants.GameStates.LVL_2);
		}
		else if(currentChoice == 2) {
			gsm.setState(Constants.GameStates.HIGH_SCORE);
		}
		else if(currentChoice == 3) {
			gsm.setState(Constants.GameStates.MAIN_MENU);
		}
	}

	@Override
	public void keyPressed(int k) {
		System.out.println(k);
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		else if(k == KeyEvent.VK_LEFT) { 
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		else if(k == KeyEvent.VK_RIGHT) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		else if(k == KeyEvent.VK_DOWN) {
			if(currentChoice == 0) {
				currentChoice = 2;
			}
			else if(currentChoice == 1) {
				currentChoice = 3;
			}
			else if(currentChoice == 2){
				currentChoice = 0;
			}
			else {
				currentChoice = 1;
			}
		}
		else if(k == KeyEvent.VK_UP) {
			if(currentChoice == 0) {
				currentChoice = 2;
			}
			else if(currentChoice == 1) {
				currentChoice = 3;
			}
			else if(currentChoice == 2){
				currentChoice = 0;
			}
			else
				currentChoice = 1;
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
