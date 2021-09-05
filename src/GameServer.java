import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private HangmanGame game;
	

	private void start(int port) throws IOException, InterruptedException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		game = new HangmanGame();

		String guess = "";
		while (!stopRequested(guess)) {
			guess = in.readLine();
			System.out.println("Client guessed: \'" + guess + "\'");
			out.println("New game state...");
			
//			if (!game.validateInput()) {
//				continue;
//			}
//			String gameState = game.processGuess(clientInput);
//			System.out.println(gameStateString);
//			out.println(gameStateString);
		}
	}

	private boolean stopRequested(String guess) {
		return "EXIT".equals(guess) || guess == null;
	}

	private void cleanUp() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		GameServer server = new GameServer();
		try {
			server.start(6667);
		} finally {
			server.cleanUp();
		}
	}
}
