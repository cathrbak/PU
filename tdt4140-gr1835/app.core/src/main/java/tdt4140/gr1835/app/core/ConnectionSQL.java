package tdt4140.gr1835.app.core;

import java.util.Collection;
import java.sql.*;

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
			//Må finne en måte å gjøre om ResultSet til et Nurse-objekt. Returnerer foreløpig kun et ResultSet-objekt.
			//Sjekk hvilke metoder man kan kalle på ResultSet. Feks en while der for hver rs.next() så legges feltet inn i
			//et string array. Første felt skal dog ikke være med, da dette er HelsesosterID.
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
			//Må matche på brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
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
		//Må få til at man ikke trenger å oppgi både helsesosterID og fakultet. HelsesosterID burde komme automatisk når
		//man oppgir fakultet.
		try {
			Statement stmt = getStatement();
			
			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kjønn, email, telefonNr) VALUES ('" + student.getUsername() + "', '" +
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
			//Må få til at man ikke trenger å oppgi både helsesosterID og fakultet. HelsesosterID burde komme automatisk når
			//man oppgir fakultet.
			
			//Må matche på brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			Statement stmt = getStatement();
			
			String query = "UPDATE datagiver SET passord= '" + student.getPassword() 
			+ "', fakultet= '" + student.getFaculty() + "', anonymitet= " + student.isAnonymous()
			+ ", fornavn= '" + student.getFirstName() 
			+ "', etternavn= '" + student.getSecondName() +"', kjønn= '" + student.getSex()
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
		//Her er også samme case som getNurse og GetStudent
		
		return null;
	}
	
	
	
	
	
	

	
	

}
