package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gamestates.EnumState;
import static utils.Constants.UI.Button.*;

public class MenuButton {
	private int xPos, yPos;
	private int rowIndex, index;
	private int xOffsetCenter = 140;
	
	private EnumState state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;
	
	public MenuButton(int xPos, int yPos, int rowIndex, EnumState state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		
		loadImgs();
		initBounds();
	}

	private void loadImgs() {
		imgs = new BufferedImage[2];
		BufferedImage temp = null;
		
		InputStream is = getClass().getResourceAsStream(MENU_BUTTON);
		System.out.println(is);
		
		try {
			temp = ImageIO.read(is);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Errornya disini, dia ga nangkep gambarnya
		
//		for(int i = 0; i < imgs.length; i++) {
//			imgs[i] = temp.getSubimage(i * B_WIDTH, rowIndex * B_HEIGHT, B_WIDTH, B_HEIGHT);
//		}
		
	}

	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void applyEnumstate() {
		EnumState.state = state;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
	
}
