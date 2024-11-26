package src.main;

import src.main.MainFrame.GameMode;

public class SimpleBoard extends Board{

    public SimpleBoard(int numberchosen) {
        super(numberchosen);
        
    }

    private static final int numberchosen = 3;
    int totalCols = numberchosen;
    int totalRows = numberchosen; 


	//  && getGameState() == GameState.PLAYING

    public void makeMove(int row, int column, int numberchosen) {
		String GameType = "simple";
		String currentLetter;
		if(getPlayersPref() == 'S' && getTurn() == 'R'){
			currentLetter = "S";
		} else{
			currentLetter = "O";
		}
		
		if (row >= 0 && row < numberchosen && column >= 0 && column < numberchosen && grid[row][column] == Cell.EMPTY && getGameState() == GameState.PLAYING && GameType == "simple") {
			if(getPlayersPref() == 'S'){
				grid[row][column] = (turn == 'R') ? Cell.CROSS : Cell.NOUGHT;
				rec.recordMove(row, column, turn, currentLetter);
			}
			else{
				grid[row][column] = (turn == 'R') ? Cell.NOUGHT : Cell.CROSS;
				rec.recordMove(row, column, turn, currentLetter);
			}
			updateGameState(turn, row, column);
			turn = (turn == 'R') ? 'B' : 'R';
		}
	}

	public void updateGameState(char turn, int row, int col) {
		if (hasWon(row, col)) {
			rec.recordSimWin(turn);
			currentGameState = (turn == 'B') ? GameState.NOUGHT_WON : GameState.CROSS_WON;
		} else if (isDraw()) {
			rec.recordDraw();
			currentGameState = GameState.DRAW;
            System.out.println(currentGameState);
		}
	}
	
	protected boolean hasWon(int row, int col) {
		int check = 0;
		if (grid[row][col] == Cell.CROSS) {
			if (row < (totalRows - 2) && col < (totalCols - 2)) {
				if (grid[row + 1][col + 1] == Cell.NOUGHT && grid[row + 2][col + 2] == Cell.CROSS) {
					check++;
				}
			} 
			if (row > 1 && col < (totalCols - 2)) {
				if (grid[row - 1][col + 1] == Cell.NOUGHT && grid[row - 2][col + 2] == Cell.CROSS) {
					check++;
				}
			} 
			if (row > 1 && col > 1) {
				if (grid[row - 1][col - 1] == Cell.NOUGHT && grid[row - 2][col - 2] == Cell.CROSS) {
					check++;
				}
			} 
			if (row < (totalRows - 2) && col > 1) {
				if (grid[row + 1][col - 1] == Cell.NOUGHT && grid[row + 2][col - 2] == Cell.CROSS) {
					check++;
				}
			} 
			if (col < (totalCols - 2)) {
				if (grid[row][col + 1] == Cell.NOUGHT && grid[row][col + 2] == Cell.CROSS) {
					check++;
				}
			} 
			if (row < (totalRows - 2)) {
				if (grid[row + 1][col] == Cell.NOUGHT && grid[row + 2][col] == Cell.CROSS) {
					check++;
				}
			} 
			if (row > 1) {
				if (grid[row - 1][col] == Cell.NOUGHT && grid[row - 2][col] == Cell.CROSS) {
					check++;
				}
			} 
			if (col > 1) {
				if (grid[row][col - 1] == Cell.NOUGHT && grid[row][col - 2] == Cell.CROSS) {
					check++;
				}
			}
		} else if (grid[row][col] == Cell.NOUGHT) {
			if (row > 0 && row < (totalRows - 1) && col > 0 && col < (totalCols - 1)) {
				if ((grid[row + 1][col + 1] == Cell.CROSS && grid[row - 1][col - 1] == Cell.CROSS)
						|| (grid[row + 1][col - 1] == Cell.CROSS && grid[row - 1][col + 1] == Cell.CROSS)) {
					check++;
				}
			} 
			if (row > 0 && row < (totalRows - 1)) {
				if (grid[row + 1][col] == Cell.CROSS && grid[row - 1][col] == Cell.CROSS) {
					check++;
				}
			} 
			if (col > 0 && col < (totalCols - 1)) {
				if (grid[row][col + 1] == Cell.CROSS && grid[row][col - 1] == Cell.CROSS) {
					check++;
				}
			}
		}
		
		if (check > 0) {
			//currentGameState = GameState.FINISHED; 
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isDraw() {
		for (int row = 0; row < totalRows; row++) {
			for (int col = 0; col < totalCols; col++) {
				if (grid[row][col] == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public Cell getGrid(int row, int col) {
		return grid[row][col];
	}

    public Object getCurrentGameState() {
        return currentGameState; 
    }
    
}
