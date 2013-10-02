package Util;
import java.io.*;
import java.sql.Time;

public class Logger {
	private static File f;
	private static FileWriter writer;

	private static void openFile(){
		f = new  File("events.log");
		try {
			f.createNewFile();
			writer = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void log(String s){
		if (f==null || !f.canWrite()) {
			openFile();
		}
		StringBuilder sb = new StringBuilder((new Time(System.currentTimeMillis()).toString()));
		sb.append("\t");
		sb.append(s);
		sb.append("\n");
		
		try {
			writer.append(sb.toString());
		} catch (IOException e) {
			System.out.println("System failed in writing log");
		}
	}
	
	
}
