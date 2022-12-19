package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import entities.HighScore;
import tilemap.Background;
import utils.Constants;

public class GameFinishState extends GameState {
	private Background bg;
	private BufferedImage gameTitle;
	private BufferedImage newHighScore;
	private Font font;
	private StringBuilder clock;
	private HighScore highScore;
	
	private int time;
	private int score;
	private int currentChoice;
	private String[] options = {
			"Level Selection", 
			"Quit"
	};
	

	public GameFinishState(GameStateManager gsm) {
		this.gsm = gsm;
		highScore = new HighScore(gsm.getCurrLevel());
		
		try {
			bg = new Background(Constants.Backgrounds.LVL_SELECTION_MENU);
			bg.setVector(-0.2, 0);
			
			gameTitle = ImageIO.read(getClass().getResourceAsStream(Constants.UI.GameFinish.GAME_FINISH));
			newHighScore = ImageIO.read(getClass().getResourceAsStream(Constants.UI.HighScore.NEW_HIGH_SCORE));
			
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
		
		// drawTime
		// getTime();
		updateTime();
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Your Time is", 370, 320);
		g.drawString(clock.toString(), 570, 320);
		if(time <= score || score == -100) {
			g.drawImage(newHighScore, 350, 150, newHighScore.getWidth() / 2, newHighScore.getHeight() / 2, null);
			highScore.highScoreSet(time);
		}
	}
	
	private void updateTime() {
		clock = new StringBuilder();
		score = highScore.getHighScore();
		highScore.highScoreSet(score);
		
		if(time < 60)
			clock.append("00:");
		else if(time < 600)
			clock.append("0" + (time / 60) + ":");
		else
			clock.append(time / 60);
		
		if(time % 60 == 0) 
			clock.append("00");
		else if(time % 600 == 0)
			clock.append("0" + (time % 60));
		else
			clock.append(time % 60);
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(Constants.GameStates.LEVEL_SELECTION);
		}
		if(currentChoice == 1) {
			gsm.setState(Constants.GameStates.MAIN_MENU);
		}
	}
	
	private void getTime() {
		String temp = "res/ui/highscore/Time.txt";
		if(!new File(temp).exists()) {
			storeFileInit(temp);
		}
		
		try {
			ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(temp));
			this.time = inFile.read();
			inFile.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void storeFileInit(String fileName) {
		try {
			ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
			outFile.flush();
			outFile.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
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
