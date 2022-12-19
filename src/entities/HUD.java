package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import utils.Constants;

public class HUD {
	private Player player;
	private Font font;
	private BufferedImage img;
	
	public HUD(Player player) {
		this.player = player;
		
		try {
			img = ImageIO.read(getClass().getResourceAsStream(Constants.UI.HUD.HUD_SCREEN));
			font = new Font("Arial", Font.PLAIN, 15);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Health " + (int) player.getHealth() + " / " + (int) player.getMaxHealth(), Constants.Panel.WIDTH - 100, 42);
		g.drawString("Energy " + (int) player.getEnergy() + " / " + (int) player.getMaxEnergy(), Constants.Panel.WIDTH - 110, 90);
	}
}
