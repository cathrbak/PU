package tdt4140.gr1835.app.core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionSQL implements UserDatabaseHandler{
		

	
	
	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35&useSSL=false";
	
	
	
	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(dbURL);
		
	}
	private Statement getStatement() throws SQLException{
		Connection conn = getConnection();
		return conn.createStatement();
	}
	
	@Override
	public void createNewNurse(Nurse nurse) throws SQLException{
		try {
			Statement stmt = getStatement();
			String faculty = nurse.getFaculty();

			
			String query = "INSERT INTO helsesoster(brukernavn, passord, fakultet, fornavn"
					+ ", etternavn, email, telefonNr) VALUES ('" + nurse.getUsername() + "', '" +
					nurse.getPassword() + "', " + switchInsert(faculty) + ", '" + nurse.getFirstName()
					+ "', '" + nurse.getSecondName() + "', '" + nurse.getEmail() + "', " + 
					nurse.getPhoneNumber() + ");";
			
			stmt.executeUpdate(query);
			System.out.println(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
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
				}
			}
			catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			}
			
			while(rs.next()) {
				String password = rs.getString("passord");
				nurse.setPassword(password);
				
				Integer faculty = rs.getInt("fakultet");
				nurse.setFaculty(switchFakultetIDtoName(faculty));
				
				String firstName = rs.getString("fornavn");
				nurse.setFirstName(firstName);
				
				String secondName = rs.getString("etternavn");
				nurse.setSecondName(secondName);
				
				String email = rs.getString("email");
				nurse.setEmail(email);
				
				String phoneNumber = rs.getString("telefonNr");
				nurse.setPhoneNumber(phoneNumber);
				
			}
			
			return nurse; 
		}
	
	@Override
	public void updateNurse(Nurse nurse) throws SQLException{
		try {
			//Mï¿½ matche pï¿½ brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			Statement stmt = getStatement();
			String faculty = nurse.getFaculty();
			
			String query = "UPDATE helsesoster SET passord= '" + nurse.getPassword() 
			+ "', fakultet= " + switchInsert(faculty) + ", fornavn= '" + nurse.getFirstName()
			+ "', etternavn= '" + nurse.getSecondName() + "', email= '" + nurse.getEmail() 
			+ "', telefonNr= " + nurse.getPhoneNumber()
			+ "WHERE brukernavn = '" + nurse.getUsername() + "';";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
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
			student.setPassword(password);
			
			Integer faculty = rs.getInt("fakultet");
			student.setFaculty(switchFakultetIDtoName(faculty));
			
			boolean isAnonymous = rs.getBoolean("anonymitet");
			student.setAnonymous(isAnonymous);
			
			String firstName = rs.getString("fornavn");
			student.setFirstName(firstName);
			
			String secondName = rs.getString("etternavn");
			student.setSecondName(secondName);
			
			String sex = rs.getString("kjonn");
			student.setSex(sex);
			
			String email = rs.getString("email");
			student.setEmail(email);
			
			String phoneNumber = rs.getString("telefonNr");
			student.setPhoneNumber(phoneNumber);
			
			int nurseID = rs.getInt("HelsesosterID");
			Nurse nurse = getNurseFromID(nurseID);
			student.setNurse(nurse);
		}
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
		
		 
		return nurse; 
	}
	@Override
	public void createNewStudent(Student student) throws SQLException {
		//Mï¿½ fï¿½ til at man ikke trenger ï¿½ oppgi bï¿½de helsesosterID og fakultet. HelsesosterID burde komme automatisk nï¿½r
		//man oppgir fakultet.
		try {
			Statement stmt = getStatement();
			String faculty = student.getFaculty();
			
			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kjonn, email, telefonNr) VALUES ('" + student.getUsername() + "', '" +
					student.getPassword() + "', " + switchInsert(faculty) + ", " + student.isAnonymous() + ", '"
					+ student.getFirstName()
					+ "', '" + student.getSecondName() + "', '" + student.getSex() + "', '" +
					student.getEmail() + "', " + 
					student.getPhoneNumber() +");";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
	}

	@Override
	public void updateStudent(Student student) throws SQLException{
		try {
			
			
			//Mï¿½ matche pï¿½ brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker
			Statement stmt = getStatement();
			String faculty = student.getFaculty();
			
			String query = "UPDATE datagiver SET passord= '" + student.getPassword() 
			+ "', fakultet= " + switchInsert(faculty) + ", anonymitet= " + student.isAnonymous()
			+ ", fornavn= '" + student.getFirstName() 
			+ "', etternavn= '" + student.getSecondName() +"', kjonn= '" + student.getSex()
			+ "', email= '" + student.getEmail() 
			+ "', telefonNr= " + student.getPhoneNumber()
			+ "WHERE brukernavn = '" + student.getUsername() + "';";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	@Override
    public Collection<Student> getStudents(Nurse nurse) throws Exception {
        Collection<Student> students = new ArrayList<Student>();
        
        Integer faculty = new Integer(null); 
        String nurseFaculty = nurse.getFaculty();
        faculty = switchInsert(nurseFaculty);
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
        return students;
    }
	


	public int switchInsert(String faculty) {
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
	
	
	
	//Sverreeeeeeee! Her kommer den metoden vi snakket om i sted. Den kjører en spørring på svarlogg
	// og får ut en liste med strenger, der hver streng er en representasjon av svarene på en undersøkelse
	// f.eks "1,3,4,2,4,2,2"
	
	@Override
	public List<String> getAnswers(Student student) throws SQLException { 

		List<String> answers = new ArrayList<String>();
		
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
			
			answers.add(answer);
			
			
		}
		
		
		
		return answers;
	}
	private int getStudentID(Student student) throws SQLException {
		Integer studentID = null;
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM datagiver WHERE brukernavn=" + student.getUsername() +";";
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

}

