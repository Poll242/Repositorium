package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Auditlog {
	private String name;
	private File log;
	private PrintWriter pw;
	
	public Auditlog() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		name = "auditlog " + timeStamp + ".txt";
		log = new File(name);
		try {
			log.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			try {
				pw = new PrintWriter(new FileWriter(name, true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.println("yyyyMMdd_HHmmss;ID;JournalID;Changes ");
			pw.flush();
			log.setWritable(false);
		
			
	}
	
	public void addEntry(String id, String journalID, String newEntry) {
		log.setWritable(true);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		pw.println(timeStamp+ ";" + id + ";" + journalID +";" + newEntry);
		pw.flush();
		log.setWritable(false);
	}
	
}