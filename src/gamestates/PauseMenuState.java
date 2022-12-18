package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tilemap.Background;
import utils.Constants;

public class PauseMenuState {
	private Font font1;
	private Font font2;
	private BufferedImage pauseScreen;
	private BufferedImage pauseTheme;
	
	private String[] notifications = {
			"Paused",
			"Press ESC to resume",
			"or",
			"Press ENTER to back ",
			"		to level selection"
	};

	public PauseMenuState() {
		try {
			pauseScreen = ImageIO.read(getClass().getResourceAsStream(Constants.UI.PauseGame.PAUSE_SCREEN));
			pauseTheme = ImageIO.read(getClass().getResourceAsStream(Constants.UI.PauseGame.PAUSE_THEME));
			
			font1 = new Font("Algerian", Font.BOLD | Font.PLAIN, 35);
			font2 = new Font("Algerian", Font.BOLD, 20);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(pauseScreen, 0, 0, pauseScreen.getWidth(), pauseScreen.getHeight(), null);
		g.drawImage(pauseTheme, 0, 0, pauseTheme.getWidth(), pauseTheme.getHeight(), null);
		
		// draw notifications
		g.setFont(font1);
		g.setColor(Color.BLACK);
		g.drawString(notifications[0], Constants.Panel.WIDTH / 2 - font1.getSize() * 2, 170);
		
		g.setFont(font2);
		g.setColor(Color.BLACK);
		for(int i = 1; i < notifications.length; i++) {
			if(i == 2) {
				g.drawString(notifications[i], 500, 276 + (i - 1) * 40);
			}
			else if(i == notifications.length - 1) {
				g.drawString(notifications[i], 410, 276 + (i - 1) * 35);
			}
			else {
				g.drawString(notifications[i], 403, 276 + (i - 1) * 40);
			}
		}
	}
	
}
