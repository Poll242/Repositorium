package server;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private ArrayList<Journal> db;

	public Database() {
		db = new ArrayList<Journal>();
	}

	public void addJournal(Journal journal) {
		db.add(journal);
	}

	public Journal getJournal(String journalID) {

		return null;
	}

	public List<Journal> getAssociatedJounrals(String name) {
		return null;
	}
}
