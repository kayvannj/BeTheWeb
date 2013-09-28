import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTTPDataTransfer {

	public static void main(String[] args) {

		try {
		  String inputLine;
		  BufferedReader in = getLocalHTTPData("8080");
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BufferedReader getLocalHTTPData(String port) throws IOException{
		BufferedReader in;
		URL localURL = new URL("http://localhost:"+port);
		URLConnection yc = localURL.openConnection();
		in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		return in;
	} 

}
