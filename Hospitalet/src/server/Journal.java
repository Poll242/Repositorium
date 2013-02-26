package server;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Journal implements Serializable{
	private String doctor;
	private ArrayList<String> nurses;
	private String patient;
	private String symptoms;
	private String treatment;
	private String comments;
	
	public Journal() {
		
	
	}
	
	public void addDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	public String getDoctor() {
		return doctor;
	}

	public void addNurse(String nurse) {
		nurses.add(nurse);
	}
	
	public String getNurses() {
		StringBuilder sb = new StringBuilder();
		for (String s : nurses) sb.append(s+" "); 
		return sb.toString();
	}
	public void addPatient(String patient) {
		this.patient = patient;
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
