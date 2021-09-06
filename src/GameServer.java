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
	private static final Logger log = Logger.getLogger(GameServer.class.getName());

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	@Override
	public void close() throws Exception {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	private void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		while (true) {
			if (!handleClientMsg())
				break;
		}
	}

	private boolean handleClientMsg() throws IOException {
		try {
			String clientMsg = in.readLine();
			if (clientMsg == null) {
				log.log(Level.WARNING, "Client disconnected. Shutting down the server.");
				return false;
			}
			log.info(String.format("Client sent: \'%s\'", clientMsg));
			out.println("Msg received.");
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
