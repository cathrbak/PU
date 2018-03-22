package tdt4140.gr1835.app.core;

import java.sql.Timestamp;

public class Message {
	
	Student student;
	Nurse nurse;
	Timestamp time;
	String text;
	Integer messageID;
	

	public Message(Student student, Nurse nurse) {
		this.student = student;
		this.nurse = nurse;
	}
	
	public void setMessageID(Integer id) {
		this.messageID=id;
	}
	
	public Integer getMessageID() {
		return messageID;
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
		} else if (text.length() > 250) {
			throw new IllegalArgumentException("Meldingen kan ikke være lengre enn 255 tegn.");
		}
		this.text = text;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
