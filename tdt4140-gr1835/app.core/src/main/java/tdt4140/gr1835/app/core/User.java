package tdt4140.gr1835.app.core;

import java.util.Arrays;
import java.util.List;

public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String secondName;
	private String faculty;
	
	//https://www.ntnu.edu/faculties
	public static List<String> LEGAL_FACULTIES_NTNU=Arrays.asList("AD","HF","IE","IV","MH","NV","SU","OK","VM") ;

	public User(String username) {
		this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String newPassword) {
		if(newPassword.equals("")) {
			throw new IllegalArgumentException("Passordet kan ikke være tomt");
		}
		this.password=newPassword;
		
	}

	public String getPassword() {
		return password;
	}

	public void setFirstName(String firstName) {
		if(containsNonletter(firstName)) {
			throw new IllegalArgumentException("Fornavn skal kun inneholde bokstaver");
		}
		this.firstName=makeNiceName(firstName);	
	}

	private String makeNiceName(String string) {
		StringBuilder s=new StringBuilder();
		s.append(Character.toUpperCase(string.charAt(0)));
		for (int i=1;i<string.length();i++) {
			s.append(Character.toLowerCase(string.charAt(i)));
		}
		return s.toString();
	}

	private boolean containsNonletter(String string) {
		for (int i=0;i<string.length();i++) {
			if(!Character.isLetter(string.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		if(containsNonletter(secondName)) {
			throw new IllegalArgumentException("Fornavn skal kun inneholde bokstaver");
		}
		this.secondName=makeNiceName(secondName);	
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		faculty=faculty.toUpperCase();
		if(!LEGAL_FACULTIES_NTNU.contains(faculty)) {
			throw new IllegalArgumentException("Dette fakultetet eksisterer ikke");
		}
		this.faculty = faculty;
		
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
