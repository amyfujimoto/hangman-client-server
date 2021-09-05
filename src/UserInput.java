// Class that reads user inputs

import java.util.Scanner;

public class UserInput {
	private Scanner scanner = new Scanner(System.in);

	public String inputString() {
		String word = scanner.nextLine();
		return word;
	}
	
	public String inputString(String message) {
		System.out.println(message);
		String word = inputString();
		return word;
	}
	
	public char inputChar() {
		char letter = scanner.next().charAt(0);
		return letter;
	}

	public char inputChar(String message) {
		System.out.println(message);
		char letter = inputChar();
		return letter;
	}
}
