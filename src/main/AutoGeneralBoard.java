package src.main;

import java.util.Random;

public class AutoGeneralBoard extends GeneralBoard{
    private char autoplayer;
    public int totalRows;
    public int totalCols; 
	
	public AutoGeneralBoard(char ap, int numchosen) {
        super(numchosen);
		autoplayer = ap;
        totalRows = numchosen;
        totalCols = numchosen;
	}

    	private boolean stratMove() {
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				if (grid[i][j] == Cell.EMPTY) {
					grid[i][j] = Cell.CROSS;
					if (madeMatch(i, j) > 0) {
						rec.recordMove(i, j, turn, "S");
						updateGameState();
						return true;
					} else {
						grid[i][j] = Cell.EMPTY;
					}
				}
			}
		}
		return false;
	}
	
    @Override
	public void makeMove(int row, int col, int numberchosen) {
		if ((grid[row][col] == Cell.EMPTY)) {
			if (autoplayer != 'Z') {
				super.makeMove(row, col, numberchosen);
			}
			compMove();
		}
	}
	public void compMove() {
		//25% of the time should be random placement
		//75% of the time should be strategic move with the exception that there are no strategic moves
		String currentLetter;
		if(getPlayersPref() == 'S' && getTurn() == 'R'){
			currentLetter = "S";
		} else{
			currentLetter = "O";
		}
		
		if ((turn == autoplayer || autoplayer == 'Z') && getGameState() == GameState.PLAYING) {
            System.out.println("Registers its turn!!");
			Random rand = new Random();
			int randRow, randCol;
			do {
				int randInt = rand.nextInt(100);
				if (randInt < 25) {
					do {
						randRow = rand.nextInt(totalRows);
						randCol = rand.nextInt(totalCols);
					} while (grid[randRow][randCol] != Cell.EMPTY);
					
					if (randInt < 13) {
						rec.recordMove(randRow, randCol, turn, currentLetter);
						grid[randRow][randCol] = Cell.NOUGHT;
					} else {
						rec.recordMove(randRow, randCol, turn, currentLetter);
						grid[randRow][randCol] = Cell.CROSS;
					}
                    updateGameState();
				} else {
					if (!stratMove()) {
						do {
							randRow = rand.nextInt(totalRows);
							randCol = rand.nextInt(totalCols);
						} while (grid[randRow][randCol] != Cell.EMPTY);
						
						randInt = rand.nextInt(2);
						if (randInt < 1) {
							rec.recordMove(randRow, randCol, turn, currentLetter);
							grid[randRow][randCol] = Cell.NOUGHT;
						} else {
							rec.recordMove(randRow, randCol, turn, currentLetter);
							grid[randRow][randCol] = Cell.CROSS;
						
						}
					} else {
						randRow = 15;
						randCol = 15;
					}
				}
				updateGameState();
			} while (madeMatch(randRow, randCol) > 0 || (randRow == 15 && randCol == 15));
			
			turn = (turn == 'B') ? 'R' : 'B';
		}
	}
	
	public void resetGame(int rows, int cols) {
		super.initGame(rows);
		if (autoplayer == 'B') {
			makeFirstMove();
		}
	}
	
	private void makeFirstMove() {
		Random rand = new Random();
		int randRow = rand.nextInt(totalRows);
		int randCol = rand.nextInt(totalCols);
		rec.recordMove(randRow, randCol, turn, "O");
		grid[randRow][randCol] = Cell.CROSS;
		turn = (turn == 'B') ? 'R' : 'B';
	}
}
