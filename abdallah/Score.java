package abdallah;

/** Keeps track of, and display the user's score
 *
 * Abdallah Day
 */


public class Score {

	protected static int score;
	protected static int highScore = 0;
	
	public Score(){
		score = 0;

	}
	
	public static void resetScore() {
		score = 0;	
	}
	
	public static void increaseScore(Kibble kibble) {
		
		score += kibble.getPoints();	//score increases depending on kibble type
		
	}
	
	public int getScore(){
		return score;
	}
	
	//Checks if current score is greater than the current high score. 
	//updates high score and returns true if so.
	
	public boolean gameOver(){
		
		if (score > highScore) {
			highScore = score;
			return true;
		}
		return false;
	}

	//These methods are useful for displaying score at the end of the game
	
	public String getStringScore() {
		return Integer.toString(score);
	}

	public String newHighScore() {
		
		if (score > highScore) {
			highScore = score;
			return "New High Score!!";
		} else {
			return "";
		}
	}

	public String getStringHighScore() {
		return Integer.toString(highScore);
	}
	
}

