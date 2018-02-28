package tdt4140.gr1835.app.core;

import java.util.Collection;
import java.sql.*;

public class ConnectionSQL implements UserDatabaseHandler{

	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/"
			+ "jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35";
	
	
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
			String query = "INSERT INTO helsesoster(brukernavn, passord, fakultet, fornavn"
					+ ", etternavn, email, telefonNr) VALUES (" + nurse.getUsername() + ", " +
					nurse.getPassword() + ", " + nurse.getFaculty() + ", " + nurse.getFirstName()
					+ ", " + nurse.getSecondName() + ", " + nurse.getEmail() + ", " + 
					nurse.getPhoneNumber() + ");";
			Statement stmt = getStatement();
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
	}

	@Override
	public void updateNurse(Nurse nurse) throws SQLException{
		try {
			//Må matche på brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			String query = "UPDATE helsesoster(brukernavn, passord, fakultet, fornavn" //Denne er ikke ferdig, må skrive update query
					+ ", etternavn, email, telefonNr) VALUES (" + nurse.getUsername() + ", " +
					nurse.getPassword() + ", " + nurse.getFaculty() + ", " + nurse.getFirstName()
					+ ", " + nurse.getSecondName() + ", " + nurse.getEmail() + ", " + 
					nurse.getPhoneNumber() + ");";
			Statement stmt = getStatement();
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}
	@Override
	public Nurse getNurse(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Student getStudent(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStudent(Student student) throws SQLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Student> getStudents(Nurse nurse) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	
	

}
