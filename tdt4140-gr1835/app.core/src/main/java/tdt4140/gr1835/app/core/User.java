package tdt4140.gr1835.app.core;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class User {
	
	private String username;
	private String password;
	private String firstName;
	private String secondName;
	private String faculty;
	private String phoneNumber;
	private String email;
	
	//https://www.ntnu.edu/faculties
	public static List<String> LEGAL_FACULTIES_NTNU=Arrays.asList("AD","HF","IE","IV","MH","NV","SU","OK","VM");
	//private static List<String> validCTLDs = Arrays.asList("ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw");

	
	public User() {
	}

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
			throw new IllegalArgumentException("Etternavn skal kun inneholde bokstaver");
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

	public void setPhoneNumber(String phoneNumber) {
		if (!containsOnlyNumbers(phoneNumber)) {
			throw new IllegalArgumentException("Mobilnummer skal kunn inneholde tall. Det gjør ikke din input: "+ phoneNumber);
		}
		if(phoneNumber.length()!=8) {
			throw new IllegalArgumentException("Mobilnummer skal ikke være lenger eller kortere enn 8 siffer. Det gjør ikke din input: "+ phoneNumber);
		}
		this.phoneNumber=phoneNumber;
		
	}

	private boolean containsOnlyNumbers(String string) {
		for (int i=0;i<string.length();i++) {
			if(!Character.isDigit(string.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	private void checkValidEmail(String email) {
		String[] parts = email.split("@");
		if (parts.length != 2) {
			throw new IllegalArgumentException("Ugyldig email");
		}
		//String[] domainParts = parts[1].split("\\.");
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmail(String email) {
		checkValidEmail(email);
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", secondName="
				+ secondName + ", faculty=" + faculty + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	
}
