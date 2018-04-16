package tdt4140.gr1835.app.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tdt4140.gr1835.app.core.Nurse;
import tdt4140.gr1835.app.core.Student;
import tdt4140.gr1835.app.core.Table;

class StudentHandler extends AbstractSQLHandler {

	void createNewStudent(Student student) throws SQLException {
		// M� f� til at man ikke trenger � oppgi b�de helsesosterID og fakultet.
		// HelsesosterID burde komme automatisk n�r
		// man oppgir fakultet.
		try {
			Statement stmt = getStatement();
			String faculty = student.getFaculty();

			String query = "INSERT INTO datagiver(brukernavn, passord, fakultet, anonymitet, fornavn"
					+ ", etternavn, kjonn, email, telefonNr, HelsesosterID, notat) VALUES ('" + student.getUsername()
					+ "', '" + student.getPassword() + "', " + switchFacultyNametoID(faculty) + ", "
					+ student.isAnonymous() + ", '" + student.getFirstName() + "', '" + student.getSecondName() + "', '"
					+ student.getSex() + "', '" + student.getEmail() + "', " + student.getPhoneNumber() + ", "
					+ getNurseID(student.getNurse()) + ", '" + student.getNotat() + "');";

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}

	public List<Student> getStudents(Nurse nurse) throws SQLException {
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

	public void updateStudent(Student student) throws SQLException {
		try {

			// M� matche p� brukernavn. Brukernavn kan dermed ikke endres i vinduet der man
			// kan endre bruker
			Statement stmt = getStatement();
			String faculty = student.getFaculty();

			String query = "UPDATE datagiver SET passord= '" + student.getPassword() + "', fakultet= "
					+ switchFacultyNametoID(faculty) + ", anonymitet= " + student.isAnonymous() + ", fornavn= '"
					+ student.getFirstName() + "', etternavn= '" + student.getSecondName() + "', kjonn= '"
					+ student.getSex() + "', email= '" + student.getEmail() + "', telefonNr= "
					+ student.getPhoneNumber() + ", notat= '" + student.getNotat() + "' WHERE brukernavn = '"
					+ student.getUsername() + "';";

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}

	public void deleteStudent(Student student) throws SQLException {
		try {
			Statement stmt = getStatement();

			String query = "DELETE from datagiver WHERE brukernavn='" + student.getUsername() + "';";

			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		closeConnection();
	}


	public List<Table> getAnswers(Student student) throws SQLException { 

		List<Table> answers = new ArrayList<>();
		
		ResultSet rs = null;
		
		
		try {
			String query = "SELECT * FROM svarlogg WHERE DatagiverID="+ student.getStudentID() + ";";
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
			Timestamp tstamp = rs.getTimestamp("datoTid");
			
			answers.add(listToTableConverter(student, answer, tstamp));
		}
		closeConnection();
		return answers;
	}


	private NurseHandler nurseHandler;
	public void setNurseHandler(NurseHandler nurseHandler) {
		this.nurseHandler = nurseHandler;
	}

}
