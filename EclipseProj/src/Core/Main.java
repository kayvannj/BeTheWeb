package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import BaseClasses.BaseClass;
import Util.Commands;

public class Main extends BaseClass {

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
				HttpListener myListener = new HttpListener();
				myListener.run();
				break;
			case CONNECT:
				if (argument.isEmpty()) {
					print("");
				}
				break;
			case STOP:
				
				break;
			case SEND:
				
				break;
			case QUIT:
				break;
			default:
				print("Bad Command");
				break;
			}
			
		}
	}
}
