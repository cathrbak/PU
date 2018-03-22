package tdt4140.gr1835.app.core;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
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
			System.out.println(query);
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
				if(!rs.wasNull()) {
					nurse.setPassword(password);
				}
								
				Integer faculty = rs.getInt("fakultet");
				if(!rs.wasNull()) {
					nurse.setFaculty(switchFakultetIDtoName(faculty));
				}
					
				String firstName = rs.getString("fornavn");
				if(!rs.wasNull()) {
					nurse.setFirstName(firstName);
				}
				
				String secondName = rs.getString("etternavn");
					nurse.setSecondName(secondName);
				
				String email = rs.getString("email");
				if(!rs.wasNull()) {
					nurse.setEmail(email);
				}
				
				String phoneNumber = rs.getString("telefonNr");
				if(!rs.wasNull()) {
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
			
			String query = "DELETE from datagiver WHERE brukernavn='" + nurse.getUsername()
			+ "';";
			
			stmt.executeUpdate(query);
			System.out.println(query);
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
			if(!rs.wasNull()) {
				student.setPassword(password);
			}
				
			Integer faculty = rs.getInt("fakultet");
			if(!rs.wasNull()) {
				student.setFaculty(switchFakultetIDtoName(faculty));
			}
			
			boolean isAnonymous = rs.getBoolean("anonymitet");
			if(!rs.wasNull()) {
				student.setAnonymous(isAnonymous);
			}
			
			String firstName = rs.getString("fornavn");
			if(!rs.wasNull()) {
				student.setFirstName(firstName);
			}
			
			String secondName = rs.getString("etternavn");
			if(!rs.wasNull()) {
				student.setSecondName(secondName);
			}
			
			String sex = rs.getString("kjonn");
			if(!rs.wasNull()) {
				student.setSex(sex);
			}
				
			String email = rs.getString("email");
			if(!rs.wasNull()) {
				student.setEmail(email);
			}
			
			String phoneNumber = rs.getString("telefonNr");
			if(!rs.wasNull()) {
				student.setPhoneNumber(phoneNumber);
			}
			
			int nurseID = rs.getInt("HelsesosterID");
			if(!rs.wasNull()) {
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
			System.out.println(query);
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
	
	public void createSurvey(Table survey) throws SQLException {
		try {
			Statement stmt = getStatement();			
			
			String query = "INSERT INTO svarlogg(DatagiverID, svarString) VALUES ('" +survey.getPersonID() + "', "  + 
					survey.getSpm1() + survey.getSpm2() + survey.getSpm3() + survey.getSpm4() + survey.getSpm5() +
					survey.getSpm6() + survey.getSpm7() + survey.getSpm8() + survey.getSpm8() + survey.getSpm9() + survey.getSpm10() + ");";
	
			stmt.executeUpdate(query); 
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}
	
	
	// sletter nå alle spørreundersøkseler knyttet til en studentID. Bør få inn dateTime her for å diskriminere undersøkelsene ytterligere.
	public void deleteSurvey(Student student) throws SQLException{
		try {
			Statement stmt = getStatement();
			
			String query = "DELETE from svarlogg WHERE datagiverID='" + getStudentID(student)
			+ "';";
			
			stmt.executeUpdate(query);
			System.out.println(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();			
	}
	
	
	//Metode for å hente ut spørreundersøkelse når time/date er oppe og går
	/*public Table getAnswerByTimeDate(List<Table> answer, boolean refresh) {
		for (Table answer : getTotal()){
			if (time/date == answer.getTimeDate() || (time/date != null && time/date.equals(answer.getTimeDate()))) {
				return answer;
			}
		}
		return null;
	}
*/
	
	
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
		
		Table survey= new Table(2,1,2,3,4,5,4,3,2,1,1,26);
		//System.out.println(tableToListConverter(survey));
		database.createSurvey(survey);

		
//		Student nora = new Student("norak");
//		nora.getUsername();
//		database.deleteSurvey(nora);
		
		
//	 	Nurse testNurse = new Nurse("cathrine");
//		testNurse.setPassword("c");
//		testNurse.setFirstName("Cathrine");
//		testNurse.setSecondName("Arke");
//		testNurse.setFaculty("IE");
//		testNurse.setEmail("sverress@stud.tnu");
//		database.createNewNurse(testNurse);
//		System.out.println(database.getNurse("cathrine"));
		
	}
	
	private static String tableToStringConverter (Table survey) {
		List<Integer> intList = new ArrayList<>();
		intList.add(survey.getSpm1());
		intList.add(survey.getSpm2());
		intList.add(survey.getSpm3());
		intList.add(survey.getSpm4());
		intList.add(survey.getSpm5());
		intList.add(survey.getSpm6());
		intList.add(survey.getSpm7());
		intList.add(survey.getSpm8());
		intList.add(survey.getSpm9());
		intList.add(survey.getSpm10());
		List<String> stringList = intList.stream().map(Object::toString).collect(Collectors.toList());
		
		String listString = String.join(",", stringList);
		return listString;
		
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
		
		closeConnection();
		return loggID;
	}

	
}

