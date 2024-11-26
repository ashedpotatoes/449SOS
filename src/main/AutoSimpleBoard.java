package src.main;

import java.util.Random;

public class AutoSimpleBoard extends SimpleBoard{
    private char autoplayer;
	
	public AutoSimpleBoard(char ap, int numchosen) {
        super(numchosen);
		autoplayer = ap;
	}
	
	private boolean stratMove() {
		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				if (grid[i][j] == Cell.EMPTY) {
					grid[i][j] = Cell.CROSS;
					if (hasWon(i, j)) {
						rec.recordMove(i, j, turn, "S");
						updateGameState(turn, i, j);
						return true;
					} else {
						grid[i][j] = Cell.EMPTY;
					}
				}
			}
		}
		return false;
	}
	
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
		
			if ((turn == autoplayer || autoplayer == 'Z') && getGameState() == GameState.PLAYING) {
                System.out.println("Registers its turn!!");
				Random rand = new Random();
				int randInt = rand.nextInt(100), randRow, randCol;
				if (randInt < 25) {
					do {
						randRow = rand.nextInt(totalRows);
						randCol = rand.nextInt(totalCols);
					} while (grid[randRow][randCol] != Cell.EMPTY);
					
					if (randInt < 13) {
						rec.recordMove(randRow, randCol, turn, "O");
						grid[randRow][randCol] = Cell.NOUGHT;
					} else {
						rec.recordMove(randRow, randCol, turn, "S");
						grid[randRow][randCol] = Cell.CROSS;
					}
					updateGameState(turn, randRow, randCol);
				} else {
					if (!stratMove()) {
						do {
							randRow = rand.nextInt(totalRows);
							randCol = rand.nextInt(totalCols);
						} while (grid[randRow][randCol] != Cell.EMPTY);
						
						randInt = rand.nextInt(2);
						if (randInt < 1) {
							rec.recordMove(randRow, randCol, turn, "O");
							grid[randRow][randCol] = Cell.NOUGHT;
						} else {
							rec.recordMove(randRow, randCol, turn, "S");
							grid[randRow][randCol] = Cell.CROSS;
						}
						updateGameState(turn, randRow, randCol);
					}
				}
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
		rec.recordMove(randRow, randCol, turn, "S");
		grid[randRow][randCol] = Cell.CROSS;
		turn = (turn == 'B') ? 'R' : 'B';
	}
}
