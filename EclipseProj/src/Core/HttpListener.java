package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import BaseClasses.BaseClass;

public class HttpListener extends BaseClass implements BaseClasses.Listener, Runnable {

	private HashMap<String, Socket> clientConnections = new HashMap<String,Socket>();
	private ServerSocket listenForClients;
	private ServerSocket listenForUsers;
	private int clientPort = 8090;
	private int userPort = 80;
	Thread listenForClientsThread;
	Thread listenForUsersThread;
	
	public HttpListener() {

		listenForClientsThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				try {
					listenForClients = new ServerSocket(clientPort);
					print("Listening on "+clientPort);
					while(true){
						Socket client = listenForClients.accept();
						addNewClient(client);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		
		listenForUsersThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					listenForUsers = new ServerSocket(userPort);
					print("Listening on "+userPort);
					while(true){
						Socket user = listenForUsers.accept();
						createProxy(user);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
	}

	@Override
	public void addNewClient(Socket socket) {
		print("new Client to Add");
		String key = parseKey(socket);
		if (key.equals("-1")) {
			print("Couldnt find Host in the packet request.");
		}
		clientConnections.put(key, socket);
		for (String s: clientConnections.keySet()) {
			print(s+"\t");
		}
	}

	@Override
	public String parseKey(Socket clientSocket) {
		
		try {
			BufferedReader socketDataReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String packetData = socketDataReader.readLine();
			while(!packetData.isEmpty()){
				print(packetData);
				if (packetData.indexOf("Host")!=-1) {
					return packetData.split(" ")[1];
				}
				packetData = socketDataReader.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";
	}

	@Override
	public void createProxy(Socket userSocket) {
		// TODO parse the user's socket, check if client exists
		print("new user to connect");
	}

	@Override
	public void run() {
		listenForClientsThread.start();
		listenForUsersThread.start();
	}

}
