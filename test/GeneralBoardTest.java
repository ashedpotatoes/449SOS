package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.Board;
import src.main.Board.Cell;
import src.main.Board.GameState;
import src.main.GeneralBoard;

public class GeneralBoardTest {
     public GeneralBoard board;

    @Before
    public void setUp() {
        board = new GeneralBoard(4); // Initialize a 4x4 board
    }

    @Test
    public void testBoardInitialization() {
        // Check if the board is initialized with empty cells
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertEquals(Cell.EMPTY, board.getCell(row, col, 4));
            }
        }
    }

    @Test
    public void testMakeMoveAndDrawCharacter() {
        board.makeMove(1, 1, 4);
        assertEquals(Cell.NOUGHT, board.getCell(1,1,4));

        // Make a move for player 'B' cross bc Red is NOUGHT
        board.makeMove(2, 2, 4);
        assertEquals(Cell.CROSS, board.getCell(2, 2, 4));
    }

    @Test
    public void testWinCondition() {
        // Set up a winning scenario for player 'R'
        board.makeMove(0, 0, 4); // R
        board.makeMove(1, 1, 4); // B
        board.makeMove(0, 1, 4); // R
        board.makeMove(1, 2, 4); // B
        board.makeMove(0, 2, 4); // R (Win here)
        
        board.makeMove(1, 3, 4); // GENERAL KEEPS GONIG
        
        assertEquals(GameState.PLAYING, board.getGameState());
    }
}
