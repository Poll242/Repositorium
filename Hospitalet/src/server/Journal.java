package server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Journal implements Serializable {
	private String id;
	private String doctor;
	private String nurse;
	private String division;
	private String patient;
	private String text;

	public Journal(String id, String doctor, String nurse, String division,
			String patient) {
		this.id = id;
		this.doctor = doctor;
		this.nurse = nurse;
		this.division = division;
		this.patient = patient;
		this.text = "";
	}

	public String getID() {
		return id;
	}

	public String getDoctor() {
		return doctor;
	}

	public String getNurse() {
		return nurse;
	}

	public String getDivision() {
		return division;
	}

	public String getPatient() {
		return patient;
	}

	public void changeText(String text) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.text);
		sb.append("\n" + text);
		this.text = sb.toString();

	}

	public String getText() {
		return text;
	}

}
