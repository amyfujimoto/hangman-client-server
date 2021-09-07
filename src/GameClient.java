import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient implements AutoCloseable {
	private static final Logger log = Logger.getLogger(GameClient.class.getName());

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Scanner scanner;

	@Override
	public void close() throws Exception {
		in.close();
		out.close();
		socket.close();
		scanner.close();
	}

	private void startConnection(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		scanner = new Scanner(System.in);
		System.out.println("testtest");

		while (true) {
			if (!sendMessageToServer())
				return;
		}
	}

	private boolean sendMessageToServer() throws IOException {
		try {
			String msg = readInput();
			if ("EXIT".equals(msg))
				return false;
			out.println(msg);
			log.info(in.readLine());
		} catch (SocketException e) {
			log.log(Level.WARNING, "Disconnected from server. Shutting down the client.", e);
			return false;
		}
		return true;
	}

	private String readInput() {
		log.info("Enter input: ");
		return scanner.nextLine();
	}

	public static void main(String[] args) throws Exception {
		try (GameClient client = new GameClient()) {
			client.startConnection("192.168.125.225", 6667);
		}
	}
}

