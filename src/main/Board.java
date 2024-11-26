package src.main;

public class Board {
	//public static String GameType; 

	public enum Cell {
		EMPTY, CROSS, NOUGHT
	}
	public Cell[][] grid;
	public char turn;
	public char playersChoice; 

	public int redScore = 0, blueScore = 0;
	protected RecordGame rec = new RecordGame();

	public enum GameState {
		PLAYING, DRAW, CROSS_WON, NOUGHT_WON, FINISHED
	}

	public GameState currentGameState;

	public Board(int numberchosen) {
		grid = new Cell[numberchosen][numberchosen];
		initGame(numberchosen);
	}

	public void initGame(int numberchosen) {
		for (int row = 0; row < numberchosen; ++row) {
			for (int col = 0; col < numberchosen; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		rec.createFile(numberchosen, numberchosen);
		currentGameState = GameState.PLAYING;
		setTurn('R');
	}

	public void resetGame(int numberchosen) {
		initGame(numberchosen);
	}


	/*public int getTotalRows() {
		return numberchosen;
	}

	public int getTotalColumns() {
		return TOTALCOLUMNS;
	}*/

	public Cell getCell(int row, int column, int numberchosen) {
		if (row >= 0 && row < numberchosen && column >= 0 && column < numberchosen) {
			return grid[row][column];
		} else {
			return null;
		}
	}

	public void setPlayersPref(char c){
		playersChoice = c; 
	}

	public char getPlayersPref(){
		return playersChoice; 
	}

	public void setTurn(char t){
		turn = t; 
	}

	public char getTurn() {
		return turn;
	}

	public int getRedScore() {
		return redScore;
	}
	
	public int getBlueScore() {
		return blueScore;
	}

	public GameState getGameState() {
		return currentGameState;
	}

      public void makeMove(int row, int column, int numberchosen) {
		if ((row >= 0 && row < numberchosen && column >= 0 && column < numberchosen && grid[row][column] == Cell.EMPTY)) {
			grid[row][column] = (turn == 'R') ? Cell.CROSS : Cell.NOUGHT;
			turn = (turn == 'R') ? 'B' : 'R';
		}
	}

	
    

}
