package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import utils.Constants;

public class Clock {
	// Attribute
	private Timer clock;
	private BufferedImage image;
	private Font font;
	private StringBuilder string;
	
	// Needed
	private int second;
	private int minute;
	private long flinchTimer;
	private boolean flinching;
	
	
	public Clock() {
		flinching = false;
		
		clock = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(second == 60) {
					second = 0;
					minute++;
				}
			}
		});
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(Constants.UI.AttributeGames.CLOCK_SCREEN));
			font = new Font("Arial", Font.PLAIN, 20);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		clock.start();
	}
	
	public void stop() {
		clock.stop();
	}
	
	public void draw(Graphics g) {
		update();
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(string.toString(), 75, 55);
		g.drawImage(image, 20, 20, image.getWidth() / 5, image.getHeight() / 5, null);
	}

	private void update() {
		string = new StringBuilder();
		if(minute < 10)
			string.append("0" + minute);
		else
			string.append(minute);
		
		string.append(":");
		
		if(second < 10)
			string.append("0" + second);
		else
			string.append(second);
	}
}
