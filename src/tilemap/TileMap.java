package tilemap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import utils.Constants;

public class TileMap {
	// Position
	private double x;
	private double y;
	private double tween;

	// Tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// Drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	private int tileSize;

	// Bounds
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	// map
	private int[][] map;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// loadmap
	BufferedReader br;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		this.numRowsToDraw = Constants.Panel.WIDTH / tileSize + 15; 
		this.numColsToDraw = Constants.Panel.HEIGHT / tileSize + 15;
		this.tween = 0.07;
	}

	public void loadTiles(String s) {
		try {
			InputStream is;
			is = getClass().getResourceAsStream(s);
			this.tileset = ImageIO.read(is);

			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String s) {
		try {
			InputStream is;
			is = getClass().getResourceAsStream(s);
			br = new BufferedReader(new InputStreamReader(is));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());

			map = new int[numRows][numCols];
//			System.out.println(numRows);
//			System.out.println(numCols);

			this.width = numCols * tileSize;
			this.height = numRows * tileSize;

			xmin = Constants.Panel.WIDTH - this.width;
			xmax = 0;
			ymin = Constants.Panel.HEIGHT - this.height;
			ymax = 0;

			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTween(double d) {
		this.tween = d;
	}

	public void setPosition(double x, double y) {

		this.x += (x - this.x) * tween; // thisx = 0 - 0 * 1
		this.y += (y - this.y) * tween; // thisy = 0 - 0 
//		System.out.println("this x : " + this.x);
//		System.out.println("this y : " + this.y);

		fixBounds();

		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;

	}
	
	public int getTileSize() { return tileSize; }
	public double getx() { return x; }
	public double gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	private void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (y < ymin)
			y = ymin;
		if (x > xmax)
			x = xmax;
		if (y > ymax)
			y = ymax;
	}

	public void draw(Graphics g) {

		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if (col >= numCols)
					break;

				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);

			}

		}

	}

}
