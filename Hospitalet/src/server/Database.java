package server;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private ArrayList<Journal> db;
	private ArrayList<String> doctors;
	private ArrayList<String> nurses;
	private ArrayList<String> patients;
	
	public Database() {
		db = new ArrayList<Journal>();
		doctors = new ArrayList<String>();
		nurses = new ArrayList<String>();
		patients = new ArrayList<String>();
		
		doctors.add("Alban Nwapa");
		doctors.add("Jonny Bombay");
		doctors.add("Gregory House");
		doctors.add("René Jones");
		
		nurses.add("Nurse Joy");
		nurses.add("Carla Espinosa");
		nurses.add("Hot Lips Houlihan");
		nurses.add("Christine Chapel");
		
		patients.add("Red Shirt");
		patients.add("Private Wilhelm");
		patients.add("Johan Gran du Nilsson");
		patients.add("Black Knight");
	}

	public void addJournal(Journal journal) {
		db.add(journal);
	}

	public Journal getJournal(String journalID) {
		Journal temp = null;
		for (Journal j : db) {
			if (journalID.equals(j.getID()))
				temp = j;
		}
		return temp;
	}

	public List<Journal> getAssociatedJournals(String name, String division) {
		List<Journal> list = new ArrayList<Journal>();
		
		return null;
	}
}
