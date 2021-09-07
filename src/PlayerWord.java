// Class to process user inputted word 

public class PlayerWord {
	private String word;
	private int life = 6;
	private char[] usedLetter;
	private String[] underscore;
	
	
	public PlayerWord(){
		this("");
	}
	
	public PlayerWord(String word) {
		this.word = word;
		this.underscore = initUnderscore();
		this.usedLetter = new char[26];
		for (int i = 0; i < 26; i++) {
			usedLetter[i] = 1;
		}
	}
	
	// Getter for word
	public String getWord() {
		return word;
	}
	
	// Setter for word
	public void setWord(String word) {
		this.word = word;
	}

	/*
	 * method to guess the letters in the word entered by user
	 */
	public String guessTheLetter(char letter) {
		char[] wordArray = word.toCharArray();
			
		validate(letter);
		record(letter);
		
		if (contains(wordArray, letter)) {
			underscore = guessLetter(letter, underscore);
			
			if (!hasUnderscore(underscore)) {
				return "Congratulations, you have guessed all the letters in the word.";
			}
			
			return toString(underscore);
			
		} else {
			life--;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Letter not in the word.");
			
			if (life == 0) {
				stringBuilder.append("Sorry, no more life left.");
			} 
			
			return stringBuilder.toString();
		}
	
	}
	
	/*
	 * checks to see if the entered char is valid.
	 */
	private String validate(char letter) {
		while (contains(usedLetter, letter) || Character.isDigit(letter)) {
			return "Please enter a valid letter.";
		}
		return null;
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
		 StringBuffer stringBuffer = new StringBuffer();
		 
	      for(int i = 0; i < stringArray.length; i++) {
	         stringBuffer.append(stringArray[i]);
	      }
	      
	      return stringBuffer.toString();
	      
	}
	
	/*
	 * returns all the guessed letters
	 */
	public String getLettersUsed() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (char c : usedLetter) {
			if (c != '1') {
				stringBuilder.append(usedLetter);
			} else {
				break;
			}
			
		}
		
		return stringBuilder.toString();
		
	}
	
	/*
	 * records all guesses and returns them
	 */
	private void record(char letter) {
		
		for(int i = 0; i < 26; i++) {
			if (usedLetter[i] == 1) {
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
	
	/*
	 * This method allows the user to guess the word
	 */
	public boolean guessTheWord(String wordGuess) {
		boolean guess = false;
		word = getWord();
		
		if (wordGuess.equalsIgnoreCase(word)) {
			guess = true;
		}
		
		return guess;
	}
	
}
