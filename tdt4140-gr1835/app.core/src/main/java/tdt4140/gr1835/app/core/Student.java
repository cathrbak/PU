package tdt4140.gr1835.app.core;


public class Student extends User{
	
	private boolean isAnonymous;
	private String sex;
	private Nurse nurse;
	private int studentID;
	
	public Student() {
		
	}
	
	
	public Student(String username) {
		super(username);
	}
	
	
	
	public int getStudentID() {
		return studentID;
	}


	public void setStudentID(int studentID) {
		if(studentID<0) {
			throw new IllegalArgumentException("Kan ikke ta inn negative tall som studentID. setStudentID tok inn: [ "+studentID+" ]");
		}
		this.studentID = studentID;
	}


	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		if(!this.getFaculty().equals(nurse.getFaculty())) {
			throw new IllegalStateException("Feilmelding da Nurse og Student må være fra samme fakultet");
		}
		this.nurse = nurse;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		StringBuilder s=new StringBuilder();
		s.append(Character.toLowerCase(sex.charAt(0)));
		for (int i=1;i<sex.length();i++) {
			s.append(Character.toLowerCase(sex.charAt(i)));
		}
		sex=s.toString();
		if(sex.equals("mann")) {
			this.sex=sex;
		}else if(sex.equals("kvinne")) {
			this.sex=sex;
		}else {
			throw new IllegalArgumentException("Kjønn kan bare med strengverdi mann eller kvinne");
		}
		
		
	}

}
