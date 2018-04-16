package tdt4140.gr1835.app.database;

import java.sql.SQLException;
import java.sql.Statement;

import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

public class SurveyHandler extends AbstractSQLHandler{

	public void createSurvey(Table survey) throws SQLException {
		try {
			Statement stmt = getStatement();			
			
			String query = "INSERT INTO svarlogg(DatagiverID, svarString) VALUES (" +survey.getPersonID() + ",'" + survey.getSpm1() + "," + survey.getSpm2() + ","  + survey.getSpm3() + ","  + survey.getSpm4() + ","  + survey.getSpm5() + ","
					+ survey.getSpm6() + "," + survey.getSpm7() + ","  + survey.getSpm8() + "," + survey.getSpm9() + ","  + survey.getSpm10() + "');" ;
			stmt.executeUpdate(query); 
			
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		
	}
	
	
	public void deleteSurvey(Student student) throws SQLException{
		try {
			Statement stmt = getStatement();
			
			String query = "DELETE from svarlogg WHERE DatagiverID=" + getStudentID(student)
			+ ";";
			
			stmt.executeUpdate(query);
			
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
				
	}
	
}
