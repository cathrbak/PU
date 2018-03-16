package tdt4140.gr1835.app.core;

import java.sql.Time;


public class Message {
	
	Student student;
	Nurse nurse;
	Time time;
	String text;
	

	public Message(Student student, Nurse nurse) {
		this.student = student;
		this.nurse = nurse;
	}
	
	public Nurse getSender() {
		return nurse;
	}
	
	public void setNurse(Nurse nurse) {
		if (nurse == null) {
			throw new IllegalArgumentException("Du må eksistere for å sende en melding.");
		}
		this.nurse = nurse;
	}
	
	public Student getReciver() {
		return student;
	}
	
	public void setReciver(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Du må definere en mottaker av meldingen.");
		}
		this.student = student;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		if (text == "") {
			throw new IllegalArgumentException("Meldingen kan ikke være tom.");
		}
		this.text = text;
	}
	
}
