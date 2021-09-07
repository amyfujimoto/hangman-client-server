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
		word = getWord();
		char[] wordArray = word.toCharArray();
		String[] underscore = changeToUnderscore();
			
		correctLetter(letter);
		
		usedLetter = recordLetter(letter, usedLetter);
		
		if (hasLetter(wordArray, letter)) {
			underscore = guessLetter(wordArray, letter, underscore);
			
			if (!hasUnderscore(underscore, wordArray)) {
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
	private String correctLetter(char letter) {
		while (hasLetter(usedLetter, letter) || Character.isDigit(letter)) {
			return "Please enter a valid letter.";
		}
		return null;
	}
	
	/*
	 * Changes the user inputted word to underscore to start the game
	 */
	private String[] changeToUnderscore() {
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
	public char[] getLettersUsed() {
		return usedLetter;
	}
	
	/*
	 * records all guesses and returns them
	 */
	private char[] recordLetter(char letter, char[] usedArray) {
		
		for(int i = 0; i < 26; i++) {
			if (usedArray[i] == 1) {
				usedArray[i] = letter;
				return usedArray;
			}
		}
		
		return usedArray;
		
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
