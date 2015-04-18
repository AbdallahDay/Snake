package abdallah;

import java.awt.*;
import java.util.Random;

/**
 * Abdallah Day
 */

public class Kibble {

	/** Identifies a random square to display a kibble
	 * Any square is ok, so long as it doesn't have any snake segments in it. 
	 * 
	 */
	
	private int kibbleX; //This is the square number (not pixel)
	private int kibbleY;  //This is the square number (not pixel)

	private Color kibbleColor;	//Represents the color of the kibble
	private int points;			//Number of points received for eating the kibble
	
	public Kibble(Snake s){
		//Kibble needs to know where the snake is, so it does not create a kibble in the snake
		//Pick a random location for kibble, check if it is in the snake
		//If in snake, try again

		moveKibble(s);
	}
	
	protected void moveKibble(Snake s){

		//Determine the type of the next kibble, then display it
		final int NORMAL = 0;
		final int SPECIAL = 1;
		int[] kibbleTypes = {NORMAL, NORMAL, NORMAL, SPECIAL};	// 3x more likely to get normal kibble
		Random rng = new Random();

		int kibbleType = kibbleTypes[rng.nextInt(kibbleTypes.length)];	//gets a random kibble type from array

		switch (kibbleType) {
			case NORMAL: {
				this.kibbleColor = Color.GREEN;		//Normal kibbles are green
				this.points = 1;					//Normal kibble is worth 1 point
				break;
			}
			case SPECIAL: {
				this.kibbleColor = Color.ORANGE;	//Special kibbles are orange
				this.points = 3;					//Special kibble is worth 3 points
				break;
			}
		}

		boolean kibbleInSnake = true;
		while (kibbleInSnake == true) {
			//Generate random kibble location
			kibbleX = rng.nextInt(SnakeGame.xSquares);
			kibbleY = rng.nextInt(SnakeGame.ySquares);
			kibbleInSnake = s.isSnakeSegment(kibbleX, kibbleY);
		}
	}

	public int getKibbleX() {
		return kibbleX;
	}

	public int getKibbleY() {
		return kibbleY;
	}

	public Color getKibbleColor() {
		return this.kibbleColor;
	}

	public int getPoints() {
		return this.points;
	}
}
