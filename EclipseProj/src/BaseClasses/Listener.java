package BaseClasses;

import java.net.Socket;
import java.util.HashMap;

public interface Listener {
	
	final HashMap<String, Socket> ClientConnections = new HashMap<String, Socket>();
	/**
	 * Adds the incoming socket to the HashMap
	 * @param clientSocket
	 */
	abstract void addNewClient(Socket clientSocket);
	/**
	 * Parses the key for HashMap from incoming socket
	 * @param socket
	 * @return
	 */
	abstract String parseKey(Socket socket);
	/**
	 * Creates a new Proxy Thread which will handle the communication among client and incoming request
	 * @param userSocket user's socket
	 */
	abstract void createProxy(Socket userSocket);
	
	
	
}
