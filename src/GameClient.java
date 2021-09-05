import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Scanner scanner;

	private void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		scanner = new Scanner(System.in);
	}

	private String readInput() {
		System.out.println("Enter input: ");
		return scanner.nextLine();
	}

	private String sendMessage(String msg) throws IOException {
		out.println(msg);
		return in.readLine();
	}

	private boolean stopRequested(String guess) {
		return "EXIT".equals(guess);
	}

	private void cleanUp() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		scanner.close();
	}

	public static void main(String[] args) throws IOException {
		GameClient client = new GameClient();
		try {
			client.startConnection("127.0.0.1", 6667);

			String guess = "";
			while (!client.stopRequested(guess)) {
				guess = client.readInput();
				System.out.println(client.sendMessage(guess));
			}
		} finally {
			client.cleanUp();
		}
	}
}

