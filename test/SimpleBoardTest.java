package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.Board;
import src.main.Board.Cell;
import src.main.Board.GameState;
import src.main.SimpleBoard;

public class SimpleBoardTest {
    private SimpleBoard board;

    // Public constructor
    public SimpleBoardTest() {
        // Constructor can be empty or can initialize any class-level variables if needed
    }

    @Before
    public void setUp() {
        board = new SimpleBoard(3); // Initialize a 3x3 board
    }

    @Test
    public void testBoardInitialization() {
        // Check if the board is initialized with empty cells
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals(Board.Cell.EMPTY, board.getCell(row, col, 3));
            }
        }
        assertEquals(Board.GameState.PLAYING, board.getGameState());
    }

    @Test
    public void testMakeMoveAndDrawCharacter() {
        board.makeMove(0, 0, 3);
        board.setPlayersPref('O');
        assertEquals(Board.Cell.NOUGHT, board.getCell(0, 0, 3));

        // Make a move for player 'B' (CROSS BC PLAYER R IS NOUGHT)
        board.makeMove(1, 1, 3);
        assertEquals(Board.Cell.CROSS, board.getCell(1, 1, 3));
    }

    @Test
    public void testWinCondition() {
        // Simulate moves to create a winning condition for player 'R'
        board.makeMove(0, 0, 3); // R
        board.makeMove(0, 1, 3); // B
        board.makeMove(0, 2, 3); // R (Winning move)
        
        // Check if 'R' has won
        assertEquals(0, board.getBlueScore());
    }


    @Test
    public void testInvalidMove() {
        // Attempt to make a move in an occupied cell
        board.makeMove(0, 0, 3); // R
        assertEquals(Board.Cell.NOUGHT, board.getCell(0, 0, 3));
        
        // Attempt to overwrite the cell
        board.makeMove(0, 0, 3); 
        assertEquals(Board.Cell.NOUGHT, board.getCell(0, 0, 3)); 
    }
}
