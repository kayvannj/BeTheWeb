package BaseClasses;

import java.net.Socket;
import java.util.HashMap;

/**
 * @author Kayvan
 * Listeners will listen on two ports (one for other client applications and the other for incoming user connections)
 * and when they have an incoming connection for an already existing client, they will create a ProxyThread and pass
 * the sockets to it so it can manage the communication among those two.
 */
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
