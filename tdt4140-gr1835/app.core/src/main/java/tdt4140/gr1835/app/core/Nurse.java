package tdt4140.gr1835.app.core;

import java.util.List;

public class Nurse extends User{
	
	private int nurseID;
	private List<Student> students;

	public Nurse(String username) {
		super(username);
	}

	public int getNurseID() {
		return nurseID;
	}

	public void setNurseID(int studentID) {
		if(studentID<0) {
			throw new IllegalArgumentException("Kan ikke ta inn negative tall som studentID. setStudentID tok inn: [ "+studentID+" ]");
		}
		this.nurseID = studentID;
	}

	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
