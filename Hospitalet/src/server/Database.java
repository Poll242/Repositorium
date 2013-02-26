package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Database implements Serializable {
	private ArrayList<Journal> db;
	private ArrayList<String> doctors;
	private ArrayList<String> nurses;
	private ArrayList<String> patients;
	private Auditlog log;

	public Database() {
		db = new ArrayList<Journal>();
		doctors = new ArrayList<String>();
		nurses = new ArrayList<String>();
		patients = new ArrayList<String>();
		log = new Auditlog();

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

	public String addJournal(Journal journal, String name) {
		if (doctors.contains(name)) {
			db.add(journal);
			log.addEntry(journal.getDoctor(), journal.getID(),
					"Added new journal; Doctor = " + journal.getDoctor()
							+ "; Nurse = " + journal.getNurse()
							+ "; Patient = " + journal.getPatient());
			return "Journal with ID " + journal.getID() + " added.";
		}
		log.addEntry(name, journal.getID(),
				"User tried to add jounral without permission.");
		return "Journal not added. Permission denied!";
	}

	public String deleteJournal(String journalID, String name) {
		if (name.equals("StyrelsenMADDERFAKKER")) {
			for (Journal j : db) {
				if (journalID.equals(j.getID())) {
					db.remove(j);

				}
			}
			log.addEntry(name, journalID, "Journal deleted.");
			return "Journal with ID " + journalID + " removed.";
		}
		log.addEntry(name, journalID,
				"User tried to delete journal without permission");
		return "Journal not deleted. Permission Denied!";
	}

	public Journal getJournal(String journalID, String name, String division) {
		Journal temp = null;
		for (Journal j : db) {
			if (journalID.equals(j.getID()))
				temp = j;
		}
		if (patients.contains(name)) {

			if (name.equals(temp.getPatient())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return temp;
			}

		}

		else if (nurses.contains(name)) {
			if (name.equals(temp.getNurse())
					|| division.equals(temp.getDivision())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return temp;
			}
		}

		else if (doctors.contains(name)) {
			if (name.equals(temp.getDoctor())
					|| division.equals(temp.getDivision())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return temp;
			}
		}
		else if (name.equals("StyrelsenMADDERFAKKER")) {
			log.addEntry(name, temp.getID(), "User read journal.");
			return temp;
		}
		log.addEntry(name, journalID,
				"User tried to access journal without permission.");
		Journal fakeJournal = new Journal("", "", "", "", "");
		fakeJournal.changeText("Permission Denied");
		return fakeJournal;
	}

	public List<Journal> getAssociatedJournals(String name, String division) {
		List<Journal> list = new ArrayList<Journal>();
		if (patients.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getPatient())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals", "User retrieved associated journals");
			return list;
		}

		else if (nurses.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getNurse())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals", "User retrieved associated journals");
			return list;
		}

		else if (doctors.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getDoctor())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals", "User retrieved associated journals");
			return list;
		}
		
		else if (name.equals("StyrelsenMADDERFAKKER")) {
			for (Journal j : db) {
					list.add(j);
			}
			log.addEntry(name, "List of journals", "User retrieved associated journals");
			return list;
		}
		log.addEntry(name, "List of journals", "User tried to retieve list of journals. Access denied!");
		return null;
	}
	
	public String appendToText(String name, String journalID, String text) {
		Journal temp = null;
		for (Journal j : db) {
			if (journalID.equals(j.getID()))
				temp = j;
		}
		if (nurses.contains(name)) {
			if (name.equals(temp.getNurse())) {
				temp.changeText(text);
				log.addEntry(name, temp.getID(), "User added text: " + text);
				return "Text added to journal";
			}
		}
		else if (nurses.contains(name)) {
			if (name.equals(temp.getDoctor())) {
				temp.changeText(text);
				log.addEntry(name, temp.getID(), "User added text: " + text);
				return "Text added to journal";
			}
		}
		log.addEntry(name, journalID, "User tried to add text \"" + text + "\" to journal without permission.");
		return "Did not add text to journal. Permission denied!";
	}
}
