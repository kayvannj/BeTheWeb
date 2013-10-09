package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.regex.Pattern;

import BaseClasses.BaseClass;
import Util.Commands;

public class Main extends BaseClass {
	private static String HELLO = "<!DOCTYPE html><html><body><h1>Hello</h1><p>this is comming from server.</p></body></html>";
	private static Socket clientSocket;
	private static PrintWriter writer;
	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private static Pattern IpPattern = Pattern.compile(IPADDRESS_PATTERN);
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		print("(use '"+Commands.QUIT+"' to exit)");
		String userInput = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
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
				/**
				 * Used to start the server and listening for both client and user incoming connections
				 */
				HttpListener myListener = new HttpListener();
				myListener.run();
				break;
			case CONNECT:
				/**
				 * Used from a client to get connected to the server of BTW
				 * It takes the IP of server as an argument and connects to ClientPort of that address
				 */
				if (argument.isEmpty()) {
					print("No address provided!");
					break;
				}
				if (Pattern.matches(IPADDRESS_PATTERN, argument)) {
					String[] addressByteStrings = argument.split(".");
					byte[] addressByte = new byte[4];
					
					for (int i = 0; i < addressByteStrings.length; i++) {
						addressByte[i] = Byte.parseByte(addressByteStrings[i]); 
					}
					
				}else{
					//clientSocket = new Socket(argument, 8090);
				}
				
				URL url = new URL("http://"+argument+":8090");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoOutput(true);
				connection.connect();
				writer = new PrintWriter(connection.getOutputStream());
				
				break;
			case STOP:
				/**
				 * Stops the server from listening
				 */
				break;
			case SEND:
				/**
				 * sends a sample HTTP Respond 
				 */
				print("Sending the sample HTTPRespond ...");
				if (clientSocket!=null) {
					writer.print(HELLO);
					writer.flush();
					print("Hello Sent.");
					break;
				}
				print("Please connect first");
				break;
			case QUIT:
				print("Exiting ...");
				System.exit(0);
				break;
			default:
				print("Bad Command");
				break;
			}
			
		}
	}
}
