package src.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MainFrame extends JFrame{

    public static final int CELL_SIZE = 100; 
	public static final int GRID_WIDTH = 8; 
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
	public static final int SYMBOL_STROKE_WIDTH = 8; 
    public static int numberchosen = 3; 

	private int CANVAS_WIDTH;
	private int CANVAS_HEIGHT;

    public GameBoardCanvas gameBoardCanvas; 

    public Board board;


    public MainFrame(Board board){
        this.board = board;
        setContentPane();
        setTitle("SOS");
        setSize(500, 600);
        setMinimumSize(new Dimension(300,  400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true); 
    }
    private void setContentPane(){
		gameBoardCanvas = new GameBoardCanvas();  
		CANVAS_WIDTH = CELL_SIZE * numberchosen;  
		CANVAS_HEIGHT = CELL_SIZE * numberchosen;
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
        JButton b1 = new JButton("New Game");

        ButtonGroup redPlayer = new ButtonGroup();
        JLabel humanPlayer = new JLabel("Red player");
        JRadioButton humanPlayerS = new JRadioButton();
        redPlayer.add(humanPlayerS);
        humanPlayerS.setText("S");
        JRadioButton humanPlayerO = new JRadioButton();
        redPlayer.add(humanPlayerO);
        humanPlayerO.setText("O");


        JLabel compPlayer = new JLabel("Blue player");
        ButtonGroup BlueP = new ButtonGroup();
        JRadioButton compPlayerS = new JRadioButton();
        compPlayerS.setText("S");
        JRadioButton compPlayerO = new JRadioButton();
        compPlayerO.setText("O");
        BlueP.add(compPlayerS);
        BlueP.add(compPlayerO);

        ButtonGroup gameModes = new ButtonGroup();
        JRadioButton simpleGame = new JRadioButton();
        simpleGame.setText("Simple Game");
        JRadioButton generalGame = new JRadioButton();
        generalGame.setText("General Game");
        gameModes.add(simpleGame);
        gameModes.add(generalGame); 

 
        JPanel gameOptions = new JPanel();
        gameOptions.add("Simple Game", simpleGame);
        gameOptions.add("General Game", generalGame);
        gameOptions.add(b1);


        JPanel playerOne = new JPanel();
        playerOne.add(humanPlayer, BorderLayout.NORTH);
        playerOne.add(humanPlayerS, BorderLayout.CENTER);
        playerOne.add(humanPlayerO, BorderLayout.SOUTH);

        JPanel playerTwo = new JPanel();
        playerTwo.add(compPlayer, BorderLayout.EAST);
        playerTwo.add(compPlayerS, BorderLayout.EAST);
        playerTwo.add(compPlayerO, BorderLayout.EAST);
        contentPane.setLayout(new BorderLayout());

        contentPane.add(gameOptions, BorderLayout.NORTH);
        contentPane.add(playerOne, BorderLayout.WEST);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
        contentPane.add(playerTwo, BorderLayout.EAST);
	}

	class GameBoardCanvas extends JPanel {
		
		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						board.makeMove(rowSelected, colSelected, numberchosen);
					repaint(); 
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g, numberchosen);
			drawBoard(g);
		}
		
		private void drawGridLines(Graphics g, int numberchosen){
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < numberchosen; row++) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < numberchosen; col++) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}

		}

		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int row = 0; row < numberchosen; row++) {
				for (int col = 0; col < numberchosen; col++) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (board.getCell(row,col, numberchosen) == 1) {
						g2d.setColor(Color.BLACK);
						int y2 = (row + 1) * CELL_SIZE - (CELL_PADDING * 3) ;
                        g2d.drawArc( x1, y1, SYMBOL_SIZE / 2, SYMBOL_SIZE / 2, 90, 180);
                        g2d.drawArc( x1, y2 , SYMBOL_SIZE / 2, SYMBOL_SIZE / 2, 90, -180);
					} else if (board.getCell(row,col, numberchosen) == 2) {
						g2d.setColor(Color.BLACK);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}

	}


    public static void main(String[] args) {
        String desiredNumber = JOptionPane.showInputDialog("Please enter a board size [3, 10]");
        int ans = Integer.parseInt(desiredNumber);
        while (ans > 10 || ans < 3){
            desiredNumber = JOptionPane.showInputDialog("Please enter a board size [3, 10]");
            ans = Integer.parseInt(desiredNumber);
        }
        numberchosen = ans; 
        SwingUtilities.invokeLater(new Runnable (){
            public void run (){
                new MainFrame(new Board (numberchosen)); 
            }
        });
    }
}
