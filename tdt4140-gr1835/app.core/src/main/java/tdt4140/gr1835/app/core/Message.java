package tdt4140.gr1835.app.core;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

import tdt3140.gr1835.app.json.Exclude;



@XmlRootElement
public class Message {
	
	private Student student;
	private Nurse nurse;
	
	@Exclude
	private transient Timestamp time;
	private long millitime;
	private String text;
	private Integer messageID;
	
	public Message(Student student, Nurse nurse) {
		this.student = student;
		this.nurse = nurse;
	}
	
	public Message() {
		
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
		this.millitime=time.getTime();
	}
	
	public long getMillitime() {
		return this.millitime;
	}
	
	
	
	
}
