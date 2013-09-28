package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.soap.Text;

public class HTTPDataTransfer {

	public String transferData() {
		String input = new String("");
		String inputLine;
		try {
		  BufferedReader in = getLocalHTTPData("8080");
		while ((inputLine = in.readLine()) != null)
			input = input+inputLine;
		in.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	private static BufferedReader getLocalHTTPData(String port) throws IOException{
		BufferedReader in;
		URL localURL = new URL("http://localhost:"+port);
		URLConnection yc = localURL.openConnection();
		in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		return in;
	} 

}
