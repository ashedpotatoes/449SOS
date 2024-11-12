package src.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import src.main.Board.Cell;
import src.main.Board.GameState;

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
    private JLabel gameStatusBar;

    public Board board;
    public static String GameType, redPlayerType, bluePlayerType; 
    protected char turn;
    public char playersChoice; 

    public enum GameMode {
		SIMPLE, GENERAL
	}


    public MainFrame(Board board){
        this.board = board;
        setContentPane();
        setTitle("SOS");
        setSize(500, 600);
        setMinimumSize(new Dimension(300,  400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true); 
    }

    public void setContentPane(){
		gameBoardCanvas = new GameBoardCanvas();  
		CANVAS_WIDTH = CELL_SIZE * numberchosen;  
		CANVAS_HEIGHT = CELL_SIZE * numberchosen;
		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
        JButton b1 = new JButton("New Game");

        b1.addActionListener(resetAction);

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
        simpleGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                //Here goes the action (method) you want to execute when clicked
                GameType = "simple";
                System.out.println("Simple game");
            }
        });  
        generalGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                //Here goes the action (method) you want to execute when clicked
                GameType = "general";
                 System.out.println("general game");
            }
        });  

 
        JPanel gameOptions = new JPanel();

        gameOptions.add("Simple Game", simpleGame);
        gameOptions.add("General Game", generalGame);
        gameOptions.add(b1);

    
        JPanel playerOne = new JPanel();
        playerOne.add(humanPlayer, BorderLayout.NORTH);
        playerOne.add(humanPlayerS, BorderLayout.CENTER);
        playerOne.add(humanPlayerO, BorderLayout.SOUTH);
        JRadioButton humanPlayerR = new JRadioButton();
        JRadioButton compPlayerR = new JRadioButton();
        playerOne.add(humanPlayerR);
        playerOne.add(compPlayerR);
        humanPlayerR.setText("Human Player");
        compPlayerR.setText("Computer Player");
        
        humanPlayerS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 board.setPlayersPref('S'); 
            }
        });  

        humanPlayerO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 board.setPlayersPref('O'); 
            }
        });
        humanPlayerR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 redPlayerType = "human";
                 System.out.println("human is selected"); 
            }
        });
        compPlayerR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 redPlayerType = "comp";
                 System.out.println("comp is selected for r"); 
            }
        });

        JPanel playerTwo = new JPanel();
        playerTwo.add(compPlayer, BorderLayout.EAST);
        playerTwo.add(compPlayerS, BorderLayout.EAST);
        playerTwo.add(compPlayerO, BorderLayout.EAST);
        JRadioButton humanPlayerB = new JRadioButton();
        JRadioButton compPlayerB = new JRadioButton();
        playerTwo.add(humanPlayerB);
        playerTwo.add(compPlayerB);
        humanPlayerB.setText("Human Opponent");
        compPlayerB.setText("Computer Opponent");
        contentPane.setLayout(new BorderLayout());
        humanPlayerB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 bluePlayerType = "human";
                 System.out.println("human is selected"); 
            }
        });
        compPlayerB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                 bluePlayerType = "comp";
                 System.out.println("comp is selected for b"); 
            }
        });

        gameStatusBar = new JLabel("  ");
        gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        contentPane.add(gameOptions, BorderLayout.NORTH);
        contentPane.add(playerOne, BorderLayout.WEST);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
        contentPane.add(playerTwo, BorderLayout.EAST);
        contentPane.add(gameStatusBar, BorderLayout.PAGE_END);
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
            printStatusBar();
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
					if (board.getCell(row,col, numberchosen) == Cell.CROSS) {
						g2d.setColor(Color.BLACK);
						int y2 = (row + 1) * CELL_SIZE - (CELL_PADDING * 3) ;
                        g2d.drawArc( x1, y1, SYMBOL_SIZE / 2, SYMBOL_SIZE / 2, 90, 180);
                        g2d.drawArc( x1, y2 , SYMBOL_SIZE / 2, SYMBOL_SIZE / 2, 90, -180);
					} else if (board.getCell(row,col, numberchosen) == Cell.NOUGHT) {
						g2d.setColor(Color.BLACK);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}
        private void printStatusBar() {
			if (board.getGameState() == GameState.PLAYING) {
				gameStatusBar.setForeground(Color.BLACK);
				if (board.getTurn() == 'R') {
					gameStatusBar.setText("Red's Turn" + playersChoice);
				} else {
					gameStatusBar.setText("Blue's Turn");
				}
			} else if (board.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.MAGENTA);
				gameStatusBar.setText("It's a Draw! Click to play again.");
			} else if (board.getGameState() == GameState.CROSS_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Red Won! Click to play again.");
			} else if (board.getGameState() == GameState.NOUGHT_WON) {
				gameStatusBar.setForeground(Color.BLUE);
				gameStatusBar.setText("Blue Won! Click to play again.");
			}
		}

        

	}

    ActionListener resetAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
            String desiredNumber = JOptionPane.showInputDialog("Please enter a board size [3, 10]");
            int ans = Integer.parseInt(desiredNumber);
            while (ans > 10 || ans < 3){
                desiredNumber = JOptionPane.showInputDialog("Please enter a board size [3, 10]");
                ans = Integer.parseInt(desiredNumber);
            }
            numberchosen = ans; 
            SwingUtilities.invokeLater(new Runnable (){
                public void run (){
                    if (GameType == "general" && redPlayerType == "human" && bluePlayerType == "human"){
                        new MainFrame(new GeneralBoard(numberchosen));
                        System.out.println("general human");
                    }
                     if (GameType == "general" && redPlayerType == "comp" && bluePlayerType == "human"){
                        new MainFrame(new AutoGeneralBoard('R', numberchosen));
                        System.out.println("general human b");
                    }
                     if (GameType == "general" && redPlayerType == "human" && bluePlayerType == "comp"){
                        new MainFrame(new AutoGeneralBoard('B', numberchosen));
                    }
                     if (GameType == "general" && redPlayerType == "comp" && bluePlayerType == "comp"){
                        new MainFrame(new AutoGeneralBoard('Z', numberchosen));
                    }
                     if (GameType == "simple" && redPlayerType == "comp" && bluePlayerType == "human"){
                        new MainFrame(new AutoSimpleBoard('R', numberchosen));
                    }
                     if (GameType == "simple" && redPlayerType == "human" && bluePlayerType == "comp"){
                        new MainFrame(new AutoSimpleBoard('B', numberchosen));
                    }
                     if (GameType == "simple" && redPlayerType == "comp" && bluePlayerType == "comp"){
                        new MainFrame(new AutoSimpleBoard('Z', numberchosen));
                }
                if (GameType == "simple" && redPlayerType == "human" && bluePlayerType == "human"){
                    new MainFrame(new SimpleBoard(numberchosen));
                    System.out.println("just defaults");
                }
                }
            });
			
		}
	};



    
    public static void main(String[] args) {
        new MainFrame(new SimpleBoard(3));
    }
}