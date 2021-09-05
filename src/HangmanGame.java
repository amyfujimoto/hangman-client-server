// Main class to test the Hang man game

public class HangmanGame {
	public static void main(String[] args){
		UserInput user = new UserInput();
		String word;
		String shouldContinue = "y";
		
		while (shouldContinue.equalsIgnoreCase("y")) {
			word = user.inputString("Player 1: \nPlease enter a word.");
			PlayerWord input = new PlayerWord(word);
			input.changeToUnderscore();
			input.guessTheLetter();
		
			shouldContinue = user.inputString("\nPlay again? (y/n)");
		}
		 
	}
}
