package tdt4140.gr1835.app.core;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


//Man kan ikke opprette en student uten � fylle inn b�de fakultet og helses�ster.

public class ConnectionSQL implements UserDatabaseHandler{

	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35&useSSL=false";
 
	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbURL);
	}
	
	
	public void closeConnection() throws SQLException {
		getConnection().close();
	}
	
	
	
	public Statement getStatement() throws SQLException{
		Connection conn = getConnection();
		return conn.createStatement();
	}
	
	@Override
	public void createNewNurse(Nurse nurse) throws SQLException{
		try {
			Statement stmt = getStatement();
			String faculty;
			if (nurse.getFaculty()!=null) {
				faculty = nurse.getFaculty();
			}else {
				faculty = null;
			}
			
			String query = "INSERT INTO helsesoster(brukernavn, passord, fakultet, fornavn"
					+ ", etternavn, email, telefonNr) VALUES ('" + nurse.getUsername() + "', '" +
					nurse.getPassword() + "', " + switchFacultyNametoID(faculty) + ", '" + nurse.getFirstName()
					+ "', '" + nurse.getSecondName() + "', '" + nurse.getEmail() + "', " + 
					nurse.getPhoneNumber() + ");";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
		closeConnection();
		
	}
	
	@Override
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
					    throw new IllegalStateException("Denne brukeren eksisterer ikke i databasen"); //Dette er burde vi endre på slik at den kanskje returnerer null isteden
					} 
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				
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
			closeConnection();
			return nurse; 
		}
	
	@Override
	public void updateNurse(Nurse nurse) throws SQLException{
		try {
			//M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			Statement stmt = getStatement();
			String faculty = nurse.getFaculty();
			
			String query = "UPDATE helsesoster SET passord= '" + nurse.getPassword() 
			+ "', fakultet= " + switchFacultyNametoID(faculty) + ", fornavn= '" + nurse.getFirstName()
			+ "', etternavn= '" + nurse.getSecondName() + "', email= '" + nurse.getEmail() 
			+ "', telefonNr= " + nurse.getPhoneNumber()
			+ "WHERE brukernavn = '" + nurse.getUsername() + "';";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}
	
	public void deleteNurse(Nurse nurse) throws SQLException{
		try {
			Statement stmt = getStatement();
			
			String query = "DELETE from helsesoster WHERE brukernavn='" + nurse.getUsername()
			+ "';";
			
			stmt.executeUpdate(query);
			//System.out.println(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
		
	}
	
	@Override
	public Student getStudent(String username) throws SQLException {
		
		ResultSet rs = null;
		Student student = new Student(username);
		
		try {
			String query = "SELECT * FROM datagiver WHERE brukernavn='" + username +"';";
			Statement stmt = getStatement();
			
			if(stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
		
		
		while(rs.next()) {
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
			
		}
		closeConnection();
		return student;
	}
	
	
	private Nurse getNurseFromID(int nurseID) throws SQLException{
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
		
		closeConnection();
		return nurse; 
	}
	
	@Override
	
	public void createNewStudent(Student student) throws SQLException {
		//M� f� til at man ikke trenger � oppgi b�de helsesosterID og fakultet. HelsesosterID burde komme automatisk n�r
		//man oppgir fakultet.
		try {
			Statement stmt = getStatement();
			String faculty = student.getFaculty();
			
			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kjonn, email, telefonNr, HelsesosterID) VALUES ('" + student.getUsername() + "', '" +
					student.getPassword() + "', " + switchFacultyNametoID(faculty) + ", " + student.isAnonymous() + ", '"
					+ student.getFirstName()
					+ "', '" + student.getSecondName() + "', '" + student.getSex() + "', '" +
					student.getEmail() + "', " + 
					student.getPhoneNumber() + ", " + getNurseID(student.getNurse()) + ");";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}

	@Override
	public void updateStudent(Student student) throws SQLException{
		try {
			
			
			//M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker
			Statement stmt = getStatement();
			String faculty = student.getFaculty();
			
			String query = "UPDATE datagiver SET passord= '" + student.getPassword() 
			+ "', fakultet= " + switchFacultyNametoID(faculty) + ", anonymitet= " + student.isAnonymous()
			+ ", fornavn= '" + student.getFirstName() 
			+ "', etternavn= '" + student.getSecondName() +"', kjonn= '" + student.getSex()
			+ "', email= '" + student.getEmail() 
			+ "', telefonNr= " + student.getPhoneNumber()
			+ " WHERE brukernavn = '" + student.getUsername() + "';";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}
	
	public void deleteStudent(Student student) throws SQLException{
		try {
			Statement stmt = getStatement();
			
			String query = "DELETE from datagiver WHERE brukernavn='" + student.getUsername()
			+ "';";
			
			stmt.executeUpdate(query);
			//System.out.println(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}

	@Override
    public List<Student> getStudents(Nurse nurse) throws Exception {
        List<Student> students = new ArrayList<Student>();
        
        String nurseFaculty = nurse.getFaculty();
        Integer faculty = switchFacultyNametoID(nurseFaculty);
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM datagiver WHERE fakultet=" + faculty +";";
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
            Student student = getStudent(username);
            students.add(student);
        }
        closeConnection();
        return students;
    }

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
	
	@Override
	public List<Table> getAnswers(Student student) throws SQLException { 

		List<Table> answers = new ArrayList<>();
		
		ResultSet rs = null;
		int studentID = getStudentID(student);
		
		try {
			String query = "SELECT * FROM svarlogg WHERE DatagiverID="+ studentID + ";";
			Statement stmt = getStatement();
			
			if(stmt.execute(query)) {
				rs = stmt.getResultSet();
			}
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
		while(rs.next()) {
			String answer = rs.getString("svarString");
			answers.add(listToTableConverter(student, answer));
		}
		closeConnection();
		return answers;
	}
	
	public static void main(String[] args) throws Exception {
		ConnectionSQL database= new ConnectionSQL();
		
		Student student= new Student("heisann");
		student.setFaculty("AD");
		student.setSex("mann");
		student.setEmail("heihei@gmail.com");
		database.createNewStudent(student);
		
		
//	 	Nurse testNurse = new Nurse("cathrine");
//		testNurse.setPassword("c");
//		testNurse.setFirstName("Cathrine");
//		testNurse.setSecondName("Arke");
//		testNurse.setFaculty("IE");
//		testNurse.setEmail("sverress@stud.tnu");
//		database.createNewNurse(testNurse);
//		System.out.println(database.getNurse("cathrine"));
		
	}
	
	private Table listToTableConverter(Student student, String anslist) throws SQLException {
		int sum=0;
		List<String> stringList= Arrays.asList(anslist.split(",")); //Deler opp strengen på komma, og lager en liste av den
		List<Integer> intlist=new ArrayList<>();
		for(String c:stringList) {
			sum+=Integer.parseInt(c); //Summerer opp for totalen
			intlist.add(Integer.parseInt(c)); //Legger til svar i svarliste kalt intlist
		}
		//Returnerer Tableobjekt med student og svar
		return new Table(getStudentID(student),intlist.get(0), intlist.get(1),intlist.get(2),intlist.get(3),intlist.get(4),intlist.get(5),intlist.get(6), intlist.get(7), intlist.get(8), intlist.get(9), sum);
	}
	
	//Ser nå at det kan være hensiktsmessig å legge inn studentID som et felt i Student-klassen for å slippe ny spørring til databasen
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
		
		closeConnection();
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
		closeConnection();
		return nurseID;
	}

	
}

