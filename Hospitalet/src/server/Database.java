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

	public void deleteJournal(String journalID) {

		for (Journal j : db) {
			if (journalID.equals(j.getID())) {
				db.remove(j);
			}
		}
		
	}
	
	public Journal getJournal(String journalID, String name, String division) {
		Journal temp = null;
		for (Journal j : db) {
			if (journalID.equals(j.getID()))
				temp = j;
		}
		if (patients.contains(name)) {

			if (name.equals(temp.getPatient())) {
				return temp;
			}

		}

		else if (nurses.contains(name)) {
			if (name.equals(temp.getNurse()) || division.equals(temp.getDivision())) {
				return temp;
			}
		}

		else if (doctors.contains(name)) {
			if (name.equals(temp.getDoctor()) || division.equals(temp.getDivision())) {
				return temp;
			}
		}

		return null;
	}

	public List<Journal> getAssociatedJournals(String name, String division) {
		List<Journal> list = new ArrayList<Journal>();
		if (patients.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getPatient())) {
					list.add(j);
				}
			}
		}

		else if (nurses.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getNurse())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
		}

		else if (doctors.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getDoctor())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
		}
		return list;
	}
}
