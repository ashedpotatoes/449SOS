package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.main.AutoGeneralBoard;
import src.main.Board;
import src.main.Board.Cell;
import src.main.Board.GameState;
import src.main.GeneralBoard;




import java.util.Arrays;

class AutoGeneralBoardTest {

    private AutoGeneralBoard board;

    @Before
    public void setUp() {
        // Initialize the board with a 3x3 grid and 'B' (computer) as the autoplayer.
        board = new AutoGeneralBoard('B', 3);
    }

    @Test
    public void testInitialization() {
        // Ensure that the board is initialized with the correct dimensions.
        assertNotNull(board, "Board should not be null");
                assertEquals(3, board.totalRows, "Total rows should be 3");
                        assertEquals(3, board.totalCols, "Total columns should be 3");
                    }
                
                
                    private void assertNotNull(AutoGeneralBoard board2, String object) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
            }
        
                    private void assertEquals(int i, int totalRows, String actual) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
            }

    @Test
    public void testStrategicMove() {
        // Test a scenario where a strategic move is possible (i.e., the computer blocks or wins).
        board.resetGame(3, 3);
        
        // Set up a scenario where a strategic move is available (computer can complete a row).
        board.grid[0][0] = Cell.CROSS;
        board.grid[0][1] = Cell.CROSS;
        
        board.compMove(); // The computer should place a 'CROSS' at (0, 2) to complete the row.
        
        assertEquals(Cell.CROSS, board.grid[0][2], "The computer should complete the row with a CROSS at (0, 2).");
    }

    @Test
    public void testTurnChangeAfterMove() {
        // Verify that the turn changes after the computer makes a move.
        board.resetGame(3, 3);
        char initialTurn = board.turn;

        board.compMove(); // Let the computer make a move.
        assertNotEquals(initialTurn, board.turn, "The turn should change after the computer's move.");
            }
        
            private void assertNotEquals(char initialTurn, char turn, String actual) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertNotEquals'");
            }
        
            @Test
            public void testEndgameDetection() {
        // Test that the game correctly detects a winner when a match is made.
        board.resetGame(3, 3);
        
        // Set up a win condition for player 'R' (for example, a horizontal win on the second row).
        board.grid[1][0] = Cell.CROSS;
        board.grid[1][1] = Cell.CROSS;
        board.grid[1][2] = Cell.CROSS;
        
        // Since 'R' placed the winning row (second row), the game should declare 'R' as the winner.
        assertEquals(GameState.NOUGHT_WON, board.getGameState(), "The game should detect player 'R' as the winner.");
    }

    @Test
    public void testResetGame() {
        // Test that the game state is reset correctly.
        board.resetGame(3, 3);
        
        // Modify the board and reset the game.
        board.grid[0][0] = Cell.CROSS;
        board.resetGame(3, 3); // Reset the game.

        assertEquals(Cell.EMPTY, board.grid[0][0], "The board should be reset and the cell should be empty.");
    }

    @Test
    public void testRandomPlacementDoesNotOverwrite() {
        // Test that the computer's random move doesn't overwrite an already occupied cell.
        board.resetGame(3, 3);
        board.grid[0][0] = Cell.NOUGHT; // Manually set a cell to NOUGHT.

        board.compMove(); // Let the computer make its move.
        
        assertEquals(Cell.NOUGHT, board.grid[0][0], "The computer's move should not overwrite an existing NOUGHT in (0, 0).");
            }
        
            private void assertEquals(Cell nought, Cell cell, String actual) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
            }
        
            @Test
            public void testGameEndsWhenFullAndNoWinner() {
        // Test that the game ends in a draw if the board is full and there is no winner.
        board.resetGame(3, 3);
        
        // Fill the board with moves, no winner.
        board.grid[0][0] = Cell.CROSS;
        board.grid[0][1] = Cell.NOUGHT;
        board.grid[0][2] = Cell.CROSS;
        board.grid[1][0] = Cell.NOUGHT;
        board.grid[1][1] = Cell.CROSS;
        board.grid[1][2] = Cell.NOUGHT;
        board.grid[2][0] = Cell.CROSS;
        board.grid[2][1] = Cell.NOUGHT;
        board.grid[2][2] = Cell.CROSS;
        
        assertEquals(GameState.DRAW, board.getGameState(), "The game should end in a draw when the board is full and no winner is found.");
            }
        
            private void assertEquals(GameState draw, GameState gameState, String actual) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
            }


}
