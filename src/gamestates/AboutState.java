package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tilemap.Background;
import utils.Constants;

public class AboutState extends GameState{
	private Background bg;
	private Font font;
	
	private String[] about = {
			"ABOUT US",
			"", 
			"Saya suka saya suka saya suka saya suka saya suka", 
			"Saya suka saya suka saya suka saya suka saya suka", 
			"Saya suka saya suka saya suka saya suka saya suka", 
			" ",
			"Richie Seputro          	- 5025211213", 
			"M Naufal Badruttamam    - 5025211240",
			" ",
			"BACK"
	};
	
	public AboutState(GameStateManager gsm) {
		this.gsm = gsm;
		
		bg = new Background(Constants.Backgrounds.ABOUT_MENU);
		font = new Font("Algerian", Font.PLAIN, 25);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// draw bg
		bg.draw(g);
		
		// draw title
		g.setFont(font);
		for(int i = 0; i < about.length; i++) {
			if(i == about.length - 1) {
				g.setColor(Color.RED);
				g.drawString(about[i], 500, 550);
			}
			else {
				g.setColor(Color.BLACK);
				g.drawString(about[i], 200, 50 + i * 50);
			}
		}
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			gsm.setState(Constants.GameStates.MAIN_MENU);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
