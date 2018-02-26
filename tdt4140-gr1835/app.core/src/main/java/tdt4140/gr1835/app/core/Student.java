package tdt4140.gr1835.app.core;

public class Student extends User{
	
	private boolean isAnonymous;
	private Nurse nurse;
	
	public Student(String username) {
		super(username);
	}
	
	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

}
