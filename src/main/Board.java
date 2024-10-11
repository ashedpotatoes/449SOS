package src.main;

public class Board {

	private int[][] grid;
	private char turn = 'S';

	public Board(int numberchosen) {
		grid = new int[numberchosen][numberchosen];
	}

	public int getCell(int row, int column, int numberchosen) {
		if (row >= 0 && row < numberchosen && column >= 0 && column < numberchosen)
			return grid[row][column];
		else
			return -1;
	}

	public char getTurn() {
		return turn;
	}

	public void makeMove(int row, int column, int numberchosen) {
		if (row >= 0 && row < numberchosen && column >= 0 && column < numberchosen
				&& grid[row][column] == 0) {
			grid[row][column] = (turn == 'S')? 1 : 2; 
			turn = (turn == 'S')? 'O' : 'S';
		}
	}

}
