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
			pw.println("yyyyMMdd_HHmmss;ID;Change ");
			pw.flush();
		
			
	}
	
	public void addEntry(String id, String newEntry) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		pw.println(timeStamp+ ";" + id + ";" + newEntry);
		pw.flush();
	}
	
	public static void main(String[] args) {
		Auditlog au = new Auditlog();
		au.addEntry("KIRK", "Captains log, stardate söldk3485ieuhfaoi4");
		au.addEntry("ELAK SNUBBE", "FUCK YOU");
		au.addEntry("HERP", "DERP");
	}
}