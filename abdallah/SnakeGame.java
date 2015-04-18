package abdallah;

import java.util.ArrayList;
import java.util.Timer;

import javax.swing.*;

/**
 * Abdallah Day
 */

public class SnakeGame {

	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;

	public static int xSquares ;
	public static int ySquares ;

	//Square size
	static final int SMALL_SQUARES = 25;	//25x25-pixel squares
	static final int LARGE_SQUARES = 50;	//50x50-pixel squares

	public static int squareSize = LARGE_SQUARES;	//defaults at 50x50

	public static ArrayList<int[]> largeSquareMazeLines = new ArrayList<int[]>();

	protected static Snake snake ;

	protected static Kibble kibble;

	protected static Score score;

	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;   //The values are not important. The important thing is to use the constants 
	//instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
	//Using constant names instead makes it easier to keep it straight. Refer to these variables 
	//using statements such as SnakeGame.GAME_OVER 

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will need to query this, and change it's value

	//represent game speed options (in milliseconds)
	static final int ROSY_BOA = 800;
	static final int KING_COBRA = 500;
	static final int BLACK_MAMBA = 200;

	protected static int gameSpeed = KING_COBRA;	//controls game speed (defaults to medium: 500 ms)
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1  second.

	public static boolean mazeOn = false;

	public static boolean warpWallsOn = false;

	static JFrame snakeFrame = null;
	static DrawSnakeGamePanel snakePanel;
	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html

	private static void createAndShowGUI() {
		if (snakeFrame != null) {
			snakeFrame.dispose();
		}

		//Create and set up the window.
		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
		snakeFrame.setUndecorated(true); //hide title bar
		snakeFrame.setVisible(true);
		snakeFrame.setResizable(false);

		snakePanel = new DrawSnakeGamePanel(snake, kibble, score);
		snakePanel.setFocusable(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

		snakeFrame.add(snakePanel);
		snakePanel.addKeyListener(new GameControls(snake));

		setGameStage(BEFORE_GAME);

		snakeFrame.setVisible(true);
	}

	private static void initializeGame() {
		//set up score, snake and first kibble
		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		// add maze line coordinates {x1, y1, x2, y2}
		largeSquareMazeLines.add(new int[]{50, 150, 50, 400});
		largeSquareMazeLines.add(new int[]{50, 400, 250, 400});
		largeSquareMazeLines.add(new int[]{250, 350, 250, 450});
		largeSquareMazeLines.add(new int[]{250, 350, 350, 350});
		largeSquareMazeLines.add(new int[]{400, 450, 450, 450});
		largeSquareMazeLines.add(new int[]{450, 300, 450, 450});
		largeSquareMazeLines.add(new int[]{450, 100, 450, 250});
		largeSquareMazeLines.add(new int[]{250, 250, 450, 250});
		largeSquareMazeLines.add(new int[]{250, 150, 250, 250});
		largeSquareMazeLines.add(new int[]{150, 50, 150, 150});
		largeSquareMazeLines.add(new int[]{150, 50, 350, 50});
		largeSquareMazeLines.add(new int[]{350, 50, 350, 100});

		snake = new Snake(xSquares, ySquares, squareSize);
		kibble = new Kibble(snake);
		score = new Score();

		gameStage = BEFORE_GAME;
	}

	protected static void newGame() {
		Timer timer = new Timer();
		GameClock clockTick = new GameClock(snake, kibble, score, snakePanel);
		timer.scheduleAtFixedRate(clockTick, 0, gameSpeed);
	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				restart();
			}
		});
	}

	public static void restart() {
		initializeGame();
		createAndShowGUI();
	}

	public static int getGameStage() {
		return gameStage;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}

	public static int getGameSpeed() {
		return gameSpeed;
	}

	public static void setGameSpeed(int speed) {
		SnakeGame.gameSpeed = speed;
	}

	public static boolean gameEnded() {
		if (gameStage == GAME_OVER || gameStage == GAME_WON){
			return true;
		}
		return false;
	}
}
