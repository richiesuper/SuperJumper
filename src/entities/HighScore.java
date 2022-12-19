package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utils.Constants;

public class HighScore {
	private StringBuilder fileName;
	private String temp = "res/ui/highscore/level";
	
	public HighScore(int level) {
		System.out.println("level" + level);
		fileName = new StringBuilder();
		fileName.append(temp + level + ".txt");
//		if(!new File(fileName.toString()).exists()) {
//			storeFileInit(fileName.toString());
//		}
	}
	
	private void storeFileInit(String fileName) {
		try {
			ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
			outFile.write(-100);
			outFile.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getHighScore() {
		try {
			ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(fileName.toString()));
			int score = inFile.read();
			inFile.close();
			return score;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return -100;
	}
	
	public void highScoreSet(int score) {
		try {
			ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName.toString()));
			outFile.write(score);
			outFile.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
