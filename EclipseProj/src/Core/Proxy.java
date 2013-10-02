package Core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.xml.stream.events.StartDocument;

import BaseClasses.BaseClass;
import Util.Commands;
import Util.Logger;


public class Proxy extends BaseClass{

	private static String HELLO = "<!DOCTYPE html><html><body><h1>Hello</h1><p>this is comming from server.</p></body></html>";
			 
	private static int DEFAULT_PORT = 80;
	ServerSocket listener;
	int portToListen;
	Thread listenerThread;
	private static Proxy myProxy;
	protected BufferedReader datain;
	protected PrintWriter dataout;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		print("Welcome ... (use '"+Commands.QUIT+"' to exit)");
		String userInput = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		myProxy = new Proxy(DEFAULT_PORT);
		while (!userInput.equals(Commands.QUIT)) {
			userInput = br.readLine();
			String command = "";
			String argument = "";
			String[] inputArray = userInput.split(" ");
			if (inputArray.length==1) {
				command = inputArray[0].toUpperCase();
			}else if (inputArray.length==2) {
				command = inputArray[0].toUpperCase();
				argument = inputArray[1];
			}else{
				print("Sorry, I couldn't undrestand your command");
			}

			switch (Commands.valueOf(command)) {
			case START:
				myProxy.start();
				break;
			case STOP:
				myProxy.stop();
				break;
			case SEND:
				myProxy.send();
				break;
			case QUIT:
				break;
			default:
				print("Bad Command");
				break;
			}
			
		}
	}
	
	private void send() {
		dataout.print(HELLO);
		dataout.flush();
		print("data Sent");
	}

	/**
	 * Initializes new proxy server 
	 * @param portToListen
	 */
	public Proxy(final int portToListen) {
		listenerThread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					listener = new ServerSocket(portToListen);
					Logger.log("ServerSocket listening on port: "+portToListen);
					print("Starting to listen on "+portToListen);
					Socket socket = listener.accept();
					print("Proxy: got connected to "+socket.getInetAddress());
					print("-----------Data From Connection-------------");
					datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					dataout = new PrintWriter(socket.getOutputStream(),true);
					String socketInput = datain.readLine();
					while (!socketInput.isEmpty()) {
						
						print(socketInput);
						socketInput = datain.readLine();
					}
					
				} catch (IOException e) {
					Logger.log("Failed to open ServerSocket on port: "+portToListen);
				}

			}
		});
		
	}
	
	/**
	 * Starts the proxy server
	 * @return status (error or done)
	 */
	private String start(){
		listenerThread.start();
		return "";
	}
	
	
	
	private String stop(){
		
		print("Stopped listening");
		return "";
	}

}
