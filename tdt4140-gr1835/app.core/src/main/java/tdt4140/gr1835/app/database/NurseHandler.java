package tdt4140.gr1835.app.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tdt4140.gr1835.app.core.Nurse;

public class NurseHandler extends AbstractSQLHandler {

	private StudentHandler studentHandler;

	public void setStudentHandler(StudentHandler studentHandler) {
		this.studentHandler = studentHandler;
	}
	
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
	
	
	public void deleteNurse(Nurse nurse) throws SQLException{
		try {
			Statement stmt = getStatement();
			
			String query = "DELETE from helsesoster WHERE brukernavn='" + nurse.getUsername()
			+ "';";
			
			stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
		
	}
	
	
	public void updateNurse(Nurse nurse) throws SQLException{
		try {
			//M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man kan endre bruker.
			Statement stmt = getStatement();
			String faculty = nurse.getFaculty();
			
			String query = "UPDATE helsesoster SET passord= '" + nurse.getPassword() 
			+ "', fakultet= " + switchFacultyNametoID(faculty) + ", fornavn= '" + nurse.getFirstName()
			+ "', etternavn= '" + nurse.getSecondName() + "', email= '" + nurse.getEmail() 
			+ "', telefonNr= " + nurse.getPhoneNumber()
			+ " WHERE brukernavn = '" + nurse.getUsername() + "';";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}
	
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
		closeConnection();
		return nurse; 
	}
	
}