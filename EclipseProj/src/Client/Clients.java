package Client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A simple Swing-based client for the capitalization server. It has a main
 * frame window with a text field for entering strings and a textarea to see the
 * results of capitalizing them.
 */
public class Clients {

	private BufferedReader in;
	private PrintWriter out;
	private String serverAddress;
	private HashMap<String, Boolean> connectedTo = new HashMap<String, Boolean>();
	private HTTPDataTransfer HDT;
	/**
	 * This is the constructor of the client class
	 */
	public Clients() {
				
	}
	
	public String getServerAddress(){
		return this.serverAddress;
	}
	
	public void setServerAddress(String a){
		this.serverAddress = a;
	}


	/**
	 * connecting to the server to send a request for a domain
	 * @param address
	 * @throws IOException
	 */
	public void connectToServer(String address) throws IOException {
		
		// Make connection and initialize streams
		Socket socket = new Socket(address, 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	/**
	 * This responds to the server request 
	 * @param address
	 */
	public void respond(){
		String response = this.HDT.transferData();
		out.println(response);
	}
	
	/**
	 * This class is a runnable object that keeps the connection between the server and client alive
	 * @author Shahin
	 * To do this we override the run method
	 */
	public class ConnectionKeeperThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("keep");
			}
		}
	}
	
	/**
	 * This function keeps the connection between the client and server alive
	 */
	public void keepConnection(){
		ConnectionKeeperThread thread = new ConnectionKeeperThread();
		thread.run();
	}
	
	/**
	 * this updates the server address to addr and create a new socket to this server
	 * @param addr
	 * @throws IOException
	 */
	public void updateAddress(String addr) throws IOException{
		this.serverAddress=addr;
		Socket socket = new Socket(addr, 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}

	/**
	 * Runs the client application.
	 */

}
