package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import entities.HighScore;
import tilemap.Background;
import utils.Constants;

public class HighScoreState extends GameState{
	private Background bg;
	private Background bgHigh;
	private Font font1, font2;
	private String[] scores;
	private StringBuilder temp;
	private HighScore highScore1, highScore2;
	
	private int[] scoreList;
	private String[] about = {
			"HIGH SCORE",
			"LEVEL 1",
			"LEVEL 2", 
			"BACK"
	};
	
	
	public HighScoreState(GameStateManager gsm) {
		this.gsm = gsm;
		scores = new String[2];
		scoreList = new int[2];
		
		try {
			bg = new Background(Constants.Backgrounds.LVL_SELECTION_MENU);
			bg.setVector(-0.2, 0);
			bgHigh = new Background(Constants.Backgrounds.HIGHSCORE_MENU);
			font1 = new Font("Algerian", Font.BOLD, 40);
			font2 = new Font("Arial", Font.PLAIN, 30);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		bg.update();
	}

	@Override
	public void draw(Graphics g) {
		// draw bg
		bg.draw(g);
		
		// draw bg highscore
		bgHigh.draw(g);
		
		// draw Title
		g.setFont(font1);
		g.setColor(Color.BLACK);
		g.drawString(about[0], 410, 75);
		
		// draw about text
		g.setFont(font2);
		for(int i = 1; i < about.length; i++) {
			if(i == about.length - 1) {
				g.setColor(Color.RED);
				g.drawString(about[i], 470, 450);
			}
			else {
				g.setColor(Color.BLACK);
				g.drawString(about[i], 250, 150 + i * 50);
			}
		}
	}
	
	private String convertToString(int score) {
		temp = new StringBuilder();
		
		if(score == -100) {
			temp.append("-");
			return temp.toString();
		}
		
		if(score < 60) 
			temp.append("00:");
		else if(score < 600) 
			temp.append("0" + (score / 60) + ":");
		else
			temp.append(score / 60);
		
		if(score % 60 == 0)
			temp.append("00");
		else if(score % 600 == 0)
			temp.append("0" + (score % 60));
		else
			temp.append(score % 60);
				
		return temp.toString();
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			gsm.setState(Constants.GameStates.LEVEL_SELECTION);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
