package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.main.Board;
import src.main.Board.Cell;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board(3); // Initialize a 3x3 board
    }

    @After
    public void tearDown() {
        board = null; // Clean up after each test
    }

    @Test
    public void testBoardInitialization() {
        // Check all cells are initialized to 0
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(Cell.EMPTY, board.getCell(i, j, 3));
            }
        }
    }

    @Test
    public void testMakeMoveInvalidRow() {
        // Attempt to make a move in an invalid row (-1)
        board.makeMove(-1, 0, 3);
        // Verify the cell remains unchanged (should still be 0)
        assertEquals(Cell.EMPTY, board.getCell(0, 0, 3));
    }

    @Test
    public void testMakeMoveInvalidColumn() {
        // Attempt to make a move in an invalid column (3)
        board.makeMove(0, 3, 3);
        // Verify the cell remains unchanged (should still be 0)
        assertEquals(Cell.EMPTY, board.getCell(0, 0, 3));
    }

    @Test
    public void testMakeMoveValid() {
        // Make a valid move
        board.makeMove(0, 0, 3);
        // Verify that the cell is updated correctly
        assertEquals(Cell.CROSS, board.getCell(0, 0, 3));
        // Check the turn has switched
        assertEquals('B', board.getTurn());
    }
}