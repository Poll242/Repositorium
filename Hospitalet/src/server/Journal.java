package server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Journal implements Serializable {
	private String id;
	private String doctor;
	private String nurse;
	private String division;
	private String patient;
	private String symptoms;
	private String treatment;
	private String comments;
	
	public Journal(String id, String doctor, String nurse, String division, String patient) {
		this.id = id;
		this.doctor = doctor;
		this.nurse = nurse;
		this.division = division;
		this.patient = patient;
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
	public void changeSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	
	public String getSymptoms() {
		return symptoms;
	}

	public void changeTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getTreatment() {
		return treatment;
	}

	public void changeComment(String comments) {
		this.comments = comments;
	}

	public String getComment() {
		return comments;
	}
}
