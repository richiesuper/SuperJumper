package gamestates;

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
			"Press ENTER to back to level selection"
	};

	public PauseMenuState() {
		try {
			pauseScreen = ImageIO.read(getClass().getResourceAsStream(Constants.UI.PauseGame.PAUSE_SCREEN));
			pauseTheme = ImageIO.read(getClass().getResourceAsStream(Constants.UI.PauseGame.PAUSE_THEME));
			
			font1 = new Font("Algerian", Font.BOLD | Font.PLAIN, 35);
			font2 = new Font("Algerian", Font.PLAIN, 20);
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
		g.drawString(notifications[0], 300, 200);
	}
	
}
