package tdt4140.gr1835.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tdt4140.gr1835.app.core.Message;
import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

abstract class AbstractSQLHandler{

	Connection con = null;
	
	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35&useSSL=false";
 
	//Metoder for � opprette en kobling mellom applikasjonen og databasen
	private Connection getConnection() throws SQLException{
        if (con == null) {
            con = DriverManager.getConnection(dbURL);
        }
        return con;
    }
	
	public void closeConnection() throws SQLException {
		getConnection().close();
	}
	
	public Statement getStatement() throws SQLException{
		Connection conn = getConnection();
		return conn.createStatement();
	}
	
	//for � s�rge for at det blir riktig format p� attributtet facultet/faculty. Dette er nemlig fakultetID i databasen
	//, alts� en int, mens det er fakultetnavnet i java-koden, alts� string.
	public int switchFacultyNametoID(String faculty) {
		
		Integer fakultetID = 0;
		
		if (faculty.equalsIgnoreCase("AD")) {
			fakultetID = 1;
		}
		else if (faculty.equalsIgnoreCase("HF")) {
			fakultetID = 2;
		}
		else if (faculty.equalsIgnoreCase("IE")) {
			fakultetID = 3;
		}
		else if (faculty.equalsIgnoreCase("IV")) {
			fakultetID = 4;
		}	
		else if (faculty.equalsIgnoreCase("MH")) {
			fakultetID = 5;
		}
		else if (faculty.equalsIgnoreCase("NV")) {
			fakultetID = 6;
		}
		else if (faculty.equalsIgnoreCase("SU")) {
			fakultetID = 7;
		}
		else if (faculty.equalsIgnoreCase("OK")) {
			fakultetID = 8;
		}
		else if (faculty.equalsIgnoreCase("VM")) {
			fakultetID = 9;
		}else {
			fakultetID = null;
		}
				
		return fakultetID;
		
	}

	public String switchFakultetIDtoName(Integer facID) {
		String facultyName = null;
		
		if(facID == 1) {
			facultyName = "AD";
		} else if(facID == 2) {
			facultyName = "HF";
		}else if(facID == 3) {
			facultyName = "IE";
		}else if(facID == 4) {
			facultyName = "IV";
		}else if(facID == 5) {
			facultyName = "MH";
		}else if(facID == 6) {
			facultyName = "NV";
		}else if(facID == 7) {
			facultyName = "SU";
		}else if(facID == 8) {
			facultyName = "OK"; 
		}else if(facID == 9) {
			facultyName = "VM";
		}
		
		return facultyName;
	}
	
	
	
	
	//metode for � hente ut et nurse-objekt n�r kan kun f�r HelsesosterID fra raden i databasen
	protected Nurse getNurseFromID(int nurseID) throws SQLException{
		ResultSet rs = null;
		Nurse nurse = new Nurse(null);
		try {
			String query = "SELECT * FROM helsesoster WHERE HelsesosterID=" + nurseID +";";
			Statement stmt = getStatement();
			
			if(stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
		while(rs.next()) {
			String username = rs.getString("brukernavn");
			nurse = getNurse(username);
		} 
		
		
		return nurse; 
	}

	// getNurseFromID bruker getNurse-metoden. Skulle helst sett at getNurse lå i NurseHandler-klassen, men da Nurse og Student er såpass avhengig av hverandre blir dette det beste vi klarer.
	public Nurse getNurse(String username) throws SQLException {
		
		ResultSet rs = null;
		Nurse nurse = new Nurse(username);
		try {
			String query = "SELECT * FROM helsesoster WHERE brukernavn='" + username +"';";
			Statement stmt = getStatement();
			
			if(stmt.execute(query)) {
				rs = stmt.getResultSet();
				
				if (!rs.isBeforeFirst() ) {    
				    System.out.println("No data"); 
				    	return null;
				} 
			}
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
		while(rs.next()) {
			Integer nurseID = rs.getInt("HelsesosterID");
			if(!(nurseID==0)) {
				nurse.setNurseID(nurseID);;
			}
			
			String password = rs.getString("passord");
			if(!(password.equals("null"))) {
				nurse.setPassword(password);
			}
							
			Integer faculty = rs.getInt("fakultet");
			if(!(faculty==0)) {
				nurse.setFaculty(switchFakultetIDtoName(faculty));
			}
				
			String firstName = rs.getString("fornavn");
			if(!(firstName.equals("null"))) {
				nurse.setFirstName(firstName);
			}
			
			String secondName = rs.getString("etternavn");
			if(!(secondName.equals("null"))) {
				nurse.setSecondName(secondName);
			}
				
			String email = rs.getString("email");
			if(!(email.equals("null"))) {
				nurse.setEmail(email);
			}
			
			String phoneNumber = rs.getString("telefonNr");
			if(phoneNumber != null) {
				nurse.setPhoneNumber(phoneNumber);
			}
		}
		
		return nurse; 
	}
	
	protected Table listToTableConverter(Student student, String anslist,Timestamp tstamp) throws SQLException {
		int sum=0;
		List<String> stringList= Arrays.asList(anslist.split(",")); //Deler opp strengen på komma, og lager en liste av den
		List<Integer> intlist=new ArrayList<>();
		for(String c:stringList) {
			sum+=Integer.parseInt(c); //Summerer opp for totalen
			intlist.add(Integer.parseInt(c)); //Legger til svar i svarliste kalt intlist
		}
		Table connectionTable = new Table(getStudentID(student),intlist.get(0), intlist.get(1),intlist.get(2),intlist.get(3),intlist.get(4),intlist.get(5),intlist.get(6), intlist.get(7), intlist.get(8), intlist.get(9), sum);
		connectionTable.setDato(tstamp);
		//Returnerer Tableobjekt med student og svar
		return connectionTable;
	}
	


	//metoder for � hente iden til en student og helsesoster n�r man bare har objektet i java. Burde kanskje legge til 
		//DatagiverID og HelsesosterID som attributt i Nurse og Student.
		public int getStudentID(Student student) throws SQLException {
			Integer studentID = null;
			
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM `datagiver` WHERE `brukernavn` LIKE '" + student.getUsername() +"'";
				//fra myphpadmin: "SELECT * FROM `datagiver` WHERE `brukernavn` LIKE 'sverress'";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			while(rs.next()) {
				studentID = rs.getInt("DatagiverID");
			} 
			
			
			return studentID;
		}
		
		public int getNurseID(Nurse nurse) throws SQLException {
			Integer nurseID = null;
			
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM `helsesoster` WHERE `brukernavn` LIKE '" + nurse.getUsername() +"'";
				//fra myphpadmin: "SELECT * FROM `datagiver` WHERE `brukernavn` LIKE 'sverress'";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			while(rs.next()) {
				nurseID = rs.getInt("HelsesosterID");
			} 
			
			return nurseID;
		}
		
		public String getLoggID(Student student) throws SQLException {
			String loggID = null;
			
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM `svarlogg` WHERE `DatagiverID` LIKE '" + getStudentID(student)+"'";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			while(rs.next()) {
				loggID = rs.getString("LoggID");
			} 
			
			
			return loggID;
		}

		public Message getMessageFromID(Integer messageid) throws SQLException{
			ResultSet rs = null;
			Message message = new Message(null, null);
			try {
				String query = "SELECT * FROM meldinger WHERE MeldingID=" + messageid + ";";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
					
					if (!rs.isBeforeFirst() ) {    
					    System.out.println("Meldingen ligger ikke i databasen."); 
					    throw new IllegalStateException("Denne meldingen eksisterer ikke i databasen"); 
					} 
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				
				Integer messageID = rs.getInt("MeldingID");
				if(!(messageID==0)) {
					message.setMessageID(messageID);
				}
				Integer studentID = rs.getInt("DatagiverID");
				if(!(studentID==0)) {
					message.setReciver(getStudentFromID(studentID));
				}
				Integer nurseID = rs.getInt("HelsesosterID");
				if(!(nurseID==0)) {
					message.setNurse(getNurseFromID(nurseID));
				}
				Timestamp tstamp = rs.getTimestamp("datoTid");
				message.setTime(tstamp);
				//System.out.println(message.getTime());
				
				String text = rs.getString("tekst");
				if(!(text.equals("null"))){
					message.setText(text);
				}
								
			}
			
			return message; 
			
			
		}

		
		protected Student getStudentFromID(int studentID) throws SQLException{
			ResultSet rs = null;
			Student student = new Student(null);
			try {
				String query = "SELECT * FROM datagiver WHERE DatagiverID=" + studentID +";";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				String username = rs.getString("brukernavn");
				student = getStudent(username);
			} 
			
			
			return student; 
		}

		


		public Student getStudent(String username) throws SQLException {
			
			ResultSet rs = null;
			Student student = new Student(username);
			
			try {
				String query = "SELECT * FROM datagiver WHERE brukernavn='" + username +"';";
				Statement stmt = getStatement();
				
				if(stmt.execute(query)) {
					rs = stmt.getResultSet();
					if (!rs.isBeforeFirst() ) {    
					    	return null;
					} 
				} 
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			
			
			while(rs.next()) {
				Integer studentID = rs.getInt("DatagiverID");
				if(!(studentID==0)) {
					student.setStudentID(studentID);
				}
				
				String password = rs.getString("passord");
				if(!(password.equals("null"))) {
					student.setPassword(password);
				}
					
				Integer faculty = rs.getInt("fakultet");
				if(!(faculty==0)) {
					student.setFaculty(switchFakultetIDtoName(faculty));
				}
				
				boolean isAnonymous = rs.getBoolean("anonymitet");
				student.setAnonymous(isAnonymous);
				
				
				String firstName = rs.getString("fornavn");
				if(!(firstName.equals("null"))) {
					student.setFirstName(firstName);
				}
				
				String secondName = rs.getString("etternavn");
				if(!(secondName.equals("null"))) {
					student.setSecondName(secondName);
				}
				
				String sex = rs.getString("kjonn");
				if(!(sex.equals("null")) ) {
					student.setSex(sex);
				}
					
				String email = rs.getString("email");
				if(!(email.equals("null"))) {
					student.setEmail(email);
				}
				
				String phoneNumber = rs.getString("telefonNr");
				if(phoneNumber != null) {
					student.setPhoneNumber(phoneNumber);
				}
				
				Integer nurseID = rs.getInt("HelsesosterID");
				if(!(nurseID==0)) {
					Nurse nurse = getNurseFromID(nurseID);
					student.setNurse(nurse);
				}
				
				String notat = rs.getString("notat");
				if((notat != null)) {
					student.setNotat(notat);
				}
				Integer studentId = rs.getInt("DatagiverID");
				if(!(studentId == 0)) {
					student.setStudentID(studentId);
				}
				
			}
			
			return student;
		}
}
