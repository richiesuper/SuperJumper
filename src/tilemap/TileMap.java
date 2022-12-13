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
	// position
	private double x;
	private double y;
	private double tween;

	// tileset
	private BufferedImage tileset;
	private int tileRows;
	private int tileCols;
	private Tile[][] tiles;

	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	private int tileWidth;
	private int tileHeight;

	// bounds
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	// map
	private int[][] map;
	private int rowCount;
	private int colCount;
	private int width;
	private int height;

	// loadmap
	BufferedReader br;

	public TileMap(int tileWidth, int tileHeight) {
		this.x = 0;
		this.y = 0;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.numRowsToDraw = Constants.Tile.VERT_SUM + Constants.TileMap.OFFSET;
		this.numColsToDraw = Constants.Tile.HORIZ_SUM + Constants.TileMap.OFFSET;
		this.tween = 0.07;
	}

	public void loadTiles(String filename) {
		InputStream is;

		try {
			is = getClass().getResourceAsStream(filename);
			tileset = ImageIO.read(is);

			// compute number of cols and rows
			tileCols = tileset.getWidth() / tileWidth;
			tileRows = tileset.getHeight() / tileHeight;

			// allocate memory for our tile matrix
			tiles = new Tile[tileRows][tileCols];

			// temporary image
			BufferedImage subimage;

			for (int row = 0; row < tileRows; row++) {
				for (int col = 0; col < tileCols; col++) {
					subimage = tileset.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
					tiles[row][col] = new Tile(subimage,
							(row == 0) ? Constants.Tile.PASSTHROUGH : Constants.Tile.BLOCKED);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filename) {
		InputStream is;

		try {
			is = getClass().getResourceAsStream(filename);
			br = new BufferedReader(new InputStreamReader(is));

			// get those first 2 lines in *.map files
			colCount = Integer.parseInt(br.readLine());
			rowCount = Integer.parseInt(br.readLine());

			map = new int[rowCount][colCount];

			this.width = colCount * tileWidth;
			this.height = rowCount * tileHeight;

			xmin = Constants.Panel.WIDTH - this.width;
			xmax = 0;
			ymin = Constants.Panel.HEIGHT - this.height;
			ymax = 0;

			String delims = "\\s+";
			String line;
			for (int row = 0; row < rowCount; row++) {
				line = br.readLine();
				String[] tokens = line.split(delims);

				for (int col = 0; col < colCount; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTween(double tween) {
		this.tween = tween;
	}

	public void setPosition(double x, double y) {
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;

		fixBounds();

		colOffset = (int) -this.x / tileWidth;
		rowOffset = (int) -this.y / tileHeight;

	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numRowsToDraw;
		int c = rc % numRowsToDraw;
		return tiles[r][c].getType();
	}

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
			if (row >= rowCount)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
				if (col >= colCount)
					break;

				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / tileCols;
				int c = rc % tileCols;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileWidth, (int) y + row * tileHeight, null);
			}
		}
	}
}
