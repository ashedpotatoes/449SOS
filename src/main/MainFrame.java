package src.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

public class MainFrame extends JFrame{
    public void initialize(){
        // text 
        // lines
        // check box 
        // radio buttons
        JButton b1 = new JButton("New Game");
        JRadioButton humanPlayer = new JRadioButton();
        JPanel mainPanel = new JPanel();
        JLabel startGame = new JLabel("Start game!");
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(b1, BorderLayout.NORTH);
        mainPanel.add(startGame, BorderLayout.CENTER);
        mainPanel.add(humanPlayer, BorderLayout.SOUTH);

        add(mainPanel); 

        setTitle("Hello World!");
        setSize(500, 600);
        setMinimumSize(new Dimension(300,  400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true); 
    }

    public static void main(String[] args) {
        MainFrame boardFrame = new MainFrame();
        boardFrame.initialize();
    }
}
