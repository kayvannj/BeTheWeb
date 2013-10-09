package Core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import BaseClasses.BaseClass;
import Util.Commands;
import Util.Logger;


public class Proxy extends BaseClass implements Runnable{

	

	protected BufferedReader cDatain;
	protected PrintWriter cDataout;
	protected BufferedReader uDatain;
	protected PrintWriter uDataout;

	public Proxy(Socket clientSocket, Socket userSocket) {
		try {
			cDatain = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			uDatain = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
			cDataout = new PrintWriter(clientSocket.getOutputStream(),true);
			uDataout = new PrintWriter(userSocket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			print("Couldnt attach readers and writers");
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void run() {
		while(true){
			try{
			
				String userSocketInput = uDatain.readLine();
				while (!userSocketInput.isEmpty()) {
					cDataout.print(userSocketInput);
					userSocketInput = uDatain.readLine();
				}
				cDataout.flush();
				String ClientSocketInput = cDatain.readLine();
				while (!ClientSocketInput.isEmpty()) {
					uDataout.print(ClientSocketInput);
					ClientSocketInput = cDatain.readLine();
				}
				uDataout.flush();
				
			}catch(Exception e){
				
			}
		}
	}
}
