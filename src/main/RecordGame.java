package src.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class RecordGame {
	private PrintWriter record;
	private File recFile;
	private int totalCols;
	
	public void createFile(int rows, int cols) {
		try {
			recFile = new File("record.txt");
			record = new PrintWriter(recFile);
		}
		catch (IOException e) {
			System.out.println("Could not create file.");
			e.printStackTrace();
		}
		
		totalCols = cols;
	}	
	
	public void recordMove(int row, int col, char player, String letter) {
		col = totalCols - col - 1;
		if (player == 'B') {
			record.println("Blue player placed a(n) " + letter + " in the " + row + ", " + col + " space.");
		} else {
			record.println("Red player placed a(n) " + letter + " in the " + row + ", " + col + " space.");
		}
	}
	
	public void recordMatch(int row, int col, char player, int score) {
		col = totalCols - col - 1;
		if (player == 'B') {
			record.println(" - Blue player made a match. Their score is now " + score + ".");
		} else {
			record.println(" - Red player made a match. Their score is now " + score + ".");
		}
	}
	
	public void recordDraw() {
		record.println("\n - It is a draw!");
		record.close();
	}
	
	public void recordGenWin(char player, int score) {
		if (player == 'B') {
			record.println("\n - Blue player won with a score of " + score + "!");
		} else {
			record.println("\n - Red player won with a score of " + score + "!");
		}
		
		record.close();
	}
	
	public void recordSimWin(char player) {
		if (player == 'B') {
			record.println("\n - Blue player won!");
		} else {
			record.println("\n - Red player won!");
		}
		
		record.close();
	}
}

