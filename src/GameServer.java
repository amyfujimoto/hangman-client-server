import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer implements AutoCloseable {
	public static final String SERVER_IP = "localhost";
	private static final Logger log = Logger.getLogger(GameServer.class.getName());

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private HangmanGame game;

	@Override
	public void close() throws Exception {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	private void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		game = new HangmanGame(new UserInput().inputString("Enter the secret word:"));
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		while (true) {
			if (!handleClientMsg()) {
				if (game.isFinished()) {
					String clientMsg = in.readLine();
					if (clientMsg.equals("y")) {
						game = new HangmanGame(new UserInput().inputString("Enter the secret word:"));
						out.println("New game started. Guess again!");
					} else if (clientMsg.equals("n")) {
						out.println("Thanks for playing! Bye!");
						break;
					} else {
						out.println("Invalid entry. Play again? (y/n)");
					}
				} else {
					// The client disconnected, stop the game.
					break;
				}
			}
		}
	}

	/**
	 * Returns whether the game is in progress
	 */
	private boolean handleClientMsg() throws IOException {
		try {
			if (game.isFinished()) {
				return false;
			}

			String clientMsg = in.readLine();

			if (clientMsg == null) {
				log.log(Level.WARNING, "Client disconnected. Shutting down the server.");
				return false;
			}

			String newState = game.guessTheLetter(clientMsg.charAt(0));
			log.info(newState);
			out.println(newState + " Letters used: " + game.getLettersUsed() + " Lives left: " + game.getLife());
		} catch (SocketException e) {
			log.log(Level.WARNING, "Client disconnected. Shutting down the server.", e);
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		try (GameServer server = new GameServer()) {
			server.start(6667);
		}
	}
}
