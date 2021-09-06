// Class to process user inputted word 

public class PlayerWord {
	private String word;
	private int life = 6;
	UserInput user = new UserInput();
	
	public PlayerWord(){
		this("");
	}
	
	public PlayerWord(String word) {
		this.word = word;
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
	public void guessTheLetter() {
		word = getWord();
		int j = 0;
		char letter;
		char[] usedLetter = new char[25];
		char[] wordArray = word.toCharArray();
		String[] underscore = changeToUnderscore();
		
		while (life > 0 && hasUnderscore(underscore, wordArray)) {
			letter = user.inputChar("\nPlayer 2: \nEnter a letter.");
			
			if (hasLetter(usedLetter, letter)) {
				System.out.println("Please enter a letter that is not used");
			} else {
				usedLetter[j] = letter;
				
				if (hasLetter(wordArray, letter)) {
					underscore = guessLetter(wordArray, letter, underscore);
				} else {
					System.out.println("Letter not in the word.");
					life--;
				}	
				
			}
			
			System.out.println("\nLetters used: " + new String(usedLetter));
			j++;
			
			if (life == 0) {
				System.out.println("Sorry, no more life left.");
			} else if (!hasUnderscore(underscore, wordArray)) {
				System.out.println("Congratulations, you have guessed all the letters in the word.");
			}
			
		}
		
		
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
