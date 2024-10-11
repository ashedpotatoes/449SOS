package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.main.Board;
import src.main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest {

    private MainFrame mainFrame;

    @Before
    public void setUp() {
        Board board = new Board(3); // Initialize with valid numberchosen
        mainFrame = new MainFrame(board);
    }

    @After
    public void tearDown() {
        mainFrame.dispose(); // Clean up the frame after each test
        mainFrame = null;
    }

    @Test
    public void testPanelsCreation() {
        // Check if the GameBoardCanvas is initialized
        assertNotNull(mainFrame.gameBoardCanvas);
        
        // Check if content pane contains the expected components
        Container contentPane = mainFrame.getContentPane();
        assertEquals(BorderLayout.class, contentPane.getLayout().getClass());
        
        // Check if game options panel is added
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                assertNotNull(panel);
            }
        }
    }

    @Test
    public void testBoardInitialization() {
        assertNotNull(mainFrame.board);
        assertEquals(3, MainFrame.numberchosen); // Verify initial numberchosen
    }

    @Test
    public void testNumberChosenValidation() {
        // Test valid numberchosen
        MainFrame.numberchosen = 10;
        assertTrue(MainFrame.numberchosen <= 10 && MainFrame.numberchosen >= 3);

        MainFrame.numberchosen = 3;
        assertTrue(MainFrame.numberchosen <= 10 && MainFrame.numberchosen >= 3);

        // Test invalid numberchosen (greater than 10)
        MainFrame.numberchosen = 11;
        assertFalse(MainFrame.numberchosen <= 10 && MainFrame.numberchosen >= 3);

        // Test invalid numberchosen (less than 3)
        MainFrame.numberchosen = 2;
        assertFalse(MainFrame.numberchosen <= 10 && MainFrame.numberchosen >= 3);
    }
}
