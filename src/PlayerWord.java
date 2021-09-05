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
			
		}	
		
		return underscore;
	}
	
	public void guessTheLetter() {
		word = getWord();
		int j = 0;
		char letter;
		char[] usedLetter = new char[25];
		char[] wordArray = word.toCharArray();
		String[] underscore = changeToUnderscore();
		
		while (life > 0) {
			letter = user.inputChar("\nPlayer 2: \nEnter a letter.");
			usedLetter[j] = letter;
			
			if (j != 0) {
				for (char ch : usedLetter) {
					if (ch == letter) {
						System.out.println("Please enter a letter that is not used");
						break;
					} 
				}
			}
			
			for (char c : wordArray) {
				if (c == letter) {
					for (int i = 0; i < word.length(); i++) {	
						
						if (Character.isWhitespace(wordArray[i])) {
							underscore[i] = "  ";
						} else if (wordArray[i] == letter) {
							underscore[i] = wordArray[i] + " ";
						} else {
							underscore[i] = "_ ";
						}
						
						System.out.print(underscore[i]);
					}
				} 
			}
			
			System.out.println("\nLetters used: " + usedLetter[j]);
			j++;
			
		}
		
		if (life == 0) {
			System.out.println("Sorry, no more life left.");
		}
		
	}
	
	private boolean hasLetter(char[] word, char letter) {
		for (char c : word) {
			if (c == letter) {
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
