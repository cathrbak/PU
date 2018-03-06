package tdt4140.gr1835.app.core;

import java.sql.*;
import java.util.Collection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionSQL implements UserDatabaseHandler{
		
	private final String dbURL="jdbc:mysql://mysql.stud.ntnu.no/jonahag_prosjektdb?user=jonahag_pu35&password=gruppe35";
	
	
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
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
	}
	
	@Override
		public Nurse getNurse(String username) throws SQLException {
			//M� finne en m�te � gj�re om ResultSet til et Nurse-objekt. Returnerer forel�pig kun et ResultSet-objekt.
			//Sjekk hvilke metoder man kan kalle p� ResultSet. Feks en while der for hver rs.next() s� legges feltet inn i
			//et string array. F�rste felt skal dog ikke v�re med, da dette er HelsesosterID.
			ResultSet rs = null;
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
			
			return null; //Skal returnere enten rs som et ResultSet eller et Nurse-objekt
		}
	
	@Override
	public void updateNurse(Nurse nurse) throws SQLException{
		try {
			//M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
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
	public Student getStudent(String username) {
		//Samme caset her som i getNurse()
		
		ResultSet rs = null;
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
		
		return null;//Skal returnere enten rs som et ResultSet eller et Student-objekt
	}
	
	@Override
	public void createNewStudent(Student student) throws SQLException {
		//M� f� til at man ikke trenger � oppgi b�de helsesosterID og fakultet. HelsesosterID burde komme automatisk n�r
		//man oppgir fakultet.
		try {
			Statement stmt = getStatement();
			
			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kj�nn, email, telefonNr) VALUES ('" + student.getUsername() + "', '" +
					student.getPassword() + "', '" + student.getFaculty() + "', " + student.isAnonymous() + ", '"
					+ student.getFirstName()
					+ "', '" + student.getSecondName() + "', '" + student.getEmail() + "', " + 
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
			//M� f� til at man ikke trenger � oppgi b�de helsesosterID og fakultet. HelsesosterID burde komme automatisk n�r
			//man oppgir fakultet.
			
			//M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			Statement stmt = getStatement();
			
			String query = "UPDATE datagiver SET passord= '" + student.getPassword() 
			+ "', fakultet= '" + student.getFaculty() + "', anonymitet= " + student.isAnonymous()
			+ ", fornavn= '" + student.getFirstName() 
			+ "', etternavn= '" + student.getSecondName() +"', kj�nn= '" + student.getSex()
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
		//Her er ogs� samme case som getNurse og GetStudent
		
		return null;
	}
	
	
	
	
	
	

	
	

}
