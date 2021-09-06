// Class to process user inputted word 

public class PlayerWord {
	private String word;
	private int life = 6;
	private char[] usedLetter;
	
	public PlayerWord(){
		this("");
	}
	
	public PlayerWord(String word) {
		this.word = word;
		this.usedLetter = new char[26];
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	/*
	 * Changes the user inputted word to underscore to start the game
	 */
	public String[] changeToUnderscore() {
		word = getWord();
		char[] wordArray = word.toCharArray();
		String[] underscore = new String[word.length()];
		
		for (int i = 0; i < word.length(); i++) {	
			if (Character.isWhitespace(wordArray[i])) {
				underscore[i] = "  ";
			} else {
				underscore[i] = "_ ";
			}
			
			System.out.print(underscore[i]);
		}	
		
		return underscore;
	}
	
	/*
	 * method to guess the letters in the word entered by user
	 */
	public String guessTheLetter(char letter) {
		word = getWord();
		int j = 0;
		char[] wordArray = word.toCharArray();
		String[] underscore = changeToUnderscore();
			
		if (hasLetter(usedLetter, letter)) {
			return "Please enter a letter that is not used";
		}
		
		usedLetter[j] = letter;
		/*
		 * TODO:
		 * replace with new record letter method
		 */
		
		if (hasLetter(wordArray, letter)) {
			underscore = guessLetter(wordArray, letter, underscore);
			
			if (!hasUnderscore(underscore, wordArray)) {
				return "Congratulations, you have guessed all the letters in the word.";
			}
			
			/*
			 * TODO:
			 * change String[] to String 
			 */
		} else {
			life--;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Letter not in the word.");
			
			if (life == 0) {
				stringBuilder.append("Sorry, no more life left.");
			} 
			
			return stringBuilder.toString();
		}
		
		return null; //TODO: remove when method is fixed
	}
	
	/*
	 * returns all the guessed letters
	 */
	public char[] getLettersUsed() {
		return usedLetter;
	}
	
	/*
	 * checks to see if there are any repeated letters
	 */
	private boolean hasLetter(char[] word, char letter) {
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
	private String[] guessLetter(char[] wordArray, char letter, String[] underscore) {
		for (char c : wordArray) {
			if (c == letter) {
				for (int i = 0; i < word.length(); i++) {	
					
					if (wordArray[i] == letter) {
						underscore[i] = wordArray[i] + " ";
					} 
					System.out.print(underscore[i]);
					
				}
			} 
			
		}
		return underscore;
	}
	
	/*
	 * checks whether all the letters in the words were guessed
	 */
	private boolean hasUnderscore(String[] underscore, char[] wordArray) {
		
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
