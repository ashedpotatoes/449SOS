package src.main;

public class GeneralBoard extends Board{

    private static final int numberchosen = 5;
    int totalCols = numberchosen; 
    int totalRows = numberchosen; 

    public GeneralBoard(int numberchosen) {
        super(numberchosen);
    }

    protected int madeMatch(int row, int col) {
		int addScore = 0;
		if (row < totalRows && col < totalCols) {
			if (grid[row][col] == Cell.CROSS) {
				if (row < (totalRows - 2) && col < (totalCols - 2)) {
					if (grid[row + 1][col + 1] == Cell.NOUGHT && grid[row + 2][col + 2] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row > 1 && col < (totalCols - 2)) {
					if (grid[row - 1][col + 1] == Cell.NOUGHT && grid[row - 2][col + 2] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row > 1 && col > 1) {
					if (grid[row - 1][col - 1] == Cell.NOUGHT && grid[row - 2][col - 2] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row < (totalRows - 2) && col > 1) {
					if (grid[row + 1][col - 1] == Cell.NOUGHT && grid[row + 2][col - 2] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (col < (totalCols - 2)) {
					if (grid[row][col + 1] == Cell.NOUGHT && grid[row][col + 2] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row < (totalRows - 2)) {
					if (grid[row + 1][col] == Cell.NOUGHT && grid[row + 2][col] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row > 1) {
					if (grid[row - 1][col] == Cell.NOUGHT && grid[row - 2][col] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (col > 1) {
					if (grid[row][col - 1] == Cell.NOUGHT && grid[row][col - 2] == Cell.CROSS) {
						addScore++;
					}
				}
			} else if (grid[row][col] == Cell.NOUGHT) {
				if (row > 0 && row < (totalRows - 1) && col > 0 && col < (totalCols - 1)) {
					if (grid[row + 1][col + 1] == Cell.CROSS && grid[row - 1][col - 1] == Cell.CROSS) {
						addScore++;
					}
					if (grid[row + 1][col - 1] == Cell.CROSS && grid[row - 1][col + 1] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (row > 0 && row < (totalRows - 1)) {
					if (grid[row + 1][col] == Cell.CROSS && grid[row - 1][col] == Cell.CROSS) {
						addScore++;
					}
				} 
				if (col > 0 && col < (totalCols - 1)) {
					if (grid[row][col + 1] == Cell.CROSS && grid[row][col - 1] == Cell.CROSS) {
						addScore++;
					}
				}
			}
		
			if (turn == 'B' && addScore > 0) {
				blueScore += addScore;
			} else if (turn == 'R' && addScore > 0) {
				redScore += addScore;
			}
		}
		return addScore;
	}

	@Override
    public void makeMove(int row, int column, int numberchosen) {
		if (row >= 0 && row < numberchosen && column >= 0 && column < numberchosen && grid[row][column] == Cell.EMPTY) {
			if(getPlayersPref() == 'S'){
				grid[row][column] = (turn == 'R') ? Cell.CROSS : Cell.NOUGHT;
			}
			else{
				grid[row][column] = (turn == 'R') ? Cell.NOUGHT : Cell.CROSS;
			}
			if (madeMatch(row, column) > 0) {
				updateGameState();
			} else {
				updateGameState();
				turn = (turn == 'B') ? 'R' : 'B';
			}
		}
	}
	

	public void updateGameState() {
		if (isDraw()) {
			currentGameState = GameState.DRAW;
		} else if (Won()) {
			if (redScore > blueScore) {
				currentGameState = GameState.CROSS_WON;
			} else {
				currentGameState = GameState.NOUGHT_WON;
			}
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
		
		if (redScore == blueScore) {
			currentGameState = GameState.DRAW; 
			return true;
		}
		return false;
	}
	
	private boolean Won() {
		for (int row = 0; row < totalRows; row++) {
			for (int col = 0; col < totalCols; col++) {
				if (grid[row][col] == Cell.EMPTY) {
					return false;
				}
			}
		}
		
		if (redScore == blueScore) {
			currentGameState = GameState.DRAW; 
			return false;
		}
		return true;
	}
}