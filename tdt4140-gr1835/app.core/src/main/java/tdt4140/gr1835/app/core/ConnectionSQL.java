package tdt4140.gr1835.app.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionSQL implements UserDatabaseHandler{
		
	// Har trøbbel med fakultet-attributtene. De er lagret som nøkkelen i fakultettabellen, altså FakultetID i databasen, 
	//men attributtet i Student og Nurse-objektene her i prosjektet er navneforkortelsen...
	
	
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
			
			String query = "INSERT INTO helsesoster(brukernavn, passord, fakultet, fornavn"
					+ ", etternavn, email, telefonNr) VALUES ('" + nurse.getUsername() + "', '" +
					nurse.getPassword() + "', '" + nurse.getFaculty() + "', '" + nurse.getFirstName()
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
				
				String faculty = rs.getString("fakultet");
				nurse.setFaculty(faculty);
				
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
			
			String query = "UPDATE helsesoster SET passord= '" + nurse.getPassword() 
			+ "', fakultet= '" + nurse.getFaculty() + "', fornavn= '" + nurse.getFirstName()
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
			
			String faculty = rs.getString("fakultet");
			student.setFaculty(faculty);
			
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
		
		if(rs.next()) {
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
			
			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kjonn, email, telefonNr) VALUES ('" + student.getUsername() + "', '" +
					student.getPassword() + "', '" + student.getFaculty() + "', " + student.isAnonymous() + ", '"
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
			
			String query = "UPDATE datagiver SET passord= '" + student.getPassword() 
			+ "', fakultet= '" + student.getFaculty() + "', anonymitet= " + student.isAnonymous()
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
	public Collection<Student> getStudents(Nurse nurse) {
		Collection<Student> students = new ArrayList<Student>();
		
	//	int faculty = nurse.getFaculty(); //Vil at faculty skal være FakultetID som det er i databasen, altså et int.
		
		
		
		
		
		return students;
	}
	
	
	/*
	public static void main(String[] args) throws SQLException {
		ConnectionSQL con = new ConnectionSQL();
		con.getConnection();
		Nurse nurse = new Nurse("soster"); 
		System.out.println(nurse.getUsername());
	}
	*/

	

	
	

}
