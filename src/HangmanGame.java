// Class to process user inputted word 

public class HangmanGame {
	private String word;
	private int life = 6;
	private char[] usedLetter;
	private String[] underscore;
	
	
	public HangmanGame(){
		this("");
	}
	
	public HangmanGame(String word) {
		this.word = word;
		this.underscore = initUnderscore();
		this.usedLetter = new char[26];
		for (int i = 0; i < 26; i++) {
			usedLetter[i] = '1';
		}
	}

	/*
	 * method to guess the letters in the word entered by user
	 */
	public String guessTheLetter(char letter) {
		char[] wordArray = word.toCharArray();
			
		if(contains(usedLetter, letter) || Character.isDigit(letter)) {
			return "Please enter a valid letter.";
		}
		
		record(letter);
		
		if (contains(wordArray, letter)) {
			underscore = guessLetter(letter, underscore);
			
			if (!hasUnderscore(underscore)) {
				return "Congratulations, you have guessed all the letters in the word. Play again? (y/n)";
			}
			
			return toString(underscore);
			
		} else {
			life--;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(letter).append(" not in the word.");
			
			if (life == 0) {
				stringBuilder.append("Sorry, no more life left. Play again? (y/n)");
			} 
			
			return stringBuilder.toString();
		}
	
	}
	
	/*
	 * returns all the guessed letters
	 */
	public String getLettersUsed() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < 26; i++) {
			if (usedLetter[i] != '1') {
				stringBuilder.append(usedLetter[i]);
			} else {
				break;
			}
		}

		return stringBuilder.toString();
	}

	public int getLife() {
		return life;
	}

	public boolean isFinished() {
		return !hasUnderscore(underscore) || life <= 0;
	}

	/*
	 * Changes the user inputted word to underscore to start the game
	 */
	private String[] initUnderscore() {
		char[] wordArray = word.toCharArray();
		String[] underscore = new String[word.length()];
		
		for (int i = 0; i < word.length(); i++) {	
			if (Character.isWhitespace(wordArray[i])) {
				underscore[i] = "  ";
			} else {
				underscore[i] = "_ ";
			}
		}	
		
		return underscore;
	}
	
	/*
	 * converts string array to string format
	 */
	private String toString(String[] stringArray) {
		StringBuilder stringBuilder = new StringBuilder();
		 
		for (int i = 0; i < stringArray.length; i++) {
			stringBuilder.append(stringArray[i]);
		}
	      
		return stringBuilder.toString();
	}
	
	/*
	 * records all guesses and returns them
	 */
	private void record(char letter) {
		
		for(int i = 0; i < 26; i++) {
			if (usedLetter[i] == '1') {
				usedLetter[i] = letter;
				break;
			}
		}
	}
	
	/*
	 * checks to see if letter appears in the word
	 */
	private boolean contains(char[] word, char letter) {
		for (char c : word) {
			if (c == letter) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns word by filling in correct guessed letters
	 */
	private String[] guessLetter(char letter, String[] underscore) {
		for (int i = 0; i < word.length(); ++i) {
			if (word.charAt(i) == letter) {
				underscore[i] = letter + " ";
			} 
			
		}
		return underscore;
	}
	
	/*
	 * checks whether all the letters in the words were guessed
	 */
	private boolean hasUnderscore(String[] underscore) {
		for (int i = 0; i < word.length(); i++) {
			if (underscore[i].equals("_ ")) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		HangmanGame game = new HangmanGame("test");
		System.out.print(game.guessTheLetter('1'));
		System.out.println(game.getLettersUsed());
		
		System.out.print(game.guessTheLetter('t'));
		System.out.println(game.getLettersUsed());
		
		System.out.print(game.guessTheLetter('t'));
		System.out.println(game.getLettersUsed());
	}
}
