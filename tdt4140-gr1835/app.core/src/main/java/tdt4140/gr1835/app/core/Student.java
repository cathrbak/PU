package tdt4140.gr1835.app.core;

public class Student extends User{
	
	private boolean isAnonymous;
	private String sex;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
