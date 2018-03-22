package tdt4140.gr1835.app.core;

public class Nurse extends User{
	
	private int nurseID;

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
	
	
	
}
