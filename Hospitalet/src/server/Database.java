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

	public String addJournal(String id, String doctor, String nurse,
			String division, String patient) {
		if (doctors.contains(doctor)) {
			Journal journal = new Journal(id, doctor, nurse, division, patient);
			db.add(journal);
			log.addEntry(doctor, id, "Added new journal; Doctor = " + doctor
					+ "; Nurse = " + nurse + "; Patient = " + patient
					+ "; Division = " + division);
			return "Journal with ID " + id + " added.";
		}
		log.addEntry(doctor, id,
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

	public String getJournal(String journalID, String name, String division) {
		Journal temp = null;
		for (Journal j : db) {
			if (journalID.equals(j.getID()))
				temp = j;
		}
		if (patients.contains(name)) {

			if (name.equals(temp.getPatient())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return "Journal ID: " + temp.getID() + "\nDoctor: "
						+ temp.getDoctor() + "\nNurse: " + temp.getNurse()
						+ "\nDivision: " + temp.getDivision() + "\nPatient: "
						+ temp.getPatient() + "\nText: " + temp.getText();
			}

		}

		else if (nurses.contains(name)) {
			if (name.equals(temp.getNurse())
					|| division.equals(temp.getDivision())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return "Journal ID: " + temp.getID() + "\nDoctor: "
						+ temp.getDoctor() + "\nNurse: " + temp.getNurse()
						+ "\nDivision: " + temp.getDivision() + "\nPatient: "
						+ temp.getPatient() + "\nText: " + temp.getText();
			}
		}

		else if (doctors.contains(name)) {
			if (name.equals(temp.getDoctor())
					|| division.equals(temp.getDivision())) {
				log.addEntry(name, temp.getID(), "User read journal.");
				return "Journal ID: " + temp.getID() + "\nDoctor: "
						+ temp.getDoctor() + "\nNurse: " + temp.getNurse()
						+ "\nDivision: " + temp.getDivision() + "\nPatient: "
						+ temp.getPatient() + "\nText: " + temp.getText();
			}
		} else if (name.equals("StyrelsenMADDERFAKKER")) {
			log.addEntry(name, temp.getID(), "User read journal.");
			return "Journal ID: " + temp.getID() + "\nDoctor: "
					+ temp.getDoctor() + "\nNurse: " + temp.getNurse()
					+ "\nDivision: " + temp.getDivision() + "\nPatient: "
					+ temp.getPatient() + "\nText: " + temp.getText();
		}
		log.addEntry(name, journalID,
				"User tried to access journal without permission.");

		return "Could not retieve journal. Access denied";
	}

	public String getAssociatedJournals(String name, String division) {
		List<Journal> list = new ArrayList<Journal>();
		if (patients.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getPatient())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals",
					"User retrieved associated journals");
			StringBuilder sb = new StringBuilder("Journal ID; Patient \n");
			for (Journal j : list) {
				sb.append(j.getID() + "; " + j.getPatient() + "\n");
			}
			return sb.toString();
		}

		else if (nurses.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getNurse())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals",
					"User retrieved associated journals");
			StringBuilder sb = new StringBuilder("Journal ID; Patient \n");
			for (Journal j : list) {
				sb.append(j.getID() + "; " + j.getPatient() + "\n");
			}
			return sb.toString();
		}

		else if (doctors.contains(name)) {
			for (Journal j : db) {
				if (name.equals(j.getDoctor())
						|| division.equals(j.getDivision())) {
					list.add(j);
				}
			}
			log.addEntry(name, "List of journals",
					"User retrieved associated journals");
			StringBuilder sb = new StringBuilder("Journal ID; Patient \n");
			for (Journal j : list) {
				sb.append(j.getID() + "; " + j.getPatient() + "\n");
			}
			return sb.toString();
		}

		else if (name.equals("StyrelsenMADDERFAKKER")) {
			for (Journal j : db) {
				list.add(j);
			}
			log.addEntry(name, "List of journals",
					"User retrieved associated journals");
			StringBuilder sb = new StringBuilder("Journal ID; Patient \n");
			for (Journal j : list) {
				sb.append(j.getID() + "; " + j.getPatient() + "\n");
			}
			return sb.toString();
		}
		log.addEntry(name, "List of journals",
				"User tried to retieve list of journals. Access denied!");
		return "No journals accessed. Access denied!";
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
		} else if (doctors.contains(name)) {
			if (name.equals(temp.getDoctor())) {
				temp.changeText(text);
				log.addEntry(name, temp.getID(), "User added text: " + text);
				return "Text added to journal";
			}
		}
		log.addEntry(name, journalID, "User tried to add text \"" + text
				+ "\" to journal without permission.");
		return "Did not add text to journal. Permission denied!";
	}

	public static void main(String[] args) {
		Database db = new Database();
		System.out.println(db.addJournal("Stefans", "Jonny Bombay",
				"Nurse Joy", "Ris", "Red Shirt"));
		System.out.println("----");
		System.out.println(db.getAssociatedJournals("Jonny Bombay", "Ris"));
		System.out.println("----");
		System.out.println(db.getJournal("Stefans", "Jonny Bombay", "Ris"));
		System.out.println("----");
		System.out.println(db.appendToText("Jonny Bombay", "Stefans",
				"Snuva som fan. "));
		System.out.println("----");
		System.out.println(db.getJournal("Stefans", "Jonny Bombay", "Ris"));
		System.out.println("----");
		System.out
				.println(db.deleteJournal("Stefans", "StyrelsenMADDERFAKKER"));
		System.out.println("----");
	}
}
